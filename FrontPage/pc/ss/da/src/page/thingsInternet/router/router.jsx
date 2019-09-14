import React from "react";
import { LeftBarThingsInternetData } from '../../component/leftBar';
import { Route, Redirect, Switch } from 'react-router-dom';
import Company1 from '../company1'; //农丰果蔬种植专业合作社
import Company2 from '../company2'; //临江村王道相大户芒果种植合作社
import Company3 from '../company3'; //永学芒果种植专业合作社
import Company4 from '../company4'; //农欣芒果种植农民专业合作社
import Company5 from '../company5'; //绿康源农业开发有限公司
import Company6 from '../company6'; //金芒果生态开发有限公司
import MonitorData from '../monitorData'; //监控数据
import TrendChart from '../trendChart'; //走势图

var route = [
    {
        path: '/company1',
        child: [
            {
                path: '/company1',
                component: Company1,
            },
            {
                path:'/MonitorData/:id',
                component: MonitorData,
            },
            {
                path: '/TrendChart/:id',
                component: TrendChart,
            }
        ]
    },
    {
        path: '/company2',
        child: [
            {
                path: '/company2',
                component: Company2,
            }
        ]
    },
    {
        path: '/company3',
        child: [
            {
                path: '/company3',
                component: Company3,
            }
        ]
    },
    {
        path: '/company4',
        child: [
            {
                path: '/company4',
                component: Company4,
            }
        ]
    },
    {
        path: '/company5',
        child: [
            {
                path: '/company5',
                component: Company5,
            }
        ]
    },
    {
        path: '/company6',
        child: [
            {
                path: '/company6',
                component: Company6,
            }
        ]
    }
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
const ThingsInternet = ({ match }) => {

    return (
        <div>
            <div className="mainBox clearfix thingsInternet">
                <LeftBarThingsInternetData></LeftBarThingsInternetData>
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
                    }
                    <Redirect from={`${match.path}/`} to={`${match.path}${route[0].path}`} />
                </Switch>
            </div>
        </div>
    )
}
export default ThingsInternet;