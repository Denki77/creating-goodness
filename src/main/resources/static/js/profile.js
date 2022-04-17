new Vue({
    el: '#app',
    props: [],

    created() {
        console.log("getProfile");
        axios.get('/api/v1/profile', [])
            .then(response => {
                if (response.data) {
                    console.log(response.data);
                    this.profile = response.data;
                }
            })
            .catch(error => {
                console.log(error);
                this.errorText = true;
            })
        // .finally(() => (window.location.href = '/'));
    },

    computed: {
        getProfile() {

        }
    },
    components: {
        'div-footer': divFooter,
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