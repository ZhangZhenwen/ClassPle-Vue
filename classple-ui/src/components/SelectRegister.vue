<template>
  <div class="logincon">
    <div class="identity-box">
      <h3>注册账号</h3>
      <p style=" margin-top: 20px; color: #707070;">请选择您在日常教学中的实际身份</p>
      <div class="choose">

        <a href="/register/tch" class="teacher">我是老师/助教</a>
        <a href="/register/stu" class="student">我是学生</a>
      </div>
      <div style="margin-top: 50px">
        <span style="margin-left: 260px;margin-top: 30px">已有账号？</span>
        <a href="/login" ><span class="str2"> 去登录</span></a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SelectRegister"
}
</script>

<style scoped>
.logincon {
  margin-top: 50px;
  width: 480px;
  height: 504px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-65%);
}

.identity-box {
  width: 480px;
  height: 520px;
  padding: 47px 41px 0;
  background:
    #FFF;
  margin-left: auto;
  margin-right: auto;
  box-sizing: border-box;
  border-radius: 4px;
}

.identity-box .choose {
  margin-top: 70px;
  margin-left: -25px;
}

.identity-box .choose a {
  display: block;
  width: 330px;
  font-size: 16px;
  color:
    #fff;
  height: 64px;
  line-height: 64px;
  text-align: center;
  background:
    #32BAF0;
  border-radius: 4px;
  margin-left: 70px;
  position: relative;
}

.student {
  margin-top:20px;
}

a {
  text-decoration: none;
  outline: 0;
  blr: expression(this.onFocus=this.blur());
}


.identity-box .btn-login, .login-box .btn-register {
  float: right;
  margin-top: 24px;
}
</style>
