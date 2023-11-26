export default {
    data() {
        return {
            cv: null,
            nom: null,
            prenom: null,
            axios: null,
            candidateId: null,
            error: null,
            activities: []
        };
    },

    methods: {
        onRouteChange() {
            // Le reste du code pour extraire l'ID et appeler l'API reste inchangé
            const hash = window.location.hash;
            const regex = /\/cv\/(\d+)/;
            const match = hash.match(regex);
            const candidatId = match ? match[1] : null;

            console.log("Candidat id " + candidatId)

            if (!candidatId || isNaN(parseInt(candidatId))) {
                this.error = 'L\'ID du candidat est invalide';
                return;
            }

            // Vérifiez si l'ID a changé avant d'appeler l'API
            if (candidatId === this.candidateId) {
                return;
            }

            this.candidateId = candidatId;
            this.error = null;

            this.axios.get(`/cvs/${this.candidateId}`)
                .then(response => {
                    console.log("request ")
                    this.activities = response.data.activities

                })
                .catch(error => {
                    console.log("error")
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
            headers: {'Content-Type': 'application/json'},
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


    template:
        `
          <div class="container">
            <h1 class="mb-4">CurriculumVitae Details</h1>
            <template v-if="activities">
              <ul class="list-group">
                <li class="list-group-item">
                  <strong>Activités :</strong>
                  <ul class="list-group">
                    <li v-for="activity in activities" :key="activity.id" class="list-group-item">
                      <strong>Type d'activité:</strong> {{ activity.activityType }}<br>
                      <strong>Année:</strong> {{ activity.activityYear }}<br>
                      <strong>Titre:</strong> {{ activity.title }}<br>
                      <strong>Adresse Web:</strong> {{ activity.adresseWeb }}<br>
                      <strong>Description:</strong> {{ activity.description }}<br>
                      <!-- Ajoutez d'autres champs au besoin -->
                    </li>
                  </ul>
                </li>
              </ul>
            </template>
          </div>



        `
}