<template>
  <div>
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
  name: "StudentList"
}
</script>

<style scoped>

</style>
