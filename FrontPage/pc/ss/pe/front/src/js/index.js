//广告图
function getimgBanner() {
    var submitData = {
        "entityRelated": {
            "domainId": "032769fd7e376c04fb13c66419a72598",
            "spaceCode": "2"
        }
    };
    ajaxGet("mango/extend/swagger/pi/piAdvertising/getListByCode", submitData, rendomImg);
}
getimgBanner();
//渲染广告位
function rendomImg(val) {
    console.log(val)
    /* if (val.isSuccess) {
       var params = {
           idHtml: 'banner',
           idScript: 'bannerSrc',
           data:{
              data: val.data
           }
       };
       initTemplate(params);
       //  轮播图
       initBanner()
    } */

    var html = '';
    if (val.isSuccess) {
        var height = val.data.height ? val.data.height : '500px',width = val.data.width ? val.data.width : '100%';
        var imgTitle = val.data.title ? val.data.title : '';
        if (val.data.resourcePathArray.length>0) {
            for (var i = 0; i < val.data.resourcePathArray.length; i++) {
                html += '<div class="swiper-slide">'
                    //+ '<a href="###">'
                    + '<img width="'+ width +'" height="'+ height +'" src="' + val.data.resourcePathArray[i] + '"  title="'+ imgTitle +'" alt="">'
                   // + '</a>'
                    + '</div>';
            }
        }else{
                html+='<div class="swiper-slide red-slide">'
                    +    '<img width="100%"  height="500" src="../../img/banner02.jpg" alt="">'
                    +   '</div>'
                    +   '<div class="swiper-slide orange-slide">'
                    +       '<img width="100%"  height="500" src="../../img/banner03.jpg" alt="">'
                    +   '</div>'
                    +   '<div class="swiper-slide green-slide">'
                    +        '<img width="100%"  height="500" src="../../img/banner01.jpg" alt="">'
                    +    '</div>'
        }

        $("#banner").html(html)
        initBanner()
    }else{
        $("#banner").html('<div class="noData">暂无数据</div>')
    }
}
$("#banner").click(function(){
    getimgBanner();
})
//  轮播图swiper封装
function initBanner(){
     var mySwiper = new Swiper('.swiper-container', {
         pagination: '.pagination',
         autoplay: 2000,
         speed: 1000,
         paginationClickable: true,
         loop: true,
         onSlideChangeStart: function (swiper) {
             var wid = $('.pagination').width()
         }
     })
}

