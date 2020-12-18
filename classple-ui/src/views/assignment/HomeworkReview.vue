<template>
  <div class="no-name hide new-page gWidth isrecruit0" style="display: block">
    <div class="head-title">
      <div class="homework-title">
        <h2 style="float:left;">{{ assignment.asgnName }}</h2>
        <div class="generate-final-grade">生成期末考成绩</div>
      </div>
      <div class="togsh">
        <p class="fl">截至&nbsp;&nbsp;{{ assignment.deadline }}</p>
        <div class="titlefr">
          <div class="search-bar">
            <input id="search-txt" type="text" placeholder="学号，姓名">
            <a class="search-bar-a"></a>
          </div>
        </div>
        <div class="hs-opt fr">
          <span>对学生显示成绩</span>
        </div>
        <div class="checkrepeatsetting fr">查重设置</div>
      </div>
    </div>
    <div>
      <div class="reviewwrap">
        <el-table
            :row-key="name"
            :row-class-name="unpaidStudentClass"
            :data="taskList">
          <el-table-column
            type="selection"
            width="">
          </el-table-column>
          <el-table-column
              prop="schoolNo"
              label="学号"
              sortable
              width="120px">
          </el-table-column>
          <el-table-column
              prop="name"
              label="姓名"
              sortable
              width="">
          </el-table-column>
          <el-table-column
              prop="score"
              label="成绩"
              sortable
              width="100px">
            <template slot-scope="scope">
              <el-input
                  v-model="scope.row.score"
                  controls-position="right"
                  @change="handleReview(scope.row)"
                  size="mini"
                  :step="1" :min="0" :max="assignment.maxScore">
                <span slot="suffix">/ {{ assignment.maxScore }}</span>
              </el-input>
            </template>
          </el-table-column>
          <el-table-column
              prop="repeatRate"
              label="相识度"
              sortable
              width="100">
          </el-table-column>
          <el-table-column
              prop="createTime"
              label="提交状态"
              sortable
              width="160px">
          </el-table-column>
          <el-table-column
              prop="updatedCount"
              label="更新次数"
              sortable
              width="">
          </el-table-column>
          <el-table-column>
            <template slot-scope="scope">
              <a @click="downloadTaskFile(scope.row)">📎附件</a>
            </template>
          </el-table-column>
          <el-table-column>
            <template slot-scope="scope">
              <a @click="reviewTask(scope.row)">进入批阅</a>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>


  </div>
</template>

<script>
import {getAssignmentDetail} from "@/api/assignment";
import {checkTask, correctTask} from "@/api/task";
import {getUserListByAsgnId} from "@/api/user";
import {download} from "@/api/common";

export default {
  name: "HomeworkReview",

  data() {
    return {
      id: this.$route.query.id,
      assignment: {},
      taskList: {},
      userList: {},
      search: '',
    }
  },

  created() {
    this.getAssignment();
    this.getTaskList();
  },

  methods: {
    getAssignment() {
      getAssignmentDetail(this.id).then(res => {
        this.assignment = res.data;
      })
    },

    getTaskList() {
      correctTask(this.id).then(res => {
        this.taskList = res.data
        getUserListByAsgnId(this.id).then(res => {
          this.userList = res.data
          this.handleTaskList()
        })
      })
    },

    reviewTask(row) {

    },

    downloadTaskFile(row) {
      const a = []

      for (let i = 0; i < row.files.length; i++) {
        const url = 'http://localhost:8080/resources/download?id=' + row.files[i].fileId;
        a[i] = document.createElement('a');
        a[i].setAttribute('download', row.files[i].fileName);
        a[i].setAttribute('target', '_blank');
        a[i].setAttribute('href', url);
        a[i].click();
      }
    },

    handleTaskList() {
      for (let i = 0; i < this.taskList.length; i++) {
        let id = this.taskList[i].userId;
        let target = this.userList.find(item => item.userId === id);
        this.taskList[i].name = target.name;
        this.taskList[i].schoolNo = target.schoolNo;
      }

      console.log(this.taskList)
    },

    handleReview(row) {
      console.log(row)
      checkTask(row).then(res => {
        this.$message.success("成绩修改成功！")
      }).catch(err => {
        this.$message.error("成绩修改失败！")
      })
    },

    unpaidStudentClass(row, rowIndex) {

    }
  }

}
</script>

<style scoped>

.new-page {
  background: #FFF;
  border: 1px solid #c8c8c8;
  margin-top: 40px;
  margin-bottom: 60px;
  width: 1020px;
  font-size: 14px;
}

.head-title {
  padding: 39px 30px 27px;
  height: 80px;
  position: relative;
  border-bottom: 1px solid #c8c8c8;
}

.homework-title {
  display: inline-block;
  width: 100%;
}

.head-title h2 {
  font-size: 18px;
  color: #2d2d2d;
  font-weight: 400;
  display: inline-block;
}

.homework-title .generate-final-grade {
  height: 26px;
  line-height: 26px;
  display: block;
  text-align: center;
  background-color: rgb(255, 255, 255);
  color: rgb(129, 129, 129);
  font-family: 微软雅黑;
  cursor: pointer;
  user-select: none;
  float: right;
  border-radius: 3px;
  padding: 0px 10px;
  border-width: 1px;
  border-style: solid;
  border-color: rgb(204, 204, 204);
  border-image: initial;
}

.togsh {
  padding-top: 25px;
}

.head-title .togsh p {
  padding: 5px 20px;
  margin-right: 10px;
  background: rgba(0,0,0,.1);
}

.head-title .titlefr {
  float: right;
  margin-left: 14px;
}

.search-bar {
  border: 1px solid #c8c8c8;
  width: 150px;
  padding: 4px 10px 4px 20px;
  float: right;
}

.search-bar input {
  width: 130px;
  font-size: 14px;
  color: #a9a9a9;
  line-height: 20px;
  height: 20px;
}

.search-bar-a {
  background: url(../../assets/image/homesearch.png) -22px 0 no-repeat;
  width: 15px;
  height: 15px;
  vertical-align: middle;
}

.search-bar-a {
  display: inline-block;
}

.head-title .togsh .hs-opt {
  min-width: 138px;
  height: 30px;
  position: relative;
}

.head-title .togsh .hs-opt span {
  display: block;
  text-indent: 20px;
  height: 30px;
  line-height: 30px;
  color: #FFF;
  background: url(../../assets/image/settingicon.png) 95% -69px no-repeat #357ae8;
  cursor: pointer;
  font-size: 12px;
  padding-right: 35px;
}

.checkrepeatsetting.fr {
  height: 30px;
  line-height: 30px;
  margin-right: 15px;
  color: #999;
  background: url(../../assets/image/settingbk.png) left no-repeat;
  padding-left: 20px;
  cursor: pointer;
  user-select: none;
}

input, select, textarea {
  outline: none;
  border: none;
  background: none;
  cursor: text;
}

.fr {
  float: right;
}

.fl {
  float: left;
}

</style>
