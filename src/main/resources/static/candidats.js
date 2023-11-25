export default {
    data() {
        return {
            candidats: [],
            axios: null,
            currentPage: 1,
            pageSize: 10,
            totalPages: 1,
            totalElements: 0,
            error: null
        };
    },
    mounted() {
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });
        this.fetchCandidats();
    },

    methods: {
        fetchCandidats() {
            console.log("PATH : "  + `/candidat/candidats?withDetails=false&page=${this.currentPage - 1}&size=${this.pageSize}`)
            this.axios
                .get(`/candidat/candidats?withDetails=false&page=${this.currentPage - 1}&size=${this.pageSize}`)
                .then(response => {
                    this.candidats = response.data;
                    this.totalPages = parseInt(response.headers['x-total-pages']);
                    this.totalElements = parseInt(response.headers['x-total-elements']);
                })
                .catch(error => {
                    this.error = 'Une erreur s\'est produite lors de la récupération des données du candidat';
                });
        },
        changePage(page) {
            this.currentPage = page;
            this.fetchCandidats();
        },
        changePageSize(size) {
            this.pageSize = size;
            this.fetchCandidats();
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
              <th>Email</th>
              <th>Détails</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="candidat in candidats" :key="candidat.id">
              <td>{{ candidat.nom }}</td>
              <td>{{ candidat.prenom }}</td>
              <td>{{ candidat.email }}</td>
              <td><a :href="'#/candidatDetail/' + candidat.id">Voir les détails</a></td>
            </tr>
            </tbody>
          </table>
        </div>

        <nav aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item" :class="{ 'disabled': currentPage === 1 }">
              <a class="page-link" href="#" @click="changePage(currentPage - 1)">Previous</a>
            </li>
            <li class="page-item" v-for="page in totalPages" :key="page" :class="{ 'active': currentPage === page }">
              <a class="page-link" href="#" @click="changePage(page)">{{ page }}</a>
            </li>
            <li class="page-item" :class="{ 'disabled': currentPage === totalPages }">
              <a class="page-link" href="#" @click="changePage(currentPage + 1)">Next</a>
            </li>
          </ul>
        </nav>
        
      </div>
      
    `
};
