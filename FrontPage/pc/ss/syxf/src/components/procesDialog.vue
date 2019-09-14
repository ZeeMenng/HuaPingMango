<template>
<div>
    <div v-if="!contenShow">
        <div class="clearfix">
            <ul class="dialogLogs fl">
                <li><span>加工产品:</span>{{form2.productName}}</li>
                <li><span>加工批次:</span>{{form2.processBatch}}</li>
                <li><span>加工产量:</span>{{form2.amount}}</li>
                <li><span>生产日期:</span>{{form2.processDate}}</li>
                <li><span>保质期:</span>{{form2.guaranteeDate}}</li>
                <li><span>包装日期:</span>{{form2.packDate}}</li>
            </ul>
            <ul class="fl dialogLogs">
                <li class="clearfix" v-if="processCertificatePaths.length > 0">
                    <span class="fl">产品认证证书:</span>
                    <label class="fl">
                        <img :src="item" alt="" v-for="(item, $index) in processCertificatePaths" :key="$index">
                    </label>
                </li>
                <li class="clearfix" v-if="processImgPaths.length > 0"><span class="fl">加工图片:</span>
                    <label class="fl">
                        <img :src="item" alt="" v-for="(item, $index) in processImgPaths" :key="$index" @click="openPic(item)">
                    </label>
                </li>
                <li v-if="processVideoPaths" class="clearfix"><span class="fl">加工视频:</span><label @click="openVedio(processVideoPaths)">点击查看视频</label></li>
            </ul>
        </div>
        <div>
            <li><span class="spanTitle">加工原料:</span>
                <el-table :data="gridData" border>
                    <el-table-column property="processProductName" label="品种"></el-table-column>
                    <el-table-column property="consumption" label="消耗量(吨)"></el-table-column>
                </el-table>
            </li>
        </div>
        <el-dialog
            width="30%"
            title="查看视频"
            :visible.sync="vedioVisible"
            append-to-body>
            <Player :video-url="videoUrl" :state="state" />
        </el-dialog>
        <el-dialog
            width="50%"
            :visible.sync="innerVisible"
            title="查看图片"
            append-to-body>
                <div class="bigImg">
                    <img :src="openUrl" alt="">
                </div>
        </el-dialog>
    </div>
  <div class="top-img" v-if="contenShow">
      <img src="../assets/img/xu.png" title="暂无数据"/>
      <p class="shu">暂无数据</p>
  </div>
</div>
</template>
<script>
import Player from '@/components/play'
export default {
    name: 'Procedialog',
    props: ['messageId'],
    components: {
        Player
    },
    data () {
        return {
            mId: this.messageId,
            contenShow: false,
            openUrl: '',
            vedioVisible: false,
            innerVisible: false,
            videoUrl: '',
            state: true,
            form2: {
                productName: '',
                processBatch: '',
                amount: '',
                processDate: '',
                guaranteeDate:'',
                packDate:'',
            },
            processCertificatePaths: [],
            processImgPaths: [],
            processVideoPaths: '',
            gridData: [
                {processProductName: '', consumption: ''}
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
            console.log(this.mId)
            var _this = this
            axios.post('/sy/traceInfoByTraceabilityCode/getProcessInfo', {
                'messageId': this.mId,
            })
            .then(function (res) {
                console.log(res)
                if (res.data.isSuccess) {
                    let data = res.data.data
                    _this.gridData = data.dataList
                    _this.form2 = data
                    _this.processVideoPaths = data.processVideoPaths
                    if (data.processCertificatePaths) {
                        _this.processCertificatePaths = data.processCertificatePaths.split(',')
                    }
                    if (data.processImgPaths) {
                        _this.processImgPaths = data.processImgPaths.split(',')
                    }
                }else{
                    _this.contenShow = true
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
