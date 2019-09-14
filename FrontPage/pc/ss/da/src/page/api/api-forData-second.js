import builder from './api-common';
// export const BASEURL_01 = 'http://192.168.200.193:8081/mango';
export const BASEURL_01 = builder.BASEURL_01;
const DI_MEDIA_TYPE = 'ac1a2895f794a09f8e2be365b4f7b9e7'; //媒体类型字典
const DI_TIME_DIMENSION = "97afe4eee26aa23a604e502b622a6c0f"; //时间维度字典
const DI_DATA_ORIGIN_CHANNEL = "a700900a555b71ff47bb8cf7f7bb0956"; //数据来源渠道字典
const DI_PEASANT_TYPE = "a1c4f5ac1ac291938fae4d1e3bc47150"; //农户类型字典
const DI_MOBILE_OPERATE_TYPE = "f03c7b3ff277b15adc93c638cba4a43f"; //智能手机操作熟练程度类型字典
const DI_REGION_LEVEL = "25fe79d29b55bdba65cf229346598886"; //区域等级字典
const DI_CROP_BREED = "f8ba8a76017c654648c239a4a0f37b34"; //作物种类字典
const DI_CROP_STRAINS = "48690fc04089cb696dfad2c1780153a7"; //作物品种字典
const DI_AREA_UNIT = "b9a7bb32ad454f89bd6eeb9c4ba0cfb6"; //面积单位字典
const DI_PROCESS_OUTPUT = "be9c1c8427ca3cbf7d2cf4a8980c4461"; //产品产量字典
const DI_WEIGHT_UNIT = "2507ae7b1899bb66e25f4553e9d1fcd2"; //重量单位字典
const DI_IDENTIFICATION_TYPE = "98f54771eac5c1fa52160e7886b581fe"; //认证类型字典
const DI_BASE_TYPE = "ad02806ca91ed6f7be7c1d523f4c68aa"; //基地类型字典
const DI_PRICE_UNIT = "adb2790cc6001d5122aefb819390860c"; //价钱单位字典
const DI_INPUT_TYPE = "4dc6fc902d4fd40ba72cfe62710e5237"; //投入品类型字典
const DI_COST_UNIT = "5943c4d653093cd90c9590461e69690d"; //成本单位字典
const DI_PROCESS_TYPE = "fba9b823668de16542f3b4d4a664538f"; //加工类型字典
const DI_CHANNEL_OF_DISTRIBUTION_TYPE = "f768e57c0536a6ebe712c68885caa1cd"; //销售渠道类型字典
const DI_EBUSINESS_TYPE = "cd924a789656342d92bcac2f918de386"; //电商平台类型字典
const DI_MODE_OF_PAYMENT = "a3591b2bc52fc111e595680483c38bdd"; //支付方式类型字典
const DI_PROCESS_BREED = "817d9f61ddf66623ffd2cf55e0f107ea"; //加工品品种字典
const DI_AGRICULTURE_DISASTER_TYPE = "e94c59ca245b8cb85038922067beb00d"; //灾害类型字典
const DI_IMP_EXP_PRICE_UNIT = "1d5bbf4e32a185a919cd6cc3bf1c72db"; //进出口价格单位字典
const DI_PRICE_TYPE = "477041e2029754cf2c746db6958a7f89"; //价格类型字典
const DI_MON_TYPE = "c568174d90de453788798ebc132b33de"
// 监测项目下拉
export const montionGet = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_MON_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 地理区域数据
export const areaData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getRegionListByLevel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 时间维度下拉
export const timeDimension = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_TIME_DIMENSION,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 成本单位下拉
export const costUnit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_COST_UNIT,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 灾害类型下拉
export const disAsterType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_AGRICULTURE_DISASTER_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 支付方式下拉
export const modeOfPayment = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_MODE_OF_PAYMENT,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 销售渠道下拉
export const channlDistributione = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_CHANNEL_OF_DISTRIBUTION_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 电商平台类型下拉
export const ebusinessType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_EBUSINESS_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 加工类型下拉
export const processType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_PROCESS_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 重量单位下拉
export const weightUnit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_WEIGHT_UNIT,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 投入品类型下拉
export const inputType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_INPUT_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 价钱单位下拉
export const priceUnit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_PRICE_UNIT,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 基地类型下拉
export const baseType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_BASE_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 作物种类下拉
export const cropBreed = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_CROP_BREED,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 作物品种下拉
export const cropStrains = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_CROP_STRAINS,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 认证类型下拉
export const identificationType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_IDENTIFICATION_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 面积单位下拉
export const areaUnit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_AREA_UNIT,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 产品产量单位下拉
export const processUnit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_PROCESS_OUTPUT,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 来源渠道下拉
export const originChannel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_DATA_ORIGIN_CHANNEL,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 农户类型下拉
export const frameType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_PEASANT_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 手机水平下拉
export const mobileOperate = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_MOBILE_OPERATE_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 区域等级下拉
export const regionLevel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_REGION_LEVEL,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植数据列表 */
export const plantingList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowYield/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植数据单条数据查询 */
export const plantingOneData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowYield/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植数据单条数据删除 */
export const plantingDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowYield/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植数据数据批量删除 */
export const plantingDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowYield/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植数据审核 */
export const plantingAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowYield/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植数据新增 */
export const plantingAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowYield/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植数据修改 */
export const plantingFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowYield/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户销售列表 */
export const framerSellList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSalePeasant/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户销售单个数据查询 */
export const framerSellSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSalePeasant/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户销售单条数据删除 */
export const framerSellDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSalePeasant/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户销售数据批量删除 */
export const framerSellDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSalePeasant/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户销售审核 */
export const framerSellAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSalePeasant/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户销售新增 */
export const framerSellAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSalePeasant/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户销售修改 */
export const framerSellFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSalePeasant/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});


