// 初始化要执行的函数
init();

function init() {
    //市场
    marketNew();
    //需求
    getDemand();
}


//banner 轮播
//autoPlay("swipe-img","swiper",true);

//autoPlay("swipe-brand","controller-box");



//banner轮播部分
function getBanner() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.bazaar,
            "relationId" :	"0103",
            "isRecommend" : "0",//1推荐，0:不推荐
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 6
        }
    };

    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomBanner);
}

//getBanner();

//渲染banner轮播部分页面
/*function rendomBanner(val) {
    //console.log(val)
    var params = {
        idHtml: 'Banner',
        idScript: 'BannerSrc',
        data: {
            "bannerData": val.data
        }
    };
    if (val.data.length > 1) {
        //banner 轮播
        autoPlay("swipe-img", "swiper", true);
    }
    initTemplate(params);

}*/

function rendomBanner(val){
    //console.log(val)
    if(val.isSuccess){
        var html = '';
            var html1 = '';
            for(var i = 0;i<val.data.length;i++){
                //console.log(val.data[i].channel_name)
                if(val.data[i].img_path){
                    html+='<li data-url="'+i+'">'
                        +'<a href="../detiles/detiles.html?channel_id='+val.data[i].channel_id+'&id='+val.data[i].content_id+'" class="skip-box">'
                        +'<img src="'+val.data[i].img_path+'" alt="">'
                        +'<div class="swiper-banner-title over-ellipsis">'+val.data[i].title+'</div>'
                        +'</a>'
                        +'</li>';
                }
                else{
                    html+='<li data-url="'+i+'">'
                        +'<a href="../detiles/detiles.html?channel_id='+val.data[i].channel_id+'&id='+val.data[i].content_id+'" class="skip-box">'
                        +'<img src="../../img/zwtp.gif" alt=""></a>'
                    +'</li>';
                }
                if(i==0){
                    html1+='<li class="iconfont icon-dian-copy-copy active"></li>';
                }else{
                    html1+='<li class="iconfont icon-dian-copy-copy"></li>';
                }
            }
            $("#swiper-banner").html(html);
            $("#controller-banner").html(html1);
            if(val.data.length>1){
                autoPlay("swiper-banner","controller-banner",true);
            }
    }else{
        $("#swiper-banner").html("<div class='noData'>暂无查询结果</div>");
    }

}



//BrandSwiper品牌轮播部分
function getBrandSwiper() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.brand,
            "relationId" :	"010304",
            "isRecommend" : "0",//1推荐，0:不推荐
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 3
        }
    };

    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomBrandSwiper);
}
//品牌里图片轮播方法调用
setTimeout(function () {
    getBrandSwiper();
},100)

function rendomBrandSwiper(val){
   // console.log(val)
   if(val.isSuccess){
    var htmlB = '';
        var html1B = '';
        for(var i = 0;i<val.data.length;i++){
                htmlB+='<li data-url="'+i+'">'
                    +'<a href="../detiles/detiles.html?channel_id='+val.data[i].channel_id+'&id='+val.data[i].content_id+'">'
                    +'<img src="'+val.data[i].img_path+'" alt="">'
                    +'</a>'
                    +'</li>';
            if(i==0){
                html1B+='<li class="iconfont icon-dian-copy-copy active"></li>';
            }else{
                html1B+='<li class="iconfont icon-dian-copy-copy"></li>';
            }
        }
        $("#swiper-brand").html(htmlB);
        $("#controller-brand").html(html1B);
        if(val.data.length>1){
            autoPlay("swiper-brand","controller-brand",true);
        }
   }else{
        $("#swiper-brand").html("<div class='noData'>暂无查询结果</div>");
   }

}

//getBrandList品牌文章详情部分
function getBrandList() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.brand,
            "relationId" :	"010304",
            "isRecommend" : "0",//0推荐，1:不推荐
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 5
        }
    };

    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomBrandList);
}
setTimeout(function(){getBrandList();},0)


//渲染品牌文章部分页面
function rendomBrandList(val) {
    //console.log(val)
    var params = {
        idHtml: 'BrandList',
        idScript: 'BrandListSrc',
        data: {
            "BrandListData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#BrandList").html("<div class='noData'>暂无查询结果</div>")
    }

}
//行情数据
function marketNew() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.market,
            "relationId" :	"010302",
            "isRecommend" : "0",//1推荐，0:不推荐
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 3
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomMarket)
}

