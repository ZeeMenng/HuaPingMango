//切换效果
var myIndex =0;
var dataer;
var regionCode = '';
var tabType ='';
// 当前选择的tab
var tabs=document.getElementById("tab").getElementsByTagName("li");
var divs=document.getElementById("BaiduMap").getElementsByTagName("div");
$('.company-list').hide();
for(var i=0;i<tabs.length;i++){
    tabs[i].onclick=function(){change(this);}
}

function change(obj){
    for(var i=0;i<tabs.length;i++){
        if(tabs[i]==obj){
            tabs[i].className="active";
            myIndex = i;
            tabType = i;
            initMapres(dataer);
            $('.company-list').hide();
            if(tabType == '2' || tabType == '3' ){
                //initCompany(curPage,regionCode,tabType)
            }
        }else{
            tabs[i].className="";
        }
    }
}

//百度地图模拟数据

var data_info;
var point1 = "/img/point1.png";

// 获取数据
(function  mapMarketinit(){
    var params = {
        submitData:{}
    }
    ajaxGet('mango/extend/swagger/da/daEnterpriseDistribution/getListByJsonData', params, initresponFn);
})();
function initresponFn(res){
    //console.log(res)
    if (res.isSuccess) {
        dataer = res;
        initMapres(res);
    }
}
// 初始化地图,设置中心点坐标和地图级别
var markers = [];
var map = null;
var markerClusterer = null;

function initMapres(res){
    map = new BMap.Map("BaiduMap"); // 创建Map实例
    map.centerAndZoom(new BMap.Point(116.420941, 39.911393), 5);
    map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
    map.setCurrentCity("华坪县");
    markers = [];
    switch(myIndex)
    {
        case 0:
            data_info = res.data.mainProductionArea
            break;
        case 1:
            data_info = res.data.wholesaleMarket
            break;
        case 2:
            data_info = res.data.onlineRetailers;
            break;
        default:
            data_info = res.data.enterpriseDistribution
    }
    for(var i=0;i<data_info.length;i++){
        var myIcon = new BMap.Icon(
            //point1,
            new BMap.Size(22, 34),
            {
                imageSize: new BMap.Size(22, 34),imageOffset: new BMap.Size(0, 0)
            }
        );
        var marker = new BMap.Marker(new BMap.Point(data_info[i].longitude, data_info[i].latitude), {
            icon: new BMap.Symbol(BMap_Symbol_SHAPE_POINT, {
                scale: 1, //图标缩放大小
                fillColor: "#6eb10c", //填充颜色
                fillOpacity: 0.8 //填充透明度
            })
        });
        marker.customData = { myProperty: data_info[i].regionCode };
        markers.push(marker);
        switch(myIndex)
        {
            case 0:
                var sContent =
                    "<h4 style='margin:0 0 5px 0;padding:0.2em 0;text-align: center;'>"+data_info[i].regionText+"</h4>" +
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>"+data_info[i].content+"</p>";
                break;
            case 1:
                var sContent =
                    "<h4 style='margin:0 0 5px 0;padding:0.2em 0;text-align: center;'>"+data_info[i].name+"</h4>" +
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 占地面积："+data_info[i].coverArea +"</p>"+
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 地址："+data_info[i].Address+"</p>"+
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 经营商户数："+data_info[i].merchantsNum+"</p>"+
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 摊位数："+data_info[i].stallsNum+"</p>"+
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 日交易量："+data_info[i].daysVolume+"</p>"+
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 联系方式："+data_info[i].tel+"</p>"+
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 年交易额："+data_info[i].yearsAmount+"</p>";
                break;
            case 2:
                var sContent =
                    "<p style='margin:0;line-height:55px;font-size:13px;text-indent:2em'>"+data_info[i].regionText+':'+data_info[i].entNumber
                    + "家</p>";
                break;
            default:
                var sContent =
                    "<p style='margin:0;line-height:55px;font-size:13px;text-indent:2em'>"+data_info[i].regionText+':'+data_info[i].entNumber
                    + "家</p>";
        }
        addClickHandler(sContent,marker);
        map.addOverlay(marker);
    }
    /* markerClusterer = new BMapLib.MarkerClusterer(map, {
        markers: markers
    }); */

}
// 百度地图API功能
function addClickHandler(sContent,marker){
    marker.addEventListener("mouseover",function(e){
        openInfo(sContent,e)}
    );
    marker.addEventListener("click",function(e){
        if(tabType == 2 || tabType == 3){
            $('.company-list').show();
        }
        regionCode = e.target.customData.myProperty;
        initCompany(curPage,regionCode,tabType);
    });
}
function openInfo(sContent,e){
    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,point); //开启信息窗口
}
//清除覆盖物
function remove_overlay(){
    map.clearOverlays();
}

