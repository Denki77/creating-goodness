Vue.component('events-data', {
    props: [],

    data() {
        return {
            oneEventTemplate: '<table><tr><td>Событие №{eventId}</td><td>{date}</td></tr><tr><td>Описание</td><td>{description}</td></tr></table>',
            html: '',
        }
    },
    mounted() {
    },
    computed: {
        renderHtml: function () {
            console.log(this.html);
            let eventDiv = document.getElementById('eventsData');
            if (eventDiv !== null) {
                eventDiv.innerHTML = this.html;
            }
            return this.html;
        },
    },

    methods: {
        getOneEventByDate: function (date) {
            axios.get('/api/v1/event/date?day=' + date)
                .then(response => {
                    if (response.data) {
                        for (oneEventKey in response.data) {
                            let newTemplate = this.oneEventTemplate;
                            newTemplate.replace('{eventId}', response.data[oneEventKey].id);
                            newTemplate.replace('{date}', response.data[oneEventKey].startDate);
                            newTemplate.replace('{description}', response.data[oneEventKey].description);
                            this.html += newTemplate;
                        }
                    }
                })
                .catch(error => {
                    console.log(error);
                    this.errorText = true;
                });
        },
    },

    async created() {
        let urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('date')) {
            await this.getOneEventByDate(urlParams.get('date'));
        }
        this.renderHtml();
    },

    template:
        '<div id="eventsData"></div>'
});