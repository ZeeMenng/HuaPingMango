import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import '../../static/scss/forData/mainData.scss';
import '../../static/scss/thingsInternet/thingsInternet.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Table, Button, Icon, Tooltip, Popconfirm, message, Cascader, Upload } from 'antd';
import * as api from "../api/api-thingsInternet";
import moment from 'moment';
// import Options from "../component/area";
const Option = Select.Option;
const { RangePicker } = DatePicker;

function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}

export default class ProcessValueData extends Component {
    constructor(props) {
        super(props)
        this.state = {
            loading: false,
            isAdd:props.match.params.id,
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            startTime:'',                               //  采集开始时间默认值
            endTime:'', 
            selectedRowKeys: [], // Check here to configure the default column
            columns: [
                {
                    title: '空气温度(℃)',
                    dataIndex: '0',
                },
                {
                    title: '空气湿度(%)',
                    dataIndex: '1',
                },
                {
                    title: '土壤温度(℃)',
                    dataIndex: '2',
                },
                {
                    title: '土壤湿度(%)',
                    dataIndex: '3',
                },
                {
                    title: '风向(度)',
                    dataIndex: '4',
                },
                {
                    title: '风速(m/s)',
                    dataIndex: '5',
                },
                {
                    title: '光照强度(lux)',
                    dataIndex: '6',
                },
                {
                    title: '降雨量(mm)',
                    dataIndex: '7',
                },
                {
                    title: '日累积雨量(mm)',
                    dataIndex: '8',
                },
                {
                    title: '监控时间',
                    dataIndex: '9',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY-MM-DD')}</span>;
                        }
                    }
                },

            ]
        }
    }

    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
    //  列表方法
    initAltitudeList=(current)=>{
        const {
            isAdd,
            startTime,
            endTime
        } = this.state
        let params={
            "hid":isAdd,//设备主机编码
            "beginTime":startTime,//检查时间开始
            "endTime":endTime,//检查时间结束
            "limit":"10",
            "page":current || 1
        }
        api.jkData.send(params).then(res=>{
            console.log(res)
            let tableData=[];
            if(res.code =='1'){
                res.page.list.forEach((v,i)=>{
                    tableData[i] = {
                        key:i,
                        0:v.airTemperature,
                        1:v.airHumidity,
                        2:v.soilTemperature,
                        3:v.soilHumidity,
                        4:v.windDirection,
                        5:v.windSpeed,
                        6:v.illumination,
                        7:v.rainFall,
                        8:v.dailyRainfall,
                        9:v.time
                    }
                })
                this.setState({
                    tableData:tableData,
                    total:res.page.totalCount
                 })
            }else{
                this.setState({
                    tableData:[],
                    total:0
                 })
            }
        })
    }
    //  采集时间选择
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            startTime:dateString[0],
            endTime:dateString[1]
        })
    }
    componentDidMount(){
        //  未登录跳转到登录页
        if(!localStorage.token){
            this.props.history.push("/login")
            return
        }
        //  列表方法
        this.initAltitudeList(this.state.current)
    }
    render() {
        const {
            current,
            total,
            selectedRowKeys,
            tableData,
            startTime,
            endTime
        } = this.state;
        const rowSelection = {
            selectedRowKeys,
            onChange: this.onSelectChange,
            onSelect: (record, selected, selectedRows) => {
                console.log(record, ` selected :${selected}`, `selectedRows:${selectedRows}`);
            },
        };
        // const hasSelected = selectedRowKeys.length > 0;
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
            <div className="forData monitorData">
                <ul className="form-search clearfix">
                    <li className="item-time">
                        <label htmlFor="">采集时间：</label>
                        <RangePicker locale={locale} onChange={this.timeSelect} style={{ width: '73%' }} />
                    </li>
                    <li>
                        <Button onClick={this.initAltitudeList.bind(undefined,1)} type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">搜索</Button>
                    </li>
                </ul>
                <Table
                    size={"small"}
                    locale={{emptyText:"暂无数据！"}}
                    pagination={pagination}
                    rowSelection={rowSelection}
                    columns={this.state.columns}
                    dataSource={tableData} />
            </div>
        )
    }
}