// 初始化要执行的函数
var param = {//channelData.demand
			"channelId": "3b8f6e987d853f93c899d82c32216c3e",
			"relationId":"01",
			"typeId": "",
			"isRecommend": "1",//0推荐，1:不推荐
			"keyword": "首页",
			"beginTime": "",
			"endTime": "",
            "currentPage":1
		}
init();


function init(){
	var defauleVal = GetUrlParam().val;
	$("#hotWord").val(defauleVal);
	param.keyword = defauleVal;
	/*laydate.render({
		  elem: '#test1',
		  type: 'datetime',
		  range: true,
		  done: function(dates){ //选择好日期的回调
		  		var dateArr = dates.split(" - ");
		  		param.beginTime = dateArr[0];
			    param.endTime = dateArr[1];
			    //console.log(param)
			    getList(param);
	      }
	});*/

	getList(param);
}

// 分页
function page(totles,pagesize){
    var pageSize = pagesize?pagesize:5;
    var currentPage = param.currentPage-1;
    $("#page").pagination(totles, {
        current_page:currentPage,
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
function pageselectCallback(page_index,jq){
    param.currentPage = page_index+1;
    getList(param);
}


$('.sort-type').on('click','span',function(){
    $(this).addClass("active").siblings().removeClass();
})
// 栏目查询
$('.programa-list').on('click','li',function(){
    $(this).addClass("active").siblings().removeClass();
    var relationId = $(this).data("num");
    param.currentPage = 1;
    if(relationId=="all"){
        param.relationId ='01';
    }else{
        param.relationId = relationId;
    }
    getList(param);


})
//时间查询
$('.time-list').on('click','li',function(){
    $(this).addClass("active").siblings().removeClass("active");
    var timeType = $(this).data("time");
    var tObject=null;
    param.currentPage = 1;
   // console.log(durTime(timeType))
    //获取当前时间
    if(timeType){
	    if(timeType=="all"){
	    	tObject = {
				startTime: "",
                endStr: ""
	    	}
	    }else{
	    	tObject = durTime(timeType);
	    }
	    param.beginTime = tObject.startTime;
	    param.endTime = tObject.endStr;
	    getList(param);

    }
})

//关键字查询
$(".keyword-list").click(function(){
	var keyWord = $("#keyword").val();
	param.keyword = keyWord;
	getList(param);
});

//搜索列表数据
function getList(params){
	var entityRelated = params;
    var currentPage = params.currentPage;
	var submitData = {
					"entityRelated" : entityRelated,
					"orderList" : [ {
						"columnName" : "release_date",
						"isASC" : false
					} ],
                    "page" : {
                        "pageIndex" : currentPage,
                        "pageSize" : '5'
                    }
				};
	var submitDataString = JSON.stringify(submitData);
	ajaxGet("/extend/swagger/pi/piContent/getContentList",submitDataString,rendomList);
}
//渲染搜索列表数据
//渲染列表
function rendomList(val) {
    //console.log(val)
    if(val.data.length>0){
        var params = {
            idHtml: 'list',
            idScript: 'searchPageSrc',
            data:{
                "searchPageData":val.data
            }
        };
        initTemplate(params);
        //分页部分
        $(".record-num").html(val.totalCount);
        page(val.totalCount)
    }else{
        $("#list").html("<div class='noData'>暂无数据！</div>");
        $(".record-num").html(0);
        $("#page").html("");
    }
    if( !$("#page").html() ){
        var totalCount = val.totalCount;
        if(totalCount<=5){
            $("#page").html("");
        }else{
            page(val.totalCount)
        }
    }
}

/*
function rendomList(val) {
	$("#list").html("");
	var html = '';
	if(val.data.length>0){
		for(var i=0;i<val.data.length;i++){
            //console.log(val.data[i].description)
			var descriptionVal = val.data[i].description == null ? '' : val.data[i].description
			html+='<div class="main-box main-list">'
	                +'<div class="news-box">'
	                    +'<i class="iconfont icon-dian-copy-copy list-style"></i>'
	                    +'<div class="news-title"><a href="../detiles/detiles.html?id='+val.data[i].content_id+'&channel_id='+val.data[i].channel_id+'">'+val.data[i].title+'</a></div>'
	                    +'<div class="news-content">'+ descriptionVal
	                        +'<span class="detiles-more"><a href="../detiles/detiles.html?id='+val.data[i].content_id+'">【详情】</a></span>'
	                    +'</div>'
	                    +'<div class="news-time">'
	                        +'<i class="iconfont icon-zhongbiao"></i>'
	                        +'<span>'+val.data[i].release_date+'</span>'
	                    +'</div>'
	                +'</div>'
	            +'</div>';
		}
		$("#list").html(html);
		$(".record-num").html(val.totalCount)
	}else{
		$(".record-num").html(0);
		$("#page").html("");
		$("#list").html("<p>暂无数据</p>");
	}
    //分页部分
    var totalCount = val.totalCount;
    if(totalCount<=8){
        $("#page").html("");
    }else{
        page(val.totalCount,5)
    }
}*/
