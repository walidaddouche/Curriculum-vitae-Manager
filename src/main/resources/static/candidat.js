const candidat = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            counter: 1,
            message: "Hello",
            list: [10, 20, 30],
            axios: null,
        }
    },

    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/candidat/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });
    },

    methods: {
        // Place pour les futures méthodes
    }
}

Vue.createApp(candidat).mount('#myApp');