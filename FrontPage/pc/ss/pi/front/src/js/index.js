// 初始化要执行的函数
init();

function init() {
	/* $("a.l1").click(function(){
		window.open("http://www.baidu.com");
	});
	$("a.r1").click(function(){
		window.open("http://192.168.200.193:8081/pc/ss/pi/front/dist/html/index/index.html");
	});
	$("a.l2").click(function(){
		window.open("http://192.168.200.193:60080/cos/sy/index.html");
	});
	$("a.r2").click(function(){
		window.open("http://www.baidu.com");
	}); */
    //互动跳转
    $(".specialist-wrapper").on("click", "li", function () {
        location.href = "#";
    })
    //视听图说
    var param = {
        hoverDom: 'tab-a',
        elementDom: 'market-banner',
        parentDom: 'market-banner-wrapper'
    }
    fadeAuto(param);
    //banner轮播


    //供求
    getDemand();
    //热点
    hotNew();
    //行情
    marketNew();
    //服务
    getServe();
    //政策
    getPolicy();
    //品牌
    getBrand();
    //视
    getSee();
    //听
    getHear();
    //图
    getImage();
    //说
    getSay();
    //首页banner
    getBanner();
    $(".market-banner").click(function () {
        var content_id = $(this).find("p").data("urlid");
        var types = $(".tab-a").eq($(this).index()).text();
        if (content_id && types) {
            location.href = "../detiles/detiles.html?type=" + types + "&id=" + content_id;
        }

    })
}

/*params={
    idHtml: 'ents',
    idScript: 'tEnts',
    data:null
}*/

//首页banner
function getBanner() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.home,
            "relationId": "01",
            "isRecommend": 0,//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomBanner)
}

function rendomBanner(val) {
    //console.log(val)
    var html = '';
    var html1 = '';
    var htmlTitle ='';
    for (var i = 0; i < val.data.length; i++) {
        //console.log(val.data[i].channel_name)
        if (val.data[i].img_path) {
//标题部分
            htmlTitle += '<div data-url="' + i + '" class="title-warp">'
                +'<h2 class="banner-title"><a href="../detiles/detiles.html?channel_id=' + val.data[0].channel_id + '&id=' + val.data[0].content_id + '">'+val.data[0].title
                +'</a></h2><h4 class="banner-info"><span><a href="../detiles/detiles.html?channel_id=' + val.data[1].channel_id + '&id=' + val.data[1].content_id + '">'+val.data[1].title
                +'</a></span><span><a href="../detiles/detiles.html?channel_id=' + val.data[2].channel_id + '&id=' + val.data[2].content_id + '">'+val.data[2].title
                +'</a></span><span class="noBorder"><a href="../detiles/detiles.html?channel_id=' + val.data[3].channel_id + '&id=' + val.data[3].content_id + '">'+val.data[3].title
                +'</a></span></h4>'+ '</a>'+ '</div>';
//banner轮播部分
            html += '<li data-url="' + i + '">'
                + '<a href="../detiles/detiles.html?channel_id=' + val.data[i].channel_id + '&id=' + val.data[i].content_id + '" class="skip-box">'
                + '<img src="' + val.data[i].img_path + '" alt="">'
                + '<div class="swiper-banner-title over-ellipsis">' + val.data[i].title + '</div>'
               /* + '</a>'*/
                + '</li>';
        } else {
            html += '<li data-url="' + i + '">'
                + '<a href="../detiles/detiles.html?channel_id=' + val.data[i].channel_id + '&id=' + val.data[i].content_id + '" class="skip-box">'
                + '<img src="../../img/zwtp.gif" alt="">'
                + '<div class="swiper-banner-title over-ellipsis">' + val.data[i].title + '</div>'
            '</a>'
            + '</li>';
        }
        if (i == 0) {
            html1 += '<li class="iconfont icon-dian-copy-copy active"></li>';
        } else {
            html1 += '<li class="iconfont icon-dian-copy-copy"></li>';
        }
    }

    $(".title-containeer").html(htmlTitle);
    $(".swiper-banner").html(html);
    $("#controller-banner").html(html1);
    if (val.data.length > 1) {
        autoPlay("swiper-banner", "controller-banner", true);
    }
}

