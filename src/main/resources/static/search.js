export default {
    data() {
        return {
            searchType: 'nom',
            searchQuery: '',
            searchResults: [],
            axios: null,
            error: null,
            searched: false,
            response: null,
            currentPage: 1,
            pageSize: 10,
            totalPages: 1,
            totalElements: 0,
        };
    },
    mounted() {
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });
        this.fetchSearchResults();
    },
    methods: {
        fetchSearchResults() {
            const pageQueryParam = this.currentPage ? `&page=${this.currentPage - 1}&size=${this.pageSize}` : '';

            if (this.searchQuery.length >= 3) {
                this.axios
                    .get(`/candidat/search/${this.searchType}?query=${this.searchQuery}${pageQueryParam}`)
                    .then((response) => {
                        this.response = response.data;
                        this.searchResults = response.data.content;
                        this.totalPages = response.data.totalPages;
                        this.totalElements = response.data.totalElements;
                        this.searched = true;
                    })
                    .catch((error) => {
                        this.error =
                            "Une erreur s'est produite lors de la récupération des résultats de recherche.";
                    });
            }
        },
        changePage(page) {
            this.currentPage = page;
            this.fetchSearchResults();
        },
        changePageSize(size) {
            this.pageSize = size;
            this.fetchSearchResults();
        },
        getPageRange() {
            const rangeStart = Math.max(1, this.currentPage - 5);
            const rangeEnd = Math.min(this.totalPages, rangeStart + 10);

            return Array.from({ length: rangeEnd - rangeStart + 1 }, (_, i) => rangeStart + i);
        },
    },
    template: `
      <div class="container mt-4">
        <h2 class="mb-4">Recherche de Candidats</h2>

        <!-- Formulaire de recherche -->
        <form @submit.prevent="fetchSearchResults">
          <div class="form-group">
            <label for="searchType">Rechercher par:</label>
            <select v-model="searchType" class="form-control" id="searchType">
              <option value="nom">Nom</option>
              <option value="prenom">Prénom</option>
            </select>
          </div>
          <div class="form-group">
            <label for="searchQuery">Recherche:</label>
            <input type="text" class="form-control" id="searchQuery" v-model="searchQuery">
          </div>
          <button type="submit" class="btn btn-primary" :disabled="searchQuery.length < 3">Rechercher</button>
        </form>

        <!-- Résultats de la recherche -->
        <div v-if="searchResults.length > 0" class="mt-4">
          <h3>Résultats de la recherche</h3>
          <ul class="list-group">
            <li v-for="result in searchResults" :key="result.id" class="list-group-item">
              {{ result.nom }} {{ result.prenom }} <a :href="'#/candidatDetail/' + result.id">détails</a>
            </li>
          </ul>
        </div>
        <div v-else class="mt-4">
          <p v-if="searched" class="alert alert-warning">Aucun résultat trouvé.</p>
        </div>

        <!-- Pagination -->
        <nav aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item" :class="{ 'disabled': currentPage === 1 }">
              <a class="page-link" href="#" @click="changePage(currentPage - 1)">Previous</a>
            </li>
            <li v-for="page in getPageRange()" :key="page" :class="{ 'active': currentPage === page }">
              <a class="page-link"  @click="changePage(page)">{{ page }}</a>
            </li>
            <li class="page-item" :class="{ 'disabled': currentPage === totalPages }">
              <a class="page-link"  @click="changePage(currentPage + 1)">Next</a>
            </li>
          </ul>
        </nav>
      </div>
    `,
};
