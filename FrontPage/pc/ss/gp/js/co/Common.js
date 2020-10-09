var selectRows = new Array();
var txtActive;
// 所有DOM元素加载之前执行登录校验
(function() {
	if (!validateLogin()) {
		return false;
	}
})(jQuery);
// 验证插件中加入手机号校验功能
jQuery.validator.addMethod("phone", function(value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请填写正确的手机号码");
$(document).ready(function() {
	if (window.location.pathname.indexOf("/lo/Login.html") != -1) {
		return true;
	}
	$("#batchEditButton").prop("class", "hidden");

	
	
	// 注销按钮
	$("#aLogout").click(function() {
		var ajaxParamter = {
			"url" : "/oauth/logout",
			"data" : {},
			"type" : "GET",
			"async" : true,
			"success" : function(resultData) {
				if (!resultData["isSuccess"]) {
					layer.alert("退出操作出错！" + resultData["resultMessage"], {
						icon : 6
					});
					return false;
				}

				if (window.localStorage) {
					localStorage.removeItem("token");
				} else {

					Cookies.remove("token");

				}

				location.href = '../lo/Login.html';
			}
		};

		universalAjax(ajaxParamter);

	});
	// 增加刷新后全选有否判断，非全选时父checkbox不选中
	var checked_count = 0;
	var num = 0;
	num = $("#contentTable input[name='childCheckbox']").length;
	$("#contentTable input[name='childCheckbox']").each(function(i, n) {
		var recordId = $(this).closest("tr").attr("id");
		if ($(this).get(0).checked == true) {
			checked_count++;
		}
	});
	if (num > checked_count) {
		$("#contentTable input[name='headerCheckbox']").attr("checked", false);
	} else if (num == checked_count) {

		$("#contentTable input[name='headerCheckbox']").prop('checked', true);
	}

	if (jQuery().datepicker) {
		$('.date-picker').datepicker({
			language : 'zh-CN',
			todayBtn : "linked",
			autoclose : true,
			rtl : App.isRTL(),
			format : "yyyy-mm-dd",
			fontAwesome : true,
			orientation : "left",
			todayHighlight : true,
		});

		initMessage();
	}
	if (jQuery().datetimepicker) {
		$(".form-datetime").datetimepicker({
			language : 'zh-CN',
			format : "yyyy-mm-dd hh:ii P"

		});
	}
	$(".form_datetime").datetimepicker({
		language : 'zh-CN',
		todayBtn : 1,
		autoclose : true,
		isRTL : App.isRTL(),
		format : "yyyy-mm-dd hh:ii",
		fontAwesome : true,
		todayHighlight : true,
		pickerPosition : (App.isRTL() ? "bottom-right" : "bottom-left")
	});

	initNavbar();
	layui.use([ 'layer', 'form' ], function() {
		var layer = layui.layer, form = layui.form;
	});
	initPageSizeSelect();
	initLinkMenu();
	initCheckbox();
	// 左侧搜索匹配激活状态
	$("body").on("keyup", ".searchInput", function(e) {
		var val = $(this).val();
		var txts = $('.badge').prev('.title');
		txts.each(function(i, v) {
			var valAll = $(v).text();
			var liParent = $(v).parent().parent().parent().parent();
			var liParent2 = $(v).parent().parent();
			if (valAll.indexOf(val) > -1 && val != '' && e.keyCode != '8' || val == '' && e.keyCode == '8' && txtActive == valAll) {
				liParent.addClass("active open");
				liParent.find(".sub-menu").css("display", "block");
				liParent.find(".arrow").addClass("open");
				liParent2.addClass("active");
			}
			if (val == "" && txtActive != valAll && e.keyCode == '8' && valAll.indexOf(val) <= 0 || txtActive != valAll && e.keyCode == '8' && valAll.indexOf(val) < 0) {
				if (liParent2.hasClass("active")) {
					liParent.find(".sub-menu").css("display", "none");
					liParent.find(".arrow").removeClass("open");
					liParent.removeClass("active open");
				}
				liParent2.removeClass("active");
			}
		})
	})
});

function initPageSizeSelect() {
	var selectData = [ {
		value : 5,
		text : 5
	}, {
		value : 10,
		text : 10
	}, {
		value : 15,
		text : 15
	}, {
		value : 30,
		text : 30
	}, {
		value : 50,
		text : 50
	},
	// {
	// value: 'All',
	// text: '所有'
	// }
	];

	$.each(selectData, function(i, n) {
		$("#pageSizeSelect").append("<option value='" + n["value"] + "'>" + n["text"] + "</option>");
	});

}

function initCheckbox() {

	// 设置单条记录的checkbox单击事件
	$("#contentTable").on("click", "input[name='childCheckbox']", function(event) {
		var recordId = $(this).closest("tr").attr("id");

		// 选中记录，则将记录添加到数组中
		if ($(this).get(0).checked) {
			if ($.inArray(recordId, selectRows) == -1)
				selectRows.push(recordId);
		} else {// 取消选中，则删除数组中的记录
			if ($.inArray(recordId, selectRows) != -1)
				selectRows.splice($.inArray(recordId, selectRows), 1);
		}
	});

	$("#contentTable").on("click", "input[name='headerCheckbox']", function(event) {
		if ($(this).get(0).checked) {
			$("#contentTable input[name='childCheckbox']").each(function(i, n) {
				var recordId = $(this).closest("tr").attr("id");
				$(this).get(0).checked = true;
				if ($.inArray(recordId, selectRows) == -1)
					selectRows.push(recordId);

			});
		} else {
			$("#contentTable input[name='childCheckbox']").each(function(i, n) {
				var recordId = $(this).closest("tr").attr("id");
				$(this).get(0).checked = false;
				if ($.inArray(recordId, selectRows) != -1)
					selectRows.splice($.inArray(recordId, selectRows), 1);
			});
		}
	});
	// 增加非全选判断，非全选时父checkbox不选中
	$("#contentTable").on("click", "input[name='childCheckbox']", function(event) {
		/*
		 * if ($("#contentTable input[name='headerCheckbox']").is(':checked')) {
		 * $("#contentTable
		 * input[name='headerCheckbox']").attr("checked",false); }
		 */
		var checked_count = 0;
		var num = 0;
		num = $("#contentTable input[name='childCheckbox']").length;
		$("#contentTable input[name='childCheckbox']").each(function(i, n) {
			var recordId = $(this).closest("tr").attr("id");
			if ($(this).get(0).checked == true) {
				checked_count++;
			}
		});
		if (num > checked_count) {
			$("#contentTable input[name='headerCheckbox']").attr("checked", false);
		} else if (num == checked_count) {
			$("#contentTable input[name='headerCheckbox']").prop('checked', true);
		}
	});

}

function initNavbar() {
	$("#navbarIndexA").click(function() {
		window.location = GP_INDEX;
		return false;
	});

	$("#navbarListA").click(function() {
		var href = window.location.href;

		var beginIndex = href.lastIndexOf('/') + 1;
		var endIndex = href.indexOf('.html');
		href = href.substring(beginIndex, endIndex);
		var sL = href.length;
		var lastUpperCodeIndex = 0;
		for (var i = 0; i < sL; i++) {
			if (href.charAt(i) === href.charAt(i).toUpperCase()) {
				lastUpperCodeIndex = i;
			}
		}
		href = href.substring(0, lastUpperCodeIndex);
		window.location = href + "List.html";
		return false;
	});

}

// 初始化左侧菜单
function initLinkMenu() {
	var userInfo = JSON.parse(localStorage.getItem("token"));
	var userName = userInfo.userName;
	var ajaxParamter = {
		"url" : RU_GPMODULE_GETLINKMENU + "?userName=" + userName,
		"type" : "GET",
		"data" : "jsonData=" + encodeURIComponent(JSON.stringify({
			"domainId" : DOMAIN_ID_GP
		})),
		"async" : true,
		"success" : function(result) {
			total = result.totalCount;
			var data = result.data;
			$.each(data, function(i, n) {
				
				if (n["level"] == "1") {
					var menu = $("#firstLevelMenuLi").clone();
					menu.attr("id", n["id"]);
					menu.attr("fartherId", n["fartherId"]);
					menu.find("h3").html(n["name"]);
					$("#linkMenuUl").append(menu);
					console.log(n["name"]);
				}

			});

			$.each(data, function(i, n) {
				if (n["level"] == "2") {
					{
						var secondLevelMenu = $("#secondeLevelMenuLi").clone();
						secondLevelMenu.attr("id", n["id"]);
						secondLevelMenu.attr("fartherId", n["fartherId"]);

						if (n["pageUrl"] != null)
							secondLevelMenu.find("a.nav-toggle").attr("href", n["pageUrl"]);
						else
							secondLevelMenu.find("a.nav-toggle").attr("href", "javascript:;");

						secondLevelMenu.find("span.title").html(n["name"]);
						secondLevelMenu.find("a i").addClass(n["iconClass"]);

						var $firstLevelMenu = $("#linkMenuUl li[id='" + n["fartherId"] + "']");
						var $lastSecondLevelMenu = $("#linkMenuUl li[fartherId='" + n["fartherId"] + "']:last");

						if ($lastSecondLevelMenu.length != 0)
							$lastSecondLevelMenu.after(secondLevelMenu);
						else if ($firstLevelMenu.length != 0)
							$firstLevelMenu.after(secondLevelMenu);
						// 说明没有一级菜单，则二级菜单为一级菜单
						else
							$("#linkMenuUl").append(secondLevelMenu);
					}
				}
			});

			$.each(data, function(i, n) {
				if (n["level"] == "3") {
					{
						var thirdLevelMenu = $("#thirdLevelMenuLi").clone();
						thirdLevelMenu.attr("id", n["id"]);
						if (n["pageUrl"] != null) {
							thirdLevelMenu.find("a").attr("href", n["pageUrl"]);
						} else
							thirdLevelMenu.find("a").attr("href", "javascript:;");

						thirdLevelMenu.find("span.title").html(n["name"]);

						thirdLevelMenu.find("span.badge").html(n["XiaoXiShuLiang"]);
						thirdLevelMenu.find("a i").addClass(n["iconClass"]);
						$("#linkMenuUl li[id='" + n["fartherId"] + "'] ul").append(thirdLevelMenu);
						// 菜单加焦点
						var pageUrl = n["pageUrl"] == null ? "" : n["pageUrl"];
						var array = pageUrl.split("");
						var prefix = null;

						for (var l = 0; l < array.length; l++) {
							if (array[l].toLocaleString().charCodeAt(0) >= 65 && array[l].toLocaleString().charCodeAt(0) <= 90)// 第一个大写字母
							{
								prefix = n["pageUrl"].substr(0, l);
							}
						}
						var lastUrlindex = pageUrl.lastIndexOf("\/");
						pageUrl = pageUrl.substring(lastUrlindex + 1, pageUrl.length);
						if (prefix != null && window.location.href.indexOf(prefix) >= 0) {
							thirdLevelMenu.parent().parent().addClass("active open");
							thirdLevelMenu.addClass("active");
						}

						/** * 左侧导航菜单隐藏事件 */
						$("#linkMenuUl .sidebar-toggler").click(function() {
							if ($("#linkMenuUl").hasClass("page-sidebar-menu-closed")) {
								$(".search-wrap").show();
							} else {
								$(".search-wrap").hide();
							}
						})

					}
				}
			});

			// 清除模板
			$("#firstLevelMenuLi").remove();
			$("#secondeLevelMenuLi").remove();
			$("li[id='thirdLevelMenuLi']").remove();
			// 获取当前页面的左侧激活菜单名称 上面调用
			txtActive = $(".nav-item.start.active .title").text();
		}
	};

	universalAjax(ajaxParamter);

}

/**
 * Zee 初始化表单按钮事件，并执行一次查询操作
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function initQueryForm(pageParam, ajaxParam, operationParam) {
	$("#" + pageParam.formId).unbind("submit");
	$("#" + pageParam.formId).submit(function(e) {
		e.preventDefault();
		// 执行查询动作要重新初始化pageIndex和pageSize
		ajaxParam.submitData.pageIndex = DEFAULT_PAGE_INDEX;
		ajaxParam.submitData.pageSize = DEFAULT_PAGE_SIZE;
		return executeQuery(pageParam, ajaxParam, operationParam);
	});
	$("#resetButton").click(function(e) {
		e.preventDefault();
		$("#" + pageParam.formId)[0].reset();
		$("input[type='hidden']").val("");
		$(".selectpicker").selectpicker({
			noneSelectedText : '请选择，可多选……',
			width : ''
		});
		$(".selectpicker").selectpicker("refresh");
		selectRows = new Array();
		// 执行查询动作要重新初始化pageIndex和pageSize
		ajaxParam.submitData.pageIndex = DEFAULT_PAGE_INDEX;
		ajaxParam.submitData.pageSize = DEFAULT_PAGE_SIZE;
		return executeQuery(pageParam, ajaxParam, operationParam);
	});
	$("#" + pageParam.formId).submit();
}

$("#submitButton").click(function(e) {
	initQueryForm(pageParam, ajaxParam, operationParam);
});

/**
 * Zee 执行表单查询动作
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function executeQuery(pageParam, ajaxParam, operationParam) {
	var formData = $("#" + pageParam.formId).serializeArray();
	// 将查询条件和其它请求参数组装
	if (ajaxParam.submitData != null)
		$.each(formData, function(i, n) {
			ajaxParam.submitData.entityRelated[formData[i].name] = formData[i].value;
		});
	initNewGrid(pageParam, ajaxParam, operationParam);
}

/**
 * Zee 初始化数据表格
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function initNewGrid(pageParam, ajaxParam, operationParam) {
	$("#pageSizeSelect").val(ajaxParam.submitData.pageSize);
	$("#" + pageParam.tableId).empty();
	// 初始化表头部分
	var header = "<thead><tr>";
	header += "<th class='table-checkbox'  style='width:50px;'>";
	header += "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>";
	header += "<input name='headerCheckbox' type='checkbox' class='group-checkable' />";
	header += "<span></span></label></th><th style='width:30px;'>序号</th>";

	for (var i = 0; i < ajaxParam.columnInfo.length; i++) {
		if (typeof (ajaxParam.columnInfo[i].width) != "undefined")
			header += "<th class='sorting' sortBy='' columnName='" + ajaxParam.columnInfo[i].columnName + "' width='" + ajaxParam.columnInfo[i].width + "'>" + ajaxParam.columnInfo[i].columnText + "</th>";
		else
			header += "<th class='sorting' sortBy='' columnName='" + ajaxParam.columnInfo[i].columnName + "'>" + ajaxParam.columnInfo[i].columnText + "</th>";
	}

	if (operationParam != null)
		operationParam.length == 0 ? header += "</tr></thead>" : header += "<th style='min-width:105px;'>操作</th></tr></thead>";

	$("#" + pageParam.tableId).append(header);

	var orderListArray = new Array();
	$("#" + pageParam.tableId + " th").unbind("click");
	$("#" + pageParam.tableId + " th:gt(1):not(:last)").click(function() {
		var orderColumn = {
			"isASC" : null,
			"columnName" : $(this).attr("columnName")
		};

		if ($(this).attr("class") == "sorting") {
			$(this).attr("class", "sorting_asc");
			$(this).attr("sortBy", "asc")
			orderColumn.isASC = true;
		} else if ($(this).attr("class") == "sorting_asc") {
			$(this).attr("class", "sorting_desc");
			$(this).attr("sortBy", "desc")
			orderColumn.isASC = false;
		} else {
			$(this).attr("class", "sorting");
			$(this).attr("sortBy", null);
		}

		if (containsColumn(orderListArray, orderColumn) >= 0)
			orderListArray.splice(containsColumn(orderListArray, orderColumn), 1);
		if (orderColumn.isASC != null)
			orderListArray = orderListArray.prepend(orderColumn);

		$("#" + pageParam.tableId + " th").each(function() {
			if ($(this).attr("sortBy") == null || $(this).attr("sortBy") == '')
				return true;

		});

		ajaxParam.submitData.orderList = orderListArray;
		ajaxParam.submitData.pageIndex = DEFAULT_PAGE_INDEX;
		ajaxParam.submitData.pageSize = DEFAULT_PAGE_SIZE;
		if ($("#pageSizeSelect").val()) {
			ajaxParam.submitData.pageSize = $("#pageSizeSelect").val();
		}

		pageClick(pageParam, ajaxParam, operationParam);
	});

	pageClick(pageParam, ajaxParam, operationParam);
	bindPageButton(pageParam, ajaxParam, operationParam);
	initTopRightButton(pageParam, ajaxParam, operationParam);
}

/**
 * Zee 分页代码
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function pageClick(pageParam, ajaxParam, operationParam) {
	var pageSize = ajaxParam.submitData.pageSize;
	var pageIndex = ajaxParam.submitData.pageIndex;
	$("#pageSizeSelect").val(pageSize);
	$("#pageIndexHidden").val(pageIndex);

	var total = 0;
	var pageCount = 0;

	ajaxParam.submitData.page = {
		"pageIndex" : pageIndex,
		"pageSize" : pageSize
	};

	var submitDataString = JSON.stringify(ajaxParam.submitData);

	// 此处应该是同步请求才精确，否则无法保证pageCountText始终有正确的值，这个稍后改
	var ajaxParamter = {
		"url" : ajaxParam.url,
		"type" : ajaxParam.type,
		"data" : "jsonData=" + submitDataString,
		"async" : true,
		"success" : function(message) {
			total = message.totalCount;
			var data = message.data;
			pageIndex = parseInt(pageIndex);

			$("#" + pageParam.tableId + " tr").not($("#" + pageParam.tableId + " tr")[0]).remove();

			if (total == null || total == 0) {
				$("#contentTable input[name='headerCheckbox']").get(0).checked = false;
				noResult();
				return;
			} else if (data.length == 0) {
				if (pageIndex > 1) {
					ajaxParam.submitData.pageIndex--;
					pageClick(pageParam, ajaxParam, operationParam);
					return;
				} else {
					noResult();
					return;
				}
			}
			$("#" + pageParam.tableId).append("<tbody");
			$.each(data, function(a, n) {

				var row = "<tr> class='odd gradeX'";
				row += "<td><label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>";
				row += "<input name='childCheckbox' type='checkbox' class='checkboxes' value='1' />";
				row += "<span></span></label></td>";
				row += "<td>";

				row += ((pageIndex - 1) * pageSize + a + 1);
				row += "</td>";
				var col = "";
				var trId = ""// 单一记录主键

				for (var i = 0; i < ajaxParam.columnInfo.length; i++) {
					col = ajaxParam.columnInfo[i].columnName;

					row += "<td";
					ajaxParam.columnInfo[i].class == null ? row += "" : row += " class='" + ajaxParam.columnInfo[i].class + "'";
					ajaxParam.columnInfo[i].style == null ? row += "" : row += " style='" + ajaxParam.columnInfo[i].style + "'";
					row += ">";
					if (n[col] == null) {
						row += "</td>";
						continue;
					}
					if (ajaxParam.columnInfo[i].linkFunction != null)
						row += "<a style='color:#337ab7;' href='" + ajaxParam.columnInfo[i].linkFunction(n) + "'>";
					if (ajaxParam.columnInfo[i].bgcolorFunction != null)
						row += "<span class='" + ajaxParam.columnInfo[i].bgcolorFunction(n) + "'>";

					row += n[col];
					if (ajaxParam.columnInfo[i].linkFunction != null)
						row += "</a>";
					if (ajaxParam.columnInfo[i].bgcolorFunction != null)
						row += "</span>";
					row += "</td>";
				}

				// 第一次遍历，插入操作按钮
				if (operationParam != null && operationParam.length != 0) {
					row += "<td>&nbsp;&nbsp;"
					$.each(operationParam, function(b, m) {
						// 如果visibleFunction方法参数返回的是false，则不显示操作按钮
						if (m.visibleFunction != null)
							if (!m.visibleFunction(n))
								return true;
						row += "<button class='btn default btn-xs ";
						if (m.buttonClass != undefined)
							row += m.buttonClass;

						row += "' id='" + m.operationText + "'>";

						row += "<i class=' ";
						if (m.iconClass != undefined)
							row += m.iconClass;
						row += "'></i>";

						row += m.operationText;
						row += "</button>&nbsp;&nbsp;";

					});
					row += "</td></tr>";

					row = $(row);
					// 兼容之前的获取记录主键写法
					n.recordId = n["id"];
					// 第二次遍历，给操作按钮添加相应事件，把整条记录的所有参数返回
					$.each(operationParam, function(b, m) {
						row.find("#" + m.operationText).unbind("click");
						row.find("#" + m.operationText).click(n, m.clickFunction);
					});

				}
				row = $(row);
				row.attr("id", n["id"]);
				// 行头的checkbox是否选中
				if ($.inArray(n["id"], selectRows) == -1)
					row.find("input[type='checkbox']").get(0).checked = false;
				else
					row.find("input[type='checkbox']").get(0).checked = true;

				$("#" + pageParam.tableId).append(row);
			});
			$("#" + pageParam.tableId).append("</tbody>");
			if (pageSize == 0)
				pageSize = total;
			if (total % pageSize == 0)
				pageCount = total / pageSize;
			else
				pageCount = parseInt(total / pageSize) + 1;
			if ($("#gotoPageText").val() != pageIndex) {
				$("#gotoPageText").val(null);
			}
			$("#pageCountHidden").val(pageCount);
			$("#totalCountSpan").text(total);
			$("#pageIndexSpan").text(pageIndex + "/" + pageCount);

			// 判断列头的复选框是否被选择,如果当前页所有的列都被选择,则复选框处于选中状态
			var isSelectAll = true;
			if ($("#contentTable input[name='childCheckbox']").length == 0)// 如果一条数据都没有，也不能选中
				isSelectAll = false;
			else
				$("#contentTable input[name='childCheckbox']").each(function(i, n) {
					var recordId = $(n).closest("tr").attr("id");
					if ($.inArray(recordId, selectRows) == -1)
						isSelectAll = false;
					return false;
				});
			if (isSelectAll)
				$("#contentTable input[name='headerCheckbox']").get(0).checked = true;
			else
				$("#contentTable input[name='headerCheckbox']").get(0).checked = false;

			return false;
		},

	};

	universalAjax(ajaxParamter);

}

function containsColumn(array, item) {
	for ( var i in array)
		if (array[i].columnName == item.columnName)
			return i

	return -1;
}

Array.prototype.prepend = function(needle) {
	var a = this.slice(0);

	// 使用unshift方法向a开头添加item
	a.unshift(needle);
	return a;
}

/**
 * Zee 初始化分页按钮事件
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function bindPageButton(pageParam, ajaxParam, operationParam) {

	$("#firstA").unbind("click");
	$("#prviousA").unbind("click");
	$("#nextA").unbind("click");
	$("#lastA").unbind("click");
	$("#gotoPageA").unbind("click");

	$("#gotoPageText").unbind("change");
	$("#pageSizeSelect").unbind("change");

	var pageSize = ajaxParam.submitData.pageSize;
	var pageIndex = ajaxParam.submitData.pageIndex;

	// 第一页
	$("#firstA").click(function() {
		if (ajaxParam.submitData.pageIndex != 1) {
			ajaxParam.submitData.pageIndex = 1;
			pageClick(pageParam, ajaxParam, operationParam);
		}
	});

	// 上一页
	$("#prviousA").click(function() {
		if (ajaxParam.submitData.pageIndex != 1) {
			ajaxParam.submitData.pageIndex--;
			pageClick(pageParam, ajaxParam, operationParam);
		}
	});

	// 最后一页
	$("#lastA").click(function() {
		var pageCount = $("#pageCountHidden").val();
		if (ajaxParam.submitData.pageIndex != pageCount) {
			ajaxParam.submitData.pageIndex = pageCount;
			pageClick(pageParam, ajaxParam, operationParam);
		}
	});

	// 下一页
	$("#nextA").click(function() {
		var pageCount = $("#pageCountHidden").val();
		if (ajaxParam.submitData.pageIndex != pageCount) {
			ajaxParam.submitData.pageIndex++;
			pageClick(pageParam, ajaxParam, operationParam);
		}
	});

	$("#pageSizeSelect").change(function() {

		ajaxParam.submitData.pageSize = $("#pageSizeSelect").val();
		ajaxParam.submitData.pageIndex = DEFAULT_PAGE_INDEX;
		pageClick(pageParam, ajaxParam, operationParam);
	});

	// 跳转到
	$("#gotoPageText").keyup(function() {
		// if (event.keyCode == 13)
		gotoPage(pageParam, ajaxParam, operationParam);
	});
	$("#gotoPageButton").click(function() {
		gotoPage(pageParam, ajaxParam, operationParam);
	});

	function gotoPage(pageParam, ajaxParam, operationParam) {
		var pageCount = $("#pageCountHidden").val();
		var gotoPage = $("#gotoPageText").val();

		var r = /^[0-9]*[1-9][0-9]*$/

		if (!r.test(gotoPage)) {
			return false;
		}
		if (eval(gotoPage) < 1 || eval(gotoPage) > pageCount) {
			return false;
		}

		var intGotoPage = parseInt(gotoPage);
		ajaxParam.submitData.pageIndex = intGotoPage;
		pageClick(pageParam, ajaxParam, operationParam);
		return false;
	}
}

function initTopRightButton(pageParam, ajaxParam, operationParam) {
	// 处理表格右上角的通用按键单击事件
	$("#addButton").unbind("click");
	$("#addButton").click(function() {
		window.location.href = pageParam.addPage.url;
	});
	$("#batchDeleteButton").unbind("click");
	$("#batchDeleteButton").click(function() {
		if (selectRows.length == 0) {
			layer.alert('请选择要删除的记录！', {
				icon : 6
			});
			return false;
		}

		layer.confirm('您确定要删除这' + selectRows.length + '条记录？', {
			btn : [ '确定', '取消' ]
		}, function() {
			layer.closeAll('dialog');
			ajaxParam.submitData.page.pageSize = $("#pageSizeText").val();
			ajaxParam.submitData.page.pageIndex = $("#pageIndexHidden").val();
			pageParam.deleteListInterface.type = "POST";
			pageParam.deleteListInterface.submitData = {
				idList : selectRows
			};

			deleteRecordList(pageParam, ajaxParam, operationParam);

			return false;
		});

	});
	$("#batchEditButton").unbind("click");
	$("#batchEditButton").click(function() {
		if (selectRows.length == 0) {
			layer.alert('请选择要修改的记录！', {
				icon : 6
			});
			return false;
		}

		var date = new Date();
		date.setTime(date.getTime() + (1 * 24 * 60 * 60 * 1000));
		Cookies.remove("selectRows");
		Cookies.set("selectRows", selectRows, {
			path : '/',
			expires : date
		});
		pageParam.editPage.selectRows = selectRows;

		popUpPage(pageParam.editPage);
	});
	$("#batchExportButton").unbind("click");
	$("#batchExportButton").click(function() {

		var excelJsonData = {
			"columnInfo" : ajaxParam.columnInfo,
			"selectRows" : selectRows
		};

		$.extend(excelJsonData, ajaxParam.submitData);
		if (selectRows.length == 0)
			layer.msg('未选择要导出的记录，默认导出当前页……', {
				time : 1500
			});
		// 如果有选择删除分页信息，默认导出最大分页值，由后台常量控制SQLQUERY_KEYWORDS_PAGESIZE_MAX
		else
			delete excelJsonData.page;

		location.href = INTERFACE_SERVER + pageParam.exportExcelInterface.url + "?jsonData=" + JSON.stringify(excelJsonData);
	});

}

/**
 * Zee 删除指定数据
 * 
 * @param ajaxParam
 *            ajax参数
 */
function deleteRecord(pageParam, ajaxParam, operationParam) {

	var type = pageParam.deleteInterface.type;
	var submitData = pageParam.deleteInterface.submitData;
	var url = pageParam.deleteInterface.url;
	if (type == null)
		type = "GET";

	var ajaxParamter = {
		"url" : url,
		"data" : submitData,
		"type" : type,
		"async" : true,
		"success" : function(resultData) {
			if (!resultData["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon : 6
				});
				return false;
			}

			// 一旦数据删除成功，就从数组中移除相应记录的ID
			if (pageParam.deleteInterface.submitData.id != null && $.inArray(pageParam.deleteInterface.submitData.id, selectRows) != -1)
				selectRows.splice($.inArray(pageParam.deleteInterface.submitData.id, selectRows), 1);
			if (pageParam.deleteInterface.submitData.idList != null) {
				var idList = pageParam.deleteInterface.submitData.idList;
				// 将数组中的的记录数单独存下来，如果一边遍历，一边删除，怎么也删除不干净的。专门写了处理这种情况的算法
				var arrayLength = selectRows.length;
				var preId;
				var i = 0;
				while (i < arrayLength) {
					preId = idList[i];
					if ($.inArray(preId, selectRows) != -1)
						selectRows.splice($.inArray(preId, selectRows), 1);
					arrayLength = selectRows.length;
					// 说明数组中已经移除相应数据了
					if (selectRows[i] != preId) {
						if (i == 0)
							continue;
						else
							i--;
					} else {
						i++;
					}
				}

			}
			pageClick(pageParam, ajaxParam, operationParam);
		}
	};

	universalAjax(ajaxParamter);

}

function deleteRecordList(pageParam, ajaxParam, operationParam) {
	var type = pageParam.deleteListInterface.type;
	var submitData = pageParam.deleteListInterface.submitData;
	var url = pageParam.deleteListInterface.url;

	if (type.toUpperCase() == "POST" && submitData != null) {
		url = pageParam.deleteListInterface.url;
		submitData = JSON.stringify(submitData);
	}

	var ajaxParamter = {
		"url" : url,
		"data" : submitData,
		"type" : type,
		"async" : true,
		"success" : function(resultData) {
			if (!resultData["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon : 6
				});
				return false;
			}

			// 一旦数据删除成功，就从数组中移除相应记录的ID
			if (pageParam.deleteListInterface.submitData.id != null && $.inArray(pageParam.deleteListInterface.submitData.id, selectRows) != -1)
				selectRows.splice($.inArray(pageParam.deleteListInterface.submitData.id, selectRows), 1);
			if (pageParam.deleteListInterface.submitData.idList != null) {
				var idList = pageParam.deleteListInterface.submitData.idList;
				// 将数组中的的记录数单独存下来，如果一边遍历，一边删除，怎么也删除不干净的。专门写了处理这种情况的算法
				var arrayLength = selectRows.length;
				var preId;
				var i = 0;
				while (i < arrayLength) {
					preId = idList[i];
					if ($.inArray(preId, selectRows) != -1)
						selectRows.splice($.inArray(preId, selectRows), 1);
					arrayLength = selectRows.length;
					// 说明数组中已经移除相应数据了
					if (selectRows[i] != preId) {
						if (i == 0)
							continue;
						else
							i--;
					} else {
						i++;
					}
				}

			}
			pageClick(pageParam, ajaxParam, operationParam);
		}
	};

	universalAjax(ajaxParamter);

}