//渲染行情
function rendomMarket(val) {
    //console.log(val)
    var params = {
        idHtml: 'market',
        idScript: 'marketSrc',
        data: {
            "hotList": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#market").html("<div class='noData'>暂无查询结果</div>")
    }
}

//供求
//服务数据
function getDemand() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.demand,
            "relationId" :	"010303",
            "isRecommend" : "1",//1推荐，0:不推荐
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 3
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomEle)
}

function rendomEle(val) {
    if (val.data.length > 0) {
        var params = {
            idHtml: 'demand',
            idScript: 'demandSrc',
            data: {
                "demandList": val.data
            }
        };
        initTemplate(params);
    } else {
        $("#demand").html("<li class='noData'>暂无查询结果</li>")
    }

}

//  价格行情
var totles, curPage = 0, pageSize = 10;
initPrice(curPage);
function initPrice(curPage) {
    var submitData= {
        "page": {
            "pageIndex": curPage+1,
            "pageSize": pageSize
        }
    }
    var submitDataString = JSON.stringify(submitData);
    ajaxGet('/extend/swagger/da/daMarketPrice/getListByJsonData', submitDataString, initPriceFn);
}
//  价格行情回调
function initPriceFn(res) {
    //console.log(res)
    if (res.isSuccess){
        var tableArr = [];  //  储存所有价格
        var totalCount = res.totalCount;
        res.data.forEach(function(v,i){
            tableArr[i] = v.averagePriceUnit
        });
        initTemplate({
            data: {
                "data": res.data
            },
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
    }else{
        $("#tempHtml").html("<span style='display:inline-block;height:80px;line-height:80px;'>暂无查询结果</span>")
    }

}
//  每个价格所占最大值的百分数
function percentResult(arr,cur) {
    var fullNum = (arr.sort(function (x, y) { return y - x; }))[0];
    return Math.round(cur / fullNum * 100)+'%';
}
/**
 * 分页调用
 * @author  rcz
 * @param totles @type { Number }     总条数
 * @param curPage @type { Number }   当前页
 */
function page(totles, curPage) {
    if (totles<11) return;
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
    $("#search").on('click', function () {
        return false;
    })
}
//  分页回调
function pageselectCallback(page_index, jq) {
    curPage = page_index;
    //  重新请求列表
    initPrice(page_index);
}

//getReferrals推介/企业微网站
function getReferrals() {
    var submitData = {
        "entityRelated": {
            "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
        },
        "page": {
            "pageIndex": 1,
            "pageSize": 2
        }
    };

    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/da/daEnterpriseInfo/getEnterpriseList", submitDataString, rendomeferrals);
}

getReferrals();

function rendomeferrals(val) {
   console.log(val)
    var params = {
        idHtml: 'MicroSiteLists',
        idScript: 'MicroSiteListsSrc',
        data: {
            "MicroSiteListsData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#MicroSiteLists").html("<div class='noData'>暂无查询结果</div>")
    }
}

//芒果微商城鲜果数据
function getMangoMallList() {
    var submitData = {
        "entityRelated": {
            "typeCode": "1", //1鲜果 2加工品
            "isRecommend": "", //是否推荐 0 是  1否
            "maxPrice": "", //最高价
            "minPrice": "" //最低价
        },
        "orderList": [{
            "columnName": "perPriceUnit",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piProductRecommend/getMangoMallList", submitDataString, rendomMangoMallList)
}
    getMangoMallList();
function rendomMangoMallList(val) {
    //console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'MangoMallLists',
            idScript: 'MangoMallListsSrc',
            data: {
                "MangoMallListsData": val.data
            }
        };
        initTemplate(params);
        autoPlay("stroe-top-swipper", "", true);
    } else {
        $("#MangoMallList").html("<li class='noData'>暂无查询结果</li>")
    }

}
//芒果微商城加工品数据
function getMangoMake() {
    var submitData = {
        "entityRelated": {
            "typeCode": "2", //1鲜果 2加工品
            "isRecommend": "", //是否推荐 0 是  1否
            "maxPrice": "", //最高价
            "minPrice": "" //最低价
        },
        "orderList": [{
            "columnName": "perPriceUnit",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piProductRecommend/getMangoMallList", submitDataString, rendomMangoMake)
}
getMangoMake();
function rendomMangoMake(val) {
    console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'MangoMake',
            idScript: 'MangoMakeSrc',
            data: {
                "MangoMakeData": val.data
            }
        };
        initTemplate(params);
        autoPlay("stroe-bottom-swipper", "", true);
    } else {
        $("#MangoMake").html("<li class='noData'>暂无查询结果</li>")
    }

}






