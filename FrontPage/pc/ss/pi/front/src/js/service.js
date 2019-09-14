// 初始化要执行的函数
/*
init();
function init(){
	page();
}
// 分页
function page(){
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



//banner轮播部分
function getBanner() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.serve,
            "relationId" :	"0102",
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
    console.log(val)
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
    var html = '';
    var html1 = '';
    if(val.isSuccess){
            for(var i = 0;i<val.data.length;i++){
            //console.log(val.data[i].channel_name)
            if(val.data[i].img_path){
                html+='<li data-url="'+i+'">'
                    +'<a href="../detiles/detiles.html?channel_id='+val.data[i].channel_id+'&id='+val.data[i].content_id+'" class="skip-box">'
                    +'<img src="'+val.data[i].img_path+'" alt="">'
                    +'<div class="swiper-banner-title over-ellipsis">'+val.data[i].title+'</div>'
                    +'</a>'
                    +'</li>';
            }else{
                html+='<li data-url="'+i+'">'
                    +'<a href="../detiles/detiles.html?channel_id='+val.data[i].channel_id+'&id='+val.data[i].content_id+'" class="skip-box">'
                    +'<img src="../../img/zwtp.gif" alt="">'
                    +'<div class="swiper-banner-title">'+val.data[i].title+'</div></a></li>';
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
        $("#swiper-banner").html("<div class='noData'>暂无查询结果</div>")
    }

}



/*****   服务页面政策部分六个模块开始  ********/


//insurance获取服务页面政策中保险的页面
function rendomInsurance(val) {
    //console.log(val)
    var params = {
        idHtml: 'Insurance',
        idScript: 'InsuranceSrc',
        data: {
            "InsuranceData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Insurance").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Insurance获取服务页面政策中保险的数据
function getInsurance() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.insurance,
            "relationId" :	"01020101",
            "isRecommend" : "1",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomInsurance);
}

getInsurance();



//Financing获取服务页面政策中融资的页面
function rendomFinancing(val) {
    //console.log(val)
    var params = {
        idHtml: 'Financing',
        idScript: 'FinancingSrc',
        data: {
            "FinancingData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Financing").html("<div class='noData'>暂无查询结果</div>")
    }

}
//Financing获取服务页面政策中融资的数据
function getFinancing() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.financing,
            "relationId" :	"01020104",
            "isRecommend" : "1",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomFinancing);
}

getFinancing();



//insurance获取服务页面政策中补贴的页面
function rendomSubsidy(val) {
    //console.log(val)
    var params = {
        idHtml: 'Subsidy',
        idScript: 'SubsidySrc',
        data: {
            "SubsidyData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Subsidy").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Subsidy获取服务页面政策中补贴的数据
function getSubsidy() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.subsidy,
            "relationId" :	"01020102",
            "isRecommend" : "1",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomSubsidy);
}

getSubsidy();


//Poverty获取服务页面政策中扶贫的页面
function rendomPoverty(val) {
    //console.log(val)
    var params = {
        idHtml: 'Poverty',
        idScript: 'PovertySrc',
        data: {
            "PovertyData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Poverty").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Poverty获取服务页面政策中扶贫的数据
function getPoverty() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.poverty,
            "relationId" :	"01020103",
            "isRecommend" : "1",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomPoverty);
}

getPoverty();



//Doctrine获取服务页面政策中三品一标的页面
function rendomDoctrine(val) {
    //console.log(val)
    var params = {
        idHtml: 'Doctrine',
        idScript: 'DoctrineSrc',
        data: {
            "DoctrineData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Doctrine").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Doctrine获取服务页面政策中三品一标的数据
function getDoctrine() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.doctrine,
            "relationId" :	"01020105",
            "isRecommend" : "1",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomDoctrine);
}

getDoctrine();


//insurance获取服务页面政策中认证规则的页面
function rendomRule(val) {
    //console.log(val)
    var params = {
        idHtml: 'Rule',
        idScript: 'RuleSrc',
        data: {
            "RuleData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Rule").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Rule获取服务页面政策中认证规则的数据
function getRule() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.rule,
            "relationId" :	"01020106",
            "isRecommend" : "1",//1推荐，0:不推荐
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomRule);
}

getRule();

/*****   服务页面政策部分六个模块结束  ********/



//Headline渲染服务头条页面
function rendomHeadline(val) {
    //console.log(val)
    var params = {
        idHtml: 'Headline',
        idScript: 'HeadlineSrc',
        data: {
            "HeadlineData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Headline").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Headline获取服务页面种植数据
function getHeadline() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.plant,
            "relationId" :	"010206",
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


//Process渲染服务加工部分页面
function rendomProcess(val) {
    //console.log(val)
    var params = {
        idHtml: 'Process',
        idScript: 'ProcessSrc',
        data: {
            "ProcessData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Process").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Process渲染服务加工部分数据
function getProcess() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.process,
            "relationId" :	"010202",
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomProcess);
}

getProcess();



//Train渲染服务培训部分页面
function rendomTrain(val) {
    //console.log(val)
    var params = {
        idHtml: 'Train',
        idScript: 'TrainSrc',
        data: {
            "TrainData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Train").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Train渲染服务培训部分数据
function getTrain() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.train,
            "relationId" :	"010203",
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomTrain);
}

getTrain();



//Pests渲染服务虫害部分页面
function rendomPests(val) {
    //console.log(val)
    var params = {
        idHtml: 'Pests',
        idScript: 'PestsSrc',
        data: {
            "PestsData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Pests").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Pests渲染服务病虫部分数据
function getPests() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.pests,
            "relationId" :	"010204",
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomPests);
}

getPests();





//Weather渲染服务气象部分页面
function rendomWeather(val) {
    //console.log(val)
    var params = {
        idHtml: 'Weather',
        idScript: 'WeatherSrc',
        data: {
            "WeatherData": val.data
        }
    };
    if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#Weather").html("<div class='noData'>暂无查询结果</div>")
    }
}
//Weather渲染服务气象部分数据
function getWeather() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.weather,
            "relationId" :	"010205",
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
    ajaxGet("/extend/swagger/pi/piContent/getContentList", submitDataString, rendomWeather);
}
    getWeather();












