

var regionId, startTime, endTime, priceType, dimension, priceUnit,unitName,titText='华坪县芒果价格数据(年度)';

//  日期初始化

function initTime(dimension) {
    var timeType;
    switch (dimension) {
        case 1:
            timeType = 'year';
            break;

        case 2:
            timeType = 'month';
            break;

        case 3:
            timeType = 'month';
            break;

        case 4:
            timeType = 'datetime';
            break;

        case 5:
            timeType = 'time';
            break;

        default:
            break;
    }
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#priceDate',
            type: timeType,
            // format: 'yyyy',
            range: '~',
            done: function (value, date, endDate) {
                // console.log(timeType)
                // console.log(value, date, endDate); //得到日期生成的值，如：2017-08-18
                var valArr = value.split('~')
                startTime = valArr[0];
                endTime = valArr[1];
                initPrice(regionId, valArr[0], valArr[1], priceType, dimension, priceUnit,unitName,titText)
            }
        });
    });
}

//  请求左侧菜单初始化页面
initPage()
function initPage() {
    var params = {
        "entityRelated": {
            "menuRelationId": "03"
        },
        "orderList": [
            { "columnName": "", "isASC": true }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    }
    ajaxGet('mango/extend/swagger/pe/peQueryMenu/getQueryMenuList', params, initPageFn);
}
function initPageFn(res) {
    // console.log(res)
    if (res.isSuccess) {
        regionId = res.data[1].regionId
        unitName = res.data[1].priceUnitText
        priceType = res.data[1].priceType
        dimension = res.data[1].dimension
        priceUnit = res.data[1].priceUnit
        initTime(dimension)
        initTree(res.data)
        initPrice(regionId, startTime, endTime, priceType, dimension, priceUnit,unitName,titText)
    }
}

/**
 * 价格数据图表数据请求
 * @param regionId @type {String}     左侧菜单获取ID
 * @param startTime @type {String}    开始时间
 * @param endTime @type {String}      结束时间
 * @param priceType @type {String&Number}    价格类型  1，田头价 2，批发价 3，零售价 4，电商价 5，进口价 6，出口价
 * @param dimension @type {String&Number}    时间维度   1 年 2季度 3月 4 日 5实时
 * @param priceUnit @type {String&Number}    价格单位 1元 2万元 3亿元 4美元
 */

// initPrice()
function initPrice(regionId, startTime, endTime, priceType, dimension, priceUnit,unitName,titText) {
    var params = {
        "entityRelated": {
            "regionId": regionId,
            "startTime": startTime,
            "endTime": endTime,
            "breed": "0",
            "priceType": priceType,
            "priceUnit": priceUnit,
            "dimension": dimension,
            "relationId": "03"
        },
        "orderList": [
            { "columnName": "", "isASC": true }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    }
    // console.log(JSON.stringify(params))
    ajaxGet('mango/extend/swagger/da/daMarketPrice/getTimesPriceData', params, initPriceCallBack);

    function initPriceCallBack(res) {
        // console.log(res)
        var params = {
            el: '#produceLine',
            unitName:unitName+'/公斤',
            titText:titText,
            xAxisData: [],
            seriesData: []
        }
        if(res.isSuccess){
            params.xAxisData = res.data.times
            params.seriesData = res.data.priceDatas
        }
        dataLine(params)
    }

}



/**
 * 树结构开始
 * @param el @type {String}     DOM元素
 * @param data @type {Object}   Json数据
 */

function initTree(data) {
    console.log(data)
    var curId = data[1].regionId;
    data[0].open = true;
    var setting = {
        view: {
            showIcon: false,
            dblClickExpand: false,
            showLine: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: function (treeId, treeNode, clickFlag) {
                // console.log(treeId, treeNode, clickFlag)
            },
            onClick: function (event, treeId, treeNode, clickFlag) {
                titText = treeNode.name;
                console.log(titText)
                var myAttr = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0];
                if (myAttr.level!==0){
                    // console.log(myAttr)
                    unitName = myAttr.priceUnitText
                    regionId = myAttr.regionId;
                    priceType = myAttr.priceType;
                    dimension = myAttr.dimension;
                    priceUnit = myAttr.priceUnit;
                    $('.date-select').html('<p>时间：</p><input type="text" class="layui-input" id="priceDate">');
                    initPrice(regionId, startTime, endTime, priceType, dimension, priceUnit,unitName,titText);
                    initTime(dimension);
                }
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandNode(treeNode);
            }
        }
    };
    $.fn.zTree.init($("#treeDemo"), setting, data || []);

    // 默认选中第一个
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo")
    var node = treeObj.getNodeByParam("regionId", curId);//根据ID找到该节点
    treeObj.selectNode(node);//根据该节点选中
}

/**
 * 树结构结束
 */