/**
 * Created by ws on 2018/6/15.
 */
//价格走势
function priceTrend(){
   var submitData={
       "entityRelated":{
       "regionId":"530723",
       "breed":"0"
   },
   "orderList":[{
           "columnName":"",
            "isASC":true
       }],
       "page":{
           "pageIndex":1,
           "pageSize":10
       }
   }
    ajaxGet("/mango/extend/swagger/da/daMarketPrice/getPriceDataAndProportion",submitData,priceTrendFn)

}
priceTrend();
function priceTrendFn(res) {
    //console.log(res)
    if ( res.isSuccess ){
        var data1=res.data;
        var pNum=[];
        data1.proportion.forEach(function(item,index){
        var proportion=item.replace("%", "");
        pNum.push(proportion);
        },this)
        var trendData ={
            xAxisData: data1.time,
            seriesData:[data1.data,pNum]
        }
        trendBarLine(trendData)
    }
}
function trendBarLine(data) {
    var trendNum = echarts.init(document.getElementById('trendNum'));
    var option1 = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow', // 默认为直线，可选为：'line' | 'shadow'
                shadowStyle: {
                    color: 'rgba(229,229,229, 0.3)',
                },
            },
            formatter: function(params) {
                // console.log(params)
                var str = params[0].name + "<br>";
                params.forEach(function(v, i) {
                    str += v.seriesName + ": " + v.value + "<br>";
                });
                return str

            },
            textStyle: {
                align: 'left',
                color: '#707070',
                fontSize: '16'
            },
            backgroundColor: 'rgba(255, 255, 255, 0.5)',
            borderWidth: '1',
            borderColor: '#dde7d0',
            extraCssText: 'box-shadow: 0 0 10px rgba(223, 234, 210, 0.7);',
            padding: 10
        },
        title: {
            text: '',
            left: 'center',
            textStyle: {
                color: '#474747',
                fontSize: 14
            }
        },
        legend: {
            show: true,
            data: ['全国芒果批发市场均价', '同比增长率'],
            right: 'center',
            top: '5',
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
            right: '1%',
            bottom: '11%',
            containLabel: true
        },
        dataZoom: {
            start: 20,
            height: 45,
            left:80,
            right:80,
            end: 90,
            bottom: 0,
            position:'inside',
            fillerColor: 'rgba(247,230,196,0.5)',  //控制轴颜色
            backgroundColor: '#f5f5f5',
            handleStyle: {
                color: '#ff9600'
            }
        },
        xAxis: [{
            type: 'category',
            data: data.xAxisData,
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#cccccc',
                    width: 2,
                }
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                textStyle: {
                    color: "#666666",
                    fontSize: 14
                }
            },

        }],
        yAxis: [
            {
                name: '单位:元/公斤',
                nameTextStyle: {
                    fontSize: 16,
                    color: '#666666',
                    padding:[0,0,10,40]
                },
                type: 'value',
                z: 2,
                gridIndex: 0,
                axisLabel: {
                    textStyle: {
                        color: '#666666',
                        fontSize: 14
                    },
                    formatter: '{value}'
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
                    show: true,
                    lineStyle: {
                        color: 'rgba(115,128,173,0.3)',
                        type: 'dashed',
                    }
                },
                splitArea: {
                    show: true,
                    areaStyle: {
                        color: ['#fcfcfc', '#fff']
                    }
                },
            },
            {
                name: '单位:%',
                nameTextStyle: {
                    fontSize: 16,
                    color: '#666666',
                    padding:[0,20,10,0]
                },
                type: 'value',
                z: 2,
                show: true,
                axisLabel: {
                    textStyle: {
                        color: '#666666',
                        fontSize: 14
                    },
                    formatter: '{value}',
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
                    lineStyle: {
                        color: 'rgba(115,128,173,0.3)',
                        type: 'dashed',
                    }
                }
            }
        ],
        series: [{
            name: '全国芒果批发市场均价',
            type: 'bar',
            stack: '总量',
            yAxisIndex: 0,
            barMaxWidth: 18,
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
            markPoint: {
                label: {
                    color: '#fff',
                    fontStyle: 'normal'
                },
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            data:data.seriesData[0]
        },
            {
                name: '同比增长率',
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
                        label: {
                            show: false,
                            position: 'top',
                            color: '#ff7200',
                            fontSize: 14,

                        }
                    },
                },
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                data: data.seriesData[1],
                symbolSize: 10
            }
        ]

    };
    trendNum.setOption(option1);
}

