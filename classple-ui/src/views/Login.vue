<template>
  <div class="big">
    <div class="login_warp">
      <div class="login_header items">
        <span @click="cur=0" :class="{active:cur===0}">账号登录</span>
        <span @click="cur=1" :class="{active:cur===1}">手机短信登录</span>
      </div>
      <div class="login_content">
        <div class="Cbody_item">
          <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="right" label-width="auto"
                   show-message>

            <span v-show="cur===0">
            <el-form-item prop="username" style="margin-left: 15px;">
              <el-col :span="22">
                <el-input type="text" v-model="loginForm.username" class="input" placeholder="邮箱/账号/手机号"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item prop="password" style="margin-left: 15px;">
              <el-col :span="22">
                <el-input type="password" v-model="loginForm.password" class="input" placeholder="密码"></el-input>
              </el-col>
            </el-form-item>
            </span>

            <span v-show="cur===1">
            <el-form-item prop="username" style="margin-left: 15px;">
              <el-col :span="22">
                <el-input type="text" v-model="loginForm.username" class="input" placeholder="手机号"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item prop="password" style="margin-left: 15px;">
              <el-col :span="22">
                <el-input type="password" v-model="loginForm.code" class="input" placeholder="验证码"></el-input>
              </el-col>
            </el-form-item>
            </span>

            <el-form-item style="margin-left: 15px;">
              <el-col :span="22">
                <div class="fl">
                  <el-checkbox v-model="loginForm.rememberMe">下次自动登录</el-checkbox>
                </div>
                <div class="fr"><a>忘记密码？</a></div>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width: 420px;margin-left: 15px;" @click="handleLogin">登录</el-button>
            </el-form-item>

            <el-form-item>
              <div class="fr" style="margin-right:25px">还没有账号？
                <router-link to="/register">去注册</router-link>
              </div>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie';

export default {
  name: "Login",

  data() {
    return {
      cur: 0,
      loginForm: {
        username: "13000112233",
        password: "test",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          {required: true, trigger: "blur", message: "用户名不能为空"}
        ],
        password: [
          {required: true, trigger: "blur", message: "密码不能为空"}
        ],
      },
      redirect: undefined,
    };
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, {expires: 30});
            Cookies.set("password", this.loginForm.password, {expires: 30});
            Cookies.set('rememberMe', this.loginForm.rememberMe, {expires: 30});
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store
              .dispatch("Login", this.loginForm).then(() => {
            this.$router.push({path: '/main'});
          }).catch(() => {
            this.$message.error('登录失败')
          });
        }
      });
    }
  }
}
</script>

<style scoped>
.big {
  height: 1000px;
  width: 100%;
  background: linear-gradient(rgba(0, 0, 0, 0.7),
  rgba(0, 0, 0, 0.7)),
  url("../assets/image/bg.jpg") no-repeat fixed;
  background-size: cover;
}

.fl {
  float: left;
  margin-left: 18px;
}

.fr {
  float: right;
}


a {
  text-decoration: none;
  color: #333;
  transition: ease all 0.5s;
}

a {
  color: #1c65ff
}

.login_header {

}

.items {
  font-size: 0;
  padding-top: 47px;
  padding-left: 41px;
  padding-bottom: 58px;
}

.login_header span {
  margin-right: 50px;
  cursor: pointer;
  font-size: 20px;
}

.Cbody_item {
  border: 0 solid #999;
  overflow: hidden;
}

.login_warp {
  width: 440px;
  height: 490px;
  margin: auto;
  border-radius: 10px;
  box-shadow: 0 0 0 #ccc;
  overflow: hidden;
  border: 1px solid #3f7dff;
  background-color: #FFFFFF;
  transition: ease all 0.5s;
  position: relative;
  top: 150px;
  cursor: pointer;
}

.input {
  width: 95%;
  height: 62px;
  float: left;
  font-size: 16px;
  padding-left: 12px;
}

</style>
