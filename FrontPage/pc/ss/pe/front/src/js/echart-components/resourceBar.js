/**
 * Created by rcz on 2018/6/20.
 */


/**
 * 单位转换
 * @param arr @type {Array}     数组
 * @param plusNum @type {String&Number}    转换倍数
 */
function unitChange(arr,plusNum) {
    var newArr = [];
    arr.forEach(function(v,i){
        newArr[i] = Math.floor(v*plusNum)
    })
    return newArr;
}

function resourceBarFn(data) {
    // console.log(data)
    var myChart = echarts.init(document.querySelector(data.el));
    var axisLabel = {
        textStyle: {
            color: '#666666',
            fontSize: 16,
        },
    }
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow', // 默认为直线，可选为：'line' | 'shadow'
                shadowStyle:{
                    color: 'rgba(229,229,229, 0.3)',
                },
            },
            formatter: function(params) {
                // console.log(params)
                var str = '';
                var str = params[0].seriesName+'：'+params[0].value+'<br>'
                        + params[1].seriesName+'：'+params[1].value+'%';
                return str;

            },
            textStyle: {
                align: 'left',
                // color: '#707070',
                fontSize: '16'
            },
            // backgroundColor: 'rgba(255, 255, 255, 0.8)',
            borderWidth: '1',
            borderColor: '#dde7d0',
            extraCssText: 'box-shadow: 10px 10px 15px rgba(216, 228, 202, 0.9);',
            padding: 10
        },
        title:{
            text:data.titText,
            left:'center',
            top:'bottom',
            textStyle:{
                color:'#333',
                fontSize:16
            }
        },
        legend: {
            show:false,
            data: data.legendData || ['', ''],
            right: 'center',
            top: '0',
            textStyle: {
                fontSize: '14',
                color: '#333'
            },
            /*itemGap: 20,
             itemHeight: 8,
             itemWidth: 8*/
        },
        grid: {
            left: '3%',
            right: '3%',
            bottom: '10%',
            containLabel: true
        },

        xAxis: [{
            type: 'category',
            data:data.xAxisData,
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#cccccc',
                    width: 1,
                }
            },
    axisTick: {
        show: false
    },
    axisLabel:axisLabel,

}],
    yAxis: [
        {
            name:data.unitName || '单位:亩',
            type: 'value',
            nameTextStyle:{
              fontSize:16,
              color:'#666666',
              padding: [3, 4, 5, 40]
            },
            z:2,
            gridIndex: 0,
            axisLabel: axisLabel,
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#cccccc',
                    width: 1,
                }
            },
            axisTick: {
                show: false,
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: 'rgba(115,128,173,0.3)',
                    type: 'dashed',
                }
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(237,242,252,0.5)','rgba(255,255,255,1)']
                }
            },
        },
        {
            name:'单位:%',
            type: 'value',
            z:2,
            show:true,
            axisLabel: axisLabel,
            nameTextStyle:{
                fontSize:16,
                color:'#666666'
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#cccccc',
                    width: 1,
                }
            },
            axisTick: {
                show: false,
            },
            splitLine: {
                show: false,
            }
        }
    ],
        series: [{
        name: data.legendData[0] || '',
        type: 'bar',
        stack: '总量',
        yAxisIndex: 0,
        barMaxWidth: 18,
        itemStyle: {
            normal: {
                barBorderRadius: [20, 20,1, 1],
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
            name: data.legendData[1] || '',
            type: 'line',
            yAxisIndex: 1,
            lineStyle: {
                normal: {
                    color: '#ffb00d'
                }
            },
            itemStyle: {
                normal: {
                    color: '#ffb00d',
                    borderWidth: '2',
                    label:{
                        show:false,
                        position: 'top',
                        color:'#ff7200',
                        fontSize:14,

                    }
                },
            },
            data:data.seriesData[1],
            symbolSize: 10
        }
    ]
}
    myChart.setOption(option);

}