/**
 * Zee 初始化下拉框
 * 
 * @param selectParam
 *            下拉框参数
 * @param ajaxParam
 *            ajax参数
 */
function initDropDownList(selectParam, ajaxParam) {

	var type = ajaxParam.type;
	var submitData = ajaxParam.submitData;

	if (type == null)
		type = "GET";
	if (type.toUpperCase() == "POST" && type != null)
		submitData = JSON.stringify(submitData);

	var ajaxParamter = {
		"url" : ajaxParam.url,
		"data" : submitData,
		"type" : type,
		"async" : false,
		"success" : function(message) {
			if (!message["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon : 6
				});
				return false;
			}
			$.each(message.data, function(i, n) {
				$("#" + selectParam.selectId).append("<option value='" + n[selectParam.valueField] + "'>" + n[selectParam.textField] + "</option>");
			});

			return false;
		},

	};

	universalAjax(ajaxParamter);

}


/**
 * Zee 初始化单选按钮组
 * 
 * @param selectParam
 *            下拉框参数
 * @param ajaxParam
 *            ajax参数
 */
function initRadioList(radioParam, ajaxParam) {

	var type = ajaxParam.type;
	var submitData = ajaxParam.submitData;

	if (type == null)
		type = "GET";
	if (type.toUpperCase() == "POST" && type != null)
		submitData = JSON.stringify(submitData);

	var ajaxParamter = {
		"url" : ajaxParam.url,
		"data" : submitData,
		"type" : type,
		"async" : false,
		"success" : function(message) {
			if (!message["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon : 6
				});
				return false;
			}
			$.each(message.data, function(i, n) {
				var html="<label class='mt-radio'>";
				html+="<input type='radio' name='";
				html+=radioParam.name;
				html+="' value='" ;
				html+= n[radioParam.valueField];
				html+= "'>";
				html+=n[radioParam.textField];
				html+="<span></span>";
				html+="</label>";
				
				$("#" + radioParam.containerId).append(html);
			});

			return false;
		},

	};

	universalAjax(ajaxParamter);

}


