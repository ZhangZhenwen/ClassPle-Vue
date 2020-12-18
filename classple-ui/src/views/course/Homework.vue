<template>
  <div class="homework-page">
    <div class="width980 clearfix" v-if="teacher">
      <div class="cWidth-title">
        <div class="send-an">
          <a id="act1" @click="showPersonAssignmentForm">发布个人作业</a>
        </div>
        <div class="send-an">
          <a @click="showGroupAssignmentForm">发布小组作业</a>
        </div>
        <div class="fr downcharts">
          <i class="iconfont iconxiazai"></i>下载所有作业
        </div>
      </div>
      <div class="send-box" id="send-box" v-show="assignmentFormVisible">
        <el-form>
          <el-form-item>
            <el-input v-model="assignmentForm.asgnName" placeholder="作业名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input
                type="textarea"
                :rows="5"
                placeholder="作业简介，作业格式等要求,温馨提醒：word,pdf,txt等文字性的文档，都可以进行查重"
                v-model="assignmentForm.asgnIntroduction">
            </el-input>
          </el-form-item>
          <el-form-item label="截止日期：">
            <el-date-picker
                v-model="assignmentForm.deadline"
                type="datetime"
                placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="满分值：">
            <el-input-number v-model="assignmentForm.maxScore" controls-position="right" :min="0"
                             :max="999"></el-input-number>
          </el-form-item>
          <el-form-item label="是否差重：">
            <el-switch v-model="assignmentForm.isRepeat">
            </el-switch>
            <span v-if="assignmentForm.isRepeat">
              查重警戒值：
              <el-input-number v-model="assignmentForm.repeatRate" controls-position="right" :min="1"
                               :max="100"></el-input-number>
            </span>
            <span v-if="assignmentForm.isRepeat">

            </span>
          </el-form-item>
          <div class="opt-cont">
            <div class="sc-box fl">
              <el-upload
                  class="upload-demo"
                  :action="upload.url"
                  ref="upload"
                  :headers="upload.headers"
                  :on-success="handleUploadSuccess"
                  multiple
                  :limit="3"
                  :file-list="upload.fileList">
                <el-button size="middle" type="primary">点击上传</el-button>
              </el-upload>
            </div>
            <div class="opt-btn fr">
              <el-button @click="cancelAssignmentForm">取消</el-button>
              <el-button type="primary" @click="submitAssignment">
                <span v-if="assignmentForm.asgnType === 'P'">发布个人作业</span>
                <span v-if="assignmentForm.asgnType === 'G'">发布小组作业</span>
              </el-button>
            </div>
          </div>
        </el-form>
      </div>
    </div>

    <div class="homework-cont width980 editor-page" v-for="(item, index) in assignmentList">
      <div class="homework-list clearfix">
        <div class="homework-box ">
          <div class="announce-cont-box">
            <div class="title clearfix">
              <div class="work-type fl" data-time="1607071464" data-fullscore="100">
                <span v-if="item.asgnType === 'P'">个人作业</span>
                <span v-if="item.asgnType === 'G'">组队作业</span>
                <span> {{ item.releasedDate }} </span>
              </div>
              <el-dropdown class="opt">
                <a class="opt-btn"></a>
                <el-dropdown-menu slot="dropdown" class="opt-box2">
                  <el-dropdown-item class="edit">编辑</el-dropdown-item>
                  <el-dropdown-item class="saveb">保存到...</el-dropdown-item>
                  <el-dropdown-item class="del" @click.native="deleteAssignment(item)" >删除</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
            <div class="announce-cont clearfix" style="margin-bottom: 10px; position: relative">
              <div class="work-new-r fr">
                <div class="total-cont" v-if="teacher">
                  <p class="score">
                    <router-link :to="{path: '/homework/review', query: {id: item.asgnId}}">{{ item.params.check }}
                    </router-link>
                    <router-link :to="{path: '/homework/review', query: {id: item.asgnId}}" class="green">已批
                    </router-link>
                  </p>
                  <p class="score">
                    <router-link :to="{path: '/homework/review', query: {id: item.asgnId}}">{{
                        item.params.uncheck
                      }}
                    </router-link>
                    <router-link :to="{path: '/homework/review', query: {id: item.asgnId}}" class="grey">未批
                    </router-link>
                  </p>
                  <p class="score">
                    <router-link :to="{path: '/homework/review', query: {id: item.asgnId}}">{{
                        item.params.unpaid
                      }}
                    </router-link>
                    <router-link :to="{path: '/homework/review', query: {id: item.asgnId}}" class="red">未交</router-link>
                  </p>
                </div>
              </div>
              <div class="work-new-l" style="margin-right:340px;">
                <h3 class="work-title">
                  <router-link v-if="teacher" :to="{path: '/homework/review', query: {id: item.asgnId}}" title="222">
                    {{ item.asgnName }}
                  </router-link>
                  <router-link v-else :to="{path: '/homework/handup', query: {id: item.asgnId}}" title="222">
                    {{ item.asgnName }}
                  </router-link>
                </h3>
                <div class="word">
                  <div class="p">{{ item.asgnIntroduction }}</div>
                </div>
                <div class="annex">
                  <ul class="clearfix">
                    <li v-for="(fileItem) in item.files" @click="handleDownload(fileItem)">
                      <img v-if="fileItem.type === 'doc'" src="../../assets/image/fileicon/file_ext_big_doc.png">
                      <img v-if="fileItem.type === 'docx'" src="../../assets/image/fileicon/file_ext_big_doc.png">
                      <img v-else src="../../assets/image/fileicon/file_ext_big_others.png">
                      <h4>
                        <a class="fileext preview" :title="fileItem.fileName">{{ fileItem.fileName }}</a>
                      </h4>
                      <a class="download">下载</a>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="work-new-r fl button" v-if="!teacher">
                <a class="view-work" v-if="item.params.status">{{ item.params.status }}</a>
                <router-link :to="{path: '/homework/handup', query:{id: item.asgnId}}" class="sc-btn" v-else>上传作业
                </router-link>
              </div>
            </div>
            <div class="comment-new">
              <p>截止日期：<span>{{ item.deadline }}</span></p>
              <a><span><b>0</b>条讨论</span>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getCourseDetail} from "@/api/course";
