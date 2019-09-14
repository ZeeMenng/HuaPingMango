import builder from './api-common';


let BASEURL_01 = builder.BASEURL_01;

// 供求预测
export const PredictionLine = builder.build({
  baseUrl: BASEURL_01,
  url: 'mango/extend/swagger/mf/mfProductSale/forecast',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

//鲜芒果不同品种产销率监测
export const MonitorLine = builder.build({
  baseUrl: BASEURL_01,
  url: 'mango/extend/swagger/mf/mfProductSale/getFreshProSaleRate',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

//芒果加工品产销率监测
export const ProcessLine = builder.build({
  baseUrl: BASEURL_01,
  url: 'mango/extend/swagger/mf/mfProductSale/getProcessProSaleRate',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

//鲜芒果不同品种下拉框
export const selectMonitorData = builder.build({
  baseUrl: BASEURL_01,
  url: 'mango/extend/swagger/gp/gpDictionary/getListByTypeId/48690fc04089cb696dfad2c1780153a7',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

//芒果加工品下拉框
export const selectProcessData = builder.build({
  baseUrl: BASEURL_01,
  url: 'mango/extend/swagger/gp/gpDictionary/getListByTypeId/817d9f61ddf66623ffd2cf55e0f107ea',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 产销价差  */
export const getMakeSalesData = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/da/daMarketPrice/getPriceGap',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
});
export const getAreaData = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/extend/swagger/gp/gpRegion/getListByJsonData',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/UnmarketablePublic.json'
});