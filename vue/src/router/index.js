import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login";
import Register from "../views/Register";

import Layout from "../layout/Layout";
import User from "../views/User";
import Book from "../views/Book";
import Oss from "../test/Oss";
Vue.use(Router)
export default new Router({
  mode: "history",//设置了这个在地址中就没有#号了
  routes: [
    {
      path: '/Layout',
      name: 'Layout',
      component: Layout,
      redirect:'/Oss',
      children:[
        {
          path: '/Oss',
          name: 'Oss',
          component: Oss,
        },
        {
          path: '/User',
          name: 'User',
          component: User,
        },
        {
          path: '/Book',
          name: 'Book',
          component: Book,
        },

      ]
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },


  ]
})