//供求
//服务数据
function getDemand() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.demand,
            "relationId": "010303",
            "isRecommend": 0,//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomEle)
}

function rendomEle(val) {
    //console.log(val)
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

//热点数据
function hotNew() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.hotNew,
            "relationId": "0101",
            "isRecommend": 0,//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomHotnew)
}

//渲染热点
function rendomHotnew(val) {
    var params = {
        idHtml: 'hotNew',
        idScript: 'hotNewSrc',
        data: {
            "hotList": val.data
        }
    };
    initTemplate(params);
}

//行情数据
function marketNew() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.market,
            "isRecommend": 0,
            "relationId": "010302",
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 8
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomMarket)
}

//渲染行情
function rendomMarket(val) {
    var params = {
        idHtml: 'market',
        idScript: 'marketSrc',
        data: {
            "marketList": val.data,
            "channel_id": val.data[0].channel_id
        }
    };
    initTemplate(params);
}

//服务列表数据
function getServe() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.serve,
            "isRecommend": 0,
            "relationId": "0102",
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 4
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomServe)
}

//渲染服务列表
function rendomServe(val) {
    if (val.data.length > 0) {
        var params = {
            idHtml: 'serve',
            idScript: 'serveSrc',
            data: {
                "ServeList": val.data,
                "channel_id": val.data[0].channel_id
            }
        };
        initTemplate(params);
    } else {
        var params = {
            idHtml: 'serve',
            idScript: 'serveSrc',
            data: {
                "ServeList": val.data
            }
        };
        initTemplate(params);
        $("#serve ul").html("<li class='noData'>暂无查询结果</li>")
    }

}

//政策数据
function getPolicy() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.policy,
            "isRecommend": 0,
            "relationId": "010201",
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 2
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomPolicy)
}

//渲染政策
function rendomPolicy(val) {
    if (val.data.length > 0) {
        var params = {
            idHtml: 'policy',
            idScript: 'policySrc',
            data: {
                "hotList": val.data,
                "channel_id": val.data[0].channel_id
            }
        };
        initTemplate(params);
    } else {
        var params = {
            idHtml: 'policy',
            idScript: 'policySrc',
            data: {
                "hotList": val.data
            }
        };
        initTemplate(params);
        $("#policy ul").html("<li class='noData'>暂无查询结果</li>")
    }
}

//品牌数据
function getBrand() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.brand,
            "relationId": "010304",
            "isRecommend": 0,//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomBrand)
}

//渲染品牌
function rendomBrand(val) {
    if (val.data.length > 0) {
        var params = {
            idHtml: 'brand',
            idScript: 'brandSrc',
            data: {
                "brandList": val.data,
                "channel_id": val.data[0].channel_id,
                "detiles": ''
            }
        };
        initTemplate(params);
    } else {
        $("#serve ul").html("<li class='noData'>暂无查询结果</li>")
    }
    //品牌轮播
    autoPlay("brand", "controller-brand");
}

//视数据
function getSee() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.home,
            "typeId": typeData.see,
            "isRecommend": 0,
            "relationId": "01",

        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 1
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomSee)
}

//渲染视
function rendomSee(val) {
    /*if(val.data.length>0){*/
    var params = {
        idHtml: 'see',
        idScript: 'seeSrc',
        data: {
            "hotList": val.data
        }
    };
    initTemplate(params);
    /*}*/
}

//听数据
function getHear() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.home,
            "typeId": typeData.hear,
            "isRecommend": 0,
            "relationId": "01",
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 1
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomHear)
}

//渲染听
function rendomHear(val) {
    /*if(val.data.length>0){*/
    var params = {
        idHtml: 'hear',
        idScript: 'hearSrc',
        data: {
            "hotList": val.data
        }
    };
    initTemplate(params);
    /*}*/
}

//图数据
function getImage() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.home,
            "typeId": typeData.image,
            "isRecommend": 0,
            "relationId": "01",
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 1
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomImage)
}