function initCommonFormPage(formId, ajaxParam) {

	$("#backButton").click(function() {
		self.location = ajaxParam.backUrl;
	});
	var id = request(RECORD_ID);
	var form = $("#" + formId);
	if (id != undefined && id != null && $("#textRecordId") != undefined && $("#textRecordId") != null)
		$("#textRecordId").val(id);

	$("#submitButton").click(function() {
		var formString = form.serializeArray();
		$.ajax({
			type : "POST",
			dataType : "JSON",
			data : formString,
			url : ajaxParam.ajaxUrl,
			success : function(message) {
				if (!message["isSuccess"]) {
					layer.alert(message["resultMessage"], {
						icon : 6
					});
					return false;
				}

				window.location.href = ajaxParam.overTargetUrl;
				return false;
			},
			error : ajaxErrorFunction
		});

	});
}

/**
 * Zee 通用ajax处理方法
 * 
 * @param ajaxParamter
 */
function universalAjax(ajaxParameter) {

	if (ajaxParameter.url == null) {
		layer.alert("请求链接不能为空！", {
			icon : 6
		});
		return;
	}
	if (ajaxParameter.crossDomain == null)
		ajaxParameter.crossDomain = false;
	if (ajaxParameter.type == null)
		ajaxParameter.type = "POST";
	if (ajaxParameter.contentType == null)
		ajaxParameter.contentType = "application/json;charset=utf-8";
	if (ajaxParameter.dataType == null)
		ajaxParameter.dataType = "JSON";
	if (ajaxParameter.async == null)
		ajaxParameter.async = true;
	if (ajaxParameter.success == null)
		ajaxParameter.success = ajaxSuccessFunction;
	if (ajaxParameter.error == null)
		ajaxParameter.error = ajaxErrorFunction;
	if (ajaxParameter.headers == null) {
		var dataStr = localStorage.getItem("token");
		ajaxParameter.headers = {
			"Authorization" : "Bearer " + JSON.parse(dataStr).accessToken,
			"ClientId" : JSON.parse(dataStr).clientId
		};
	}

	var url = ajaxParameter.url;
	var data = ajaxParameter.data;

	url = INTERFACE_SERVER + url;

	$.ajax({
		url : url,
		type : ajaxParameter.type,
		contentType : ajaxParameter.contentType,
		headers : ajaxParameter.headers,
		data : data,
		dataType : ajaxParameter.dataType,
		async : ajaxParameter.async,
		error : ajaxParameter.error,
		beforeSend : ajaxParameter.beforeSend,
		success : ajaxParameter.success
	});
}

