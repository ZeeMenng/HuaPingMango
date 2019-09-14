<template>
<div v-loading="fullscreenLoading">
    <div v-if="!contenShow">
        <div class="clearfix">
            <ul class="dialogLogs fl">
                <li><span>种植基地:</span>{{form1.originName}}</li>
                <li><span>基地地址:</span>{{form1.plantAddress}}</li>
                <li><span>地块:</span>{{form1.detailName}}</li>
                <li><span>产品名称:</span>{{form1.productName}}</li>
                <!--<li><span>种植时间:</span>{{form1.harvestTime}}</li>-->
                <li><span>采收时间:</span>{{form1.harvestTime}}</li>
                <li><span>采收批次:</span>{{form1.recoveryBatch}}</li>
            </ul>
            <ul class="dialogLogs fr wrapImg">
                <li class="clearfix" v-for="(item, $index) in form1.imgList" :key="$index" v-if="item.url.length > 0">
                    <span class="fl">{{item.name}}</span>
                    <span class="fl imgWrp" v-for="(i, Index) in item.url" :key="Index">
                        <img :src="i" alt="" @click="openPic(i)">
                    </span>
                </li>
            </ul>
        </div>
        <div><span class="spanTitle">农事操作:</span>
            <el-table :data="dataList" border>
                <el-table-column prop="growthstageName" label="生育期阶段"></el-table-column>
                <el-table-column prop="operationName" label="农事操作"></el-table-column>
                <el-table-column prop="inputsName" label="使用品牌"></el-table-column>
                <el-table-column prop="useAmount" label="使用量"></el-table-column>
                <el-table-column prop="spec" label="规格"></el-table-column>
                <el-table-column prop="operator" label="操作人"></el-table-column>
                <el-table-column prop="operationDate" label="操作时间"></el-table-column>
                <el-table-column property="imgUrl" label="图片">
                    <template slot-scope="scope">
                        <div style="width: 50px;height: 50px;" v-if="scope.row.imgUrl">
                            <img style="width: 100%;" :src="scope.row.imgUrl" alt="" @click="openPic(scope.row.imgUrl)">
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
            <el-dialog
                width="50%"
                :visible.sync="innerVisible"
                title="查看图片"
                append-to-body>
                    <div class="bigImg">
                        <img :src="openUrl" alt="">
                    </div>
            </el-dialog>
            <el-dialog
                width="50%"
                title="查看视频"
                :visible.sync="vedioVisible"
                append-to-body>
                <Player :video-url="videoUrl" :state="state" />
            </el-dialog>
        </div>
    </div>
    <div class="top-img"  v-if="contenShow">
        <img src="../assets/img/xu.png" title="暂无数据"/>
        <p class="shu">暂无数据</p>
    </div>
</div>
</template>

<script>
import Player from '@/components/play'
export default {
    name: 'cropDialog',
    props: ['messageId'],
    components: {
        Player
    },
    data () {
        return {
            fullscreenLoading:true,
            mId: this.messageId,
            openUrl: '',
            contenShow: false,
            vedioVisible: false,
            innerVisible: false,
            videoUrl: '',
            state: true,
            form1: {
                originName: '',
                plantAddress: '',
                detailName: '',
                productName: '',
                plantTime:'',
                harvestTime:'',
                recoveryBatch: '',
                imgList: [
                    {name: '产地认定证书:', url: []}
                    // {name: '产品认证证书:', url: []}
                ]
            },
            dataList: [
                {
                    growthstageName: '',
                    imgUrl: '',
                    operationContent: '',
                    operationDate: '',
                    operationId: '',
                    operationName: '',
                    operator: '',
                    planId: '',
                    videoUrl: ''
                }
            ]
        }
    },
    methods: {
        openVedio (url) {
            this.videoUrl = url
            this.vedioVisible = true
        },
        openPic (url) {
            this.openUrl = url
            this.innerVisible = true
        },
        getData () {
            var _this = this
            axios.post('/sy/traceInfoByTraceabilityCode/getPlantingInfo', {
                'messageId': this.mId,
            })
            .then(function (res) {
                if (res.data.isSuccess) {
                    console.log(res)
                    let data = res.data.data
                    _this.dataList = data.dataList
                    _this.form1.originName = data.originName
                    _this.form1.plantAddress = data.plantAddress
                    _this.form1.detailName = data.detailName
                    _this.form1.productName = data.productName
                    _this.form1.plantTime = data.plantTime
                    _this.form1.harvestTime = data.harvestTime
                    _this.form1.recoveryBatch = data.recoveryBatch
                    _this.fullscreenLoading = false
                    if (data.productionCertificatePaths) {
                        _this.form1.imgList[0].url = data.productionCertificatePaths.split(',')
                    }
                    if (data.processCertificatePaths) {
                        _this.form1.imgList[1].url = data.processCertificatePaths.split(',')
                    }
                    console.log(_this.form1.imgList[0].url)
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
        messageId: function(){
            console.log(this.messageId)
            this.mId = this.messageId
            this.getData()
        }
    },
    created () {
        this.getData()
    }
}
</script>

<style lang="scss" scoped>
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
.bigImg{
    width: 100%
    img{
        width: 100%;
    }
}
</style>

