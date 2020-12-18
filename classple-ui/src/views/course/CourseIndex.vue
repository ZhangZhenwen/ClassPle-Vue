<template>
  <div class="ktp-class">
    <div class="ktcon1">
      <div class="left-item">全部课程</div>
      <div class="right-item" v-if="userRole === 'stu'">
        <el-button style=" height: 26px" type="primary" icon="el-icon-plus" @click="joinCourseForm = true">
          加入课程
        </el-button>
      </div>
      <div class="right-item" v-if="userRole === 'tch'">
        <el-dropdown trigger="click">
          <el-button style=" height: 26px" type="primary" icon="el-icon-plus">
            创建/加入课程
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="createCourseFormVisible = true">创建课程</el-dropdown-item>
            <el-dropdown-item @click.native="joinCourseForm = true">加入课程</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div class="right-item" @click="showArchive">归档管理</div>
      <div class="right-item">课程排序</div>
    </div>
    <!--添加课程------------------------>
    <div>
      <el-dialog
          :visible.sync="joinCourseForm"
          title="加入课程"
          width="300px">
        <el-form v-model="form" style="text-align: left" ref="dataForm">
          <el-form-item prop="id">
            <el-input v-model="form.crseCode" autocomplete="off" placeholder="请输入课程加课码"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="joinCourseForm = false">取 消</el-button>
          <el-button type="primary" @click="onSubmit">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div>
      <el-dialog
          :visible="createCourseFormVisible"
          title="创建课程"
          class=""
          width="660px">
        <el-form ref="createForm" v-model="createCourseForm" label-position="right">
          <el-form-item label="课程名称：" style="margin-top: 30px">
            <el-input style="width: 80%" v-model="createCourseForm.crseName" placeholder="请输入课程名称"></el-input>
          </el-form-item>
          <el-form-item label="班级名称：" style="margin-top: 30px">
            <el-input style="width: 80%" v-model="createCourseForm.className" placeholder="请输入班级名称（选填）"></el-input>
          </el-form-item>
          <el-form-item label="选择学期：" style="margin-top: 30px">
            <el-select v-model="yearValue">
              <el-option
                  v-for="item in yearOptions"
                  :key="item.value"
                  :label="item.value"
                  :value="item.value">
              </el-option>
            </el-select>
            <el-select v-model="semesterValue">
              <el-option
                  v-for="item in semesterOptions"
                  :key="item.value"
                  :label="item.value"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-checkbox v-model="classCheck">自然班级</el-checkbox>
            <el-checkbox v-model="gradeCheck">年级</el-checkbox>
            <el-checkbox v-model="yearCheck">入学年月</el-checkbox>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="createCourseFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="createCourse">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div>
      <el-dialog
          :visible.sync="archiveVisible"
          class=""
          width="810px"
          center
      >
        <div slot="title" class="guidangpaixutop cl">
          <ul>
            <li class="ktli1"><a>课程排序</a></li>
            <li class="ktli2 on"><a> 归档管理</a></li>
          </ul>
        </div>
        <div class="guidangpaixucon gd2 clearfix" style="height: 442px;">
          <div class="bb-div" style="top: 0;">
            <dl class="gpdl1 dv1" v-for="item in fileCourses" :title="item.crseName">
              <dt style="background:url(//assets.ketangpai.com/theme/min_ar/01.jpg) no-repeat;background-color: #1066af;">
                <strong><a class="text-overflow" href="/Interact/index/courseid/MDAwMDAwMDAwMLScy5SIqbts.html"
                           :title="item.crseName">{{ item.crseName }}</a></strong>
                <p>角色：{{ item.params.role }} &nbsp;&nbsp;&nbsp;&nbsp; 老师：{{ item.params.admin }}</p>
                <a class="kdmorex">
                  <el-dropdown trigger="click">
                    <a class="drop-button"></a>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item class="kdli1 gdhf"><a @click="unFileCourse(item.crseId)">恢复</a>
                      </el-dropdown-item>
                      <el-dropdown-item class="kdli2 gdsc"><a>退课</a></el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </a>
              </dt>
            </dl>
          </div>
        </div>
      </el-dialog>
    </div>

    <div>
      <el-dialog
        :visible.sync="archiveTchConfirmVisible"
        title="要归档此课程么？"
        width="500px">
        <span>
          <p>您可以在“课堂”-“归档管理”中查看此课程</p>
          <p>【归档全部】，学生的课程也会一起被归档</p>
          <p>【归档自己】，学生的课程不会被归档</p>
        </span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="archiveTchConfirmVisible = false">取 消</el-button>
          <el-button @click="fileCourseAll">归档全部</el-button>
          <el-button type="primary" @click="fileCourseOne">归档自己</el-button>
        </span>
      </el-dialog>
    </div>

    <div>
      <el-dialog
          :visible.sync="archiveStuConfirmVisible"
          title="要归档此课程么？"
          width="500px">
        <span>
          <p>您可以在“课堂”-“归档管理”中查看此课程</p>
        </span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="archiveStuConfirmVisible = false">取 消</el-button>
          <el-button type="primary" @click="fileCourseOne">归档自己</el-button>
        </span>
      </el-dialog>
    </div>

    <div class="ktcon2 cl  hide" id="viewer-container-lists">
      <dl class="ktdl1 ktpx1" data-color="#318eeb" v-for="(item,index) in allCourse">
        <dt style="background:url(//assets.ketangpai.com/theme/student/min/22.png)" class="bgclass1">
          <strong style="text-align: left">
            <a class="jumptoclass" :title="item.crseName"
               @click="goToCourse(item.crseId)">
              {{ item.crseName }}
            </a>
            <span :title="item.className">{{ item.className }}</span>
          </strong>

          <div class="invitecode">
            <div class="qrcode">
              <i class="iconfont iconketangma1"></i>
            </div>
            <div class="down-menu" data-role="0">加课码：
              <span class="code">
                {{ item.crseCode }}
              </span>
            </div>
          </div>

          <span class="time">{{ item.crseSemester }}</span>

          <div class="bigDiv learncl" v-if="item.params.role === 'stu'">
            <div class="squr"></div>
            <div class="ju">学</div>
          </div>
          <div class="bigDiv teacl" v-else>
            <div class="squr"></div>
            <div class="ju">教</div>
          </div>
          <div v-if="item.params.role === 'stu'" class="zhidings zhidings-two" @click="fileStuCourse(item.crseId)">归档</div>
          <div v-else class="zhidings zhidings-two" @click="fileTchCourse(item.crseId)">归档</div>
          <a class="kdmore">
            <span @click="exitCourse(item.crseId)">退课</span>
            <i></i>
          </a>

        </dt>
        <dd>
          <ul style="text-align: left">
            <li class="off"><span>课程介绍</span></li>
            <li><span title="介绍"><a href="">{{ item.introduction }}</a></span></li>
          </ul>
          <div class="ddfoot clearfix">

            <div class="user-avatar-area">
              <img src="../../assets/image/30.png">
              <div class="teachername">{{ item.params.admin }}</div>
            </div>
          </div>
        </dd>
      </dl>

      <dl class="ktdl1" v-if="userRole === 'stu'">
        <dt class="joinClass"></dt>
        <dd>
          <i class="iconfont"></i>
          <p class="el-icon-circle-plus-outline" @click="joinCourseForm = true">加入课程</p>
        </dd>
      </dl>
      <dl style="width:272px"></dl>
      <dl style="width:272px"></dl>
      <dl style="width:272px"></dl>
      <dl style="width:272px"></dl>
      <dl style="width:272px"></dl>
    </div>

  </div>


