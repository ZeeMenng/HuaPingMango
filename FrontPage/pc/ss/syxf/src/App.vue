<template>
  <div id="app">
    <div v-if="flag">
      <Header />
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
      <Footer />
      <div class="right-codePic">
        <div class="ios">
          <span class="icon-span"> </span>
          <div class="ios-pos">
            <h2>IOS</h2>
            <img src="./assets/img/mango_ios.png" alt="">
            <p>扫一扫，下载app</p>
          </div>
        </div>
        <div class="android">
          <span class="icon-span"> </span>
          <div class="android-pos">
            <h2>Android</h2>
            <img :src="android" alt="">
            <p>扫一扫，下载app</p>
          </div>
        </div>
      </div>
    </div>
    <div style="height: 100%" v-else>
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </div>
  </div>
</template>

<script>
import Header from '@/components/header'
import Footer from '@/components/footer'
import Pcdetail from '@/components/pcDetail'
import Appdetail from '@/components/appDetail'
export default {
  data () {
    return {
      flag: false,
      android:'',
    }
  },
  components: {
    Header,
    Footer,
    Pcdetail,
    Appdetail
  },
  methods: {
    IsPC () {  
        var userAgentInfo = navigator.userAgent;
        var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");  
        var flag = true;  
        for (var v = 0; v < Agents.length; v++) {  
            if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }  
        }
        this.flag = flag
    },
    //  获取安卓二维码
    getAndroid(){
      axios.get('sy/versionManagement/getVersion', {
          params:{
            "type":"android"
          }
        })
        .then(function (response) {
          return response.data
        })
        .catch(function (error) {
          console.log(error);
        }).then(res=>{
            // console.log(res)
            this.android = res.qRCode
        });
    }
  },
  created () {
      this.IsPC();
      this.getAndroid();
  }
}
</script>

<style scoped lang="scss">
  .right-codePic{
    position: fixed;
    right: 0;
    bottom: 35vh;
    &>div{
      background-color: #909090;
      margin-bottom: 10px;
      border-radius: 5px;
      padding: 3px;
      position: relative;
      &>div{
        border-radius: 5px;
        border: 1px solid #19b498;
        width: 150px;
        height: 192px;
        position: absolute;
        left: -170px;
        top: 0;
        opacity: 0;
        transition: all .3s;
        z-index: -1;
        background: rgba(255,255,255,.9);
        padding: 3px;
        transform: scale3d(0,0,0);
        transform-origin: top right 0;
        text-align: center;
        img{
          width: 120px;
          height: 120px;
          margin: 2px 0;
        }
        h2,p{
          line-height: 30px;
          text-align: center;
          color:rgba(0,0,0,.6);
        }
        &:after{
          width: 10px;
          height: 10px;
          border: 1px solid #19b498;
          content: "";
          display: inline-block;
          position: absolute;
          right: -6px;
          top: 10px;
          transform: rotate(45deg);
          background-color: #fff;
          border-width: 1px 1px 0 0;
        }
      }
      &:hover{
        &>div{
          transform: scaleX(1);
          opacity: 1;
        }
      }
    }
    .icon-span{
      display: block;
      width: 28px;
      height: 28px;
      cursor: pointer;
    }
    .ios{
      span{
        background: url('./assets/img/IOS.png') no-repeat;
        background-size: 100% 100%;
      }
    }
    .android{
      span{
        background: url('./assets/img/android.png') no-repeat;
        background-size: 100% 100%;
      }
    }
  }
</style>