function convertToColumnName(property) {
	for (i = 0; i < property.length; i++) {
		if (/[A-Z]/.test(property.charAt(i)))
			property = property.replace(property.charAt(i), '_' + property.charAt(i).toLowerCase());
	}
	return property;
}

function popUpPage(pageParam) {
	var width = "800px";
	var height = $(window).height() - 50 + 'px';
	var offsetTop = '20px';
	var offsetRight = "";

	if (typeof (pageParam.width) != "undefined")
		width = pageParam.width;
	if (typeof (pageParam.height) != "undefined") {
		height = pageParam.height;
		offsetTop = ($(window).height() - pageParam.height) / 2 + 'px';
	}
	if (typeof (pageParam.offsetTop) != "undefined")
		offsetTop = pageParam.offsetTop;
	if (typeof (pageParam.offsetRight) != "undefined")
		offsetRight = pageParam.offsetRight;

	layer.open({
		type : 2,
		title : pageParam.title,
		content : pageParam.url,
		area : [ width, height ],
		offset : [ offsetTop, offsetRight ]
	});
}

/**
 * Zee 接受页面传递的参数
 * 
 * @param name
 *            传递参数
 * @returns 参数值
 */
function request(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

/**
 * Zee ajax方法默认的失败回调函数
 * 
 * @param XMLHttpRequest
 * @param textStatus
 * @param errorThrown
 */
function ajaxErrorFunction(XMLHttpRequest, textStatus, errorThrown) {
	layer.closeAll();
	if (XMLHttpRequest.responseText != null && XMLHttpRequest.responseText != "") {
		var result = JSON.parse(XMLHttpRequest.responseText)
		layer.alert(result.resultMessage, {
			icon : 6
		});
	} else {
		layer.alert("调用后台接口时出现错误！" + textStatus + " " + errorThrown, {
			icon : 6
		});
	}
}

function noResult() {
	$("#totalCountSpan").text(0);
	$("#pageIndexSpan").text(0 + "/" + 0);
}

function dynamicRules(pageParam) {

	var validateResult = true;
	var dr = pageParam.dynamicRules;
	if (dr != "" && dr != null && dr != undefined) {

		var len = dr.length;
		for (var i = 0; i < len; i++) {

			$("#errorBox_" + dr[i].slave).hide();
			if ($("#" + dr[i].master).val() == dr[i].value) {
				if ($("#" + dr[i].slave).val() == "") {
					$("#errorBox_" + dr[i].slave).show();
					validateResult = false;
				}
			}
		}
		if (!$("#errorBox_ImgFormVerification").is(":hidden")) {
			$("#errorBox_hiddenImgPath").hide();
		}
	}
	return validateResult;
}

function ajaxRules(pageParam) {
	var passCheck = true;
	var rs = pageParam.ajaxRules;
	if (rs != "" && rs != null && rs != undefined) {
		for (var i = 0, len = rs.length; i < len; i++) {
			var rst = $("#errorBox_" + rs[i].eid).attr("passCheck");
			if (rst == "false") {
				passCheck = false;
				break;
			}
		}
	}
	return passCheck;

}

function lengthVerificationRules(pageParam) {
	var validateResult = true;
	var dr = pageParam.lengthVerificationRules;
	if (dr != "" && dr != null && dr != undefined) {
		var len = dr.length;
		for (var i = 0; i < len; i++) {
			$("#errorBox_" + dr[i].promptId).hide();
			var len1 = $("#" + dr[i].elementId).val().length;
			var len2 = parseInt(dr[i].lengthRestrict);
			if ($("#" + dr[i].elementId).val().length >= parseInt(dr[i].lengthRestrict)) {
				validateResult = false;
				$("#errorBox_" + dr[i].promptId).show();
			}
		}
	}
	return validateResult;
}

function promptElementRules(pageParam) {
	var validateResult = true;
	var dr = pageParam.promptElementRules;
	if (dr != "" && dr != null && dr != undefined) {
		var len = dr.length;
		for (var i = 0; i < len; i++) {
			if (!($("#" + dr[i].elementId).is(":hidden"))) {
				validateResult = false;
			}
		}
	}
	return validateResult;
}

function initAddPage(pageParam, ajaxParam) {

	var formAdd = $('#' + pageParam.formId);
	var errorMessage = $('.alert-danger', formAdd);
	var successMessage = $('.alert-success', formAdd);
	var resultAjaxData;

	formAdd.on("submit", function() {
		for ( var e in CKEDITOR.instances)
			CKEDITOR.instances[e].updateElement();
		dynamicRules(pageParam);
		lengthVerificationRules(pageParam);
		promptElementRules(pageParam);
	});

	formAdd.validate({
		errorClass : "help-block",
		rules : pageParam.validateRules,
		messages : pageParam.validateMessages,
		ignore : '',
		errorPlacement : function(e, r) {

			r.attr("data-error-container") ? e.appendTo(r.attr("data-error-container")) : e.insertAfter(r)
		},
		highlight : function(element) {
			$(element).closest('.element-group').addClass('has-error');
		},

		unhighlight : function(element) {
			$(element).closest('.element-group').removeClass('has-error');
		},

		success : function(label) {
			label.closest('.element-group').removeClass('has-error');
		},

		submitHandler : function(form) {
			if (!dynamicRules(pageParam)) {
				return;
			}
			if (!ajaxRules(pageParam)) {
				return;
			}
			if (!lengthVerificationRules(pageParam)) {
				return;
			}
			if (!promptElementRules(pageParam)) {
				return;
			}
			successMessage.show();
			errorMessage.hide();
			var formData = formAdd.serializeArray();
			// 将查询条件和其它请求参数组装
			if (ajaxParam.submitData != null)
				$.each(formData, function(i, n) {
					var propertyName = getPropertyName(formData[i].name)
					ajaxParam.submitData[propertyName] = formData[i].value;
				});

			if (ajaxParam.type == null)
				ajaxParam.type = "POST";
			if (ajaxParam.contentType == null)
				ajaxParam.contentType = "application/json;charset=utf-8";
			if (ajaxParam.contentType === "application/json;charset=utf-8")
				ajaxParam.submitData = JSON.stringify(ajaxParam.submitData);
			if (ajaxParam.contentType === "application/x-www-form-urlencoded")
				ajaxParam.submitData = "jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.submitData));
			if (ajaxParam.dataType == null)
				ajaxParam.dataType = "JSON";
			if (ajaxParam.async == null)
				ajaxParam.async = true;
			if (ajaxParam.success == null)
				ajaxParam.success = function(resultData) {
					resultAjaxData = resultData;
					if (!resultData["isSuccess"]) {
						alert(resultData["resultMessage"]);
						return false;
					}

					layer.msg('记录添加成功，即将跳回列表页……', {
						time : 1000
					});
					setTimeout("$('#navbarListA').click();", 1100);

				};
			if (ajaxParam.error == null)
				ajaxParam.error = ajaxErrorFunction;

			var ajaxParamter = {
				"url" : ajaxParam.url,
				"data" : ajaxParam.submitData,
				"dataType" : ajaxParam.dataType,
				"contentType" : ajaxParam.contentType,
				"type" : ajaxParam.type,
				"async" : ajaxParam.sync,
				"success" : ajaxParam.success,
				"error" : ajaxParam.error
			};

			universalAjax(ajaxParamter);
		}

	});

	$("#buttonBack").click(function() {
		history.back();
		return false;
	});
	if (!ajaxParam.async)
		return resultAjaxData;
	return null;

}

function getPropertyName(fieldName) {

	var array = fieldName.split("");
	var prefix = null;
	for (var n = 0; n < array.length; n++) {
		if (array[n].toLocaleString().charCodeAt(0) >= 65 && array[n].toLocaleString().charCodeAt(0) <= 90)// 第一个大写字母
		{
			prefix = fieldName.substr(0, n);
			break;
		}
	}
	var tagLength = prefix == null ? 0 : prefix.length;
	var prop = fieldName.substr(tagLength);
	prop = prop.substr(0, 1).toLowerCase() + prop.substr(1);
	return prop;
}

function initEditPage(pageParam, ajaxParam) {
	var resultAjaxData;
	var formEdit = $('#' + pageParam.formId);
	var errorMessage = $('.alert-danger', formEdit);
	var successMessage = $('.alert-success', formEdit);
	var selectRowsCookie = Cookies.get("selectRows");
	var id = request(RECORD_ID);
	var isUpdateList = false;
	if (selectRowsCookie != null && id == null)
		isUpdateList = true;

	if (isUpdateList) {
		pageParam.validateRules = {};
	}

	formEdit.on("submit", function() {
		for ( var e in CKEDITOR.instances)
			CKEDITOR.instances[e].updateElement();
		dynamicRules(pageParam);
		lengthVerificationRules(pageParam);
		promptElementRules(pageParam);
	});

	formEdit.validate({
		errorClass : 'help-block',
		rules : pageParam.validateRules,
		messages : pageParam.validateMessages,
		ignore : '',
		errorPlacement : function(e, r) {

			r.attr("data-error-container") ? e.appendTo(r.attr("data-error-container")) : e.insertAfter(r)
		},
		highlight : function(element) {
			$(element).closest('.element-group').addClass('has-error');
		},

		unhighlight : function(element) {
			$(element).closest('.element-group').removeClass('has-error');
		},
		success : function(label) {
			label.closest('.element-group').removeClass('has-error');
		},

		submitHandler : function(form) {

			if (!dynamicRules(pageParam)) {
				return;
			}
			if (!ajaxRules(pageParam)) {
				return;
			}
			if (!lengthVerificationRules(pageParam)) {
				return;
			}
			if (!promptElementRules(pageParam)) {
				return;
			}
			successMessage.show();
			errorMessage.hide();

			var formData = formEdit.serializeArray();
			// 将查询条件和其它请求参数组装
			if (ajaxParam.submitData != null)
				$.each(formData, function(i, n) {
					var propertyName = getPropertyName(formData[i].name);
					ajaxParam.submitData[propertyName] = formData[i].value;
				});

			if (ajaxParam.type == null)
				ajaxParam.type = "POST";
			if (ajaxParam.contentType == null)
				ajaxParam.contentType = "application/json;charset=utf-8";

			if (isUpdateList) {
				ajaxParam.submitData = {
					"entity" : ajaxParam.submitData,
					"idList" : JSON.parse(selectRowsCookie)
				};
				ajaxParam.url = ajaxParam.updateListUrl;
			}
			if (ajaxParam.contentType === "application/json;charset=utf-8")
				ajaxParam.submitData = JSON.stringify(ajaxParam.submitData);
			// 提交富文本数据，如果包含特殊符号"&"，到后台的数据会被截断，所以用encodeURIComponent。
			if (ajaxParam.contentType === "application/x-www-form-urlencoded")
				ajaxParam.submitData = "jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.submitData));
			if (ajaxParam.dataType == null)
				ajaxParam.dataType = "JSON";
			if (ajaxParam.async == null)
				ajaxParam.async = true;
			if (ajaxParam.success == null)
				ajaxParam.success = function(resultData) {
					if (!resultData["isSuccess"]) {
						alert(resultData["resultMessage"]);
						return false;
					}

					layer.msg('记录修改成功，即将跳回列表页……', {
						time : 1000
					});

					if (isUpdateList) {
						setTimeout("closeLayer();", 1100);
						Cookies.remove("selectRows");
						parent.location.reload(); // 父页面刷新
					} else {
						setTimeout("$('#navbarListA').click();", 1100);
					}
				};
			if (ajaxParam.error == null)
				ajaxParam.error = ajaxErrorFunction;

			var ajaxParamter = {
				"url" : ajaxParam.url,
				"data" : ajaxParam.submitData,
				"dataType" : ajaxParam.dataType,
				"contentType" : ajaxParam.contentType,
				"type" : ajaxParam.type,
				"async" : ajaxParam.async,
				"success" : ajaxParam.success,
				"error" : ajaxParam.error
			};
			universalAjax(ajaxParamter);
		}
	});

	$("#buttonBack").click(function() {
		history.back();
		return false;
	});

	if (isUpdateList) {
		$("span.required").remove();
		$(".page-sidebar-wrapper").remove();
		$(".page-header").remove();
		$(".page-bar").remove();
		$(".page-footer").remove();
		$("#buttonBack").click(function() {

			closeLayer();

			return false;
		});
		return null;
	}

	// 初始化页面标签
	var ajaxParamter = {
		"url" : ajaxParam.getModelUrl + "/" + id,
		"type" : "GET",
		"async" : true,
		"success" : function(resultData) {
			resultAjaxData = resultData;
			if (!resultData["isSuccess"]) {
				alert(resultData["resultMessage"]);
				return false;
			}
			var ajaxData = resultData.data;

			if (ajaxData.imgPath != null) {
				$("#imgPath").attr("src", ajaxData.imgPath);
				$("#new").hide();
				$("#exists").show();
				$("#move").show();
			}
			var form = document.forms[pageParam.formId];
			// 遍历指定form表单所有元素
			for (var i = 0; i < form.length; i++) {
				var fieldName = form[i].name;
				var array = fieldName.split("");
				var prefix = null;
				for (var n = 0; n < array.length; n++) {
					if (array[n].toLocaleString().charCodeAt(0) >= 65 && array[n].toLocaleString().charCodeAt(0) <= 90)// 第一个大写字母
					{
						prefix = fieldName.substr(0, n);
						break;
					}
				}

				var tagLength = prefix == null ? 0 : prefix.length;
				var prop = fieldName.substr(tagLength);
				prop = prop.substr(0, 1).toLowerCase() + prop.substr(1);

				var value = ajaxData[prop];

				switch (prefix) {
				case "hidden":
					$("[name='" + fieldName + "']").val(value);
					break;
				case "text":
					$("[name='" + fieldName + "']").val(value);
					break;
				case "select":
					$("select[name='" + fieldName + "']").val(value);
					break;
				case "radio":
					if (value != null)
						$("[name='" + fieldName + "'][value='" + value + "']").get(0).checked = true;
					break;
				case "textarea":
					$("textarea[name='" + fieldName + "']").val(value);
					break;
				case "checkbox":
					$("[name='" + fieldName + "'][value='" + value + "']").get(0).checked = true;
					break;
				default:
					break;
				}
			}
		}
	};
	if (ajaxParam.getModelAsync != null)
		ajaxParamter.async = ajaxParam.getModelAsync;
	universalAjax(ajaxParamter);

	if (!ajaxParam.getModelAsync)
		return resultAjaxData;
	return null;

}

function closeLayer() {
	try {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	} catch (e) {
		return undefined;
	}
}

/**
 * Zee 初始化详细信息页面
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 */
function initDetailPage(pageParam, ajaxParamter) {
	$("#buttonBack").click(function() {
		history.back();
		return false;
	});
	var id = request("id");
	var resultAjaxData;
	if (id == null) {
		layer.alert("未能获取到主键信息……", {
			icon : 6
		});
		return;
	}
	if (ajaxParamter.url == null) {
		layer.alert("请求的链接地址不能为空……", {
			icon : 6
		});
		return;
	}
	ajaxParamter.url = ajaxParamter.url + "/" + id;
	if (ajaxParamter.type == null)
		ajaxParamter.type = "GET";
	if (ajaxParamter.dataType == null)
		ajaxParamter.dataType = "JSON";
	if (ajaxParamter.async == null)
		ajaxParamter.async = true;
	if (ajaxParamter.success == null)
		ajaxParamter.success = function(resultData) {
			if (!resultData["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon : 6
				});
				return;
			}

			$("#" + pageParam.formId + " label").each(function(i, n) {
				// 遍历指定form表单中的所有label标签
				var fieldId = $(n).attr("id");
				var prefix = fieldId.substr(5);
				prefix = prefix.substr(0, 1).toLowerCase() + prefix.substr(1);
				$(n).html(resultData.data[prefix]);
			});
			$("#" + pageParam.formId + " img").each(function(i, n) {
				// 遍历指定form表单中的所有图像标签
				var fieldId = $(n).attr("id");
				var prefix = fieldId.substr(3);
				prefix = prefix.substr(0, 1).toLowerCase() + prefix.substr(1);
				if (resultData.data[prefix] != null)
					// $(n).attr("src", siteName + resultData.data[prefix]);
					$(n).attr("src", resultData.data[prefix]);
			});

			resultAjaxData = resultData;

		};
	if (ajaxParamter.error == null)
		ajaxParamter.error = ajaxErrorFunction;

	universalAjax(ajaxParamter);
	// 只有同步请求才能将获取的值返回
	if (!ajaxParamter.async)
		return resultAjaxData;
	return null;
}

