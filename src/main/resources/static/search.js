export default {
    data() {
        return {
            searchType: "nom", // Valeur par défaut
            searchQuery: "",
            searchResults: [],
            axios: null,
            error: null,
        };
    },
    mounted() {
        this.axios = axios.create({
            baseURL: "http://localhost:8080/",
            timeout: 1000,
            headers: { "Content-Type": "application/json" },
        });
    },
    methods: {
        submitSearch() {
            // Vérifie que le terme de recherche a au moins trois lettres
            if (this.searchQuery.length >= 3) {
                console.log(this.searchType)
                this.axios
                    .get(`/candidat/search/${this.searchType}?query=${this.searchQuery}`)
                    .then((response) => {
                        this.searchResults = response.data;
                    })
                    .catch((error) => {
                        this.error =
                            "Une erreur s'est produite lors de la récupération des résultats de recherche.";
                    });
            }
        },
    },
    template :
    `<div class="container mt-4">
      <h2 class="mb-4">Recherche de Candidats</h2>

      <!-- Formulaire de recherche -->
      <form @submit.prevent="submitSearch">
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
        <p class="alert alert-warning">Aucun résultat trouvé.</p>
      </div>
    </div>
    `
};