//  数说华芒
initPlant();
function initPlant() {
    var params = {
        "entityRelated": {
            "regionId": "530723",
            "breed": "0"
        },
        "orderList": [
            {
                "columnName": "",
                "isASC": true
            }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    }
    ajaxGet('mango/extend/swagger/da/daGrowYield/getMangoGrowDetails', params, initPlantFn);

}
//  种植回调
function initPlantFn(res) {
    //console.log(res)
    if (res.isSuccess) {
        initTemplate({
            data: res.data,
            idScript: 'tpl',
            idHtml: 'tendency',
        });
    }
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

/**
 * 每个价格所占最大值的百分数
 * @author  rcz
 * @param arr @type { Array }       存储所有价格数据
 * @param cur @type { Number }      当前项价格
 * @return  { String }              百分数
 */
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
        ellipse_text: '...',            //  省略的页数用什么文字表示 可选字符串参数，默认是"…"
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
    //  点击确定按钮是清除a标签默认事件
    $('#search').on('click',function () {
        return false;
    });
}
//  分页回调
function pageselectCallback(page_index, jq) {
    // console.log(page_index)  //  所点击索引，0为第1页
    // console.log(jq)
    curPage = page_index;

    //  重新请求列表
    initPrice(page_index);
}

//  芒果分布
initMarket()
function initMarket() {
    ajaxGet('mango/extend/swagger/da/daEnterpriseDistribution/getListByJsonData', {}, initMarketFn);
}
//  芒果分布回调
function initMarketFn(res) {
     console.log(res)
    var seriesData = [];
    res.data.wholesaleMarket.forEach(function (v,i) {
        seriesData[i] = {
            name: v.regionText,
            value: [v.longitude, v.latitude, v.content]
        }
    })
    var params = {
        el: '#chinaMap',
        seriesData: seriesData
    };

    //  初始化中国地图
    initChina({
        el: '#chinaMap',
        seriesData: seriesData
    });
}
myChart1();
function myChart1() {
    var params = {
        "entityRelated": {
            "regionId": "530723",
            "breed": "0",
            "startTime": "",
            "endTime": "",
            "dimension": "1",
            "relationId": "01"
        },
        "orderList": [
            { "columnName": "", "isASC": true }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    ajaxGet('mango/extend/swagger/da/daGrowYield/getGrowDataAndProportion', params, myChart1CallBack);

}
function myChart1CallBack(res) {
     //console.log(res)
    var params = {
        el: '.mychart1',
        xData: ['',  '', ' ', '', '', ''],
        // yData: [120, 140, 90, 110, 120, 110, 120, 130, 140, 100],
        seriesData: [0, 0, 0, 0, 0, 0]
    }
    if(res.isSuccess){
        var echartData = res.data.product;
        var params = {
            el: '.mychart1',
            xData: echartData.times,
            // yData: [120, 140, 90, 110, 120, 110, 120, 130, 140, 100],
            seriesData: echartData.data
        }
    }
    barFn(params)
}
//  华坪县芒果价格走势
myChart2();
function myChart2() {
    var params = {
        "entityRelated": {
            "regionId": "530723",
            // "startTime": new Date().getFullYear() + '-01',       //  当年一月开始
            // "endTime": new Date().getFullYear() + '-' + (new Date().getMonth() + 1), //  当年目前月份结束
            "breed": "0",
            "priceType": 1,
            "priceUnit": null,
            "dimension": 1,
            "relationId": "03"
        },
        "orderList": [
            {
                "columnName": "",
                "isASC": true
            }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    }
    ajaxGet('mango/extend/swagger/da/daMarketPrice/getTimesPriceData', params, myChart2CallBack);
}
//  华坪县芒果价格走势回调
function myChart2CallBack(res) {
     //console.log(res)
    var data = {
        el: '.mychart2',
        xAxisData: [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
        seriesData: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    }
    if (res.isSuccess){
            data.xAxisData = res.data.times,
            data.seriesData = res.data.priceDatas
    }
    lineFn(data)
}
//  全国芒果批发市场价格监测
myChart3();
function myChart3() {
    var params = {
        "entityRelated": {
            "regionId": "530723",
            "breed": "0"
        },
        "orderList": [
            { "columnName": "", "isASC": true }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    ajaxGet('mango/extend/swagger/da/daMarketPrice/getPriceDataAndProportion', params, myChart3CallBack);
}
//  全国芒果批发市场价格监测回调
function myChart3CallBack(res) {
    //console.log(res)
    if (res.isSuccess) {
        var data = {
            el: '.mychart3',
            xAxisData: res.data.time || ['2012', '2013', '2014', '2015', '2016', '2017'],
            seriesData: [
                res.data.data || [2, 3, 4, 2, 3, 9],
                res.data.proportion || [2, 3, 4, 2, 3, 9]
            ]
        }
        barLineFn(data)
    }
}
//  世界地图
worldMapInit()
function worldMapInit(){
    var params = {
        "entityRelated": {
            "queryPattern": "1",
            "queryType": "1",
            "queryTime": "2016"
        },
        "orderList": [
            { "columnName": "sale_amount", "isASC": true }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    }
    ajaxGet('mango/extend/swagger/da/daImportExport/getTradeRank', params, worldMapInitFn);
}
function worldMapInitFn(res) {
    //console.log(res)
    if (res.isSuccess) {
        var seriesData = [];
        res.data.forEach(function (v, i) {
            seriesData[i] = {
                name: v.country,
                value: v.value,
                unit: v.unit
            }
        })
        var params = {
            el: '.mychart4',
            seriesData: seriesData
        }
        worldFn(params)
    }

}

//首页鸟瞰华坪图片轮播数据
function getViewBird() {

    ajaxGet("mango/extend/swagger/pe/peAerialView/getHomeAerialList", {}, ViewBirdFn);
    //首页鸟瞰华坪图片轮播页面
    function ViewBirdFn(val) {
        var imgArr = [];
        var imgPsuh,imgShift;
        if (val.isSuccess){
            val.data.forEach(function (v,i) {
                if (i == (val.data.length-1)){
                    imgShift = v.path
                }
                if (i == 0){
                    imgPsuh = v.path
                }

                imgArr[i] = v.path
            });
            //  swiper前后两个dom图片路径
            imgArr.unshift(imgShift);
            imgArr.push(imgPsuh);
            var params = {
                idHtml: 'viewBird',
                idScript: 'viewBirdSrc',
                data: {
                    "viewBirdData": val.data
                }
            };
            //引用handleBars模板
            initTemplate(params);
            //  首页鸟瞰华坪轮播图
            var mySwiper = new Swiper('.swiper-container1', {
                autoplay: 2000,
                speed: 1000,
                paginationClickable: true,
                loop: true,
                keyboardControl: true,
                grabCursor: true,
                onSwiperCreated: function (swiper) {
                    //  兼容低级ie
                    if (!(navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion.split(";")[1].replace(/[ ]/g, "").replace("MSIE", "")) < 10)) {
                        var div = $(".panorama");
                        // console.log(div)
                        div.each(function (i, v) {
                            // console.log(v)
                            // var imgArr = [
                            //     "../../img/IMG_1032.PNG",
                            //     "../../img/IMG_1032.PNG",
                            //     "../../img/IMG_1032.PNG",
                            //     "../../img/IMG_1032.PNG",
                            //     "../../img/IMG_1032.PNG",
                            //     "../../img/IMG_1032.PNG",
                            //     "../../img/IMG_1032.PNG",
                            //     "../../img/IMG_1032.PNG"
                            // ]
                            // console.log(imgArr)
                            //  调用全景图方法
                            initPSV(v, imgArr[i]);
                        })
                    } else {
                        alert("您的浏览器版本过低，请下载IE10以上版本");
                    }

                }
            })

            $('.arrow-left').on('click', function (e) {
                e.preventDefault()
                mySwiper.swipePrev()
            })
            $('.arrow-right').on('click', function (e) {
                e.preventDefault()
                mySwiper.swipeNext()
            })
        }
    }
}
getViewBird();

/**
 * 全景图初始化
 * @author  rcz
 * @param el @type {String}     DOM元素
 * @param img @type {String}   图片地址
 */

function initPSV(el, img) {
    var PSV = new PhotoSphereViewer({
        // Path to the panorama
        panorama: img,
        // autoload:false,
        // Container
        container: el,
        // 可选，默认值为2000，全景图在time_anim毫秒后会自动进行动画。（设置为false禁用它）
        time_anim: 10000,
        //  可选，默认值为null，在加载时显示的图片的路径。
        // loading_img: "../../img/IMG_1032.PNG",
        // Display the navigation bar
        navbar: true,
        // Resize the panorama
        size: {
            width: '100%',
            height: '100%'
        }
    });
}
