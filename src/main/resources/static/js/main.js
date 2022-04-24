Vue.component('main-menu', {
    template:
        '<div class="nav-scroller py-1 mb-2">' +
        '<nav class="nav d-flex justify-content-between">' +
        '<a class="p-2 link-secondary" href="dream.html">Мечты</a>' +
        '<a class="p-2 link-secondary" href="events.html">События</a>' +
        '<a class="p-2 link-secondary" href="contacts.html">Контакты</a>' +
        '</nav>' +
        '</div>',
});
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