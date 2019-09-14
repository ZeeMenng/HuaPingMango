import React from "react";
import { LeftBarViewData } from '../../component/leftBar';
import { Route, Redirect, Switch } from 'react-router-dom';
// import DocumentData from '../documentData';                 //  文档数据
// import PictureData from '../pictureData';               //  数据审核
// import VideoData from '../videoData';         //  数据审核

import MonitorDataPage from "../../forData/monitorData";//检测数据
import MonitorDataAdd from "../../forData/monitorDataAdd";//检测数据-新增
import MonitorDataAduit from "../../forData/monitorDataAduit";//检测数据-审核
import SamplingDataPage from "../../forData/samplingData";//检测数据-采样数据
import SamplingDataAdd from "../../forData/samplingDataAdd";//检测数据-采样数据-新增
import SamplingDataAduit from "../../forData/samplingDataAduit";//检测数据-采样数据-审核
var route = [
    {
        path: '/monitorData',
        child: [
            {
                path: '/monitorDataPage',
                component: MonitorDataPage,
            },
            {
                path: '/monitorDataAdd/:id',
                component: MonitorDataAdd,
            },
            {
                path: '/monitorDataAduit/:id',
                component: MonitorDataAduit,
            }
        ]
    },
    {
        path: '/samplingData',
        child: [
            {
                path: '/samplingDataPage',
                component: SamplingDataPage,
            },
            {
                path: '/samplingDataAdd/:id',
                component: SamplingDataAdd,
            },
            {
                path: '/samplingDataAduit/:id',
                component: SamplingDataAduit,
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
const ViewData = ({ match }) => {
    console.log(`${match.url}/farmerMess`)
    return (
        <div>
            <div className="mainBox clearfix">
                <LeftBarViewData></LeftBarViewData>
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
export default ViewData;