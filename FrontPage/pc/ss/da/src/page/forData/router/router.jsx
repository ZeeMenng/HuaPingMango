import React from "react";
import { LeftBarForData } from '../../component/leftBar';
import { Route, Redirect, Switch } from 'react-router-dom'
import FarmerMessPage from "../farmerMess";//主体数据-农户信息
import FarmerMessAdd from "../farmerMessAdd";//主体数据-农户信息-新增修改
import FarmerMessAduit from "../farmerMessAduit";//主体数据-农户信息-数据审核
import EnterpriseMessPage from "../enterpriseMess";//主体数据-企业信息
import EnterpriseMessAdd from "../enterpriseMessAdd";//主体数据-企业信息-新增
import EnterpriseMessAduit from "../enterpriseMessAduit";//主体数据-企业信息-审核
import BaseDataPage from "../baseData";//产地数据-基地数据
import BaseDataAdd from "../baseDataAdd";//产地数据-基地数据-新增
import BaseDataAduit from "../baseDataAduit";//产地数据-基地数据-审核
import EnvironmentDataPage from "../environmentData";//环境数据-海拔数据
import EnvironmentDataAdd from "../environmentDataAdd";//环境数据-海拔数据-新增
import EnvironmentDataAduit from "../environmentDataAduit";//环境数据-海拔数据-审核
import PlantingIndexPage from "../plantingIndex";//环境数据-种植生态指标
import PlantingIndexAdd from "../plantingIndexAdd";//环境数据-种植生态指标-新增
import PlantingIndexAduit from "../plantingIndexAduit";//环境数据-种植生态指标-审核
import AuthenticationDataPage from "../authenticationData";//认证数据-三品一标认证
import AuthenticationDataAdd from "../authenticationDataAdd";//认证数据-三品一标认证-新增
import AuthenticationDataAduit from "../authenticationDataAduit";//认证数据-三品一标认证-审核
import ProductionDataPage from "../productionData";//生产数据-投入品使用数据
import ProductionDataAdd from "../productionDataAdd";//生产数据-投入品使用数据-新增
import ProductionDataAduit from "../productionDataAduit";//生产数据-投入品使用数据-审核
import PlantingDataPage from "../plantingData";//生产数据-种植数据
import PlantingDataAdd from "../plantingDataAdd";//生产数据-种植数据-新增
import PlantingDataAduit from "../plantingDataAduit";//生产数据-种植数据-审核
import ProcessDataPage from "../processData";//加工数据-加工品数据
import ProcessDataAdd from "../processDataAdd";//加工数据-加工品数据-新增
import ProcessDataAduit from "../processDataAduit";//加工数据-加工品数据-审核
import SellDataPage from "../sellData";//销售数据-农户销售数据
import SellDataAdd from "../sellDataAdd";//销售数据-农户销售数据-新增
import SellDataAduit from "../sellDataAduit";//销售数据-农户销售数据-审核
import FreshFruitSellDataPage from "../freshFruitSellData";//销售数据-鲜果销售数据
import FreshFruitSellDataAdd from "../freshFruitSellDataAdd";//销售数据-鲜果销售数据-新增
import FreshFruitSellDataAduit from "../freshFruitSellDataAduit";//销售数据-鲜果销售数据-审核
import ProcessSellDataPage from "../processSellData";//销售数据-加工销售数据
import ProcessSellDataAdd from "../processSellDataAdd";//销售数据-加工销售数据-新增
import ProcessSellDataAduit from "../processSellDataAduit";//销售数据-加工销售数据-审核
import ChannelSellDataPage from "../channelSellData";//销售数据-渠道销售数据
import ChannelSellDataAdd from "../channelSellDataAdd";//销售数据-渠道销售数据-新增
import ChannelSellDataAduit from "../channelSellDataAduit";//销售数据-渠道销售数据-审核
import ECommerceSellDataPage from "../eCommerceSellData";//销售数据-电商订单数据
import ECommerceSellDataAdd from "../eCommerceSellDataAdd";//销售数据-电商订单数据-新增
import ECommerceSellDataAduit from "../eCommerceSellDataAduit";//销售数据-电商订单数据-审核
// import IndustryDataPage from "../industryData";//产业数据-基本情况数据
// import IndustryDataAdd from "../industryDataAdd";//产业数据-基本情况数据-新增
// import IndustryDataAduit from "../industryDataAduit";//产业数据-基本情况数据-审核
// import FreshFruitDataPage from "../freshFruitData";//产业数据-鲜果产值数据
// import FreshFruitDataAdd from "../freshFruitDataAdd";//产业数据-鲜果产值数据-新增
// import FreshFruitDataAduit from "../freshFruitDataAduit";//产业数据-鲜果产值数据-审核
// import ProcessValueDataPage from "../processValueData";//产业数据-加工品产值数据
// import ProcessValueDataAdd from "../processValueDataAdd";//产业数据-加工品产值数据-新增
// import ProcessValueDataAduit from "../processValueDataAduit";//产业数据-加工品产值数据-审核
// import StatisticsDataPage from "../statisticsData";//产业数据-产业统计数据
// import StatisticsDataAdd from "../statisticsDataAdd";//产业数据-产业统计数据-新增
// import StatisticsDataAduit from "../statisticsDataAduit";//产业数据-产业统计数据-审核
// import MonitorDataPage from "../monitorData";//检测数据
// import MonitorDataAdd from "../monitorDataAdd";//检测数据-新增
// import MonitorDataAduit from "../monitorDataAduit";//检测数据-审核
// import SamplingDataPage from "../samplingData";//检测数据-采样数据
// import SamplingDataAdd from "../samplingDataAdd";//检测数据-采样数据-新增
// import SamplingDataAduit from "../samplingDataAduit";//检测数据-采样数据-审核
import TradeDataPage from "../tradeData";//贸易数据
import TradeDataAdd from "../tradeDataAdd";//贸易数据-新增
import TradeDataAduit from "../tradeDataAduit";//贸易数据-审核
import PriceDataPage from "../priceData";//价格数据
import PriceDataAdd from "../priceDataAdd";//价格数据-新增
import PriceDataAduit from "../priceDataAduit";//价格数据-审核
import DisasterDataPage from "../disasterData";//灾害数据
import DisasterDataAdd from "../disasterDataAdd";//灾害数据-新增
import DisasterDataAduit from "../disasterDataAduit";//灾害数据-审核
import CostDataPage from "../costData";//成本数据
import CostDataAdd from "../costDataAdd";//成本数据-新增
import CostDataAduit from "../costDataAduit";//成本数据-审核

