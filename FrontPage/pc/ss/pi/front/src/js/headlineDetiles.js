//获取标题下的辅助信息数据
function getDetiles(currentPage){
	var detilesId = GetUrlParam().id;
	var currentPage = currentPage?currentPage:1;
    var submitData = {
                    "entityRelated" : {
                        "contentId" : detilesId,
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


    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piContent/getContent",submitDataString,rendomDetiles)
}
getDetiles();
//渲染一级菜单
function rendomDetiles(val) {
    var params = {
		idHtml: 'detiles',
	    idScript: 'detilesSrc',
	    data:{
	    	"detilesData":val.data[0]
	    }
	};
	initTemplate(params);

}

//相关资讯数据
function getDemand() {
    var submitData = {
        "entityRelated": {
            "channelId": channelData.demand,
            "relationId" :  "010303",
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
