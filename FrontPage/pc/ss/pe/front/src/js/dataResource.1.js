var laydate;
var regionId, startTime, endTime,titText;

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
            produceNum(regionId, valArr[0], valArr[1])
        }
    });
});

//  请求左侧菜单初始化页面
initPage()
function initPage() {
    var params = {
        "entityRelated": {
            "menuRelationId": "02"
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
        regionId = res.data[0].regionId
        initTree(res.data)
        produceNum(res.data[0].regionId, startTime, endTime)
    }
}

/**
 * 生产数据图表数据请求
 * @param regionId @type {String}     左侧菜单获取ID
 * @param startTime @type {String}    开始时间
 * @param endTime @type {String}      结束时间
 * @param areaType @type {String&Number}     图表类型  种植面积为1，产量为2
 */

function produceNum(regionId, startTime, endTime) {
    var params = {
        "entityRelated": {
            "regionId": regionId,
            "breed": "0",
            "startTime": startTime,
            "endTime": endTime,
            "dimension": "1",
            "relationId": "02"
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
}
function initProduceNum(res) {
    // console.log(res)
    var data = res.data;
    var params = {
        el: '#priceNum',
        titText: titText,
        legendData:['产值','同比增长率'],
        unitName:'单位:万元',
        xAxisData: [],
        seriesData: [
            [],
            []
        ]
    }
    if (res.isSuccess) {
        var data = res.data.output;
        params.xAxisData = data.times
        params.seriesData = [
            unitChange(data.data,0.0001),
            data.proportion
        ]
    }
    resourceBarFn(params);
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
    titText = data[0].name;
    var curId = data[0].regionId;
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
            beforeClick: function (treeId, treeNode, clickFlag){
                // console.log(treeId, treeNode, clickFlag)
            },
            onClick: function (event, treeId, treeNode, clickFlag){
                var myAttr = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0]
                regionId = myAttr.regionId;
                titText = treeNode.name;
                produceNum(myAttr.regionId, startTime, endTime,titText)
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandNode(treeNode);
            }
        }
    };
    $.fn.zTree.init($("#treeDemo"), setting, data);

    // 默认选中第一个
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo")
    var node = treeObj.getNodeByParam("regionId", curId);//根据ID找到该节点
    treeObj.selectNode(node);//根据该节点选中
}

/**
 * 树结构结束
 */