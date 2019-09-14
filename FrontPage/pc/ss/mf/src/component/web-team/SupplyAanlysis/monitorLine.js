import React, { Component } from "react";
import echarts from 'echarts';

export default class MonitorLine extends Component {
  constructor(props) {
    super(props);
    console.log(props)
    let me = this;
    this.initEchart = this.initEchart.bind(this);
    me.echartStyle = {
      width: '100%',
      height: '100%',
    }
  }

  initEchart(){   // 渲染图表方法
    let data = this.props.data;
    let myChart = echarts.init(this.refs.myChart);
    let axisLabel = {
      textStyle: {
        color: '#fff',
        fontSize: 14,
        fontWeight: 'lighter'
      },
      margin: 18,
      formatter:(params)=>{
        return params
      }
    };
    let option = {
      calculable : true,
      tooltip: {
        trigger: 'axis',
        formatter: function (params) {
            var str = params[0].name + "<br>";
            params.forEach(function (v, i) {
                str += v.seriesName + ": " + v.value + "%" + "<br>";
            });
            return str
        }
      },
      legend: {
        show: true,
        data: data.legendData1,
        left: 'center',
        top: '20',
        textStyle: {
          color: '#fff',
          fontSize: 14
        },
        itemWidth: 20,
        itemHeight: 10,
      },
      grid: {
        right: '5%',
        left: '10%',
        bottom: '18%',
        top: '15%'
      },
      xAxis: {
        name:'',
        nameTextStyle:{
          color:'#fff'
        },

        type: 'category',
        boundaryGap: true,//折线距离y轴有无距离
        axisLabel: axisLabel,
        axisTick: {
          show:false
        },

        axisLine: {
          lineStyle: {
            color: "#35ece6",
            type: "solid",
            width: 1
          }
        },

        data : data.xAxisData1,

      },
      yAxis: {
        name:'%',
        nameTextStyle:{
          color:'#fff'
        },
        type: 'value',
        //min: 0,
        //max: 100,
        //interval: 20,
        axisLabel: axisLabel,
        axisTick: {
          show: false
        },
        axisLine: {
          lineStyle: {
            color: "#35ece6",
            type: "solid",
            opacity:1
          }
        },
        splitLine: {
          lineStyle: {
            color: '#17a096', //分割线颜色
            type: "solid",
            width:1
          }
        }
      },
      itemStyle:{
        normal:{
          color:'#00a6ff',
          borderWidth:'2'
        }
      },
      color: ['#007ad0','#1ea9f9','#00c66b','#fbd657','#ff315c','#b15aff',"#35ece6",'#00e6af'],
      series : [{
                  name: data.legendData1[0],
                  type: 'line',
                  data: data.seriesData1[0],
                  symbolSize:8
              },
              {
                  name: data.legendData1[1],
                  type: 'line',
                  data: data.seriesData1[1],
                  symbolSize:8
              },
              {
                  name: data.legendData1[2],
                  type: 'line',
                  data: data.seriesData1[2],
                  symbolSize:10
              },
              {
                  name: data.legendData1[3],
                  type: 'line',
                  data: data.seriesData1[3],
                  symbolSize:8
              },
              {
                  name: data.legendData1[4],
                  type: 'line',
                  data: data.seriesData1[4],
                  symbolSize:8
              },
              {
                  name: data.legendData1[5],
                  type: 'line',
                  data: data.seriesData1[5],
                  symbolSize:8
              },
              {
                  name: data.legendData1[6],
                  type: 'line',
                  data: data.seriesData1[6],
                  symbolSize:8
              },
              {
                  name: data.legendData1[7],
                  type: 'line',
                  data: data.seriesData1[7],
                  symbolSize:8
              }
        /* {
          name:'产销率',
          type:'line',
          smooth:false,
          itemStyle: {normal: {areaStyle: {type: 'default'},borderColor:'#2f4dad'}},
          data: data.seriesData,
          symbolSize: 6,
          lineStyle:{
            normal:{
              color:'#00f8ff'
            }
          },
          areaStyle:{
            normal:{
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(15,255,252,1)'
              }, {
                offset: 1,
                color: 'rgba(42,238,245,0.01)'
              }]),
              opacity:0.6

            }
          }
        } */

      ]
    };
    /* this.state.option.series.map((v.i)=>{
      v.data=null;
    })
    let newOption ={
      legend:{
        data.legend
      },
      serise[{
        name:'',
        data:[]
      },
      {
        name:'',
        data:[]
      },{
        name:'',
        data:[]
      }]
    }
    this.state.option.extent(true,option,newOption) */
    myChart.setOption(option);
  }

  componentDidMount() {   //初始化渲染图表
    this.initEchart()
  }

  componentDidUpdate() {  //更新数据重新渲染图表
    this.initEchart()
  }

  render() {
    if (this.props.data){
    return (
        <div style={{width:'100%',height:'430px'}} ref='myChart' className="my-chart"></div>
    )
    }else{
        return (
            <div style={this.echartStyle}></div>
        )
    }

  }
}