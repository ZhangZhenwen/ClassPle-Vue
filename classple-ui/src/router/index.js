import Vue from 'vue'
import Router from 'vue-router'
import Register from "../views/Register";
import Login from "../views/Login";
import CourseIndex from "@/views/course/CourseIndex";
import Index from "@/views/Index";
import CourseDetail from "@/views/course/CourseDetail";
import Navigation from "@/views/nav/Navigation";
import Homework from "@/views/course/Homework";
import Interact from "@/views/course/Interact";
import TopicDiscuss from "@/views/course/TopicDiscuss";
import Courseware from "@/views/course/Courseware";
import HomeworkHandup from "@/views/assignment/HomeworkHandup";
import HomeworkDiscuss from "@/views/assignment/HomeworkDiscuss";
import AssignmentDetail from "@/views/assignment/AssignmentDetail";
import HomeworkReview from "@/views/assignment/HomeworkReview";
import MemberIndex from "@/views/memeber/MemberIndex";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/register',
      name: 'Register',
      component: Register,
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/main',
      name: 'Nav',
      component:Navigation,
      children: [
        {
          path: '/main',
          name: 'Course',
          component: CourseIndex
        }]
    },
    {
      path: '/main/detail',
      name: '课程详情',
      component: CourseDetail,
      children: [
        {
          path: '/course/homework/index',
          name: 'Homework',
          component: Homework
        },
        {
          path: '/interact/index',
          name: 'Interact',
          component: Interact
        },
        {
          path: '/TopicDiscuss/index',
          name: 'TopicDiscuss',
          component: TopicDiscuss
        },
        {
          path: '/Courseware/index',
          name: 'Courseware',
          component: Courseware
        }]
    },
    {
      path: '/homework',
      name: 'AssignmentDetail',
      component: AssignmentDetail,
      children: [
        {
          path: '/homework/handup',
          name: 'homeworkHandup',
          component: HomeworkHandup
        },
        {
          path: '/homework/discuss',
          name: 'homeworkDiscuss',
          component: HomeworkDiscuss
        },
        {
          path: '/homework/review',
          name: 'homeworkReview',
          component: HomeworkReview
        }]
    },
    {
      path: '/member/index',
      name: 'MemberIndex',
      component: MemberIndex
    }
  ]
})
