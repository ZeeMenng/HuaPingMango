var totalCount, curPage = 0, pageSize = 8;
var browser = navigator.appName
var b_version = navigator.appVersion
var version = b_version.split(";");
//console.log(version)
//解决轮播图火狐报错不显示问题
var ver = typeof(version[1]) == 'undefined' ? version[0] : version[1];
var trim_Version = ver.replace(/[ ]/g, ""); 

/**
 * 轮播图数据请求
 * @author  rcz
 */
getSwiperData();
function getSwiperData() {

    ajaxGet('mango/extend/swagger/pe/peAerialView/getBroadcastByJsonData', {"page":{"pageIndex":1,"pageSize":4}}, getDataCallBack);
   
}
// 轮播图数据请求回调
function getDataCallBack(res) {
    var dom = $('.swiper-wrapper');
    dom.html('');
    if (res.isSuccess){
        var imgArr = [];
        var txtArr = [];
        res.data.forEach(function(v,i){
            var swiperHtml = '<div class="swiper-slide">'
                            +'<span class="swiper-center-title"></span>'
                            +'<span class="swiper-center-text">'+v.title+'</span >'
                            +'<span class="swiper-subtext"><i>丽江好风光</i><i>华坪好芒果</i></span >'
                            +'<img width = "100%" height = "500" src = "'+v.path+'" alt = ""/>'
                            +'</div>'
            imgArr[i] = v.path;
            txtArr[i] = v.title;
            dom.append(swiperHtml);
        })
        var mySwiper = new Swiper('.swiper-container', {
            pagination: '.pagination',  //  绑定dom
            autoplay: 2000,             //  图片切换时间间隔
            speed: 1500,                //  播放速度
            paginationClickable: true,
            loop: true,                 //  循环
            onSwiperCreated: function (swiper) {
                // var imgArr = ['../../img/play-view-small_01.png', '../../img/play-view-small_02.png', '../../img/play-view-small_03.jpg', '../../img/play-view-small_04.png']
                // var txtArr = ['基地航拍1', '基地航拍2', '基地航拍3', '基地航拍4']
                imgArr.forEach(function (v, i) {
                    $('.pagination span').eq(i).css({
                        "background-image": "url(" + imgArr[i] + ")",
                        "text-overflow": "ellipsis",
                        "overflow": "hidden",
                        "white-space": "nowrap",
                        "text-shadow": "0 0 20px #59684e",
                        // "background-size": "170px 90px",
                        "background-size": "100% 100%"
                    }).text(txtArr[i])
                    $('.pagination span').eq(i).append('<div class="pic-cover" style="position:absolute;top:0;left:0;width:100%;height:100%;background:#333;opacity:.5;filter:Alpha(opacity=50);"></div>')
                    
                    if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE8.0") {
                        $('.pagination span').eq(i).append('<img class="pic-img" onclick="clickImg(this)" style="position:absolute;top:0;left:0;z-index="-100" width="170" height="90" src="' + imgArr[i] + '" />')
                        $('.pagination span').eq(i).append('<div class="pic-text" style="position:absolute;text-shadow: 0 0 20px #59684e;top:0;left:0;width:100%;height:100%;">' + txtArr[i] + '</div>')
                        $('.pic-cover').css('z-index','100')
                    } 
                })
            }
        })
    }
}
//  在ie8点击img让父级dom触发点击事件
function clickImg(e) {
    $(e).parent().trigger('click')
}
//  初始化列表
initList(curPage,pageSize);

/**
 * 列表数据请求
 * @author  rcz
 * @param curPage @type { Number }     当前页
 * @param pageSize @type { Number }   每页显示多少条
 */

function initList(curPage,pageSize) {
    var params = { 
        "page": { 
            "pageIndex": curPage+1, 
            "pageSize": pageSize
        } 
    }
    ajaxGet('mango/extend/swagger/pe/peAerialView/getAerialList', params, initBirdFn);
}

