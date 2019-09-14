import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Table, Button, Icon, Tooltip, Popconfirm, message, Cascader, Upload } from 'antd';
import * as api from "../api/api-publicData";
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
            areaData:[],                                //  地理区域数据
            delList:[],                                 //  批量删除
            startDate:'',                               //  数据开始日期默认值
            endDate:'',                                 //  数据结束日期默认值
            startTime:'',                               //  采集开始时间默认值
            endTime:'',                                 //  采集结束时间默认值
            name:'',                                    //  名称
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            yearData: [],
            selectedRowKeys: [], // Check here to configure the default column
            columns: [
                {
                    title: '日期',
                    dataIndex: '1',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY')}</span>;
                        }
                    }
                },
                {
                    title: '地区',
                    dataIndex: '2',
                },
                {
                    title: '种植户数（单位：户）',
                    dataIndex: '3',
                },
                {
                    title: '鲜果产量（万吨）',
                    dataIndex: '4',
                },
                {
                    title: '种植面积（万亩）',
                    dataIndex: '5',
                },
                {
                    title: '单产',
                    dataIndex: '7',
                },
                {
                    title: '状态',
                    dataIndex: '6',
                },
                {
                    title: '操作',
                    dataIndex: '10',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="审核">
                                <NavLink to={`/publicData/mainProducingData/MainProducingAduit/${text}-${record.key}`}>
                                    <Icon
                                        type="credit-card"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                        />
                                </NavLink>
                            </Tooltip>
                             <Tooltip placement="bottom" title="编辑">
                                <NavLink to={`/publicData/mainProducingData/MainProducingAdd/${text}-${record.key}`}>
                                    <Icon
                                        type="form"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                                </NavLink>
                            </Tooltip>
                             <Tooltip placement="bottom" title="删除">
                                <Popconfirm title="确定删除吗?" onConfirm={this.confirmDel.bind(undefined,text)} onCancel={cancel} okText="确定" cancelText="取消">
                                    <Icon
                                        type="delete"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                                </Popconfirm>
                            </Tooltip>
                        </span>
                    )
                }
            ]
        }
        // console.log(props)
    }
     //  删除单个数据
     confirmDel=(e)=>{
        // console.log(e);
        api.MainProducingDel.send({id:e}).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                message.success('删除成功！');
                //  删除成功刷新列表
                this.initAltitudeList(this.state.current)
            }
        })
    }
     //  批量删除
     delListFn = (delArr) =>{
        let params ={
            "idList": delArr
        }
        api.daMarketPriceDelList.send(params).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                message.success('批量删除成功！')
                //  删除成功刷新列表
                this.initAltitudeList(this.state.current)
            }
        })
    }
    //  全选获得删除的id
    onSelectChange = (selectedRowKeys,arr) => {
        let delList = [];
        arr.forEach((v,i)=>{
            delList[i] = v[10]
        })
        // console.log(delList)
        this.setState({
            delList:delList
        },()=>{
            // console.log(this.state.delList)
        })
        this.setState({ selectedRowKeys });
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
    //  数据日期选择
    dataSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            startDate:dateString[0],
            endDate:dateString[1]
        })
    }
    //  地理区域数据请求
    areaDataGet(){
        let params = {
            jsonData:{
                "entityRelated":{
                    "level":""
                }
            }
        }
        api.areaData.send(params).then(res=>{
            if(res.isSuccess){
                this.setState({
                    areaData:res.data
                })
            }
        })
    }
    //  地理区域选择
    areaFn=(value)=> {
        this.setState({
            regionId:value[value.length-1]
        })
    }



    //  列表方法
    initAltitudeList=(current)=>{
        const {
            dataTimeTypeCode,
            sourceChannelTypeCode,
            wholePrice1,
            wholePrice2,
            regionId,
            startDate,
            endDate,
            startTime,
            endTime,
            name
        } = this.state
        let params={
            "jsonData": {
                "entityRelated":{
                    "dataTimeTypeCode":dataTimeTypeCode === 0?'':dataTimeTypeCode,
                    "sourceChannelTypeCode":sourceChannelTypeCode===0?'':sourceChannelTypeCode,
                    "wholePrice1":wholePrice1,
                    "wholePrice2":wholePrice2,
                    "regionId":regionId,
                    "startTime":startTime,
                    "endTime":startTime,
                    "name":name,
                    "yearS": startDate,
                    "yearE": endDate
                },
                "page":{
                    "pageIndex":current || 1,
                    "pageSize":10
                },
                "orderList" : [{
                    "columnName" : "B.add_time",
                    "isASC" : false
                }]
            }
        }
        api.MainProducingList.send(params).then(res=>{
            console.log(res)
            let tableData=[];
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:v.commonId,
                        0:v.dataTimeTypeText,
                        1:v.startTime,
                        2:v.regionName,
                        3:v.peasantCount,
                        4:v.productTotal,
                        5:v.growArea,
                        6:v.auditStatusText,
                        7:v.priceTypeText,
                        10:v.id,
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
    initYear (){
        var date=new Date();
        var year=date.getFullYear();
        year = year - 3;
        var newArr = [];
        for (var i=0;i<10;i++){
            year ++;
            newArr.push(year)
            this.state.yearData = newArr;
        }
    }
    //  采集时间选择
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            startDate:date
        })
    }
    componentDidMount(){
        //  未登录跳转到登录页
        if(!localStorage.token){
            this.props.history.push("/login")
            return
        }
        //  地理区域数据请求
        this.areaDataGet()
        //  列表方法
        this.initAltitudeList(this.state.current)
        this.initYear()

    }
    render() {
        const {
            current,
            total,
            selectedRowKeys,
            timeDimensionData,
            originChannelData,
            wholePrice1,
            wholePrice2,
            name,
            tableData,
            delList,
            propsAjax,
            areaData,
            yearData,
            startDate
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
            <div className="forData">
                <ul className="form-search clearfix">
                    <li>
                        <label htmlFor="">年份：</label>
                        <Select placeholder={'请选择'} style={{ width: '65%' }} onChange={this.timeSelect}>
                        {
                            yearData.map((v,i)=>{
                                return(
                                    <Option codenum={'v'} typename={'v'} key={v} value={v}>{v}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">区域：</label>
                        <Cascader
                            locale={locale}
                            placeholder={'请选择'}
                            style={{ width: '65%' }}
                            options={areaData}
                            onChange={this.areaFn}
                            />
                    </li>
                    <li>
                        <label htmlFor="">单产：</label>
                        <Input style={{ width: '25%' }} data-type="wholePrice1" onChange={this.getValue} defaultValue={wholePrice1} />
                        <span style={{ width: '5%', color:'#666',display:'inline-block',margin:'0 5px' }}>－</span>
                        <Input style={{ width: '25%' }} data-type="wholePrice2" onChange={this.getValue} defaultValue={wholePrice2} />
                    </li>

                    <li>
                        <Button onClick={this.initAltitudeList.bind(undefined,1)} type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">查询</Button>
                    </li>
                </ul>
                <div className="btn-type">
                    <NavLink to="/publicData/mainProducingData/MainProducingAdd/1"><Button icon="plus">新增</Button></NavLink>
                </div>
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