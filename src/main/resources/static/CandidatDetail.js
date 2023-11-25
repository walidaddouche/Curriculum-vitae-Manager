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
        };
    },

    mounted() {
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });

        // Récupérer l'ID du candidat depuis l'URL
        this.candidateId = 1;

        // Appel à l'API pour récupérer les données du candidat par son ID
        this.axios.get(`/candidat/${this.candidateId}`)
            .then(response => {
                this.candidate = response.data;
                // Assurez-vous que les propriétés sont correctement définies
                this.nom = this.candidate.nom;
                this.prenom = this.candidate.prenom;
                this.email = this.candidate.email;
                this.siteweb = this.candidate.siteweb;
                this.dateDeNaissance = this.candidate.dateDeNaissance;
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des données du candidat', error);
            });
    },

    template: `
      <div>
        <h1> CandidatDetail </h1>
        <p>ID du Candidat : {{ candidateId }}</p>

        <!-- Condition pour afficher les données uniquement si elles sont disponibles -->
        <template v-if="candidate">
          <p>Nom : {{ nom }}</p>
          <p>Prénom : {{ prenom }}</p>
          <p>Email : {{ email }}</p>
          <p>Siteweb : {{ siteweb }}</p>
          <p>Date de Naissance : {{ dateDeNaissance }}</p>
          <!-- Ajoutez d'autres propriétés selon vos besoins -->
        </template>
      </div>
    `,
};
