let now = new Date();

var app = new Vue({
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

        isReg(bool) {
            this.isAuth = bool
        }
    },

    computed: {
        token() {
            if (localStorage.getItem("token") != null) return true
            return this.isAuth;
        }
    },

    data: {
        nowYear: now.getFullYear(),
        isAuth: false,
        message: 'Привет, Vue!',
        form: {
            email: null,
            password: null
        },
        errorText: false
    }
});