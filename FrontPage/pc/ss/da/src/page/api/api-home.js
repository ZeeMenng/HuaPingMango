import builder from './api-common';
// export const BASEURL_01 = 'http://192.168.200.193:8081/mango';
export const BASEURL_01 = builder.BASEURL_01;


/* 菜单接口 */
export const menu = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpMenu/getLinkMenuFormat',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 数据总量走势 */
export const allData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCommonField/getTrendByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 数据来源渠道排名 */
export const sourceData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCommonField/getSource',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 日采集数据走势 */
export const dayData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCommonField/getDayTrendByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 数据主题数据统计 */
export const areaData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCommonField/getSubject',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 待审核数据 */
export const dataType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCommonField/getMonthlyTobeAudited',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 总栏数 */
export const numList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCommonField/getTotalNumByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

