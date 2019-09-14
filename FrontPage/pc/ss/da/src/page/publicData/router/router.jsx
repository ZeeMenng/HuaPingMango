import React from "react";
import { LeftBarInternetData } from '../../component/leftBar';
import { Route, Redirect, Switch } from 'react-router-dom';
import DataMain from '../dataMaintaining';   //  数据维护
import DataMainAdd from '../dataMainAdd';
import ThemeMan from '../themeManager';         //  主题管理
import Acquisition from '../acquisitionRules';  //  采集规则
import AcquisitionAdd from '../acquisitionRulesAdd';  //  采集规则新增
import DataMana from '../dataManager';           //  数据源管理
import PriceWholesale from '../priceWholesale';  //  价格数据批发价格数据
import PriceDataAdd from "../priceDataAdd";//价格数据-新增
import PriceDataAduit from "../priceDataAduit";//价格数据-审核

import PriceEshop from '../priceEshop';          //  价格数据电商价格数据
import PriceEshopAdd from "../priceEshopAdd";//电商价格数据-修改
import PriceEshopAduit from "../priceEshopAduit";//电商价格数据-审核

import MainProducingData from '../mainProducingData';  //  主产区数据
import MainProducingAdd from '../MainProducingAdd';  //  主产区新增
import MainProducingAduit from '../mainProducingAduit';  //  主产区审核
import MainProducingUpdate from '../mainProducingUpdate';  //  主产区编辑



import MerchantsData from '../MerchantsData';  //  招商引资数据
import MerchantsAdd from '../MerchantsAdd';  //  招商引资数据
import MerchantsAudit from '../MerchantsAudit';  //  招商引资数据
import TradeInData from '../tradeInData';  //  进口数据
import TradeInAdd from '../tradeInAdd';  //  进口数据
import TradeInAudit from '../tradeInAudit';  //  进口数据
import TradeOutData from '../tradeOutData';  //  出口数据
import TradeOutAdd from '../TradeOutAdd';  //  出口数据
import TradeOutAudit from '../TradeOutAudit';  //  出口数据

import PriceDataPage from "../priceData";//价格数据


var route = [
    {
        path:'/priceWholesale',
        child:[
            {
                path:'/PriceWholesale',
                component: PriceWholesale,
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
        path:'/priceEshop',
        child:[
            {
                path:'/PriceEshop',
                component: PriceEshop,
            },
            {
                path: '/priceEshopAdd/:id',
                component: PriceEshopAdd,
            },
            {
                path: '/priceEshopAduit/:id',
                component: PriceEshopAduit,
            }
        ]
    },
    {
        path:'/MerchantsData',
        child:[
            {
                path:'/MerchantsData',
                component: MerchantsData,
            },
            {
                path:'/MerchantsAdd/:id',
                component: MerchantsAdd,
            },
            {
                path:'/MerchantsAudit/:id',
                component: MerchantsAudit,
            }
        ]
    },
    {
        path:'/mainProducingData',
        child:[
            {
                path:'/MainProducingData',
                component: MainProducingData,
            },
            {
                path:'/MainProducingAdd/:id',
                component: MainProducingAdd,
            },
            {
                path:'/MainProducingAduit/:id',
                component: MainProducingAduit,
            },
            {
                path:'/MainProducingUpdate/:id',
                component: MainProducingUpdate,
            }
        ]
    },
    {
        path:'/tradeInData',
        child:[
            {
                path:'/TradeInData',
                component: TradeInData,
            },
            {
                path:'/TradeInAdd/:id',
                component: TradeInAdd,
            },
            {
                path:'/TradeInAudit/:id',
                component: TradeInAudit,
            }
        ]
    },
    {
        path:'/tradeOutData',
        child:[
            {
                path:'/TradeOutData',
                component: TradeOutData,
            },
            {
                path:'/TradeOutAdd/:id',
                component: TradeOutAdd,
            },
            {
                path:'/TradeOutAudit/:id',
                component: TradeOutAudit,
            }
        ]
    },
    {
        path:'/dataMaintaining',
        child:[
            {
                path:'/dataMain',
                component: DataMain,
            },
            {
                path:'/dataMainAdd/:id',
                component: DataMainAdd,
            }
        ]
    },
    {
        path:'/themeManager',
        child:[
            {
                path:'/themeMan',
                component:ThemeMan,
            },
        ]
    },
    {
        path:'/acquisitionRules',
        child:[
            {
                path:'/acquisition',
                component:Acquisition,
            },
            {
                path:'/acquisitionAdd',
                component: AcquisitionAdd,
            },
        ]
    },
    {
        path:'/dataManager',
        child:[
            {
                path:'/dataMana',
                component:DataMana,
            },
        ]
    }
]
function creatChildRouter(item,url) {
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

const PublicData = ({ match }) => {
    //console.log(`${match.url}/farmerMess`)
    return (
        <div>
            <div className="mainBox clearfix">
                <LeftBarInternetData></LeftBarInternetData>
                <Switch>
                    {
                        route.map((item,index)=>{
                            return(
                                <Route key={index} path={`${match.path}${item.path}`}
                                       render ={
                                           () => ( creatChildRouter(item.child, `${match.path}${item.path}`))
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
export default PublicData;