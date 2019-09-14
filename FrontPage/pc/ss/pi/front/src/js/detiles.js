// 初始化要执行的函数
init();
function init(){
	//互动跳转
	$(".specialist-wrapper").on("click","li",function(){
		location.href="#";
	})
	//当前位置
}

// 分页
function page(totle){
	$("#page").pagination(totle, {
		num_edge_entries: 0, //边缘页数
		num_display_entries: 8, //主体页数
		first_text:'首页',
		last_text:'尾页',
		callback: pageselectCallback,
		totleNum:totle,
		items_per_page:10 //每页显示1项
	});
}


// 分页回调函数
function pageselectCallback(page_index, jq){
	//console.log(page_index, jq)
	getDetiles(page_index+1)
}

//获取详情数据
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
	console.log(val)
    if( !$("#page").html() ){
		page(val.totalCount)
    }
    var params = {
		idHtml: 'detiles',
	    idScript: 'detilesSrc',
	    data:{
	    	"detilesData":val.data[0]
	    }
	};
	initTemplate(params);

}
//服务数据
function getServe(){
	var submitData = {
					"entityRelated" : {
						"channelId" : channelData.serve,
                        "relationId" :	"0102",
                        "isRecommend" : "0",//0推荐，1:不推荐
					},
					"orderList" : [ {
						"columnName" : "release_date",
						"isASC" : false
					} ],
					"page" : {
						"pageIndex" : 1,
						"pageSize" : 1
					}
				};
	var submitDataString = JSON.stringify(submitData);
	ajaxGet("/extend/swagger/pi/piContent/getContentList",submitDataString,rendomServe)
}
getServe();
//渲染服务
function rendomServe(val) {
	console.log(val.data)
	var params = {
		idHtml: 'serve1',
	    idScript: 'serveSrc1',
	    data:{
	    	"serveData1":val.data[0]
	    }
	};
	if(val.data!= ''){
		initTemplate(params);
	}else{
		$('#serve .market-banner').html('暂无数据！')
		.css({'color':'#333','text-align':'center'})
	}
}
//政策数据
function getPolicy(){
	var submitData = {
					"entityRelated" : {
						"channelId" : channelData.policy,
                        "relationId" :	"010201",
                        "isRecommend" : "0",//0推荐，1:不推荐
					},
					"orderList" : [ {
						"columnName" : "release_date",
						"isASC" : false
					} ],
					"page" : {
						"pageIndex" : 1,
						"pageSize" : 1
					}
				};
	var submitDataString = JSON.stringify(submitData);
	ajaxGet("/extend/swagger/pi/piContent/getContentList",submitDataString,rendomPolicy)
}
getPolicy();
//渲染政策
function rendomPolicy(val) {
	console.log(val.data)
	var params = {
		idHtml: 'policy1',
	    idScript: 'policySrc1',
	    data:{
	    	"policyData1":val.data[0]
	    }
	};
	if(val.data!= ''){
		initTemplate(params);
	}else{
		$('#policy').html('暂无数据！')
		.css({'color':'#333','text-align':'center'})
	}
}