import {deleteAssignment, getAssignmentList, postAssignment} from "@/api/assignment";
import moment from "moment";
import {getToken} from "@/utils/auth";
import {download} from "@/api/common";

export default {
  name: "Homework",

  data() {
    return {
      id: this.$route.query.id,
      upload: {
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: {Authorization: "Bearer " + getToken()},
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/resources/upload",
        // 上传的文件列表
        fileList: []
      },
      assignmentList: {
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
          check: '',
          uncheck: '',
          unpaid: '',
        },
        files: [],
      },
      assignmentForm: {
        params: {
          crseId: '',
          files: [],
        }
      },
      assignmentFormVisible: false,
      course: {},

      pathToHandup: {path: '/homework/handup', query: {id: this.id}},
      pathToDiscuss: {path: '/homework/discuss', query: {id: this.id}},
      pathToReview: {path: '/homework/review', query: {id: this.id}},

      teacher: false,
    }
  },

  created() {
    this.getCourse();
    this.getAssignmentList();
  },

  methods: {
    getCourse() {
      getCourseDetail(this.id).then(res => {
        console.log(res.data)
        this.course = res.data;
        this.teacher = this.course.params.role === 'tch' || this.course.params.role === 'admin'
      })
    },

    getAssignmentList() {
      getAssignmentList(this.id).then(res => {
        this.assignmentList = res.data
      })
    },

    handleUploadSuccess(response, file, fileList) {
      this.assignmentForm.params.files.push(response.data)
    },

    submitAssignment() {
      this.assignmentForm.params.crseId = this.id;
      this.assignmentForm.deadline = moment(this.assignmentForm.deadline).format('yyyy-MM-DD HH:mm:ss')

      postAssignment(this.assignmentForm).then(res => {
        this.cancelAssignmentForm();
        this.getAssignmentList();
        this.$message.success("添加作业成功！")
      }).catch(err => {
        this.$message.error("添加作业失败！" + err.message)
      })
    },

    showPersonAssignmentForm() {
      this.assignmentFormVisible = true;
      this.assignmentForm.asgnType = 'P';
    },

    showGroupAssignmentForm() {
      this.assignmentFormVisible = true;
      this.assignmentForm.asgnType = 'G';
    },

    cancelAssignmentForm() {
      this.assignmentFormVisible = false;
    },

    handleDownload(row) {
      const url = 'http://localhost:8080/resources/download?id=' + row.fileId;
      const a = document.createElement('a');
      a.setAttribute('download', row.fileName);
      a.setAttribute('target', '_blank');
      a.setAttribute('href', url);
      a.click();
    },

    deleteAssignment(row) {
      deleteAssignment(row.asgnId).then(() => {
        this.getAssignmentList()
        this.$message.success("删除成功！")
      }).catch(() => {
        this.$message.error("删除失败！")
      })
    }
  }
}
</script>

