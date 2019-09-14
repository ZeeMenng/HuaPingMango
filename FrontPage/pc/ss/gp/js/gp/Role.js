/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 系统角色。 相关页面的js方法。
 */

$(document).ready(function() {
	// 初始化应用领域下拉框
	var selectParam = {
		selectId : "selectDomainId",
		textField : "name",
		valueField : "id"
	};

	var ajaxParam = {
		url : RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData={}"
	}
	initDropDownList(selectParam, ajaxParam);
});

// 树形结构begin
var setting = {
	check : {
		enable : true
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "fartherId"
		}
	},
	callback : {
		onCheck : onCheck
	}
};
function initTreeNodes(domainId, isInitChecked, initResult) {
	$.ajax({
		async : false,
		url : INTERFACE_SERVER + RU_GPMODULE_GETLISTBYDOMAINID + domainId,
		success : function(res) {
			var treeNodes = res.data;
			var treeObj =$.fn.zTree.init($("#treeContainer"), setting, treeNodes);
			if (isInitChecked)
				initChecked(initResult);
			$.each(treeNodes, function(index, value) {
				if ( value.levelCode < 2) {
					var node = treeObj.getNodeByParam("id", value.id);
					treeObj.expandNode(node, true);// 展开指定节点
				}
			});
		}
	});
}

// 默认选中节点
function initChecked(initResult) {
	var treeObj = $.fn.zTree.getZTreeObj("treeContainer");
	if (initResult != null && initResult != '' && initResult != undefined) {
		var moduleIds = initResult.data.moduleIds;
		if (moduleIds != "" && moduleIds != null && moduleIds != undefined) {
			var arr = moduleIds.split(",");
			for (idx in arr) {
				var node = treeObj.getNodeByParam("id", arr[idx]);
				if (node) {
					treeObj.checkNode(node);
				}
			}
		}
	}
}

// 点击节点回调函数，给隐藏文本域添加角色id数组
function onCheck() {
	var treeObj = $.fn.zTree.getZTreeObj("treeContainer");
	var nodes = treeObj.getCheckedNodes(true);
	var arr = [];
	for (var i = 0; i < nodes.length; i++) {
		arr.push(nodes[i].id)
	}
	$("#hiddenModuleIds").val(arr)
}
// 树形结构end

