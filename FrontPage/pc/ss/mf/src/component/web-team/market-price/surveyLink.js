import React, { Component } from "react";
import echarts from 'echarts';

export default class SurveyLink extends Component {
    constructor(props) {
        super(props);
        //console.log(props)
        this.initEchart = this.initEchart.bind(this)
    }
    initEchart(data){   // 渲染图表方法
        console.log(data)
        let myChart = echarts.init(this.refs.myChart);
        let echartOpt = {
            fz: '14',
            color: '#dbfcff'
        };
        let axisLabel = {
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
            tooltip: {
                show:true,
                trigger: 'axis',
                textStyle:{
                    align:'left',
                    color:'#fff',
                    fontSize:'16'
                },
                /*backgroundColor:'rgba(15, 52, 135, 0.9)',
                borderWidth:'1',
                borderColor:'#5cc1ff',
                extraCssText: 'box-shadow: 0 0 10px rgba(255, 255, 255, 0.7);'*/
            },
            legend: {
                icon: 'line',
                itemWidth: 8,
                itemHeight: 4,
                itemGap: 10,
                data:data.legendData,
                right: '10',
                top:'3%',
                textStyle: {
                    fontSize: 15,
                    color: '#fff'
                }
            },

            grid: {
                top:'15%',
                left: '5%',
                right: '5%',
                bottom: '5%',
                containLabel:true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: true,
                axisTick: {
                    show: false
                },
                axisLabel: axisLabel,
                axisLine: {
                    lineStyle: {
                        color: '#99aef8',
                        fontSize:15
                    }
                },
                data: data.xAxisData
            }],
            yAxis: [{
                type: 'value',
                name: '元/公斤',
                show:true,
                nameTextStyle:{
                    fontSize: echartOpt.fz,
                    color:echartOpt.color
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    show:true,
                    lineStyle: {
                        color: '#99aef8'
                    }
                },
                axisLabel: axisLabel,
                splitLine: {
                    show:true,
                    formatter: '{value}',
                    lineStyle: {
                        color: '#476bbe'
                    }
                }
            }],
            color: ['#007ad0','#1ea9f9','#00c66b','#fbd657','#ff315c','#b15aff','#00e6af'],
            series: [
                {
                    name: data.legendData[0],
                    type: 'line',
                    data: data.seriesData[0],
                    symbolSize:8
                },
                {
                    name: data.legendData[1],
                    type: 'line',
                    data: data.seriesData[1],
                    symbolSize:8
                },
                {
                    name: data.legendData[2],
                    type: 'line',
                    data: data.seriesData[2],
                    symbolSize:10
                },
                {
                    name: data.legendData[3],
                    type: 'line',
                    data: data.seriesData[3],
                    symbolSize:8
                },
                {
                    name: data.legendData[4],
                    type: 'line',
                    data: data.seriesData[4],
                    symbolSize:8
                },
                {
                    name: data.legendData[5],
                    type: 'line',
                    data: data.seriesData[5],
                    symbolSize:8
                },
                {
                    name: data.legendData[6],
                    type: 'line',
                    data: data.seriesData[6],
                    symbolSize:8
                },
                {
                    name: data.legendData[7],
                    type: 'line',
                    data: data.seriesData[7],
                    symbolSize:8
                },
                {
                    name: data.legendData[8],
                    type: 'line',
                    data: data.seriesData[8],
                    symbolSize:8
                },
                {
                    name: data.legendData[9],
                    type: 'line',
                    data: data.seriesData[9],
                    symbolSize:8
                },



                /* {
                name: data.legendData[0],
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 2,
                        symbolSize:0,
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(49,145,224,1)'
                        }, {
                            offset: 1,
                            color: 'rgba(49,145,224,.1)'
                        }])
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#00ffff',
                        symbolSize:0,
                        borderColor:'#00ffff',
                    },
                    emphasis: {
                        borderColor:'#00ffff',
                        symbolSize:8,
                    }
                },
                showSymbol: false,
                data:data.seriesData[0]
            },
            {
                name: data.legendData[1],
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 2,
                        symbolSize:0,
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(51,255,153,1)'
                        }, {
                            offset: 1,
                            color: 'rgba(49,145,224,.1)'
                        }])
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#2af594',
                        borderColor:'#2af594',
                        symbolSize:0
                    },
                    emphasis: {
                        borderColor:'#2af594',
                        symbolSize:8,
                    }
                },
                showSymbol: false,
                data:data.seriesData[1]
            },
            {
                name: data.legendData[2],
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 2,
                        symbolSize:0,
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(204,204,51,1)'
                        }, {
                            offset: 1,
                            color: 'rgba(49,145,224,.1)'
                        }])
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#eee73d',
                        borderWidth:2,
                        borderColor:'#eee73d'

                    },
                    emphasis: {
                        borderColor:'#eee73d',
                        symbolSize:8,
                    }
                },
                showSymbol: false,
                data:data.seriesData[2]
            } */]
        };
        myChart.setOption(option);
    }

    componentDidMount() {   //初始化渲染图表
        let data = this.props.data;
        if (data){
            this.initEchart(data)
        }
    }

    componentDidUpdate() {  //更新数据重新渲染图表
        let data = this.props.data;
        if (data) {
            if (data.length === 0) {
                let newData = {
                    legendData:[],
                    xAxisData:[],
                    seriesData:[]
                }
                this.initEchart(newData)
            } else {
                this.initEchart(data)
            }
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