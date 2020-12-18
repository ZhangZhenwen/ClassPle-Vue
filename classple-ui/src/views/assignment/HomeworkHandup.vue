<template>
  <div class="mywork-page">
    <div class="gWidth cWidth-new company">
      <div class="txt">
        <p style="color: #3C4043;font-size: 20px;margin-bottom: 8px;"></p>
        <p style="color: #202124;font-size: 14px;"></p>
      </div>
    </div>
    <div class="gWidth cWidth-new">
      <div class="work-title clearfix">
        <div class="recruitment-post">
          <span class="job-name">{{ assignment.asgnName }}</span>
        </div>
        <div class="description">
          <p>
            {{ assignment.asgnIntroduction }}
          </p>
          <p><br></p></div>
        <p>截至日期：<span>{{ assignment.deadline }}</span></p>
        <p>个人作业</p>
        <p title="温馨提醒：所有个人作业均需要查重" v-if="assignment.isRepeat">需要查重</p>
        <p class="whohandup"><i class="iconfont iconchengyuantuikexinxi1"></i>查看谁交了</p>
      </div>
      <div id="viewer-handup">
        <div class="sc-tj-box">
          <div class="sc-tj fl">
            <a @click="submitTask" class="tj-btn">提交</a>
          </div>
          <div class="status fr">
            <span class="wwc" v-if="task.taskId !== undefined">已完成</span>
            <span class="wwc" v-else-if="task.score !== undefined">得分：{{ task.score }}</span>
            <span class="wwc" v-else>未完成</span>
          </div>
        </div>
        <div class="homework-box">
          <div class="work-list">
            <!-- <div class="will-work hide">
                <h3>即将提交的文件</h3>
                <p>（提交文档、图片，老师可在线批改,压缩包仅能下载）</p>
            </div> -->
            <ul>
              <li :id="item.fileId" v-for="(item, index) in task.files">
                <div class="file">
                  <div class="file-icon fl">
                    <a class="fileext" :title="item.fileName" @click="downloadTaskFile(item)">
                      <img src="../../assets/image/fileicon/file_ext_big_doc.png">
                    </a>
                  </div>
                  <div class="file-cont fl">
                    <h3 class="file-name">
                      <a class="fileext" :title="item.fileName" @click="downloadTaskFile(item)">
                        {{ item.fileName }}
                      </a>
                    </h3>
                    <div class="opt clearfix">
                      <p class="file-size fl" style="line-height: 14px;">{{ item.fileSize }}字节
                        <a class="cancel hide"
                           style="display: inline;" @click="deleteTaskFile(item)">删除</a></p>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <div class="sc-tj-box sc-tj-box-new">
            <div class="sc-tj">
              <i class="sc-btn iconfont iconxinjian1 webuploader-container">
                <div class="webuploader-pick">
                </div>
                <div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; overflow: hidden;">
                  <label slot="trigger" @click="selectUpload"
                         style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                </div>
              </i>
              <div class="shangchuan">
                <el-upload
                    class="upload-demo"
                    ref="upload"
                    :action="upload.url"
                    :headers="upload.headers"
                    :on-success="handleUploadSuccess"
                    multiple
                    :limit="3"
                    :file-list="upload.fileList"
                    :show-file-list="true">
                  <a class="sc-btn webuploader-container">
                    <div class="webuploader-pick">添加作业文件</div>
                    <div id="rt_rt_1eoscgfeuoi996r1atr16vq185e3"
                         style="position: absolute; top: 0px; left: 0px; width: 84px; height: 36px; overflow: hidden; bottom: auto; right: auto;">
                      <label
                          style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                    </div>
                  </a>
                  <span>支持各类文档、图片、代码、压缩包格式</span>
                </el-upload>
              </div>
            </div>
          </div>
          <div class="work-message clearfix" id="mess1" style="background:#F1F3F4;">
            <span class="s1">作业留言：</span>
            <span class="s2">点击添加留言（仅老师可看）...</span>
            ::after
          </div>
        </div>
      </div>
      <div class="homework-share">
        <ul>

        </ul>
      </div>
      <div class="homework-log">
        <p class="logp1">
          <a class="togglesee">查看提交日志</a><i></i>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import {getAssignmentDetail} from "@/api/assignment";
