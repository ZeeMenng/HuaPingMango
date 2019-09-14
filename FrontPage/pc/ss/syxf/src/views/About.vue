<template>
  <div class="container">
    <div style="width:1280px;margin:40px auto;" class="clearfix">
      <div class="about-left fl">
        <ul class="box">
          <li class="lian" :class="{active : myIndex === $index}" v-for="(item, $index) in lists" :key="$index" @click="myIndex = $index">
            <span class="top-img" v-if="myIndex === $index">
              <img :src="item.url1" alt="">
            </span>
            <span class="top-img" v-else>
              <img :src="item.url" alt="">
            </span>
            {{item.name}}
          </li>
        </ul>
      </div>
      <div class="fr">
        <Introduce v-if="myIndex === 0" />
        <Contact v-else />
      </div>
    </div>
  </div>
</template>
<script>
  import Introduce from './contact'
  import Contact from './introduce'
  export default {
    data () {
      return {
        myIndex: 0,
        lists: [
          {name: '公司介绍', url: require('../assets/img/home.png'), url1: require('../assets/img/home1.png')},  
          {name: '联系我们', url: require('../assets/img/tell.png'), url1: require('../assets/img/tell1.png')}
        ]
      }
    },
    components: {
      Introduce,
      Contact
    }
  }
</script>

<style lang="scss" scoped>
.container{
  min-width: 1280px;
  min-height:calc(125vh - 180px);
  .about-left{
    float: left;
    background-color: #fff;
  }
}
.box{
    border-radius: 5px;
    width: 218px;
    height: 435px;
    border: 1px solid #f1f1f1;
    background-color: #f9f9f9;
    .lian{
      line-height: 45px;
      padding-left:65px;
      line-height: 50px;
      font-size: 14px;
      color: #5e5e5e;
      cursor: pointer;
    }
    .active{
      color: #fff;
      background-color: #49a9ee;
    }
}
.top-img{
  width: 25px;
  height: 25px;
  display: inline-block;
  position: relative;
  margin-right: 10px;
  img{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 6px;
  }
}
</style>