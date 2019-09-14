import builder from './api-common';
// let BASEURL_01 = 'http://192.168.200.193:8081/mango'
//let BASEURL_01 = builder.BASEURL_01;
let DI_MEDIA_TYPE = 'ac1a2895f794a09f8e2be365b4f7b9e7'//媒体类型字典
let DI_POPULAR_CONTENT_ORIGIN_TYPE = "45b3fc76d3a7b9a84f6c6519b98903c5";//舆情文章表数据来源类型字典

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
const DI_ENTERPRISE_TYPE = "941b13c7c816cde7254b1bcdb431752c"; //企业类型下拉




// export const BASEURL_01 = 'http://192.168.200.193:8081/mango';
export const BASEURL_01 = builder.BASEURL_01;

// 地理区域数据
export const areaData = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/gp/gpDictionary/getRegionListByLevel',
    method: 'GET',
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
/* 市场价格数据新增 */
export const daMarketPriceAdd = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daMarketPrice/add',
    method: 'POST',
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
// 来源渠道下拉
export const originChannel = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_DATA_ORIGIN_CHANNEL,
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
/* 市场价格数据单条查询 */
export const daMarketPriceGetModel = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daMarketPrice/getModel',
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
// 进出口价格
export const getCountryPrice = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/gp/gpDictionary/getListByTypeId/' + DI_IMP_EXP_PRICE_UNIT,
    method: 'GET',
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
/* 市场价格数据列表 */
export const daMarketPriceList = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daMarketPrice/getListByJsonDatas',
    method: 'GET',
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








// 数据维护初始列表
export const dataMaintain = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentArticle/getListByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 数据维护修改
export const dataMainFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentArticle/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 数据维护删除
export const dataMainDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentArticle/delete',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 数据维护单条查询
export const dataMainSearch = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentArticle/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 数据源管理初始列表
export const dataManager = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentSource/getListByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 数据源管理新增
export const FrameMessAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentSource/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 数据源管理编辑
export const FrameMessUpdate = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentSource/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 数据源管理删除
export const FrameMessDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentSource/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 媒体类型下拉
export const mediaType = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/'+DI_MEDIA_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 数据来源下拉
export const popularData = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/gp/gpDictionary/getListByTypeId/'+DI_POPULAR_CONTENT_ORIGIN_TYPE,
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
//采集修改
export const acquisitionRulesUpdate = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daSentimentRule/update',
    method: 'POST',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
});
//采集新增
export const acquisitionRulesAdd = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daSentimentRule/add',
    method: 'POST',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 采集初始列表
export const acquisitionRules = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daSentimentRule/getListByJsonData',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 采集删除
export const acquisitionRulesDel = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daSentimentRule/delete',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 主题管理
export const daSentimentThemeList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentTheme/getListByJsonData',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 主题管理单条
export const daSentimentThemeGetModel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentTheme/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 主题管理新增
export const daSentimentThemeAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentTheme/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
// 主题管理修改
export const daSentimentThemeFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentTheme/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
//  主题管理删除
export const daSentimentThemeDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daSentimentTheme/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});

/*****
 * 最新
 */
//  主题管理删除
export const priceWholesaleList = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daMarketPrice/getListByJsonDatas',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
  });


/* 电商价格数据列表 */
export const PriceEshopList = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/mf/mfSaleEcommerceCraw/getListByJsonData',
    method: 'GET',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 电商价格单调数据 */
  export const PriceEshopGet = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/mf/mfSaleEcommerceCraw/getModel',
      method: 'GET',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 电商价格审核 */
  export const PriceEshopAudit = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/mf/mfSaleEcommerceCraw/audit',
      method: 'POST',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 电商价格删除 */
  export const PriceEshopRemove = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/mf/mfSaleEcommerceCraw/delete',
      method: 'POST',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 主产区数据列表 */
  export const MainProducingList = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daGrowYield/getListByJsonDatasWl',
      method: 'GET',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 主产区数据添加 */
  export const MainProducingAdd = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daGrowYield/insert',
      method: 'POST',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 主产区数据删除 */
  export const MainProducingDel = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daGrowYield/remove',
      method: 'POST',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 主产区数据单条 */
  export const MainProducingGet = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daGrowYield/getInfo',
      method: 'GET',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 主产区数据单条 */
  export const MainProducingFix = builder.build({
    baseUrl: BASEURL_01,
    url: '/extend/swagger/da/daGrowYield/modify',
    method: 'POST',
    simulation: false,
    simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 主产区数据审核 */
export const MainProducingAduit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daGrowYield/auditWl',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
  /* 出口列表 */
  export const TradeOutList = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daImportExport/getListByJsonDatas',
      method: 'GET',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 出口新增 */
  export const TradeOutAdd = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daImportExport/add',
      method: 'POST',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 出口查询单条 */
  export const TradeOutGet = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daImportExport/getModel',
      method: 'GET',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 出口修改 */
  export const TradeOutFix = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daImportExport/update',
      method: 'POST',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 出口审核 */
  export const TradeOutAduit = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daImportExport/audit',
      method: 'POST',
      simulation: false,
      simulator: './static/api-simulation/home-page/deepProcessing.json'
  });
  /* 出口删除 */
  export const TradeOutRemove = builder.build({
      baseUrl: BASEURL_01,
      url: '/extend/swagger/da/daImportExport/delete',
      method: 'GET',
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
/* 进口列表 */
export const TradeInList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/getListByJsonDatas',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进口审核 */
export const TradeInAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进口单条 */
export const TradeInGet = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进口删除 */
export const TradeInDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进口添加 */
export const TradeInAdd = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/add',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 进口修改 */
export const TradeInFix = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daImportExport/update',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业列表*/
export const MerchantsList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/getListByJsonData',
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
/* 企业信息删除*/
export const MerchantsDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业单条*/
export const MerchantsGet = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/getModel',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 股东*/
export const gdxxList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseShareholderInfo/getList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 股东删除*/
export const gdxxDel = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseShareholderInfo/delete',
  method: 'GET',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 行政许可*/
export const xzxkList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseCertificateInfo/getList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 资产*/
export const qyzcList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterprisePropertyInfo/getList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 法院*/
export const fyList = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseLitigationInfo/getList',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});
/* 企业审核*/
export const MerchantsAudit = builder.build({
  baseUrl: BASEURL_01,
  url: '/extend/swagger/da/daEnterpriseInfo/audit',
  method: 'POST',
  simulation: false,
  simulator: './static/api-simulation/home-page/deepProcessing.json'
});