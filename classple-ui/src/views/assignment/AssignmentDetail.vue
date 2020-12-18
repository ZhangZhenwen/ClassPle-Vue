<template>
  <div>
    <div class="in-header"
         style="box-shadow:0px 1px 3px 0px rgba(0, 0, 0, 0.04);background: none repeat scroll 0% 0% #fff;">
      <div class="nav gWidth" id="third-nav" style="box-shadow: none">
        <router-link v-if="role === 'stu'" :to="{path: '/homework/handup', query: {id: this.id}}" class="active">提交作业</router-link>
        <router-link v-else :to="{path: '/homework/review', query: {id: this.id}}" class="active">批改作业</router-link>
        <router-link :to="{path: '/homework/discuss', query: {id: this.id}}">作业讨论</router-link>
        <u-nav style="right: 0; top: 0; position: absolute"></u-nav>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import UserNav from "@/views/nav/UserNav";
import {getRoleByAsgnId} from "@/api/user";

export default {
  name: "AssignmentDetail",

  components: {
    'u-nav': UserNav
  },

  data() {
    return {
      id: this.$route.query.id,
      role: '',
    }
  },

  created() {
    this.getRole()
  },

  methods: {
    getRole() {
      getRoleByAsgnId(this.id).then(res => {
        this.role = res.msg
      })
    }
  }
}
</script>

<style scoped>
.in-header .nav {
  height: 72px;
  text-align: center;
  width: 930px;
}

.nav {
  font-size: 16px;
  height: 72px;
  /* box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.06); */
}

.gWidth {
  width: 810px;
  margin-left: auto;
  margin-right: auto;
}

.in-header .nav a.active {
  border-bottom: 4px solid #2C58AB;
  border-left: 2px solid transparent;
  border-right: 2px solid transparent;
  /* color: #FFF; */
  font-size: 16px;
  font-weight: 500;
  color: rgba(59, 61, 69, 1);
}

.in-header .nav a {
  display: inline-block;
  min-width: 80px;
  text-align: center;
  font-size: 16px;
  height: 67px;
  line-height: 70px;
  /* color: #DCDCDC; */
  position: relative;
  padding-left: 10px;
  padding-right: 10px;
  margin-left: 20px;
  font-size: 16px;
  font-weight: 400;
  color: rgba(95, 99, 104, 1);
}

a {
  text-decoration: none;
  outline: none;
  blr: expression(this.onFocus=this.blur ());
  cursor: pointer;
}
</style>
