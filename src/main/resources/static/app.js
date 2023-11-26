import CandidatDetail from './candidatDetail.js';
import NotFound from './notFound.js';
import Candidats from "./candidats.js";
import Search from "./search.js";
import Cv from "./cv.js"
import CreatePerson from "./createPerson.js"

const routes = {
    '/': Candidats,
    '/candidatDetail/:id': CandidatDetail,
    '/search' :  Search,
    '/cv/:id' : Cv,
    '/create/person' : CreatePerson


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
            if (this.currentPath.startsWith('#/cv/')) {
                return Cv;
            }
            if(this.currentPath.startsWith("#/create/person")){
                return CreatePerson;
            }
            return routes[this.currentPath.slice(2) || '/'] || NotFound;
        },
    },
});

app.use(routes);
app.mount('#myApp');
