import builder from './api-common';

let BASEURL_01 = builder.BASEURL_01;
const DI_INPUT_TYPE = "e94c59ca245b8cb85038922067beb00d"
/* 下拉时间 */
export const getTimeData = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/gp/gpDictionary/getTimesView',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/UnmarketablePublic.json'
});
/** 灾害类型数字统计  */
export const getAdisasterNumData = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/da/daIotMonitorDisasterData/getAgricultureDisasterFrequency',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/UnmarketablePublic.json'
});
/** 灾害类型预警信息  */
export const getAdisasterTableData = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/da/daIotMonitorDisasterData/getPerBaseAgricultueDisasterInfo',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/UnmarketablePublic.json'
});
/** 影响因素  */
export const getFactorBarData = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/da/daIotMonitorDisasterData/getPerLifeCycleAgricultueDisasterInfo',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/UnmarketablePublic.json'
});
/** 灾害类型预警走势  */
export const getWarningLineData = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/da/daIotMonitorDisasterData/getAgricultueDisasterEarlyWarningTrend',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/UnmarketablePublic.json'
});
/** 灾害类型预警走势下拉  */
export const getWarningType = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_INPUT_TYPE,
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/UnmarketablePublic.json'
});
/** 百度地图数据信息  */
export const getAdisasterBaiduMap = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/da/daIotMonitorDisasterData/getPerBaseAgricultueDisasterInfo',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/UnmarketablePublic.json'
});
