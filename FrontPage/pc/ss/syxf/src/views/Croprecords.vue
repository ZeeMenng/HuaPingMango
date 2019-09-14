<template>
<div>
  <div v-loading="fullscreenLoading">
  <AppHeader />
  <div v-if="!contenShow">
    <div class="headerBank"></div>
    <div class="appWrap">
      <ul class="wer">
      <li class="er clearfix"><span class="fl">种植基地：</span><label class="fl">{{plantEntName}}</label></li>
      <li class="er clearfix"><span class="fl">基地地址：</span><label class="fl">{{plantAddress}}</label></li>
      <li class="er clearfix"><span class="fl">地块：</span><label class="fl">{{detailName}}</label></li>
      <li class="er clearfix"><span class="fl">产品名称：</span><label class="fl">{{productName}}</label></li>
      <!--<li class="er clearfix"><span class="fl">种植时间：</span><label class="fl">{{plantTime}}</label></li>-->
      <li class="er clearfix"><span class="fl">采收时间：</span><label class="fl">{{harvestTime}}</label></li>
      <li class="er clearfix"><span class="fl">采收批次：</span><label class="fl">{{recoveryBatch}}</label></li>
      </ul>
      <p class="pl">农事操作:</p>
      <el-table :data="gridData" border>
          <el-table-column prop="operationName" label="农事操作"></el-table-column>
          <el-table-column prop="inputsName" label="使用品牌"></el-table-column>
          <el-table-column prop="useAmount" label="使用量"></el-table-column>
          <el-table-column prop="spec" label="规格"></el-table-column>
          <el-table-column prop="operationDate" label="操作时间"></el-table-column>
          <el-table-column property="imgUrl" label="图片">
              <template slot-scope="scope">
                  <div style="width: 50px;height: 50px;" v-if="scope.row.imgUrl">
                      <img style="width: 100%;" :src="scope.row.imgUrl" alt="" @click="openImg(scope.row.imgUrl)">
                  </div>
              </template>
          </el-table-column>
          <el-table-column property="videoUrl" label="视频">
              <template slot-scope="scope">
                  <div v-if="scope.row.videoUrl" @click="openVedio(scope.row.videoUrl)">
                      查看视频
                  </div>
              </template>
          </el-table-column>
      </el-table>
      <!--<ul class="wer">
        <li class="er clearfix"><span class="fl">产地认定证书：</span><label class="fl">
          <img :src="item" alt="" v-for="(item, $index) in productPath" :key="$index"  @click="openImg(item)">
        </label>
        </li>
        <li class="er clearfix"><span class="fl">产品认证证书：</span><label class="fl">
          <img :src="item" alt="" v-for="(item, $index) in processPath" :key="$index"  @click="openImg(item)">
        </label>
        </li>
      </ul>-->
    </div>
    <el-dialog
        width="90%"
        title="查看视频"
        :visible.sync="vedioVisible"
        append-to-body>
        <Player :video-url="videoUrl" :state="state" />
    </el-dialog>
    <div v-show="mask" class="maskWrap" @click="closePic">
      <img :src="bigImg" alt="">
    </div>
  </div>
  <div v-if="contenShow"><img class="top-img" src="../assets/img/xu.png" title="暂无数据"/><br><p class="shu">暂无数据</p></div>
  </div>
</div>
</template>
<script>
import Player from '@/components/play'
import AppHeader from '@/components/Appheader'
export default{
  components: {
    AppHeader,
    Player
  },
  data () {
    return {
      state: true,
      vedioVisible: false,
      videoUrl: '',
      fullscreenLoading:true,
      bigImg: '',
      mask: false,
      contenShow: false,
      messageId: '',
      plantEntName:'',
      plantAddress:'',
      detailName:'',
      productName:'',
      plantTime:'',
      harvestTime:'',
      recoveryBatch:'',
      productPath: [],
      processPath: [],
      gridData: [
        {operationName: '', inputsName: '', useAmount: '', spec: '',operationDate:''}
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
    openVedio (url) {
        this.videoUrl = url
        this.vedioVisible = true
    },
    openImg (url) {
      this.mask = true
      this.bigImg = url
    },
    getData () {
      var _this = this
      axios.post('/sy/traceInfoByTraceabilityCode/getPlantingInfo', {
        messageId:this.messageId
      })
      .then(function (res) {
          console.log(res)
          let data = res.data.data
          if (res.data.isSuccess) {
            console.log(res);
              _this.plantEntName = data.plantEntName
              _this.plantAddress = data.plantAddress
              _this.detailName = data.detailName
              _this.productName=data.productName
              _this.plantTime=data.plantTime
              _this.harvestTime=data.harvestTime
              _this.recoveryBatch=data.recoveryBatch
              _this.gridData = data.dataList
              _this.fullscreenLoading = false
              if (data.productionCertificatePaths) {
                _this.productPath = data.productionCertificatePaths.split(',')
              }
              if (data.processCertificatePaths) {
                _this.processPath = data.processCertificatePaths.split(',')
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