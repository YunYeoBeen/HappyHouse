import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store/index.js";
import { BootstrapVueIcons, BootstrapVue, IconsPlugin, JumbotronPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import VueAlertify from "vue-alertify";

Vue.use(JumbotronPlugin);
Vue.use(VueAlertify);
// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.use(IconsPlugin);
// Optionally install the BootstrapVue icon components plugin
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
