Vue.component('events-data', {
    props: [],

    data() {
        return {
            oneEventTemplate: '<table><tr><td>Событие №{eventId}</td><td>{date} в {time}</td></tr><tr><td colspan="2">{name}</td></tr></table><hr>',
            html: '',
        }
    },
    mounted() {
    },
    computed: {
        renderHtml: function () {
            let eventDiv = document.getElementById('eventsData');
            if (eventDiv !== null) {
                eventDiv.innerHTML = this.html;
            }
            return this.html;
        },
    },

    methods: {
        getAllEventsByDate: function (date) {
            axios.get('/api/v1/event/date?day=' + date)
                .then(response => {
                    if (response.data) {
                        for (oneEventKey in response.data) {
                            let newTemplate = this.oneEventTemplate;
                            let date = new Date(response.data[oneEventKey].startDate);
                            newTemplate = newTemplate.replace('{eventId}', response.data[oneEventKey].id);
                            newTemplate = newTemplate.replace('{date}', date.getDate() + '.' + (date.getMonth() + 1) + '.' + date.getFullYear());
                            newTemplate = newTemplate.replace('{time}', date.getHours() + ':' + date.getMinutes());
                            newTemplate = newTemplate.replace('{name}', '<a href="?id=' + response.data[oneEventKey].id + '">' + response.data[oneEventKey].name + '</a>');
                            this.html += newTemplate;
                        }
                    }
                    console.log(this.renderHtml);
                })
                .catch(error => {
                    console.log(error);
                    this.errorText = true;
                });
        },
        getOneEvent: function (id) {
            axios.get('/api/v1/event/' + id)
                .then(response => {
                    if (response.data) {
                        let newTemplate = this.oneEventTemplate;
                        let date = new Date(response.data.startDate),
                            description = ''
                                + '<b>' + response.data.name + '</b>'
                                + '<br>'
                                + response.data.description
                                + '<br>'
                                + 'Статус: ' + response.data.status
                                + '. Дней проведения: ' + response.data.countDays
                                + '<br>'
                                + 'Контактное лицо: ' + response.data.user.firstname + ' ' + response.data.user.lastname;
                        newTemplate = newTemplate.replace('{eventId}', response.data.id);
                        newTemplate = newTemplate.replace('{date}', date.getDate() + '.' + (date.getMonth() + 1) + '.' + date.getFullYear());
                        newTemplate = newTemplate.replace('{time}', date.getHours() + ':' + date.getMinutes());
                        newTemplate = newTemplate.replace('{name}', description);
                        this.html += newTemplate;
                    }
                    console.log(this.renderHtml);
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
            await this.getAllEventsByDate(urlParams.get('date'));
        } else if (urlParams.has('id')) {
            await this.getOneEvent(urlParams.get('id'));
        } else {
            // await this.getOneEvent(urlParams.get('id'));
        }
    },

    template:
        '<div id="eventsData"></div>'
});