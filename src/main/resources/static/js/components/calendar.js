Vue.component('calendar3', {
    props: [],

    data() {
        return {
            selected: new Date().getMonth(),
            month: [
                {text: 'Январь', value: 0},
                {text: 'Февраль', value: 1},
                {text: 'Март', value: 2},
                {text: 'Апрель', value: 3},
                {text: 'Май', value: 4},
                {text: 'Июнь', value: 5},
                {text: 'Июль', value: 6},
                {text: 'Август', value: 7},
                {text: 'Сентябрь', value: 8},
                {text: 'Октябрь', value: 9},
                {text: 'Ноябрь', value: 10},
                {text: 'Декабрь', value: 11}
            ],
            year: new Date().getFullYear(),
            events: [],
        }
    },
    mounted() {
        this.Calendar3("calendar3", new Date().getFullYear(), new Date().getMonth());
    },
    computed: {},

    methods: {
        getEventsByMonthAndYear: async function (month, year) {
            await axios.get('/api/v1/event/month?month=' + (month + 1) + '&year=' + year)
                .then(response => {
                    if (response.data) {
                        this.events = [];
                        for (let oneEventKey in response.data) {
                            var eventDate = new Date(Date.parse(response.data[oneEventKey].startDate)).getDate();
                            if (typeof this.events[eventDate] === 'undefined') {
                                this.events[eventDate] = [];
                            }
                            this.events[eventDate].push(response.data[oneEventKey]);
                        }
                    }
                })
                .catch(error => {
                    console.log(error);
                    this.errorText = true;
                });
        },
        changecalendar: function (event) {
            this.Calendar3("calendar3", this.year, this.selected);
        },
        async Calendar3(id, year, month) {
            await this.getEventsByMonthAndYear(month, year);
            var Dlast = new Date(year, month + 1, 0).getDate(),
                D = new Date(year, month, Dlast),
                DNlast = D.getDay(),
                DNfirst = new Date(D.getFullYear(), D.getMonth(), 1).getDay(),
                calendar = '<tr>',
                m = document.querySelector('#' + id + ' option[value="' + D.getMonth() + '"]'),
                g = document.querySelector('#' + id + ' input'),
                i = 0;
            if (DNfirst != 0) {
                for (i = 1; i < DNfirst; i++) calendar += '<td>';
            } else {
                for (i = 0; i < 6; i++) calendar += '<td>';
            }
            for (i = 1; i <= Dlast; i++) {
                let getClass = '';
                if (i == new Date().getDate() && D.getFullYear() == new Date().getFullYear() && D.getMonth() == new Date().getMonth()) {
                    getClass = 'today';
                }
                if (i in this.events) {
                    calendar += '<td class="holiday ' + getClass + '"><a href="/events.html?date='
                        + year + '-' + (month + 1) + '-' + i
                        + '">' + i + '</a>';
                } else {
                    calendar += '<td class="' + getClass + '">' + i;
                }
                if (new Date(D.getFullYear(), D.getMonth(), i).getDay() == 0) {
                    calendar += '<tr>';
                }
            }
            for (i = DNlast; i < 7; i++) calendar += '<td>&nbsp;';
            document.querySelector('#' + id + ' tbody').innerHTML = calendar;
            g.value = D.getFullYear();
            m.selected = true;
            if (document.querySelectorAll('#' + id + ' tbody tr').length < 6) {
                document.querySelector('#' + id + ' tbody').innerHTML += '<tr><td>&nbsp;<td>&nbsp;<td>&nbsp;<td>&nbsp;<td>&nbsp;<td>&nbsp;<td>&nbsp;';
            }
            document.querySelector('#' + id + ' option[value="' + new Date().getMonth() + '"]').style.color = 'rgb(220, 0, 0)'; // в выпадающем списке выделен текущий месяц
        }
    },

    template: '<div><h4 class="font-italic">События</h4>' +
        '<table id="calendar3"><tr><td></td></tr>' +
        '<thead>' +
        '<tr><td colspan="4"><select v-model="selected" v-on:change="changecalendar">' +
        '  <option v-for="option in month" v-bind:value="option.value">' +
        '    {{ option.text }}' +
        '  </option>\n' +
        '</select>' +
        '</td><td colspan="3"><input type="number" value="" min="0" max="9999" size="4" v-model="year" v-on:change="changecalendar"></td></tr>' +
        '<tr><td>Пн</td><td>Вт</td><td>Ср</td><td>Чт</td><td>Пт</td><td>Сб</td><td>Вс</td></tr>' +
        '</thead><tbody></tbody>' +
        '</table></div>'
});