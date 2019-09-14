// 贸易国家数据
function tradeCountry(url, params){
    ajaxGet(url, params, responseFn);
}
function responseFn(res){
    console.log(res)
    if (res.isSuccess) {
        var nationsBarData = {
            yAxisData:[],
            seriesData:[],
            maxData: [],
        };
        var newData = res.data;
        for (var i=0;i<newData.length;i++) {
            if(newData[i].country){
                nationsBarData.yAxisData.push((parseInt(i)+1)+'.'+newData[i].country);
                nationsBarData.seriesData.push(newData[i].value);
                nationsBarData.maxData.push((newData[0].value)*1.5);
            }
        };
        /** 贸易国家柱图 */
        nationsBar(nationsBarData)

        /** 贸易国家 地图 */

        var seriesData = [];
        res.data.forEach(function (v, i) {
            seriesData[i] = {
                name: v.country,
                value: v.value,
                unit: v.unit,
                Pattern: v.Pattern,
                Type: v.Type
            }
        })
        var params = {
            seriesData: seriesData
        }
        nationsMap(params)
    }else{
       var nationsBarData = {
            yAxisData:[],
            seriesData:[],
            maxData: [],
        }; 
        nationsBar(nationsBarData);
        var params = {
            seriesData: [{
                name: '',
                value: '',
                unit: '',
                Pattern: '',
                Type: ''
            }]
        }
        nationsMap(params)
        
    }
}

// 贸易规模数据
function tradeScale(url, sParams){
    ajaxGet(url, sParams, responseFnScale);
}
function responseFnScale(res){
    if (res.isSuccess) {
        var data1=res.data;
        var xData=[];
        var sData=[];
        data1.forEach(function(item,index){
            //console.log(item)
            xData.push(item.time);
            sData.push(item.value)
        })
        var tradeScaleBarData ={
            xAxisData:xData,
            seriesData:sData,
            Pattern: res.data[0].Pattern,
            Type: res.data[0].Type
        }
        //console.log(tradeScaleBarData)
        //贸易规模柱图
        tradeScaleBar(tradeScaleBarData)
    }
}

