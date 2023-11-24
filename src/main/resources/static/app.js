import CandidatDetail from './candidatDetail.js';
import NotFound from './notFound.js';
import Candidats from "./candidats.js";

const routes = {
    '/': Candidats,
    '/candidatDetail': CandidatDetail
};

const app = Vue.createApp({
    data() {
        return {
            currentPath: window.location.pathname
        };
    },
    computed: {
        currentView() {
            console.log("PATH" + window.location.pathname )

            return routes[this.currentPath] || NotFound;
        }
    },
    mounted() {
        window.addEventListener('popstate', () => {
            this.currentPath = window.location.pathname;
        });
    }
});

app.mount('#myApp');
