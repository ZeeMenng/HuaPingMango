import React, { Component } from "react";
import echarts from 'echarts';

export default class RankBar extends Component {
    constructor(props) {
        super(props);
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
let option = {
    backgroundColor:'#fff',
    grid: {
        left: '5%',
        right: '5%',
        top:'5%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:  {
        show:false,
        type: 'value',
    },
    yAxis: {
        show:true,
        type: 'category',
        inverse:true,
        data: data.yAxisData,
        nameTextStyle:{color:'#8199c3'},
        splitLine:{ show:false },
        axisTick:{ show:false },
        axisLabel:{
            color:'#333'
        },
        axisLine:{
            show:false,
            lineStyle:{
                color:"#18418a"
            }
        }
    },
    series: [
        {
            name: '访问次数',
            type: 'bar',
            barGap:'-100%',
            barWidth:7,
            itemStyle:{
                normal:{
                    barBorderRadius: [0, 20, 20, 0],
                    color:'#eaf2fa'
                }
            },
            silent:true,
            data: data.bgData

        },
        {
            name: '访问次数',
            type: 'bar',
            barWidth:7,
            label: {
                normal: {
                    show: true,
                    position:'right',
                    color:'#5799f3',
                    fontWeight:'bold',
                    formatter: function(params){
                        return params.value + '%';
                    }


                }
            },
            itemStyle: {
                normal: {
                    barBorderRadius: [0, 20, 20, 0],
                    color: new echarts.graphic.LinearGradient(
                        1, 0, 0, 0, [{
                            offset: 0,
                            color: '#4d92ff'
                        },
                            {
                                offset: 1,
                                color: '#7eb5ff'
                            }
                        ]
                    )
                }
            },
            silent:true,
            data: data.seriesData
        }

    ]
};
        myChart.setOption(option);
    }

    componentDidMount() {   //初始化渲染图表
        if(this.props.data){
            this.initEchart()
        }

    }

    componentDidUpdate() {  //更新数据重新渲染图表
        if(this.props.data){
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
}
