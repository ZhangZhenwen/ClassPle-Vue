<template>
  <div class="big" :style="backgroundDiv">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "Register"
}
</script>

<style scoped>
.big{

  height: 100%;
  width:100%;
  margin-top: -60px;;
}
</style>
