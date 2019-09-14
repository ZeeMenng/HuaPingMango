<template>
  <div class="container home">
    <el-carousel :interval="5000" height="500px" arrow="always">
      <el-carousel-item v-for="banner in banners" :key="banner.url">
        <img :src="banner.url" alt="">
      </el-carousel-item>
    </el-carousel>
    <div class="statistics-bar">
      <ul class="clearfix">
        <li v-for="(statistic,index) in statistics" :key="index" :style="{ 'background-image': 'url(' + statistic.url + ')','background-repeat':'no-repeat','background-size':'cover' }">
          <span>{{statistic.text}}<var>{{statistic.num}}</var>{{statistic.unit}}</span>
        </li>
      </ul>
    </div>
    <div class="tab-wrap">
      <ul class="tab-bar clearfix">
        <li v-for="(item,index) in tabs" :key="index" @click="activeTab(index)" :class="index == tabIndex ? 'active':''">{{item}}</li>
      </ul>
      <div class="content">
        <ul class="tab-content" v-for="(Content,index) in Contents" :key="index" v-if="index == tabIndex">
          <li v-for="(item,i) in Content.mockData" :key="i" :class="{bln_:i==Content.mockData.length-1}" class="clearfix" >
            <div :class="i%2 == 0?'fl':'fr'" class="txt-wrap">
              <h3>{{item.title}}</h3>
              <p>{{item.text}}</p>
            </div>
            <div :class="i%2 == 0?'fl':'fr'" class="img-wrap">
              <img :src="item.url" alt="">
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
import {Content} from "../assets/mock/home.js"
export default {
  data() {
    return {
      tabIndex:0,
      Contents:Content,
      tabs:[
        '消费者',
        '企业',
        '政府'
      ],
      banners:[
          {url:require('../assets/img/banner1_01.jpg')},
          {url:require('../assets/img/banner2_01.jpg')},
          {url:require('../assets/img/banner3_01.jpg')},
          {url:require('../assets/img/banner4_01.jpg')},
          {url:require('../assets/img/banner5_01.jpg')}
      ],
      statistics:[
          {text:'企业数量',num:100,unit:'家',url:require('../assets/img/staticstics_1.png')},
          {text:'芒果产量',num:10,unit:'万吨',url:require('../assets/img/staticstics_2.png')},
          {text:'对接品种',num:12,unit:'种',url:require('../assets/img/staticstics_3.png')},
          {text:'种植面积',num:20,unit:'万亩',url:require('../assets/img/staticstics_4.png')},
          {text:'对接产值',num:10,unit:'亿元',url:require('../assets/img/staticstics_5.png')},
      ],
    };
  },
  methods: {
    activeTab(index){
      this.tabIndex =index;
    }
  },
}
</script>

<style lang="scss">
.container{
  min-width: 1280px;
  min-height:calc(100vh - 80px);
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}
  
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.el-carousel__item img{
  width: 100%;
  height: 100%;
}
.home .el-carousel__indicators{
  bottom: 20px;
}
li .el-carousel__button {
  width: 60px;
  height: 3px;
}
.el-carousel__container .el-carousel__arrow--left{
  left: 100px;
  width: 50px;
  height: 50px;
  i{
    font-size: 25px;
  }
}
.el-carousel__container .el-carousel__arrow--right{
  right: 100px;
  width: 50px;
  height: 50px;
  i{
    font-size: 25px;
  }
}
.statistics-bar{
  width: 100%;
  background: #efefef;
  ul{
    width: 1280px;
    margin: 0 auto;
    li{
      width: 256px;
      height: 130px;
      float: left;
      position: relative;
    }
    span{
      display: inline-block;
      position: absolute;
      bottom: 3px;
      width: 100%;
      text-align: center;
    }
    var{
      font-size: 30px;
      color: #108ee9;
      position: relative;
      padding: 0 3px;
      top: 3px;
    }
  }
}
.tab-wrap{
  width: 960px;
  margin: 30px auto;
}
.tab-bar{
  position: relative;
  height: 32px;
  border-radius: 15px;
  border: 1px solid #ddd;
  margin-bottom: 30px;
  text-align: center;
  li{
    width: 162px;
    display: inline-block;
    box-sizing: border-box;
    line-height: 32px;
    cursor: pointer;
    font-size: 18px;
    color: #303030;
    &:first-child{
      position: absolute;
      left: -2px;
    }
    &:last-child{
      position: absolute;
      right: -2px;
    }
    &.active{
      animation: tabAnimation 1s linear;
      background: url(../assets/img/tabicon.png) no-repeat 50%;
      color: #fff;
    }
  }
}
@keyframes tabAnimation{
  from{transform: rotateY(180deg);}
  to{transform: rotateY(0deg);}
}
.tab-content{
  box-sizing: border-box;
  margin: 10px;
  li{
    border-bottom: 1px dashed #ccc;
    &>div{
      width: 50%;
    }
  }
}
.txt-wrap {
    padding-top: 70px;
    width: 470px;
    height: 100%;
    box-sizing: border-box;
    h3{
      font-size: 24px;
      color: #303030;
      margin-bottom: 10px;
      font-weight: normal;
    }
    p{
      font-size: 14px;
      color: #474747;
      line-height: 24px;
    }
}
.img-wrap {
    width: 470px;
    height: 280px;
    padding: 20px 0;
    text-align: center;
    img{
      max-width: 100%;
      max-height: 100%;
    }
}
</style>
