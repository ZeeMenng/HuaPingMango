<template>
  <div :class="backGround" class="header">
    <div class="clearfix" style="width:1280px;margin:0 auto;">
      <div class="logo fl">
        <h1>芒果质量安全追溯服务平台 </h1>
      </div>
      <Nav class="fl"/>
    </div>
  </div>
</template>

<script>
import Nav from '@/components/nav'
export default {
  name: 'Header',
  data () {
    return {
      backGround: 'index'
    }
  },
  components: {
    Nav
  },
  watch: {
    '$route': 'getPath'
  },
  methods: {
    getPath () {
      console.log(this.$route.path)
      switch (this.$route.path) {
        case '/search':
          this.backGround = 'search'
          break
        case '/':
          this.backGround = 'index'
          break
        case '/searchDetail':
          this.backGround = 'search'
          break
        default:
          this.backGround = 'about'
      }
    }
  },
  mounted () {
    let name = this.$route.path.substring(1)
    switch (this.$route.path) {
      case '/search':
        name = 'search'
        break
      case '/searchDetail':
        name = 'search'
        break
      case '/':
        name = 'index'
        break
      default:
        name = 'about'
    }
    this.backGround = name
  }
}
</script>

<style scoped lang="scss">
.index{
    position:absolute;
    z-index: 10;
}
.search{
    position:absolute;
    z-index: 10;
}
.about{
   background: url(../assets/img/bg.png) no-repeat;
   background-size: 100% 100%;
}
.header{
  height: 100px;
  width: 100%;
}
.nav{
  height: 50px;
  margin: 25px 0;
  background: transparent;
}
.logo{
    width: 382px;
    height: 100px;
    display: inline-block;
    padding-left: 82px;
    background: url(../assets/img/logo.png) no-repeat 0;
    background-size: 60px 60px;
    h1{
      position: relative;
      line-height: 100px;
      font-size: 20px;
      color: #fff;
      &:before {
        display: inline-block;
        content: "";
        width: 0;
        height: 30px;
        border: 1px solid #fff;
        border-radius: 100%;
        position: absolute;
        top: 35px;
        left: -10px;
    }
  }
}
</style>