function initBirdFn(res) {
    console.log(res)
    if(res.isSuccess){
        totalCount = res.totalCount;
        page(totalCount, curPage);
        if (res.isSuccess) {
            var data = res.data;
            var html = '';
            var imgArr = [];
            data.forEach(function (v, i) {
                // console.log(v)
                imgArr[i] = v.path;
                if ((i + 1) % 4 == 0) {
                    html += '<div class="item item-no-right">'
                        + '<div class="panorama-box" ><div class="panorama"><img width="276" height="188" src="' + v.path+'"/></div>'
                        + '</div>'
                        + '<p>' + v.title + '</p>'
                        + '</div>'
                } else {
                    html += '<div class="item">'
                        + '<div class="panorama-box"><div class="panorama"><img width="276" height="188"  src="' + v.path +'"/></div>'
                        + '</div>'
                        + '<p>' + v.title + '</p>'
                        + '</div>'
                }
            })
            $('.view-list').html(html)

            // var imgArr = [
            //     "../../img/IMG_1032.PNG",
            //     "../../img/IMG_1032.PNG",
            //     "../../img/IMG_1032.PNG",
            //     "../../img/IMG_1032.PNG",
            //     "../../img/IMG_1032.PNG",
            //     "../../img/IMG_1032.PNG",
            //     "../../img/IMG_1032.PNG",
            //     "../../img/IMG_1032.PNG"
            // ]
            // $('#test').attr('src',imgArr[0])
            //  兼容低级ie
            if (!(navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion.split(";")[1].replace(/[ ]/g, "").replace("MSIE", "")) < 10)) {
                var div = $(".panorama");
                div.each(function (i, v) {
                    initPSV(v, imgArr[i]);
                })
            }else{
                alert("您的浏览器版本过低，请下载IE10以上版本");
            }
        }
    }
}

/**
 * 全景图初始化
 * @author  rcz
 * @param el @type {String}     DOM元素
 * @param img @type {String}   图片地址
 */

function initPSV(el, img){
    //console.log(img)
    var PSV = new PhotoSphereViewer({
        // Path to the panorama
        panorama: img,
        // autoload:false,
        // Container
        container: el,
        // 可选，默认值为2000，全景图在time_anim毫秒后会自动进行动画。（设置为false禁用它）
        time_anim: 100,
        //  可选，默认值为null，在加载时显示的图片的路径。
        // loading_img: "../../img/IMG_1032.PNG",
        // Display the navigation bar
        navbar: true,
        // Resize the panorama
        size: {
            width: '100%',
            height: '100%'
        },
        // onready: function () {
        //     // console.log(newEl.length)
        //     if (newEl.length != undefined) {
        //         //  递归
        //         console.log(imgArr)
        //         initPSV(newEl, imgArr)
        //     }
        // }
    });
}
/**
 * 分页调用
 * @author  rcz
 * @param totles @type { Number }     总条数
 * @param curPage @type { Number }   当前页
 */
function page(totles, curPage) {
    $("#page").pagination(totles, {
        current_page: curPage,          //  当前选中的页面	可选参数，默认是0，表示第1页
        link_to: 'javascript:;',        //  分页的链接 字符串，可选参数，默认是"#"
        num_edge_entries: 0,            //  边缘页数
        num_display_entries: 8,         //  主体页数
        ellipse_text  : '...',          //  省略的页数用什么文字表示 可选字符串参数，默认是"…"
        first_text: '首页',
        last_text: '尾页',
        callback: pageselectCallback,   //  回调函数	默认无执行效果
        totleNum: totles,
        items_per_page: pageSize        //  每页显示多少项
    });

    //  点击确定按钮是清楚a标签默认事件
    $('#search').on('click', function () {
        return false;
    });
}
//  分页回调
function pageselectCallback(page_index, jq) {
    console.log(page_index)  //  所点击索引，0为第1页
    // console.log(jq)
    curPage = page_index;

    //  重新请求列表
    initList(page_index, pageSize)
}