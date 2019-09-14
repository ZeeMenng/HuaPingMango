function barFn(data) {
    var myChart = echarts.init(document.querySelector(data.el))
    var option = {
        color: ['#1a98f3'],
        title: {
            text: '华坪县芒果产量走势',
            textStyle: {
                fontSize: 14
            },
            top: 18,
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            },
            padding: 5,
            backgroundColor: '#fff',
            borderWidth: 1,
            borderColor: '#e0f5ea',
            extraCssText: 'box-shadow: 0 0 15px #e0f5ea;',
            textStyle: {
                color: '#666'
            },
            formatter: function (params) {
                var value=params[0].value/1000
                return params[0].name +'年<br/><span style="color:#ffc428;font-size:18px;font-weight:bold;">' + value+'</span>吨'
            }
        },
        grid: {
            top:85,
            left: '4%',
            right: '4%',
            bottom: 10,
            containLabel: true
        },
        xAxis: {
            name: '',
            type: 'category',
            axisLine: {
                lineStyle: {
                    color: '#ccc',
                }
            },
            axisLabel: {
                color: '#666',
                interval: 2
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#ccc',
                    width: 1,
                    type: 'solid',
                }
            },
            axisTick: {
                show: false
            },
            data: data.xData
        },
        yAxis: [{
            name: '吨',
            nameTextStyle: {
                color: '#666'
            },
            type: 'value',
            axisLine: {
                lineStyle: {
                    color: '#ccc',
                }
            },
            axisLabel: {
                color: '#666',
                formatter: function(value,index){
                    var value;
                    value = value/1000;
                   return value
                }

            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#f4f4f4',
                    width: 1,
                    type: 'solid',
                }
            },
            axisTick: {
                show: false
            }
        }],
        series: [
            {
                name: '设备数量',
                type: 'bar',
                stack: '总',
                barMaxWidth: 15,
                itemStyle: {
                    normal: {
                        barBorderRadius: [20, 20, 1, 1],
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                offset: 0,
                                color: '#ffc630'
                            },
                            {
                                offset: 1,
                                color: '#ffaa03'
                            }
                            ]
                        )
                    },
                    /*emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#ff9a16'
                                },
                                {
                                    offset: 1,
                                    color: '#ff8212'
                                }
                            ]
                        )
                    }*/
                },
                label: {
                    normal: {
                        show: false,
                        color: '#fff',
                        position: 'insideTop'
                    }
                },
                data: data.seriesData
            }
        ]
    };
    myChart.setOption(option)
}