export default {
    data() {
        return {
            candidate: null,
            nom: null,
            prenom: null,
            email: null,
            siteweb: null,
            dateDeNaissance: null,
            axios: null,
            candidateId: null,
            error: null,
        };
    },

    methods: {
        onRouteChange() {
            // Le reste du code pour extraire l'ID et appeler l'API reste inchangé
            const hash = window.location.hash;
            const regex = /#\/candidatDetail\/(\d+)/;
            const match = hash.match(regex);
            const candidatId = match ? match[1] : null;

            if (!candidatId || isNaN(candidatId)) {
                this.error = 'L\'ID du candidat est invalide';
                return;
            }

            // Vérifiez si l'ID a changé avant d'appeler l'API
            if (candidatId === this.candidateId) {
                return;
            }

            this.candidateId = candidatId;
            this.error = null;

            this.axios.get(`/candidat/${this.candidateId}`)
                .then(response => {
                    this.candidate = response.data;
                    this.nom = this.candidate.nom;
                    this.prenom = this.candidate.prenom;
                    this.email = this.candidate.email;
                    this.siteweb = this.candidate.siteweb;
                    this.dateDeNaissance = this.candidate.dateDeNaissance;
                })
                .catch(error => {
                    this.error = 'Une erreur s\'est produite lors de la récupération des données du candidat';
                });
        },

        onWindowHashChange() {
            this.onRouteChange();
            // Log du changement de hash de la fenêtre à la console
            console.log(`Changement de hash de la fenêtre vers "${window.location.hash}"`);
        }
    },

    mounted() {
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });

        // Appel initial pour s'assurer que les données sont chargées au premier rendu
        this.onRouteChange();

        // Ajout de l'écouteur d'événements sur le changement de hash de la fenêtre
        window.addEventListener('hashchange', this.onWindowHashChange);
    },

    // Assurez-vous de supprimer l'écouteur d'événements lors de la destruction du composant
    beforeDestroy() {
        window.removeEventListener('hashchange', this.onWindowHashChange);
    },

    template: `
      <div>
      <h1 class="mb-4">CandidatDetail</h1>
      <template v-if="candidate">
        <ul class="list-group">
          <li class="list-group-item"><strong>Nom :</strong> {{ nom }}</li>
          <li class="list-group-item"><strong>Prénom :</strong> {{ prenom }}</li>
          <li class="list-group-item"><strong>Email :</strong> {{ email }}</li>
          <li class="list-group-item"><strong>Siteweb :</strong> {{ siteweb }}</li>
          <li class="list-group-item"><strong>Date de Naissance :</strong> {{ dateDeNaissance }}</li>
        </ul>
      </template>

      <template v-else>
        <div class="alert alert-danger mt-4" role="alert">
          <p v-if="error">L'identifiant du candidat est invalide</p>
          <p v-else>Une erreur s'est produite lors de la récupération des données du candidat</p>
        </div>
      </template>
      </div>
    `,
};
