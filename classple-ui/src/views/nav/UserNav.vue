<template>
  <el-menu class="right-nav" mode="horizontal">
    <el-menu-item class="menu-item">
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">工具</template>
      <el-menu-item class="menu-item"><span class="el-icon-message-solid"></span></el-menu-item>
      <el-menu-item class="menu-item"><span class="el-icon-document-copy"></span>论文查重</el-menu-item>
    </el-submenu>
    <el-menu-item>
      <el-badge :value="3" class="item">
        <img src="../../assets/image/notice.png" class="notice-img" width="24" height="24">
      </el-badge>
    </el-menu-item>
    <el-menu-item>
      <el-dropdown>
        <img src="../../assets/image/30.png" width="30" height="30">
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu-item>
  </el-menu>
</template>

<script>
import {getUserInfo} from "@/api/user";
import {logout} from "@/api/login";

export default {
  name: "UserNav",

  data() {
    return {
      user: {
        role: ''
      }
    }
  },
  created() {

  },
  methods: {
    getUserInfo() {
      getUserInfo().then(res => {
      })
    },

    logout() {
      this.$store.dispatch("LogOut").then(() => {
        this.$router.push('/login')
      })
    }
  }
}
</script>

<style scoped>
.item {
  width: 32px;
  height: 32px;
}

.notice-img {
  position: relative;
  transform: translateY(-16px);
}

.right-nav {
  margin: 5px 0 0 0;
  float: right;
  border: 0;
}
</style>
