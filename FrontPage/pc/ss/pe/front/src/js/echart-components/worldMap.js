function randomData() {
    return Math.round(Math.random() * 1000);
}

function worldFn(data) {
    console.log(data)
    var arrData = data.seriesData;
    var maxMapData = Math.max.apply(Math, arrData.map(function(o) {return o.value}))
    var myChart = echarts.init(document.querySelector(data.el))
    //console.log(data.seriesData)
    // var defaultSeriesData = [
    //     { name: 'China', value: 123 },
    //     { name: 'Afghanistan', value: 233 },
    //     { name: 'Benin', value: 12343 },
    //     { name: 'Albania', value: 12343 },
    //     { name: 'Bangladesh', value: 12343 },
    //     { name: 'Belarus', value: 12343 },
    //     { name: 'Switzerland', value: 12343 },
    //     { name: 'Colombia', value: 12343 },
    //     { name: 'Algeria', value: 12343 },
    //     { name: 'Australia', value: 12343 },
    //     { name: 'Canada', value: 12343 },
    //     { name: 'Serbia', value: 12343 },
    //     { name: 'Thailand', value: 12343 },
    //     { name: 'Philippines', value: 12343 }
    // ];
    var option = {
        title:{
            text:'芒果进出口贸易情况',
            textStyle:{
                fontSize:14
            },
            top: 20,
            left:'center'
        },
        tooltip: {
            trigger: 'item',
            axisPointer: {
                type: 'shadow',
                shadowStyle: {
                    color: 'rgba(0,46, 115, 0.3)'
                }
            },
            formatter: function (params) {
                if (params.name) {
                    var str = '<div style="padding:5px;margin:0px;">' + params.name + "<br>" + "进口量： " + params.value+params.data.unit + "</div>";
                    return str
                }
            }
        },
        visualMap: {
            min: 0,
            max: maxMapData,
            left: 'left',
            top: 'bottom',
            itemHeight:'60px',
            itemWidth:'8px',
            text: ['高', '低'],
            calculable: true,
            color: ['#3f6901', '#b6e176'],
            orient: 'vertical'
        },
        series: [{
            name: '贸易国',
            type: 'map',
            mapType: 'world',
            roam: true,
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            nameMap: nameMap,
            top: 75,
            bottom: 0,
            left: 15,
            right: "0",
            itemStyle: {
                normal: {
                    color: "#e5e5e5",
                    borderColor: "#fff"
                }
            },
            data: data.seriesData
        }]
    };
    myChart.setOption(option)
}