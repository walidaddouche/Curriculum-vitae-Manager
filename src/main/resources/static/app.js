import CandidatDetail from './candidatDetail.js';
import NotFound from './notFound.js';
import Candidats from "./candidats.js";
import Search from "./search.js";
import search from "./search.js";

const routes = {
    '/': Candidats,
    '/candidatDetail/:id': CandidatDetail,
    '/search' :  Search

};

const app = Vue.createApp({
    data() {
        return {
            currentPath: window.location.hash,
        };
    },
    created() {
        window.addEventListener('hashchange', () => {
            this.currentPath = window.location.hash;
        });
    },
    computed: {
        currentView() {
            if (this.currentPath.startsWith('#/candidatDetail/')) {
                return CandidatDetail;
            }
            if (this.currentPath.startsWith('#/search/')) {
                return Search;
            }
            return routes[this.currentPath.slice(2) || '/'] || NotFound;
        },
    },
});

app.use(routes);
app.mount('#myApp');
