<template>
  <div>
    <div class="in-header"
         style="box-shadow:0 1px 3px 0 rgba(0, 0, 0, 0.04);background: none repeat scroll 0 0 #fff;">
      <div class="nav gWidth" id="third-nav" style="box-shadow: none">
        <router-link :to="{path: '/member/index', query: {id: this.id}}" class="active">成员</router-link>
        <router-link :to="{path: '/homework/discuss', query: {id: this.id}}">学生分组</router-link>
        <u-nav style="right: 0; top: 0; position: absolute"></u-nav>
      </div>
    </div>
    <div class="member-page cWidth">
      <div class="member-title member-titlenew clearfix" v-if="teacher">
        <a class="drmember-btn fl">导入成员</a>
        <a class="fl down-btn">
          <i class="iconfont iconxiazaibaobiao">
            <img src="../../assets/image/member_download.png" width="18" height="17">
          </i>
          下载成员信息
        </a>
        <a class="stu-whitelist fl hide2">学生白名单</a>
        <div class="fr input-box">
          <input type="text" placeholder="姓名、学号">
          <i></i>
        </div>
        <div class="member-change fl">
        <span class="tit">
          <i class="iconfont iconchengyuantuikexinxi1">
            <img src="../../assets/image/member_drop.png" width="16" height="16">
          </i>
          成员退课记录
        </span>
        </div>
      </div>
      <div class="member-cont clearfix">
        <div class="m-l-box fl">
          <el-menu
              @select="handleSelect"
              default-active="1">
            <el-menu-item index="1">
              教学团队（{{ tchCount }}）
            </el-menu-item>
            <el-menu-item index="2">
              全部学生（学生{{ stuCount }}）
            </el-menu-item>
          </el-menu>
        </div>
        <div class="m-r-box fl">
          <div class="all-list">
            <template v-if="showTeacher === true">
              <div class="title">
                <div class="all-title clearfix">
                  <h3 class="fl" style="width: auto;">教学团队（老师<span>{{ tchCount }}</span>）</h3>
                  <a class="fr invite-tea" style="display: block;" v-if="teacher" @click="inviteFormVisible = true">
                    <i class="iconfont icontianjiachengyuan1"></i>
                    添加助教/老师
                  </a>
                </div>
              </div>
              <ul class="batchstart">
                <li class="teacher" v-for="item in teacherList">
                  <img src="../../assets/image/30.png" alt="">
                  <p class="teaName"><span :title="item.name">{{ item.name }}</span></p>
                  <p class="mail" :title="item.params.account">{{ item.params.account }}</p>
                  <p class="assistant_sign" title="（管理员）">（管理员）</p>
                </li>
              </ul>
            </template>
            <template v-if="showStudent === true">
              <div class="title">
                <div class="all-title clearfix">
                  <h3 class="fl" style="width: auto;">全部学生（学生<span>{{ stuCount }}</span>）
                    <div class="allowquit fr">
                      <div class="allowquittip">不允许退课</div>
                    </div>
                  </h3>
                </div>
              </div>
              <ul class="batchstart">
                <li v-for="item in studentList">
                  <p class="studentavatar">
                    <img class="avatar" src="../../assets/image/30.png" alt="">
                  </p>
                  <p class="name"><span :title="item.name">{{ item.name }}</span></p>
                  <p class="stuno"><span :title="item.schoolNo">{{ item.schoolNo }}</span></p>
                  <p class="mail" :title="item.params.account">{{ item.params.account }}</p>
                  <p class="natureclass" title="">　</p>
                  <p class="createtime" :title="item.joinDate">{{ formatTime(item.joinDate) }}</p>
                </li>
              </ul>
            </template>
          </div>
        </div>
      </div>
    </div>
    <div>
      <el-dialog
          :visible.sync="inviteFormVisible"
          title="添加老师/助教">
        <div>
          <span>老师账号列表：账号列表复制到这里（账号之间要 换行 进行分开）</span>
          <el-input
              v-model="inviteForm.code"
              type="textarea"
              :rows="5">
          </el-input>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="inviteFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitInvite">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import UserNav from "@/views/nav/UserNav";
