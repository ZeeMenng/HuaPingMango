<template>
  <div v-loading="fullscreenLoading">
    <div v-if="!contenShow">
      <ul class="switchList clearfix">
        <li :class="{active : myIndex === $index}" v-for="(item, $index) in lists" :key="$index" @click="handleClick($index, item.type,item.unit)">
          {{item.displayName}}
        </li>
      </ul>
      <div id="myChart" :style="{width: '100%', height: '300px'}"></div>
    </div>
    <div class="top-img" v-if="contenShow">
      <img src="../assets/img/xu.png" title="暂无数据"/>
      <p class="shu">暂无数据</p>
    </div>
  </div>
</template>

<script>
export default{
  name: 'Informationdialog',
  props:['enterpriseId'],
  data () {
    return {
      fullscreenLoading:true,
      contenShow: false,
      activeName: '',
      priseid:this.enterpriseId,
      myIndex: 0,
      type: '769',
      unit: '',      
      lists: [
        {displayName: '', type: ''}
      ],
      xAxis: [],
      series:[]
    }
  },
  mounted () {
    this.getInfor()
  },
  watch: {
      enterpriseId: function(){
        this.priseid = this.enterpriseId
        this.getData()
      }
  }, 
  created () {
    this.getData()
    this.getInfor()
  },
  methods: {
    handleClick (i, type, unit) {
      this.myIndex = i
      this.activeName = this.lists[i].displayName + '平均值走势'
      this.type = type
      this.unit = unit
      this.getInfor()
    },
    getData () {
        var _this = this
        axios.get('/iot/monitor/getMonitorList')
        .then(function (res) {
            if (res.status === 200) {
                _this.lists = res.data.data
            }
        })
        .catch(function (error) {
            console.log(error);
        });
    },
    getInfor () {
      var _this = this
      //alert(_this.type)
        axios.post('/iot/monitor/getIotMonitorDataByMonth', {
         'enterpriseId':this.priseid,
          type: _this.type.toString()
        })
        .then(function (res) {
            if (res.data.isSuccess) {
                let data = res.data.data
                _this.xAxis = []
                _this.series = []
                _this.fullscreenLoading = false
                for (let i in data) {
                  _this.xAxis.push(data[i].time)
                  _this.series.push(data[i].val)
                }
                _this.drawLine()
            }else{
              _this.contenShow = true
               _this.fullscreenLoading = false
            }
        })
        .catch(function (error) {
            console.log(error);
        });
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
.switchList{
  width: 600px;
  margin: auto;
  li{
    background: #ccc;
    min-width: 185px;
    float: left;
    margin-right: 10px;
    margin-bottom: 10px;
    line-height: 40px;
    text-align: center;
    color: #333;
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