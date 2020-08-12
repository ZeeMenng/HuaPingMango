/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 系统角色。 相关页面的js方法。
 */

$(document).ready(function() {

});


// 点击节点回调函数，给隐藏文本域赋值——选中的应用领域或功能模块列表
function onCheck() {
	var treeObj = $.fn.zTree.getZTreeObj("treeContainer");
	var nodes = treeObj.getCheckedNodes(true);

	var moduleIdsArray = [];
	var domainIdsArray = [];
	for (var i = 0; i < nodes.length; i++) {
		if (nodes[i].level == 0) {
			domainIdsArray.push(nodes[i].id);
		} else {
			moduleIdsArray.push(nodes[i].id);
		}
	}
	$("#hiddenModuleIds").val(moduleIdsArray.toString())
	$("#hiddenDomainIds").val(domainIdsArray.toString())
}

//此方法暂时未使用
function initTreeNodes(domainId, isInitChecked, initResult) {
	$.ajax({
		async : false,
		url : INTERFACE_SERVER + RU_GPMODULE_GETLISTBYDOMAINID + domainId,
		success : function(res) {
			var treeNodes = res.data;
			var treeObj = $.fn.zTree.init($("#treeContainer"), setting, treeNodes);
			if (isInitChecked)
				initChecked(initResult);
			$.each(treeNodes, function(index, value) {
				if (value.levelCode < 2) {
					var node = treeObj.getNodeByParam("id", value.id);
					treeObj.expandNode(node, true);// 展开指定节点
				}
			});
		}
	});
}
