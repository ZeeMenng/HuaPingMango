<template>
<div style="height:100%;">
    <div class="appDetail">
        <div class="avator">
            <img :src="productPicture" alt="">
            <h3>【{{brand}}】{{productName}}</h3>
            <p><span>溯源码：</span>{{tracingSource}}</p>
            <a class="buy" v-if="emallWebsite !== ''" :href="emallWebsite">点我购买</a>
        </div>
    </div>
    <div class="appWrap">
        <a class="appList" @click="goComponent($index)" v-for="(item, $index) in linkList" :key="$index">
            <h2>{{item.name}}</h2>
            <img :src="item.url" alt="">
        </a>
        <el-dialog
            :visible.sync="centerDialogVisible"
            width="80%"
            center>
            <span>暂无相关内容</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</div>
</template>

<script>
export default {
  name: "Pcdetail",
  data () {
      return {
          tracingSource: '',
          productPicture: '',
          brand: '',
          productName: '',
          messageId: '',
          productType: '',
          enterpriseId: '',
          emallWebsite:'',
          centerDialogVisible: false,
          insData: {
            planId: '',
            processId: '',
            productType: ''
          },
          linkList: [
            {name: '种植档案', url: require('../assets/img/a1.png')},
            {name: '加工档案', url: require('../assets/img/a2.png')},
            {name: '企业信息', url: require('../assets/img/a3.png')},
            {name: '检验检测', url: require('../assets/img/a4.png')},
            {name: '物联网检测信息', url: require('../assets/img/a5.png')},
            {name: '物流流向', url: require('../assets/img/a6.png')}
          ]
      }
  },
  methods: {
    goComponent (i) {
        switch(i)
            {
            case 0:
                if (this.productType === '1') {
                    this.$router.push({ path: '/croprecords', query: {paicheNo: this.messageId}})
                } else {
                    this.centerDialogVisible = true
                }
                break;
            case 1:
                if (this.productType ==='2') {
                    this.$router.push({ path: '/processing', query: {paicheNo: this.messageId}})
                } else {
                    this.centerDialogVisible = true
                }
                break;
            case 2:
                this.$router.push({ path: '/enterpriseinformation', query: {paicheNo: this.enterpriseId}})
                break;
            case 3:
                this.$router.push({ path: '/inspection', query: {paicheNo: JSON.stringify(this.insData)}})
                break;
            case 4:
                this.$router.push({ path: '/information', query: {paicheNo: this.enterpriseId}})
                break;
            case 5:
                this.$router.push({ path: '/logistics', query: {paicheNo: this.tracingSource}})
                break;
            }
    },  
    getData () {
      var _this = this
      axios.post('/sy/traceInfoByTraceabilityCode/getTrCodeBaseInfo', {
        traceabilityCode: this.tracingSource,
        source: 'app'
      })
      .then(function (res) {
          let data = res.data.data
          if (res.data.isSuccess) {
              _this.brand = data.brand
              _this.productName = data.productName
              _this.productPicture = data.productPicture
              _this.tracingSource = data.tracingSource
              _this.messageId = data.messageId
              _this.productType = data.productType
              _this.enterpriseId  = data.enterpriseId
              _this.insData.processId = data.processId
              _this.insData.planId = data.planId
              _this.insData.productType = data.productType
               _this.emallWebsite = data.emallWebsite
          }
      })
      .catch(function (error) {
          console.log(error);
      });
    }
  },
  created () {
    this.tracingSource = this.$route.query.traceabilityCode
    this.getData()
  }
}
</script>

<style lang="scss" scoped>
.appDetail{
    background: url('../assets/img/banner.png') no-repeat;
    background-size: 100%;
    height: 37%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .avator{
        margin-bottom: 10px;
        text-align: center;
        font-size: 15px;
        color: #fff;
        img{
            width: 100px;
            height: 100px;
            border-radius: 5px;
        }
        h3{
            margin-bottom: 10px
        }
        p{
            font-size: 12px;
        }
    }
}
.appWrap{
    display: flex;
    box-sizing: border-box;
    width: 100%;
    height: calc(100vh - 37%);
    padding: 16px;
    border-radius: 5px;
    flex-wrap: wrap;
    align-content: flex-start;
    .appList{
        position: relative;
        display: inline-flex;
        flex-direction: column;
        box-sizing: border-box;
        width: 50%;
        height: 33%;
        padding: 10px;
        color: #fff;
        background: #218549;
        justify-content: center;
        align-items: center;
        h2{
            font-size: 12px;
            font-weight: 400;
            line-height: 30px;
            height: 30px;
            color: #fff;
        }
        img{
            width: 44px;
            height: 44px;
        }
    }
    .appList:nth-child(2), .appList:nth-child(3), .appList:nth-child(6){
        background-color: #2c9154;
    }
    .appList::after {
        position: absolute;
        top: 42%;
        right: 25%;
        display: inline-block;
        width: 13px;
        height: 13px;
        content: "";
        background: url('../assets/img/noinfo.png');
        background-size: cover;
    }
    .appList:nth-child(1)::after {
        background: url('../assets/img/hasinfo.png');
        background-size: cover;
    }
}
.buy{
  color: red;
  display: inline-block;
  margin-top: 5px;
}
</style>