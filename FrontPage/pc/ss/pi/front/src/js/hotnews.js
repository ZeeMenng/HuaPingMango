// 初始化要执行的函数
init();

function init() {
    //page();
}

// 分页
/*function page(){
	$("#page").pagination(100, {
		num_edge_entries: 0, //边缘页数
		num_display_entries: 8, //主体页数
		first_text:'首页',
		last_text:'尾页',
		callback: pageselectCallback,
		totleNum:100,
		items_per_page:10 //每页显示1项
	});
}
// 分页回调函数
function pageselectCallback(page_index, jq){
	console.log(page_index, jq)
}*/
//banner 轮播
//autoPlay("swiper-banner", "controller-banner");

/*//说
function sayEle() {
    var params = {
        idHtml: 'say',
        idScript: 'saySrc',
        data: {
            "sayList": [
                {
                    value: '1适龄芒果控梢促花技术详解控梢促花技术详解控梢促花技术详解',
                    id: '1'
                },
                {
                    value: '2适龄芒果控梢促花技术详解控梢促花技术详解控梢促花技术详解',
                    id: '2'
                },
                {
                    value: '3适龄芒果控梢促花技术详解控梢促花技术详解控梢促花技术详解',
                    id: '3'
                },
                {
                    value: '4适龄芒果控梢促花技术详解控梢促花技术详解控梢促花技术详解',
                    id: '4'
                },
                {
                    value: '',
                    id: '5'
                }
            ]
        }
    };
    initTemplate(params);
}*/

//banner轮播部分
function getBanner() {
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomBanner);
}

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
        autoPlay("swiper-banner", "controller-banner");
    }
    initTemplate(params);

}*/

function rendomBanner(val){
    //console.log(val)
    var html = '';
    var html1 = '';
    for(var i = 0;i<val.data.length;i++){
        //console.log(val.data[i].description)
        var description = val.data[i].description ? val.data[i].description : '暂无描述内容';
        if(val.data[i].img_path){
            html+='<li data-url="'+i+'">'
                +'<div class="banner-left fl">'
                +'<a href="../detiles/detiles.html?channel_id='
                +val.data[i].channel_id +'&id='+val.data[i].content_id
                +'"class="skip-box"><img src="'+val.data[i].img_path+'" alt=""></a>'
                +'</div><div class="banner-right fr"><div class="img-describe"><h2><a href="../detiles/detiles.html?channel_id='
                +val.data[i].channel_id+'&id='+val.data[i].content_id
                +'"class="skip-box" title="'+val.data[i].title+'">'+val.data[i].title.strEllipsis(20)
                +'</a></h2><div class="title-detiles"><p style="height:155px;overflow:hidden;">'+description
                +'</p></div><div class="more"><a href="../detiles/detiles.html?channel_id='
                +val.data[i].channel_id +'&id='+val.data[i].content_id
                +'"class="skip-box">查看详情 ></a></div></div></div></li>'
        }else{
            html+='<li data-url="'+i+'">'
                +'<div class="banner-left fl">'
                +'<a href="../detiles/detiles.html?channel_id='
                +val.data[i].channel_id +'&id='+val.data[i].content_id
                +'"class="skip-box"><img src="../../img/zwtp.gif" alt=""></a>'
                +'</div><div class="banner-right fr"><div class="img-describe"><h2><a href="../detiles/detiles.html?channel_id='
                +val.data[i].channel_id+'&id='+val.data[i].content_id
                +'"class="skip-box" title="'+val.data[i].title+'">'+val.data[i].title.strEllipsis(20)
                +'</a></h2><div class="title-detiles"><p style="height:155px;overflow:hidden;">'+description
                +'</p></div><div class="more"><a href="../detiles/detiles.html?channel_id='
                +val.data[i].channel_id +'&id='+val.data[i].content_id
                +'"class="skip-box">查看详情 ></a></div></div></div></li>'
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
}

  getBanner();



//Headline获取热点页面头条数据
function getHeadline() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.hotNew,
            "typeId": typeData.headline,
            "relationId" :	"0101",
            "isRecommend" : "0",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomHeadline);
}

getHeadline();

//Headline渲染热点头条页面
function rendomHeadline(val) {
    //console.log(val)
    var params = {
        idHtml: 'Headline',
        idScript: 'HeadlineSrc',
        data: {
            "HeadlineData": val.data
        }
    };
    initTemplate(params);
}

//Watch获取热点视部分数据
function getWatch() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.hotNew,
            "typeId": typeData.see,
            "relationId" :	"0101",
            "isRecommend" : "0",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomWatch);
}

getWatch();

//Watch获取热点视部分页面
function rendomWatch(val) {
    //console.log(val)
    var params = {
        idHtml: 'Watch',
        idScript: 'WatchSrc',
        data: {
            "WatchData": val.data
        }
    };
    initTemplate(params);
}

//Listen获取热点听部分数据
function getListen() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.hotNew,
            "typeId": typeData.hear,
            "relationId" :	"0101",
            "isRecommend" : "0",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomListen);
}

getListen();

//Listen获取热点听部分页面
function rendomListen(val) {
    // console.log(val)
    var params = {
        idHtml: 'Listen',
        idScript: 'ListenSrc',
        data: {
            "ListenData": val.data
        }
    };
    initTemplate(params);
}

//Picture获取热点图部分数据
function getPicture() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.hotNew,
            "typeId": typeData.image,
            "relationId" :	"0101",
            "isRecommend" : "0",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomPicture);
}

getPicture();

//Picture获取热点图部分页面
function rendomPicture(val) {
    //console.log(val)
    var params = {
        idHtml: 'Picture',
        idScript: 'PictureSrc',
        data: {
            "PictureData": val.data
        }
    };
    initTemplate(params);
}

//Say获取热点说部分数据
function getSay() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.hotNew,
            "typeId": typeData.say,
            "relationId" :	"0101",
            "isRecommend" : "0",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomSay);
}

getSay();

//Say渲染说部分页面
function rendomSay(val) {
    //console.log(val)
    var params = {
        idHtml: 'Say',
        idScript: 'SaySrc',
        data: {
            "SayData": val.data
        }
    };
    initTemplate(params);
}

//页面中间的图片广告位
function getimgBanner() {
    var submitData = {
        "entityRelated": {
            "domainId": "032769fd7e376c04fb13c66419a72598",
            "spaceCode": "1"
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piAdvertising/getListByCode", submitDataString, rendomImg);
}

getimgBanner();
//渲染广告位
function rendomImg(val) {
   var html = '';
   //console.log(val)
           if (val.isSuccess) {
               //标题部分
               var targets = val.data[0].targetCode == 2 ? '_blank' : '_self';
               var width = val.data[0].width || '1200px';
               var height = val.data[0].height || '124px';
               html += '<a href="'
                +val.data[0].targetUrl + '" target="' + targets + '">' +
                    '<img style="width=' + width +'"height="'+ height +'" src="' + val.data[0].resourcePath + '" title="' + val.data[0].title + '">' + '</a>';
           }else{
               html += '<img src = "../../img/img-9.png" alt = "">'
           }
       $(".midimg").html(html);
}
//计数广告位点击次数
$(".midimg").click(function(){
    getimgBanner();
})