// 进出口价格模数据
function tradePrice(pUrl, pParams){
    ajaxGet(pUrl, pParams, responseFnPrice);
}
function responseFnPrice(res){

    if (res.isSuccess) {
        var data1=res.data;
        var xData=[];
        var pData=[];
        data1.forEach(function(item,index){
            xData.push(item.time);
            pData.push(item.value)
        })
        var tradePriceLineData ={
            xAxisData:xData,
            seriesData:pData,
            Pattern: res.data[0].Pattern,
        }
        //贸易规模柱图
        tradePriceLine(tradePriceLineData)
    }else{
        $('#tradePriceLine').html('<div class="noData">暂无数据</div>')
    }
}
$(function () {
    // 贸易国家data
    var params = {
        "entityRelated" : {
            "queryPattern" : '1', //进出口类型 1：对中国出口（进口） 2：自中国进口（出口）
            "queryType" : '1',    //查询类型  1：按金额，2：按数量。
            "queryTime" : '2016', //查询日期  2018
        },
        "orderList" : [ {
            "columnName" : "sale_amount",
            "isASC" : true
        } ],
        "page" : {
            "pageIndex" : 1, 	//页数 默认1即可
            "pageSize" : 10		//每页条数 前十条填10即可
        }
    };
    //贸易国家 进出口  数量 金额
    var tab1=new Tab('tradeNationsType', function (res) {
        params.entityRelated.queryPattern = ($(res).index()+1);
        //渲染地图和前十柱图
         tradeCountry(url, params);
    });
    var tab2=new Tab('tradeNationsNum', function (res) {
        params.entityRelated.queryType = ($(res).index()+1);
        //渲染地图和前十柱图
         tradeCountry(url, params);
    });

    // 贸易规模data
    var sParams = {
        "entityRelated" : {
            "queryPattern" : "1", //进出口类型 1：进口 2：出口。
            "queryType" : "1",    //查询类型     1：按金额，2：按数量。
            "queryTimes" : "'2012','2013','2014','2015','2016','2017','2018'", //查询日期     '2015','2016','2017','2018'
        },
        "orderList" : [ {
            "columnName" : "sale_amount",
            "isASC" : true
        } ],
        "page" : {
            "pageIndex" : 1,    //页数 默认1即可
            "pageSize" : 10     //每页条数 前十条填10即可
        }
    }

    //贸易规模 进出口  数量 金额
    var tab3=new Tab('tradeScaleType', function (res) {
        sParams.entityRelated.queryPattern = ($(res).index()+1);
        //渲染规模柱图
        tradeScale(sUrl,sParams)
    });
    var tab4=new Tab('tradeScaleNum', function (res) {
        sParams.entityRelated.queryType = ($(res).index()+1);
        //渲染规模柱图
        tradeScale(sUrl,sParams)
    });


    // 进出口价格data
    var pParams = {
        "entityRelated" : {
            "queryPattern" : "1", //进出口类型 1：进口 2：出口。
            "queryTimes" : "'2012','2013','2014','2015','2016','2017','2018'", //查询日期     '2015','2016','2017','2018'
        },
        "orderList" : [ {
            "columnName" : "sale_amount",
            "isASC" : true
        } ],
        "page" : {
            "pageIndex" : 1,    //页数 默认1即可
            "pageSize" : 10     //每页条数 前十条填10即可
        }
    }
    //进出口价格 进出口  数量 金额
    var tab5=new Tab('tradePriceType',function(res){
        pParams.entityRelated.queryPattern = ($(res).index()+1)
        //渲染进出口价格折线图
        tradePrice(pUrl, pParams)
    });

    /** 日历插件处理部分  */
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //贸易国家
        laydate.render({
            elem: '#startWorld',type: 'year',range: false,value: params.entityRelated.queryTime,done: function (value, date, endDate) {
                params.entityRelated.queryTime = value;
                console.log(params)
                //渲染地图和前十柱图
                 tradeCountry(url, params);
            }
        });
        //贸易规模
        laydate.render({
            elem: '#startScale',type: 'year',range: true,value: '2012 - 2018',done: function(value, date, endDate) {
                sParams.entityRelated.queryTimes = "";
                var sTime = value.split('-');
                var cTime = parseInt(sTime[1])-parseInt(sTime[0]);
                var mTime = parseInt(sTime[0]);
                sParams.entityRelated.queryTimes = parseInt(sTime[0])+ ',';
                for (var i=0;i<cTime;i++) {
                    mTime ++;
                    sParams.entityRelated.queryTimes += mTime +',';
                }
                sParams.entityRelated.queryTimes = sParams.entityRelated.queryTimes.slice(0,sParams.entityRelated.queryTimes.length-1);
                    //渲染规模柱图
                    tradeScale(sUrl,sParams)
            }
        });
        //进出口价格
        laydate.render({
            elem: '#startPrice',type: 'year',range: true,value: '2012 - 2018',done: function(value, date, endDate) {
                pParams.entityRelated.queryTimes = "";
                var sTime = value.split('-');
                var cTime = parseInt(sTime[1])-parseInt(sTime[0]);
                var mTime = parseInt(sTime[0]);
                pParams.entityRelated.queryTimes = parseInt(sTime[0])+ ',';
                for (var i=0;i<cTime;i++) {
                    mTime ++;
                    pParams.entityRelated.queryTimes += mTime +',';
                }
                pParams.entityRelated.queryTimes = pParams.entityRelated.queryTimes.slice(0,pParams.entityRelated.queryTimes.length-1);
                    //渲染规模柱图
                     tradePrice(pUrl, pParams)
            }
        });
    });
    //贸易国家前十排名柱图和地图的接口
    var url = 'mango/extend/swagger/da/daImportExport/getTradeRank';
    tradeCountry(url, params);
    //贸易规模柱图接口
    var sUrl = 'mango/extend/swagger/da/daImportExport/getTradeScaleData';
    tradeScale(sUrl, sParams)
    //进出口价格接口
    var pUrl = 'mango/extend/swagger/da/daImportExport/getTradePriceData';
    tradePrice(pUrl, pParams)
});

//echarts配置图表部分
/** 贸易国家柱图 */

