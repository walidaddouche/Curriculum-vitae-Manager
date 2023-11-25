import CandidatDetail from './candidatDetail.js';
import NotFound from './notFound.js';
import Candidats from "./candidats.js";

const routes = {
    '/': Candidats,
    '/candidatDetail/:id': CandidatDetail,
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
            return routes[this.currentPath.slice(2) || '/'] || NotFound;
        },
    },
});

app.use(routes);
app.mount('#myApp');
