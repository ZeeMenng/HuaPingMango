var num = GetUrlParam().num;//获取Url带过来的参数判断是鲜果还是加工品
$('#data-tab li').eq(num).addClass('active').siblings().removeClass('active');
//获取最高价格和最低价格
var maxPrice = $('input[name="maxPrice"]');
var minPrice = $('input[name="minPrice"]');
//初始化参数
var params = {
    typeCode: num || '', //1鲜果 2加工品
    isRecommend: "1", //是否推荐 0 是  1否
    maxPrice:"", //最高价
    minPrice: "", //最低价
    priceSort:true//默认价格从低到高
};
//点击搜索按钮查询价格区间数据
$('.search-button').on('click', function () {
    params.maxPrice = maxPrice.val();
    params.minPrice = minPrice.val();
    getMangoMake(curPage, params);
})
//产品种类切换
$('#data-tab').on('click','li',function(){
    var index = $(this).index() == 0 ? '' : $(this).index();
    params.typeCode = index;
    $(this).addClass('active').siblings().removeClass('active');
    getMangoMake(curPage, params);
})
//是否推荐
$(".t-groom").on('click',function () {
    if($(this).hasClass('active')){
        $(this).removeClass('active');
        params.isRecommend=1;
    }else{
        $(this).addClass('active');
        params.isRecommend=0;
    }
    //console.log(params)
    getMangoMake(curPage, params);
})
//按价格高低排序
$(".price").on('click','span',function () {
    $(this).addClass('active').siblings().removeClass('active');
    if($(this).hasClass("low")){
        params.priceSort = true;
    }else{
        params.priceSort = false;
    }
    getMangoMake(curPage, params);
})

// 分页
var pageSize = 8,
    curPage = 0;

function page(totles, curPage, pageSize) {
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
    getMangoMake(page_index + 1,params);
}

//芒果微商城列表数据
function getMangoMake(currentPage,params) {
    //console.log(params)
    var currentPage = currentPage ? currentPage : 1;
    var submitData = {
        "entityRelated": {
            "typeCode": params.typeCode || '', //1鲜果 2加工品
            "isRecommend": params.isRecommend, //是否推荐 0 是  1否
            "maxPrice": params.maxPrice, //最高价
            "minPrice": params.minPrice //最低价
        },
        "orderList": [{
            "columnName": "perPriceUnit",
            "isASC": params.priceSort
        }],
        "page": {
            "pageIndex": currentPage,
            "pageSize": pageSize
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piProductRecommend/getMangoMallList", submitDataString, rendomMangoMake)
}
getMangoMake(curPage, params);

function rendomMangoMake(val) {
    console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'MangoMake',
            idScript: 'MangoMakeSrc',
            data: {
                "MangoMakeData": val.data
            }
        };
        initTemplate(params);
        page(val.totalCount, curPage, pageSize)
    } else {
        $("#MangoMake").html("<div class='noData'>暂无查询结果</div>")
    }

}