function nationsBar(data){
    var nationsBar = echarts.init(document.getElementById('nationsBar'));
    /*function frontThree(index) {
        if (index < 3){
          var color = ['#fb954b','#fbc74b'];
        }else{
          var color = ['#2873ef','#8adbfd'];
        }
        return new echarts.graphic.LinearGradient(0, 0, 1, 0,[{offset: 0, color: color[0]},{offset: 1, color: color[1]}]);
      }*/
    var option1 = {
        backgroundColor: '#fff',
        grid: {
          left: 20,
          right: 30,
          top:30,
          bottom:0,
          containLabel: true
        },
        title: {
            text: '前十贸易国',
            left:"left"
        },
        tooltip: {
          show:true,
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        xAxis: {
          show: false,
          type: 'value',
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          splitLine:{
            show:false
          },
        },
        yAxis: {
          type: 'category',
          splitLine: {show: false},
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          offset: 70,
          axisLabel:{
            align:'left',
            formatter: function (value, index) {
                //console.log(value + value.length)
                if(value.length>7){
                   var value = value.substring(0,6);
                   return value+'...';
                }else{
                    return value;
                }                
            }
          },
          nameTextStyle: {
            fontSize: 12
          },
          inverse:true,
          data:data.yAxisData
        },
        series: [
          {
            type: 'bar',
            z:1,
            silent:true,
            itemStyle: {
              normal: {
                color: '#f2f5fb',
                barBorderRadius: [20, 20, 20, 20],}
            },
            barGap:'-100%',
            barWidth: 14,
            barCategoryGap:'1%',
            data: data.maxData,
            animation: false,
            tooltip:{
              show:false
            }
          },
          {
            name: '',
            type: 'bar',
            z:2,
            data: data.seriesData,
            barCategoryGap:'1%',
            barWidth: 12,
            smooth: true,
            label: {
              normal: {
                show: true,
                position: 'right',
                textStyle: {
                  color: '#5e8d18',
                  fontSize: 14
                }
              }
            },
            itemStyle: {
              normal: {
                color: '#86cb20',
                barBorderRadius: [20, 20, 20, 20]
              },
              backgroundColor:'#f3f2f2',
              grid: {
                left: '0%',
                right:'10%',
                containLabel: true
              }
            }
          }
        ]
      };

    nationsBar.setOption(option1);
}

/** 贸易国家 地图*/
function nationsMap(data){
    var arrData = data.seriesData;
    var maxMapData = Math.max.apply(Math, arrData.map(function(o) {return o.value}))
    
    var nationsMap = echarts.init(document.getElementById('nationsMap'));
    var option2 = {
        title:{
            text:'芒果进出口贸易情况',
            textStyle:{
                fontSize:24
            },
            top: 0,
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
                    var str = '<div style="padding:5px;margin:0px;">' + params.name + "<br>" + params.data.Pattern+"： " + params.value+params.data.unit + "</div>";
                    return str
                }
            }
        },
        visualMap: {
            min: 0,
            max: maxMapData,
            left: 'right',
            top: 'bottom',
            text: ['高','低'],
            calculable: true,
            color:['#3f6901','#b6e176'],
            orient:'horizontal'
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
        nationsMap.setOption(option2);
}




/*** 贸易规模柱图  **/
function tradeScaleBar(data){
   // console.log(data)
    var flag = data.Type == '金额' ? '单位：10000美元' : '单位：吨';
    var titles = data.Type == '金额' ? '中国芒果'+ data.Pattern +'金额' : '中国芒果'+ data.Pattern +'量'
    var tradeScaleBar = echarts.init(document.getElementById('tradeScaleBar'));
    var echartOpt = {
        fz: '16',
        color: '#dbfcff'
    };
    var axisLabel = {
      textStyle: {
        color: '#858585',
        fontSize: 16,
        fontWeight: 'lighter'
      },
      margin: 18
    };
    option3 = {
         title:{
            text:titles,
            left:'center',
            top:'bottom',
            textStyle:{
                color:'#333',
                fontSize:16
            }
        },
        tooltip : {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                },
                formatter: function(params) {
                    var str = params[0].name + '<br>' + data.Pattern + data.Type + '：';
                    params.forEach(function(v, i) {
                        str += params[0].data;
                    });
                    return str
                },
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
                emphasis:{
                    textStyle:{
                        color:"#ff3b00"
                    }
                }
            },
            grid: {
                left: '1%',
                right: '1%',
                bottom: '10%',
                top:'80',
                containLabel: true
            },
               yAxis: [{
                name: flag,
                nameTextStyle:{
                  fontSize:16,
                  padding: [3, 4, 25, 40]
                },
                type : 'value',
                min:'dataMin',
                axisLine: {show:false},
                axisTick:{show:false},
                axisLabel:{
                    textStyle:{
                        color:"#7d8690"
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
                    color: ['rgba(255,255,255,0)','rgba(237,242,252,0.5)']
                }
            },
                axisLabel:axisLabel
            }],

            xAxis: {
                type : 'category',
                name: '',
                splitLine: {
                   show:false,
                   lineStyle: {
                        color: ['#254495'],
                        type:'dashed'
                    }
                },
                axisTick:{
                    show:false
                },
                axisLabel:axisLabel,
                axisLine:{
                    lineStyle: {
                        color:echartOpt.color,
                        fontSize:echartOpt.fz
                    }
                },
                 data : data.xAxisData,
            },
            series: [
                {
                    name: '中国芒果进口金额',
                    type: 'bar',
                    barWidth : 14,
                    label: {
                        normal: {
                            show: false,
                            position: 'right',
                            formatter: "{c}%"
                        }
                    },
                    itemStyle: {
                        normal: {
                            borderColor:'#ffab04',
                            barBorderRadius: [20, 20, 20, 20],
                            color:'#ffc52a'
                        }

                    },
                    // 顺序 从下向上 传入
                    data:data.seriesData
                }
            ]
        };

    tradeScaleBar.setOption(option3);

}


