switch (true) {
    case typeof divFooter === 'undefined':
        divFooter = null;
        break;
    case typeof dataProfile === 'undefined':
        dataProfile = null;
        break;
}

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

    computed: {
        token() {
            console.log("check auth");
            if (localStorage.getItem("token") != null) return true
            return this.isAuth;
        }
    },
    components: {
        'div-footer': divFooter,
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