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
const DI_ENTERPRISE_TYPE = "941b13c7c816cde7254b1bcdb431752c"; //企业类型下拉
const DI_IMP_EXP_PRICE_UNIT = "1d5bbf4e32a185a919cd6cc3bf1c72db";//进出口价格单位字典
const DI_AGRICULTURE_DISASTER_TYPE = "e94c59ca245b8cb85038922067beb00d";//灾害类型字典
// 地理区域数据
export const areaData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getRegionListByLevel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 企业类型下拉
export const enterpriseType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_ENTERPRISE_TYPE,
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
// 时间维度下拉
export const timeDimension = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_TIME_DIMENSION,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 价格元/斤下拉
export const impExpPrice = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_IMP_EXP_PRICE_UNIT,
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
/* 农户信息列表 */
export const FrameMess = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daPeasantInfo/getListByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户信息新增 */
export const FrameMessAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daPeasantInfo/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户信息修改 */
export const FrameMessFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daPeasantInfo/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户信息审核 */
export const FrameMessAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daPeasantInfo/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户信息修改之前渲染数据 */
export const FrameMessFixBefore = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daPeasantInfo/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户信息删除信息 */
export const FrameMessDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daPeasantInfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 农户信息批量删除方法 */
export const FrameMessDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daPeasantInfo/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 海拔数据列表 */
export const altitudeList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daAltitudeInfo/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 海拔数据单条数据查询 */
export const altitudeOneData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIdentificationInfo/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 海拔数据单条数据删除 */
export const altitudeDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daAltitudeInfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 海拔数据单条数据批量删除 */
export const altitudeDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daAltitudeInfo/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 海拔数据审核 */
export const altitudeAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daAltitudeInfo/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 海拔数据新增 */
export const altitudeAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daAltitudeInfo/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 海拔数据新增 */
export const altitudeFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daAltitudeInfo/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植生态列表 */
export const plantingIndxList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEcologyNormInfo/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植生态单条数据查询 */
export const plantingIndxOne = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEcologyNormInfo/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 种植生态删除 */
export const plantingIndxDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEcologyNormInfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植生态批量删除 */
export const plantingIndxDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEcologyNormInfo/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植生态审核 */
export const plantingIndexAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEcologyNormInfo/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植生态新增 */
export const plantingIndxAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEcologyNormInfo/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植生态修改 */
export const plantingIndxFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEcologyNormInfo/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 三品一标列表 */
export const authenticationList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIdentificationInfo/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 三品一标单个删除 */
export const authenticationDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIdentificationInfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 三品一标单个批量删除 */
export const authenticationDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIdentificationInfo/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 三品一标新增 */
export const authenticationAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIdentificationInfo/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 三品一标修改 */
export const authenticationFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIdentificationInfo/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 三品一标审核 */
export const authenticationAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daIdentificationInfo/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 投入品列表 */
export const inputList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daInput/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 投入品查询 */
export const inputOneData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daInput/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 投入品单个删除 */
export const inputDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daInput/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 投入品批量删除 */
export const inputDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daInput/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 投入品审核 */
export const inputAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daInput/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 投入品新增 */
export const inputAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daInput/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 投入品修改 */
export const inputFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daInput/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});


//企业信息列表
export const enterpriseMess = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业信息新增 */
export const enterpriseMessAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业信息单条数据删除 */
export const enterpriseMessDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业信息批量删除 */
export const enterpriseMessDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业信息修改前渲染*/
export const enterpriseMessBefore = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业信息修改 */
export const enterpriseMessUpdate = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业信息审核 */
export const enterpriseMessAduit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 种植成本列表 */
export const costDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowCost/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植成本单个数据查询 */
export const costDataSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowCost/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植成本单条数据删除 */
export const costDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowCost/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植成本批量删除 */
export const costDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowCost/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植成本审核 */
export const costDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowCost/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植成本新增 */
export const costDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowCost/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 种植成本修改 */
export const costDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowCost/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/* 基地数据列表 */
export const baseDataList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daBaseInfo/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基地数据单个数据查询 */
export const baseDataSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daBaseInfo/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基地数据单条数据删除 */
export const baseDataDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daBaseInfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基地数据批量删除 */
export const baseDataDelList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daBaseInfo/deleteList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基地数据审核 */
export const baseDataAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daBaseInfo/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基地数据新增 */
export const baseDataAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daBaseInfo/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 基地数据修改 */
export const baseDataFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daBaseInfo/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});