import {editTask, getTaskDetail, submitTask} from "@/api/task";
import {getToken} from "@/utils/auth";

export default {
  name: "HomeworkHandUp",

  data() {
    return {
      id: this.$route.query.id,
      assignment: {},

      task: {
        taskId: '',
        asgnId: '',
        params: {
          files: []
        }
      },

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

    }
  },

  created() {
    this.getAssignment();
    this.getTask();
  },

  methods: {
    getAssignment() {
      getAssignmentDetail(this.id).then(res => {
        this.assignment = res.data
      })
    },

    getTask() {
      getTaskDetail(this.id).then(res => {
        if (res.data !== undefined) {
          this.task = res.data;
          this.task.params = {
            files: []
          };

          for (let i = 0; i < this.task.files.length; i++) {
            this.task.params.files.push(this.task.files[i].fileId)
          }

          console.log(this.task)
        }
      })
    },

    selectUpload() {
      let upload = document.getElementsByName('file').item(0);
      upload.click();
    },

    handleUploadSuccess(response, file, fileList) {
      this.task.params.files.push(response.data);
    },

    submitTask() {
      this.task.asgnId = this.id;

      if (this.task.taskId === undefined || this.task.taskId === '') {
        submitTask(this.task).then(res => {
          this.$message.success("提交成功！")
        }).catch(() => {
          this.$message.error("提交失败！")
        });
      } else {
        editTask(this.task).then(res => {
          this.$message.success("更新提交成功！")
        }).catch(() => {
          this.$message.error("更新提交失败！")
        });
      }
    },

    downloadTaskFile(row) {
      const url = 'http://localhost:8080/resources/download?id=' + row.fileId;
      const a = document.createElement('a');
      a.setAttribute('download', row.fileName);
      a.setAttribute('target', '_blank');
      a.setAttribute('href', url);
      a.click();
    },

    deleteTaskFile(row) {
      const li = document.getElementById(row.fileId);
      li.parentNode.removeChild(li);
      const index = this.task.params.files.indexOf(row.fileId);
      this.task.params.files.splice(index, 1);
    }
  }
}
</script>

<style scoped>
.mywork-page {
  padding-top: 40px;
  padding-bottom: 60px;
}

.mywork-page .company {
  border-radius: 8px;
  border: 1px solid rgba(218, 220, 224, 1);
  padding: 24px;
  margin-bottom: 24px;
  box-sizing: border-box;
}

.cWidth-new {
  width: 1224px;
  margin-left: auto;
  margin-right: auto;
}

.gWidth {
  /*width: 810px;*/
  margin-left: auto;
  margin-right: auto;
}

.mywork-page .recruitment-post {
  margin-bottom: 10px;
}

.mywork-page .recruitment-post .job-name {
  font-size: 18px;
}

.mywork-page .work-title .description {
  color: #707070;
  overflow: hidden;
  line-height: 1.8;
  font-size: 14px;
  margin-bottom: 30px;
  clear: both;
}

.mywork-page .work-title .description p {
  float: initial;
  background-color: #fff;
  margin: 0;
  padding: 0;
  color: #707070;
  line-height: 1.8;
}

.mywork-page .work-title p {
  font-size: 12px;
  padding: 0 5px;
  line-height: 20px;
  margin-right: 10px;
  margin-bottom: 40px;
  background-color: #F1F3F4;
  color: #5F6368;
  border-radius: 2px;
  float: left;
}

.mywork-page .work-title p span {
  margin-right: 8px;
}

.mywork-page .work-title .whohandup {
  display: inline-block;
  margin: 0;
  color: #5F6368;
  background: 0 0;
  cursor: pointer;
  font-size: 14px;
  float: right;
}

