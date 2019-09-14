import React from "react";
import { NavLink,withRouter } from "react-router-dom";
import "./common.scss";
//  直报数据
let forDataRoute = [
    {
        name:'主体数据',
        iconClass:'mainData',
        path: "/forData/farmerMess",
        child:[
            {
                name: '农户信息',
                path: "/forData/farmerMess",
            },
            {
                name: '企业信息',
                path: "/forData/enterpriseMess",
            }
        ]
    },
    {
        name:'产地数据',
        iconClass:'areaData',
        path: "/forData/baseData",
        child:[
            {
                name: '基地数据',
                path: "/forData/baseData",
            }
        ]
    },
    // {
    //     name:'环境数据',
    //     iconClass:'environmentData',
    //     path: "/forData/environmentData",
    //     child:[
    //         {
    //             name: '海拔数据',
    //             path: "/forData/environmentData",
    //         },
    //         {
    //             name: '种植生态指标',
    //             path: "/forData/plantingIndex",
    //         }
    //     ]
    // },
    {
        name:'认证数据',
        iconClass:'authenticationData',
        path: "/forData/authenticationData",
        child:[
            {
                name: '三品一标认证',
                path: "/forData/authenticationData",
            }
        ]
    },
    {
        name:'生产数据',
        iconClass: 'productionData',
        path: "/forData/productionData",
        child:[
            {
                name: '投入品使用数据',
                path: "/forData/productionData",
            },
            {
                name: '种植数据',
                path: "/forData/plantingData",
            }
        ]
    },
    {
        name:'加工数据',
        iconClass: 'processData',
        path: "/forData/processData",
        child:[
            {
                name: '加工品数据',
                path: "/forData/processData",
            }
        ]
    },
    {
        name:'销售数据',
        iconClass: 'sellData',
        path: "/forData/sellData",
        child:[
            {
                name: '农户销售数据',
                path: "/forData/sellData",
            },
            {
                name: '鲜果销售数据',
                path: "/forData/freshFruitSellData",
            },
            {
                name: '加工销售数据',
                path: "/forData/processSellData",
            },
            {
                name: '渠道销售数据',
                path: "/forData/channelSellData",
            },
            {
                name: '电商订单数据',
                path: "/forData/eCommerceSellData",
            }
        ]
    },
    // {
    //     name:'产业数据',
    //     iconClass: 'industryData',
    //     path: "/forData/industryData",
    //     child:[
    //         {
    //             name: '基本情况数据',
    //             path: "/forData/industryData",
    //         },
    //         {
    //             name: '鲜果产值数据',
    //             path: "/forData/freshFruitData",
    //         },
    //         {
    //             name: '加工品产值数据',
    //             path: "/forData/processValueData",
    //         },
    //         {
    //             name: '产业统计数据',
    //             path: "/forData/statisticsData",
    //         }
    //     ]
    // },
    // {
    //     name:'检测数据',
    //     iconClass: 'monitorData',
    //     path: "/forData/monitorData",
    //     child: [
    //         {
    //             name: '采样数据',
    //             path: "/forData/samplingData",
    //         },
    //         {
    //             name: '检测数据',
    //             path: "/forData/monitorData",
    //         }
    //     ]
    // },
    {
        name:'贸易数据',
        iconClass: 'tradeData',
        path: "/forData/tradeData",
        child:[
            {
                name: '进出口量数据',
                path: "/forData/tradeData",
            }
        ]
    },
    {
        name:'价格数据',
        iconClass: 'priceData',
        path: "/forData/priceData",
        child:[
            {
                name: '市场价格数据',
                path: "/forData/priceData",
            }
        ]
    },
    {
        name:'气象数据',
        iconClass: 'disasterData',
        path: "/forData/disasterData",
        child:[
            {
                name: '气象数据',
                path: "/forData/disasterData",
            }
        ]
    },
    {
        name:'成本数据',
        iconClass: 'costData',
        path: "/forData/costData",
        child:[
            {
                name: '农户种植成本数据',
                path: "/forData/costData",
            }
        ]
    }
]
//  产业数据
let indexReportRoute = [
    {
        name: '基本情况数据',
        iconClass: 'dataMaintaining',
        path: "/indexReport/industryData",
    },
    {
        name: '鲜果产值数据',
        iconClass: 'themeManager',
        path: "/indexReport/freshFruitData",
    },
    {
        name: '加工品产值数据',
        iconClass: 'acquisitionRules',
        path: "/indexReport/processValueData",
    },
    {
        name: '产业统计数据',
        iconClass: 'dataManager',
        path: "/indexReport/statisticsData",
    },
    // {
    //     name: '数据维护',
    //     iconClass: 'dataMaintaining',
    //     path: "/indexReport/dataMaintaining"
    // },
    // {
    //     name: '主题管理',
    //     iconClass: 'themeManager',
    //     path: "/indexReport/themeManager"
    // },
    // {
    //     name: '采集规则',
    //     iconClass: 'acquisitionRules',
    //     path: "/indexReport/acquisitionRules"
    // },
    // {
    //     name: '数据源管理',
    //     iconClass: 'dataManager',
    //     path: "/indexReport/dataManager"
    // }
]
//  检测数据
let viewDataRoute = [
    {
        name: '检测数据',
        iconClass: 'monitorData',
        path: "/viewData/monitorData",
    },
    {
        name: '采样数据',
        iconClass: 'documentData',
        path: "/viewData/samplingData",
    }
]
//  数据资源
let dataResourceRoute = [
    {
        name: '资源方管理',
        iconClass: 'resourceManagement',
        path: "/dataResource/resourceManagement"
    },
    {
        name: '服务器管理',
        iconClass: 'serverManagement',
        path: "/dataResource/serverManagement"
    }
]
//网络数据
let internetDataRoute = [
    {
        name:'价格数据',
        iconClass:'acquisitionRules',
        path: "/publicData/priceWholesale",
        child:[
            {
                name: '批发价格数据',
                path: "/publicData/priceWholesale",
            },
            {
                name: '电商价格数据',
                path: "/publicData/priceEshop",
            }
        ]
    },
    {
        name: '招商引资数据',
        iconClass: 'resourceManagement',
        path: "/publicData/MerchantsData"
    },
    {
        name: '主产区数据',
        iconClass: 'serverManagement',
        path: "/publicData/mainProducingData"
    },
    {
        name:'对外贸易数据',
        iconClass:'mainData',
        path: "/publicData/tradeOutData",
        child:[
            {
                name: '出口数据',
                path: "/publicData/tradeOutData",
            },
            {
                name: '进口数据',
                path: "/publicData/tradeInData",
            }
        ]
    },
    {
        name:'舆情数据',
        iconClass:'resourceManagement',
        path: "/publicData/dataMaintaining",
        child:[
            {
                name: '数据维护',
                path: "/publicData/dataMaintaining"
            },
            {
                name: '主题管理',
                path: "/publicData/themeManager"
            },
            {
                name: '采集规则',
                path: "/publicData/acquisitionRules"
            },
            {
                name: '数据源管理',
                path: "/publicData/dataManager"
            }
        ]
    },
]
//  物联网数据
let thingsInternetDataRoute = [
    {
        name: '农丰果蔬种植专业合作社',
        iconClass: 'dataMaintaining',
        path: "/thingsInternet/company1",
        child:[
            {
                name: '走势图',
                path: "/thingsInternet/trendChart",
            },
            {
                name: '监控数据',
                path: "/thingsInternet/monitorData",
            }
        ]
    },
    {
        name: '临江村王道相大户芒果种植合作社',
        iconClass: 'resourceManagement',
        path: "/thingsInternet/company2"
    },
    {
        name: '永学芒果种植专业合作社',
        iconClass: 'themeManager',
        path: "/thingsInternet/company3"
    },
    {
        name: '农欣芒果种植农民专业合作社',
        iconClass: 'sellData',
        path: "/thingsInternet/company4"
    },
    {
        name: '绿康源农业开发有限公司',
        iconClass: 'resourceManagement',
        path: "/thingsInternet/company5"
    },
    {
        name: '金芒果生态开发有限公司',
        iconClass: 'acquisitionRules',
        path: "/thingsInternet/company6"
    }
]
//  舆情数据
/* let publicDataRoute = [
    {
        name: '数据维护',
        iconClass: 'dataMaintaining',
        path: "/publicData/dataMaintaining"
    },
    {
        name: '主题管理',
        iconClass: 'themeManager',
        path: "/publicData/themeManager"
    },
    {
        name: '采集规则',
        iconClass: 'acquisitionRules',
        path: "/publicData/acquisitionRules"
    },
    {
        name: '数据源管理',
        iconClass: 'dataManager',
        path: "/publicData/dataManager"
    }
] */
// function clickHandleFn(e) {
//     // e.preventDefault();
//     // e.stopPropagation();
//     e.target.parentNode.parentNode.childNodes.forEach(function(v,i){
//         v.setAttribute('class','')
//     })
//     e.target.parentNode.setAttribute('class', 'active')
// }
function activeFn(params, pathName) {
    let isTrue = false;
    if (!params.child) {
        return (pathName.indexOf(params.path) !== -1) ?'active':'';
    }else{
        params.child.forEach((v,i) => {
            if (pathName.indexOf(v.path) !== -1) {
                isTrue = true;
            }
        })
        return isTrue?'active':'';
    }
}
function createList(children, i) {
    if (!children) return;
    return <ul>
        {
            children.map((v, index) => {
                return (
                    <li key={v.name}><NavLink to={v.path}>{v.name}</NavLink></li>
                )
            })
        }
    </ul>

}

