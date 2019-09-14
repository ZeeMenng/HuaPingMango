function cityMap(data) {
    var myChart = echarts.init(document.querySelector(data.el));
    var seriesData = [
        {
            name: "华坪县黄土地农产品开发基地", value: [101.28158569312529, 26.595281262620233]
        },
        {
            name: "华坪县黄土地农产品开发基地", value: [101.4657819999394, 26.786716000343176]
        }
    ];
    var url = '../../img/map-icon.png';
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '{b}'
        },
        geo: {
            map: '华坪县',
            top: 70,
            zoom: 1.2,
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        fontSize: 13,
                        color: '#2f6e00'
                    }
                },
                emphasis: {
                    show: false,
                    textStyle: {
                        color: '#333'
                    }
                }
            },
            // roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#f4ffe8',
                    borderColor: '#79ae39'
                },
                emphasis: {
                    areaColor: '#d5ffab',
                    borderColor: '#a2d6f3'
                }
            },
            regions: data.regionsData
        },
        series: [{
            name: '',
            type: 'scatter',
            coordinateSystem: 'geo',
            data: data.seriesData[0].value[0] ? data.seriesData : seriesData,
            symbol: 'image://' + url,
            symbolSize: [22,32],
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    color: '#ff0'
                },
                emphasis: {
                    borderColor: '#fff',
                    borderWidth: 1
                }
            }
        } /* , {
            type: 'map',
            map: '华坪县',
            zoom: 1.2,
            // roam: true,
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        fontSize: 13,
                        color: '#2f6e00'
                    }
                },
                emphasis: {
                    textStyle: {
                        color: '#333'
                    }
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: function (params) {
                    return '';
                }
            },
            itemStyle: {
                normal: {
                    borderColor: 'rgba(147, 235, 248, 1)',
                    borderWidth: 1,
                    areaColor: {
                        type: 'linear',
                        x: 0.5,
                        y: 0.5,
                        x2: 0,
                        y2: 1,
                        colorStops: [{
                            offset: 0,
                            color: 'rgba(213, 255, 177, .1)' // 0% 处的颜色
                        }, {
                            offset: 1,
                                color: 'rgba(213, 255, 177, .2)' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    },
                    shadowColor: 'rgba(213, 255, 177, 1)',
                    shadowOffsetX: -2,
                    shadowOffsetY: 2,
                    shadowBlur: 10
                },
                emphasis: {
                    areaColor: '#d5ffab',
                    borderWidth: 0
                }
            }
        }  */]
    }
    $.getJSON("../../js/echart-components/hua.1.json", function (result) {
        echarts.registerMap('华坪县', result);
        myChart.setOption(option)
    });
}