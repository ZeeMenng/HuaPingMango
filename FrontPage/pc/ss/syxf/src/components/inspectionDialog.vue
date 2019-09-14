<template>
 <div v-loading="fullscreenLoading">
    <ul class="jceList" v-if="!contenShow">
        <li class="clearfix" v-for="(item, $index) in insList" :key="$index">
            <div class="fl" style="width: 200px;">
                <h3 v-if="item.detectionType === '1'">企业自检</h3>
                <h3 v-if="item.detectionType === '2'">第三方检测</h3>
                <h3 v-if="item.detectionType === '3'">政府抽检</h3>
                <div class="jceP">
                    <p v-if="item.detectionType !== '1'">检测机构：{{item.detectionMechanism}}</p>
                    <p>检测时间：{{item.testTime}}</p>
                </div>
            </div>
            <div class="fl wrapJce">
                <div class="fl jceImgWrap" v-for="(i, $index) in item.imgList" :key="$index">
                    <img :src="i" alt="" @click="openPic(i)">
                </div>
            </div>
            <div class="imgHg">
                <img src="../assets/img/23.png" alt="">
            </div>
        </li>
        <el-dialog
        width="30%"
        :visible.sync="innerVisible"
        title="查看图片"
        append-to-body>
            <div class="bigImg">
                <img :src="openUrl" alt="">
            </div>
        </el-dialog>
    </ul>
    <div class="top-img" v-if="contenShow">
        <img src="../assets/img/xu.png" title="暂无数据"/>
        <p class="shu">暂无数据</p>
    </div>
 </div>
</template>

<script>
export default {
    name: 'Inspectiodialog',
    props: ['indata'],
    data () {
        return {
            fullscreenLoading:true,
            openUrl: '',
            contenShow: false,
            innerVisible: false,
            productType: this.indata.productType,
            planId: this.indata.planId,
            processId: this.indata.processId,
            insList: [
                {detectionType: '', inspectionUnit: '', testTime: '', url: '', imgList:[]}
            ]
        }
    },
    methods: {
        openPic (url) {
            this.openUrl = url
            this.innerVisible = true
        },
        getData () {
            var _this = this
            var params = {}
            if (_this.productType === '1') {
                params = {
                    "productType": _this.productType,
                    "planId": _this.planId
                }
            } else {
                params = {
                    "productType": _this.productType,
                    "processId": _this.processId
                }
            }
            axios.post('/sy/traceInfoByTraceabilityCode/getInspectionInfo', params)
            .then(function (res) {
                if (res.data.isSuccess) {
                    console.log(res)
                    let data = res.data.data
                    _this.insList = data.dataList
                    _this.fullscreenLoading = false
                    for (let i in _this.insList) {
                        _this.insList[i].imgList = _this.insList[i].url.split(',')
                    }
                    console.log(_this.insList)
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
    watch: {
        indata: {
    　　　　handler(newValue, oldValue) {
            this.productType = newValue.productType
            this.planId = newValue.planId
            this.processId = newValue.processId
            this.getData()
    　　　　},
    　　　　deep: true
    　　}
    },
    created () {
        this.getData()
    }
}
</script>

<style lang="scss" scoped>
.jceList{
    width: 920px;
    margin: 0 auto;
  li{
    background: url('../assets/img/jce.png') no-repeat;
    background-size: cover;
    padding: 10px 0 10px 30px;
    margin-bottom: 20px;
    position: relative;
    .imgHg{
        position: absolute;
        top: -20px;
        right: -20px;
    }
    h3{
        font-size: 14px;
        color: #dfffa1;
        line-height: 24px;
        width: 80px;
        padding: 0 16px;
        background: #3f8a40;
        border-radius: 20px;
        text-align: center;
        font-weight: 500;
    }
    .jceP{
        margin-top: 15px;
        p{
            line-height: 24px;
        }
    }
    .wrapJce{
        .jceImgWrap{
            width: 110px;
            height: 102px;
            display: inline-block;
            text-align: center;
            line-height: 102px;
            margin-left: 15px;
            img{
                vertical-align:middle;
                max-width: 110px;
                max-height: 102px;
            }
        }
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
