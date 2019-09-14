
//获取网站声明数据
function getDetiles(currentPage){
        var params = {
                    "entityRelated" : {
                        "contentId" : 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',
                        /*"relationId": "01",
                        "isRecommend": 1,//1推荐，0:不推荐*/
                    },
                    "orderList" : [ {
                        "columnName" : "release_date",
                        "isASC" : false
                    } ],
                    "page" : {
                        "pageIndex" : currentPage,
                        "pageSize" : 10
                    }
                }
        var submitDataString = JSON.stringify(params);

    ajaxGet("/extend/swagger/pi/piContent/getWebSiteBaseInfo",submitDataString,rendomDetiles)
}
getDetiles();
//网站声明的回调函数
function rendomDetiles(val) {
    console.log(val)
    var params = {
		idHtml: 'statement',
	    idScript: 'statementSrc',
	    data:{
	    	"statementData":val.data[0]
	    }
	};
	if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#statement").html("<div class='noData'>暂无查询结果</div>")
    }

}


//获取关于我们数据
function getAboutUs(currentPage){
        var params = {
                    "entityRelated" : {
                        "contentId" : 'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb',
                        /*"relationId": "01",
                        "isRecommend": 1,//1推荐，0:不推荐*/
                    },
                    "orderList" : [ {
                        "columnName" : "release_date",
                        "isASC" : false
                    } ],
                    "page" : {
                        "pageIndex" : currentPage,
                        "pageSize" : 10
                    }
                }
        var submitDataString = JSON.stringify(params);

    ajaxGet("/extend/swagger/pi/piContent/getWebSiteBaseInfo",submitDataString,rendomAboutUs)
}
getAboutUs();
//网站声明的回调函数
function rendomAboutUs(val) {
    //console.log(val)
    var params = {
		idHtml: 'aboutUs',
	    idScript: 'aboutUsSrc',
	    data:{
	    	"aboutUsData":val.data[0]
	    }
	};
	if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#aboutUs").html("<div class='noData'>暂无查询结果</div>")
    }

}



//获取联系我们数据
function getContactUs(currentPage){
        var params = {
                    "entityRelated" : {
                        "contentId" : 'cccccccccccccccccccccccccccccccc',
                        /*"relationId": "01",
                        "isRecommend": 1,//1推荐，0:不推荐*/
                    },
                    "orderList" : [ {
                        "columnName" : "release_date",
                        "isASC" : false
                    } ],
                    "page" : {
                        "pageIndex" : currentPage,
                        "pageSize" : 10
                    }
                }
        var submitDataString = JSON.stringify(params);
    ajaxGet("/extend/swagger/pi/piContent/getWebSiteBaseInfo",submitDataString,rendomContactUs)
}
getContactUs();
//联系我们的回调函数
function rendomContactUs(val) {
    //console.log(val)
    var params = {
		idHtml: 'contactUs',
	    idScript: 'contactUsSrc',
	    data:{
	    	"contactUsData":val.data[0]
	    }
	};
	if(val.isSuccess){
        initTemplate(params);
    }else{
        $("#contactUs").html("<div class='noData'>暂无查询结果</div>")
    }

}