function historyBack() {
	history.back();
	return false;
}


// 初始化AutoComplete输入框
function initAutoComplete(itemParam, ajaxParam) {
	var autoCompleteCache = {};
	// 失去输入焦点后，如果显示值为空，则同时清空隐藏值
	$("#" + itemParam.textFieldInputId).unbind("blur");
	$("#" + itemParam.textFieldInputId).bind("blur", function() {
		if ($("#" + itemParam.textFieldInputId).val() == "") {
			$("#" + itemParam.textFieldInputId).val("");
			$("#" + itemParam.valueFieldInputId).val("");
		} else {
			// 如果是已经存在缓存中的关键字，也就是输入到文本框中的文字是不能提交的，只能选择后才能提交
			if (($("#" + itemParam.textFieldInputId).val() in autoCompleteCache) || $("#" + itemParam.textFieldInputId).val().toString().length == 1) {
				$("#" + itemParam.textFieldInputId).val("");
				$("#" + itemParam.valueFieldInputId).val("");
			}
		}
	});

	$("#" + itemParam.textFieldInputId).autocomplete({
		minLength : 2,
		autoFocus : true,
		source : function(request, response) {
			var term = request.term;
			if (term in autoCompleteCache) {
				response($.map(autoCompleteCache[term], function(item) {
					return {
						value : item[itemParam.textField],
						label : item[itemParam.textField],
						submitValue : item[itemParam.valueField]
					}
				}));
				return;
			}
			// 将关键字赋予模糊查询的键
			ajaxParam.jsonData.entityRelated.autoCompleteKey = request.term;

			var ajaxParamter = {
				"url" : ajaxParam.url + "?jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.jsonData)),
				"async" : true,
				"type" : "GET",
				"success" : function(resultData) {
					autoCompleteCache[term] = resultData.data;
					response($.map(resultData.data, function(item) {
						return {
							value : item[itemParam.textField],
							label : item[itemParam.textField],
							submitValue : item[itemParam.valueField]
						}
					}));
				}
			};
			universalAjax(ajaxParamter);

		},
		select : function(e, ui) {
			$("#" + itemParam.valueFieldInputId).val(ui.item.submitValue);
		}
	});
}