/*** 进出口价格折线图  **/
function tradePriceLine(data){
        var titles ='中国芒果'+ data.Pattern +'价格'
        var tradePriceLine = echarts.init(document.getElementById('tradePriceLine'));
        var echartOpt = {
            fz: '16',
            color: '#dbfcff'
        };
        var axisLabel = {
          textStyle: {
            color: '#858585',
            fontSize: 16,
            fontWeight: 'lighter'
          },
          margin: 18
        }
        option4 = {
             title:{
                text:titles,
                left:'center',
                top:'bottom',
                textStyle:{
                    color:'#333',
                    fontSize:16
                }
            },
            tooltip : {
             trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function(params) {
                         var str = params[0].name + '<br>中国芒果' + data.Pattern;
                        params.forEach(function(v, i) {
                            str += '价格：' + params[0].data;
                        });
                        return str

                    },
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
                    emphasis:{
                        textStyle:{
                            color:"#ff3b00"
                        }
                    }
                },
                grid: {
                    left: '1%',
                    right: '1%',
                    bottom: '10%',
                    top:'80',
                    containLabel: true
                },
                   yAxis: [{
                    name: "单位：美元/公斤",
                    nameTextStyle:{
                      fontSize:16,
                      padding: [3, 4, 25, 40]
                    },
                    type : 'value',
                    min:'dataMin',
                    axisLine: {show:false},
                    axisTick:{show:false},
                    axisLabel:{
                        textStyle:{
                            color:"#7d8690"
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
                        color: ['rgba(255,255,255,0)','rgba(237,242,252,0.5)']
                    }
                },
                    axisLabel:axisLabel
                }],

                xAxis: {
                    type : 'category',
                    name: '',
                    splitLine: {
                       show:false,
                       lineStyle: {
                            color: ['#254495'],
                            type:'dashed'
                        }
                    },
                    axisTick:{
                        show:false
                    },
                    axisLabel:axisLabel,
                    axisLine:{
                        lineStyle: {
                            color:echartOpt.color,
                            fontSize:echartOpt.fz
                        }
                    },
                     data : data.xAxisData,
                },
                series: [
                    {
                        name: '中国芒果进口价格',
                        type: 'line',
                        smooth:true,
                        barWidth : 14,
                        label: {
                            normal: {
                                show: false,
                                position: 'right',
                                formatter: "{c}%"
                            }
                        },
                        itemStyle: {
                            normal: {
                                color:'#9ccf51'
                            }

                        },
                        // 顺序 从下向上 传入
                        data:data.seriesData
                    }
                ]
            };

        tradePriceLine.setOption(option4);
}













