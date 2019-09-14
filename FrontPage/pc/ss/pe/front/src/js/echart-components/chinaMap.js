function initChina(data) {
    var myMap = echarts.init(document.querySelector(data.el))
    var url = '../../img/map-icon.png';
    var option = {
        title: {
            text: '全国芒果批发市场分布',
            textStyle: {
                fontSize: 14
            },
            top: 40,
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            confine:true,
            textStyle:{
                whiteSpace: 'normal'
            },
            formatter: function (params) {
                return '<div style="text-align:center;width:300px;white-space: normal;">' + params.name + '<p style="text-align:center;width:300px;white-space: normal;height:auto;">' + params.value[2] +'</p></div>';
            }
        },
        legend: {
            orient: 'vertical',
            y: 'bottom',
            x: 'right',
            data: ['pm2.5'],
            textStyle: {
                color: '#fff'
            }
        },
        geo: {
            map: 'china',
            label: {
                normal: {
                    show: false,
                    color: '#509987',
                },
                emphasis: {
                    show: false,
                    color: '#e69e21'
                }
            },
            itemStyle: {
                normal: {
                    areaColor: '#f9fff1',
                    borderColor: '#b7e182'
                },
                emphasis: {
                    areaColor: '#fffae2',
                    borderColor: '#f4cc21'
                }
            }
        },
        series: [{
            type: 'scatter',
            coordinateSystem: 'geo',
            symbol: 'image://' + url,
            symbolSize: [14, 24],
            data: data.seriesData
        }]
    }
    myMap.setOption(option)
}