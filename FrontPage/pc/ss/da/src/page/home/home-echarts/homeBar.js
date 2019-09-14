import React, { Component } from "react";
import echarts from 'echarts';

export default class HomeBar extends Component {
    constructor(props) {
        super(props);
        //console.log(props)
        this.initEchart = this.initEchart.bind(this)
    }

    initEchart(){   // 渲染图表方法
        let data = this.props.data;
        let myChart = echarts.init(this.refs.myChart);
        let axisLabel = {
                interval:2,
                textStyle: {
                    color: '#999999',
                    fontSize: 12,
                    fontWeight: 'lighter'
                },
                margin: 16,
                formatter: (params) => {
                return params
            }
    }
        let echartOpt = {
            fz: '10',
            color: '#dbfcff'
        };
        let option = {
            tooltip : {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow',
                    shadowStyle: {
                        color: 'rgba(0,46, 115, 0.3)'
                    }
                },
                formatter: function(params) {
                    let str = '';
                    params.forEach(function(v, i) {
                        str += params[0].name + '<br>数量： ' + params[0].data;
                    });
                    return str

                },
                textStyle: {
                    align: 'left',
                    color: '#5cc1ff',
                    fontSize: '16'
                },
                backgroundColor: 'rgba(15, 52, 135, 0.8)',
                borderWidth: '1',
                borderColor: '#5cc1ff',
                extraCssText: 'box-shadow: 0 0 10px rgba(255, 255, 255, 0.7);'


            },
            legend: {
                show:false,
                data: [{
                    name: "",
                    textStyle: {
                        color:"#fff"
                    }
                }],
            },
            label: {
                normal: {
                    textStyle: {
                        color: "#ff3b00"
                    }
                },
                emphasis:{
                    textStyle:{
                        color:"#ff3b00"
                    }
                }
            },
            grid: {
                left: '5%',
                right: '5%',
                bottom: '5%',
                top:'30',
                containLabel: true
            },
            yAxis: [{

                type : 'value',
                //min:'dataMin',
                axisTick: {
                    show: false
                },
                splitLine: {
                    show:false,
                    formatter: '{value}',
                    lineStyle: {
                        color: '#476bbe'
                    }
                },
                axisLine:{
                    lineStyle: {
                        color:echartOpt.color,
                        fontSize:echartOpt.fz
                    }
                },
                axisLabel:axisLabel,

            }],

            xAxis: {
                type : 'category',
                name: '',
                splitLine: {
                    show:false,
                    lineStyle: {
                        color: ['#254495'],
                        type:'dashed'
                    }
                },
                axisTick:{
                    show:false
                },
                axisLabel: {
                    rotate:45,
                    interval:0,
                    formatter: function(params){
                        if(params.length>9){
                            return  params.slice(0,9)+'...'
                        }else{
                            return  params
                        }
                    },
                    textStyle:{
                        color:'##808080'
                    },
                    triggerEvent:true
                },
                axisLine:{
                    lineStyle: {
                        color:echartOpt.color,
                        fontSize:echartOpt.fz
                    }
                },
                data: data.xAxisData
            },
            series: [
                {
                    name: '',
                    type: 'bar',
                    barWidth : 14,
                    label: {
                        normal: {
                            show: false,
                            position: 'right',
                            formatter: "{c}%"
                        }
                    },
                    itemStyle: {
                        normal: {
                            barBorderRadius: [20, 20, 0, 0],
                            color: new echarts.graphic.LinearGradient(0,0, 0, 1, [{
                                offset: 0,
                                color: 'rgba(80, 155, 250, 1)'
                            }, {
                                offset: 1,
                                color: 'rgba(62, 126, 244, 1)'
                            }])
                        }

                    },
                    // 顺序 从下向上 传入
                    data:data.seriesData
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
                <div style={{ width: '100%', height: '270px' }} ref='myChart' className="my-chart"></div>
        )
        }else{
            return (
                <div style={{ width: '100%', height: '100%' }}></div>
        )
        }

    }
}/**
 * Created by ws on 2018/5/22.
 */
