/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2021/1/18 19:49:24
 * @description  配置项信息。 相关页面的js方法。
 */

$(document).ready(function() {

	
	//初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_GPCONFIG_EDIT
		},
		detailPage : {
			url : RP_GPCONFIG_DETAIL
		},
		addPage : {
			url : RP_GPCONFIG_ADD
		},
		deleteInterface : {
			url : RU_GPCONFIG_DELETE
		},
		deleteListInterface : {
			url : RU_GPCONFIG_DELETELIST
		}

	};
	var ajaxParam = {
		url : RU_GPCONFIG_GETLISTBYJSONDATA,
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
			"columnName" : "serialNo",
			"columnText" : "编号",
			"style" : "text-align:left",
			"linkFunction" : function(event) {
				var href = RP_GPCONFIG_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
			}, 
			 {
			"columnName" : "defaultValue",
			"columnText" : "默认值",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "valueType",
			"columnText" : "值类型",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "priority",
			"columnText" : "排列顺序",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "remark",
			"columnText" : "备注",
			"style" : "text-align:left",
			}
        
       ]
	};

	var operationParam = [ {
		"operationText" : "修改",
		"buttonClass" : "yellow",
		"iconClass" : "fa fa-pencil-square-o",
		"clickFunction" : function(event) {
			window.location.href = pageParam.editPage.url + "?" + RECORD_ID + "=" + event.data.id;
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
				pageParam.deleteInterface.url = RU_GPCONFIG_DELETE;
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