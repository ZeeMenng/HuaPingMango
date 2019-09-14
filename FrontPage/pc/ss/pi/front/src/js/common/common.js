//调用后台接口的ip
//var URL = 'http://192.168.200.193:60011/mango';
var URL = typeof(URLMG) != 'undefined' ? URLMG :'http://192.168.200.193:8081/';
//溯源的ip
var URLSYip = typeof(URLSY) != 'undefined' ? URLSY :'http://192.168.200.193:60080';



/***
 * 去空格
 * @returns {string}
 */
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '');
};
/**
 * 多出字符串长度用...代替
 * @param num 截取长度
 * @returns {string}
 */
String.prototype.strEllipsis = function (num) {
    if(this.length>num){
        return this.substring(0, num) + '...';
    }else{
        return this
    }

};
String.prototype.trimTag = function(str){
   var reg = /<\/?.+?\/?>/g;
    return str.replace(reg, '');
}
//让ie8 兼容 forEach
if (!Array.prototype.forEach) {
    Array.prototype.forEach = function forEach(callback, thisArg) {
        var T, k;
        if (this == null) {
            throw new TypeError("this is null or not defined");
        }
        var O = Object(this);
        var len = O.length >>> 0;
        if (typeof callback !== "function") {
            throw new TypeError(callback + " is not a function");
        }
        if (arguments.length > 1) {
            T = thisArg;
        }
        k = 0;
        while (k < len) {
            var kValue;
            if (k in O) {
                kValue = O[k];
                callback.call(T, kValue, k, O);
            }
            k++;
        }
    };
}
/**
 * 设置Cookie
 * @param key key键值名称
 * @param value key键所对应的值
 * @param seconds 超时时间，毫秒
 */
function setCookie(key, value, seconds) {
    seconds = seconds || 0;
    var expires = "";
    if (seconds != 0) {
        var date = new Date();
        date.setTime(date.getTime() + (seconds * 1000));
        expires = "; expires=" + date.toGMTString();
    }
    document.cookie = key + "=" + escape(value) + expires + "; path=/";
}

/**
 * 从cookie获取指定key的值
 * @param key 要获取cookie的指定key名称
 * @returns {string}
 */
function getCookie(key) {
    var nameEQ = key + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1, c.length);
        }
        if (c.indexOf(nameEQ) == 0) {
            return unescape(c.substring(nameEQ.length, c.length));
        }
    }
    return '';
}

/**
 * 清除指定key的Cookie值
 * @param key 指定key名称
 */
function clearCookie(key) {
    setCookie(key, "", -1);
}

var TimeoutCookie = 60 * 30;

/**
 * 存储指定的key和value
 * @param key 存储键
 * @param value 存储值
 */
function setKeyItem(key, value) {
    if (window.sessionStorage) {
        sessionStorage.setItem(key, value);
    } else {
        setCookie(key, value, TimeoutCookie)
    }
}

/**
 * 根据key键获取value
 * @param key 键
 */
function getKeyItem(key) {
    if (window.sessionStorage) {
        return sessionStorage.getItem(key);
    } else {
        return getCookie(key);
    }
}

/**
 * 清空存储的键值对
 */
function clearKeyItem() {
    if (window.sessionStorage) {
        sessionStorage.clear();
    } else {
        clearCookie("KEY_USER_INFO");
    }
}

/**
 * 存储用户登录信息
 * @param user 要存储的用户对象
 */
function saveUserInfo(user) {
    if (window.sessionStorage) {
        sessionStorage.setItem("KEY_USER_INFO", JSON.stringify(user));
    } else {
        setCookie("KEY_USER_INFO", JSON.stringify(user), TimeoutCookie);
    }
}

/**
 * 获取用户信息
 * @returns {Object} 返回用户对象
 */
function getUserInfo() {
    if (window.sessionStorage) {
        var userInfo = JSON.parse(sessionStorage.getItem("KEY_USER_INFO"));
        return userInfo;
    } else {
        return JSON.parse(getCookie("KEY_USER_INFO"));
    }
}

/**
 * 获取url中参数，并封装为对象
 * @returns {Object}
 * @constructor
 */
function GetUrlParam() {
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] =
                decodeURIComponent(decodeURIComponent(strs[i].split("=")[1]));
        }
    }
    return theRequest;
}

