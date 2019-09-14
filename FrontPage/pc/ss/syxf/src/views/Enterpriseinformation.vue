<template>
  <div>
    <div v-loading="fullscreenLoading">
    <AppHeader />
    <div v-if="!contenShow">
      <div class="headerBank"></div>
      <div class="appWrap">
        <ul class="wer">
          <li class="er clearfix"><span class="fl">企业名称：</span><label class="fl">{{enterpriseName}}</label></li>
          <li class="er clearfix"><span class="fl">企业地址：</span><label class="fl">{{enterpriseAddress}}</label></li>
          <li class="er clearfix"><span class="fl">联系人：</span><label class="fl">{{contactPerson}}</label></li>
          <li class="er clearfix"><span class="fl">联系电话：</span><label class="fl">{{telePhone}}</label></li>
          <!--<li class="er clearfix"><span class="fl">电商网址：</span><label class="fl">{{emallWebsite}}</label></li>-->
          <li class="er clearfix"><span class="fl">企业介绍：</span><label class="fl">{{description}}</label></li>
          <li class="er clearfix"><span class="fl">企业资质：</span>
          <label class="fl">
            <div v-for="(item, $index) in zizhi" :key="$index">
                <img :src="item" alt="" @click="openImg(item)" style="width:100px;height:100px;"/>
            </div>
          </label></li>
        </ul>
      </div>
      <div v-show="mask" class="maskWrap" @click="closePic">
        <img :src="bigImg" alt="">
      </div>
    </div>
    <div class="top_img" v-if="contenShow">
      <img src="../assets/img/xu.png" title="暂无数据"/>
     <p class="shus">暂无数据</p>
    </div>
    </div>
  </div>
</template>
<script>
import AppHeader from '@/components/Appheader'
 export default{
   components: {
    AppHeader
  },
  data(){
    return{
      fullscreenLoading:true,
      bigImg: '',
      mask: false,
      contenShow: false,
      enterpriseName:'',
      enterpriseAddress:'',
      contactPerson:'',
      telePhone:'',
      emallWebsite:'',
      description:'',
      zizhi: [],
    }
  },
  created () {
    this.enterpriseId = this.$route.query.paicheNo
    this.getData()                                                                     
  },
  methods: {
    closePic () {
      this.mask = false
    },
    openImg (url) {
      this.mask = true
      this.bigImg = url
    },
    getData () {
      var _this = this
      axios.post('/sy/traceInfoByTraceabilityCode/getEnterpriseInfo', {
        'enterpriseId':this.enterpriseId
      })
      .then(function (re) {
          console.log(re)
          let data = re.data.data
          if (re.data.isSuccess) {
              _this.enterpriseName = data.enterpriseName
              _this.enterpriseAddress = data.enterpriseAddress
              _this.contactPerson = data.contactPerson
              _this.telePhone = data.telePhone
              _this.emallWebsite = data.emallWebsite
              _this.description = data.description
              _this.fullscreenLoading = false
              if (data.fileResourcePath) {
                _this.zizhi = data.fileResourcePath.split(',')
              }
          }else{
              _this.contenShow = true
              _this.fullscreenLoading = false
          }
      })
      .catch(function (error) {
          console.log(error);
      })
    }
  }
 }
</script>
<style lang="scss" scoped>
.maskWrap{
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 999;
  display: flex;
  justify-content: center;
  align-items: center;
  img{
    max-width: 100%;
    max-height: 100%;
  }
}
.headerBank{
  height: 40px;
}
.appWrap{
  height: 100%;
  margin: 16px;
  h3{
    height: 40px;
    line-height: 40px;
    color: #333;
    font-size: 14px;
    text-indent: 10px;
  }
  .wer{
   margin-top: 15px;
   color: #333;
  }
  .er{
    margin-top: 5px;
    color: #555;
    span{
      font-weight: bold;
      width: 100px;
      display: inline-block;
      color: #333;
    }
    label{
      width: calc(100vw - 136px);
      display: inline-block;
    }
  }
  .pl{
    padding-top: 15px;
    font-weight: bold;
  }
}
.bigImg{
    width: 100%
    img{
        width: 100%;
    }
}
.top_img{
  width:282px;
  margin: 0 auto;
  img{
    width: 100%;
  }
}
.shus{
  color: #49a9ee;
  text-align: center;
  font-size: 14px;
}
</style>