/* 鲜果销售列表 */
export const fruitSellList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleFresh/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果销售单个数据查询 */
export const fruitSellSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleFresh/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果销售单条数据删除 */
export const fruitSellDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleFresh/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果销售数据批量删除 */
export const fruitSellDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleFresh/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果销售审核 */
export const fruitSellAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleFresh/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果销售新增 */
export const fruitSellAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleFresh/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果销售修改 */
export const fruitSellFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleFresh/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 加工销售列表 */
export const processSellList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleProcess/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工销售单个数据查询 */
export const processSellSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleProcess/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工销售单条数据删除 */
export const processSellDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleProcess/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工销售数据批量删除 */
export const processSellDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleProcess/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工销售审核 */
export const processSellAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleProcess/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工销售新增 */
export const processSellAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleProcess/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工销售修改 */
export const processSellFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleProcess/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 渠道销售列表 */
export const channelSellList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleChannel/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 渠道销售单个数据查询 */
export const channelSellSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleChannel/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 渠道销售单条数据删除 */
export const channelSellDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleChannel/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 渠道销售数据批量删除 */
export const channelSellDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleChannel/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 渠道销售审核 */
export const channelSellAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleChannel/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 渠道销售新增 */
export const channelSellAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleChannel/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 渠道销售修改 */
export const channelSellFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleChannel/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 电商订单列表 */
export const shopFormList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleEcommerceOrder/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 电商订单单个数据查询 */
export const shopFormSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleEcommerceOrder/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 电商订单单条数据删除 */
export const shopFormDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleEcommerceOrder/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 电商订单数据批量删除 */
export const shopFormDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleEcommerceOrder/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 电商订单审核 */
export const shopFormAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleEcommerceOrder/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 电商订单新增 */
export const shopFormAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleEcommerceOrder/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 电商订单修改 */
export const shopFormFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSaleEcommerceOrder/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 基本情况列表 */
export const industryDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryBasicinfo/getListByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基本情况单个数据查询 */
export const industryDataSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryBasicinfo/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基本情况单条数据删除 */
export const industryDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryBasicinfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基本情况数据批量删除 */
export const industryDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryBasicinfo/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基本情况审核 */
export const industryDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryBasicinfo/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基本情况新增 */
export const industryDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryBasicinfo/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基本情况修改 */
export const industryDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryBasicinfo/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 鲜果产值列表 */
export const freshFruitList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryFresh/getListByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果产值单个数据查询 */
export const freshFruitSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryFresh/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果产值单条数据删除 */
export const freshFruitDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryFresh/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果产值数据批量删除 */
export const freshFruitDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryFresh/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果产值审核 */
export const freshFruitAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryFresh/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果产值新增 */
export const freshFruitAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryFresh/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 鲜果产值修改 */
export const freshFruitFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryFresh/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 采样数据列表 */
export const samplingDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckCollection/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 采样数据单个数据查询 */
export const samplingDataSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckCollection/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 采样数据单条数据删除 */
export const samplingDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckCollection/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 采样数据数据批量删除 */
export const samplingDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckCollection/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 采样数据审核 */
export const samplingDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckCollection/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 采样数据新增 */
export const samplingDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckCollection/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 采样数据修改 */
export const samplingDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckCollection/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 检测数据列表 */
export const monitorDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckTesting/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 检测数据单个数据查询 */
export const monitorDataSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckTesting/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 检测数据单条数据删除 */
export const monitorDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckTesting/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 检测数据批量删除 */
export const monitorDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckTesting/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 检测数据审核 */
export const monitorDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckTesting/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 检测数据新增 */
export const monitorDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckTesting/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 检测数据修改 */
export const monitorDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCheckTesting/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 作物受灾列表 */
export const disasterDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCorpDisaster/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 作物受灾单个数据查询 */
export const disasterDataSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCorpDisaster/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 作物受灾单条数据删除 */
export const disasterDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCorpDisaster/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 作物受灾批量删除 */
export const disasterDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCorpDisaster/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 作物受灾审核 */
export const disasterDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCorpDisaster/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 作物受灾新增 */
export const disasterDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCorpDisaster/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 作物受灾修改 */
export const disasterDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daCorpDisaster/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 加工品产值数据 */
export const processValueDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryProcess/getListByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品产值单条数据查询 */
export const daIndustryProcess = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryProcess/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品产值单条数据删除 */
export const processValueDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryProcess/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品产值数据批量删除 */
export const processValueDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryProcess/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品产值数据新增 */
export const processValueDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryProcess/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品产值数据审核 */
export const processValueDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryProcess/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品产值数据修改 */
export const processValueDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryProcess/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品种 */
export const processStrains = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_PROCESS_BREED,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 产业统计数据列表 */
export const dIStatisticsDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryStatistics/getListByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/*  产业统计数据新增 */
export const dIStatisticsDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryStatistics/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/*  产业统计数据修改 */
export const dIStatisticsDataUpdate = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryStatistics/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 产业统计数据单条查询 */
export const dIStatisticsDataGetModel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryStatistics/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 产业统计数据单条删除 */
export const dIStatisticsDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryStatistics/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 产业统计数据批量删除 */
export const dIStatisticsDataDeleteList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryStatistics/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 产业统计数据审核 */
export const dIStatisticsDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIndustryStatistics/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进出口量数据列表 */
export const tradeDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进出口量数据单条查询 */
export const tradeDataGetModel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进出口量数据新增 */
export const tradeDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进出口量数据删除 */
export const tradeDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进出口量数据批量删除 */
export const tradeDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进出口量数据修改 */
export const tradeDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进出口量数据审核 */
export const tradeDataAduit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进出口国家 */
export const getCountry = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpRegionCountry/getListByJsonData?jsonData={}',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 进出口价格
export const getCountryPrice = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_IMP_EXP_PRICE_UNIT,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 市场价格数据列表 */
export const daMarketPriceList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daMarketPrice/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 市场价格数据单条查询 */
export const daMarketPriceGetModel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daMarketPrice/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 市场价格数据新增 */
export const daMarketPriceAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daMarketPrice/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 市场价格数据修改 */
export const daMarketPriceFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daMarketPrice/update',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 市场价格数据删除 */
export const daMarketPriceDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daMarketPrice/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 市场价格数据批量删除 */
export const daMarketPriceDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daMarketPrice/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 市场价格数据批量审核 */
export const daMarketPriceAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daMarketPrice/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 价格类型下拉
export const priceType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_PRICE_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品数据列表 */
export const processDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daProcess/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品数据单个数据查询 */
export const processDataSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daProcess/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品数据单条数据删除 */
export const processDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daProcess/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品数据数据批量删除 */
export const processDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daProcess/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品数据审核 */
export const processDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daProcess/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品数据新增 */
export const processDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daProcess/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 加工品数据修改 */
export const processDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daProcess/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