/***
 * ajax的post请求
 * @param url 请求的url
 * @param params 请求参数
 * @param successBack 成功之后的回调函数
 * @param errorBack 失败之后的回调函数
 */
function ajaxPost(url, params, successBack, errorBack) {
    ajax('POST', url, params, successBack, errorBack);
}

/***
 * ajax的get请求
 * @param url 请求的url
 * @param params 请求参数
 * @param successBack 成功之后的回调函数
 * @param errorBack 失败之后的回调函数
 */
function ajaxGet(url, params, successBack, errorBack) {
    ajax('GET', url, params, successBack, errorBack);
}

function ajax(type, wUrl, params, callBack, errorFn) {
    var p = encodeURI(params)
    $.ajax({
        url: URL+ 'mango' + wUrl,
        type: type,
        data: "jsonData=" + p,
        dataType: 'json',
        contentType: "application/json",
        crossDomain: true == !(document.all),
        success: function (res) {
            if (callBack) callBack(res);
        },
        error: function (err) {
            if (errorFn) errorFn(err);
        }
    })
}

function ajaxPostForm(wUrl, params, callBack, errorFn) {
    $.ajax({
        url: URL+ 'mango' + wUrl,
        type: "POST",
        data: "jsonData=" + params,
        dataType: 'json',
        success: function (res) {
            if (callBack) callBack(res);
        },
        error: function (err) {
            if (errorFn) errorFn(err);
        }
    })
}
/*
$(function(){
    var value = '芒果'
    var inp = $('#hotWord');
    inp.focus(function(){
        $(this).attr('placeholder',value)
    })
})*/


$('#myModal .close').click(function(){
    $('#myModal').hide();
})

$("#search-hotWord input").click(function () {
    this.value = '';
});
//跳转搜索页面
$("#search-hotWord .search-button").click(function () {
    var value = $("#hotWord").val();
    if (value == '' || value == '请输入关键词' ) {
       // alert("请输入要查询的关键字")
        $('#myModal').show();
    } else {
        location.href = "../searching/searching_index.html?val=" + value;
    };
});

// 关键词搜索框添加绑定回车函数
$('#search-hotWord').bind('keypress', function(event) {
    if (event.keyCode == "13") {
        $("#search-hotWord .search-button").click();
        location.href = "../searching/searching_index.html?val=" + value;
    }
});


//跳转搜索页面
$("#search-hotWord input").blur(function () {
    var value = $("#hotWord").val();
    if (value == '') {
        this.value = '请输入关键词';
    } else {
        this.value = value;
    };
});

