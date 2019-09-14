<template>
  <div class="nav">
    <el-menu :default-active="$route.path" class="ulMenu el-menu-demo" mode="horizontal" @open="handleopen"
        @close="handleclose"
        @select="handleselect" unique-opened router >
      <template v-for="(item,index) in $router.options.routes" v-if="!item.hidden">
        <el-menu-item :index="item.path" :key="index">{{item.name}}</el-menu-item >
      </template>
    </el-menu>
  </div>
</template>

<script>

export default {
  name: 'Nav',
  methods: {
    handleopen () {
    // console.log('handleopen');
    },
    handleclose () {
    // console.log('handleclose');
    },
    handleselect: function (a, b) {
    }
  }
}
</script>

<style scoped lang="scss">
.ulMenu{
  border: none;
  background:transparent;
}
.ulMenu li{
  color: #fff;
  font-size: 16px
}
.ulMenu .el-menu-item:focus, .ulMenu .el-menu-item:hover{
  background: transparent;
}
.el-menu--horizontal>.el-menu-item.is-active,.el-menu--horizontal>.el-menu-item:not(.is-disabled):focus, .el-menu--horizontal>.el-menu-item:not(.is-disabled):hover{
  background: transparent;
  color: #fff;
}
.el-menu--horizontal > .el-menu-item.is-active[data-v-0276edc3], .el-menu--horizontal > .el-menu-item[data-v-0276edc3]:not(.is-disabled):focus, .el-menu--horizontal > .el-menu-item[data-v-0276edc3]:not(.is-disabled):hover{
  border-bottom: 2px solid #ffe00d;
}
</style>