<style scoped>
.homework-page {
  padding-bottom: 60px;
}

.width980 {
  width: 1224px;
  margin-left: auto;
  margin-right: auto;
}

.clearfix {
  zoom: 1;
}

.cWidth-title {
  width: auto;
  height: auto;
  padding-top: 40px;
  overflow: hidden;
}

.send-an {
  width: 136px;
  border: 1px solid #32BAF0;
  height: 36px;
  border-radius: 4px;

  background: #FFF;
  margin-right: 16px;
  text-align: center;
  line-height: 36px;
  float: left;
}

.send-an a {
  color: #32BAF0;
  display: block;
  cursor: pointer;
}

.downcharts {
  color: #5F6368;
  font-size: 14px;
  cursor: pointer;
  user-select: none;
  line-height: 35px;
  margin-top: -10px;
  background: url('../../assets/image/down-url.png') left no-repeat;
  padding-left: 27px;
}

.homework-page .width980 {
  width: 1224px;
  margin-left: auto;
  margin-right: auto;
}

.homework-cont, .send-box {
  margin-top: 20px;
}

.homework-page .width980 {
  width: 1224px;
  margin-left: auto;
  margin-right: auto;
}

.homework-cont, .send-box {
  margin-top: 20px;
}

.homework-cont .homework-list .homework-box {
  padding: 0 20px 0 40px;
  border: 1px solid #E2E6ED;
  border-radius: 8px;
  background: #FFF;
  margin-bottom: 20px;
}

.homework-list .homework-box .announce-cont-box {
  padding: 0 0 20px;
}


.homework-cont .homework-list .homework-box .announce-cont-box {
  /*padding-bottom: 10px;*/
  /*padding-right: 37px;*/
}

.homework-cont .homework-list .homework-box .announce-cont-box .work-new-r {
  /*position: absolute;*/
  bottom: 0;
  right: 0;
}

.homework-cont .homework-list .homework-box .announce-cont-box .button {
  position: absolute;
  bottom: 0;
  right: 0;
}

.announce-cont-box {
  padding: 0 20px 10px 30px;
  position: relative;
}

.announce-cont-box .title .work-type {
  color: #AAA;
  font-size: 0;
  padding: 21px 0 10px;
  line-height: 30px;
}

.announce-cont-box .title .work-type span:first-child {
  color: #5F6368;
  background: #F1F3F4;
  padding: 3px 5px;
  border-radius: 2px;
}

.announce-cont-box .title .work-type span {
  font-size: 14px;
  margin-right: 15px;
  color: #5F6368;
}

.announce-cont-box .title .work-type {
  color: #AAA;
  font-size: 0;
  padding: 21px 0 10px;
  line-height: 30px;
}

.announce-cont-box .announce-cont .annex li img {
  width: 64px;
  height: 64px;
}

.announce-cont-box .title .opt {
  float: right;
  width: 34px;
  height: 34px;
  margin-top: 10px;
  position: relative;
}

.announce-cont-box .title .opt .opt-btn {
  display: block;
  width: 34px;
  height: 34px;
  background: url(../../assets/image/opt1.png) left center no-repeat;
  border-radius: 3px;
}

.announce-cont-box .announce-cont .annex li .download {
  color: #4d90fe;
  font-size: 12px;
  display: none;
  line-height: 24px;
}

.announce-cont-box .title a {
  color: #404040;
  text-decoration: none;
  outline: 0;
  blr: expression(this.onFocus=this.blur());
  cursor: pointer;
}

.announce-cont-box .announce-cont .annex li h4 a {
  display: block;
  font-size: 12px;
  color: #2c2c2c;
  font-weight: 400;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  line-height: 20px;
}

.homework-cont .homework-list .homework-box .announce-cont-box .work-new-r a.sc-btn {
  background: #32BAF0;
}

a.view-work {

}


.homework-cont .homework-list .homework-box .announce-cont-box .work-new-r > a {
  float: right;
  width: 80px;
  height: 30px;
  text-align: center;
  line-height: 30px;
  color: #FFF;
  border-radius: 4px;
  font-size: 14px;
}

