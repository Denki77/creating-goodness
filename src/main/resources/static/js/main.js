


Vue.component('modal-registration', {
    props: [],

    data() {
      return {
          body: document.getElementsByTagName('body').item(0),
          isActive: false,
          isLoading: false,
          succedeed: false,

          options: [
              { text: 'Воспитанник детского дома', value: '1' },
              { text: 'Официальный представитель детского дома', value: '2' },
              { text: 'Волонтё', value: '3' },
              { text: 'Представитель бизнеса', value: '4' },
          ],


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

    methods : {
        show() {
            this.isActive = !this.isActive
            // if(this.isActive) $('.form-select').select2();
        },

        reg () {
            let vm = this
            vm.isLoading = true
            axios.post('/api/v1/auth/register', this.form).then(function(response) {


            }).catch(function (error){

            }).finally(function (){
                setTimeout(function (){
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
        '            \t\t<select class="form-select" v-model="form.role">\n' +
        '            \t\t\t<option></option>\n' +
        '            \t\t\t<option v-for="option in options" v-bind:value="option.value">\n' +
        '    \t\t\t\t\t\t{{ option.text }}\n' +
        '  \t\t\t\t\t\t</option>\n' +
        '                    </select>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Представьтесь</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<input type="text" class="form-control mb-2" v-model="form.username" placeholder="Имя пользователя">\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Email</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<input type="text" class="form-control mb-2" v-model="form.email" placeholder="simple@mail.ru">\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Школа</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<input type="text" class="form-control mb-2" v-model="form.shelter" placeholder="18">\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Город</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<input type="text" class="form-control mb-2" v-model="form.city"  placeholder="Москва">\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            <div class="form-group mb-3 row">\n' +
        '\t\t\t\t<label class="form-label col-3 col-form-label">Пароль</label>\n' +
        '\t\t\t\t<div class="col">\n' +
        '\t\t\t\t\t<input type="password" class="form-control mb-2" v-model="form.password" placeholder="Password">\n' +
        '\t\t\t\t</div>\n' +
        '            </div>\n' +
        '            </div>\n' +
        '            \n' +
        '            <div v-if="succedeed">\n' +
        '           \t\t<div class="modal-status bg-success"></div>\n' +
        '           \t\t<h3>Payment succedeed</h3>\n' +
        '      \t\t\t<div class="text-muted">Your payment of $290 has been successfully submitted. Your invoice has been sent to support@tabler.io.</div>\n' +
        '            </div>\n' +
        '            \n' +
        '          </div>\n' +
        '          <div class="modal-footer">\n' +
        '            <button type="button" class="btn btn-primary" v-on:click="reg">\n' +
        '             <div v-if="isLoading" class="spinner-border spinner-border-sm"></div>\n' +
        '             <span v-if="!isLoading">Зарегистрироваться</span>\n' +
        '            </button>\n' +
        '          </div>\n' +
        '        </div>\n' +
        '      </div>\n' +
        '    </div>'
})



var app = new Vue({
    el: '#app',
    methods : {
        showRegistration () {
            this.$refs.reg.show();
        }
    },

    data: {
        message: 'Привет, Vue!'
    }
})