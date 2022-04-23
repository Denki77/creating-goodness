Vue.component('dream-list', {
    props: [],

    data() {
        return {
            dreams: [],
        }
    },
    mounted() {
    },
    computed: {},

    methods: {
        getDreams: function (page, count) {
            axios.get('/api/v1/dream?page=' + page + '&count=' + count)
                .then(response => {
                    if (response.data) {
                        console.log(response.data);
                        this.dreams = response.data.content;
                    }
                })
                .catch(error => {
                    console.log(error);
                    this.errorText = true;
                });
        },
    },

    created() {
        this.getDreams(1, 10);
    },

    template:
        '<div>' +
        '<div v-if="dreams.length > 0">' +
        '<table class="table">' +
        '<tr v-for="dream in dreams">' +
        '<td>{{dream.profile.firstname}}<br>' +
        '<small>{{dream.profile.shelter.name}}</small></td>' +
        '<td>{{dream.annotation}}</td>' +
        '</tr>' +
        '</table>' +
        // '<paginator v-bind:pages="pages" gap="5" v-bind:currentPage="currentPage" v-on:setPage="setPage($event)" v-on:setPrevPage="setPrevPage()" v-on:setNextPage="setNextPage()" class="text-center"></paginator>' +
        '</div>' +
        '<div v-else>' +
        ' No records' +
        '</div>' +
        '</div>'
});