</template>

<script>


import {addCourse, dropoutCourse, getCourse, joinCourse} from "@/api/course";
import {addArchive, addArchiveAll, cancelArchive, cancelArchiveAll, getArchive} from "@/api/archive";
import {getUserInfo} from "@/api/user";
import {logout} from "@/api/login";

export default {
  data() {
    return {
      joinCourseForm: false,
      form: {},
      studentName: '',
      studentId: '',
      createCourseFormVisible: false,

      createCourseForm: {},
      courseNo: '',
      id: this.$route.query.id,
      type: '',
      archiveVisible: false,
      archiveTchConfirmVisible: false,
      archiveStuConfirmVisible: false,
      archiveCourseId: '',
      form2: {
        course: ''
      },
      formLabelWidth: '120px',
      allCourse: '',
      fileCourses: '',
      user: this.$parent.user,
      userRole: {},

      yearOptions: [{
        value: '2021-2022'
      }, {
        value: '2020-2021'
      }, {
        value: '2019-2020'
      }, {
        value: '2018-2019'
      }, {
        value: '2017-2018'
      }, {
        value: '2016-2017'
      }, {
        value: '2015-2016'
      }, {
        value: '2014-2015'
      }, {
        value: '2013-2014'
      }, {
        value: '2012-2013'
      }, {
        value: '2011-2012'
      }, {
        value: '2010-2011'
      },],
      semesterOptions: [{
        value: '第一学期'
      }, {
        value: '第二学期'
      }, {
        value: '第三学期'
      }],

      yearValue: '2020-2021',
      semesterValue: '第一学期',
      classCheck: false,
      gradeCheck: false,
      yearCheck: false,
    }
  },

  mounted() {
    this.getUserInfo();
    this.getCourses();
  },
  methods: {
    getUserInfo() {
      getUserInfo().then(res => {
        this.user = res.data.user
        this.userRole = res.data.roles[0]
      })
    },

    showArchive() {
      getArchive().then(res => {
        this.fileCourses = res.data;
      })

      console.log(this.fileCourses)

      this.archiveVisible = true;
    },

    fileTchCourse(id) {
      this.archiveTchConfirmVisible = true;
      this.archiveCourseId = id;
    },

    fileStuCourse(id) {
      this.archiveStuConfirmVisible = true;
      this.archiveCourseId = id;
    },

    fileCourseOne() {
      addArchive(this.archiveCourseId).then(res => {
        this.$message.success("归档成功！");
        this.jumpToList();
      }).catch(err => {
        this.$message.error("归档失败！");
      });
    },

    fileCourseAll() {
      this.$confirm(
          "归档后，全班学生的 该课程 将被移到“归档管理”里，学生也可以自行取消归档",
          "确定要归档全部？",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消"
          }).then(() => {
        addArchiveAll(this.archiveCourseId).then(res => {
          this.$message.success("恢复成功！");
          this.jumpToList();
        }).catch(err => {
          this.$message.error("恢复失败！" + err.message);
        })
      })
    },

    unFileCourse(id) {
      this.$confirm(
          "您和您的学生将可以重新在此课程中互动。\n" +
          "此课程会在课堂页上显示。",
          "要恢复此课程么？",
          {
            confirmButtonText: "恢复",
            cancelButtonText: "取消"
          }).then(() => {
        cancelArchive(id).then(res => {
          this.$message.success("恢复成功！");
          this.jumpToList();
        }).catch(err => {
          this.$message.error("恢复失败！" + err.message);
        });
      })
    },

    exitCourse(courseId) {
      this.$confirm(
          "您的这个课程的任何信息或评论将被永久删除\n" +
          "警告：此操作无法撤消\n" +
          "\n" +
          "提醒：已用课程数包含了“删除课程数”",
          "确定要退课么？",
          {
            confirmButtonText: "退课",
            cancelButtonText: "取消"
          }).then(() => {
        dropoutCourse(courseId).then(res => {
          this.$message.success("退课成功！")
          this.jumpToList();
        }).catch(err => {
          this.$message.error("退课失败！" + err.message)
        });
      })
    },

    createCourse() {
      this.createCourseForm.crseSemester = this.yearValue + " " + this.semesterValue;
      this.createCourseForm.crseJoinCode = 0;

      if (this.classCheck) {
        this.createCourseForm.crseJoinCode += 1;
      }

      if (this.gradeCheck) {
        this.createCourseForm.crseJoinCode += 2;
      }

      if (this.yearCheck) {
        this.createCourseForm.crseJoinCode += 4;
      }

      addCourse(this.createCourseForm).then(res => {
        this.$message.success("创建课程成功！")
        this.jumpToList()
      }).catch(err => {
        this.$message.error("创建课程失败!")
      });
    },

    getCourses() {
      getCourse().then(res => {
        this.allCourse = res.data
      })
    },

    jumpToList() {
      this.getCourses();
      this.joinCourseForm = false;
      this.archiveStuConfirmVisible = false;
      this.archiveTchConfirmVisible = false;
      this.createCourseFormVisible = false;
      this.archiveVisible = false;
    },

    onSubmit() {
      joinCourse(this.form.crseCode).then(() => {
        this.$message.success("加入课程成功")
        this.jumpToList();
      }).catch(() => {
        this.$message.error("该课程不存在或已停用！")
      });
    },

    goToCourse(id) {
      this.$router.push({path: '/main/detail', query: {id: id}})
    },

  }
}
</script>