// 图片轮播
function autoPlay(dom1, dom2, choose) {
    var lis = $("." + dom1).find("li").length,
        liWidth = $("." + dom1).find("li").width(),
        parentWidth = $("." + dom1).find("li").width() * lis;
    var ind = 0;
    //console.log(liWidth)
    $("." + dom1).css("width", parentWidth + "px");

    var startRollOne = setInterval(rollOne, 2000);
    //滑入轮播盒清除定时器
    $("." + dom1).hover(function () {
        clearInterval(startRollOne);
    }, function () {
        startRollOne = setInterval(rollOne, 2000);
    });

    //滑入控制器清除定时器
    if (dom2) {
        $("." + dom2).hover(function () {
            clearInterval(startRollOne);
        }, function () {
            startRollOne = setInterval(rollOne, 2000);
        });
    }

    //自动播放
    function rollOne() {
        $("." + dom1).animate({marginLeft: -liWidth + "px"}, 500, "linear", function () {
            $(this).css({marginLeft: "0px"});
            $(this).children("li").first().remove().clone(true).appendTo("." + dom1);
        });
        ind++;

        if (ind > lis - 1) {
            ind = 0;
        }
        if (dom2) {
            $("." + dom2).find("li").eq(ind).addClass("active").siblings().removeClass("active");
        }

    }

    //控制器点击事件
    if (dom2) {
        $("." + dom2).on("click", "li", function () {
            var inds = $(this).index();
            ind = inds;
            $("." + dom1).find("li").each(function (i, v) {
                var urlData = $(v).data("url");
                //console.log(urlData == ind)
                if (urlData == inds) {
                    //console.log(i)
                    $("." + dom1).animate({marginLeft: -(i * liWidth) + "px"}, 500, function () {
                        $(this).css({marginLeft: "0px"});
                        var htmlArr = [];
                        /*$(this).find("li").eq(i).prevAll().appendTo("."+dom1);*/
                        var prevs = $(this).find("li").eq(i).prevAll().length;
                        $(this).find("li").eq(i).prevAll().each(function (ind, lis) {

                            htmlArr.push(lis)
                        })
                        $.each(htmlArr.reverse(), function (i, v) {
                            $(v).appendTo("." + dom1)
                        })
                    });
                }
            });
            clearInterval(startRollOne);
            $(this).addClass("active").siblings().removeClass("active");

        })

    }
    if (choose) {
        $(".swiper-click").hover(function () {
            clearInterval(startRollOne);
        }, function () {
            startRollOne=setInterval(rollOne,2000);
        });
        $(".swiper-right").click(function () {
            $(this).siblings("." + dom1).animate({marginLeft: -liWidth + "px"}, 500, "linear", function () {
                $(this).css({marginLeft: "0px"});
                $(this).children("li").first().remove().clone(true).appendTo("." + dom1);
            });
            ind++;
            if (ind > lis - 1) {
                ind = 0;
            }
            //console.log(ind)
            if (dom2) {
                $("." + dom2).find("li").eq(ind).addClass("active").siblings().removeClass("active");
            }

        })
        $(".swiper-left").click(function () {
            $(this).siblings("." + dom1).css({marginLeft: -liWidth + "px"});
            $(this).siblings("." + dom1).children("li").last().remove().clone(true).prependTo("." + dom1);
            $(this).siblings("." + dom1).animate({marginLeft: "0"}, 500, "linear");
            ind--;
            if (ind < 0) {
                ind = lis - 1;
            }
            //console.log(ind)
            if (dom2) {
                $("." + dom2).find("li").eq(ind).addClass("active").siblings().removeClass("active");
            }

        })
    }
}

//视听图说
function fadeAuto(params) {
    var inds = 0;
    var timers = null;
    var len = $("." + params.elementDom).length;
    play();
    $("." + params.hoverDom).hover(function () {
        var ind = $(this).index();
        if (ind == 1) {
            inds = 0;
        } else if (ind == 3) {
            inds = 1;
        } else if (ind == 5) {
            inds = 2;
        } else if (ind == 7) {
            inds = 3;
        }
        clearInterval(timers);
        $("." + params.hoverDom).eq(inds).addClass("active").siblings().removeClass("active")
        $("." + params.parentDom).find("." + params.elementDom).eq(inds).addClass("active").siblings().removeClass("active")
    }, function () {
        play();
    })

    function play() {
        timers = setInterval(function () {
            inds++;
            if (inds > len - 1) inds = 0;
            $("." + params.hoverDom).eq(inds).addClass("active").siblings().removeClass("active")
            $("." + params.parentDom).find("." + params.elementDom).eq(inds).addClass("active").siblings().removeClass("active")

        }, 2000);
    }

    $("." + params.parentDom).find("." + params.elementDom).hover(function () {
        clearInterval(timers);
    }, function () {
        play();
    });
}

//模板引擎引用
function initTemplate(params) {
    var context = params.data;
    var source = '';
    source = $("#" + params.idScript).html();
    //注册一个Handlebars Helper,用来将索引+1，因为默认是从0开始的
    Handlebars.registerHelper("addOne", function (index, options) {
        return parseInt(index) + 1;
    });
    Handlebars.registerHelper("compare", function (index, options) {
        if (index == 0) {
            return true
        } else {
            return false
        }
    });

    Handlebars.registerHelper('eq', function (v1, v2, opts) {
        if (v1 == v2)
            return opts.fn(this);
        else
            return opts.inverse(this);
    });
    //注册判断是否是图说
    Handlebars.registerHelper("other", function (x1, options) {
        if (x1 == 1 || x1 == 2) {
            return options.fn(this);
        } else {
            return options.inverse(this);
        }

    });
    //注册超出省略号
    Handlebars.registerHelper("substr", function (num, options) {
        //console.log(options)
        if(options){
            var l = options.length;
            if ( l > num) {
                return options.strEllipsis(num,this);
            } else {
                return options;
            }
        }
    });
    //注册去除字段里的标签
    Handlebars.registerHelper("trimTag", function (options) {
        return options.trimTag(options);
    });
    //注册一个Handlebars Helper,用来显示数组的第一项
    Handlebars.registerHelper("showFirst", function (index, options) {
         return options.fn(index[0]);
    });
    //  注册判断class是否需要显示
    Handlebars.registerHelper('className4', function (index, opts) {
        return (index+1) % 4 == 0 ? 'mrt0' : ''
    });
    //  注册判断class是否需要显示
    Handlebars.registerHelper('className2', function (index, opts) {
        return (index + 1) % 2 == 0 ? 'mrt0' : ''
    });
    //  注册判断class  even或者odd
    Handlebars.registerHelper('clssName', function (index, opts) {
        return index%2 == 0 ? '' :'even'
    });
    //  注册判断class  even或者odd
    Handlebars.registerHelper('ifShow', function (index, opts) {
        return index == 0 ? 'divHide' :''
    });
    var template = Handlebars.compile(source);
    $("#" + params.idHtml).html(template(context));
}

