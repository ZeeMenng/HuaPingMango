
var laydate;
var regionId, startTime, endTime,areaType = 1,titText='华坪县芒果生产数据';
//  图表tab初始化
var tab = new Tab('importsType',function (params) {
    // console.log($(params).data('index'));
    var index = $(params).data('index');
    areaType = index;
    produceNum(regionId, startTime, endTime, index,titText)
});

//  日期初始化
layui.use('laydate', function () {
    laydate = layui.laydate;
    laydate.render({
        elem: '#priceDate',
        type: 'year',
        range: true,
        format: 'yyyy',
        done: function (value, date, endDate) {
            // console.log(value); //得到日期生成的值，如：2017-08-18
            var valArr = value.split('-')
            startTime = valArr[0];
            endTime = valArr[1];
            produceNum(regionId, valArr[0], valArr[1], areaType,titText)
        }
    });
});
//  请求左侧菜单初始化页面
initPage()
function initPage() {
    var params = {
        "entityRelated": {
            "menuRelationId": "01"
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
    if (res.isSuccess) {
        regionId = res.data[0].regionId
        initTree(res.data)
        produceNum(res.data[0].regionId, startTime, endTime, areaType,titText)
    }
}

/**
 * 生产数据图表数据请求
 * @param regionId @type {String}     左侧菜单获取ID
 * @param startTime @type {String}    开始时间
 * @param endTime @type {String}      结束时间
 * @param areaType @type {String&Number}     图表类型  种植面积为1，产量为2
 */

function produceNum(regionId, startTime, endTime, areaType,titText) {
    var params = {
        "entityRelated": {
            "regionId": regionId,
            "breed": "0",
            "startTime": startTime,
            "endTime": endTime,
            "dimension": "1",
            "relationId": "01"
        },
        "orderList": [
            { "columnName": "", "isASC": true }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    ajaxGet('mango/extend/swagger/da/daGrowYield/getGrowDataAndProportion', params, initProduceNum);

    function initProduceNum(res) {
        // console.log(res)
        var data = res.data;
        var params = {
            el: '#priceNum',
            titText: titText,
            legendData:['种植面积','同比增长率'],
            unitName:'单位:亩',
            xAxisData: [],
            seriesData: [
                [],
                []
            ]
        }
        if(res.isSuccess){
            if (areaType == 1) {
                params.xAxisData = data.growArea.times;
                params.seriesData = [
                    unitChange(data.growArea.data,0.0015),
                    data.growArea.proportion
                ];
            } else {
                params.xAxisData = data.product.times;
                params.unitName = '单位：吨';
                params.legendData = ['产量','同比增长率'];
                params.seriesData = [
                    unitChange(data.product.data,0.001),
                    data.product.proportion
                ];
            }
        }
        resourceBarFn(params);
    }
}
/**
 * 生产数据图表结束
 */


/**
 * 树结构开始
 * @param el @type {String}     DOM元素
 * @param data @type {Object}   Json数据
 */

function initTree(data) {
    var curId = data[0].regionId;
    produceNum(regionId, startTime, endTime, areaType,data[0].name)
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
                //console.log(treeId, treeNode, clickFlag)
            },
            onClick: function (event, treeId, treeNode, clickFlag) {
                var myAttr = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0]
                regionId = myAttr.regionId;
                titText = treeNode.name;
                produceNum(regionId, startTime, endTime, areaType,titText)
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandNode(treeNode);
            }
        }
    };

    $.fn.zTree.init($("#treeDemo"), setting, data);

    // 默认选中第一个
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var node = treeObj.getNodeByParam("regionId", curId);//根据ID找到该节点
    treeObj.selectNode(node);//根据该节点选中
}

/**
 * 树结构结束
 */