<style scoped>
.menu-title {
  font-size: 18px;
  margin: auto;
  padding: 0 251px;
}

.cl {
  clear: left;
}

.guidangpaixutop {
  background: #f8f8f8;
  border-bottom: 1px solid #dcdcdc;
  height: 58px;
  width: 100%;
}

.guidangpaixutop ul {
  text-align: center;
}

.guidangpaixutop ul li a {
  color: #818181;
  font-size: 18px;
  display: inline-block;
  line-height: 55px;
  height: 55px;
  min-width: 118px;
  text-align: center;
  padding-left: 18px;
  padding-right: 18px;
}

a {
  text-decoration: none;
}

.guidangpaixutop ul li.on a {
  color: #2d2d2d;
  font-size: 18px;
  border-bottom: 3px solid #aaa;
}


.guidangpaixutop ul li {
  display: inline;
  text-align: center;
}

.drop-button {
  display: block;
  width: 32px;
  height: 32px;
  line-height: 32px;
  text-align: center;
  background: url(../../assets/image/kczt_23.png) center center no-repeat;
}

li, ul {
  list-style: none;
}

.guidangpaixucon dl.gpdl1 dt .kdcgdx {
  z-index: 11;
  display: none;
  position: absolute;
  top: 69px;
  right: 15px;
  border: 1px solid #dcdcdc;
  width: 128px;
  height: 88px;
  box-shadow: 1px 2px 3px #dcdcdc;
  background: #fff;
}

