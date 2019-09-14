// 分页
var pageSize = 6,
    curPage = 0;

function page(totles, curPage, pageSize) {
    if (totles < 8) return;
    $("#page").pagination(totles, {
        current_page: curPage,
        num_edge_entries: 0, //边缘页数
        num_display_entries: 8, //主体页数
        first_text: '首页',
        link_to: 'javascript:;',
        last_text: '尾页',
        callback: pageselectCallback,
        totleNum: totles,
        items_per_page: pageSize //每页显示1项
    });
    $('#search').on('click', function () {
        return false;
    })
}
// 分页回调函数
function pageselectCallback(page_index, jq) {
    curPage = page_index;
    getSpecialists(page_index + 1);
}

//  已互动次数
function getNum(){
    var submitData = {
                "entityRelated":{}
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piInteraction/getInteractionNum",submitDataString,rendomNum)
}
  getNum();
//互动主页互动次数
function rendomNum(val) {
    //console.log(val)
    if(val.isSuccess){
    var num= val.data[0].num || 0;
        numSplit(num);
    }else{
        numSplit(0);
    }
}

function numSplit(num) {
    var arr = num.toString().split('')
    arr.forEach(function(v,i){
        $('.num-span').append('<span>'+v+'</span>')
    })
}

//互动主页面回应栏数据
function getInterList(){
    var submitData = {
                "entityRelated":{
                    "interactionType":"1"
                },
                "orderList":[{
                    "columnName":"update_time",
                    "isASC":false
                }],
                "page":{
                    "pageIndex":1,
                    "pageSize":2
                }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piInteraction/getInteractionList",submitDataString,rendomInterList)
}
   getInterList();
//互动主页面回应栏
function rendomInterList(val) {
    //console.log(val)
    var params = {
        idHtml: 'interList',
        idScript: 'interListSrc',
        data:{
            "interListData":val.data
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#listPage li').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}

//互动主页面留言数据
function getMsgList(){
    var submitData = {
                "entityRelated":{
                    "interactionType":"2"
                },
                "orderList":[{
                    "columnName":"update_time",
                    "isASC":false
                }],
                "page":{
                    "pageIndex":1,
                    "pageSize":2
                }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piInteraction/getInteractionList",submitDataString,rendomMsgList)
}
   getMsgList();
//互动主页面留言
function rendomMsgList(val) {
    //console.log(val)
    var params = {
        idHtml: 'msgList',
        idScript: 'msgListSrc',
        data:{
            "msgListData":val.data
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#listPage li').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}
//互动主页面2个专家列表接口
function getSpecialist() {
    var submitData = {
        "entityRelated": {
            "userName": "",
            "introduction": ""
        },
        "orderList": [{
            "columnName": "isRecommend",
            "isRecommend":false,
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 2
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piExpert/getListByJsonData", submitDataString, rendomSpecialist)
}
   getSpecialist();
//互动主页面2个专家列表数据
function rendomSpecialist(val) {
    //console.log(val)
    var params = {
        idHtml: 'speciaList',
        idScript: 'speciaListSrc',
        data:{
            "speciaListData": val.data
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#Specialist li').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}
//专家列表页面specialist.html
function getSpecialists(currentPage) {
     var currentPage = currentPage ? currentPage : 1;
    var submitData = {
        "entityRelated": {
            "userName": "",
            "introduction": ""
        },
        "orderList": [{
            "columnName": "isRecommend",
            "isASC": false
        }],
        "page": {
            "pageIndex": currentPage,
            "pageSize": 6
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piExpert/getListByJsonData", submitDataString, rendomSpecialists)
}
   getSpecialists(curPage);
//专家列表页面
function rendomSpecialists(val) {
   // console.log(val)
    var params = {
        idHtml: 'speciaLists',
        idScript: 'speciaListsSrc',
        data:{
            "speciaListsData": val.data
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#Specialists li').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}


//专家详情页面
function getSpeciaDetile() {
    var speciaId = GetUrlParam().id;
    //console.log(speciaId)
    ajaxGet("/extend/swagger/pi/piExpert/getModel/" + speciaId, '', rendomSpeciaDetile)
}
    getSpeciaDetile();
//专家详情页面
function rendomSpeciaDetile(val) {
    //console.log(val)
    var params = {
        idHtml: 'SpeciaDetile',
        idScript: 'SpeciaDetileSrc',
        data:{
            "SpeciaDetileData": val.data
        }
    };
    if(val.data!= ''){
        initTemplate(params);
    }else{
        $('#SpeciaDetile li').html('暂无数据！')
        .css({'color':'#333','text-align':'center'})
    }
}