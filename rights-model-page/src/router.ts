import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Login from "./components/Login.vue";
import Index from "./components/Index.vue";
import User from "./components/User.vue";
import Role from "./components/Role.vue";
import Resource from "./components/Resource.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "login",
      component: Login
    },
    {
      path: "/index",
      name: "index",
      component: Index,
      children:[
        {path:'/index/user',component:User},
        {path:'/index/role',component:Role},
        {path:'/index/resource',component:Resource},
      ]
    },
  ]
});
