import React, { Component } from "react";
import echarts from "echarts";
import "echarts-liquidfill";


export default class LiquidPie extends Component {
    constructor(props) {
        super(props)
        this.initChart = this.initChart.bind(this)
    }
    randomNum = () => {
        return (Math.random() * 100).toFixed(0);
    }
    initChart = ()=> {
        let myChart = echarts.init(this.refs.chart);
        let data = this.props.data;
        let legend = [];
        data.forEach((v,i)=>{
            legend[i] = v.name
        })
        console.log(data)
        let option = {
            backgroundColor: 'transparent',
            legend:{
                top:'center',
                left:40,
                itemGap:30,
                orient:'vertical',
                textStyle:{
                    color:'#fff'
                },
                data:legend || []
            },
            color:['#49d3f5','#ffd552','#ff902e','#90d96c','#24b282'],
            tooltip: {
                trigger: 'item',
                position: function (point, params, dom, rect, size) {
                    //console.log(size.contentSize);
                    //console.log(size.viewSize);
                    return [(size.viewSize[0] - size.contentSize[0])*13.5 / 20, (size.viewSize[1] - size.contentSize[1]) / 2 ];
                },
                formatter:  (params)=> {
                    // this.refs.maxNum.style.display = 'none';
                    let html=`<div style="background:transparent;color:#fff;font-size:16px;">
                                ${params.percent}%
                            </div>`;
                    return html;
                },
                backgroundColor:'rgba(55,67,95,0)',
                padding: 10
            },
            series : [
                {
                    name:'',
                    type:'pie',
                    radius : [50, 75],
                    center : ['65%', '50%'],
                    label: {
                        normal: {
                            show:false
                        },
                        emphasis:{
                            show:true,
                            color: '#fff',
                            fontSize: 15,
                            lineHeight: 20,
                            formatter: '{c}亩\n（{d}%）\n{b}',
                            align: 'center'
                        }
                    },
                    itemStyle:{
                            normal:{
                                borderColor:'#2f4dad',
                                borderWidth:1
                            }
                        },
                    data: data
                }
            ]
        };
        myChart.setOption(option)
        // myChart.on('mouseout',(params)=>{
        //     // console.log(params)
        //     this.refs.maxNum.style.display = 'block';
        // })
    }

    componentDidMount() {
        if (this.props.data) {
            this.initChart()
        }
    }

    componentDidUpdate() {
        // console.log(this.props)
        if (this.props.data) {
            this.initChart()
        }
    }

    render() {
        if (this.props.data) {
            return (
                <div style={{ position: 'relative', width: '100%', height: '90%',top:'15%' }}>
                    {/* <span ref="maxNum" style={{ fontSize:'14px',position: 'absolute', right: '122px', top: '100px', fontSize: '17px', color: '#fff', zIndex: 9999 }}>{this.props.maxValue || ''}</span> */}
                    <div ref="chart" style={{ width: '100%', height: '100%' }}></div>
                </div>
                
            )
        } else {
            return (
                <div style={{ width: '100%', height: '100%' }}></div>
            )
        }

    }
}