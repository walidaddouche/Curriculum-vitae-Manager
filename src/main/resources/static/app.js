import CandidatDetail from './candidatDetail.js';
import NotFound from './notFound.js';
import Candidats from "./candidats.js";
const routes = {
    '/': Candidats,
    '/candidatDetail': CandidatDetail ,
};

const app = Vue.createApp({
    data() {
        return {
            currentPath: window.location.pathname
        };
    },
    computed: {
        currentView() {
            return routes[this.currentPath] || NotFound;
        }
    },
    mounted() {
        window.addEventListener('popstate', () => {
            this.currentPath = window.location.pathname;
        });
    }
});
app.use(routes)
app.mount('#myApp');