import {getCourseDetail, getStudentCount, getTeacherCount, getUserList} from "@/api/course";
import moment from "moment";

export default {
  name: "MemberIndex",

  components: {
    'u-nav': UserNav
  },

  data() {
    return {
      id: this.$route.query.id,
      course: {},
      tchCount: {},
      stuCount: {},
      studentList: [],
      teacherList: [],
      userList: {},
      showTeacher: true,
      showStudent: false,
      teacher: false,
      inviteFormVisible: false,
      inviteForm: {
        id: '',
        code: '',
      },
    }
  },

  created() {
    this.getCourse();
    this.getUserList();
  },

  methods: {
    getCourse() {
      getCourseDetail(this.id).then(res => {
        this.course = res.data
        this.teacher = this.course.params.role === 'tch' || this.course.params.role === 'admin'

        getStudentCount(this.id).then(res => {
          this.stuCount = res.data
        });

        getTeacherCount(this.id).then(res => {
          this.tchCount = res.data
        })
      })
    },

    getUserList() {
      getUserList(this.id).then(res => {
        this.userList = res.data

        for (let value of this.userList) {
          if (value.userRole === 'stu') {
            this.studentList.push(value);
          } else {
            this.teacherList.push(value);
          }
        }
      })
    },

    submitInvite() {
      this.inviteForm.id = this.id;
      inviteCourse(this.inviteForm).then(() => {
        this.$message.success("邀请成功！")
      }).catch(err => {
        this.$message.success("邀请失败！" + err.message)
      })
    },

    formatTime(date) {
      return moment(date).format("YY/MM/DD HH:mm")
    },

    handleSelect(key, keyPath) {
      if (key === '1') {
        this.showTeacher = true;
        this.showStudent = false;
      } else if (key === '2') {
        this.showTeacher = false;
        this.showStudent = true;
      }
    },
  }

}
</script>

<style scoped>
.member-page.cWidth {
  width: 1224px;
}

.member-page {
  margin-top: 40px;
  font-size: 14px;
}

.m-r-box ul, .member-page, .member-search ul {
  padding-bottom: 60px;
}

.cWidth {
  /* width: 980px; */
  margin-left: auto;
  margin-right: auto;
}

.member-titlenew {
  box-shadow: 0 1px 0 0 rgba(226, 230, 237, 1);
  padding-bottom: 20px;
  height: 33px;
}

.member-title {
  margin-bottom: 27px;
}

.m-r-box ul li p.studentavatar {
  height: 60px;
  min-width: 50px;
}

.clearfix {
  zoom: 1;
}

.drmember-btn {
  vertical-align: middle;
  min-width: 104px;
  height: 32px;
  border-radius: 4px;
  color: #FFF;
  font-size: 14px;
}

.drmember-btn {
  background: #32BAF0;
  text-align: center;
}

.drmember-btn, .member-title .down-btn {
  line-height: 32px;
  display: inline-block;
  margin-right: 20px;
}

.m-r-box ul li p.groupName, .m-r-box ul li p.mail, .m-r-box ul li p.name, .m-r-box ul li p.stuno, .member-search ul li p.mail, .member-search ul li p.name, .member-search ul li p.stuno {
  min-width: 100px;
  max-width: 100px;
}

.fl {
  float: left;
}

.member-title .down-btn {
  color: #5F6368;
}

.iconfont.iconchengyuantuikexinxi1, .iconfont.iconxiazaibaobiao {
  margin-right: 7px;
}

