<template>
  <div class="content" v-loading="fullscreenLoading">
    <div class="search-input">
      <div class="search-content">
        <el-input placeholder="溯源码" v-model="input5" class="input-with-select">
          <el-button slot="append" icon="el-icon-search" @click="searchBtn">溯源查询</el-button>
        </el-input>
      </div>
    </div>
    <h3 class="search-title">溯源查询方法</h3>
    <div class="s-wrap clearfix">
      <div class="code-img fl">
        <div :class="on ? 'card' : 'card flipped'" @click="on = !on">
          <figure class="front ">
            <div class="img-wrap">
              <img :src="ewmUrl">
            </div>
            <div class="word-wrap code-word">
              <p>溯源档案二维码</p>
              <p>手机扫一扫</p>
            </div>
            <span class="backbtn"></span>
          </figure>
          <figure class="back">
            <div class="img-wrap">
              <img :src="proUrl">
            </div>
            <div class="word-wrap">
              <h3>【{{brand}}】{{productName}}</h3>
              <p><span class="spant">溯源码：</span>{{tracingSource}}</p>
              <a class="buy" v-if="emallWebsite !== ''" :href="emallWebsite">点我购买</a>
            </div>
            <span class="backbtn"></span>
          </figure>
        </div>
      </div>
      <ul class="fl s-list">
        <li v-for="(item, $index) in cLists" :key="$index" :class="'s-list' + $index" @click = "handList($index)">
          <h2>{{item.name}}</h2>
        </li>
      </ul>
      <el-dialog title="种植档案" :visible.sync="cLists[0].dia" width="1200px">
        <Cropdialog :messageId = 'messageId' />
      </el-dialog>
      <el-dialog title="加工档案" :visible.sync="cLists[1].dia">
        <Procedialog :messageId = 'messageId'/>
      </el-dialog>
      <el-dialog title="企业信息" :visible.sync="cLists[2].dia">
        <Enterprisedialog :enterpriseId = 'enterpriseId' />
      </el-dialog>
      <el-dialog title="检验检测" :visible.sync="cLists[3].dia" width="1200px">
        <Inspectiodialog :indata = 'indata'/>
      </el-dialog>
      <el-dialog title="物联网检测信息" :visible.sync="cLists[4].dia">
        <Informationdialog :enterpriseId = 'enterpriseId'/>
      </el-dialog>
      <el-dialog title="物流流向" :visible.sync="cLists[5].dia" width="1200px">
        <Logisticdialog :tracingSource = 'tracingSource'/>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import Cropdialog from '@/components/cropDialog'
