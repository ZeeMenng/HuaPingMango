<template>
  <div>
    <div v-loading="fullscreenLoading">
    <AppHeader />
    <div v-if="!contenShow">
      <div class="headerBank"></div>
      <ul class="switchList clearfix">
        <li :class="{active : myIndex === $index}" v-for="(item, $index) in lists" :key="$index" @click="handleClick($index, item.type, item.unit)">
          {{item.displayName}}
        </li>
      </ul>
      <div id="myChart" :style="{width: '100%', height: '300px'}"></div>
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
      contenShow: false,
      activeName:'',
      myIndex: 0,
      type:'769',
      unit:'',
      lists: [
        {displayName: '', type: ''}
      ],
      addval: [],
      addtime: [],
      //enterpriseId:[]
    }
  },
  created () {
    this.enterpriseId=this.$route.query.paicheNo
    this.getData()
    this.getDatas()
  },
  methods: {
    handleClick(i, type, unit){
        this.myIndex=i
        this.activeName = this.lists[i].displayName + '平均值走势'
        //console.log(type)
        //alert(type)
        this.type=type
        this.unit=unit
        this.getDatas()
    },
    getData () {
      var _this = this
      axios.get('/iot/monitor/getMonitorList')
      .then(function (res) {
          let data = res.data.data
          if (res.status === 200) {
            _this.lists = res.data.data
          }
      })
      .catch(function (error) {
            console.log(error);
      })
    },
    getDatas () {
      var _this = this
      axios.post('/iot/monitor/getIotMonitorDataByMonth', {
      enterpriseId: this.enterpriseId,
      type: _this.type.toString()
      })
      .then(function (res) {
          if (res.data.isSuccess) {
            let data = res.data.data
            _this.xAxis = []
            _this.series = []
            _this.fullscreenLoading = false
            for(var i=0;i<data.length;i++){
              _this.xAxis.push(data[i].time)
              _this.series.push(data[i].val)
            }
            console.log(_this.addval)
            _this.drawLine()
          } else {
            _this.contenShow = true
            _this.fullscreenLoading = false
          }
      })
      .catch(function (error) {
          console.log(error);
      })
    },
    drawLine(){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        myChart.setOption({
            title: { text: this.activeName },
            tooltip: {},
            xAxis: {
                type: 'category',
                data: this.xAxis
            },
            yAxis: {
              axisLabel: {
              formatter: '{value} '+this.unit+'',
             },
            },
            series: [{
                data: this.series,
                type: 'line'
            }]
        });
    }
  }
}
</script>
<style lang="scss" scoped>
.headerBank{
  height: 40px;
}
.switchList{
  padding-left: 10px;
  padding-top: 10px;
  li{
    width: 30%;
    background: #ccc;
    float: left;
    margin-right: 10px;
    text-align: center;
    color: #333;
    line-height: 30px;
    margin-bottom: 10px;
    border-radius: 10px 10px 0 0;
    cursor: pointer;
  }
  .active{
    background: #49a9ee;
    color: #fff;
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
#myChart{
  margin: 0 16px;
}
</style>