.iconfont {
  font-family: iconfont !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.member-title .stu-whitelist {
  display: inline-block;
  color: #666;
  line-height: 38px;
  /*background: url(../img/stuwhitelist.png) left no-repeat;*/
  padding-left: 20px;
  margin-left: 30px;
}

.m-r-box ul li p.natureclass {
  width: 90px;
  height: 60px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.m-r-box ul li p.natureclass, .member-search ul li p.natureclass {
  min-width: 90px;
  max-width: 90px;
}

.m-r-box ul li p .avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-top: 10px;
  background-repeat: no-repeat;
  background-position: center;
}
img {
  border: none;
}

.hide2 {
  display: none !important;
}


.allowquit.fr {
  margin-left: 30px;
}

.allowquittip {
  font-size: 14px;
  margin-right: 10px;
  float: left;
}

.m-r-box ul {
  padding-bottom: 60px;
}

.m-r-box ul li {
  height: 60px;
  line-height: 60px;
  padding: 0 30px;
}

li {
  list-style: none;
}

li.teacher > img {
  height: 40px;
  margin: 10px 20px;
  float: left;
  border-radius: 100%;
}

img {
  border: none;
}

.m-r-box ul li p.teaName, .member-search ul li p.teaName, li.teacher > p.mail {
  width: 300px;
}

.m-r-box ul li p.teaName span, .member-search ul li p.teaName span {
  display: inline-block;
  max-width: 200px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  vertical-align: top;
}

.m-r-box ul li p, .member-search ul li p {
  float: left;
  margin-right: 20px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.m-r-box ul li p.mail, .m-r-box ul li p.name, .m-r-box ul li p.stuno, .member-search ul li p.mail, .member-search ul li p.name, .member-search ul li p.stuno {
  min-width: 100px;
  max-width: 100px;
}

.m-r-box ul li p.teaName, .member-search ul li p.teaName, li.teacher > p.mail {
  width: 300px;
}

.m-r-box ul li p, .member-search ul li p {
  float: left;
  margin-right: 20px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.m-r-box ul li p, .member-search ul li p {
  float: left;
  margin-right: 20px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.member-titlenew .input-box {
  border: none;
  background: rgba(241, 243, 244, 1);
  border-radius: 4px;
}

.member-title .input-box {
  width: 176px;
  height: 30px;
  border: 1px solid #c8c8c8;
  font-size: 0;
}

.member-title .input-box input {
  display: inline-block;
  vertical-align: middle;
  height: 30px;
  line-height: 30px \9;
  width: 136px;
  padding-left: 10px;
  font-size: 13px;
}

input, select, textarea {
  outline: none;
  border: none;
  background: none;
  cursor: text;
}

.member-title .input-box i {
  display: inline-block;
  width: 30px;
  height: 14px;
  cursor: pointer;
}

.member-title .input-box i, .stutent-search i {
  background: url(../../assets/image/se_s.png) center center no-repeat;
  vertical-align: middle;
}

.member-title .member-change {
  min-width: 100px;
  height: 32px;
  line-height: 32px;
  position: relative;
}

.member-title .member-change .tit {
  height: 32px;
  line-height: 32px;
  color: #5F6368;
  cursor: pointer;
}

.member-cont {
  background: #FFF;
  border: 1px solid rgba(226, 230, 237, 1);
  min-height: 600px;
  border-radius: 8px;
  position: relative;
}

.member-cont .m-l-box {
  width: 286px;
  min-height: 300px;
}

.member-cont .m-r-box {
  width: 936px;
  border-left: 1px solid rgba(226, 230, 237, 1);
  min-height: 600px;
  margin-left: -1px;
}

.all-list {
  padding: 0 !important;
}

.m-r-box .title {
  padding: 0 12px 0 25px;
  height: 55px;
  background: rgba(241, 243, 244, 1);
  border-radius: 0 8px 0 0;
}

.m-r-box .title h3 {
  display: inline-block;
  height: 55px;
  line-height: 55px;
  font-size: 16px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(59, 61, 69, 1);
}

.member-titlenew + .member-cont .invite-tea {
  width: 172px;
  height: 32px;
  background: rgba(50, 186, 240, 1);
  border-radius: 4px;
  color: #fff;
  text-align: center;
  line-height: 32px;
  margin-top: 12px;
}

.m-r-box .title a {
  color: #357ae8;
  font-size: 16px;
}

.member-titlenew + .member-cont .invite-tea i {
  margin-right: 8px;
}

.m-r-box .title .invite-tea i {
  font-style: normal;
  display: inline-block;
}

.clearfix {
  zoom: 1;
}

.fr {
  float: right;
}

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
