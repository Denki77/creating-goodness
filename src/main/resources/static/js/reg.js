Vue.component('modal-registration', {
    props: [],

    data() {
        return {
            body: document.getElementsByTagName('body').item(0),
            isActive: false,
            isLoading: false,
            succedeed: false,
            classError: 'is-invalid',

            options: null,

            invalid: {
                role: false,
                username: false,
                email: false,
                shelter: false,
                city: false,
                password: false
            },

            form: {
                role: null,
                username: null,
                email: null,
                shelter: null,
                city: null,
                password: null
            }
        }
    },
    created() {

    },


    methods: {
        show() {
            this.isActive = !this.isActive
            if (!this.isActive) return
            let selects2 = this.body.getElementsByClassName("s2")
            for (let i = 0; i < selects2.length; i++) {
                this.initS2(selects2[i])
            }
        },

        initS2(s2) {
            let vm = this
            let placeholder = s2.getAttribute('placeholder')
            let attribute = s2.getAttribute('attribute')
            let map = function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {text: item.name, id: item.id}
                    })
                };
            }

            $(s2).select2({
                placeholder: placeholder,
                theme: "classic",
                ajax: {
                    url: "/api/v1/attribute/" + attribute, dataType: 'json',
                    data: function (params) {
                        return {q: params.term}
                    },
                    processResults: map,
                },
            }).on('select2:select', function (e) {
                vm.form[attribute] = $(e.currentTarget).val();
            });

            let selects2 = this.body.getElementsByClassName("select2-container")
            for (let i = 0; i < selects2.length; i++) {
                selects2[i].style.width = "100%"
            }
        },

        handlerErrors(fields) {
            let vm = this
            Object.entries(vm.invalid).forEach(([key]) => {
                vm.invalid[key] = false
            });
            fields.forEach(function (item) {
                vm.invalid[item] = true
            })
        },


        reg() {
            let vm = this
            vm.isLoading = true
            axios.post('/api/v1/auth/register', this.form).then(function (response) {
                localStorage.token = response.data;
                axios.defaults.headers.common['Authorization'] = localStorage.token;
                vm.succedeed = true
                vm.$emit('callback', true)
            }).catch(function (error) {
                if (error.response) vm.handlerErrors(error.response.data.messages)
            }).finally(function () {
                setTimeout(function () {
                    vm.isLoading = false
                }, 1000);
            })
        }
    },

    template: '<div class="modal modal-blur fade" :class="{show: isActive}">\n' +
        '      <div class="modal-dialog modal-dialog-centered" role="document">\n' +
        '        <div class="modal-content">\n' +
        '          <div class="modal-header">\n' +
        '            <h5 class="modal-title">Регистрация</h5>\n' +
        '            <button type="button" class="btn-close" v-on:click="show"></button>\n' +
        '          </div>\n' +
        '          <div class="modal-body">\n' +
        '           \t\n' +
        '           \t<div v-if="!succedeed">\n' +
        '           \t<div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Ваша роль</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<select class="form-select mb-2 s2" attribute="role" v-bind:class="{ \'is-invalid\' : invalid.role}" v-model="form.role" placeholder=""></select>\n' +
        '                    </select>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Представьтесь</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<input type="text" class="form-control mb-2" v-bind:class="{ \'is-invalid\' : invalid.username}" v-model="form.username" placeholder="Имя пользователя">\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Email</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<input type="text" class="form-control mb-2" v-bind:class="{ \'is-invalid\' : invalid.email}" v-model="form.email" placeholder="simple@mail.ru">\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">№ Детского дома</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<select class="form-select mb-2 s2" attribute="shelter" v-bind:class="{ \'is-invalid\' : invalid.shelter}" v-model="form.shelter" placeholder="18"></select>\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Город</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<select class="form-select mb-2 s2"  attribute="city" v-bind:class="{ \'is-invalid\' : invalid.city}" v-model="form.city" placeholder="Москва"></select>\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Пароль</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<input type="password" class="form-control mb-2" v-bind:class="{ \'is-invalid\' : invalid.password}" v-model="form.password" placeholder="Password">\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            </div>\n' +
        '            \n' +
        '            <div v-if="succedeed">\n' +
        '           \t\t<div class="modal-status bg-success"></div>\n' +
        '           \t\t<h3>Регистрация прошла успешно</h3>\n' +
        '      \t\t\t<div class="text-muted">Вам направлено письмо для подтверждения учетной записи</div>\n' +
        '            </div>\n' +
        '            \n' +
        '          </div>\n' +
        '          <div class="modal-footer.js">\n' +
        '            <button type="button" v-if="!succedeed" class="btn btn-primary" v-on:click="reg">\n' +
        '             <div v-if="isLoading" class="spinner-border spinner-border-sm"></div>\n' +
        '             <span v-if="!isLoading">Зарегистрироваться</span>\n' +
        '            </button>\n' +
        '          </div>\n' +
        '        </div>\n' +
        '      </div>\n' +
        '    </div>'
})