function printPage() {
	bdhtml = window.document.body.innerHTML;// 获取当前页的html代码
	sprnstr = "<!--startprint-->";// 设置打印开始区域
	eprnstr = "<!--endprint-->";// 设置打印结束区域
	prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); // 从开始代码向后取html

	prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));// 从结束代码向前取html
	window.document.body.innerHTML = prnhtml;
	window.print();
	window.document.body.innerHTML = bdhtml;
}

// 读取cookies
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

	if (arr = document.cookie.match(reg)) {

		return unescape(arr[2]);
	} else {
		return null;
	}
}

// 删除cookies
function delCookie(name) {
	setCookie(name, "");
}

// 读取localStorage
function getStorage(name) {
	var dataStr = localStorage.getItem(name);
	var data = JSON.parse(dataStr)
	return data
}

// 删除localStorage
function delStorage(name) {
	localStorage.setItem(name, '')
}

// 读取数据
function readData(name) {
	if (window.localStorage) {
		getStorage(name)
	} else {
		getCookie(name)
	}
}

// 删除数据
function delData(name) {
	if (window.localStorage) {
		delStorage(name)
	} else {
		delCookie(name)
	}
}

// 跳转首页
function validateLogin(resultCode) {
	if (resultCode === RESULT_CODE_TOKEN_EXPIRED && window.location.pathname.indexOf("/lo/Login.html") == -1) {
		localStorage.removeItem("token");
		location.href = '../lo/Login.html';
		return fasle;
	}
	$("#goHome").click(function() {
		location.href = HOME_PATH + RP_ININDEX
	});

	if (getStorage("token")) {
		var token = getStorage("token");
		$("#userName").text(token.userName);
		if (token.gpUser != null && token.gpUser.icon != null)
			$("#userIcon").attr("src", token.gpUser.icon)
		if (new Date(token.adeadTime) >= new Date())
			return true;
	}
	if (window.location.pathname.indexOf("/lo/Login.html") == -1) {
		location.href = '../lo/Login.html';
	}

	return false;
}

function initMessage() {
	if (!getStorage("token"))
		return false;

	var token = getStorage("token");
	var userInfo = token.gpUser;
	$("#userName").text(userInfo.userName);

	var ajaxParameter = {
		"url" : "/extend/swagger/gp/gprMessageUser/getSysListByJsonData",
		"data" : "jsonData=" + JSON.stringify({
			"entityRelated" : {
				"userName" : token.userName,
				"userId" : token.userId
			}
		}),
		"dataType" : "json",
		"type" : "GET",
		"async" : false,
		"success" : function(res) {
			if (!validateLogin(res.resultCode))
				return false;
			if (res.totalCount == 0) {
				$("#header_notification_bar .dropdown-menu").hide();
				$("#header_notification_bar .badge").hide();
				return false;
			}

			var html = '';
			$("#header_notification_bar").find("span").text(res.totalCount);
			$("#systematic").text(res.totalCount);
			/*
			 * $("#systematic-time").text(res.data[0].addTime)
			 * $("#systematic-detiles").text(res.data[0].content)
			 */
			for (var i = 0; i < res.data.length; i++) {
				html += '<li>' + '<a href="/pc/ss/gp/html/gp/MessageList.html">' + '<span class="time" id="systematic-time">' + res.data[i].addTime + '</span>' + '<span class="details" style="width: 160px;display: inline-block;">' + '<span class="label label-sm label-icon label-warning">' + '<i class="fa fa-bell-o"></i>' + res.data[i].content + '</span>'
				'</span>' + '</a>' + '</li>'
			}
			$("#systematic-detiles").html(html)
		}
	};
	universalAjax(ajaxParameter);

	ajaxParameter = {
		"url" : "/extend/swagger/gp/gprMessageUser/getUserListByJsonData",
		"dataType" : "json",
		"type" : "GET",
		"async" : false,
		"data" : "jsonData=" + JSON.stringify({
			"entityRelated" : {
				"userName" : token.userName,
				"userId" : token.userId
			}
		}),
		success : function(res) {
			if (res.totalCount == 0) {
				$("#header_inbox_bar .dropdown-menu").hide();
				$("#header_inbox_bar .badge").hide();
			}
			var html = '';
			$("#header_inbox_bar").find("span").text(res.totalCount);
			$("#userInfo").text(res.totalCount);
			for (var i = 0; i < res.data.length; i++) {
				html += '<li>' + '<a href="/pc/ss/gp/html/gp/MessageList.html">' + '<span class="photo">' + '<img src="../../assets/layouts/layout3/img/avatar2.jpg" class="img-circle" alt="">' + '</span>' + '<span class="subject">' + '<span class="from"> ' + res.data[i].userName + '</span>' + '<span class="time">' + res.data[i].addTime + '</span>' + '</span>' + '<span class="message"> ' + res.data[i].content + '</span>' + '</a>' + '</li>'
			}
			$("#userInfo-detiles").html(html)
		}
	};
	universalAjax(ajaxParameter);

}

