/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description token信息。 相关页面的js方法。
 */

function initTokenListPage() {

    // 初始化列表页主体部分，包括查询条件表单及数据表格等。
    var pageParam = {
        formId: "queryBuilderForm",
        tableId: "contentTable",
        editPage: {
            title: "批量修改表单",
            url: RP_GPTOKEN_EDIT
        },
        detailPage: {
            url: RP_GPTOKEN_DETAIL
        },
        addPage: {
            url: RP_GPTOKEN_ADD
        },
        deleteInterface: {
            url: RU_GPTOKEN_DELETE
        },
        deleteListInterface: {
            url: RU_GPTOKEN_DELETELIST
        }

    };
    var ajaxParam = {
        url: RU_GPTOKEN_GETLISTBYJSONDATA,
        type: "GET",
        submitData: {
            "entityRelated": {},
            "orderList": [{
                "columnName": "id",
                "isASC": true
            }],
            "page": {
                "pageIndex": DEFAULT_PAGE_INDEX,
                "pageSize": DEFAULT_PAGE_SIZE
            }
        },
        columnInfo: [

            {
                "columnName": "accessToken",
                "columnText": "access_token",
                "style": "text-align:left",
                "width": "100px",
                "linkFunction": function (event) {
                    var href = RP_GPTOKEN_DETAIL + "?" + RECORD_ID + "=" + event.id;
                    return href;
                }
            }, {
                "columnName": "userName",
                "columnText": "所属用户 ",
                "style": "text-align:left"
            }, {
                "columnName": "addTime",
                "columnText": "登录时间",
                "style": "text-align:left",
            }, {
                "columnName": "updateTime",
                "columnText": "修改时间",
                "style": "text-align:left",
            },

        ]
    };

    var operationParam = [{
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
                pageParam.deleteInterface.url = RU_GPTOKEN_DELETE;
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

}