import Procedialog from '@/components/procesDialog'
import Informationdialog from '@/components/MoninformationDialog'
import Enterprisedialog from '@/components/enterpriseDialog'
import Inspectiodialog from '@/components/inspectionDialog'
import Logisticdialog from '@/components/logisticsDialog'
export default {
  components: {
    Cropdialog,
    Procedialog,
    Informationdialog,
    Enterprisedialog,
    Inspectiodialog,
    Logisticdialog
  },
  data () {
    return {
      fullscreenLoading: true,
      show: false,
      on: false,
      brand: '',
      productName: '',
      tracingSource: '',
      messageId: '',
      productType: '',
      enterpriseId: '',
      emallWebsite: '',
      indata: {
        planId: '',
        processId: '',
        productType: ''
      },
      input5: '',
      ewmUrl: '',
      proUrl: '',
      cLists: [
        {name: '种植档案', dia: false},
        {name: '加工档案', dia: false},
        {name: '企业信息', dia: false},
        {name: '检验检测', dia: false},
        {name: '物联网监测信息', dia: false},
        {name: '货物流向', dia: false}
      ]
    }
  },
  methods: {
    openVedio (url) {
      this.videoUrl = url
      this.vedioVisible = true
    },
    handList (i) {
        if (i === 0) {
          if (this.productType === '1') {
            this.cLists[i].dia = true
          } else {
            this.$alert('暂无内容', '提示', {
              confirmButtonText: '确定'
            })
          }
        } else if (i === 1) {
          if (this.productType === '2') {
            this.cLists[i].dia = true
          } else {
            this.$alert('暂无内容', '提示', {
              confirmButtonText: '确定'
            })
          }
        } else {
          this.cLists[i].dia = true
        }
    },
    searchBtn () {
      let dest = /^HP[0-9A-Z]{4}[12]\d{3}[\d]{10}$/
      if (dest.test(this.input5)) {
        this.$router.push({path: '/searchDetail', query: {traceabilityCode: this.input5}})
        this.getData()
        this.fullscreenLoading = true
      } else {
        this.$alert('请输入正确的溯源码', '提示', {
          confirmButtonText: '确定'
        })
      }
    },
    getData () {
      var _this = this
      axios.post('/sy/traceInfoByTraceabilityCode/getTrCodeBaseInfo', {
          'traceabilityCode': this.input5,
          'source': 'pc'
      })
      .then(function (res) {
        if(res.data.isSuccess){
          let data = res.data.data
          _this.ewmUrl = data.qRCode
          _this.proUrl = data.productPicture
          _this.brand = data.brand
          _this.productName = data.productName
          _this.tracingSource = data.tracingSource
          _this.messageId = data.messageId
          _this.productType = data.productType
          _this.enterpriseId = data.enterpriseId
          _this.indata.processId = data.processId
          _this.indata.planId = data.planId
          _this.indata.productType = data.productType
          _this.emallWebsite = data.emallWebsite
          _this.fullscreenLoading = false
        }else{
            _this.$message.error('该溯源码无数据')
            _this.fullscreenLoading = false
        }
      })
      .catch(function (error) {
          console.log(error);
      });
    }
  },
  created () {
    this.input5 = this.$route.query.traceabilityCode
    this.getData()
  }
}
</script>
<style lang="scss" scoped>
.content{
  min-width: 1280px;
  .search-input{
    height: 200px;
    padding-top: 150px;
    text-align: center;
    background: url('../assets/img/bg.png') no-repeat;
    background-size: cover;
    background-position: center;
    .search-content{
        margin-top: 30px;
        width: 531px;
        height: 40px;
        display: inline-block;
        
    }
  }
  .search-title{
    font-size: 16px;
    line-height: 54px;
    font-weight: 600;
    color: rgb(16, 142, 233);
    text-align: left;
    padding-left: 25px;
    background: url('../assets/img/titlt.png') 0px center no-repeat;
    background-position: 0px center;
    width: 1175px;
    margin: 0 auto;
  }
  .s-wrap{
    width: 1202px;
    margin: 0 auto;
  }
  .code-img {
    width: 448px;
    height: 412px;
    display: inline-block;
    float: left;
    border: 1px solid #e2e2e2;
    background-color: #fff;
    margin-right: 10px;
    border-radius: 5px;
    position: relative;
    perspective: 800px;
    img{
      width: 163px;
      height: 163px;
    }
    .card {
      width: 100%;
      height: 100%;
      position: absolute;
      transform-style: preserve-3d;
      transition: transform 1s;
        figure {
          display: block;
          position: absolute;
          width: 100%;
          height: 100%;
          backface-visibility: visible;
          background-color: #f0f0f0;
          border-radius: 5px;
          .img-wrap{
            width: 218px;
            height: 218px;
            border-radius: 5px;
            border:2px solid #53bc72;
            margin:47px auto 0;
            margin-bottom: 30px;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            justify-content: center;
          }
          .word-wrap{
            h3{
              font-size: 18px;
              color: #474747;
              font-weight: normal;
              margin-bottom: 10px;
              text-align: center;
            }
            p{
              font-size: 14px;
              color: #474747;
              line-height: 2.2;
              width: 230px;
              overflow: hidden;
              text-align: left;
              margin: 0 auto;
              .spant{
                float: left;
                display: inline-block;
                width: 60px;
                text-align: right;
              }
            }
          }
          .code-word{
            p{
              text-align: center;
            }
          }
        }
        figure .backbtn{
          display: inline-block;
          width: 70px;
          height: 78px;
          background: url('../assets/img/9.png') no-repeat;
          position: absolute;
          right: 0;
          top:0; 
        }
        .front {
          backface-visibility: hidden;
        }
        .back {
          transform: rotateY( 180deg);
          backface-visibility: hidden;
          .backbtn{
            background: url('../assets/img/2.png') no-repeat;
          }
        }
      }
      .card.flipped {
        transform: rotateY( 180deg);
      }
  }
}
.buy{
  color: red;
  display: inline-block;
  margin-left: 195px;
}
.s-list{
  width: 741px;
  li{
    display: inline-block;
    height: 200px;
    float: left;
    width: 237px;
    border: 1px solid #e2e2e2;
    border-radius: 5px;
    box-sizing: border-box;
    background-color: #f0f0f0;
    margin: 0 10px 10px 0;
    cursor: pointer;
    position: relative;
    counter-increment: my;
    h2{
      border-top-right-radius: 5px;
      border-top-left-radius: 5px;
      background-color: #e7e7e7;
      height: 40px;
      font-size: 20px;
      font-weight: 400;
      text-align: center;
      color: #4f4f4f;
      line-height: 40px;
      background: linear-gradient(90deg,#f0f0f0,#e7e7e7 50%,#f0f0f0);
    }
  }
  li::before {
    background: url('../assets/img/tanhao.png');
    content: "";
    display: inline-block;
    width: 13px;
    height: 13px;
    background-size: cover;
    top: 10px;
    right: 10px;
    position: absolute;
  }
  li::after {
    content: counter(my);
    color: #cfcfcf;
    position: absolute;
    bottom: 10px;
    right: 10px;
    font-size: 22px;
    font-weight: 700;
  }
  .s-list0 {
      background: url('../assets/img/icon_03.png') no-repeat 20px 70px #f0f0f0;
  }
  .s-list1 {
      background: url('../assets/img/icon_03.png') no-repeat -230px 70px #f0f0f0;
  }
  .s-list2 {
      background: url('../assets/img/icon_03.png') no-repeat -475px 70px #f0f0f0;
  }
  .s-list3 {
      background: url('../assets/img/icon_03.png') no-repeat 25px -145px #f0f0f0;
  }
  .s-list4 {
      background: url('../assets/img/icon_03.png') no-repeat -230px -145px #f0f0f0;
  }
  .s-list5 {
      background: url('../assets/img/icon_03.png') no-repeat -475px -145px #f0f0f0;
  }
}
</style>