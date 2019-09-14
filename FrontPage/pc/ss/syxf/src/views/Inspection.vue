<template>
<div>
  <div v-loading="fullscreenLoading">
  <AppHeader />
  <div v-if="!contenShow">
    <div class="headerBank"></div>
    <ul class="bigList">
      <div class="bigLine"></div>
      <li class="clearfix" v-for="(item, $index) in lists" :key="$index">
        <div class="bigCricle fl"></div>
        <div class="fr biglistCon">
          <div class="frist">
          <span class="tells" v-if="item.detectionType === '1'">企业自检</span>
          <span class="tells" v-if="item.detectionType === '2'">第三方检测</span>
          <span class="tells" v-if="item.detectionType === '3'">政府抽检</span>
          <p v-if="item.detectionType !== '1'">{{item.detectionMechanism}}</p>
          <p>{{item.testTime}}</p>
          <p>
              <img :src="item" v-for="(item, $index) in item.url.split(',')" :key="$index" @click="openImg(item)"/>
          </p>
          </div>
        </div>
        <div class="hg">
          <img class="top-img" src="../assets/img/23.png"/>
        </div>
      </li>
    </ul>
  </div>
  <div v-show="mask" class="maskWrap" @click="closePic">
    <img :src="bigImg" alt="">
  </div>
  <div v-if="contenShow"><img class="top-pic" src="../assets/img/xu.png" title="暂无数据"/><br><p class="shu">暂无数据</p></div>
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
      bigImg: '',
      mask: false,
      contenShow: false,
      routerData: {
        planId: '',
        processId: '',
        productType: ''
      },
      lists: [
        {
          detectionType: '',
          inspectionUnit: '',
          testTime: ''
        }
      ],
      imgList: []
    }
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
        var params = {}
        if (_this.routerData.productType === '1') {
          params = {
            "productType": _this.routerData.productType,
            "planId": _this.routerData.planId
          }
        } else {
          params = {
            "productType": _this.routerData.productType,
            "processId": _this.routerData.processId
          }
        }
        axios.post('/sy/traceInfoByTraceabilityCode/getInspectionInfo', params)
        .then(function (res) {
          console.log(res)
            if (res.data.isSuccess) {
                let data = res.data.data
                _this.lists = data.dataList
                _this.fullscreenLoading = false
                if (data.dataList.url) {
                    _this.imgList = data.dataList.url.split(',')
                }
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
    this.routerData = JSON.parse(this.$route.query.paicheNo)
    this.getData()
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
.bigList{
  position: relative;
  margin: 20px;
  margin-top: 80px;
  li{
    position: relative;
    .hg{
      position: absolute;
      top: -10px;
      right: -10px;
      width: 50px;
      height: 50px;
      img{
        width: 100%;
      }
    }
  }
  .bigLine{
    z-index: -1;
    width: 2px;
    height: 100%;
    position: absolute;
    left: 7px;
    background: greenyellow;
  }
  .biglistCon{
    width: calc(100vw - 90px);
    margin-bottom: 20px;
  }
  .bigCricle{
    width: 20px;
    height: 20px;
    border-radius: 50%;
    border: 1px solid greenyellow;
    background-color: greenyellow;
    box-shadow: 5px 5px 5px #888888;
    margin-left: -3px;
  }
}
.frist{
  border: 1px solid greenyellow;
  border-radius: 5px;
  padding: 10px;
  img{
    width:30px;
    margin-left: 12px;
  }
}
.tells{
  display: block;
  font-weight: 600;
  line-height:25px;
  border-radius: 5px; 
  color: #333;
  margin-bottom: 15px;
}
.top-pic{
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