.guidangpaixucon dl.gpdl1 dt .kdcgdx li {
  cursor: pointer;
  width: 105px;
  height: 44px;
  padding-left: 23px;
  line-height: 44px;
}

.guidangpaixucon ul li {
  padding-left: 40px;
  height: 58px;
  line-height: 58px;
  border: 1px solid #ececec;
  margin-top: -1px;
  position: relative;
}

.guidangpaixucon dl.gpdl1 dt .kdcgdx li a {
  color: #2c2c2c;
}

.gd2 {
  padding-left: 70px;
  padding-right: 70px;
}

.guidangpaixucon {
  box-sizing: border-box;
  overflow: hidden;
  position: relative;
}

.clearfix {
  zoom: 1;
}

.guidangpaixucon.gd2 .bb-div {
  position: absolute;
  left: 0;
  top: 0;
  padding: 30px 70px;
  width: 670px;
}

.guidangpaixucon dl.gpdl1 {
  float: left;
  width: 300px;
  -webkit-box-shadow: 0 0.1rem 0.2rem rgba(0, 0, 0, .12), 0 0 0.1rem rgba(0, 0, 0, .12);
  box-shadow: 0 0.1rem 0.2rem rgba(0, 0, 0, .12), 0 0 0.1rem rgba(0, 0, 0, .12);
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  background-clip: padding-box;
}

.guidangpaixucon dl {
  position: relative;
  margin: 15px 10px;
}

.guidangpaixucon dl.gpdl1.dv1 dt {
  /*background: url(/Public/Common/img/ketang_15.png) no-repeat #1066af;*/
}

.guidangpaixucon dl.gpdl1.dv1 dt, .guidangpaixucon dl.gpdl1.dv2 dt, .guidangpaixucon dl.gpdl1.dv3 dt {
  width: 268px;
  clear: both;
  height: 72px;
  padding: 24px 16px 14px;
}

.guidangpaixucon dl.gpdl1 dt .kdcgdx li:hover {
  background: #ababab;
}

.guidangpaixucon dl.gpdl1 dt strong {
  font-size: 20px;
  font-weight: lighter;
  color: #fff;
  height: 26px;
  line-height: 26px;
}

.guidangpaixucon dl.gpdl1 dt strong a {
  display: block;
  color: #fff;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 240px;
}

.text-overflow {
  white-space: nowrap;
  overflow: hidden;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
}

.guidangpaixucon dl.gpdl1 dt p {
  color: #fff;
  line-height: 20px;
  padding-top: 14px;
  font-size: 12px;
}

