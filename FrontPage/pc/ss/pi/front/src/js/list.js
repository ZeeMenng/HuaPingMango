// 初始化要执行的函数
init();
function init(){

	var param = {
		hoverDom:'tab-b',
		elementDom:'market-banner-item',
		parentDom:'sever-market-banner'
	}
	fadeAuto(param);
	var param1 = {
		hoverDom:'tab-c',
		elementDom:'market-banner-say',
		parentDom:'say-market-banner'
	}
	fadeAuto(param1);
}


$(document).ready(function(){
    var titleId = window.location.search;
    var arr = GetRequest(titleId);
    var typeId = arr.type_id;
    var channelId = arr.channel_id;
    var say = typeData.say,hear= typeData.hear,see= typeData.see,image= typeData.image,textTitle='文章列表';
    switch(typeId){
        case say : textTitle = '说'
            break;
        case hear : textTitle = '听'
            break;
        case see : textTitle = '视'
            break;
        case image : textTitle = '图'
            break;
        default:
            break;
    }
        $('.list-left-title').text(textTitle);
    //判断如果是推介隐藏视听图说的dom
    if( channelId == 'dde558fd9baf441090be1f840b63d8f1'){
        $('.titleClick').css('display','none');
    }
});

// 分页
function page(totles,pagesize){
    var pageSize = pagesize?pagesize:10;
    if (totles < 8) return;
    $("#page").pagination(totles, {
        current_page:0,
        num_edge_entries: 0, //边缘页数
        num_display_entries: 8, //主体页数
        first_text:'首页',
        last_text:'尾页',
        callback: pageselectCallback,
        totleNum:totles,
        items_per_page:pageSize //每页显示1项
    });

}
// 分页回调函数
function pageselectCallback(page_index, jq){
    //console.log(page_index)
    getList(page_index+1);
}

//列表数据
function getList(currentPage){
    var channelId = GetUrlParam().channel_id;
    var relationId = GetUrlParam().relation_id;
    var typeId = GetUrlParam().type_id;
    var currentPage = currentPage?currentPage:1;
    //console.log(relationId)
    var submitData = {
        "entityRelated" : {
            "channelId" : channelId,
            "relationId":relationId,
            "typeId":typeId
        },
        "orderList" : [ {
            "columnName" : "release_date",
            "isASC" : false
        }],
        "page" : {
            "pageIndex" : currentPage,
            "pageSize" : 7
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList",submitDataString,rendomList)
    //动态添加视听图说的href属性
    var UrlHrefSee = "list.html?channel_id="+ channelId +"&type_id=954effd987455f4a12c647c09779915f&relation_id="+relationId;
    var UrlHrefHear = "list.html?channel_id="+ channelId +"&type_id=d9ebd8db0dfce217ad73ca2118fd09dc&relation_id="+relationId;
    var UrlHrefImage = "list.html?channel_id="+ channelId +"&type_id=778a3e846ce7fff470256b308dff02db&relation_id="+relationId;
    var UrlHrefSay = "list.html?channel_id="+ channelId +"&type_id=5e0399a53bce096c0eab56e7a3671f18&relation_id="+relationId;
    $("#seeHref").attr("href",UrlHrefSee);
    $("#hearHref").attr("href",UrlHrefHear);
    $("#imageHref").attr("href",UrlHrefImage);
    $("#sayHref").attr("href",UrlHrefSay);

    //判断如果是推介隐藏视听图说的dom
    if( channelId == '6252e4700a6a4f6a832d0b55c4dc5f72'){
        $('.titleClick').css('display','none');
    }

$("#wordHref").attr("href",UrlHrefSay);
$("#videoHref").attr("href",UrlHrefSee);
    //var say = typeData.say,hear= typeData.hear,see= typeData.see,image= typeData.image,textTitle='文章列表';
/*var see;
    switch(active){
        case see : $("#videoHref").attr("href",UrlHrefSee);
            break;
        case hear : $("#videoHref").attr("href",UrlHrefHear);
            break;
        case image : $("#wordHref").attr("href",UrlHrefImage);
            break;
        case say : $("#wordHref").attr("href",UrlHrefSay);
            break;
        default:
            break;
    }*/

}
    getList();


//渲染列表
function rendomList(val) {
    //console.log(val)
    if(val.data.length>0){
        var params = {
            idHtml: 'listPage',
            idScript: 'listPageSrc',
            data:{
                "listData":val.data
            }
        };
        initTemplate(params);
    }else{
        $("#listPage").html("<div class='noData'>暂无数据！</div>")
    }
    if( !$("#page").html() ){
        page(val.totalCount,7)
    }

}

//视数据
function getServe(){
    var channelId = GetUrlParam().channel_id;
    var relationId = GetUrlParam().relation_id;
    var typeId = typeData.see
    var currentPage = currentPage?currentPage:1;
    var submitData = {
        "entityRelated" : {
            "channelId" : channelId,
            "relationId":relationId,
            "typeId":typeId
        },
        "orderList" : [ {
            "columnName" : "release_date",
            "isASC" : false
        } ],
        "page" : {
            "pageIndex" : currentPage,
            "pageSize" : 7
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList",submitDataString,rendomServe)
}
getServe();
//渲染视
function rendomServe(val) {
    var params = {
        idHtml: 'serve',
        idScript: 'serveSrc',
        data:{
            "serveData":val.data[0]
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $('#serve').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}
//听数据
function getHear(){
    var channelId = GetUrlParam().channel_id;
    var relationId = GetUrlParam().relation_id;
    var typeId = typeData.hear
    var currentPage = currentPage?currentPage:1;
    var submitData = {
        "entityRelated" : {
            "channelId" : channelId,
            "relationId":relationId,
            "typeId":typeId
        },
        "orderList" : [ {
            "columnName" : "release_date",
            "isASC" : false
        } ],
        "page" : {
            "pageIndex" : currentPage,
            "pageSize" : 7
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList",submitDataString,rendomHear)
}
getHear();
//渲染听
function rendomHear(val) {
    var params = {
        idHtml: 'hear',
        idScript: 'hearSrc',
        data:{
            "hearData":val.data[0]
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#hear').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}
//图数据
function getImage(){
    var channelId = GetUrlParam().channel_id;
    var relationId = GetUrlParam().relation_id;
    var typeId = typeData.image
    var currentPage = currentPage?currentPage:1;
    var submitData = {
        "entityRelated" : {
            "channelId" : channelId,
            "relationId":relationId,
            "typeId":typeId
        },
        "orderList" : [ {
            "columnName" : "release_date",
            "isASC" : false
        } ],
        "page" : {
            "pageIndex" : currentPage,
            "pageSize" : 7
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList",submitDataString,rendomImage)
}
getImage();
//渲染图
function rendomImage(val) {
    var params = {
        idHtml: 'image',
        idScript: 'imageSrc',
        data:{
            "imageData":val.data[0]
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#image').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}
//说数据
function getSay(){
    var channelId = GetUrlParam().channel_id;
    var relationId = GetUrlParam().relation_id;
    var typeId = typeData.say
    var currentPage = currentPage?currentPage:1;
    var submitData = {
        "entityRelated" : {
            "channelId" : channelId,
            "relationId":relationId,
            "typeId":typeId
        },
        "orderList" : [ {
            "columnName" : "release_date",
            "isASC" : false
        } ],
        "page" : {
            "pageIndex" : currentPage,
            "pageSize" : 7
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContentList",submitDataString,rendomSay)
}
getSay();
//渲染说
function rendomSay(val) {
    var params = {
        idHtml: 'say',
        idScript: 'saySrc',
        data:{
            "sayData":val.data[0]
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#say').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}