/** *******************************************文件上传相关方法******************************************* */
// 多文件上传控件 接受后台数据后 初始化
function initAddFileInput() {
	// 初始化上传控件的样式
	var $Control = $("#fileIcons").fileinput({
		language : 'zh',
		theme : 'fa',
		showRemove : false,
		showZoom : false,
		showDrag : false,
		showUpload : false,
		showCaption : false,
		ajaxSettings : {
			headers : {
				'Authorization' : "Bearer " + JSON.parse(localStorage.getItem("token")).accessToken
			}
		},
		uploadUrl : INTERFACE_SERVER + "/extend/swagger/gp/gpResource/saveUploadFile",
		uploadAsync : true,
		browseClass : "btn btn-primary btn-lg",
		fileType : "image",
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		overwriteInitial : false,
		initialPreviewAsData : true

	});
	initFileInput($Control, "hiddenIconIds", "hiddenIconPaths");
}

function initEditFileInput(IconIdArray, IconPathArray) {
	var initialPreviewConfigArray = [];
	if (IconIdArray != null & IconPathArray != null) {
		for (var i = 0; i < IconIdArray.length; i++) {
			initialPreviewConfigArray[i] = {
				url : INTERFACE_SERVER + RU_GPRESOURCE_GETMODELBYPATH + IconIdArray[i]
			};
		}
	}
	// 初始化前先销毁上一个初始化，否则点击左侧功能模块，控制无法显示图片。同时取消绑定on事件，否则上传时会重复执行on事件。
	 $("#fileIcons").fileinput('destroy');
	 $("#fileIcons").unbind('on');
	 
	 
	var $Control = $("#fileIcons").fileinput({
		language : 'zh',
		theme : 'fa',
		showRemove : false,
		showZoom : false,
		showDrag : false,
		showUpload : false,
		showCaption : false,
		ajaxSettings : {
			headers : {
				'Authorization' : "Bearer " + JSON.parse(localStorage.getItem("token")).accessToken
			}
		},
		uploadUrl : INTERFACE_SERVER + "/extend/swagger/gp/gpResource/saveUploadFile",
		uploadAsync : true,
		browseClass : "btn btn-primary btn-lg",
		fileType : "image",
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		overwriteInitial : false,
		initialPreviewAsData : true,
		initialPreview : IconPathArray,
		initialPreviewConfig : initialPreviewConfigArray
	});
	initFileInput($Control, "hiddenIconIds", "hiddenIconPaths");
}

// 多文件上传控制 刚进入新增页面 初始化
function initFileInput(fileControl, hiddenResourceIdsControl, hiddenResourcePathsControl) {
	$(fileControl).on('filepreupload', function(event, data, previewId, index) {
		for (var i = 0; i < data.files.length; i++) {
			var file = data.files[i];
			if (file.name.length > 100) {
				layer.alert("文件名不能超过100个字符！" + data.result.resultMessage, {
					icon : 6
				});
				return false;
			}
			if (file.name.indexOf(',') != -1) {
				layer.alert("文件名中不能包含字符“,”" + data.result.resultMessage, {
					icon : 6
				});
				return false;
			}
		}
	}).on('filebatchselected', function(event, data, id, index) {
		$(this).fileinput("upload");
	}).on("fileuploaded", function(event, data, previewId, index) {

		// 清除元素
		$("#fileTitleImage-error").remove();
		// 图片格式校验
		var gpResource = data.response.data;
		if (!data.response.isSuccess) {
			layer.alert("上传标题图片出错！" + data.response.resultMessage, {
				icon : 6
			});
			return false;
		}

		$("#" + previewId).attr("resourceId", gpResource[0].id);
		$("#" + previewId).attr("resourcePath", gpResource[0].path);

		var resourceIdList = $("#" + hiddenResourceIdsControl).val();
		if (resourceIdList.endsWith(","))
			resourceIdList += gpResource[0].id + ",";
		else
			resourceIdList += "," + gpResource[0].id + ",";
		var resourcePathList = $("#" + hiddenResourcePathsControl).val();
		if (resourcePathList.endsWith(","))
			resourcePathList += gpResource[0].path + ",";
		else
			resourcePathList += "," + gpResource[0].path + ",";

		$("#" + hiddenResourceIdsControl).val(resourceIdList);
		$("#" + hiddenResourcePathsControl).val(resourcePathList);
		return true;
	}).on('filebatchuploadcomplete', function(event, files, extra) {
		var resourceIdList = $("#" + hiddenResourceIdsControl).val();
		var resourcePathList = $("#" + hiddenResourcePathsControl).val();
		if (resourceIdList.endsWith(","))
			resourceIdList = resourceIdList.substr(0, resourceIdList.length - 1);
		if (resourceIdList.startsWith(","))
			resourceIdList = resourceIdList.substr(1, resourceIdList.length);
		if (resourcePathList.startsWith(","))
			resourcePathList = resourcePathList.substr(1, resourcePathList.length);
		if (resourcePathList.endsWith(","))
			resourcePathList = resourcePathList.substr(0, resourcePathList.length - 1);

		$("#" + hiddenResourceIdsControl).val(resourceIdList);
		$("#" + hiddenResourcePathsControl).val(resourcePathList);
	}).on("filesuccessremove", function(event, id, index) {

		var resourceIdList = $("#" + hiddenResourceIdsControl).val();
		var resourceId = $("#" + id).attr("resourceId");
		var resourceIdShu = $("#" + id).attr("resourceId") + ",";
		if (resourceIdList.indexOf(resourceIdShu) != -1)
			resourceIdList = resourceIdList.replace(resourceIdShu, "");
		if (resourceIdList.indexOf(resourceId) != -1)
			resourceIdList = resourceIdList.replace(resourceId, "");

		$("#" + hiddenResourceIdsControl).val(resourceIdList);

		var resourcePathList = $("#" + hiddenResourcePathsControl).val();
		var resourcePath = $("#" + id).attr("resourcePath");
		var resourcePathShu = $("#" + id).attr("resourcePath") + ",";
		if (resourcePathList.indexOf(resourcePathShu) != -1)
			resourcePathList = resourcePathList.replace(resourcePathShu, "");
		if (resourcePathList.indexOf(resourcePath) != -1)
			resourcePathList = resourcePathList.replace(resourcePath, "");

		$("#" + hiddenResourcePathsControl).val(resourcePathList);
	}).on('fileremoved', function(event, id, index) {

	}).on('filedeleted', function(event, key, jqXHR, data) {
		var result = jqXHR.responseJSON;
		if (!result.isSuccess) {
			layer.alert("删除图片出错！" + result.resultMessage, {
				icon : 6
			});
			return false;
		}
		var resourceId = result.data.id;
		var resourceIdShu = resourceId + ",";
		var resourceIdList = $("#" + hiddenResourceIdsControl).val();
		if (resourceIdList.indexOf(resourceIdShu) != -1)
			resourceIdList = resourceIdList.replace(resourceIdShu, "");
		if (resourceIdList.indexOf(resourceId) != -1)
			resourceIdList = resourceIdList.replace(resourceId, "");
		$("#" + hiddenResourceIdsControl).val(resourceIdList);

		var resourcePath = result.data.path;
		var resourcePathList = $("#" + hiddenResourcePathsControl).val();
		var resourcePathShu = resourcePath + ",";
		if (resourcePathList.indexOf(resourcePathShu) != -1)
			resourcePathList = resourcePathList.replace(resourcePathShu, "");
		if (resourcePathList.indexOf(resourcePath) != -1)
			resourcePathList = resourcePathList.replace(resourcePath, "");
		$("#" + hiddenResourcePathsControl).val(resourcePathList);

	});

}


/** *******************************************文件上传相关方法******************************************* */




/** *******************************************树形菜单相关方法******************************************* */


