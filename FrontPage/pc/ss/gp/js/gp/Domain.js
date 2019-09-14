/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 应用领域。 相关页面的js方法。
 */
var className = "dark";
$(document).ready(function() {

});
var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
		return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_" + treeNode.tId);
	if (btn)
		btn.bind("click", function() {
			var newNode = {
				id : (100 + newCount),
				fartherId : treeNode.id,
				name : "new node" + (newCount++)
			};
			zTree.addNodes(treeNode, newNode);
			var treeNodes = new Array();
			treeNodes.push(zTree.getNodeByParam("name", newNode.name, treeNode));
			updateModulesData(treeId, treeNodes, 'ADD');
			return false;
		});

};
function removeHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	$("#addBtn_" + treeNode.tId).unbind().remove();
};
function showRemoveBtn(treeId, treeNode) {
	return treeNode.level != 0;
}
function showRenameBtn(treeId, treeNode) {
	return treeNode.level != 0;
}
function beforeDrag(treeId, treeNodes) {
	for (var i = 0, l = treeNodes.length; i < l; i++) {
		var pid = treeNodes[i].fartherId;
		if (pid == "root" || pid == null || pid == "null") {
			layer.alert("根节点不能移动。", {
				icon : 6
			});
			return false;
		}
	}
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {

	if (targetNode.level == 0 && moveType != 'inner') {
		layer.alert("不能移动到根节点。", {
			icon : 6
		});
		return false;
	}
	return true;
};

var IS_IMMEDIATE = true;

function onDrop(event, treeId, treeNodes, targetNode, moveType) {
	updateModulesData(treeId, treeNodes, 'UPDATE');
};

function beforeEditName(treeId, treeNode) {
	className = (className === "dark" ? "" : "dark");
	var treeNodes = new Array();
	treeNodes.push(treeNode);
	updateModulesData(treeId, treeNodes, 'UPDATE');
	var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
	zTree.selectNode(treeNode);
	zTree.editName(treeNode);
	return false;
}
function beforeRemove(treeId, treeNode) {
	className = (className === "dark" ? "" : "dark");
	var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
	zTree.selectNode(treeNode);
	layer.confirm('您确定要删除节点  ' + treeNode.name + ' 吗？', {
		btn : [ '确定', '取消' ]
	}, function(index) {
		// 手动处理删除逻辑
		layer.close(index);
		zTree.removeNode(treeNode);
		onRemove(null, treeId, treeNode);
	});
	// 不再自动去发onRemove事件
	return false;
}
function onRemove(e, treeId, treeNode) {
	var treeNodes = new Array();
	treeNodes.push(treeNode);
	updateModulesData(treeId, treeNodes, 'DELETE');
}
function beforeRename(treeId, treeNode, newName, isCancel) {
	className = (className === "dark" ? "" : "dark");
	if (newName.length == 0) {
		setTimeout(function() {
			var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
			zTree.cancelEditName();
			layer.alert("节点名称不能为空。", {
				icon : 6
			});
		}, 0);
		return false;
	}
	return true;
}
function onRename(e, treeId, treeNode, isCancel) {
	var treeNodes = new Array();
	treeNodes.push(treeNode);
	updateModulesData(treeId, treeNodes, 'UPDATE');
}

function updateModulesData(treeId, treeNodes, action) {

	if (IS_IMMEDIATE) {
		immediateUpdate(treeId, treeNodes, action);
		return;
	}

	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var zTreeNodes = zTree.getNodes();
	var zTreeNodesJsonArray = getModulesJsonArray(zTreeNodes);
	var infoData = JSON.stringify(zTreeNodesJsonArray);
	$("#hiddenModules").val(infoData);
}

function immediateUpdate(treeId, treeNodes, action) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var treeNodesArray = zTree.transformToArray(treeNodes);

	var ajaxParamter = {
		"async" : true,
		"type" : "POST",
		"success" : function(resultData) {
			// 添加成功更新当前系统ID
			if (action == "ADD") {
				treeNodes[0].id = resultData.objectId;
				zTree.updateNode(treeNodes[0])
			}

			layer.msg('数据已实时更新……', {
				time : 1500
			});
		}
	};
	var cascade = $("input[name='cascadeTypeCodeRadio']:checked").val();
	if (action == "ADD") {
		var domainNode = getCurrentRootNode(treeNodesArray[0]);
		var zTreeNodeJson = {
			id : null,
			cascadeTypeCode : cascade,
			domainId : domainNode.id,
			domainName : domainNode.name,
			name : treeNodesArray[0].name,
			fartherId : treeNodesArray[0].fartherId,
			levelCode : treeNodesArray[0].level + 1,
			priority : treeNodesArray[0].getIndex()
		}
		ajaxParamter.data = JSON.stringify(zTreeNodeJson);
		ajaxParamter.url = RU_GPMODULE_ADD;
	}

	else if (action == "DELETE") {

		var idArray = new Array();
		$.each(treeNodesArray, function(i, v) {
			idArray.push(v.id)
		});
		var submitData = {
			idList : idArray,
			cascadeTypeCode : cascade
		};
		ajaxParamter.type = 'POST';
		ajaxParamter.data = JSON.stringify(submitData);
		ajaxParamter.url = RU_GPMODULE_DELETELIST;
	} else if (action = "UPDATE") {
		var zTreeNodeJsonArray = new Array();
		$.each(treeNodesArray, function(i, v) {
			var domainNode = getCurrentRootNode(treeNodesArray[i]);
			var zTreeNodeJson = {
				id : treeNodesArray[i].id,
				cascadeTypeCode : cascade,
				domainId : domainNode.id,
				name : treeNodesArray[i].name,
				fartherId : treeNodesArray[i].fartherId,
				levelCode : treeNodesArray[i].level + 1,
				priority : treeNodesArray[i].getIndex()
			}
			zTreeNodeJsonArray.push(zTreeNodeJson);
		});
		var submitData = {
			entityList : zTreeNodeJsonArray
		}
		var RU_GPMODULE_UPDATELISTWIDFF = "/extend/swagger/gp/gpModule/updateListWithDff"
		ajaxParamter.data = JSON.stringify(submitData);
		ajaxParamter.url = RU_GPMODULE_UPDATELISTWIDFF;

	}

	universalAjax(ajaxParamter);

}

function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
	zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
}

// 获取当前节点的根节点(treeNode为当前节点)
function getCurrentRootNode(treeNode) {
	if (treeNode.getParentNode() != null) {
		var parentNode = treeNode.getParentNode();
		return getCurrentRootNode(parentNode);
	} else {
		return treeNode;
	}
}