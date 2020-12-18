<template>
  <div>
    <div class="nav">
      <el-menu class="left-nav" default-active="2" mode="horizontal">
        <el-menu-item class="ktplogo" index="1">
          <img src="../../assets/image/ppp.png" alt height="30px" width="100px"/>
        </el-menu-item>
        <el-menu-item class="menu-item" index="2" style="margin-left: 20px;">课堂</el-menu-item>
        <el-menu-item class="menu-item" index="3">精品慕课</el-menu-item>
        <el-menu-item class="menu-item" index="4">我的精品</el-menu-item>
      </el-menu>
      <u-nav></u-nav>
    </div>
    <div class="content">
      <router-view/>
    </div>
  </div>
</template>

<script>
import UserNav from "@/views/nav/UserNav";

export default {
  components: {
    'u-nav': UserNav
  },

  name: "Navigation"
}
</script>

<style scoped>
.nav {
  padding: 0 3%;
  height: 72px;
  font-size: 18px;

  background: #FFFFFF;

  width: 100%;
  position: fixed;
  z-index: 2;
  box-sizing: border-box;
  box-shadow: 0 0 10px #ccc;
}

.ktplogo {
  width: 134px;
  height: 32px;
  margin: 32px 0;
}

.left-nav {
  /*margin: 5px 0 0 0;*/
  float: left;
  height: 100%;
}

.menu-item {
  font-size: 18px;
  height: 100%;
}


</style>
