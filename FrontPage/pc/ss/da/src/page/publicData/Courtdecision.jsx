import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, Tabs, Button, message, Icon, Tooltip, Table, Popconfirm } from 'antd';
import * as api from "../api/api-publicData";
import moment from 'moment';
import { NavLink } from "react-router-dom";
// import Options from "../component/area";
function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}
const Option = Select.Option;
const TabPane = Tabs.TabPane;
export default class QyLink extends Component {
    constructor(props) {
        super(props)
        this.state = {
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            columns: [
                {
                    title: '序号',
                    dataIndex: '0',
                },
                {
                    title: '法院',
                    dataIndex: '1',
                },
                {
                    title: '案件名称',
                    dataIndex: '2',
                },
                {
                    title: '判决时间',
                    dataIndex: '3',
                }
            ]
        }
    }
    //  删除单个数据
    confirmDel=(e)=>{
        // console.log(e);
        api.gdxxDel.send({id:e}).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                message.success('删除成功！');
                //  删除成功刷新列表
                this.initAltitudeList(this.state.current)
            }
        })
    }
    // 股东信息
    initGd=(id)=>{
        let params = {
                "entityRelated":{
                    "enterpriseId" : id//主键
                },
                "page" : {
                    "pageIndex" : 1,
                    "pageSize" : 10
                },
                "orderList" : [
                   {
                       "columnName" : "created_time",
                       "isASC" : false
                   }
                ]  
        }
        api.fyList.send(params).then(res=>{
            console.log(res.data)
            let tableData=[];
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:i,
                        0:i+1,
                        1:v.executionCourt,
                        2:v.referenceNumber,
                        3:v.year
                    }
                })
                this.setState({
                    tableData:tableData,
                    total:res.totalCount
                 })
            }else{
                this.setState({
                    tableData:[],
                    total:0
                 })
            }
        })
    }
    componentDidMount(){
        let id = this.props.id;
        this.initGd(id.split('-')[0])
    }
    render() {
        const {
            current,
            total,
            tableData
        } = this.state
        let pagination = {
            total: total,
            current: current,
            pageSize: 10,
            hideOnSinglePage:true,
            onChange:(current, pageSize) => {
                //  跳转到点击的页面
                this.initAltitudeList(current)
                //  当前页current变化
                this.setState({
                    current:current
                })
            },
        }
        return (
            <Table
                size={"small"}
                locale={{emptyText:"暂无数据！"}}
                pagination={pagination}
                columns={this.state.columns}
                dataSource={tableData} />
        )
    }
}