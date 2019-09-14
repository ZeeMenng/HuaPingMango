function dataLine(data) {
    var priceNum = echarts.init(document.querySelector(data.el));
    var echartOpt = {
        fz: '16',
        color: '#666666'
    };
    var axisLabel = {
        textStyle: {
            color: '#666666',
            fontSize: 16,
            fontWeight: 'lighter'
        },
        margin: 18
    }
    option = {
        title: {
            text: data.titText,
            left: 'center',
            top: 'bottom',
            textStyle: {
                color: '#333',
                fontSize: 16
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            },
            /*formatter: function(params) {
             var str = '';
             params.forEach(function(v, i) {
             str += '收购价 ' + '<br>' + '日度： ' + params[0].data;
             });
             return str

             },*/
            textStyle: {
                align: 'left',
                fontSize: '16'
            }
        },
        label: {
            normal: {
                textStyle: {
                    color: "#ff3b00"
                }
            },
            emphasis: {
                textStyle: {
                    color: "#ff3b00"
                }
            }
        },
        grid: {
            left: '5%',
            right: '1%',
            bottom: '10%',
            top: '80',
            containLabel: true
        },
        yAxis: [{
            name: data.unitName || "美元/公斤",
            nameTextStyle: {
                fontSize: 16,
                padding: [3, 4, 5, 40]
            },
            type: 'value',
            min: 'dataMin',
            axisLine: {
                show: true,
                lineStyle: {
                    color: echartOpt.color,
                    fontSize: echartOpt.fz,
                    width: 1,
                }
            },
            axisTick: { show: false },
            axisLabel: {
                textStyle: {
                    color: "#cccccc"
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: 'rgba(115,128,173,0.3)',
                    type: 'dashed',
                    width: 1
                }
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(255,255,255,0)', 'rgba(237,242,252,0.5)']
                }
            },
            axisLabel: axisLabel
        }],

        xAxis: {
            type: 'category',
            name: '',
            splitLine: {
                show: false,
                lineStyle: {
                    color: ['#254495'],
                    type: 'dashed'
                }
            },
            axisTick: {
                show: false
            },
            axisLabel: axisLabel,
            axisLine: {
                lineStyle: {
                    color: echartOpt.color,
                    fontSize: echartOpt.fz
                }
            },
            data: data.xAxisData,
        },
        series: [
            {
                name: '价格',
                type: 'line',
                smooth: true,
                barWidth: 14,
                label: {
                    normal: {
                        show: false,
                        position: 'right',
                        formatter: "{c}%"
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#9ccf51'
                    }

                },
                // 顺序 从下向上 传入
                data: data.seriesData
            }
        ]
    };

    priceNum.setOption(option);
}