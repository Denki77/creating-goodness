new Vue({
    el: '#app',
    methods: {
        showRegistration() {
            this.$refs.reg.show();
        },

        actionLogout() {
            localStorage.removeItem("token");
            this.isReg(false);
            // axios.get('/api/v1/auth/logout')
            // .then(response => (this.info = response));
            window.location.reload();
        },

        isReg(bool) {
            this.isAuth = bool
        }
    },
    created() {
        if (this.token !== null) {
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.token;
            this.isReg(true);
        }
    },

    computed: {
        token() {
            return localStorage.getItem("token");
        }
    },
    data: {
        nowYear: now.getFullYear(),
        isAuth: false,
        form: {
            email: null,
            password: null
        }
    }
});