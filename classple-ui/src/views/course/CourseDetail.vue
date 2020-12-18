<template>
  <div class="course-detail-app">
    <div class="top">
      <ul style="float: left">
        <li style="list-style: none;margin-left: 50px">
          <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;margin-top: -10px">
            <el-breadcrumb-item to="/main">课堂</el-breadcrumb-item>
            <el-breadcrumb-item>{{ course.crseName }}</el-breadcrumb-item>
          </el-breadcrumb>
        </li>
      </ul>
      <u-nav></u-nav>
    </div>

    <div class="top-box"
         style="background-image: url(//assets.ketangpai.com/theme/teacher/big/21.png);
         filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='//assets.ketangpai.com/theme/big/21.jpg',
         sizingMethod='scale')">
      <a class="select-theme">更改背景</a>
      <div class="topm">
        <h1 data-semester="2020-2021" data-term="1" style="padding-top: 0;padding-bottom: 5px">
          {{ course.crseName }}<i></i>
        </h1>
        <h2>{{ course.className }}</h2>

        <div class="box-box">
          <div class="sele" id="course-qrcode">
            <div class="qrcode fl"></div>
            <div class="infotextmain">加课二维码</div>
          </div>
          <div class="sele" id="course-code-select" data-abc="5X6G7J" style="margin-left:10px;">
            <div class="infotextmain" style="padding-left:10px;">加课码:</div>
            <div class="codetext"> {{ this.course.crseCode }}</div>
            <i class="iconfont iconzhucebuchongxinxidanchuang-xiala"></i>
          </div>
          <ul>
            <li class="li3">
              <router-link :to="{path: '/member/index', query: {id: this.$route.query.id}}">
                <i class="iconfont iconchengyuan"></i>
                成员 {{ this.course.params.memberCount }}
              </router-link>
            </li>
            <li class="li0">
              <a>
                <i class="iconfont iconshujufenxi"></i> 数据分析 </a>
            </li>
            <li class="li4">
              <a>
                <i class="chengji"
                   style="background: url(../../assets/image/achievementicons.png) no-repeat 0/14px;vertical-align:-2px;width: 14px;height: 14px;"></i>
                成绩管理 </a>
            </li>
          </ul>
          <div class="coursedatabox" style="float: right" v-if="teacher">
            <div class="coursedata">
              <ul>
                <li style="display:block">0</li>
                <li>互动个数</li>
              </ul>
              <ul>
                <li style="display:block">2</li>
                <li>发布作业</li>
              </ul>
              <ul>
                <li style="display:block">0</li>
                <li>发布测试</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="coursebannernew banner cl coursebanner ">
      <div class="in-header" style="background: none repeat scroll 0 0">
        <div id="third  -nav" class="nav gWidth">
          <router-link :to="{path: '/interact/index', query: {id: this.id}}">课堂互动</router-link>
          <router-link :to="{path: '/course/homework/index', query: {id: this.id}}">作业</router-link>
          <router-link :to="{path: '/TopicDiscuss/index', query: {id: this.id}}">话题</router-link>
          <router-link :to="{path: '/Courseware/index', query: {id: this.id}}">资料</router-link>
          <router-link to="">测试(考试)</router-link>
          <router-link to="">公告</router-link>
        </div>
      </div>
    </div>
    <router-view></router-view>
  </div>

</template>

<script>
import {getCourse, getCourseDetail} from "@/api/course";
import UserNav from "@/views/nav/UserNav";
import {getAssignmentList, postAssignment} from "@/api/assignment";
import {isCourseTch} from "@/utils/application";

export default {
  name: "CourseDetail",

  components: {
    'u-nav': UserNav
  },

  data() {
    return {
      id: this.$route.query.id,

      showDivEdit: false,
      showDivAdd: false,
      type: '',
      course: {
        crseName: '',
        crseCode: '',
        memberCount: '',
      },
      workAddForm: {
        asgnId: '',
        asgnName: '',
        asgnIntroduction: '',
        asgnType: 'P',
        releasedDate: '',
        deadline: '',
        maxScore: '',
        isRepeat: true,
        repeatRate: '',
        returnRate: '',
        params: {
          crseId: this.$route.query.id,
        },
        files: [],
      },
      workEditForm: {
        files: [],
      },
      allWork: {},
      allStudent: {},
      workId: '',
      fileUrl: '',
      activeName: 'second',
      assStuId: {
        assId: this.workId,
        no: this.id
      },
      fileList: [],
      fileAdd: [],

      teacher: false,
    };

  },

  created() {
    this.getCourse();
    this.getAllWorks();
  },

  methods: {

    jumpIndex() {
      this.showDivAdd = false;
      this.showDivEdit = false;
      this.getAllWorks();
    },

    getCourse() {
      getCourseDetail(this.id).then(res => {
        this.course = res.data;
        this.teacher = this.course.params.role === 'tch' || this.course.params.role === 'admin'
      }).catch(err => {
        alert(err.response.data.msg);
      });
    },

    getAllWorks() {
      getAssignmentList(this.id).then(res => {
        console.log(res.data)
        this.allWork = res.data
      })
    },
  }
}
</script>

<style scoped>
.top-box {
  position: relative;
  margin: 0 auto;
  background: url(/Public/Common/img/ktp2.jpg) no-repeat;
  width: 1224px;
  background-position: 0 center;
  background-size: cover;
  height: 200px;
  margin-top: 105px;
  border-radius: 8px 8px 0 0;
  padding: 48px 0 0 40px;
  box-sizing: border-box;
}

