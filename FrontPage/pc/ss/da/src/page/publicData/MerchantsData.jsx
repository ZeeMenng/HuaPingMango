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
            enterpriseTypeData: [{ text: '全部', code: '' }],//  企业类型下拉列表
            enterpriseTypeCode: '',//企业类型编码
            enterpriseTypeText: "全部",//企业类型名称
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            enterpriseName: '',
            startTime:'',                               //  采集开始时间默认值
            endTime:'',                                 //  采集结束时间默认值
            selectedRowKeys: [], // Check here to configure the default column
            propsAjax: {
                name: 'file',
                action: api.BASEURL_01 + '/extend/swagger/da/daMarketPrice/importExcel',
                headers: {
                    'Authorization': sessionStorage.getItem("token")?('Bearer '+sessionStorage.getItem("token")): ""
                },
                showUploadList: false,
                onChange: (info) => {
                    // console.log(info)
                    if (info.file.status !== 'uploading') {
                        // console.log(info.file, info.fileList);
                    }
                    if (info.file.status === 'done') {
                        let isSuccess = info.file.response.isSuccess;
                        if (isSuccess) {
                            message.success("数据批量上传成功！");
                            //  刷新列表
                            this.initAltitudeList(this.state.current)
                        } else {
                            message.error("上传失败！");
                        }
                    } else if (info.file.status === 'error') {
                        console.log(`${info.file.name} file upload failed.`);
                    }
                },
            },
            columns: [
                {
                    title: '企业名称',
                    dataIndex: '0',
                },
                {
                    title: '统一社会信用码',
                    dataIndex: '1',
                },
                {
                    title: '法定代表人',
                    dataIndex: '2',
                },
                {
                    title: '成立日期',
                    dataIndex: '3',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY-MM-DD')}</span>;
                        }
                    }
                },
                {
                    title: '所在区域',
                    dataIndex: '4',
                },
                {
                    title: '产业链环节',
                    dataIndex: '5',
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
                                <NavLink to={`/publicData/MerchantsData/MerchantsAudit/${text}-${record.key}`}>
                                    <Icon
                                        type="credit-card"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                        />
                                </NavLink>
                            </Tooltip>
                             <Tooltip placement="bottom" title="查看">
                                <NavLink to={`/publicData/MerchantsData/MerchantsAdd/${text}-${record.key}`}>
                                    <Icon
                                        type="eye"
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
        api.daMarketPriceDel.send({id:e}).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                message.success('删除成功！');
                //  删除成功刷新列表
                this.initAltitudeList(this.state.current)
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
    //企业类型下拉
    initEnterpriseType() {
        api.enterpriseType.send().then(res => {
            // console.log(res)
            let enterpriseTypeNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    enterpriseTypeNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    enterpriseTypeData: this.state.enterpriseTypeData.concat(enterpriseTypeNew)
                }, () => {
                    // console.log(this.state.regionLevelData)
                })
            }
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
    //  下拉获取值通用方法
    handleSelect = (e,option) =>{
        // console.log(e,option)
        let type = option.props.typename;
        let code = option.props.codenum;
        let text = option.props.children;
        if(code === "areaLatitudeTypeCode"){
            this.areaDataGet()
        }
        this.setState({
            [type]:text,
            [code]:e
        },()=>{
            // console.log(this.state[type],this.state[code])
        })
    }
    //  数据日期选择
    dataSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            startTime:dateString[0],
            endTime:dateString[1]
        })
    }
    //  列表方法
    initAltitudeList=(current)=>{
        const {
            startTime,
            endTime,
            enterpriseTypeCode,
            enterpriseName
        } = this.state
        let params={
            "jsonData": {
                "entityRelated" : {
                    "foundDateS" : startTime,//成立日期
                    "foundDateE" : endTime,//成立日期
                    "enterpriseName" : enterpriseName,//企业名称
                    "enterpriseTypeCode" : enterpriseTypeCode,//产业链环节
                    "regionCode":""//区域
                },    
                "page":{
                    "pageIndex":current || 1,
                    "pageSize":10
                }
            }
        }
        api.MerchantsList.send(params).then(res=>{
            console.log(res)
            let tableData=[];
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:v.commonFieldId,
                        0:v.enterpriseName,
                        1:v.societyCreditId,
                        2:v.corporationName,
                        3:v.foundDate,
                        4:v.enterpriseAddress,
                        5:v.enterpriseTypeText,
                        6:v.auditStatusText,
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
    componentDidMount(){
        //  未登录跳转到登录页
        if(!localStorage.token){
            this.props.history.push("/login")
            return
        }
        //  列表方法
        this.initAltitudeList(this.state.current)
        this.initEnterpriseType()
    }
    render() {
        const {
            current,
            total,
            selectedRowKeys,
            tableData,
            delList,
            enterpriseTypeData,
            enterpriseTypeCode,
            enterpriseName
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
                        <label htmlFor="">企业名称：</label>
                        <Input data-type="enterpriseName" onChange={this.getValue} style={{ width: '65%' }} defaultValue={enterpriseName} />
                    </li>
                    <li className="item-time">
                        <label htmlFor="">成立日期：</label>
                        <RangePicker locale={locale}  onChange={this.dataSelect} style={{ width: '73%' }} />
                    </li>
                    <li>
                        <label htmlFor="">产业环节：</label>
                        <Select defaultValue={enterpriseTypeData[0].code} value={enterpriseTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                            {
                                enterpriseTypeData.map((v, i) => {
                                    return (
                                        <Option codenum={'enterpriseTypeCode'} typename={'enterpriseTypeText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>

                    {/* <li>
                        <label htmlFor="">区域：</label>
                        <Cascader
                            locale={locale}
                            placeholder={'请选择'}
                            style={{ width: '65%' }}
                            options={areaData}
                            onChange={this.areaFn}
                            />
                    </li> */}

                    <li>
                        <Button onClick={this.initAltitudeList.bind(undefined,1)} type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">检索</Button>
                    </li>
                </ul>
                <div className="btn-type">
                    <Button onClick={this.delListFn.bind(undefined,delList)} icon="delete" type="danger" >批量删除</Button>
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