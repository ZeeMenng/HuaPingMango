<template>
  <div class="appHeader">
      <div class="appBack" @click="goBack">返回</div>
      <div>{{this.$route.name}}</div>
  </div>
</template>

<script>
export default{
    name: 'AppHeader',
    methods: {
        goBack () {
            this.$router.go(-1)
        }
    }
}
</script>

<style lang="scss" scoped>
.appHeader{
    z-index: 9999;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    font-size: 16px;
    font-weight: 400;
    line-height: 40px;
    color: #fff;
    background-color: #1c1b20;
    position: fixed;
    top: 0;
    width: 100%;
    .appBack{
        position: absolute;
        left: 10px;
    }
}
</style>