/*
* BASEURL_01 ====> http://zhr.shi.da.hao:ren/
* */
// var URL = 'http://192.168.200.193:8081/';
//外网地址
//window.BASEURL_01 = 'http://202.106.10.250:62011';
//测试地址
var test_Url = 'http://192.168.200.193:8081/';
//var test_Url = 'http://202.106.10.250:62019/';
window.BASEURL_01 = typeof(URLMG) === 'undefined' ? test_Url : URLMG;

// full: 0x03, debug: 0x05
window.resizeMode = 0x05;
