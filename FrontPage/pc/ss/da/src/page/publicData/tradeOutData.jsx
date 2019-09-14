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
            timeDimensionData:[{text:'全部',code:0}],   //  时间维度下拉列表
            originChannelData:[{text:'全部',code:0}],   //  来源渠道下拉列表
            dataTimeTypeCode:0,                         //  时间维度默认code
            sourceChannelTypeCode:0,                    //  来源渠道默认code
            startDate:'',                               //  数据开始日期默认值
            endDate:'',                                 //  数据结束日期默认值
            startTime:'',                               //  采集开始时间默认值
            endTime:'',                                 //  采集结束时间默认值
            dataSources:'',                             //  数据来源默认值
            name:'',                                    //  名称
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            exportCountryCode: '',
            exportCountryData:[{text:'全部',code:0}],    //  出口国家
            yearData: [],
            exportCountryText: '',
            exportAmountUnitS: '',
            exportAmountUnitE: '',
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
                    title: '年度',
                    dataIndex: '0',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY')}</span>;
                        }
                    }
                },
                {
                    title: '出口国家',
                    dataIndex: '2',
                },
                {
                    title: '出口金额(万美元)',
                    dataIndex: '3',
                },
                {
                    title: '出口量(万吨)',
                    dataIndex: '4',
                },
                {
                    title: '出口价格(美元/公斤)',
                    dataIndex: '1',
                },
                {
                    title: '审核状态',
                    dataIndex: '5',
                },
                {
                    title: '操作',
                    dataIndex: '10',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="审核">
                                <NavLink to={`/publicData/tradeOutData/TradeOutAudit/${text}-${record.key}`}>
                                    <Icon
                                        type="credit-card"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                        />
                                </NavLink>
                            </Tooltip>
                             <Tooltip placement="bottom" title="编辑">
                                <NavLink to={`/publicData/tradeOutData/TradeOutAdd/${text}-${record.key}`}>
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
        api.TradeOutRemove.send({id:e}).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                message.success('删除成功！');
                //  删除成功刷新列表
                this.initAltitudeList(this.state.current)
            }
        })
    }
    handleSelect = (e,option) =>{
        // console.log(e,option)
        let type = option.props.typename;
        let code = option.props.codenum;
        let text = option.props.children;
        this.setState({
            [type]:text,
            [code]:e
        },()=>{
            // console.log(this.state[type],this.state[code])
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
    initYear (){
        var date=new Date;
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
    //  来源渠道下拉方法
    initOriginChannel(){
        api.originChannel.send().then(res=>{
            let originChannelNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    originChannelNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    originChannelData:this.state.originChannelData.concat(originChannelNew)
                },()=>{
                    // console.log(this.state.originChannelData)
                })
            }
        })
    }
    // 国家下拉
    initareaUnit() {
        api.getCountry.send().then(res=>{
            console.log(res)
            let areaUnitArr = []
            if(res.isSuccess){
                console.log(res.data)
                res.data.forEach((v,i)=>{
                    areaUnitArr[i] = {
                        text:v.chineseName,
                        code:v.isoCode
                    }
                })
                this.setState({
                    exportCountryData:areaUnitArr
                },()=>{
                    // console.log(this.state.processUnitData)
                })
            }
        })
    }
    //  来源渠道
    areaHandleChange = (value)=> {
        // console.log(`selected ${value}`);
        this.setState({
            sourceChannelTypeCode:value
        })
    }
    //  时间维度下拉方法
    initTimeSelect(){
        api.timeDimension.send().then(res=>{
            let timeDimensionNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    timeDimensionNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    timeDimensionData:this.state.timeDimensionData.concat(timeDimensionNew)
                },()=>{
                    // console.log(this.state.timeDimensionData)
                })
            }
        })
    }
    //  时间维度
    timeHandleChange = (value)=> {
        // console.log(`selected ${value}`);
        this.setState({
            dataTimeTypeCode:value
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
            exportCountryText,
            exportAmountUnitS,
            exportAmountUnitE,
            startDate
        } = this.state
        let params={
            "jsonData": {
                "entityRelated" : {
                    "year" : startDate,//年度
                    "dataTimeTypeCode" : "1",//年度数据
                    "regionId" : "",//年度数据
                    "flag" : "1",//出口标识
                    "exportCountryText" : exportCountryText,//出口国家
                    "exportAmountUnitE" : exportAmountUnitE,//出口量下線
                    "exportAmountUnitS" : exportAmountUnitS//出口量上線
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
        api.TradeOutList.send(params).then(res=>{
            console.log(res)
            let tableData=[];
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:v.commonFieldId,
                        0:v.startTime,
                        1:v.exportPrice,
                        2:v.exportCountryText,
                        3:v.exportVolume,
                        4:v.exportAmount,
                        5:v.auditStatusText,
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
        //  地理区域数据请求
        this.areaDataGet()
        //  初始化时间维度
        this.initTimeSelect()
        //  初始化来源渠道
        this.initOriginChannel()
        this.initareaUnit()
        this.initYear()
    }
    render() {
        const {
            current,
            total,
            selectedRowKeys,
            dataSources,
            tableData,
            exportCountryText,
            exportCountryCode,
            exportAmountUnitS,
            exportAmountUnitE,
            exportCountryData,
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
                        <label htmlFor="">出口国家：</label>
                        <Select defaultValue={exportCountryData[0].code} value={exportCountryCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            exportCountryData.map((v,i)=>{
                                return(
                                    <Option codenum={'exportCountryCode'} typename={'exportCountryText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">数据来源：</label>
                        <Input style={{ width: '65%' }} data-type="dataSources" onChange={this.getValue} defaultValue={dataSources} />
                    </li>
                    <li>
                        <label htmlFor="">出口量：</label>
                        <Input style={{ width: '25%' }} data-type="exportAmountUnitS" onChange={this.getValue} defaultValue={exportAmountUnitS} />
                        <span style={{ width: '5%', color:'#666',display:'inline-block',margin:'0 5px' }}>－</span>
                        <Input style={{ width: '25%' }} data-type="exportAmountUnitE" onChange={this.getValue} defaultValue={exportAmountUnitE} />
                    </li>
                    <li>
                        <Button onClick={this.initAltitudeList.bind(undefined,1)} type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">检索</Button>
                    </li>
                </ul>
                <div className="btn-type">
                    <NavLink to="/publicData/tradeOutData/TradeOutAdd/1"><Button icon="plus">增加</Button></NavLink>
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