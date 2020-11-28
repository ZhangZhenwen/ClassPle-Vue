import Vue from 'vue'
import Router from 'vue-router'
import Register from "../view/Register";
import SelectRegister from "../components/SelectRegister";
import RegisterStudent from "../components/RegisterStudent";
import RegisterTeacher from "../components/RegisterTeacher";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/register',
      name: 'Register',
      component: Register,
      children: [
        {
          path: '/register',
          name: 'SelectRegister',
          component: SelectRegister
        },
        {
          path: '/register/stu',
          name: 'RegisterStudent',
          component: RegisterStudent
        },
        {
          path: '/register/tch',
          name: 'RegisterTeacher',
          component: RegisterTeacher
        }]
    }
  ]
})