//城市涨跌幅 同比环比切换按钮
var tab = new Tab('comparison',function (params) {
    //console.log($(params).data('index'));
    var index = $(params).data('index');
    chgCity(index)
});
//城市涨跌幅
function chgCity(compareType){
   var submitData={
       "entityRelated":{
           "compareType":compareType//1代表环比 2代表同比
       },
       "orderList":[{
           "columnName":"sale_amount",
           "isASC":true
       }],
       "page":{
           "pageIndex":1,
           "pageSize":10
       }
   }
    ajaxGet("/mango/extend/swagger/da/daMarketPrice/getPriceCityChg",submitData,chgFn)

};

chgCity(1);
function chgFn(res) {
    console.log(res)
    if ( res.isSuccess){
        var data1=res.data;
        var xData=[];
        var sData=[], queryTime='';
        data1.forEach(function(item,index){
            //console.log(item)
            xData.push(item.region_name);
            sData.push(item.chg);
            queryTime=item.queryTime;
        },this)
        var chgData ={
            xAxisData: xData,
            seriesData:sData
        }
        chgBar(chgData);
        $('.price-chg .title-unit').text(queryTime)
    }else{
        $('.chgNum').html('<div class="noData">暂无数据</div>')
    }
}
function chgBar(data) {
    var chgNum = echarts.init(document.getElementById('chgNum'));
    var option2 = {
        //backgroundColor: '#fff',
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
                shadowStyle: {
                    color: 'rgba(229,229,229, 0.3)',
                },
            },
            /*formatter: function () {
                return
            },*/
            padding: 10
        },
        title: {
            text: '',
            left: 'center',
            textStyle: {
                color: '#474747',
                fontSize: 14
            }
        },
        legend: {
            show: false,
            data: ['全国芒果批发市场均价', '同比增长率'],
            right: 'center',
            top: '5',
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
            right: '4%',
            bottom: '2%',
            containLabel: true
        },

        xAxis: [{
            type: 'category',
            data:data.xAxisData,
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#cccccc',
                    width: 1,
                }
            },
            axisLabel: {
                textStyle: {
                    color: "#666",
                    fontSize: 16
                }
            },
        }],
        yAxis: [
            {
                type: 'value',
                name: '%',
                axisLabel: {
                    textStyle: {
                        color: '#666666',
                        fontSize: 16
                    },
                    formatter: '{value}',
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
                    show: true,
                    lineStyle: {
                        color: '#f5f5f5',
                        type: 'solid',
                    }
                },
            }
        ],
        series: [{
            name: '芒果价格波动幅度',
            type: 'bar',
            stack: '总量',
            yAxisIndex: 0,
            barMaxWidth: 14,
            itemStyle: {
                normal: {
                     barBorderRadius: 6,
                    color: function (d) {
                        if (d.data > 0) {
                            return '#ffab05'
                        } else {
                            return '#83cc29'
                        }
                    },
                    //opacity: 0.6,
                    barBorderRadius: 1,
                }
            },
            markPoint: {
                label: {
                    color: '#fff',
                    fontStyle: 'normal'
                },
            },
            data: data.seriesData
        },
        ]

    };
    chgNum.setOption(option2);
}
//各品种价格

