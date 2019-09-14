import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import '../../static/scss/forData/mainData.scss';
import '../../static/scss/thingsInternet/thingsInternet.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Table, Button, Icon, Tooltip, Popconfirm, message, Cascader, Upload } from 'antd';
import * as api from "../api/api-thingsInternet";
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
            hid:'',
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            deviceName: '',
            selectedRowKeys: [], // Check here to configure the default column
            id: "",
            columns: [
                {
                    title: '设备主机编号',
                    dataIndex: '0',
                },
                {
                    title: '设备名称',
                    dataIndex: '1',
                },
                {
                    title: '基地',
                    dataIndex: '2',
                },
                {
                    title: '芒果品种',
                    dataIndex: '3',
                },
                {
                    title: '操作',
                    dataIndex: '10',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom">
                                <NavLink to={`/thingsInternet/company1/trendChart/${text}`}  style={{'marginRight':'10px','color':'#6b9cd4'}}>
                                    走势图
                                </NavLink>
                            </Tooltip>
                             <Tooltip placement="bottom">
                                <NavLink to={`/thingsInternet/company1/monitorData/${text}`} style={{'color':'#6b9cd4'}}>
                                    监控数据
                                </NavLink>
                            </Tooltip>
                        </span>
                    )
                }
            ]
        }
        // console.log(props)
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
    //  监听input
    getValue=(e)=>{
        let type = e.target.getAttribute('data-type');
        let value = e.target.value;
        this.setState({
            [type]:value
        },()=>{
            // console.log(this.state[type])
        })
    }
    initPage=(current)=>{
        api.getJd.send().then(res=>{
            console.log(res)
            let data = res.data;
            if(res.isSuccess){
                for (let i in data) {
                    console.log(data[i].cdName)
                    if (data[i].cdName == '农丰果蔬种植专业合作社基地') {
                        this.setState({
                            id: data[i].belongingEnterpriseId
                        })
                    }
                }
                this.initAltitudeList(this.state.current)
            }
        })
    }
    //  列表方法
    initAltitudeList=(current)=>{
        console.log(this.state.id)
        const {
            id,
            deviceName,
            hid
        } = this.state
        console.log(id)
        let params={
            "deviceName" : deviceName,//设备名称
            "hid" : hid,//设备主机编码
            "limit" : "10",//分页
            "page" :current || 1,
            "orderByField" : "device_name",
            "enterpriseId" : id,//公司id
            "orderByRule" : "asc",
            "sysType" : "sjcj"
        }
        api.getList.send(params).then(res=>{
            let data = res.page.list
            let tableData=[];
            console.log(res)
            if(res.code == "1"){
                data.forEach((v,i)=>{
                    tableData[i] = {
                        key:v.id,
                        0:v.hid,
                        1:v.deviceName,
                        2:v.bascName,
                        3:v.mangoType,
                        10:v.hid,
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
    componentDidMount(){
        //  列表方法
        this.initPage()
    }
    render() {
        const {
            current,
            total,
            selectedRowKeys,
            deviceName,
            hid,
            tableData,
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
            <div className="forData thingsInternet">
                <ul className="form-search clearfix">
                    <li>
                        <label htmlFor="">设备主机编号：</label>
                        <Input data-type="hid" onChange={this.getValue} style={{ width: '65%' }} defaultValue={hid} />
                    </li>
                    <li>
                        <label htmlFor="">设备名称：</label>
                        <Input style={{ width: '65%' }} data-type="deviceName" onChange={this.getValue} defaultValue={deviceName} />
                    </li>
                    <li>
                        <Button onClick={this.initAltitudeList.bind(undefined,1)} type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">查询</Button>
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