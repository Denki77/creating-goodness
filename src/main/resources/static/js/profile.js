new Vue({
    el: '#app',
    props: [],

    created() {
        if (localStorage.getItem("token") !== null) {
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("token");
        }
        axios.get('/api/v1/profile')
            .then(response => {
                if (response.data) {
                    console.log(response.data);
                    this.profile = response.data;
                }
            })
            .catch(error => {
                if (error.response.status > 400) {
                    localStorage.removeItem("token");
                    window.location.href = '/login.html';
                }
                console.log(error);
                this.errorText = true;
            })
        // .finally(() => (window.location.href = '/'));
    },

    computed: {
        getProfile() {

        }
    },
    data: {
        profile: {
            role: null,
            username: null,
            email: null,
            firstname: null,
            lastname: null,
            annotation: null,
            description: null
        }
    },

});