.guidangpaixucon dl.gpdl1 p {
  width: 264px;
  height: 42px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.guidangpaixucon dl.gpdl1 dt a.kdmorex {
  position: absolute;
  top: 36px;
  right: 15px;
  display: block;
  width: 32px;
  height: 32px;
  line-height: 32px;
  text-align: center;
  background: url(../../assets/image/kczt_23.png) center center no-repeat;
}

.ktcon1 {
  padding-bottom: 12px;
  position: relative;
  z-index: 1;
  justify-content: flex-start;
  box-shadow: 0 1px 0 0 rgba(226, 230, 237, 1);
  margin-bottom: 20px;
  padding-top: 20px;
  font-size: 14.5px;
  height: 26px;
}

.ktp-class {
  padding: 86px 4% 100px;
}

.left-item {
  float: left;
}

.right-item {
  float: right;
  padding: 0 10px;
  cursor: pointer;
}

.learncl {
  position: absolute;
  width: 24px;
  top: 0;
}

.learncl, .teacl {
  height: 28px;
  right: 25px;
}

.learncl .ju {
  background: #32BAF0;
  color: #fff;
}

.teacl {
  position: absolute;
  width: 24px;
  top: 0;
}

.learncl, .teacl {
  height: 28px;
  right: 25px;
}

.teacl .squr {
  width: 0;
  height: 0;
  border-left: 10px solid #fff;
  border-right: 10px solid #fff;
  border-bottom: 4px solid transparent;
  position: absolute;
  z-index: 1;
  top: 21px;
  left: 15px;
}

.teacl .ju {
  background: #fff;
  color: #2C58AB;
}

.learncl .squr {
  width: 0;
  height: 0;
  border-left: 10px solid #32BAF0;
  border-right: 10px solid #32BAF0;
  border-bottom: 4px solid transparent;
  position: absolute;
  top: 22px;
  left: 15px;
}


.cl {
  clear: left;
}

.ktcon2 {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

body, dd, div, dl, dt, fieldset, form, h1, h2, h3, h4, h5, h6, img, input, li, ol, p, table, td, textarea, ul {
  margin: 0;
  padding: 0;
}

* {
  outline: 0;
}

.ktcon2 dl.ktdl1 {
  width: 270px;
  background: #fff;
  border: 1px solid #E2E6ED;
  border-radius: 8px;
  position: relative;
}

.ktcon2 dl {
  position: relative;
  margin-bottom: 24px;
}

.ktcon2 dl dt strong {
  display: block;
  font-size: 20px;
  font-weight: lighter;
  color: #fff;
  height: 62px;
  margin-left: 18px;
  margin-right: 48px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.ktcon2 dl dt strong a {
  color: #fff;
  font-size: 20px;
  line-height: 30px;
}

a {
  text-decoration: none;
}

.ktcon2 dl dt strong > span {
  display: block;
  font-size: 16px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 28px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.ktcon2 dl dt .invitecode {
  height: 22px;
  background: rgba(255, 255, 255, .2);
  border-radius: 2px;
  font-size: 12px;
  font-family: PingFang-SC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 22px;
  text-align: center;
  margin-left: 18px;
  padding-left: 2px;
  padding-right: 4px;
  margin-top: 13px;
  display: inline-block;
}

.invitecode .qrcode {
  display: block;
  float: left;
  width: 20px;
}

.iconketangma1 {
  font-size: 14px;
}

.iconfont {
  font-family: iconfont !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.invitecode .down-menu {
  display: block;
  float: left;
  cursor: pointer;
}

.ktcon2 dl dt .invitecode + span, .ktcon2 dl dt .time {
  float: right;
  font-size: 12px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  padding-right: 14px;
  opacity: .5;
  display: flex;
  align-items: flex-end;
  height: 30px;
}

.learncl {
  position: absolute;
  width: 24px;
  top: 0;
}

.learncl, .teacl {
  height: 28px;
  right: 25px;
}

.learncl .squr {
  width: 0;
  height: 0;
  border-left: 10px solid #32BAF0;
  border-right: 10px solid #32BAF0;
  border-bottom: 4px solid transparent;
  position: absolute;
  top: 22px;
  left: 15px;
}

.learncl .ju {
  background: #32BAF0;
  color: #fff;
}

.learncl .ju, .teacl .ju {
  line-height: 22px;
  left: 15px;
  font-size: 14px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  width: 20px;
  position: absolute;
  text-align: center;
}

body {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  background: #fff;
  color: #2d2d2d;
}

.ktcon2 dl.ktdl1 {
  width: 270px;
  background: #fff;
  position: relative;
  border: 1px solid #E2E6ED;
  border-radius: 8px;
}

.ktcon2 dl {

  margin-bottom: 24px;
}

*, dd, dl, dt, html, li, ul {
  padding: 0;
  margin: 0;
}

* {
  outline: 0;
}

user agent stylesheet
dl {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}

element.style {
  background: url(//assets.ketangpai.com/theme/student/min/22.png);
}

.ktcon2 dl.ktdl1.ktpx1 dt {

  background-size: cover !important;
}

.ktcon2 dl dt {
  clear: both;
  height: 95px;
  padding-top: 18px;
  padding-bottom: 14px;
  border-radius: 4px 4px 0 0;
}

*, dd, dl, dt, html, li, ul {
  padding: 0;
  margin: 0;
}

* {
  outline: 0;
}

user agent stylesheet
dt {
  display: block;
}

.ktcon2 dl dt strong {
  display: block;
  font-size: 20px;
  font-weight: lighter;
  color: #fff;
  height: 62px;
  margin-left: 18px;
  margin-right: 48px;
  overflow: hidden;
}

#notice-pop .pop-title p, .deletetop span, .guidangpaixucon dl.gpdl1 dt strong a, .guidangpaixucon dl.gpdl1 p, .ktcon2 dl dd ul li, .ktcon2 dl dt p, .ktcon2 dl dt strong, .ktcon2 dl dt strong > span, .transfertop span {
  text-overflow: ellipsis;
  white-space: nowrap;
}

* {
  outline: 0;
}

*, dd, dl, dt, html, li, ul {
  padding: 0;
  margin: 0;
}

body {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  background: #fff;
  color: #2d2d2d;
}


.ktcon2 dl dt strong > span {
  display: block;
  font-size: 16px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 28px;
  overflow: hidden;
}

#notice-pop .pop-title p, .deletetop span, .guidangpaixucon dl.gpdl1 dt strong a, .guidangpaixucon dl.gpdl1 p, .ktcon2 dl dd ul li, .ktcon2 dl dt p, .ktcon2 dl dt strong, .ktcon2 dl dt strong > span, .transfertop span {
  text-overflow: ellipsis;
  white-space: nowrap;
}

* {
  outline: 0;
}

*, dd, dl, dt, html, li, ul {
  padding: 0;
  margin: 0;
}


.ktcon2 dl dt .invitecode {
  height: 22px;
  background: rgba(255, 255, 255, .2);
  border-radius: 2px;
  font-size: 12px;
  font-family: PingFang-SC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 22px;
  text-align: center;
  margin-left: 18px;
  padding-left: 2px;
  padding-right: 4px;
  margin-top: 13px;
  display: inline-block;
}

.ktcon2 dl dt p {
  color: #fff;
  line-height: 40px;
  padding: 0 20px;
  margin-top: 7px;
  font-size: 12px;
  height: 40px;
  overflow: hidden;
}

#notice-pop .pop-title p, .deletetop span, .guidangpaixucon dl.gpdl1 dt strong a, .guidangpaixucon dl.gpdl1 p, .ktcon2 dl dd ul li, .ktcon2 dl dt p, .ktcon2 dl dt strong, .ktcon2 dl dt strong > span, .transfertop span {
  text-overflow: ellipsis;
  white-space: nowrap;
}

body, dd, div, dl, dt, fieldset, form, h1, h2, h3, h4, h5, h6, img, input, li, ol, p, table, td, textarea, ul {
  margin: 0;
  padding: 0;
}

* {
  outline: 0;
}

.iconketangma1 {
  font-size: 14px;
}

.iconfont {
  font-family: iconfont !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.ktcon2 dl dt .invitecode + span, .ktcon2 dl dt .time {
  float: right;
  font-size: 12px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  padding-right: 14px;
  opacity: .5;
  display: flex;
  align-items: flex-end;
  height: 30px;
}

.ktcon2 dl dt .invitecode + span, .ktcon2 dl dt .time {
  float: right;
  font-size: 12px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  padding-right: 14px;
  opacity: .5;
  display: flex;
  align-items: flex-end;
  height: 30px;
}


.ktcon2 dl dt div.zhidings-two {
  color: rgba(153, 153, 153, 1);
  right: 40px;
}

.ktcon2 dl dt div.zhidings {
  bottom: 8px;
  right: 52px;
  z-index: 1;
  width: 48px;
  border-radius: 2px;
}

.ktcon2 dl dt a.kdmore, .ktcon2 dl dt div.zhidings {
  position: absolute;
  display: block;
  cursor: pointer;
  text-align: center;
}

.ktcon2 dl dt div.zhidings a {
  font-size: 12px;
  color: #A0A0A0;
}

a {
  text-decoration: none;
}

.ktcon2 dl dt a.kdmore {
  bottom: 0;
  right: 5px;
  height: 32px;
  line-height: 32px;
}

.ktcon2 dl dt a.kdmore, .ktcon2 dl dt div.zhidings {
  position: absolute;
  display: block;
  cursor: pointer;
  text-align: center;
}

a {
  text-decoration: none;
}

.ktcon2 dl dt a.kdmore span {
  float: left;
  font-size: 12px;
  font-family: PingFang SC;
  font-weight: 400;
  color: rgba(44, 88, 171, 1);
}

.ktcon2 dl dt a.kdmore i {
  display: inline-block;
  width: 10px;
  height: 100%;
  margin-left: 1px;
}

.ktcon2 dl dt .kdcgd li {
  cursor: pointer;
  width: 105px;
  height: 30px;
  padding-left: 23px;
  line-height: 30px;
}

.ktcon2 dl dt .kdcgd li a {
  color: #2c2c2c;
}


li, ul {
  list-style: none;
}

*, dd, dl, dt, html, li, ul {
  padding: 0;
  margin: 0;
}

.ktcon2 dl dd {
  background: #fff;
  clear: both;
  height: 141px;
  border-radius: 0 0 8px 8px;
}

.ktcon2 dl dd ul {
  margin-top: 15px;
  padding: 0 16px;
  height: 105px;
  border-bottom: 1px solid rgba(226, 230, 237, 1);
}

.ktcon2 dl dd ul li {
  height: 30px;
  line-height: 30px;
  width: 100%;
  overflow: hidden;
}

.ktcon2 dl dd ul li, .ktcon2 dl dd ul li a {
  font-size: 14px;
  color: rgba(31, 32, 35, 1);
  font-family: PingFangSC-Medium;
  font-weight: 500;
}

#notice-pop .pop-title p, .deletetop span, .guidangpaixucon dl.gpdl1 dt strong a, .guidangpaixucon dl.gpdl1 p, .ktcon2 dl dd ul li, .ktcon2 dl dt p, .ktcon2 dl dt strong, .ktcon2 dl dt strong > span, .transfertop span {
  text-overflow: ellipsis;
  white-space: nowrap;
}

.ktcon2 dl dd ul li.off span {
  font-size: 12px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(95, 99, 104, 1);
}

.ktcon2 dl dd ul li span {
  padding-bottom: 1px;
}

.ktcon2 dl dd ul li {
  height: 30px;
  line-height: 30px;
  width: 100%;
  overflow: hidden;
}

.ktcon2 dl dd ul li, .ktcon2 dl dd ul li a {
  font-size: 14px;
  color: rgba(31, 32, 35, 1);
  font-family: PingFangSC-Medium;
  font-weight: 500;
}

.ktcon2 dl dd .ddfoot {
  width: 80%;
  position: absolute;
  bottom: 4px;
  left: 16px;
  right: 16px;
}

.clearfix {
  zoom: 1;
}

.user-avatar-area {
  height: 25px;
  line-height: 25px;
  float: left;
}

.user-avatar-area img {
  border: 2px solid #fff;
}

.user-avatar-area img {
  width: 20px;
  height: 20px;
  display: inline-block;
  float: left;
  border-radius: 50%;
}

.user-avatar-area .teachername {
  height: 25px;
  line-height: 25px;
  margin-left: 10px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  float: left;
}

.user-avatar-area .teachername, .user-avatar-area + a {
  font-size: 12px;
  color: rgba(95, 99, 104, 1);
  font-family: PingFangSC-Medium;
  font-weight: 500;
}


</style>

