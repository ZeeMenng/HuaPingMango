<template>
  <div>
    <AppHeader />
    <div v-if="!contenShow">
      <div class="headerBank"></div>
      <div class="appWrap">
        <ul class="wer">
          <li class="er clearfix"><span class="fl">加工产品：</span><label class="fl">{{productName}}</label></li>
          <li class="er clearfix"><span class="fl">加工批次：</span><label class="fl">{{processBatch}}</label></li>
          <li class="er clearfix"><span class="fl">加工产量：</span><label class="fl">{{amount}}</label></li>
          <li class="er clearfix"><span class="fl">生产日期：</span><label class="fl">{{processDate}}</label></li>
          <li class="er clearfix"><span class="fl">保质期：</span><label class="fl">{{guaranteeDate}}</label></li>
          <li class="er clearfix"><span class="fl">包装日期：</span><label class="fl">{{packDate}}</label></li>
        </ul>
        <p class="pl">加工原料:</p>
        <el-table :data="gridData" border>
            <el-table-column property="processProductName" label="品种"></el-table-column>
            <el-table-column property="consumption" label="消耗量"></el-table-column>
        </el-table>
        <ul class="wer">
          <li class="er" v-if="proImgList.length > 0"><span>产品认证证书：</span>
              <p>
                <img :src="item" alt="" v-for="(item, $index) in proImgList" :key="$index" @click="openImg(item)">
              </p>
          </li>
          <li class="er" v-if="processImgPaths > 0"><span>加工图片：</span>
              <p>
                <img :src="item" alt="" v-for="(item, $index) in processImgPaths" :key="$index" @click="openImg(item)">
              </p>
          </li>
          <li class="er" v-if="videoUrl"><span>加工视频：</span>
              <p>
                <Player :video-url="videoUrl" :state="state" />
              </p>
          </li>
        </ul>
      </div>
      <div v-show="mask" class="maskWrap" @click="closePic">
        <img :src="bigImg" alt="">
      </div>
    </div>
    <div v-if="contenShow"><img class="top-img" src="../assets/img/xu.png" title="暂无数据"/><br><p class="shu">暂无数据</p></div>
  </div>
</template>
<script>
 import AppHeader from '@/components/Appheader'
 import Player from '@/components/play'
 export default{
   components: {
    AppHeader,
    Player
  },
  data(){
    return{
      bigImg: '',
      mask: false,
      contenShow: false,
      videoUrl: '',
      state: true,
      productName: '',
      processBatch: '',
      amount: '',
      processDate: '',
      guaranteeDate: '',
      packDate: '',
      proImgList: [],
      processImgPaths: [],
      gridData: [
        {processProductName: '', consumption: '',}
      ]
    }
  },
   created () {
    this.messageId = this.$route.query.paicheNo
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
      axios.post('/sy/traceInfoByTraceabilityCode/getProcessInfo', {
        messageId:this.messageId
      })
      .then(function (res) {
          let data = res.data.data
          if (res.data.isSuccess) {
            console.log(res);
              _this.productName = data.productName
              _this.processBatch = data.processBatch
              _this.amount = data.amount
              _this.processDate = data.processDate
              _this.guaranteeDate = data.guaranteeDate
              _this.packDate = data.packDate
              _this.gridData = data.dataList
              if (data.processCertificatePaths) {
                _this.proImgList = data.processCertificatePaths.split(',')
              }
              if (data.processImgPaths) {
                _this.processImgPaths = data.processImgPaths.split(',')
              }
              _this.videoUrl = data.processVideoPaths
          }else{
             _this.contenShow = true
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
    p{
      width: 100%;
    }
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
.top-img{
  width:282px;
  height: 282px;
  margin-top: 80px;
  margin-left: 60px;
}
.shu{
  margin-left: 160px;
  margin-bottom: 40px;
  position: relative;
  top: -50px;
  color: #49a9ee;
}
</style>