function breedPrice(){
    var submitData={
        "entityRelated":{
            "regionId":"530723"
        },
        "orderList":[{
            "columnName":"",
            "isASC":true
        }],
        "page":{
            "pageIndex":1,"pageSize":10
        }
    }
    ajaxGet("/mango/extend/swagger/da/daMarketPrice/getperBreedPriceData",submitData,breedFn)
}
breedPrice();
function breedFn(res){
    if ( res.isSuccess ){
        console.log(res)
        var data=res.data;
        var legendData = [];
        var sData = [];
        //console.log(data.info)
        data.info.forEach(function(v,i){
           // console.log(v)
            legendData.push(v.name);
            sData.push(v.price)
        })
        console.log(legendData)
        var chgData ={
            xAxisData: data.time,
            legendData:legendData,
            seriesData:[
                sData[0],sData[1],sData[2],sData[3],sData[4],sData[5],sData[6],sData[7],sData[8],sData[9],sData[10],sData[11],sData[12],sData[13]
            ]
        }
        breedLine(chgData)
    }
}
function breedLine(data){
    var breedNum = echarts.init(document.getElementById('breedNum'));
    option3 = {
        //backgroundColor:'#fff',
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:data.legend,
            top:'1%',
            textStyle: {
            },
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '11%',
            containLabel: true
        },
        dataZoom:{
            start:20,
            height:45,
            end:90,
            bottom:0,
            fillerColor:'rgba(247,230,196,0.5)',
            backgroundColor:'#f5f5f5',
            handleStyle:{
                color:'#ff9600'
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data:data.xAxisData,
            boundaryGap:['20%','20%'],
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#cccccc',
                    width: 1,
                }
            },
            axisLabel: {
                textStyle: {
                    color: "#666666",
                    fontSize: 14
                }
            },
        },
        yAxis: {
            type: 'value',
            name: '单位:元/公斤',
            nameTextStyle:{
                fontSize:16,
                color:'#666666'
            },
            axisTick:{show:false},
            axisLabel: {
                textStyle: {
                    color: '#666666',
                    fontSize: 14
                },
                formatter: '{value}',
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: 'rgba(115,128,173,0.3)',
                    type: 'dashed',
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#cccccc',
                    width: 1,
                }
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['#fcfcfc', '#fff']
                }
            },

        },
        series: [
            {
                name:data.legendData[0],
                type:'line',
                data:data.seriesData[0],
                symbolSize:8,
                itemStyle: {
                    normal: {
                        color: '#ffa200'
                    }
                },
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
            },
            {
                name:data.legendData[1],
                type:'line',
                symbolSize:8,
                data:data.seriesData[1],
                itemStyle: {
                    normal: {
                        color: '#ffcc00'
                    }
                },
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
            },
            {
                name:data.legendData[2],
                type:'line',
                data:data.seriesData[2],
                symbolSize:8,
                itemStyle: {
                    normal: {
                        color: '#81d815'
                    }
                },
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
            },
            {
                name:data.legendData[3],
                type:'line',
                symbolSize:8,
                data:data.seriesData[3],
                itemStyle: {
                    normal: {
                        color: '#0abdff'
                    }
                },
            },{
                name:data.legendData[4],
                type:'line',
                symbolSize:8,
                data:data.seriesData[4],
                itemStyle: {
                    normal: {
                        color: "#f0a629"
                    }
                },
            },{
                name:data.legendData[5],
                type:'line',
                symbolSize:8,
                data:data.seriesData[5],
                itemStyle: {
                    normal: {
                        color: "#f54fc5"
                    }
                },
            },{
                name:data.legendData[6],
                type:'line',
                symbolSize:8,
                data:data.seriesData[6],
                itemStyle: {
                    normal: {
                        color: "#1eb5c9"
                    }
                },
            },{
                name:data.legendData[7],
                type:'line',
                symbolSize:8,
                data:data.seriesData[7],
                itemStyle: {
                    normal: {
                        color: "#734be9"
                    }
                }
            },
            {
                name:data.legendData[8],
                type:'line',
                symbolSize:8,
                data:data.seriesData[8],
                itemStyle: {
                    normal: {
                        color: '#0abdff'
                    }
                },
            },{
                name:data.legendData[9],
                type:'line',
                symbolSize:8,
                data:data.seriesData[9],
                itemStyle: {
                    normal: {
                        color: "#f0a629"
                    }
                },
            },{
                name:data.legendData[10],
                type:'line',
                symbolSize:8,
                data:data.seriesData[10],
                itemStyle: {
                    normal: {
                        color: "#f54fc5"
                    }
                },
            },{
                name:data.legendData[11],
                type:'line',
                symbolSize:8,
                data:data.seriesData[11],
                itemStyle: {
                    normal: {
                        color: "#1eb5c9"
                    }
                },
            },{
                name:data.legendData[12],
                type:'line',
                symbolSize:8,
                data:data.seriesData[12],
                itemStyle: {
                    normal: {
                        color: "#734be9"
                    }
                }
            },{
                name:data.legendData[13],
                type:'line',
                symbolSize:8,
                data:data.seriesData[13],
                itemStyle: {
                    normal: {
                        color: '#3b7ef4'
                    }
                },
            }
        ]
    };
    /* option3.series.forEach(function (v, i) {
        v.data = null;
    })


    var newOption = {

        series: [{
            name: '进口价格',
            data: [70, 110, 145, 165, 135],
        }, {
            name: '出口价格',
            data: [30, 60, 80, 125, 70],
        }, {
            name: '批发价格',
            data: [50, 70, 80, 90, 70],
        }]
    }
    $.extend(true, option3, newOption); */

    breedNum.setOption(option3);

}
//  价格行情
var totles, curPage = 0, pageSize = 8;
initPrice(curPage);
function initPrice(curPage) {
    var params = {
        "page": {
            "pageIndex": curPage + 1,
            "pageSize": pageSize
        }
    }
    ajaxGet('mango/extend/swagger/da/daMarketPrice/getListByJsonData', params, initPriceFn);
}
//  价格行情回调
function initPriceFn(res) {
    // console.log(res)
    if (res.isSuccess){
        var tableArr = [];  //  储存所有价格
        var totalCount = res.totalCount;
        res.data.forEach(function(v,i){
            tableArr[i] = v.averagePriceUnit
        });
        initTemplate({
            data: res,
            idScript: 'tplScr',
            idHtml: 'tempHtml',
        });
        //  初始化比例bar
        $('.tableList .react-item').each(function (i, v) {
            var curNum = $(v).data('num');
            var percentWid = percentResult(tableArr, curNum);
            $(this).animate({
                width: percentWid
            }, 500);
        })
        page(totalCount, curPage)
    }
}