//  直报数据
export const LeftBarForData = withRouter(({history,location,match})=>(
    <ul className="leftBar fl">
        {
            forDataRoute.map((v,i)=>{
                    return (
                        <li className={activeFn(v, location.pathname)} key={v.name}>
                            <NavLink className={v.iconClass} to={v.child?v.child[0].path:v.path}>{v.name}</NavLink>
                            {
                                createList(v.child, i)
                            }
                        </li>
                    )
            })
    }
    </ul>
))
//  指标数据
export const LeftBarIndexReport = withRouter(({history,location})=>(
    <ul className="leftBar fl">
        {
            indexReportRoute.map((v,i)=>{
                    return (
                        <li className={activeFn(v, location.pathname)} key={v.name}>
                            <NavLink className={v.iconClass} to={v.path}>{v.name}</NavLink>
                            {
                                createList(v.child, i)
                            }
                        </li>
                    )
            })
    }
    </ul>
))
//  视图数据
export const LeftBarViewData = withRouter(({history,location})=>(
    <ul className="leftBar fl">
        {
            viewDataRoute.map((v,i)=>{
                    return (
                        <li className={activeFn(v, location.pathname)} key={v.name}>
                            <NavLink className={v.iconClass} to={v.path}>{v.name}</NavLink>
                            {
                                createList(v.child, i)
                            }
                        </li>
                    )
            })
    }
    </ul>
))
//  数据资源
export const LeftBarDataResource = withRouter(({history,location})=>(
    <ul className="leftBar fl">
        {
            dataResourceRoute.map((v,i)=>{
                    return (
                        <li className={activeFn(v, location.pathname)} key={v.name}>
                            <NavLink className={v.iconClass} to={v.path}>{v.name}</NavLink>
                            {
                                createList(v.child, i)
                            }
                        </li>
                    )
            })
    }
    </ul>
))
//  网络数据
export const LeftBarInternetData = withRouter(({history,location})=>(
    <ul className="leftBar fl">
        {
            internetDataRoute.map((v,i)=>{
                //console.log(v)
                    return (
                        <li className={activeFn(v, location.pathname)} key={v.name}>
                            <NavLink className={v.iconClass} to={v.path}>{v.name}</NavLink>
                            {
                                createList(v.child, i)
                            }
                        </li>
                    )
            })
    }
    </ul>
))
//  物联网数据
export const LeftBarThingsInternetData = withRouter(({history,location})=>(
    <ul className="leftBar fl">
        {
            thingsInternetDataRoute.map((v,i)=>{
                    return (
                        <li className={activeFn(v, location.pathname)} key={v.name} title={v.name}>
                            <NavLink className={v.iconClass} to={v.path}>{v.name}</NavLink>
                            {
                                createList(v.child, i)
                            }
                        </li>
                    )
            })
    }
    </ul>
))



