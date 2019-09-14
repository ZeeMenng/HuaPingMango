﻿/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/7/12 14:43:26
 * @description  CMS友情链接 相关页面的js方法。
 */

$(document).ready(function() {

	
	//初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_PIFRIENDLINK_EDIT
		},
		detailPage : {
			url : RP_PIFRIENDLINK_DETAIL
		},
		addPage : {
			url : RP_PIFRIENDLINK_ADD
		},
		deleteInterface : {
			url : RU_PIFRIENDLINK_DELETE
		},
		deleteListInterface : {
			url : RU_PIFRIENDLINK_DELETELIST
		}

	};
	var ajaxParam = {
		url : RU_PIFRIENDLINK_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {

			},
			"orderList" : [ {
				"columnName" : "addTime",
				"isASC" : true
			} ],
			"page" : {
				"pageIndex" : DEFAULT_PAGE_INDEX,
				"pageSize" : DEFAULT_PAGE_SIZE
			}
		},
		columnInfo : [ 
        
			 {
			"columnName" : "name",
			"columnText" : "链接网站名称",
			"style" : "text-align:left",
			"linkFunction" : function(event) {
				var href = RP_PIFRIENDLINK_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
			}, 
			 {
			"columnName" : "url",
			"columnText" : "网站地址",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "email",
			"columnText" : "站长邮箱",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "remark",
			"columnText" : "备注",
			"style" : "text-align:left",
			}, 
			 {
				"columnName" : "addTime",
				"columnText" : "添加时间",
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
				pageParam.deleteInterface.url = RU_PIFRIENDLINK_DELETE;
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