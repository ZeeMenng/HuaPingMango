<template>
   <div v-loading="fullscreenLoading">
    <ul class="timeTree" v-if="!contenShow">
        <div class="treeLine"></div>
        <li class="clearfix">
          <div class="fl timeT">
              <p>{{deliverTime}}</p>
              <img src="../assets/img/treeP.png" alt="">
          </div>
          <div class="timeContent fl">
              <p><span>收货方:</span>{{receiverName}}</p>
              <p class=""><span>所在位置:</span>{{receiverAddress}}</p>
          </div>
        </li>
        <li class="clearfix" v-if="logisticsName !== ''&& logisticsNo !== ''">
          <div class="fl timeT">
              <p>{{deliverTime}}</p>
              <img src="../assets/img/treeP.png" alt="">
          </div>
          <div class="timeContent fl">
              <p><span>物流商:</span>{{logisticsName}}</p>
              <p><span>物流单号:</span>{{logisticsNo}}</p>
          </div>
        </li>
        <li class="clearfix">
          <div class="fl timeT">
              <p>{{deliverTime}}</p>
              <img src="../assets/img/treeP.png" alt="">
          </div>
          <div class="timeContent fl">
              <p class=""><span>发货方:</span>{{entName}}</p>
              <p class=""><span>所在位置:</span>{{entAddress}}</p>
          </div>
        </li>
    </ul>
     <div class="top-img" v-if="contenShow">
       <img src="../assets/img/xu.png" title="暂无数据"/>
       <p class="shu">暂无数据</p>
     </div>
   </div>
</template>

<script>
export default {
    name: 'Logisticdialog',
    props: ['tracingSource'],
    data () {
      return {
        fullscreenLoading:true,
        traceabilityCode: this.tracingSource,
        contenShow: false,
        deliverTime: '',
        receiverName: '',
        receiverAddress: '',
        logisticsName: '',
        logisticsNo: '',
        entName:'',
        entContacter: '',
        entAddress: ''
      }
    },
    methods: {
      getData () {
        var _this = this
        axios.post('/sy/traceInfoByTraceabilityCode/getLogisticsInfo', {
            'traceabilityCode': this.traceabilityCode,
        })
        .then(function (res) {
          console.log(res)
          console.log(res.data.isSuccess)
            if (res.data.isSuccess) {
                let data = res.data.data
                _this.deliverTime = data.deliverTime
                _this.receiverName = data.receiverName
                _this.receiverAddress = data.receiverAddress
                _this.logisticsName = data.logisticsName
                _this.logisticsNo = data.logisticsNo
                _this.entContacter = data.entContacter
                _this.entAddress = data.entAddress
                _this.entName=data.entName
                _this.fullscreenLoading = false
            }else{
              _this.contenShow = true
              _this.fullscreenLoading = false
            }
        })
        .catch(function (error) {
            console.log(error);
        });
      }
    },
    created () {
      this.getData()
    },
    watch: {
        traceabilityCode: function(){
            this.traceabilityCode = this.tracingSource
            this.getData()
        }
    }
}
</script>

<style lang="scss" scoped>
.timeTree{
  position: relative;
  width: 1000px;
  margin: 0 auto;
  li{
    margin-top: 50px;
  }
  .timeT{
    position: relative;
    top: -10px;
    img{
      width: 60px;
      height: 55px;
    }
  }
  .treeLine{
    width: 2px;
    height: 100%;
    position: absolute;
    left: 30px;
  }
  .timeContent{
    font-size: 14px;
    line-height: 40px;
    border-radius: 5px;
    background: url('../assets/img/timeT.png') no-repeat;
    background-size: cover;
    width: 880px;
    margin-left: 40px;
    p{
      padding-right: 30px;
      padding-left: 50px;
      span{
        margin-right: 10px;
        font-size: 16px;
      }
    }
    .timeName{
      padding: 0 70px 0 50px;
      width: auto;
    }
  }
}
.top-img{
  width:282px;
  margin: 0 auto;
  img{
    width: 100%;
  }
}
.shu{
  color: #49a9ee;
  text-align: center;
  font-size: 20px;
}
</style>