/*params={
    idHtml: 'ents',
    idScript: 'tEnts',
    data:null
}*/

//获取菜单数据
function getMenu() {
    var submitData = {
        "entityRelated": {
            "channelLevel": '2'
        },
        "orderList" : [{
            "columnName" : "relationId",
            "isASC" : true
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 10
        }
    }

    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piChannel/getChannel", submitDataString, rendomMenu)
}

if ($("#menuList li").length < 1) {
    getMenu();
} else {
    var num = $("body").data("num") * 1;
    $("#menuList li").eq(num).addClass("active").siblings().removeClass("active");
}

//渲染一级菜单
var IpURL = window.location.origin; //获取当前ip地址和端口号
//console.log(IpURL)
function rendomMenu(val) {
    //console.log(val)
    var menuData = val.data;
    var num = $("body").data("num") * 1;
    var html = '<li><a href="../index/index.html">首页</a></li>';
    for (var i = 0; i < menuData.length; i++) {
        if (menuData[i].channel_name == '溯源') {
            html += '<li><a target="_blank" href="' + menuData[i].channel_path + '?channel_id=' + menuData[i].channel_id + '">' + menuData[i].channel_name + '</a></li>';
        } else {
            html += '<li><a href="' + IpURL + menuData[i].channel_path + '?channel_id=' + menuData[i].channel_id + '">' + menuData[i].channel_name + '</a></li>';
        }
    }
    $("#menuList").html(html)
    $("#menuList li").eq(num).addClass("active").siblings().removeClass("active");
    //判断首页是否需要选中
    var currentId = getQuery("channel_id");
    //console.log(currentId)
    if (currentId == '3b8f6e987d853f93c899d82c32216c3e') {
        $("#menuList li").eq(0).addClass("active").siblings().removeClass("active");
    }
}


//时间格式
function formatDate(now) {
    var now = new Date(now);
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    return year + "-" + addZero(month) + "-" + addZero(date) + " " + addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);
}

//时间范围
function durTime(param) {
    var date = new Date() * 1;
    var timeStr = {};
    //开始时间
    timeStr.endStr = formatDate(date);
    if (param == "day") {
        var startTime = date - 24 * 60 * 60 * 1000;
        timeStr.startTime = formatDate(startTime);
    } else if (param == "week") {
        var startTime = date - 7 * 24 * 60 * 60 * 1000;
        timeStr.startTime = formatDate(startTime);
    } else if (param == "month") {
        var startTime = date - 30 * 24 * 60 * 60 * 1000;
        timeStr.startTime = formatDate(startTime);
    } else if (param == "year") {
        var startTime = date - 365 * 24 * 60 * 60 * 1000;
        timeStr.startTime = formatDate(startTime);
    }
    return timeStr
}

//补零
function addZero(val) {
    if (val >= 10) {
        return val;
    } else {
        return "0" + val;
    }
}

