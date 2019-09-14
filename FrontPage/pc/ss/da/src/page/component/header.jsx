import React, { Component } from "react";
import { NavLink } from "react-router-dom";
// import * as api from "../api/api-home";
import "./common.scss";
const url = [
    {
        path:'/home',
        name:'首页'
    },
    {
        path:'/forData',
        name:'直报数据'
    },
    {
        path:'/indexReport',
        name:'产业数据'
    },
    {
        path:'/viewData',
        name:'检测数据'
    },
    {
        path:'/publicData',
        name:'网络数据'
    },
    {
        path:'/thingsInternet',
        name:'物联网数据'
    },
    // {
    //     path:'/dataResource',
    //     name:'数据资源'
    // }
]
export default class Header extends Component {
    constructor(props){
        super(props)
        this.state = {
            url:[]
        }
    }
    componentDidMount(){
        // api.menu.send().then(res=>{
        // //   console.log(res)
        //   let parentRoutes = [];
        //   if(res.isSuccess){
        //     res.data[0].child.forEach((v,i)=>{
        //         parentRoutes[i] = {
        //             path:v.path,
        //             name:v.name
        //         }
        //     })
        //     // console.log(parentRoutes)
        //     this.setState({
        //         url:parentRoutes || url
        //     })
        //   }
        // })
    }
    //  退出清空token
    delToken=()=>{
        sessionStorage.clear();
        localStorage.removeItem('token')
    }
    render() {
        return (
            <div className="header">
                <div className="header-container">
                    <div className="admin clearfix">
                        <div className="user fl">
                            <span className="iconUserName"></span>
                            <span>你好，{localStorage.username || "请登录！"}</span>
                        </div>
                        <div onClick={this.delToken} className="loginOut fl">
                            <NavLink to="/login">
                                <span className="iconLoginOut"></span>退出
                            </NavLink>
                        </div>
                    </div>
                    <ul className="clearfix">
                    {
                        url.map((v,i)=>{
                            return(
                                <li key={i}>
                                    <NavLink exact={i===0} to={v.path}>{v.name}</NavLink>
                                </li>
                            )
                        })
                    }
                        {/* <li>
                            <NavLink exact to="/">首页</NavLink>
                        </li>
                        <li>
                            <NavLink to="/forData">直报数据</NavLink>
                        </li>
                        <li>
                            <NavLink to="/indexReport">指标上报</NavLink>
                        </li>
                        <li>
                            <NavLink to="/viewData">视图数据</NavLink>
                        </li>
                        <li>
                            <NavLink to="/publicData">舆情数据</NavLink>
                        </li>
                        <li>
                            <NavLink to="/dataResource">数据资源</NavLink>
                        </li> */}
                    </ul>
                </div>
            </div>
        )
    }
}