.homework-cont .homework-list .homework-box .announce-cont-box .work-new-r > a.view-work {
  width: 80px;
  height: 30px;
  line-height: 30px;
  color: #32BAF0;
  border: 1px solid #32BAF0;
}

.homework-cont .homework-list .homework-box .announce-cont-box .work-new-l {
  margin-right: 320px;
  height: auto;
}

.homework-cont .homework-list .homework-box .announce-cont-box .work-new-l:hover a {
  color: #32BAF0;
}

.homework-cont .homework-list .work-new-r .total-cont {
  min-width: 290px;
  height: 66px;
  margin-top: 5px;
  margin-right: 0;
  border: none;
  background: #FFF;
  padding: 0;
}

.homework-cont .homework-list .work-new-r .total-cont p {
  font-size: 0;
  line-height: 20px;
  text-align: center;
  float: left;
}

.homework-cont .homework-list .total-cont {
  min-width: 120px;
  height: 46px;
  margin-right: 11px;
  border: 1px solid #c8c8c8;
  background: #FFF;
  padding: 10px 18px;
}

.homework-cont .homework-list .work-new-r .total-cont p a:first-child {
  font-size: 30px;
  height: 44px;
  line-height: 44px;
}

.homework-cont .homework-list .work-new-r .total-cont p a:last-child {
  font-size: 14px;
  height: 26px;
  line-height: 26px;
}

.homework-cont .homework-list .work-new-r .total-cont p a {
  display: block;
  min-width: 80px;
  padding-left: 15px;
  color: #707070;
  padding-right: 15px;
}

.homework-cont .homework-list .work-new-r .total-cont .grey {
  color: #970002;
}

.announce-cont-box .work-title a {
  display: inline-block;
  color: #3B3D45;
  font-size: 20px;
  font-weight: 400;
  line-height: 30px;
  padding-bottom: 5px;
  word-break: break-all;
  word-wrap: break-word;
}

.homework-page .announce-cont-box .announce-cont .word {
  height: auto;
  overflow: auto;
  max-height: inherit;
}

.announce-cont-box .announce-cont .word {
  line-height: 30px;
  max-height: 100px;
  overflow: hidden;
  font-size: 14px;
  word-wrap: break-word;
  word-break: break-all;
}

.announce-cont-box .announce-cont .word .p, .announce-cont-box .announce-cont {
  color: #707070;
  overflow: hidden;
  line-height: 1.8;
}

.homework-page .announce-cont-box .announce-cont .annex, .homework-page .announce-cont-box .announce-cont .word {
  height: auto;
  overflow: auto;
  max-height: inherit;
}

.announce-cont-box .announce-cont .annex {
  max-height: 110px;
  margin-top: 10px;
  overflow: hidden;
}

.announce-cont-box .announce-cont .annex li {
  width: 92px;
  float: left;
  text-align: center;
  height: 110px;
  margin-right: 40px;
  margin-bottom: 10px;
}

.comment-new {
  height: 20px;
  line-height: 20px;
}

.comment-new a, .comment-new p {
  float: left;
  margin-right: 20px;
  color: #A0A0A0;
}

.comment-new p, .downcharts i {
  padding-right: 10px;
}

.comment-new p b {
  font-weight: 400;
  margin-left: 10px;
}

.announce-cont-box .title .opt .opt-box2 {
  position: absolute;
  right: 0;
  top: 34px;
  border: 1px solid #dcdcdc;
  box-shadow: 1px 2px 4px rgba(0, 0, 0, .2);
  width: 230px;
  padding: 5px 0;
  background: #FFF;
  display: none;
  z-index: 2;
}

.announce-cont-box .title .opt .opt-box2 a {
  display: block;
  height: 37px;
  line-height: 37px;
  padding-left: 24px;
  color: #2c2c2c;
}

.announce-cont-box .title a {
  color: #404040;
  text-decoration: none;
  outline: 0;
  blr: expression(this.onFocus=this.blur());
  cursor: pointer;
}

.title {
  height: 66px;
}

#send-box {
  border: 1px solid #E2E6ED;
}

.send-box {
  border-radius: 8px;
  background: #fff;
  padding: 20px 20px 60px;
}

.homework-cont, .send-box {
  margin-top: 20px;
}

.send-box .opt-cont {
  padding: 40px 0 0;
}

li {
  list-style: none;
}

a {
  text-decoration: none;
  outline: none;
  blr: expression(this.onFocus=this.blur ());
  cursor: pointer;
}

.fl {
  float: left;
}

.fr {
  float: right;
}
</style>
