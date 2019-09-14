/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  登录日志。 相关页面的js方法。
 */

$(document).ready(function() {

    //初始化下拉框--是否成功
    var selectParam = {
        selectId : "selectSuccessCode",
        textField : "text",
        valueField : "code"
    };
    var ajaxParam = {
        url : RU_GPDICTIONARY_GETLISTBYTYPEID + "/" + DI_BOOLEAN
    }
    initDropDownList(selectParam, ajaxParam);

	//初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_GPLOGINLOG_EDIT
		},
		detailPage : {
			url : RP_GPLOGINLOG_DETAIL
		},
		addPage : {
			url : RP_GPLOGINLOG_ADD
		},
		deleteInterface : {
			url : RU_GPLOGINLOG_DELETE
		},
		deleteListInterface : {
			url : RU_GPLOGINLOG_DELETELIST
		},
		exportExcelInterface:{
			url:RU_GPLOGINLOG_EXPORTEXCEL
		}

	};
	var ajaxParam = {
		url : RU_GPLOGINLOG_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {

			},
			"orderList" : [ {
				"columnName" : "id",
				"isASC" : true
			} ],
			"page" : {
				"pageIndex" : DEFAULT_PAGE_INDEX,
				"pageSize" : DEFAULT_PAGE_SIZE
			}
		},
		columnInfo : [ 
        
			 {
			"columnName" : "userName",
			"columnText" : "登录用户名",
			"style" : "text-align:center",
			"linkFunction" : function(event) {
				var href = RP_GPLOGINLOG_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
			},
            {
                "columnName" : "loginTime",
                "columnText" : "登录时间",
                "style" : "text-align:center",
            },
            {
                "columnName" : "ip",
                "columnText" : "IP地址",
                "style" : "text-align:center",
            },
			{
				"columnName" : "browser",
				"columnText" : "浏览器版本",
				"style" : "text-align:center",
			},
			{
				"columnName" : "os",
				"columnText" : "操作系统",
				"style" : "text-align:center",
			},
            {
                "columnName" : "isSuccessCode",
                "columnText" : "是否成功",
                "style" : "text-align:center",
            }

        
       ]
	};

	var operationParam = [ {
		"operationText" : "修改",
		"buttonClass" : "yellow",
		"iconClass" : "fa fa-pencil-square-o",
		"clickFunction" : function(event) {
			window.location.href = pageParam.editPage.url + "?" + RECORD_ID + "=" + event.data.id;
		},
		"visibleFunction" : function(recordData) {
			if (recordData.status == "1")
				return false;
			return false;
		}
	}, {
		"operationText" : "删除",
		"buttonClass" : "red",
		"iconClass" : "fa fa-trash-o",
		"clickFunction" : function(event) {
			layer.confirm('您确定要删除当前记录？', {
				btn : [ '确定', '取消' ]
			}, function() {
				layer.closeAll('dialog');
				ajaxParam.submitData.page.pageSize = $("#pageSizeText").val();
				ajaxParam.submitData.page.pageIndex = $("#pageIndexHidden").val();
				pageParam.deleteInterface.url = RU_GPLOGINLOG_DELETE;
				pageParam.deleteInterface.type = "GET";
				pageParam.deleteInterface.submitData = {
					"id" : event.data.id,
				};
				deleteRecord(pageParam, ajaxParam, operationParam);
			});
		},
		"visibleFunction" : function(recordData) {
			if (recordData.status == "1")
				return false;
			return true;
		}
	} ];
	initQueryForm(pageParam, ajaxParam, operationParam);

});