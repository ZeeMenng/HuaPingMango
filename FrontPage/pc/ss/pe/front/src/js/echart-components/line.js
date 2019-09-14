function lineFn(data) {
    var myChart = echarts.init(document.querySelector(data.el));
    var option = {
        backgroundColor: '#fff',
        title: {
            text: '华坪县芒果价格走势',
            textStyle: {
                fontSize: 14
            },
            top: 18,
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            backgroundColor: '#fff',
            borderWidth: 1,
            borderColor: '#e0f5ea',
            textStyle: {
                color: '#666'
            },
            formatter: function (params) {
                return params[0].name + '年<br/>价格：<span style="color:#ffc428;font-size:18px;font-weight:bold;">' + params[0].value + '</span>元/公斤'
            }
        },
        legend: {
            show: false
        },
        grid: {
            top:85,
            left: '3%',
            right: '4%',
            bottom: 10,
            containLabel: true
        },

        xAxis: {
            boundaryGap: false,
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
            },
            data: data.xAxisData
        },
        yAxis: {
            name: '元/公斤',
            nameTextStyle:{
                color:'#666'
            },
            type: 'value',
            axisLabel: {
                textStyle: {
                    color: '#666',
                    fontSize: 12
                }
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
            },
            splitLine: {
                lineStyle: {
                    color: '#f4f4f4',
                    type: 'solid',
                    width: 1,
                }
            }
        },
        color: ['#14aaff'],
        series: [
            {
                name: '',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 6,
                showSymbol:false,
                lineStyle: {
                    normal: {
                        width: 2,
                        color:'#85c636'
                    }
                },
                data: data.seriesData
            }
        ]
    };
    myChart.setOption(option)
}