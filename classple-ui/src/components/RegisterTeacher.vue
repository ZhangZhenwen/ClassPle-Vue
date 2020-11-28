<template>
  <div class="logincon">

    <div class="header">
      <h1 style="margin-right:180px; ">教师/助教注册</h1>
    </div>

    <div>
      <el-form ref="teacherForm" :model="teacherForm" label-width="80px" style="width:300px;margin-left:5%">
        <el-form-item label="账号">
          <el-input v-model="teacherForm.account" style="width:240px;"></el-input>
        </el-form-item>

        <el-form-item label="密码">
          <el-input v-model="teacherForm.password" type="password" style="width:240px;"></el-input>
        </el-form-item>

        <el-form-item label="用户名">
          <el-input v-model="teacherForm.name" style="width:240px;"></el-input>
        </el-form-item>

        <el-form-item label="手机">
          <el-input v-model="teacherForm.tel" style="width:240px;"></el-input>
        </el-form-item>

        <el-form-item label="学校">
          <el-input v-model="teacherForm.university" style="width:240px;"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" style="width:240px" @click="submit">注册</el-button>

        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script>
export default {
  name: "RegisterTeacher"
}
</script>

<style scoped>

.header {
  background-color: #FFFFFF;
  height: 60px;
}

.logincon {
  width: 440px;
  height: 450px;
  margin: auto;
  border-radius: 10px;
  box-shadow: 0 0 0 #ccc;
  overflow: hidden;
  border: 1px solid #3f7dff;
  background-color: #FFFFFF;
  transition: ease all 0.5s;
  position: relative;
  top: 120px;
  cursor: pointer;

}
</style>
