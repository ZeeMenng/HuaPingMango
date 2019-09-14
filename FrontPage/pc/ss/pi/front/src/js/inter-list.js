var pageSize = 7; //分页中每页显示的条数
// 初始化要执行的函数
init();
function init() {
    var param = {
        hoverDom: 'tab-b',
        elementDom: 'market-banner-item',
        parentDom: 'sever-market-banner'
    }
    fadeAuto(param);
    var param1 = {
        hoverDom: 'tab-c',
        elementDom: 'market-banner-say',
        parentDom: 'say-market-banner'
    }
    fadeAuto(param1);
}

// 分页
function page(totles, pagesize) {
    var pageSize = pagesize ? pagesize : 10;
    if (totles < 8) return;
    $("#page").pagination(totles, {
        current_page: 0,
        num_edge_entries: 0, //边缘页数
        num_display_entries: 8, //主体页数
        first_text: '首页',
        last_text: '尾页',
        callback: pageselectCallback,
        totleNum: totles,
        items_per_page: pageSize //每页显示1项
    });

}
// 分页回调函数
function pageselectCallback(page_index, jq) {
    //console.log(page_index)
    getList(page_index + 1);
    //回应栏
    getInterList(page_index + 1);
    //留言板
    getMessageList(page_index + 1);
}

/* //问专家列表详情页右侧的热点轮播
function getHotnews() {
    var channelId = GetUrlParam().channel_id;
    var relationId = GetUrlParam().relation_id;
    var typeId = typeData.image
    var currentPage = currentPage ? currentPage : 1;
    var submitData = {
        "entityRelated": {
            "channelId": channelId,
            "relationId": relationId,
            "typeId": typeId
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": currentPage,
            "pageSize": pageSize
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomHotnews)
}
getHotnews();
function rendomHotnews(val) {
    var params = {
        idHtml: 'Hotnews',
        idScript: 'HotnewsSrc',
        data: {
            "HotnewsData": val.data[0]
        }
    };
    if (val.data != '') {
        initTemplate(params);
    } else {
        $('#Hotnews').html('暂无数据！')
            .css({ 'color': '#333', 'text-align': 'center' })
    }
}
//问专家列表详情页右侧的服务轮播
function getServe() {
    var channelId = GetUrlParam().channel_id;
    var relationId = GetUrlParam().relation_id;
    var typeId = typeData.see
    var currentPage = currentPage ? currentPage : 1;
    var submitData = {
        "entityRelated": {
            "channelId": channelId,
            "relationId": relationId,
            "typeId": typeId
        },
        "orderList": [{
            "columnName": "release_date",
            "isASC": false
        }],
        "page": {
            "pageIndex": currentPage,
            "pageSize": pageSize
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomServe)
}
getServe();
function rendomServe(val) {
    var params = {
        idHtml: 'serve',
        idScript: 'serveSrc',
        data: {
            "serveData": val.data[0]
        }
    };
    if (val.data != '') {
        initTemplate(params);
    } else {
        $('#serve').html('暂无数据！')
            .css({ 'color': '#333', 'text-align': 'center' })
    }
} */








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
hotNew()
//渲染热点
function rendomHotnew(val) {
    console.log(val)
    var params = {
        idHtml: 'hotNew',
        idScript: 'hotNewSrc',
        data: {
            "data": val.data
        }
    };
    initTemplate(params);
    initBanner('.swiper-container1');
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
            "pageSize": 6
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomServe)
}
getServe()
//渲染服务列表
function rendomServe(val) {
    console.log(val)
    var params = {
        idHtml: 'serve',
        idScript: 'serveSrc',
        data: {
            "serveData": val.data
        }
    };
    initTemplate(params);
    initBanner('.swiper-container2');
}






