.select-theme {
  color: #FFF;
  background: url(../../assets/image/theme.png) no-repeat 0;
  position: absolute;
  top: 15px;
  right: 15px;
  padding-left: 30px;
  cursor: pointer;
}

.topm h1 {
  padding-top: 22px;
  font-size: 36px;
  color: #fff;
  font-weight: 500;
  margin: 0;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.topm h1 i {
  display: inline-block;
  width: 24px;
  height: 24px;
  background: url(../../assets/image/edit-course.png) no-repeat 0/24px;
  cursor: pointer;
  margin-left: 15px;
}

.topm h2 {
  font-size: 20px;
  font-family: PingFang SC;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 28px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 600px;
  margin-top: 0;
  margin-bottom: 0;
}

.topm .box-box {
  /* width: 122px; */
  height: 35px;
  position: relative;
  margin: 20px auto 0;
}

.topm .sele {
  /* border: 1px solid #dcdcdc; */
  font-size: 15px;
  color: #fff;
  text-align: left;
  height: 24px;
  line-height: 24px;
  /* background: url(/Public/Home/img/pcxl_1.png) no-repeat; */
  background-position: 100px 10px;
  background-size: 14px auto;
  display: inline-block;
  position: relative;
  cursor: pointer;
  user-select: none;
  background: rgba(255, 255, 255, .4);
  border-radius: 2px;
  padding-right: 10px;
}

.topm .sele .qrcode {
  background: url(../../assets/image/qrcodehover.png);
  width: 24px;
  height: 24px;
  display: inline-block;
  line-height: 24px;
  background-repeat: no-repeat;
  background-position: center;
  background-size: 16px;
}

.topm .sele .codetext {
  display: inline-block;
  line-height: 24px;
  height: 24px;
  font-size: 12px;
}

.topm ul {
  display: inline-block;
  padding: 0;
  margin: 0;
}

.topm .selecon ul li {
  cursor: pointer;
  padding: 0px;
  margin: 0px;
  /* padding-left: 20px; */
  display: block;
  width: 100%;
  height: 44px;
  line-height: 44px;
  color: #2c2c2c;
  font-size: 14px;
  cursor: pointer;
}

.topm ul li {
  display: inline-block;
  height: 24px;
  line-height: 24px;
  margin-left: 10px;
  position: relative;
  background: rgba(255, 255, 255, .4);
  text-align: center;
  padding-left: 10px;
  padding-right: 10px;
}

.topm ul li.li4 {
  background: #32BAF0;
}

a {
  text-decoration: none;
  outline: none;
  blr: expression(this.onFocus=this.blur ());
  cursor: pointer;
}

li {
  list-style: none;
}

.topm .selecon ul li a {
  padding: 0px;
  margin: 0px;
  cursor: pointer;
  line-height: 44px;
  color: #2c2c2c;
  display: block;
  width: 100%;
  height: 44px;
  text-align: center;
}

.topm ul li a {
  color: #fff;
  font-weight: lighter;
  font-size: 12px;
  display: block;
  height: 24px;
  line-height: 24px;
}

.topm .selecon {
  position: absolute;
  z-index: 11;
  top: 33px;
  right: 0px;
  display: none;
  width: 126px;
  padding: 5px 0;
  border: 1px solid #dcdcdc;
  background: #fff;
}

.coursedatabox .coursedata {
  margin-right: 30px;
}

.coursedatabox .coursedata ul {
  margin-top: -50px;
}

.coursedatabox .coursedata ul li {
  background: none;
}

.coursedatabox .coursedata ul li:first-of-type {
  font-size: 40px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 48px;
  height: 50px;
}

.coursedatabox .coursedata ul li + li {
  font-size: 12px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 16px;
  width: 80px;
}

.coursebannernew {
  width: 1224px;
  background: rgba(241, 243, 244, 1);
  border-radius: 0px 0px 8px 8px;
  margin: 0 auto;
}
.banner {
  height: 72px;
}

.coursebanner .in-header {
  background: rgba(241, 243, 244, 1);
  border-radius: 0px 0px 8px 8px;
}
.in-header {
  height: 72px;
  background: #4d90fe;
  position: relative;
  display: inline;
}

.coursebannernew .gWidth {
  padding-left: 35px;
  width: 100%;
  text-align: left;
  box-sizing: border-box;
  box-shadow: none;
}
.in-header .nav {
  height: 72px;
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

.coursebanner .in-header .nav a {
  color: #818181;
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

.coursebanner .in-header .nav a.active {
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(59, 61, 69, 1);
  border-bottom: 4px solid #328eeb;
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

.fl {
  float: left;
}

.infotextmain {
  display: inline-block;
  color: white;
  font-size: 12px;
  height: 24px;
  line-height: 24px;
}

.iconfont {
  font-family: iconfont !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.iconfont.iconchengyuan {
  font-size: 14px;
  margin-right: 4px;
  background: url();
}

.iconfont.iconshujufenxi {
  font-size: 14px;
}

.tb-edit .current-row {
  display: block
}

.tb-edit .current-row .el-input + span {
  display: none
}


.avatar-uploader {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader ul li {
  width: 300px;
}

.avatar-uploader {
  border-color: #409EFF;
}

.top {
  width: 100%;
  height: 72px;
  position: fixed;
  z-index: 5;

  top: 0;

  background: #FFFFFF;

  box-sizing: border-box;
  box-shadow: 0 0 10px #FFFFFF;

}

li {
  list-style: none;

}

title {
  width: 70%;
  height: 980px;
  margin-left: 220px;
  background: chartreuse;

  z-index: -1;
}
</style>
