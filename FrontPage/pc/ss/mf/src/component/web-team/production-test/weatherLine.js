import React, { Component } from "react";
import echarts from 'echarts';

export default class weatherLine extends Component {
    constructor(props) {
        super(props);
        //console.log(props)
        this.initEchart = this.initEchart.bind(this)
    }

    initEchart(){   // 渲染图表方法
        let data = this.props.data;
        let myChart = echarts.init(this.refs.myChart);
        let datas = {
            datax:[],
            datas:[]
        }
        let axisLabel = {
                textStyle: {
                    color: '#fff',
                    fontSize: 16,
                    fontWeight: 'lighter'
                },
                margin: 18,
                formatter:(params)=>{
                return params
            }
    }
        let option = {
            calculable : true,
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow',
                    shadowStyle: {
                        color: 'rgba(0,46, 115, 0.3)'
                    }
                },
                textStyle: {
                    align: 'left',
                    color: '#5cc1ff',
                    fontSize: '16'
                },
                /* formatter: function(param){
                    //console.log(param);
                    var html='',unit = '万';
                    html +='<div style="position:relative;padding: 15px 15px 5px 15px;font-size:14px;line-height:14px;font-weight:bold;color:#fff;background:rgba(15, 52, 135, 0.5);border:2px solid #167cc4;border-radius:5px;box-shadow:0 0 10px 0 rgba(40,157,252,.6);">';
                    html +='<p style="font-size:16px;">'+ param[0].name+'</p>';
                    html += '<p>预警次数： ' + param[0].data + '次</p>';
                    return html;
                }, */
                backgroundColor: 'rgba(15, 52, 135, 0.8)',
                borderWidth: '1',
                borderColor: '#5cc1ff',
                extraCssText: 'box-shadow: 0 0 10px rgba(255, 255, 255, 0.7);'
            },
            legend: {
                show: true,
                icon:'rect',
                data: data.legendData || ['预警值', '温度'],
                left: 'center',
                top: '5',
                textStyle: {
                    color: '#fff',
                    fontSize: 14
                },
                itemWidth: 12,
                itemHeight: 5,
            },
            grid:{
                right:'10',
                top:'45'
            },
            xAxis: {
                name:'',
                boundaryGap:true,
                nameTextStyle:{
                    color:'#fff'
                },

                type: 'category',
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
                data: data.xAxisData
            },
            yAxis: {
                name:'次',
                nameTextStyle:{
                    color:'#fff'
                },
                type: 'value',
                //min: 0,
                // min: this.echartsData.minData,
               /* max:function (value) {
                  if(value.max<=data.mData){
                        return data.mData
                  }else{
                      return value.max*1.1
                  }
                },*/
                min: function(value) {
                    return (value.min*0.9).toFixed(0);
                },
                max: function(value) {
                    return (value.max*1.1).toFixed(0);
                },
            /*    interval:10,*/
                axisLabel: axisLabel,
                axisTick: {
                    show:false,
                },
                markLine:{
                    precision:20,
                    lineStyle: {
                        color: "#9b6966",
                        type: "solid",
                        opacity:1
                    }
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
                        color: '#17a096',//分割线颜色
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

            series : [
                {
                    name:data.legendData[0] || '温度',
                    type:'line',
                    smooth:false,
                    itemStyle: {normal: {areaStyle: {type: 'default'},borderColor:'#2f4dad',}},
                    data:data.seriesData[0],
                    symbolSize: 6,
                    lineStyle:{
                        normal:{
                            color:'#2af594',
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
                },
                {
                    name: '预警值',
                    type: 'line',
                    itemStyle: {
                        normal: {
                            color: '#efcf34',
                            borderColor: '#fff',
                            borderWidth: '2',
                        }
                    },
                    symbol: 'circle',
                    symbolSize: 0,
                    showSymbol: false,
                    smooth: true,
                    data: data.seriesData[1] || [0, 0, 0, 0, 0, 0]
                }
            ]
        };
        myChart.setOption(option);
    }

    componentDidMount() {   //初始化渲染图表
        if (this.props.data){
            this.initEchart()
        }
    }

    componentDidUpdate() {  //更新数据重新渲染图表
        if (this.props.data) {
            this.initEchart()
        }
    }

    render() {
        if (this.props.data) {
            return (
                <div style={{width:'630px',height:'250px',marginTop:"50px",}} ref='myChart' className="my-chart"></div>
        )
        }else{
            return (
                <div style={{ width: '100%', height: '100%' }}></div>
        )
        }

    }
}