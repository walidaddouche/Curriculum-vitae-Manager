export default  {

    data(){
        return{
            candidats : [],
            axios : null
        }
    },
    mounted() {
            this.axios = axios.create({
                baseURL: 'http://localhost:8080/',
                timeout: 1000,
                headers: { 'Content-Type': 'application/json' },
            });
            this.fetchCandidats();
    },

    methods : {
        fetchCandidats() {
            this.axios.get(`/candidat/candidats`)
                .then(response => {
                    this.candidats = response.data;
                })
                .catch(error => {
                    this.error = 'Une erreur s\'est produite lors de la récupération des données du candidat';
                });
        }
    },

    template: `
      <div class="container mt-4">
        <h1 class="mb-4">Liste des Candidats</h1>

        <div class="table-responsive">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Email </th>
              <th>Détails</th> <!-- Nouvelle colonne pour les détails -->

            </tr>
            </thead>
            <tbody>
            <tr v-for="candidat in candidats" :key="candidat.id">
              <td>{{ candidat.nom }}</td>
              <td>{{ candidat.prenom }}</td>
              <td> {{candidat.email}}</td>
              <td>
              <td><a :href="'#/candidatDetail/' + candidat.id">Voir les détails</a></td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    `}