//渲染图
function rendomImage(val) {
    /*if(val.data.length>0){*/
    var params = {
        idHtml: 'image',
        idScript: 'imageSrc',
        data: {
            "hotList": val.data
        }
    };
    initTemplate(params);
    /*}*/
}

//说数据
function getSay() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.home,
            "typeId": typeData.say,
            "isRecommend": 0,
            "relationId": "01",
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 1
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomSay)
}

//渲染说
function rendomSay(val) {
    //console.log(val)
    var params = {
        idHtml: 'say',
        idScript: 'saySrc',
        data: {
            "hotList": val.data
        }
    };
    initTemplate(params);

}

//首页搜索溯源码的代码
$("#searchCode input").click(function () {
    this.value = '';
});

$('#myModalIndex .close').click(function(){
    $('#myModalIndex').hide();
})
//跳转搜索页面
$("#searchCode .search-button").click(function () {
    var value = $("#codeWord").val();
    var newUrl = URLSYip + "/sy/traceInfoByTraceabilityCode/getCodeSimpleInfo?traceabilityCode="+value;
    if (value == '' || value == '请输入20位溯源码' ) {
        $('#myModalIndex').show();
    } else {
        //location.href = "../searching/searching_index.html?val=" + value;
        //window.open("http://192.168.200.193:60080/cos/sy/index.html?channel_id=dd7bb191cc357bdf6a72c1e5c3f0ced5#/0/search/pc");
        $.get(newUrl, function(res){
            //console.log(res)
            var html="";
            if(res.isSuccess){
                html+='<p>企业：'
                + res.data.entName
                + '</p><p>基地：'
                + res.data.originName
                +'</p><p><span>品种：'
                + res.data.productName
                +'</span><span>采收时间：'
                + res.data.harvestTime
                +'</span></p><p><span>联系人：'
                + res.data.contactPerson
                +'</span><span>电话：'
                + res.data.telephone
                +'</span></p>'
                $("#syCodeMessage").html(html)
            }else{
                $("#syCodeMessage").html("<div class='noData' style='margin-top:45%'>暂无查询结果</div>")
            }
        });
    };
});

//跳转搜索页面
$("#searchCode input").blur(function () {
    var value = $("#codeWord").val();
    if (value == '') {
        this.value = '请输入20位溯源码';
    } else {
        this.value = value;
    };
});
//121查询溯源部分的事件
$(".chooseHtml").hide();
$(".echart .l2").click(function (){
    var flag = $(".chooseHtml").is(":hidden");
    if(flag){
        $(".chooseHtml").show();
    }else{
        $(".chooseHtml").hide();
    }
});
//鼠标移入移出弹窗显示隐藏
$(".chooseHtml").mouseover(function (){
    $(".chooseHtml").show();
}).mouseout(function (){
    $(".chooseHtml").hide();
});

//采集体系添加跳转链接
/* 政府端 府端 http://202.106.10.250:63012/login/gov
运营端
运营端 营端 http://202.106.10.250:63012/login/ops        
企业端：业端：http://202.106.10.250:63012/login/ent
消费端
消费端：费端：http://202.106.10.250:63012/cos/sy/index.html */
var addHrefl2ZF = URLSYip +'/login/gov';//政府端
var addHrefl2YY = URLSYip +'/login/ops';//运营端
var addHrefl2QY = URLSYip +'/login/ent';//企业端
var addHrefl2XF = URL +'/pc/ss/syxf/dist/index.html';//消费端
$(".chooseHtml .zfd").attr('href',addHrefl2ZF);
$(".chooseHtml .yyd").attr('href',addHrefl2YY);
$(".chooseHtml .qyd").attr('href',addHrefl2QY);
$(".chooseHtml .xfd").attr('href',addHrefl2XF);
//数据采集添加跳转链接
var addHrefl1CJ = URL +'/pc/ss/da/build/index.html#/login';//数据采集
$(".echart .l1").attr('href',addHrefl1CJ)
//监测预警页面跳转
var addHrefJCYJ = URL +'/pc/ss/mf/build/index.html#/';//数据采集/mf/build/index.html#/
$(".echart .r2").attr('href',addHrefJCYJ)
