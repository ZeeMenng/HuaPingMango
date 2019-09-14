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
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            importCountryCode: 0,                     //  进口国家code
            importCountryText: "",                //  进口国家名称文本
            exportCountryData:[{text:'全部',code:0}],    //  出口国家
            importAmountUnitE: "",
            importAmountUnitS: "",
            yearData: [],
            wholePrice1:'',
            wholePrice2:'',
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
                    title: '进口国家',
                    dataIndex: '1',
                },
                {
                    title: '进口金额(万美元)',
                    dataIndex: '2',
                },
                {
                    title: '进口量(万吨)',
                    dataIndex: '3',
                },
                {
                    title: '进口价格(美元/公斤)',
                    dataIndex: '4',
                },
                {
                    title: '审核状态',
                    dataIndex: '5',
                },
                {
                    title: '操作',
                    dataIndex: '6',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="审核">
                                <NavLink to={`/publicData/tradeInData/TradeInAudit/${text}-${record.key}`}>
                                    <Icon
                                        type="credit-card"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                        />
                                </NavLink>
                            </Tooltip>
                             <Tooltip placement="bottom" title="编辑">
                                <NavLink to={`/publicData/tradeInData/TradeInAdd/${text}-${record.key}`}>
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
        api.TradeInDel.send({id:e}).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                message.success('删除成功！');
                //  删除成功刷新列表
                this.initAltitudeList(this.state.current)
            }
        })
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
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
    // 国家下拉
    initareaUnit() {
        api.getCountry.send().then(res=>{
            console.log(res)
            let areaUnitArr = []
            if(res.isSuccess){
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
    //  列表方法
    initAltitudeList=(current)=>{
        const {
            importCountryCode,
            importCountryText,
            importAmountUnitE,
            importAmountUnitS,
            startDate
        } = this.state
        let params={
            "jsonData": {
                "entityRelated" : {
                    "year" : startDate,//年度
                    "dataTimeTypeCode" : "1",//年度数据
                    "regionId" : "031000000",//年度数据
                    "flag" : "2",//进口标识
                    "importCountryText" : importCountryText,//进口国家
                    "importAmountUnitE" : importAmountUnitE,//进口量下線
                    "importAmountUnitS" : importAmountUnitS//进口量上線
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
        api.TradeInList.send(params).then(res=>{
            console.log(res)
            let tableData=[];
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:v.commonFieldId,
                        0:v.startTime,
                        1:v.importCountryText,
                        2:v.importVolume,
                        3:v.importAmount,
                        4:v.importPrice,
                        5:v.auditStatusText,
                        6:v.id
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
    componentDidMount(){
        //  未登录跳转到登录页
        if(!localStorage.token){
            this.props.history.push("/login")
            return
        }
        //  列表方法
        this.initAltitudeList(this.state.current)
        //  地理区域数据请求
        this.initareaUnit()
        this.initYear()
    }
    render() {
        const {
            current,
            total,
            selectedRowKeys,
            wholePrice1,
            wholePrice2,
            tableData,
            importCountryCode,
            importCountryText,
            exportCountryData,
            yearData
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
                    <li className="position">
                        <label htmlFor="">进口国家：</label>
                        <Select defaultValue={exportCountryData[0].code} value={importCountryCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            exportCountryData.map((v,i)=>{
                                return(
                                    <Option codenum={'importCountryCode'} typename={'importCountryText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">进口量：</label>
                        <Input style={{ width: '25%' }} data-type="wholePrice1" onChange={this.getValue} defaultValue={wholePrice1} />
                        <span style={{ width: '5%', color:'#666',display:'inline-block',margin:'0 5px' }}>－</span>
                        <Input style={{ width: '25%' }} data-type="wholePrice2" onChange={this.getValue} defaultValue={wholePrice2} />
                    </li>
                    <li>
                        <Button onClick={this.initAltitudeList.bind(undefined,1)} type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">检索</Button>
                    </li>
                </ul>
                <div className="btn-type">
                    <NavLink to="/publicData/tradeInData/TradeInAdd/1"><Button icon="plus">增加</Button></NavLink>
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