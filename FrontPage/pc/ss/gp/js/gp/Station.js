﻿/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  岗位。 相关页面的js方法。
 */

$(document).ready(function() {

	//初始化所属组织机构级别下拉框
	var selectParamAdmin = {
		selectId : "selectOrganizationId",
		textField : "text",
		valueField : "code"
	};
	var ajaxParamAdmin = {
		url : RU_GPORGANIZATION_GETNAME
	}
	initDropDownList(selectParamAdmin, ajaxParamAdmin);
	
	//初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_GPSTATION_EDIT
		},
		detailPage : {
			url : RP_GPSTATION_DETAIL
		},
		addPage : {
			url : RP_GPSTATION_ADD
		},
		deleteInterface : {
			url : RU_GPSTATION_DELETE
		},
		deleteListInterface : {
			url : RU_GPSTATION_DELETELIST
		},
		exportExcelInterface:{
			url:RU_GPSTATION_EXPORTEXCEL
		}

	};
	var ajaxParam = {
		url : RU_GPSTATION_GETLISTBYJSONDATA,
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
        
			// {
			//"columnName" : "serialNo",
			//"columnText" : "编号",
			//"style" : "text-align:left",
			//"linkFunction" : function(event) {
			//	var href = RP_GPSTATION_DETAIL + "?" + RECORD_ID + "=" + event.id;
			//	return href;
			//},
			//},
			 {
			"columnName" : "name",
			"columnText" : "名称",
			"style" : "text-align:left",
				 "linkFunction" : function(event) {
				 	var href = RP_GPSTATION_DETAIL + "?" + RECORD_ID + "=" + event.id;
				 	return href;
				 },
			},
			 {
			"columnName" : "responsibility",
			"columnText" : "机构职能",
			"style" : "text-align:left",
			}
			//,
			// {
			//"columnName" : "remark",
			//"columnText" : "备注字段",
			//"style" : "text-align:left",
			//}
        
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
				pageParam.deleteInterface.url = RU_GPSTATION_DELETE;
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
			return false;
		}
	} ];
	initQueryForm(pageParam, ajaxParam, operationParam);

});