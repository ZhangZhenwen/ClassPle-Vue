<template>
  <div>
    <div class="title">
      <div class="all-title clearfix">
        <h3 class="fl" style="width: auto;">教学团队（老师<span>{{ tchCount }}</span>）</h3>
        <a class="fr invite-tea" style="display: block;" v-if="teacher">
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
  </div>
</template>

<script>
export default {
  name: "TeacherList"
}
</script>

<style scoped>

</style>