var financing = "4cf9f045f84fb07a62c9196d0e6bd858";
//channelid变量
var channelData = {
    "hotNew": "6b8e0f738daed0e79952eba26faa82dc",//热点
    "brand": "061f1ee79c1ae60be0a089227de9af7d",//品牌
    "home": "3b8f6e987d853f93c899d82c32216c3e",//首页
    "trace": "dd7bb191cc357bdf6a72c1e5c3f0ced5",//溯源
    "demand": "e35a1ae2b0d5b3b2bc4a974126353027",//供求
    "bazaar": "1ea3ad71d14df858658faaf3df1a679e",//市场
    "interaction": "9b879659459acb5df1f987fd47a68eb0",//互动
    "serve": "f6399d07963955cd9f130a36b83e8409",//服务
    "market": "f05ceea60e43ceea731284802e3f12f6",//行情
    "price": "1c067d6d8336fec09d9a8aa6e0d9e36a",//价格
    "process": "2b6e1eb98ed2027292f244d49952979a",//加工
    "train": "afad9c378053afeb54db93993eb40c60",//培训
    "pests": "1da6e5de08b7eabeb97bff0ad7384c7c",//虫害
    "weather": "ae64d31600ff9b2398bee723fe928245",//气象
    "policy": "b8cd038d85c616e5c63d207339c2a07f",//政策
    "insurance": "1244c13a8d23b7bec4f7af216ad4c351",//保险
    "subsidy": "ae7d9b90fd2895816096fe815ec958b0 ",//补贴
    "poverty": "a027bd637de73eacf3c11c3afeea38b9",//扶贫
    "financing": "4cf9f045f84fb07a62c9196d0e6bd858",//融资
    "doctrine": "64e618ae9121cca7fda2e4cb60489041",//三品一标
    "rule": "66e177223f98b51fa3306d82b274fdc0",//认证规则
    "plant": "90cb31e180b0f1579f59db9a7629fde6",//种植
    "referrals": "6252e4700a6a4f6a832d0b55c4dc5f72",//推介
    "MicroSite": "dde558fd9baf441090be1f840b63d8f1",//企业微网站
    "MicroMall": "7e08ff47f4634674843ed10f71254d45",//企业微商城
    "interaction": "4d2382021c09499680c00032ec230197", //互动
    "respond":"de0522c544a67233a87078141f3b2b0f"//回应
};
var typeData = {
    "headline": "096bbfd1f15685395f0d5b4bd516e640",//头条
    "say": "5e0399a53bce096c0eab56e7a3671f18",//说
    "image": "778a3e846ce7fff470256b308dff02db",//图
    "imageText": "7fcf5967c79e58d971eff384f88a42a0",//图文
    "see": "954effd987455f4a12c647c09779915f",//视
    "focus": "c9126117dc414c9844248141a6fddaf3",//焦点
    "hear": "d9ebd8db0dfce217ad73ca2118fd09dc",//听
    "common": "da22285ea9b05d024aec2ea434bc3fee"//普通
}

//页面当前位置
if (getQuery("channel_id")) {
    var currentId = getQuery("channel_id"), url = 'mango/extend/swagger/pi/piChannel/getNavigationChannelList/channelId=' + getQuery("channel_id"), IndexId = '3b8f6e987d853f93c899d82c32216c3e';
    if (currentId != IndexId) {
        $.ajax({
            url: URL + url,
            success: function (res) {
               if(res.isSuccess){
                    //console.log(res)
                    var locationData = res.data.reverse();
                    var len = locationData.length;
                    if (len <= 1) {
                        html = '';
                    } else {
                        html = '<li>您的位置：</li>'
                        for (var i = 0; i < locationData.length; i++) {
                            if (locationData[i].channelName != '首页') {
                                //console.log(locationData[i].channelName)
                                 if(locationData[i].channelName == '推介'){
                                    html += '<li>' + locationData[i].channelName + '></li>'
                                 }else{
                                    if (i != locationData.length - 1) {
                                        html += '<li><a href="' + IpURL + locationData[i].channelPath + '?channel_id=' + locationData[i].id + '&relation_id=' + locationData[i].relationId + '">' + locationData[i].channelName + '</a>></li>'
                                    } else {
                                        html += '<li><a href="' + IpURL + locationData[i].channelPath + '?channel_id=' + locationData[i].id + '&relation_id=' + locationData[i].relationId + '">' + locationData[i].channelName + '</a></li>'
                                    }
                                 }

                            }
                        }
                    }
                    $("#CurrentLocation").html(html);
                    //判断当前位置的根节点添加active类名
                    var menuPositon = locationData[1].channelName;
                    //console.log(menuPositon)
                    var num = '';
                    switch (menuPositon) {
                        case '首页' :
                            num = 0;
                            break;
                        case '热点' :
                            num = 1;
                            break;
                        case '服务' :
                            num = 2;
                            break;
                        case '市场' :
                            num = 3;
                            break;
                        case '互动' :
                            num = 4;
                            break;
                        case '溯源' :
                            num = 5;
                            break;
                        default :
                            break;
                    };
                    $("#menuList li").eq(num).addClass("active").siblings().removeClass("active");
                }else{
                    return;
                }
            }
        })
    }

}

