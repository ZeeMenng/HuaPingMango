import React from "react";
import { LeftBarDataResource } from '../../component/leftBar';
import { Route, Redirect, Switch } from 'react-router-dom';
import ResourceManagementPage from '../resourceManagement'; //  资源方管理
/*import ResourceManagementAdd from '../resourceManagementAdd'; //  资源方管理-新增*/
import ServerManagementPage from '../serverManagement';     //  服务器管理
/*import ServerManagementAdd from '../serverManagementAdd';*/     //  服务器管理-新增
var route = [
    {
        path: '/resourceManagement',
        child: [
            {
                path: '/resourceManagementPage',
                component: ResourceManagementPage,
            },
            /*{
                path: '/resourceManagementAdd',
                component: ResourceManagementAdd,
            }*/
        ]
    },
    {
        path: '/serverManagement',
        child: [
            {
                path: '/serverManagementPage',
                component: ServerManagementPage,
            },
            /*{
                path: '/serverManagementAdd',
                component: ServerManagementAdd,
            }*/
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
const DataResource = ({ match }) => {

    return (
        <div>
            <div className="mainBox clearfix">
                <LeftBarDataResource></LeftBarDataResource>
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
export default DataResource;