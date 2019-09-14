var Id = GetUrlParam().id
// 分页
var pageSize =  6,curPage = 0;
function page(totles, curPage, pageSize) {
    if (totles < 6) return;
    $("#page").pagination(totles, {
        current_page: curPage,
        num_edge_entries: 0, //边缘页数
        num_display_entries: 8, //主体页数
        first_text: '首页',
        link_to:'javascript:;',
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
   getEnterpriseList(page_index + 1);
}

//企业微网站列表
function getEnterpriseList(currentPage) {
    var currentPage = currentPage ? currentPage : 1;
    var submitData = {
        "entityRelated": {
            "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
        },
        "page": {
            "pageIndex": currentPage,
            "pageSize": 6
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/da/daEnterpriseInfo/getEnterpriseList", submitDataString, rendomEnterpriseList)
}
getEnterpriseList()

function rendomEnterpriseList(val) {
    console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'EnterpriseList',
            idScript: 'EnterpriseListSrc',
            data: {
                "EnterpriseListData": val.data
            }
        };
        initTemplate(params);
        page(val.totalCount, curPage, pageSize)
    } else {
        $("#EnterpriseList").html("<div class='noData'>暂无查询结果</div>")
    }

}

//首页banner
    getBanner();

//首页banner
function getBanner() {
    var submitData = {
        "entityRelated": {
            "enterpriseId": Id
        }, "page": {
            "pageIndex": 1,
            "pageSize": 6
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/da/daEnterpriseInfo/getEnterpriseRecommendPicList", submitDataString, rendomBanner)
}

function rendomBanner(val) {
    //console.log(val)
    if(val.isSuccess){
        var html = '';
            var html1 = '';
            for (var i = 0; i < val.data.paths.length; i++) {
                if (val.data.paths[i]) {
        //banner轮播部分
                    html += '<li data-url="' + i + '">'
                        +'<img src="' + val.data.paths[i] + '" alt="">'
                        + '</li>';
                } else {
                    html += '<li data-url="' + i + '">'
                        + '<img src="../../img/zwtp.gif" alt="">'
                        + '</li>';
                }
                if (i == 0) {
                    html1 += '<li class="iconfont icon-dian-copy-copy active"></li>';
                } else {
                    html1 += '<li class="iconfont icon-dian-copy-copy"></li>';
                }
            }
            $(".swiper-banner").html(html);
            $("#controller-banner").html(html1);
            if (val.data.paths.length > 1) {
                autoPlay("swiper-banner", "controller-banner", true);
            }
    }else{
        $(".swiper-banner").html("<div class='noData'>暂无查询结果</div>");
    }

}

//企业简介
function getEnterprise() {
    var Id = GetUrlParam().id;
    var submitData = {
        "entityRelated": {
            "enterpriseId": Id
        }, "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/da/daEnterpriseInfo/getEnterpriseSummary", submitDataString, rendomEnterprise)
}
getEnterprise()
function rendomEnterprise(val) {
    //console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'Enterprise',
            idScript: 'EnterpriseSrc',
            data: {
                "EnterpriseData": val.data
            }
        };
        initTemplate(params);
    } else {
        $("#Enterprise").html("<li class='noData'>暂无查询结果</li>")
    }

}
//企业工商信息
function getCommerceInfo() {
    var submitData = {
        "entityRelated": {
            "enterpriseId": Id
        }, "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/da/daEnterpriseInfo/getEnterpriseIndustryAndCommerceInfo", submitDataString, rendomCommerceInfo)
}
getCommerceInfo()
function rendomCommerceInfo(val) {
    //console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'CommerceInfo',
            idScript: 'CommerceInfoSrc',
            data: {
                "CommerceInfoData": val.data[0]
            }
        };
        initTemplate(params);
    } else {
        $("#CommerceInfo").html("<li class='noData'>暂无查询结果</li>")
    }

}
//视频信息
function getVideoInfo() {
    var submitData = {
        "entityRelated": {
            "enterpriseId": Id
        }, "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/da/daEnterpriseInfo/getEnterpriseVideoInfo", submitDataString, rendomVideoInfo)
}
getVideoInfo()
function rendomVideoInfo(val) {
   // console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'VideoInfo',
            idScript: 'VideoInfoSrc',
            data: {
                "VideoInfoData": val.data[0]
            }
        };
        initTemplate(params);
    } else {
        $("#VideoInfo").html("<li class='noData'>暂无查询结果</li>")
    }

}
//产品推介列表
function getMangoMallList() {
    var submitData = {
        "entityRelated": {
            "enterpriseId": Id //企业id
        },
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piProductRecommend/getMangoMallListByEnterpriseId", submitDataString, rendomMangoMallList)
}
getMangoMallList()
function rendomMangoMallList(val) {
    console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'MangoMallLists',
            idScript: 'MangoMallListsSrc',
            data: {
                "MangoMallListsData": val.data
            }
        };
        initTemplate(params);
    } else {
        $("#MangoMallList").html("<li class='noData'>暂无查询结果</li>")
    }

}