//var styleJson = [{"label":"水域","featureType":"water","elementType":"all","stylers":{"color":"#397ed8"}},{"label":"公路填充","featureType":"highway","elementType":"geometry.fill","stylers":{"color":"#157da4"}},{"label":"公路线条","featureType":"highway","elementType":"geometry.stroke","stylers":{"color":"#254cb4"}},{"label":"主干道填充","featureType":"arterial","elementType":"geometry.fill","stylers":{"color":"#254cb4"}},{"label":"主干道线条","featureType":"arterial","elementType":"geometry.stroke","stylers":{"color":"#157da4"}},{"label":"局部?","featureType":"local","elementType":"geometry","stylers":{"color":"#2672f3"}},{"label":"陆地","featureType":"land","elementType":"all","stylers":{"color":"#2f53bb"}},{"label":"铁路填充","featureType":"railway","elementType":"geometry.fill","stylers":{"color":"#08304b"}},{"label":"铁路线条","featureType":"railway","elementType":"geometry.stroke","stylers":{"color":"#000000"}},{"label":"建筑填充","featureType":"building","elementType":"geometry.fill","stylers":{"color":"#021736"}},{"label":"建筑默认","featureType":"building","elementType":"geometry","stylers":{"color":"#021736"}},{"label":"标签填充","featureType":"all","elementType":"labels.text.fill","stylers":{"color":"#66ccff","font-size":"38px"}},{"label":"标签线条","featureType":"all","elementType":"labels.text.stroke","stylers":{"weight":"normal","color":"#1f41a0"}},{"label":"绿化","featureType":"green","elementType":"geometry","stylers":{"color":"#2b8284"}},{"label":"边界","featureType":"boundary","elementType":"all","stylers":{"color":"#05365a"}},{"label":"人造物","featureType":"manmade","elementType":"all","stylers":{"color":"#05365a"}}]
//map.setMapStyle({ styleJson: styleJson });
// map.setCurrentCity("华坪县");          // 设置地图显示的城市 此项是必须设置的
//  企业弹窗
var totles, curPage = 0, pageSize = 3;
function initCompany(curPage,regionCode,tabType) {
    if(tabType == 2){
        pageSize =6
    }else{
        pageSize=3;
    }
    var params = {
        entityRelated:{
            regionCode:regionCode,
            tabType:tabType
        },
        orderList:[
            {
                columnName: "registered_capital",
                isASC:false
            }
        ],
        page:{
            "pageIndex": curPage + 1,
            "pageSize": pageSize
        }
    }
        ajaxGet('mango/extend/swagger/da/daEnterpriseInfo/getTabByJsonData', params, initCompanyFn);
}
//  企业列表回调(相关企业分布)
function initCompanyFn(res) {
     var totles = res.totalCount;
     //console.log(res)
    if (res.isSuccess){
        var html = '';
        if(tabType == 2){
            /* initTemplate({
                data:{
                    "data":res
                },
                idScript: 'CompanySrc',
                idHtml: 'CompanyList',
            }); */
            for (var i = 0; i < res.data.length; i++) {
                html += '<li><h3>'+ res.data[i].onlineName
                + '</h3><p><span>地址：</span><span>' + res.data[i].address
                + '</span></p><p><span>电话：</span><span>' + res.data[i].tel
                + '</span></p></li>'
            }
        }else{
            /* initTemplate({
                data:{
                    "data":res
                },
                idScript: 'CompanySrc',
                idHtml: 'CompanyList',
            }); */
            for (var i = 0; i < res.data.length; i++) {
                html += '<li><h3>'+ res.data[i].enterpriseName
                + '</h3><p><span>统一社会信用代码：</span><span>' + res.data[i].societyId
                + '</span></p><p><span>注册资金：</span><span>' + res.data[i].registerCapital
                + '</span></p><p><span>经营范围：</span><span>' + res.data[i].businessScope
                + '</span></p><p><span>地址：</span><span>' + res.data[i].address
                + '</span></p><p><span>电话：</span><span>' + res.data[i].tel
                + '</span></p></li>'
            }
        }
        $('#CompanyList').html(html);
        page(totles, curPage)
    }
}

/**
 * 分页调用
 * @param totles @type { Number }     总条数
 * @param curPage @type { Number }   当前页
 */
function page(totles, curPage) {
    $("#page").pagination(totles, {
        current_page: curPage,          //  当前选中的页面	可选参数，默认是0，表示第1页
        link_to: 'javascript:;',        //  分页的链接 字符串，可选参数，默认是"#"
        num_edge_entries: 0,            //  边缘页数
        num_display_entries: 2,         //  主体页数
        ellipse_text: '...',          //  省略的页数用什么文字表示 可选字符串参数，默认是"…"
        first_text: '首页',
        last_text: '尾页',
        callback: pageselectCallback,   //  回调函数	默认无执行效果
        totleNum: totles,
        items_per_page: pageSize        //  每页显示多少项
    });
    //  隐藏共多少页
    /* $("#page b").eq(0).hide();
    $("#page b").eq(1).hide();
    $("#page b").eq(2).hide(); */
    $("#search").on('click',function () {
        return false;
    })
}
//  分页回调
function pageselectCallback(page_index, jq) {
    // console.log(page_index)  //  所点击索引，0为第1页
    // console.log(jq)
    curPage = page_index;
    //  重新请求列表
    initCompany(curPage,regionCode,tabType);
}
//分页调用
page(totles,1);
//关闭按钮
 function clickBtn(){
    $('.company-list').hide();
}

clickBtn()