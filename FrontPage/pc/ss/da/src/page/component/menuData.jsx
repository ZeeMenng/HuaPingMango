import FarmerMessPage from "../forData/farmerMess";//主体数据-农户信息
import FarmerMessAdd from "../forData/farmerMessAdd";//主体数据-农户信息-新增修改
import FarmerMessAduit from "../forData/farmerMessAduit";//主体数据-农户信息-数据审核
import EnterpriseMessPage from "../forData/enterpriseMess";//主体数据-企业信息
import EnterpriseMessAdd from "../forData/enterpriseMessAdd";//主体数据-企业信息-新增
import EnterpriseMessAduit from "../forData/enterpriseMessAduit";//主体数据-企业信息-审核
import BaseDataPage from "../forData/baseData";//产地数据-基地数据
import BaseDataAdd from "../forData/baseDataAdd";//产地数据-基地数据-新增
import BaseDataAduit from "../forData/baseDataAduit";//产地数据-基地数据-审核
import EnvironmentDataPage from "../forData/environmentData";//环境数据-海拔数据
import EnvironmentDataAdd from "../forData/environmentDataAdd";//环境数据-海拔数据-新增
import EnvironmentDataAduit from "../forData/environmentDataAduit";//环境数据-海拔数据-审核
import PlantingIndexPage from "../forData/plantingIndex";//环境数据-种植生态指标
import PlantingIndexAdd from "../forData/plantingIndexAdd";//环境数据-种植生态指标-新增
import PlantingIndexAduit from "../forData/plantingIndexAduit";//环境数据-种植生态指标-审核
import AuthenticationDataPage from "../forData/authenticationData";//认证数据-三品一标认证
import AuthenticationDataAdd from "../forData/authenticationDataAdd";//认证数据-三品一标认证-新增
import AuthenticationDataAduit from "../forData/authenticationDataAduit";//认证数据-三品一标认证-审核
import ProductionDataPage from "../forData/productionData";//生产数据-投入品使用数据
import ProductionDataAdd from "../forData/productionDataAdd";//生产数据-投入品使用数据-新增
import ProductionDataAduit from "../forData/productionDataAduit";//生产数据-投入品使用数据-审核
import PlantingDataPage from "../forData/plantingData";//生产数据-种植数据
import PlantingDataAdd from "../forData/plantingDataAdd";//生产数据-种植数据-新增
import PlantingDataAduit from "../forData/plantingDataAduit";//生产数据-种植数据-审核
import ProcessDataPage from "../forData/processData";//加工数据-加工品数据
import ProcessDataAdd from "../forData/processDataAdd";//加工数据-加工品数据-新增
import ProcessDataAduit from "../forData/processDataAduit";//加工数据-加工品数据-审核
import SellDataPage from "../forData/sellData";//销售数据-农户销售数据
import SellDataAdd from "../forData/sellDataAdd";//销售数据-农户销售数据-新增
import SellDataAduit from "../forData/sellDataAduit";//销售数据-农户销售数据-审核
import FreshFruitSellDataPage from "../forData/freshFruitSellData";//销售数据-鲜果销售数据
import FreshFruitSellDataAdd from "../forData/freshFruitSellDataAdd";//销售数据-鲜果销售数据-新增
import FreshFruitSellDataAduit from "../forData/freshFruitSellDataAduit";//销售数据-鲜果销售数据-审核
import ProcessSellDataPage from "../forData/processSellData";//销售数据-加工销售数据
import ProcessSellDataAdd from "../forData/processSellDataAdd";//销售数据-加工销售数据-新增
import ProcessSellDataAduit from "../forData/processSellDataAduit";//销售数据-加工销售数据-审核
import ChannelSellDataPage from "../forData/channelSellData";//销售数据-渠道销售数据
import ChannelSellDataAdd from "../forData/channelSellDataAdd";//销售数据-渠道销售数据-新增
import ChannelSellDataAduit from "../forData/channelSellDataAduit";//销售数据-渠道销售数据-审核
import ECommerceSellDataPage from "../forData/eCommerceSellData";//销售数据-电商订单数据
import ECommerceSellDataAdd from "../forData/eCommerceSellDataAdd";//销售数据-电商订单数据-新增
import ECommerceSellDataAduit from "../forData/eCommerceSellDataAduit";//销售数据-电商订单数据-审核
import IndustryDataPage from "../forData/industryData";//产业数据-基本情况数据
import IndustryDataAdd from "../forData/industryDataAdd";//产业数据-基本情况数据-新增
import IndustryDataAduit from "../forData/industryDataAduit";//产业数据-基本情况数据-审核
import FreshFruitDataPage from "../forData/freshFruitData";//产业数据-鲜果产值数据
import FreshFruitDataAdd from "../forData/freshFruitDataAdd";//产业数据-鲜果产值数据-新增
import FreshFruitDataAduit from "../forData/freshFruitDataAduit";//产业数据-鲜果产值数据-审核
import ProcessValueDataPage from "../forData/processValueData";//产业数据-加工品产值数据
import ProcessValueDataAdd from "../forData/processValueDataAdd";//产业数据-加工品产值数据-新增
import ProcessValueDataAduit from "../forData/processValueDataAduit";//产业数据-加工品产值数据-审核
import StatisticsDataPage from "../forData/statisticsData";//产业数据-产业统计数据
import StatisticsDataAdd from "../forData/statisticsDataAdd";//产业数据-产业统计数据-新增
import StatisticsDataAduit from "../forData/statisticsDataAduit";//产业数据-产业统计数据-审核
import MonitorDataPage from "../forData/monitorData";//检测数据
import MonitorDataAdd from "../forData/monitorDataAdd";//检测数据-新增
import MonitorDataAduit from "../forData/monitorDataAduit";//检测数据-审核
import SamplingDataPage from "../forData/samplingData";//检测数据-采样数据
import SamplingDataAdd from "../forData/samplingDataAdd";//检测数据-采样数据-新增
import SamplingDataAduit from "../forData/samplingDataAduit";//检测数据-采样数据-审核
import TradeDataPage from "../forData/tradeData";//贸易数据
import TradeDataAdd from "../forData/tradeDataAdd";//贸易数据-新增
import TradeDataAduit from "../forData/tradeDataAduit";//贸易数据-审核
import PriceDataPage from "../forData/priceData";//价格数据
import PriceDataAdd from "../forData/priceDataAdd";//价格数据-新增
import PriceDataAduit from "../forData/priceDataAduit";//价格数据-审核
import DisasterDataPage from "../forData/disasterData";//灾害数据
import DisasterDataAdd from "../forData/disasterDataAdd";//灾害数据-新增
import DisasterDataAduit from "../forData/disasterDataAduit";//灾害数据-审核
import CostDataPage from "../forData/costData";//成本数据
import CostDataAdd from "../forData/costDataAdd";//成本数据-新增
import CostDataAduit from "../forData/costDataAduit";//成本数据-审核

