/**
 * Created by ws on 2018/5/21.
 */
import React, { Component } from "react";
import echarts from 'echarts';import "echarts-liquidfill";

export default class talkPie extends Component {
    constructor(props) {
        super(props);
        this.initEchart = this.initEchart.bind(this)
    }

    initEchart(){   // 渲染图表方法
        let data = this.props.data;
        //console.log(data)
        let myChart = echarts.init(this.refs.myChart);
        data.seriesData.sort((x,y)=>{
            return  y.value - x.value
        })
        let seriesData1 = data.seriesData.slice(0,4)
        let seriesData2 = data.seriesData.slice(4,data.seriesData.length)
        let seriesDataArr = []
        seriesData2.map((v,i)=>{
            seriesDataArr[i] = {
                value: v.value,
                name: v.name,
                label: {
                    normal: {
                        show:false,
                        textStyle: {
                            color: "#fff",
                            fontSize:'14'
                        },
                    },
                    emphasis:{
                        show:false
                    }
                },
                labelLine:{
                    normal: {
                        show:false
                    },
                    emphasis:{
                        show:false
                    }
                }
            }
        })
        seriesData1.map((v,i)=>{
            seriesDataArr[i] = {
                value: v.value,
                name: v.name,
                label: {
                    normal: {
                        textStyle: {
                            color: "#fff",
                            fontSize:'16'
                        },
                        formatter: "{b} : ({d}%)",
                    }
                },
            }
        })
        let option = {
                grid: {
                    left: '5%',
                    right: '5%',
                    bottom: '5%',
                    containLabel: true
                },
            tooltip: {
                trigger: 'item',
                formatter: "{b} : ({d}%)",
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
          /*  toolbox: {
                show: true,
                itemSize:22,
                top:-10,
                right:10,
                iconStyle:{
                    borderColor:'#479dec',
                    borderWidth:'2',
                },
                feature: {
                    saveAsImage: {

                    }
                }
            },*/
         /*   tooltip: {
                trigger: 'item',
                position: function (point, params, dom, rect, size) {
                    //console.log(size.contentSize);
                    //console.log(size.viewSize);
                    return [(size.viewSize[0] - size.contentSize[0]) / 2, (size.viewSize[1] - size.contentSize[1]) / 2 ];
                },
                formatter: function (params) {
                    // console.log(params)
                    let html=`<div style="background:transparent;font-size:16px;background: linear-gradient(#9affff, #21d1f5);-webkit-background-clip: text;">
                                ${params.percent}%
                            </div>`;
                    return html;
                },
                backgroundColor:'rgba(55,67,95,0)',
                padding: 10
            },*/
                legend: {
                    show: false,
                    right: '0',
                    textStyle: { color: "#82bcff" },
                    data: ['', '', '', '', '', '', '']
                },
                series: [{
                    name: '',
                    type: 'pie',
                    itemStyle: {
                        normal: {
                            show:false,
                            borderColor: '#67fbb5',
                            shadowColor: 'rgba(102,102,153, 0.6)'
                        },emphasis:{
                            show:true
                        },
                    },
                    labelLine: {
                        normal: {
                            length: 10,
                            length2: 40,
                            lineStyle: {
                                color:'#2c9be9'
                            }
                        }
                    },
                    radius: ['35%', '70%'],
                    center: ['50%', '50%'],
                    color: data.pieColor,
                    data: seriesDataArr
                }],
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
                <div style={{ width: '100%', height: '100%' }} ref='myChart' className="my-chart"></div>
        )
        }else{
            return (
                <div style={{ width: '100%', height: '100%' }}></div>
        )
        }

    }
}