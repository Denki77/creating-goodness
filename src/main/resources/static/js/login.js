let now = new Date();

new Vue({
    el: '#app',
    methods: {
        actionLogin() {
            this.isActive = !this.isActive;
            axios.post('/api/v1/auth/login', this.form)
                .then(res => {
                    if (res.data) {
                        localStorage.setItem("token", res.data)
                        this.isAuth = true;
                        window.location.href = '/';
                    }
                })
                .catch(error => {
                    console.log(error);
                    this.errorText = true;
                })
            // .finally(() => (window.location.href = '/'));
        },
    },

    computed: {
        checkAuth() {
            if (localStorage.getItem("token") != null) {
                window.location.href = '/';
            }
        }
    },
    components: {
        'div-footer': divFooter,
    },

    data: {
        nowYear: now.getFullYear(),
        form: {
            email: null,
            password: null
        },
        errorText: false
    }
});