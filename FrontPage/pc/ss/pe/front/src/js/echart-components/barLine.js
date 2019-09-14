function barLineFn(data) {
    var myChart = echarts.init(document.querySelector(data.el));
    var option = {
        backgroundColor: '#fff',
        title: {
            text: '全国芒果批发市场价格监测',
            textStyle: {
                fontSize: 14
            },
            top: 20,
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                return params[0].name + '<br/><span>' + params[0].seriesName + '：</span><span>' + params[0].value + '元/公斤</span><br>'
                    + '<span>' + params[1].seriesName + '：</span><span>' + params[1].value + '%</span>'
            },
            padding: 10
        },
        legend: {
            show: false
        },
        grid: {
            top:100,
            left: '3%',
            right: '4%',
            bottom: 0,
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: data.xAxisData,
            axisLabel: {
                textStyle: {
                    color: '#666',
                    fontSize: 12
                },
                margin: 12
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#ccc',
                    width: 1,
                }
            },
            axisTick: {
                show: false
            }
        }],
        yAxis: [

            {
                name:'元/公斤',
                nameTextStyle: {
                    color: '#666'
                },
                type: 'value',
                z: 2,
                axisLabel: {
                    textStyle: {
                        color: '#666',
                        fontSize: 12
                    },
                    margin: 12
                },
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: '#ccc',
                        width: 1,
                    }
                },
                axisTick: {
                    show: false,
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#f4f4f4',
                        width: 1,
                        type: 'solid',
                    }
                },
            },
            {
                name: '周期增长率',
                nameTextStyle: {
                    color: '#666',
                    align:'left',
                    padding:[0,38,0,0]
                },
                type: 'value',
                z: 2,
                show: true,
                axisLabel: {
                    textStyle: {
                        color: '#666',
                        fontSize: 12
                    },
                    formatter: function (value, index) {
                        return value+'%';
                    },
                    margin: 12
                },
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: '#ccc',
                        width: 1,
                    }
                },
                axisTick: {
                    show: false,
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#f4f4f4',
                        width: 1,
                        type: 'solid',
                    }
                }
            }
        ],
        series: [{
            name: '价格',
            type: 'bar',
            stack: '总量',
            yAxisIndex: 0,
            barMaxWidth: 15,
            markArea: {
                silent: true,
                data: [
                    [{
                        name: '',
                        xAxis: data.xAxisData[-1],
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        { offset: 0, color: '#bae586' },
                                        { offset: 1, color: '#84cc2b' }
                                    ]
                                )
                            }
                        },
                    }, {
                        xAxis: data.xAxisData[6],
                    }]

                ]
            },
            itemStyle: {
                normal: {
                    barBorderRadius: [20, 20, 1, 1],
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1, [{
                            offset: 0,
                            color: '#abdf6c'
                        },
                        {
                            offset: 1,
                            color: '#86cd2e'
                        }
                        ]
                    )
                },
            },
            data: data.seriesData[0]
        },
        {
            name: '周期增长率',
            type: 'line',
            yAxisIndex: 1,
            lineStyle: {
                normal: {
                    color: '#ffaf0a'
                }
            },
            symbolSize:5,
            itemStyle: {
                normal: {
                    color: '#ffb00d',
                    borderWidth: '2',
                    label: {
                        show: false,
                        position: 'top',
                        color: '#ff7200',
                        fontSize: 14,

                    }
                },
            },
            data: data.seriesData[1]
        }
        ]
    };
    myChart.setOption(option)
}