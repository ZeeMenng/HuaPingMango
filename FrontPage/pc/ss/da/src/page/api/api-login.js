import builder from './api-common';

/* 鲜果销量TOP10 */
let BASEURL_01 = builder.BASEURL_01;
// let BASEURL_01 = 'http://192.168.200.193:8081/mango'
export const Login = builder.build({
  baseUrl: BASEURL_01,
  url: '/oauth/token',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 菜单接口 */
export const menu = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/gp/gpMenu/getLinkMenuFormat',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
  });