// 初始化详情页中的zTree插件
function initDetailTree(treeParam) {

	// 组织机构树形结构begin
	var setting = {
		check : {
			enable : false
		},
		view : {
			showIcon : false,
			showLine : true,
			selectedMulti : false
		},

		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "fartherId"
			}
		}
	};
	

	var treeNodes=[];
	if(treeParam.initNodes)
		treeNodes=treeParam.initNodes;
	var ajaxParamter = {
		"url" : treeParam.url,
		"type" : "GET",
		"async" : true,
		"success" : function(resultData) {
			treeNodes = treeNodes.concat(resultData.data);
			
			// 将一级功能模块的fartherId都变为应用领域的ID，级别变为0，否则功能模块无法依附应用领域
			$.each(treeNodes, function(index, value) {
				if(value.fartherId==null){
					value.levelCode=0;
					value.fartherId=value.domainId;
					treeNodes[index]=value;
				}
			});
			var orgnaizationTree = $.fn.zTree.init($("#" + treeParam.container), setting, treeNodes);

				$.each(treeNodes, function(index, value) {
					
					if (treeParam.expandNodeLevel == null || value.levelCode < treeParam.expandNodeLevel) {
						var node = orgnaizationTree.getNodeByParam("id", value.id);
						orgnaizationTree.expandNode(node, true);// 展开指定节点
					}
				});
		}
	};

	universalAjax(ajaxParamter);
}



var className = "dark";
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
		var level=treeNodes[i].level;
		if ((pid == "root" || pid == null || pid == "null")&level==0) {
			layer.msg('根节点不能移动……', {
				time : 1000
			});
			return false;
		}
	}
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {

	if(targetNode.level==0){
		layer.msg('根节点为应用领域，不能移动到根节点……', {
			time : 1000
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
function beforeClick(treeId, treeNode,clickFlag) {
	
	if(treeNode.level==0){
		layer.msg('根节点为应用领域，不能修改……', {
			time : 1000
		});
		return false;
	}
	return true;
}
function onClick(e, treeId, treeNode) {

	var pageParam = {
		formId : "formEdit",
		validateRules : {
			textDomainId : {
				required : true
			},
			textName : {
				required : true
			},
			selectLevelCode : {
				required : true
			},
			textPriority : {
				digits : true
			}
		}
	};
	var ajaxParam = {
		recordId : treeNode.id,
		getModelAsync : false,
		url : RU_GPMODULE_UPDATE,
		getModelUrl : RU_GPMODULE_GETMODELBYPATH,
		submitData : {}
	};
	
	var initResult = initZTreeEditForm(pageParam, ajaxParam);
	if (!initResult.isSuccess) {
		layer.alert("查询信息错误" + initResult.resultMessage, {
			icon : 6
		});
		return;
	}
	if (initResult.data.iconIds != null){
		 initEditFileInput(initResult.data.iconIds.split(","), initResult.data.iconPaths.split(","));
	}
}

function initZTreeEditForm(pageParam, ajaxParam) {
	var resultAjaxData;
	var formEdit = $('#' + pageParam.formId);
	var errorMessage = $('.alert-danger', formEdit);
	var successMessage = $('.alert-success', formEdit);
	var selectRowsCookie = Cookies.get("selectRows");
	var id = ajaxParam.recordId;
	
	// 添加重置按钮事件，重置的动作类似于重新加载
	$("#buttonReset").unbind('click');
	$("#buttonReset").click(function(){
		var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
		var selectNodes=zTree.getSelectedNodes();
		if(selectNodes!=null&&selectNodes.length!=0)
			$('#' + selectNodes[0].tId + '_a').trigger('click');
		
		layer.msg('表单已重置……', {
			time : 1000
		});
	});

	formEdit.on("submit", function() {
		for ( var e in CKEDITOR.instances)
			CKEDITOR.instances[e].updateElement();
	});

	formEdit.validate({
		errorClass : 'help-block',
		rules : pageParam.validateRules,
		messages : pageParam.validateMessages,
		ignore : '',
		errorPlacement : function(e, r) {
			r.attr("data-error-container") ? e.appendTo(r.attr("data-error-container")) : e.insertAfter(r)
		},
		highlight : function(element) {
			$(element).closest('.element-group').addClass('has-error');
		},

		unhighlight : function(element) {
			$(element).closest('.element-group').removeClass('has-error');
		},
		success : function(label) {
			label.closest('.element-group').removeClass('has-error');
		},

		submitHandler : function(form) {

			var formData = formEdit.serializeArray();
			// 将查询条件和其它请求参数组装
			if (ajaxParam.submitData != null)
				$.each(formData, function(i, n) {
					var propertyName = getPropertyName(formData[i].name);
					ajaxParam.submitData[propertyName] = formData[i].value;
				});

			if (ajaxParam.type == null)
				ajaxParam.type = "POST";
			if (ajaxParam.contentType == null)
				ajaxParam.contentType = "application/json;charset=utf-8";
			if (ajaxParam.contentType === "application/json;charset=utf-8")
				ajaxParam.submitData = JSON.stringify(ajaxParam.submitData);
			// 提交富文本数据，如果包含特殊符号"&"，到后台的数据会被截断，所以用encodeURIComponent。
			if (ajaxParam.contentType === "application/x-www-form-urlencoded")
				ajaxParam.submitData = "jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.submitData));
			if (ajaxParam.dataType == null)
				ajaxParam.dataType = "JSON";
			if (ajaxParam.async == null)
				ajaxParam.async = true;
			if (ajaxParam.success == null)
				ajaxParam.success = function(resultData) {
					if (!resultData["isSuccess"]) {
						alert(resultData["resultMessage"]);
						return false;
					}
					
					// 更新当前节点，也可以用zTree.refresh();
					var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
					var node = zTree.getNodeByParam("id", ajaxParam.recordId);
					node.name=$("#textName").val();
					zTree.updateNode(node)
					
					layer.msg('记录修改成功……', {
						time : 1000
					});
					
					// 修改成功后要清空submitData函数，否则再次修改会出错
					ajaxParam.submitData={};
				};
			if (ajaxParam.error == null)
				ajaxParam.error = ajaxErrorFunction;

			var ajaxParamter = {
				"url" : ajaxParam.url,
				"data" : ajaxParam.submitData,
				"dataType" : ajaxParam.dataType,
				"contentType" : ajaxParam.contentType,
				"type" : ajaxParam.type,
				"async" : ajaxParam.async,
				"success" : ajaxParam.success,
				"error" : ajaxParam.error
			};
			universalAjax(ajaxParamter);
		}
	});

	$("#buttonBack").click(function() {
		history.back();
		return false;
	});

	// 初始化页面标签
	var ajaxParamter = {
		"url" : ajaxParam.getModelUrl + "/" + id,
		"type" : "GET",
		"async" : true,
		"success" : function(resultData) {
			resultAjaxData = resultData;
			if (!resultData["isSuccess"]) {
				alert(resultData["resultMessage"]);
				return false;
			}
			var ajaxData = resultData.data;

			if (ajaxData.imgPath != null) {
				$("#imgPath").attr("src", ajaxData.imgPath);
				$("#new").hide();
				$("#exists").show();
				$("#move").show();
			}
			var form = document.forms[pageParam.formId];
			// 遍历指定form表单所有元素
			for (var i = 0; i < form.length; i++) {
				var fieldName = form[i].name;
				var array = fieldName.split("");
				var prefix = null;
				for (var n = 0; n < array.length; n++) {
					if (array[n].toLocaleString().charCodeAt(0) >= 65 && array[n].toLocaleString().charCodeAt(0) <= 90)// 第一个大写字母
					{
						prefix = fieldName.substr(0, n);
						break;
					}
				}

				var tagLength = prefix == null ? 0 : prefix.length;
				var prop = fieldName.substr(tagLength);
				prop = prop.substr(0, 1).toLowerCase() + prop.substr(1);

				var value = ajaxData[prop];

				switch (prefix) {
				case "hidden":
					$("[name='" + fieldName + "']").val(value);
					break;
				case "text":
					$("[name='" + fieldName + "']").val(value);
					break;
				case "select":
					$("select[name='" + fieldName + "']").val(value);
					break;
				case "radio":
					if (value != null)
						$("[name='" + fieldName + "'][value='" + value + "']").get(0).checked = true;
					break;
				case "textarea":
					$("textarea[name='" + fieldName + "']").val(value);
					break;
				case "checkbox":
					$("[name='" + fieldName + "'][value='" + value + "']").get(0).checked = true;
					break;
				default:
					break;
				}
			}
		}
	};
	if (ajaxParam.getModelAsync != null)
		ajaxParamter.async = ajaxParam.getModelAsync;
	universalAjax(ajaxParamter);

	if (!ajaxParam.getModelAsync)
		return resultAjaxData;
	return null;

}

function updateModulesData(treeId, treeNodes, action) {

	if (IS_IMMEDIATE) {
		immediateUpdate(treeId, treeNodes, action);
		return;
	}

	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var zTreeNodes = zTree.getNodes();
	var zTreeNodesJsonArray = zTree.transformToArray(zTreeNodes);
	// 修改数组长度为1，以达到删除其它节点只保留根目录节点的目的，因为根目录中已经用嵌套方式包含所有节点。
	zTreeNodesJsonArray.length=1;
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
			
			// 更新成功右侧名称同步更改
			if (action == "UPDATE") {
				$("#textName").val(treeNodes[0].name);
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
			levelCode : treeNodesArray[0].level,
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
		$.each(treeNodesArray, function(h, u) {
			var domainNode = getCurrentRootNode(treeNodesArray[h]);
           //拖拽会影响兄弟结点的排序		   
			var treeNodesBrotherArray=new Array();
			u.getParentNode()==null?treeNodesBrotherArray=treeNodesArray:treeNodesBrotherArray=u.getParentNode().children;  
			$.each(treeNodesBrotherArray, function(i, v) {
			
			var zTreeNodeJson = {
				id : v.id,
				cascadeTypeCode : cascade,
				domainId : domainNode.id,
				name : v.name,
				fartherId : v.fartherId,
				levelCode :v.level,
				priority : v.getIndex()
			}
			zTreeNodeJsonArray.push(zTreeNodeJson);
			});
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

/** *******************************************树形菜单相关方法******************************************* */