function getQuery(key, url) {
    url = url || window.location.href + '';
    if (url.indexOf('#') !== -1) {
        url = url.substring(0, url.indexOf('#'));
    }
    var rts = [], rt;
    var queryReg = new RegExp('(^|\\?|&)' + key + '=([^&]*)(?=&|#|$)', 'g');
    while ((rt = queryReg.exec(url)) != null) {
        rts.push(decodeURIComponent(rt[2]));
    }
    if (rts.length == 0) return null;
    if (rts.length == 1) return rts[0];
    return rts;
}


//将地址栏中?号后面的参数转化成数组的方法
function GetRequest(url) {
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

//  轮播swiper封装
function initBanner(dom){
    var mySwiper = new Swiper(dom, {
        //pagination: '.paginationswiper',
        autoplay: 2000,
        speed: 1000,
        paginationClickable: true,
        loop: true,
        /* onSlideChangeStart: function (swiper) {
            var wid = $('.pagination').width()
        } */
    })
}
//友情链接部分的交互
function getBlogroll() {
    var submitData = {
        "entityRelated": {
            "domainId": "032769fd7e376c04fb13c66419a72598"
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piFriendlink/getListByDomain", submitDataString, rendomBlogroll);
}

getBlogroll();
//友情链接
function rendomBlogroll(val) {
    var html = '';
   // console.log(val)
    for (var i = 0; i < val.data.length; i++) {
        if (val.isSuccess) {
            //标题部分
            if (i == val.data.length - 1) {
                html += '<a href="' + val.data[i].url + '" target="_blank" style="border-right:none;">' + val.data[i].name + '</a>';
            } else {
                html += '<a href="' + val.data[i].url + '" target="_blank">' + val.data[i].name + '</a>';
            }
        } else {
            html = '暂无链接';
        }
        }
    $(".blogroll-info").html(html);
}

//推介里面微商城(取一条鲜果数据)
function getMangoMallList() {
    var submitData = {
        "entityRelated": {
            "typeCode": "1", //1鲜果 2加工品
            "isRecommend": "", //是否推荐 0 是  1否
            "maxPrice": "", //最高价
            "minPrice": "" //最低价
        },
        "orderList": [{
            "columnName": "perPriceUnit",
            "isASC": false
        }],
        "page": {
            "pageIndex": 1,
            "pageSize": 1
        }
    };
    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/pi/piProductRecommend/getMangoMallList", submitDataString, rendomMangoMallList)
}
getMangoMallList();

function rendomMangoMallList(val) {
    //console.log(val)
    if (val.isSuccess) {
        var params = {
            idHtml: 'MangoMallList',
            idScript: 'MangoMallListSrc',
            data: {
                "MangoMallListData": val.data
            }
        };
        initTemplate(params);
        autoPlay("stroe-top-swipper", "", true);
    } else {
        $("#MangoMallList").html("<li class='noData'>暂无查询结果</li>")
    }

}

//推介里的企业微网站(取一条数据)
 function getReferrals() {
    var submitData = {
        "entityRelated": {
            "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
        },
        "page": {
            "pageIndex": 1,
            "pageSize": 1
        }
    };

    var submitDataString = JSON.stringify(submitData);
    ajaxGet("/extend/swagger/da/daEnterpriseInfo/getEnterpriseList", submitDataString, rendomeferrals);
}

getReferrals();

function rendomeferrals(val) {
    //console.log(val)
    var params = {
        idHtml: 'MicroSiteList',
        idScript: 'MicroSiteListSrc',
        data: {
            "MicroSiteListData": val.data
        }
    };
    if(val.isSuccess){
       initTemplate(params);
    }else{
        $("#MicroSiteList").html("<div class='noData'>暂无查询结果</div>")
    }

}