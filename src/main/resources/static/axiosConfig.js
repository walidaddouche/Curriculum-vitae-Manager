const axiosInstance = axios.create({
    baseURL: 'http://localhost:8081/api/',
    timeout: 1000,
    headers: { 'Content-Type': 'application/json' },
});

const AxiosPlugin = {
    install(Vue) {
        Vue.prototype.$axios = axiosInstance;
    },
};

export default AxiosPlugin;
