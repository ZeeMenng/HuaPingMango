import React, {Component} from "react";
import echarts from 'echarts';
export default class EchartsLine extends Component {
    constructor(props) {
        super(props);
        //console.log(props)
        this.initEchart = this.initEchart.bind(this)
    }
    initEchart() { // 渲染图表方法
        let data = this.props.data;
        let myChart = echarts.init(this.refs.myChart);
        let echartOpt = {
            fz: '14',
            color: '#dbfcff'
        };
        let axisLabel = {
            textStyle: {
                color: '#999999',
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
                show: true,
                trigger: 'axis',
                textStyle: {
                    align: 'left',
                    color: '#fff',
                    fontSize: '16'
                }
            },
            legend: {
                icon: 'line',
                itemWidth: 14,
                itemHeight: 4,
                itemGap: 13,
                x: 'center',
                top: '5%',
                textStyle: {
                    fontSize: 15,
                    color: '#fff'
                }
            },

            grid: {
                top: '15%',
                left: '5%',
                right: '5%',
                bottom: '15%',
                containLabel: true
            },
            dataZoom:{
                start:20,
                height:15,
                end:90,
                bottom:10,
                fillerColor:'rgba(247,230,196,0.5)',  //控制轴颜色
                backgroundColor:'#f5f5f5',
                handleStyle:{
                    color:'#ff9600'
                }
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
                        color: '#c8ddf1',
                        fontSize: 15
                    }
                },
                data: data.xAxisData
            }],
            yAxis: [{
                type: 'value',
                name: '单位：条',
                show: true,
                nameTextStyle: {
                    fontSize: echartOpt.fz,
                    color: '#999999',
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: '#c8ddf1'
                    }
                },
                axisLabel: axisLabel,
                splitLine: {
                    show: false,
                    formatter: '{value}',
                    lineStyle: {
                        color: '#476bbe'
                    }
                }
            }],
            series: [{
                    type: 'line',
                    smooth: false,
                    lineStyle: {
                        normal: {
                            width: 2,
                            symbolSize: 0,
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#e69a23',
                            symbolSize: 3,
                            borderColor: '#e69a23',
                        },
                        emphasis: {
                            borderColor: 'e69a23',
                            symbolSize: 5,
                        }
                    },
                areaStyle:{
                    normal:{
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#faf5e2'
                        },
                            {
                                offset: 1,
                                color: '#fff'
                            }]),
                        opacity:0.8

                    }
                },
                    showSymbol: true,
                    data: data.seriesData
                },
            ]
        };
        myChart.setOption(option);
    }

    componentDidMount() { //初始化渲染图表
        if (this.props.data) {
            this.initEchart()
        }
    }

    componentDidUpdate() { //更新数据重新渲染图表
        if (this.props.data) {
            this.initEchart()
        }
    }

    render() {
        if (this.props.data) {
            return ( <div style = {
                    {
                        width: '100%',
                        height: '270px'
                    }
                }
                ref = 'myChart'
                className = "my-chart" > </div>
            )
        } else {
            return ( <div style = {
                    {
                        width: '100%',
                        height: '100%'
                    }
                }> </div>
            )
        }

    }
}