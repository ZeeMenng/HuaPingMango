<template>
<div>
  <div v-loading="fullscreenLoading">
  <AppHeader />
  <div v-if="!contenShow">
    <div class="headerBank"></div>
    <ul class="logList">
      <div class="logLine"></div>
      <li class="clearfix">
        <div class="logCricle fl"></div>
        <div class="fl loglistCon">
          <div class="log">{{deliverTime}}<span class="str">(发货日期)</span></div>
          <div class="sales"><span class="tell">销售</span>所在位置：{{receiverAddress}}</div>
        </div>
      </li>
      <li class="clearfix" v-if="logisticsName !== ''&& logisticsNo !== ''">
        <div class="logCricle fl"></div>
        <div class="fl loglistCon">
          <div class="log">{{deliverTime}}<span class="str">(物流日期)</span></div>
          <div class="sales"><span class="tell">物流</span>
          <p>物流商：{{logisticsName}}</p>
          <p>物流单号：{{logisticsNo}}</p>
          </div>
        </div>
      </li>
      <li class="clearfix">
        <div class="logCricle fl"></div>
        <div class="fl loglistCon">
          <div class="log">{{deliverTime}}<span class="str">(生产日期)</span></div>
          <div class="sales"><span class="tell">生产/加工</span>所在位置：{{entAddress}}</div>
        </div>
      </li>
    </ul>
  </div>
  <div v-if="contenShow"><img class="top-img" src="../assets/img/xu.png" title="暂无数据"/><br><p class="shu">暂无数据</p></div>
  </div>
</div>
</template>
<script>
import AppHeader from '@/components/Appheader'
export default{
  components: {
    AppHeader
  },
  data () {
    return {
      fullscreenLoading:true,
      deliverTime:'',
      contenShow: false,
      receiverAddress: '',
      logisticsName:'',
      logisticsNo:'',
      entAddress:'',
    }
  },
    created () {
    this.traceabilityCode = this.$route.query.paicheNo
    this.getData()                                                                         
  },
  methods: {
    getData () {
      var _this = this
      axios.post('/sy/traceInfoByTraceabilityCode/getLogisticsInfo', {
        traceabilityCode:this.traceabilityCode
      })
      .then(function (res) {
          console.log(res)
          let data = res.data.data
          if (res.data.isSuccess) {
            console.log(res);
              _this.deliverTime=data.deliverTime
              _this.receiverAddress = data.receiverAddress
              _this.logisticsName=data.logisticsName
              _this.logisticsNo=data.logisticsNo
              _this.entAddress=data.entAddress
              _this.fullscreenLoading = false
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
.headerBank{
  height: 40px;
}
.logList{
  position: relative;
  margin: 20px;
  li{
    margin-bottom: 15px;
  }
  .tell{
    display: block;
    color: #49a9ee;
    font-weight: 600;
  }
  .logLine{
    z-index: -1;
    width: 2px;
    height: 100%;
    position: absolute;
    left: 21px;
    background: greenyellow;
  }
  .loglistCon{
    width: calc(100vw - 150px);
  }
  .logCricle{
    width: 20px;
    height: 20px;
    border-radius: 50%;
    border: 1px solid greenyellow;
    margin: 0 10px;
    background-color: greenyellow;
    box-shadow: 5px 5px 5px #888888;
    margin-top: 20px;
  }
}
.str{
  color: #333;
  text-align: center;
  line-height: 24px;
  border-radius: 5px;
  width: 100px;
  display: inline-block;
}
.log{
  padding-top: 10px;
  font-size: 18px;
}
.sales{
  padding-top: 10px;
  background: #f9f9f9;
  //background: url(../assets/img/timeT.png);
  padding-left: 10px;
  border-radius: 5px;
  padding-bottom: 10px;
}
.top{
      position: absolute;
      top: 12px;
      left: 7px;
      width: 50px;
      height: 50px;
      .bin{
        position: relative;
        top:100px;
      }
      .bin1{
        position: relative;
        top:200px;
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