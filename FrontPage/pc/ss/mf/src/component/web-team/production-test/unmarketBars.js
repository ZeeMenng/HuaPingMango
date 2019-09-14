import React, { Component } from "react";
import echarts from 'echarts';

export default class UnmarketBarline extends Component {
    constructor(props) {
        super(props);
        this.initEchart = this.initEchart.bind(this)
    }

    initEchart(){   // 渲染图表方法
        let data = this.props.data;
        //console.log(data)
        let myChart = echarts.init(this.refs.myChart);
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
            tooltip : {
                trigger: 'axis',
                axisPointer : {
                    type : 'shadow',
                    shadowStyle:{
                        color:'rgba(255,255,255,0.5)'
                    }
                },
                padding:0,
                backgroundColor:'transparent',
                formatter: function(param){
                    var value1,value2;
                    value1 =(param[0].data/10000).toFixed(2);
                    value2=(param[1].data/10000).toFixed(2);

                    //console.log(param);
                    var html='',unit = '万';
                    html +='<div style="position:relative;padding: 15px 15px 5px 15px;font-size:14px;line-height:14px;font-weight:bold;color:#fff;background:rgba(15, 52, 135, 0.5);border:2px solid #167cc4;border-radius:5px;box-shadow:0 0 10px 0 rgba(40,157,252,.6);">';
                    html +='<p style="font-size:16px;">'+ param[0].name+'月</p>';
                    html += '<p>销量： ' + value2+ '万吨</p>';
                    html += '<p>产量： ' +value1+ '万吨</p>';
                    return html;
                }
            },
            legend: {
                left: 'center',
                itemWidth: 15,
                itemHeight: 8,
                itemGap: 40,
                data: [{
                    name: "销量",
                    borderRadius: 30,
                    textStyle: {
                        color:"#fff",
                        fontSize: 16
                    }
                },
                    {
                        name: "产量",
                        icon:'rect',
                        textStyle: {
                            color:"#fff",
                            fontSize: 16
                        },
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
                containLabel: true
            },
            yAxis: [{
                name: "万吨",
                type: 'value',
                min:'0',
                axisTick: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color:'#8ac7ff',
                        width: 1
                    }
                },
                axisLabel:{
                    color: '#fff',
                    fontSize: 16,
                    formatter: function(value,index){
                        var value;
                        value = value/10000;
                        if(value>0 && value<0.1){
                            return value.toFixed(1);

                        }else{
                            return value;

                        }


                    }
                },
                /*splitArea: {
                 show: true,
                 areaStyle: {
                 color: ['rgba(2,24,65,0.8)', 'rgba(2,13,41,0.8)']
                 }
                 },*/
                splitLine:{
                    show: true,
                    lineStyle:{
                        color:'#3f7adf',
                        type:'solid',
                        width:1
                    }
                }
            },{
                name: "",
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color:'#8ac7ff',
                        width: 2
                    }
                }
            }],

            xAxis: {
                type: 'category',
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
                axisLine:{
                    lineStyle: {
                        color:'#6297df',
                        fontSize: 16,
                        width: 2
                    }
                },
                axisLabel:axisLabel,
                data: data.xAxisData
            },
            series: [
                {
                    name: '产量',
                    type: 'bar',
                    barWidth:20,
                    itemStyle: {
                        normal: {
                            barBorderRadius: 10,
                            borderColor: '#ffea38',
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 1,
                                color: 'rgba(255,234,56, 0.8)'
                            }, {
                                offset: 0,
                                color: 'rgba(255,234,56, 0.4)'
                            }])

                        }
                    },
                    barCategoryGap:'40%',
                    data:data.seriesData[1],
                    animation: false
                },
                {
                    name: '销量',
                    type: 'bar',
                    barWidth : 20,
                    barCategoryGap:'40%',
                    itemStyle: {
                        normal: {
                            borderColor:'#07a2f5',
                            barBorderRadius: [20, 20, 20, 20],
                            color: new echarts.graphic.LinearGradient(0,0, 0, 1, [{
                                offset: 0,
                                color: 'rgba(0, 255, 255, 1)'
                            }, {
                                offset: 1,
                                color: 'rgba(51, 102, 204, 0.4)'
                            }])
                        }

                    },
                    data:data.seriesData[0]
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
                <div style={{width:'100%',height:'90%',marginTop:'6%'}} ref='myChart' className="my-chart"></div>
        )
        }else{
            return (
                <div style={{ width: '100%', height: '100%' }}></div>
        )
        }
    }
}