//回应栏列表数据
function getInterList(currentPage){
    //var channelId = GetUrlParam().channel_id ? GetUrlParam().channel_id : '';
    var currentPage = currentPage ? currentPage : 1;
    var submitData = {
                "entityRelated":{
                    "interactionType":"1",
                    //"id":Id
                },
                "orderList":[{
                    "columnName":"update_time",
                    "isASC":false
                }],
                "page":{
                    "pageIndex":currentPage,
                    "pageSize": pageSize
                }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piInteraction/getInteractionList",submitDataString,rendomInterList)
}
   getInterList();
function rendomInterList(val) {
    console.log(val)
    var params = {
        idHtml: 'interList',
        idScript: 'interListSrc',
        data:{
            "interListData":val.data
        }
    };
    if(val.data!= ''){
        initTemplate(params);
        page(val.totalCount, 7);
    }else{
        $('#listPage li').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}
//详情数据
function getInterDetiles(){
    //var channelId = GetUrlParam().channel_id ? GetUrlParam().channel_id : '';
    var Id = GetUrlParam().id ? GetUrlParam().id : ''
    var submitData = {
                "entityRelated":{
                    "interactionType":"1",
                    "id":Id
                },
                "orderList":[{
                    "columnName":"update_time",
                    "isASC":false
                }],
                "page":{
                    "pageIndex":1,
                    "pageSize": pageSize
                }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piInteraction/getInteractionList",submitDataString,rendomInterDetiles)
}

   getInterDetiles();
function rendomInterDetiles(val){
    //console.log(val)
    var params = {
        idHtml: 'interDetiles',
        idScript: 'interDetilesSrc',
        data:{
            "interDetilesData":val.data
        }
    };
    if(val.data!= ''){
        initTemplate(params);
        layer.ready(function () { //为了layer.ext.js加载完毕再执行
            layer.photos({
                photos: '#layer-photos-demo',
                shift: 5 //0-6的选择，指定弹出图片动画类型，默认随机
        });
});


    }else{
        $('#listPage li').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}

//更多互动数据
function getMoreResponses(){
    var submitData = {
                "entityRelated":{
                    "interactionType":"1"
                },
                "orderList":[{
                    "columnName":"update_time",
                    "isASC":false
                }],
                "page":{
                    "pageIndex":1,
                    "pageSize": pageSize
                }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piInteraction/getInteractionList",submitDataString,rendomMoreResponses)
}

   getMoreResponses();
function rendomMoreResponses(val){
   // console.log(val)
    var params = {
        idHtml: 'MoreResponses',
        idScript: 'MoreResponsesSrc',
        data:{
            "MoreResponsesData":val.data
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#listPage li').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}

//留言板列表数据
function getMessageList(currentPage) {
    var channelId = GetUrlParam().channel_id ? GetUrlParam().channel_id : '';
    var currentPage = currentPage ? currentPage : 1;
    var submitData = {
        "entityRelated": {
            "interactionType": "2",
            "id": channelId
        },
        "orderList": [{
            "columnName": "update_time",
            "isASC": false
        }],
        "page": {
            "pageIndex": currentPage,
            "pageSize": pageSize
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piInteraction/getInteractionList", submitDataString, rendomMessageList)
}
getMessageList();
//留言板列表数据
function rendomMessageList(val) {
    //console.log(val)
    var params = {
        idHtml: 'listPage',
        idScript: 'listPageSrc',
        data: {
            "listData": val.data
        }
    };
    if (val.data != '') {
        initTemplate(params);
    } else {
        $('#listPage li').html('暂无数据！')
            .css({
                'color': '#333',
                'text-align': 'center'
            })
    }
}

//互动留言列表页数据
function getMsgList() {
    var submitData = {
        "entityRelated": {
            "interactionType": "2"
        },
        "orderList": [{
            "columnName": "update_time",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": pageSize
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piInteraction/getInteractionList", submitDataString, rendomMsgList)
}
getMsgList();
//互动主页面留言
function rendomMsgList(val) {
    //console.log(val)
    var params = {
        idHtml: 'msgList',
        idScript: 'msgListSrc',
        data: {
            "msgListData": val.data
        }
    };
    if (val.data != '') {
        initTemplate(params);
    } else {
        $('#listPage li').html('暂无数据！')
            .css({
                'color': '#333',
                'text-align': 'center'
            })
    }
}

//问专家列表页微商城鲜果数据
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
            "pageSize": 1
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
            idHtml: 'MangoMallList',
            idScript: 'MangoMallListSrc',
            data: {
                "MangoMallListData": val.data
            }
        };
        initTemplate(params);
        autoPlay("stroe-top-swipper", "", true);
    } else {
        $("#MangoMallList").html("<li class='noData'>暂无查询结果</li>")
    }

}