.mywork-page .sc-tj-box {
  height: 40px;
  line-height: 40px;
}

.mywork-page .homework-box {
  margin-top: 20px;
  border: 1px solid #E2E6ED;
  background: #FFF;
  border-radius: 8px;
}

.mywork-page .homework-share {
  margin-top: 30px;
}

.mywork-page .homework-log, .mywork-page .homework-log a, .mywork-page .homework-log a:hover {
  color: #32BAF0;
}

.mywork-page .sc-tj-box-new {
  width: 330px;
  height: 80px;
  border: 2px dashed #CCC;
  margin: 0 0 24px 24px;
}

.mywork-page .sc-tj-box-new .shangchuan span {
  color: #999;
  font-size: 12px;
  display: block;
  line-height: 12px;
}

.mywork-page .sc-tj-box .sc-tj .tj-btn {
  width: 102px;
  background: rgba(50, 186, 240, .5);
  color: #fff;
}

.mywork-page .sc-tj-box .sc-tj a {
  display: inline-block;
  font-size: 14px;
  text-align: center;
  border-radius: 3px;
}

.mywork-page .sc-tj-box-new .sc-tj i {
  width: 36px;
  height: 36px;
  line-height: 36px !important;
  background: rgba(50, 186, 240, 1);
  border-radius: 50%;
  display: block;
  text-align: center;
  font-size: 16px !important;
  color: #fff !important;
  margin: 22px 13px 0 30px;
  float: left;
}

.webuploader-container {
  position: relative;
}

.mywork-page .homework-box .work-message .s1 {
  font-size: 14px;
  width: 100px;
  padding-right: 35px;
  text-align: right;
  color: #010000;
  float: left;
}

.mywork-page .homework-box .work-message {
  border-top: 1px solid #dcdcdc;
  border-radius: 0 0 8px 8px;
  line-height: 30px;
  padding: 24px 0;
  font-size: 0;
  cursor: text;
}

.mywork-page .homework-box .work-message .s2 {
  float: left;
  font-size: 14px;
  color: #818181;
  width: 685px;
  word-break: break-all;
  word-wrap: break-word;
}

.mywork-page .sc-tj-box .sc-tj .sc-btn {
  font-size: 14px;
  margin-top: 20px;
  line-height: 26px;
  color: #1F2023;
}

.mywork-page .sc-tj-box .sc-tj a {
  display: inline-block;
  font-size: 14px;
  text-align: center;
  border-radius: 3px;
}

.webuploader-container {
  position: relative;
}

.webuploader-element-invisible {
  position: absolute !important;
  clip: rect(1px 1px 1px 1px);
  clip: rect(1px, 1px, 1px, 1px);
}

.mywork-page .homework-box .file {
  padding: 25px 25px 0;
  height: 64px;
}

.file .file-icon {
  margin-right: 20px;
}

.file .file-cont {
  margin-right: 30px;
}

.file .file-name {
  line-height: 30px;
  font-weight: normal;
  font-size: 14px;
  max-width: 600px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.file .file-name a, .file .file-name a:hover, .mywork-page .file .download {
  color: #707070;
}

.file .file-size {
  font-size: 14px;
  color: #ababab;
  margin-right: 14px;
}

.file .file-cont .opt {
  margin-top: 5px;
}

.file .file-icon, .file .file-icon img {
  width: 55px;
  height: 55px;
}

.file .cancel {
  width: 30px;
  height: 14px;
  margin-top: 0;
  color: #32BAF0;
  margin-left: 20px;
  float: right;
  right: 28px;
  border-radius: 3px;
}

.clearfix:after {
  content: "";
  display: block;
  height: 0;
  clear: both;
  visibility: hidden;
}

.fl {
  float: left;
}

.fr {
  float: right;
}

a {
  text-decoration: none;
  outline: none;
  blr: expression(this.onFocus=this.blur ());
  cursor: pointer;
}

</style>