import DataMain from '../publicData/dataMaintaining';   //  数据维护
import DataMainAdd from '../publicData/dataMainAdd';    //  数据维护
import ThemeMan from '../publicData/themeManager';         //  主题管理
import Acquisition from '../publicData/acquisitionRules';  //  采集规则
import AcquisitionAdd from '../publicData/acquisitionRulesAdd';  //  采集规则新增
import DataMana from '../publicData/dataManager';         //  数据源管理

import Company1 from '../thingsInternet/company1';   //  农丰果蔬种植专业合作社
import Company2 from '../thingsInternet/company2';   //  临江村王道相大户芒果种植合作社


import ResourceManagementPage from '../dataResource/resourceManagement'; //  资源方管理
/*import ResourceManagementAdd from '../dataResource/resourceManagementAdd'; //  资源方管理-新增*/
import ServerManagementPage from '../dataResource/serverManagement';     //  服务器管理
/*import ServerManagementAdd from '../dataResource/serverManagementAdd';*/     //  服务器管理-新增



export {
    FarmerMessPage,
    FarmerMessAdd,
    FarmerMessAduit,
    EnterpriseMessPage,
    EnterpriseMessAdd,
    EnterpriseMessAduit,
    BaseDataPage,
    BaseDataAdd,
    BaseDataAduit,
    EnvironmentDataPage,
    EnvironmentDataAdd,
    EnvironmentDataAduit,
    PlantingIndexPage,
    PlantingIndexAdd,
    PlantingIndexAduit,
    AuthenticationDataPage,
    AuthenticationDataAdd,
    AuthenticationDataAduit,
    ProductionDataPage,
    ProductionDataAdd,
    ProductionDataAduit,
    PlantingDataPage,
    PlantingDataAdd,
    PlantingDataAduit,
    ProcessDataPage,
    ProcessDataAdd,
    ProcessDataAduit,
    SellDataPage,
    SellDataAdd,
    SellDataAduit,
    FreshFruitSellDataPage,
    FreshFruitSellDataAdd,
    FreshFruitSellDataAduit,
    ProcessSellDataPage,
    ProcessSellDataAdd,
    ProcessSellDataAduit,
    ChannelSellDataPage,
    ChannelSellDataAdd,
    ChannelSellDataAduit,
    ECommerceSellDataPage,
    ECommerceSellDataAdd,
    ECommerceSellDataAduit,
    IndustryDataPage,
    IndustryDataAdd,
    IndustryDataAduit,
    FreshFruitDataPage,
    FreshFruitDataAdd,
    FreshFruitDataAduit,
    ProcessValueDataPage,
    ProcessValueDataAdd,
    ProcessValueDataAduit,
    StatisticsDataPage,
    StatisticsDataAdd,
    StatisticsDataAduit,
    MonitorDataPage,
    MonitorDataAdd,
    MonitorDataAduit,
    SamplingDataPage,
    SamplingDataAdd,
    SamplingDataAduit,
    TradeDataPage,
    TradeDataAdd,
    TradeDataAduit,
    PriceDataPage,
    PriceDataAdd,
    PriceDataAduit,
    DisasterDataPage,
    DisasterDataAdd,
    DisasterDataAduit,
    CostDataPage,
    CostDataAdd,
    CostDataAduit,
    DataMain,
    DataMainAdd,
    ThemeMan,
    Acquisition,
    AcquisitionAdd,
    DataMana,
    ResourceManagementPage,
    ServerManagementPage,
    Company1,
    Company2
};