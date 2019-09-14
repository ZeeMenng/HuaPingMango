var laydate;

var regionId, startTime, dimension, endTime, areaType = 1,unitName='',titText="中国芒果进出口金额";

//  图表tab初始化
var tab = new Tab('importsType', function (params) {
    // console.log($(params).data('index'))
    var index = $(params).data('index');
    areaType = index;
    produceNum(regionId, startTime, endTime, priceUnit, dimension, queryType, index,unitName,titText);
});

//  日期初始化
layui.use('laydate', function () {
    laydate = layui.laydate;
    laydate.render({
        elem: '#priceDate',
        type: 'year',
        range: '~',
        format: 'yyyy',
        done: function (value, date, endDate) {
            // console.log(value); //得到日期生成的值，如：2017-08-18
            var valArr = value.split('~')
            startTime = valArr[0];
            endTime = valArr[1];
            produceNum(regionId, valArr[0], valArr[1], priceUnit, dimension, queryType, areaType,unitName,titText);
        }
    });
});

// 贸易数据请求接口
function produceNum(regionId, startTime, endTime, priceUnit, dimension, queryType, areaType,unitName,titText) {
    console.log(queryType)
    var params = {
        "entityRelated": {
            "regionId": regionId,
            "breed": "0",
            "dimension": dimension,
            "startTime": startTime,
            "endTime": endTime,
            "priceUnit": priceUnit,
            "queryType": queryType,
            "relationId": "04"
        },
        "orderList": [
            { "columnName": "", "isASC": true }
        ],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    }
    ajaxGet('mango/extend/swagger/da/daImportExport/getImportExportDataAndProportion', params, produceNumCallBack);

    function produceNumCallBack(res) {
         console.log(res)

        var params = {
            el: '#priceNum',
            titText: titText ? titText : '中国芒果种植面积',
            legendData:['进口金额','同比增长率'],
            unitName:'单位:'+unitName,
            xAxisData: [],
            seriesData: [
                [],
                []
            ]
        }
        if (res.isSuccess) {
            var data = res.data;
            //console.log(areaType , queryType)
            if (areaType == 1){
                params.xAxisData = data.importData.times;
                
                if(queryType=='volume'){
                        params.legendData = ['进口金额','同比增长率'];
                        params.seriesData = [
                        data.importData.volumeData,
                        data.importData.volumeProportion
                        //data.importData.amountData,
                        //data.importData.amountProportion
                    ];
                }else{
                    params.legendData = ['进口量','同比增长率'];
                        params.seriesData = [
                        //data.importData.volumeData,
                        //data.importData.volumeProportion
                        data.importData.amountData,
                        data.importData.amountProportion
                    ];
                }
                
            }else{
                params.xAxisData = data.exportData.times;
                
                if(queryType=='volume'){
                    params.legendData = ['出口金额','同比增长率'];
                        params.seriesData = [
                        data.exportData.volumeData,
                        data.exportData.volumeProportion
                        //data.exportData.amountData,
                        //data.exportData.amountProportion
                    ];
                }else{
                    params.legendData = ['出口量','同比增长率'];
                        params.seriesData = [
                        //data.exportData.volumeData,
                        //data.exportData.volumeProportion
                        data.exportData.amountData,
                        data.exportData.amountProportion
                    ];
                }
                
            }
        }
        resourceBarFn(params);
    }
}
//  初始化请求左侧菜单
initPage()
function initPage() {
    var params = {
        "entityRelated": {
            "menuRelationId": "04"
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
     console.log(res)
    if (res.isSuccess) {
        //  初始化左侧树菜单
        initTree(res.data);

        regionId = res.data[1].regionId;
        unitName = res.data[1].priceUnitText;
        dimension = res.data[1].dimension;
        priceUnit = res.data[1].priceUnit;
        queryType = res.data[1].queryType;
        //  生产数据
        produceNum(regionId, startTime, endTime, priceUnit, dimension, queryType, areaType,unitName,titText);
    }
}
/**
 * 树结构开始
 * @param el @type {String}     DOM元素
 * @param data @type {Object}   Json数据
 */

function initTree(data) {
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
                var myAttr = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0];
                if (myAttr.level !== 0) {
                    // console.log(myAttr)
                    unitName = myAttr.priceUnitText;
                    regionId = myAttr.regionId;
                    queryType = myAttr.queryType;
                    priceUnit = myAttr.priceUnit;
                    dimension = myAttr.dimension;
                    titText = treeNode.name;
                    produceNum(myAttr.regionId, startTime, endTime, myAttr.priceUnit, myAttr.dimension, myAttr.queryType, areaType,myAttr.priceUnitText,titText)
                }
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandNode(treeNode);
                // console.log(event, treeId, treeNode, clickFlag)
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