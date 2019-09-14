import React from "react";
import { LeftBarIndexReport } from '../../component/leftBar';
import { Route, Redirect, Switch } from 'react-router-dom';

// import DataMain from '../../publicData/dataMaintaining';   //  数据维护
// import DataMainAdd from '../../publicData/dataMainAdd';
// import ThemeMan from '../../publicData/themeManager';         //  主题管理
// import Acquisition from '../../publicData/acquisitionRules';  //  采集规则
// import AcquisitionAdd from '../../publicData/acquisitionRulesAdd';  //  采集规则新增
// import DataMana from '../../publicData/dataManager';         //  数据源管理

import IndustryDataPage from "../../forData/industryData";//产业数据-基本情况数据
import IndustryDataAdd from "../../forData/industryDataAdd";//产业数据-基本情况数据-新增
import IndustryDataAduit from "../../forData/industryDataAduit";//产业数据-基本情况数据-审核
import FreshFruitDataPage from "../../forData/freshFruitData";//产业数据-鲜果产值数据
import FreshFruitDataAdd from "../../forData/freshFruitDataAdd";//产业数据-鲜果产值数据-新增
import FreshFruitDataAduit from "../../forData/freshFruitDataAduit";//产业数据-鲜果产值数据-审核
import ProcessValueDataPage from "../../forData/processValueData";//产业数据-加工品产值数据
import ProcessValueDataAdd from "../../forData/processValueDataAdd";//产业数据-加工品产值数据-新增
import ProcessValueDataAduit from "../../forData/processValueDataAduit";//产业数据-加工品产值数据-审核
import StatisticsDataPage from "../../forData/statisticsData";//产业数据-产业统计数据
import StatisticsDataAdd from "../../forData/statisticsDataAdd";//产业数据-产业统计数据-新增
import StatisticsDataAduit from "../../forData/statisticsDataAduit";//产业数据-产业统计数据-审核
var route = [
    {
        path:'/industryData',
        child:[
            {
                path:'/industryDataPage',
                component: IndustryDataPage,
            },
            {
                path:'/industryDataAdd/:id',
                component: IndustryDataAdd,
            },
            {
                path:'/industryDataAduit/:id',
                component: IndustryDataAduit,
            }
        ]
    },
    {
        path:'/freshFruitData',
        child:[
            {
                path:'/freshFruitDataPage',
                component:FreshFruitDataPage,
            },
            {
                path:'/freshFruitDataAdd/:id',
                component: FreshFruitDataAdd,
            },
            {
                path:'/freshFruitDataAduit/:id',
                component: FreshFruitDataAduit,
            }
        ]
    },
    {
        path:'/processValueData',
        child:[
            {
                path:'/ProcessValueDataPage',
                component:ProcessValueDataPage,
            },
            {
                path:'/processValueDataAdd/:id',
                component: ProcessValueDataAdd,
            },
            {
                path:'/processValueDataAduit/:id',
                component: ProcessValueDataAduit,
            }
        ]
    },
    {
        path:'/statisticsData',
        child:[
            {
                path:'/statisticsDataPage',
                component:StatisticsDataPage,
            },
            {
                path:'/statisticsDataAdd/:id',
                component: StatisticsDataAdd,
            },
            {
                path:'/statisticsDataAduit/:id',
                component: StatisticsDataAduit,
            }
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
const IndexReport = ({ match }) => {
    console.log(`${match.url}/farmerMess`)
    return (
        <div>
            <div className="mainBox clearfix">
                <LeftBarIndexReport></LeftBarIndexReport>
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
export default IndexReport;