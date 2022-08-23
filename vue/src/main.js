// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import '@/assets/css/global.css'


//导入element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI,); //{ locale }  {size:"small"}//所有组件变小

//Element 组件内部默认使用中文，若希望使用其他语言
// import locale from 'element-ui/lib/locale/lang/zh-cn'

/*导入axios请求数据交互*/
import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)

axios.defaults.withCredentials = true

// /* eslint-disable no-new  去除警告 */
Vue.config.productionTip = false
Vue.config.silent = true
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
