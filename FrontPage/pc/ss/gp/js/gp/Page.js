/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  系统页面。 相关页面的js方法。
 */

$(document).ready(function () {


    //初始化应用领域下拉框
    var selectParam = {
        selectId: "selectTypeDomain",
        textField: "name",
        valueField: "id"
    };

    var ajaxParam = {
        url: RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData={}"
    }
    initDropDownList(selectParam, ajaxParam);

    //初始化是否为公共页面下拉框
    var selectParamAdmin = {
        selectId: "selectIsPublicCode",
        textField: "text",
        valueField: "code"
    };
    var ajaxParamAdmin = {
        url: RU_GPDICTIONARY_GETLISTBYTYPEID + "/" + DI_BOOLEAN
    }
    initDropDownList(selectParamAdmin, ajaxParamAdmin);

    //初始化列表页主体部分，包括查询条件表单及数据表格等。
    var pageParam = {
        formId: "queryBuilderForm",
        tableId: "contentTable",
        editPage: {
            title: "批量修改表单",
            url: RP_GPPAGE_EDIT
        },
        detailPage: {
            url: RP_GPPAGE_DETAIL
        },
        addPage: {
            url: RP_GPPAGE_ADD
        },
        deleteInterface: {
            url: RU_GPPAGE_DELETE
        },
        deleteListInterface: {
            url: RU_GPPAGE_DELETELIST
        },
        exportExcelInterface: {
            url: RU_GPPAGE_EXPORTEXCEL
        }

    };
    var ajaxParam = {
        url: RU_GPPAGE_GETLISTBYJSONDATA,
        type: "GET",
        submitData: {
            "entityRelated": {},
            "orderList": [{
                "columnName": "addTime",
                "isASC": false
            }],
            "page": {
                "pageIndex": DEFAULT_PAGE_INDEX,
                "pageSize": DEFAULT_PAGE_SIZE
            }
        },
        columnInfo: [

            {
                "columnName": "name",
                "columnText": "页面名称",
                "style": "text-align:left",
                "linkFunction": function (event) {
                    var href = RP_GPPAGE_DETAIL + "?" + RECORD_ID + "=" + event.id;
                    return href;
                },
            },
            {
                "columnName": "url",
                "columnText": "存放路径",
                "width":'300px',
                "style": "text-align:left",
            },
            {
                "columnName": "addTime",
                "columnText": "更新时间",
                "style": "text-align:center",
            },
            {
                "columnName": "isPublicCode",
                "columnText": "是否公共页面",
                "style": "text-align:center",
            }

        ]
    };

    var operationParam = [{
        "operationText": "修改",
        "buttonClass": "yellow",
        "iconClass": "fa fa-pencil-square-o",
        "clickFunction": function (event) {
            window.location.href = pageParam.editPage.url + "?" + RECORD_ID + "=" + event.data.id;
        },
        "visibleFunction": function (recordData) {
            if (recordData.status == "1")
                return false;
            return true;
        }
    }, {
        "operationText": "删除",
        "buttonClass": "red",
        "iconClass": "fa fa-trash-o",
        "clickFunction": function (event) {
            layer.confirm('您确定要删除当前记录？', {
                btn: ['确定', '取消']
            }, function () {
                layer.closeAll('dialog');
                ajaxParam.submitData.page.pageSize = $("#pageSizeText").val();
                ajaxParam.submitData.page.pageIndex = $("#pageIndexHidden").val();
                pageParam.deleteInterface.url = RU_GPPAGE_DELETE;
                pageParam.deleteInterface.type = "GET";
                pageParam.deleteInterface.submitData = {
                    "id": event.data.id,
                };
                deleteRecord(pageParam, ajaxParam, operationParam);
            });
        },
        "visibleFunction": function (recordData) {
            if (recordData.status == "1")
                return false;
            return true;
        }
    }];
    initQueryForm(pageParam, ajaxParam, operationParam);


    $("#updatePageConstantsButton").click(function () {

        layer.open({
            area: ['800px', '230px'],
            type: 1,
            closeBtn: true,
            shift: 7,
            shadeClose: true,
            content: "<div style='width:750px;text-align: center' class='form-inline'>"
            + "<div style='margin: 10px' class='form-group'><label class='form-label' style='font-weight:bold'>前端页面路径： </label><input size='60pt' id='textFrontPagePath' class='form-control' type='text' name='textFrontPagePath' value='E:\\JAVA\\JavaProject\\HuaPingMango\\代码\\PC\\FrontPage\\pc\\ss\\gp\\html'/></div>"
            + "<br/><div  style='margin: 10px' class='form-group'><span style='font-weight:bold'>JS    常量路径： </span><input size='60pt'  id='textJsConstantsPath' class='form-control' type='text' name='textJsConstantsPath' value='E:\\JAVA\\JavaProject\\HuaPingMango\\代码\\PC\\FrontPage\\pc\\global\\js\\constant\\Page.js'/></div>"
            + "<br/><div style='margin: 20px,0px,20px' class='form-group'><button style='margin-top:5%;' type='button' class='form-control btn btn-block  btn-lg red-mint' onclick='updatePageConstant()'>提交</button></div></div>"
        });
        return;


    });

});

function updatePageConstant() {
    var ajaxParamter = {
        "url": "/extend/swagger/gp/gpPage/updatePageConstants",
        "data": "jsonData=" + JSON.stringify({
            "jsConstantsPath": $("#textJsConstantsPath").val(),
            "frontPagePath": $("#textFrontPagePath").val()
        }),
        "type": "GET",
        "async": true,
        "success": function (resultData) {
            if (!resultData["isSuccess"]) {
                layer.alert(resultData["resultMessage"], {
                    icon: 6
                });
                return false;
            }
            layer.closeAll();
            layer.msg('更新成功……', {
                time: 500,
            });
            $("#queryBuilderForm").submit();
        },

    };

    universalAjax(ajaxParamter);
}