var route = [
    {
        path: '/farmerMess',
        child: [
            {
                path: '/farmerMessPage',
                component: FarmerMessPage,
            },
            {
                path: '/farmerMessAdd/:id',
                component: FarmerMessAdd,
            },
            {
                path: '/farmerMessAduit/:id',
                component: FarmerMessAduit,
            }
        ]
    },
    {
        path: '/enterpriseMess',
        child: [
            {
                path: '/enterpriseMessPage',
                component: EnterpriseMessPage,
            },
            {
                path: '/enterpriseMessAdd/:id',
                component: EnterpriseMessAdd,
            },
            {
                path: '/enterpriseMessAduit/:id',
                component: EnterpriseMessAduit,
            }
        ]
    },
    {
        path: '/baseData',
        child: [
            {
                path: '/baseDataPage',
                component: BaseDataPage,
            },
            {
                path: '/baseDataAdd/:id',
                component: BaseDataAdd,
            },
            {
                path: '/baseDataAduit/:id',
                component: BaseDataAduit,
            }
        ]
    },
    {
        path: '/environmentData',
        child: [
            {
                path: '/environmentDataPage',
                component: EnvironmentDataPage,
            },
            {
                path: '/environmentDataAdd/:id',
                component: EnvironmentDataAdd,
            },
            {
                path: '/environmentDataAduit/:id',
                component: EnvironmentDataAduit,
            }
        ]
    },
    {
        path: '/plantingIndex',
        child: [
            {
                path: '/plantingIndexPage',
                component: PlantingIndexPage,
            },
            {
                path: '/plantingIndexAdd/:id',
                component: PlantingIndexAdd,
            },
            {
                path: '/plantingIndexAduit/:id',
                component: PlantingIndexAduit,
            }
        ]
    },
    {
        path: '/authenticationData',
        child: [
            {
                path: '/authenticationDataPage',
                component: AuthenticationDataPage,
            },
            {
                path: '/authenticationDataAdd/:id',
                component: AuthenticationDataAdd,
            },
            {
                path: '/authenticationDataAduit/:id',
                component: AuthenticationDataAduit,
            }
        ]
    },
    {
        path: '/productionData',
        child: [
            {
                path: '/productionDataPage',
                component: ProductionDataPage,
            },
            {
                path: '/productionDataAdd/:id',
                component: ProductionDataAdd,
            },
            {
                path: '/productionDataAduit/:id',
                component: ProductionDataAduit,
            }
        ]
    },
    {
        path: '/plantingData',
        child: [
            {
                path: '/plantingDataPage',
                component: PlantingDataPage,
            },
            {
                path: '/plantingDataAdd/:id',
                component: PlantingDataAdd,
            },
            {
                path: '/plantingDataAduit/:id',
                component: PlantingDataAduit,
            }
        ]
    },
    {
        path: '/processData',
        child: [
            {
                path: '/processDataPage',
                component: ProcessDataPage,
            },
            {
                path: '/processDataAdd/:id',
                component: ProcessDataAdd,
            },
            {
                path: '/processDataAduit/:id',
                component: ProcessDataAduit,
            }
        ]
    },
    {
        path: '/sellData',
        child: [
            {
                path: '/sellDataPage',
                component: SellDataPage,
            },
            {
                path: '/sellDataAdd/:id',
                component: SellDataAdd,
            },
            {
                path: '/sellDataAduit/:id',
                component: SellDataAduit,
            }
        ]
    },
    {
        path: '/freshFruitSellData',
        child: [
            {
                path: '/freshFruitSellDataPage',
                component: FreshFruitSellDataPage,
            },
            {
                path: '/freshFruitSellDataAdd/:id',
                component: FreshFruitSellDataAdd,
            },
            {
                path: '/freshFruitSellDataAduit/:id',
                component: FreshFruitSellDataAduit,
            }
        ]
    },
    {
        path: '/processSellData',
        child: [
            {
                path: '/processSellDataPage',
                component: ProcessSellDataPage,
            },
            {
                path: '/processSellDataAdd/:id',
                component: ProcessSellDataAdd,
            },
            {
                path: '/processSellDataAduit/:id',
                component: ProcessSellDataAduit,
            }
        ]
    },
    {
        path: '/channelSellData',
        child: [
            {
                path: '/channelSellDataPage',
                component: ChannelSellDataPage,
            },
            {
                path: '/channelSellDataAdd/:id',
                component: ChannelSellDataAdd,
            },
            {
                path: '/channelSellDataAduit/:id',
                component: ChannelSellDataAduit,
            }
        ]
    },
    {
        path: '/eCommerceSellData',
        child: [
            {
                path: '/eCommerceSellDataPage',
                component: ECommerceSellDataPage,
            },
            {
                path: '/eCommerceSellDataAdd/:id',
                component: ECommerceSellDataAdd,
            },
            {
                path: '/eCommerceSellDataAduit/:id',
                component: ECommerceSellDataAduit,
            }
        ]
    },
    // {
    //     path: '/industryData',
    //     child: [
    //         {
    //             path: '/industryDataPage',
    //             component: IndustryDataPage,
    //         },
    //         {
    //             path: '/industryDataAdd/:id',
    //             component: IndustryDataAdd,
    //         },
    //         {
    //             path: '/industryDataAduit/:id',
    //             component: IndustryDataAduit,
    //         }
    //     ]
    // },
    // {
    //     path: '/freshFruitData',
    //     child: [
    //         {
    //             path: '/freshFruitDataPage',
    //             component: FreshFruitDataPage,
    //         },
    //         {
    //             path: '/freshFruitDataAdd/:id',
    //             component: FreshFruitDataAdd,
    //         },
    //         {
    //             path: '/freshFruitDataAduit/:id',
    //             component: FreshFruitDataAduit,
    //         }
    //     ]
    // },
    // {
    //     path: '/processValueData',
    //     child: [
    //         {
    //             path: '/processValueDataPage',
    //             component: ProcessValueDataPage,
    //         },
    //         {
    //             path: '/processValueDataAdd/:id',
    //             component: ProcessValueDataAdd,
    //         },
    //         {
    //             path: '/processValueDataAduit/:id',
    //             component: ProcessValueDataAduit,
    //         }
    //     ]
    // },
    // {
    //     path: '/statisticsData',
    //     child: [
    //         {
    //             path: '/statisticsDataPage',
    //             component: StatisticsDataPage,
    //         },
    //         {
    //             path: '/statisticsDataAdd/:id',
    //             component: StatisticsDataAdd,
    //         },
    //         {
    //             path: '/statisticsDataAduit/:id',
    //             component: StatisticsDataAduit,
    //         }
    //     ]
    // },
    // {
    //     path: '/monitorData',
    //     child: [
    //         {
    //             path: '/monitorDataPage',
    //             component: MonitorDataPage,
    //         },
    //         {
    //             path: '/monitorDataAdd/:id',
    //             component: MonitorDataAdd,
    //         },
    //         {
    //             path: '/monitorDataAduit/:id',
    //             component: MonitorDataAduit,
    //         }
    //     ]
    // },
    // {
    //     path: '/samplingData',
    //     child: [
    //         {
    //             path: '/samplingDataPage',
    //             component: SamplingDataPage,
    //         },
    //         {
    //             path: '/samplingDataAdd/:id',
    //             component: SamplingDataAdd,
    //         },
    //         {
    //             path: '/samplingDataAduit/:id',
    //             component: SamplingDataAduit,
    //         }
    //     ]
    // },
    {
        path: '/tradeData',
        child: [
            {
                path: '/tradeDataPage',
                component: TradeDataPage,
            },
            {
                path: '/tradeDataAdd/:id',
                component: TradeDataAdd,
            },
            {
                path: '/tradeDataAduit/:id',
                component: TradeDataAduit,
            }
        ]
    },
    {
        path: '/priceData',
        child: [
            {
                path: '/priceDataPage',
                component: PriceDataPage,
            },
            {
                path: '/priceDataAdd/:id',
                component: PriceDataAdd,
            },
            {
                path: '/priceDataAduit/:id',
                component: PriceDataAduit,
            }
        ]
    },
    {
        path: '/disasterData',
        child: [
            {
                path: '/disasterDataPage',
                component: DisasterDataPage,
            },
            {
                path: '/disasterDataAdd/:id',
                component: DisasterDataAdd,
            },
            {
                path: '/disasterDataAduit/:id',
                component: DisasterDataAduit,
            }
        ]
    },
    {
        path: '/costData',
        child: [
            {
                path: '/costDataPage',
                component: CostDataPage,
            },
            {
                path: '/costDataAdd/:id',
                component: CostDataAdd,
            },
            {
                path: '/costDataAduit/:id',
                component: CostDataAduit,
            }
        ]
    },
]
function creatChildRouter(item, url) {
    if (!item) return;
    return <Switch>
        {
            item.map((v, index) => {
                return (
                    <Route key={index} path={`${url}${v.path}`} component={v.component} />
                )
            })
        }
        <Redirect from={url} to={`${url}${item[0].path}`} />
    </Switch>
}
const ForData = ({ match }) => {
    return (
        <div>
            <div className="mainBox clearfix">
                <LeftBarForData></LeftBarForData>
                <Switch>
                    {
                        route.map((item, index) => {
                            return (
                                <Route key={index} path={`${match.path}${item.path}`}
                                    render={
                                        () => (creatChildRouter(item.child, `${match.path}${item.path}`))
                                    }
                                />
                            )
                        })
                        // route[route.length]
                    }

                    <Redirect from={`${match.path}/`} to={`${match.path}${route[0].path}`} />
                </Switch>
            </div>
        </div>
    )
}
export default ForData;