//  每个价格所占最大值的百分数
function percentResult(arr, cur) {
    var fullNum = (arr.sort(function (x, y) { return y - x; }))[0];
    return Math.round(cur / fullNum * 100) + '%';
}

/**
 * 分页调用
 * @author  rcz
 * @param totles @type { Number }     总条数
 * @param curPage @type { Number }   当前页
 */
function page(totles, curPage) {
    $("#page").pagination(totles, {
        current_page: curPage,          //  当前选中的页面	可选参数，默认是0，表示第1页
        link_to: 'javascript:;',        //  分页的链接 字符串，可选参数，默认是"#"
        num_edge_entries: 0,            //  边缘页数
        num_display_entries: 4,         //  主体页数
        ellipse_text: '...',          //  省略的页数用什么文字表示 可选字符串参数，默认是"…"
        first_text: '首页',
        last_text: '尾页',
        callback: pageselectCallback,   //  回调函数	默认无执行效果
        totleNum: totles,
        items_per_page: pageSize        //  每页显示多少项
    });
    //  隐藏共多少页
    $("#page b").eq(0).hide();
    $("#page b").eq(1).hide();
    $("#page b").eq(2).hide();
    $("#search").on('click',function () {
        return false;
    })
}
//  分页回调
function pageselectCallback(page_index, jq) {
    // console.log(page_index)  //  所点击索引，0为第1页
    // console.log(jq)
    curPage = page_index;

    //  重新请求列表
    initPrice(page_index);
}
