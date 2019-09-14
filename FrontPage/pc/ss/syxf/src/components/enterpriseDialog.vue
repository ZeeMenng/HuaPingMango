<template>
  <div v-loading="fullscreenLoading">
    <ul class="dialogLogs" v-if="!contenShow">
        <li><span>企业名称:</span>{{enterpriseName}}</li>
        <li><span>企业地址:</span>{{enterpriseAddress}}</li>
        <li><span>联系人:</span>{{contactPerson}}</li>
        <li><span>联系电话:</span>{{telePhone}}</li>
        <!--<li><span>电商网址:</span>{{emallWebsite}}</li>-->
        <li class="clearfix"><span class="fl">企业介绍:</span><label class="fl longContent">{{description}}</label></li>
        <li class="clearfix">
            <span class="fl">企业资质:</span>
            <label class="fl longContent">
                <div v-for="(item, $index) in imgFile" :key="$index">
                    <img :src="item" alt="" @click="openPic(item)" style="width:200px;height:200px;"/>
                </div>
            </label>
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
    name: 'Enterprisedialog',
    props: ['enterpriseId'],
    data () {
        return {
            fullscreenLoading:true,
            contenShow: false,
            enterprisId: this.enterpriseId,
            enterpriseName: '',
            enterpriseAddress: '',
            contactPerson: '',
            telePhone: '',
            emallWebsite:'',
            description:'',
            imgFile: [],
            innerVisible: false,
            openUrl: ''
        }
    },
    methods: {
        openPic (url) {
            this.openUrl = url
            this.innerVisible = true
        },
        getData () {
            var _this = this
            axios.post('/sy/traceInfoByTraceabilityCode/getEnterpriseInfo', {
                'enterpriseId': this.enterprisId,
            })
            .then(function (res) {
                if (res.data.isSuccess) {
                    console.log(res)
                    _this.enterpriseName = res.data.data.enterpriseName
                    _this.enterpriseAddress = res.data.data.enterpriseAddress
                    _this.contactPerson = res.data.data.contactPerson
                    _this.telePhone = res.data.data.telePhone
                    _this.emallWebsite = res.data.data.emallWebsite
                    _this.description = res.data.data.description
                    _this.fullscreenLoading = false
                    if (res.data.data.fileResourcePath) {
                        _this.imgFile = res.data.data.fileResourcePath.split(',')
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
    watch: {
        enterpriseId: function(){
            this.enterprisId = this.enterpriseId
            this.getData()
        }
    }, 
    created () {
        this.getData()
    }
}
</script>

<style lang="scss" scoped>
.dialogLogs{
    width: auto;
}
.longContent{
    width: 531px;
}
.bigImg{
    width: 100%
    img{
        width: 100%;
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
