<template>
  <div class="big">
    <div class="logincon" v-if="model === 0">
      <div class="identity-box">
        <h3>注册账号</h3>
        <p style=" margin-top: 20px; color: #707070;">请选择您在日常教学中的实际身份</p>
        <div class="choose">
          <img class="register-img" src="../assets/image/teacher.png">
          <a @click="toTeacher" class="teacher">我是老师/助教</a>
          <img class="register-img" style="margin-top: 70px" src="../assets/image/student.png">
          <a @click="toStudent" class="student">我是学生</a>
        </div>
        <div style="padding: 15px 0; font-size: 14px">
          <span style="margin-left: 260px;margin-top: 30px">已有账号？</span>
          <router-link to="/login">去登录</router-link>
        </div>
      </div>
    </div>

    <div class="logincon" v-if="model !== 0">

      <div class="header">
        <a class="returnImg" @click="goBack"><img src="../assets/image/returnReg.png"></a>
        <h3 v-if="model === 1">教师/助教注册</h3>
        <h3 v-if="model === 2">学生注册</h3>
      </div>

      <div class="content">
        <el-form ref="userForm" :model="userForm" :rules="userRules" label-width="40px" style="padding: 40px 0 0 0">

          <el-form-item prop="tel">
            <el-col :span="11">
              <el-input v-model="userForm.tel" class="input-box" placeholder="邮箱/手机号"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item prop="password">
            <el-col :span="11">
              <el-input v-model="userForm.password" type="password" class="input-box" autocomplete="off" placeholder="密码"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item prop="checkPassword">
            <el-col :span="11">
              <el-input v-model="userForm.checkPassword" type="password" class="input-box" autocomplete="off" placeholder="再次输入密码"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item prop="userName">
            <el-col :span="11">
              <el-input v-model="userForm.userName" class="input-box" placeholder="姓名"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item prop="school">
            <el-col :span="11">
              <el-input v-model="userForm.school" class="input-box" placeholder="学校"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item v-if="model === 2" prop="schoolNo">
            <el-col :span="11">
              <el-input v-model="userForm.schoolNo" class="input-box" placeholder="学号"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item>
            <el-button
                type="primary"
                style=" width:400px"
                @click="handleSubmit">
              <span v-if="model === 1">注册为老师/助教</span>
              <span v-if="model === 2">注册为学生</span>
            </el-button>
          </el-form-item>

          <el-form-item>
            <div class="fr" style="margin-right:25px">已有账号？
              <router-link to="/login">去登录</router-link>
            </div>
          </el-form-item>
        </el-form>

      </div>
    </div>


  </div>
</template>

<script>
import {registerStudent, registerTeacher} from "@/api/user";

export default {
  name: "Register",

  data() {
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('再次输入密码不能为空'))
      } else if (value !== this.userForm.password) {
        callback(new Error('两次输入的密码不一致'));
      } else {
        callback();
      }
    };

    return {
      model: 0,

      userForm: {
        userName: '',
        tel: '',
        password: '',
        school: '',
        schoolNo: '',
        checkPassword: '',
      },

      userRules: {
        tel: [
          { require: true, trigger: "blur", message: '邮箱/手机不能为空' }
        ],
        password: [
          { require: true, trigger: "blur", message: '密码不能为空' }
        ],
        checkPassword: [
          { validator: validatePassword, trigger: "blur" }
        ],
        school: [
          { require: true, trigger: "blur", message: '学校不能为空' }
        ],
        schoolNo: [
          { require: true, trigger: "blur", message: '学号不能为空' }
        ],
      }
    }
  },

  methods: {
    toTeacher() {
      this.model = 1;
    },

    toStudent() {
      this.model = 2;
    },

    goBack() {
      this.model = 0;
    },

    handleSubmit() {
      this.$refs.userForm.validate(valid => {
        if (valid) {
          console.log(this.model)

          if (this.model === 1) {
            registerTeacher(this.userForm).then(() => {
              this.$message.success("注册成功！");
              this.$router.push({path: '/login'});
            }).catch(() => {
              this.$message.error("注册失败！")
            })
          } else if (this.model === 2) {
            registerStudent(this.userForm).then(() => {
              this.$message.success("注册成功！")
              this.$router.push({path: '/login'});
            }).catch(() => {
              this.$message.error("注册失败！")
            })
          }
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
  margin-top: -60px;
  background: url("../assets/image/reg-bg-01.jpg") no-repeat fixed;
  background-size: cover;
}

.logincon {
  margin-top: 50px;
  width: 480px;
  height: 504px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -65%);
}

.content {
  background: #FFF;
  width: 480px;
}

.identity-box {
  width: 480px;
  height: 504px;
  padding: 47px 41px 0;
  background: #FFF;
  margin-left: auto;
  margin-right: auto;
  box-sizing: border-box;
  border-radius: 4px;
}

.returnImg {
  margin: 0 22px 0 0;

  width: 40px;
  height: 40px;
}

.identity-box .choose {
  margin-top: 70px;
}

.identity-box .choose a {
  display: block;
  width: 330px;
  font-size: 16px;
  color: #fff;
  height: 64px;
  line-height: 64px;
  text-align: center;
  background: #32BAF0;
  border-radius: 4px;
  margin-left: 70px;
  position: relative;
}

.identity-box h3 {
  margin: 0;
  font-size: 32px;
  font-weight: 400;
  color: #3B3D45;
}

.student {
  margin-top: 70px;
}

.register-img {
  float: left
}

.identity-box .btn-login, .login-box .btn-register {
  float: right;
  margin-top: 24px;
}

.header {
  background-color: #FFFFFF;
  padding: 30px 0 0 30px;
}

.input-box {
  width: 400px;
  height: 1px;
}

.header h3 {
  font-size: 32px;
  display: inline;
}

.fr {
  float: right;
}
</style>
