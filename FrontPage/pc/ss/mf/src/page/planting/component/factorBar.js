import React, { Component } from "react";
import echarts from 'echarts';

export default class FactorBar extends Component {
    constructor(props) {
        super(props);
        //console.log(props)
        this.initEchart = this.initEchart.bind(this)
    }

    initEchart(){   // 渲染图表方法
       // console.log(this.props.data)
        let data;
        if(this.props.data.legendData){
            data = this.props.data;
        }else{
            data = {
                legendData: [],
                xAxisData:[],
                seriesData: {
                    dryData:[],
                    coldData:[],
                    blowingData:[],
                    rainData:[]
                }
            }
        }

        let myChart = echarts.init(this.refs.myChart);
        let axisLabel = {
            interval:0,
            //rotate: '45',
            textStyle: {
                color: '#fff',
                fontSize: 14,
                fontWeight: 'lighter'
            },
            margin: 10,
            formatter: (params) => {
                return params
            }
        }
        let option = {
            //backgroundColor: '#1b237e',
            grid: {
                right: '1%',
                left: '10%',
                bottom: '25%',
                top: '20%'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow',
                    shadowStyle: {
                        color: 'rgba(0,46, 115, 0.3)'
                    }
                },
                //formatter: '{a0}: {c0}次<br />{a1}: {c1}次<br />{a2}: {c2}次<br />{a3}: {c3}次',
                textStyle:{
                            align:'left',
                            color:'#5cc1ff',
                            fontSize:'16'
                        },
                backgroundColor:'rgba(15, 52, 135, 0.5)',
                borderWidth:'1',
                borderColor:'#5cc1ff',
                extraCssText: 'box-shadow: 0 0 10px rgba(255, 255, 255, 0.7);'

            },
            legend: {
                data: data.legendData,
                right: '60',
                top: '15',
                textStyle: {
                    color: '#8ac7ff'
                },
                itemGap: 20,
                itemHeight: 15,
                itemWidth: 15
            },
            calculable: true,
            xAxis: {
                type : 'category',
                 axisLine:{
                        lineStyle: {
                            color:'#8ac7ff'
                        }
                    },
                axisTick:{
                        show: false,
                        interval: 0,
                        alignWithLabel: true
                },
                axisLabel:{
                        interval: 0,
                        //rotate:'30',
                        textStyle:{
                            fontSize:10,
                            color:'#fff'
                        }
                },
                data : data.xAxisData,
                    splitLine: {
                       show:true,
                       lineStyle: {
                            color: ['#2f46a1']
                        }
                    }
            },
            yAxis: {
                    type: 'value',
                    name: '次',
                    nameTextStyle: {
                        color: '#fff'
                    },
                    splitLine: {
                       show:true,
                       lineStyle: {
                            color: ['#2f46a1']
                        }
                    },
                    axisTick:{
                        show:false
                    },
                    axisLabel: {
                        show: true,
                        textStyle:{
                            fontSize:10,
                            color:'#fff'
                        }
                    },
                    axisLine:{
                        lineStyle: {
                            color:'#8ac7ff'
                        }
                    }
            },
            series: [{
                name: data.legendData[0],
                type: 'bar',
                barMaxWidth: 20,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                offset: 0,
                                color: '#00b8fe'
                            }, {
                                offset: 1,
                                color: '#1846a3'
                            }]
                        ),
                        opacity: 0.6,
                        barBorderRadius: 30,
                        barBorderColor: '#00b6fc'
                    },
                    emphasis: {
                        opacity: 1
                    }
                },
                data: data.seriesData.dryData,
                zlevel: 9

            }, {
                name: data.legendData[1],
                type: 'bar',
                barMaxWidth: 20,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                offset: 0,
                                color: '#d0bf44'
                            }, {
                                offset: 1,
                                color: '#d0bc44'
                            }]
                        ),
                        opacity: 0.6,
                        barBorderRadius: 30,
                        barBorderColor: '#fbde30'
                    },
                    emphasis: {
                        opacity: 1
                    }
                },
                data: data.seriesData.coldData,
                zlevel: 9

            }, {
                name: data.legendData[2],
                type: 'bar',
                barMaxWidth: 20,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                offset: 0,
                                color: '#d0bf44'
                            }, {
                                offset: 1,
                                color: '#6a3b63'
                            }]
                        ),
                        opacity: 0.6,
                        barBorderRadius: 30,
                        barBorderColor: '#fb9635'
                    },
                    emphasis: {
                        opacity: 1
                    }
                },
                data: data.seriesData.blowingData,
                zlevel: 9

            }, {
                name: data.legendData[3],
                type: 'bar',
                barMaxWidth: 20,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                offset: 0,
                                color: '#39ab4f'
                            }, {
                                offset: 1,
                                color: '#595d71'
                            }]
                        ),
                        opacity: 0.6,
                        barBorderRadius: 30,
                        barBorderColor: '#2fee37'
                    },
                    emphasis: {
                        opacity: 1
                    }
                },
                data: data.seriesData.rainData,
                zlevel: 9

            }]



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
                <div style={{width:'100%',height:'100%'}} ref='myChart' className="my-chart"></div>
            )
        }else{
            return (
                <div style={{ width: '100%', height: '100%' }}></div>
            )
        }

    }
}