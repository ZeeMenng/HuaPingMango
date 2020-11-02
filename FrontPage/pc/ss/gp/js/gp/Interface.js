/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 系统接口。 相关页面的js方法。
 */

$(document).ready(function() {


});

function initSelect(async){
	
	
	// 初始化应用领域下拉框
	var selectParam1 = {
		selectId : "selectDomainId",
		textField : "name",
		valueField : "id"
	};
	var ajaxParam1 = {
		url : RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData={}",
		async:async
	}
	initDropDownList(selectParam1, ajaxParam1);

	// 初始化是否为公共接口下拉框
	var selectParam2 = {
		selectId : "selectIsPublicCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam2 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + "/" + DI_BOOLEAN,
		async:async
	}
	initDropDownList(selectParam2, ajaxParam2);

	// 初始化接口调用方法
	var selectParam3 = {
		selectId : "selectTypeCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam3 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + "/" + DI_REQUEST_METHOD,
		async:async
	}
	initDropDownList(selectParam3, ajaxParam3);
	
}

function initCatalogZTree() {

	var jsonData = {
		"entityRelated" : {

		},
		"orderList" : [ {
			"columnName" : "name",
			"isASC" : true
		} ]
	};
	// 树形结构begin
	var setting = {
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			selectedMulti : true,
			dblClickExpand : false
		},
		edit : {
			enable : true,
			editNameSelectAll : true,
			showRemoveBtn : showRemoveBtn,
			showRenameBtn : showRenameBtn
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "fartherId"
			}
		},
		callback : {
			beforeDrag : beforeDrag,
			// beforeEditName : beforeEditName,
			beforeRemove : beforeRemove,
			beforeRename : beforeRename,
			beforeClick : beforeClick,
			onDrop : onDrop,
			onRemove : onRemove,
			onRename : onRename,
			beforeDrop : beforeDrop,
			onClick : onClick
		}
	};
	var ajaxParamter = {
		"url" : RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData),
		"async" : true,
		"type" : "GET",
		"success" : function(resultData) {
			var zNodes = [];
			if (resultData.data != null && resultData.data.length != 0) {
				zNodes = resultData.data;

				// 更改应用领域图标展示
				for (i = 0; i < zNodes.length; i++) {
					zNodes[i].icon = "/pc/global/plugins/zTree_v3/css/zTreeStyle/img/diy/5.png";
					zNodes[i].iconSkin = "diy01";
				}

			}
			$.fn.zTree.init($("#ulModuleTree"), setting, zNodes);

			$(".ztree .level0 a").attr("style", "cursor:default")

			var moduleTree = $.fn.zTree.getZTreeObj("ulModuleTree");

			// 树形菜单加上 F2快捷键
			$("#ulModuleTree").on("keydown", "li", function(event) {
				if (event.keyCode == 113) {
					var node = moduleTree.getNodeByTId($(this).attr("id"));
					if (node.isHover && node.level != 0)
						moduleTree.editName(node);
				}
			});

			var nodeList = moduleTree.getNodes();
			$.each(nodeList, function(index, value) {
				var ajaxParamter = {
					"url" : RU_GPMODULE_GETLISTBYDOMAINID + value.id,
					"async" : true,
					"type" : "GET",
					"success" : function(resultData) {
						if (resultData.totalCount != 0) {

							// 更改功能模块图标展示
							for (i = 0; i < resultData.data.length; i++) {
								resultData.data[i].icon = "/pc/global/plugins/zTree_v3/css/zTreeStyle/img/diy/3.png";
								resultData.data[i].iconSkin = "diy02";
							}
							moduleTree.addNodes(value, -1, resultData.data);

						}

					}
				};
				universalAjax(ajaxParamter);
			});
			fuzzySearch("ulModuleTree", '#ztree_search', null, true); // 初始化模糊搜索方法
		}
	};
	universalAjax(ajaxParamter);
	initEditFileInput();
}

function initInterfaceTable() {
	// 初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_GPINTERFACE_EDIT
		},
		detailPage : {
			url : RP_GPINTERFACE_DETAIL
		},
		addPage : {
			url : RP_GPINTERFACE_ADD
		},
		deleteInterface : {
			url : RU_GPINTERFACE_DELETE
		},
		deleteListInterface : {
			url : RU_GPINTERFACE_DELETELIST
		},
		exportExcelInterface : {
			url : RU_GPINTERFACE_EXPORTEXCEL
		}

	};
	var ajaxParam = {
		url : RU_GPINTERFACE_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {},
			"orderList" : [ {
				"columnName" : "A.add_time",
				"isASC" : false
			} ],
			"page" : {
				"pageIndex" : DEFAULT_PAGE_INDEX,
				"pageSize" : DEFAULT_PAGE_SIZE
			}
		},
		columnInfo : [

		{
			"columnName" : "name",
			"columnText" : "接口名称",
			"style" : "text-align:left",
			"width" : "100px",
			"linkFunction" : function(event) {
				var href = RP_GPINTERFACE_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
		}, {
			"columnName" : "url",
			"columnText" : "访问路径",
			"style" : "text-align:left"

		} ]
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
				pageParam.deleteInterface.url = RU_GPINTERFACE_DELETE;
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

	$("#updateInterfaceConstantsButton").click(function() {

		layer.open({
			area : [ '800px', '230px' ],
			type : 1,
			closeBtn : true,
			shift : 7,
			shadeClose : true,
			content : "<div style='width:750px;text-align: center' class='form-inline'>" + "<br/><div  style='margin: 10px' class='form-group'><span style='font-weight:bold'>JS    常量路径： </span><input size='60pt'  id='textJsConstantsPath' class='form-control' type='text' name='textJsConstantsPath' value='D:\\JAVA\\GitHubTarget\\HuaPingMango\\FrontPage\\pc\\global\\js\\constant\\Interface.js'/></div>" + "<br/><div style='margin: 20px,0px,20px' class='form-group'><button style='margin-top:5%;' type='button' class='form-control btn btn-block  btn-lg red-mint' onclick='updatePageConstant()'>提交</button></div></div>"
		});
		return;

	});
}

/**
 * @author Zee
 * @createDate 2020年9月17日 下午6:05:12
 * @updateDate 2020年9月17日 下午6:05:12
 * @description layer.load 参数: icon:0,1,2 loading风格 shade:false 是否有遮罩，true表示有遮罩
 *              time : 2*1000 设定最长等待时间,设置时间之后，loading会在时间到之后自动关闭
 * 
 * 
 */
function updatePageConstant() {
	var ajaxParamter = {
		"url" : "/extend/swagger/gp/gpInterface/updateInterfaceConstants",
		"data" : "jsonData=" + JSON.stringify({
			"jsConstantsPath" : $("#textJsConstantsPath").val()
		}),
		"type" : "GET",
		"async" : true,
		"beforeSend" : function() {
			layer.closeAll();
			layer.load(0, {
				shade : false
			});
		},
		"success" : function(resultData) {
			if (!resultData["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon : 6
				});
				return false;
			}
			layer.closeAll();
			layer.msg('更新成功……', {
				time : 500,
			});
			$("#queryBuilderForm").submit();
		},

	};

	universalAjax(ajaxParamter);
}
