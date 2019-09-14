import builder from './api-common';
let BASEURL_01 = 'http://202.106.10.250:62018'
// let BASEURL_01 = builder.BASEURL_01;
let DI_MEDIA_TYPE = 'ac1a2895f794a09f8e2be365b4f7b9e7'//媒体类型字典
let DI_POPULAR_CONTENT_ORIGIN_TYPE = "45b3fc76d3a7b9a84f6c6519b98903c5";//舆情文章表数据来源类型字典



export const getJd = builder.build({
  baseUrl: BASEURL_01,
  url: '/ent/syBaseOrigin/getOriginListGropByEnId',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
export const getList = builder.build({
  baseUrl: BASEURL_01,
  url: '/iot/basic/getListData',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 监控类型
export const jkType = builder.build({
  baseUrl: BASEURL_01,
  url: '/iot/monitor/getMonitorList',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 走势图
export const zsEchart = builder.build({
  baseUrl: BASEURL_01,
  url: '/iot/monitor/getIotMonitorDataTrend',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 监控数据
export const jkData = builder.build({
  baseUrl: BASEURL_01,
  url: '/iot/monitor/getListData',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});