import React, { Component } from "react";
import * as api from "../api/api-forData";
import { NavLink } from "react-router-dom";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker,Cascader, Input, Table, Button, Upload, Icon, Tooltip, Popconfirm, message } from 'antd';
import moment from 'moment';
const Option = Select.Option;
const { RangePicker } = DatePicker;
function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}
export default class EnterpriseMess extends Component {
    constructor(props) {
        super(props)
        this.state = {
            selectedRowKeys: [], // Check here to configure the default column
            loading: false,
            areaData:[],                                //  地理区域数据
            current: 1,  //当前页
            total: 0, //总条数
            delList: [],
            timeDimensionData: [{ text: '全部', code: 0 }],   //  时间维度下拉列表
            originChannelData: [{ text: '全部', code: 0 }],   //  来源渠道下拉列表
            dataTimeTypeCode: 0,                         //  时间维度默认code
            sourceChannelTypeCode: 0,                    //  来源渠道默认code
            dataSources: '',                             //  数据来源默认值
            enterpriseName: '',                          //企业名称
            startDate: '',                               //  数据开始日期默认值
            endDate: '',                                 //  数据结束日期默认值
            startTime: '',                               //  采集开始时间默认值
            endTime: '',                                 //  采集结束时间默认值
            tableData: [],                               //  列表数据  默认0条
            propsAjax: {
                name: 'file',
                action: api.BASEURL_01 + '/extend/swagger/da/daPeasantInfo/importExcel',
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
                // {
                //     title: '采集维度',
                //     dataIndex: '0',
                // },
                {
                    title: '采集日期',
                    dataIndex: '4',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY-MM-DD')}</span>;
                        }
                    }
                },
                // {
                //     title: '区域维度',
                //     dataIndex: '2',
                // },
                {
                    title: '地理区域',
                    dataIndex: '3',
                },
                {
                    title: '数据日期',
                    dataIndex: '1',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY-MM-DD')}</span>;
                        }
                    }
                },
                {
                    title: '统一社会信用代码',
                    dataIndex: '5',
                },
                {
                    title: '企业姓名',
                    dataIndex: '6',
                },
                // {
                //     title: '数据来源',
                //     dataIndex: '7',
                // },
                {
                    title: '来源渠道',
                    dataIndex: '8',
                },
                {
                    title: '审核状态',
                    dataIndex: '9',
                },
                {
                    title: '操作',
                    dataIndex: '10',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="审核">
                                <NavLink to={`/forData/enterpriseMess/enterpriseMessAduit/${text}-${record.key}`}>
                                    <Icon
                                        type="credit-card"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                                </NavLink>
                            </Tooltip>
                            <Tooltip placement="bottom" title="编辑">
                                <NavLink to={`/forData/enterpriseMess/enterpriseMessAdd/${text}-${record.key}`}>
                                    <Icon
                                        type="form"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                                </NavLink>
                            </Tooltip>
                            <Tooltip placement="bottom" title="删除">
                                <Popconfirm title="确定删除吗?" onConfirm={this.confirmDel.bind(undefined, text)} onCancel={cancel} okText="确定" cancelText="取消">
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

    //  批量删除
    delListFn = (delArr) => {
        let params = {
            "idList": delArr
        }
        api.enterpriseMessDelList.send(params).then(res => {
            console.log(res)
            if (res.isSuccess) {
                message.success('批量删除成功！')
                //  删除成功刷新列表
                this.getListFn(this.state.current)
            }
        })
    }
    //  删除单个数据
    confirmDel = (e) => {
        // console.log(e);
        api.enterpriseMessDel.send({ id: e }).then(res => {
            // console.log(res)
            if (res.isSuccess) {
                message.success('删除成功！');
                //  删除成功刷新列表
                this.getListFn(this.state.current)
            }
        })
    }
    //  全选获得删除的id
    onSelectChange = (selectedRowKeys, arr) => {
        console.log(selectedRowKeys, arr);
        let delList = [];
        arr.forEach((v, i) => {
            delList[i] = v[10]
        })
        // console.log(delList)
        this.setState({
            delList: delList
        }, () => {
            // console.log(this.state.delList)
        })
        this.setState({ selectedRowKeys });
    }
    //  table列表方法
    getListFn = (current) => {
        const { enterpriseName, dataSources, regionId, startDate, endDate, startTime, endTime, dataTimeTypeCode, sourceChannelTypeCode } = this.state;
        let params = {
            "jsonData": {
                "entityRelated": {
                    "dataTimeTypeCode": dataTimeTypeCode === 0 ? '' : dataTimeTypeCode,
                    "sourceChannelTypeCode": sourceChannelTypeCode === 0 ? '' : sourceChannelTypeCode,
                    "dataSources": dataSources,
                    "regionId": regionId,
                    "startDate": startDate,
                    "endDate": endDate,
                    "startTime": startTime,
                    "endTime": endTime,
                    "enterpriseName": enterpriseName
                },//dataTimeTypeCode:时间维度，sourceChannelTypeCode：来源渠道，dataSources：数据来源，regionId:地区区域id,startDate:数据日期开始时间，endDate:数据日期结束时间，startTime：采集时间开始时间,endTime采集时间结束时间,enterpriseName:企业名称               
                "page": {
                    "pageIndex": current || 1,
                    "pageSize": 10
                },
                "orderList" : [{
                    "columnName" : "B.add_time",
                    "isASC" : false
                }]
            }
        }
        api.enterpriseMess.send(params).then(res => {
            console.log(res)
            let tableData = [];
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    tableData[i] = {
                        key: v.commonId,
                        0: v.dataTimeTypeText,
                        1: v.startTime,
                        2: v.areaLatitudeTypeText,
                        3: v.regionName,
                        4: v.addTime,
                        5: v.societyCreditId,   //  身份证号：后台数据返回暂无此字段
                        6: v.enterpriseName,
                        7: v.dataSources,
                        8: v.sourceChannelTypeText,
                        9: v.auditerSuggestion,
                        10: v.id,
                    }
                })
                this.setState({
                    tableData: tableData,
                    total: res.totalCount
                })
            } else {
                this.setState({
                    tableData: [],
                    total: 0
                })
            }
        })
    }
    //  时间维度下拉方法
    initTimeSelect() {
        api.timeDimension.send().then(res => {
            let timeDimensionNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    timeDimensionNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    timeDimensionData: this.state.timeDimensionData.concat(timeDimensionNew)
                }, () => {
                    // console.log(this.state.timeDimensionData)
                })
            }
        })
    }
    //  来源渠道下拉方法
    initOriginChannel() {
        api.originChannel.send().then(res => {
            let originChannelNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    originChannelNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    originChannelData: this.state.originChannelData.concat(originChannelNew)
                }, () => {
                    // console.log(this.state.originChannelData)
                })
            }
        })
    }
    //  数据日期选择
    dataSelect = (date, dateString) => {
        // console.log(date, dateString);
        this.setState({
            startDate: dateString[0],
            endDate: dateString[1]
        })
    }
    //  采集时间选择
    timeSelect = (date, dateString) => {
        // console.log(date, dateString);
        this.setState({
            startTime: dateString[0],
            endTime: dateString[1]
        })
    }
    //  监听input
    getValue = (e) => {
        let type = e.target.getAttribute('data-type');
        let value = e.target.value;
        this.setState({
            [type]: value
        }, () => {
            // console.log(this.state[type])
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
    componentDidMount() {
        //  未登录跳转到登录页
        if(!localStorage.token){
            this.props.history.push("/login")
            return
        }
        //  初始化列表
        this.getListFn(this.state.current);
        //  地理区域数据请求
        this.areaDataGet()
        //  初始化时间维度
        this.initTimeSelect()
        //  初始化来源渠道
        this.initOriginChannel()
    }
    //  时间维度
    timeHandleChange = (value) => {
        // console.log(`selected ${value}`);
        this.setState({
            dataTimeTypeCode: value
        })
    }
    //  来源渠道
    areaHandleChange = (value) => {
        // console.log(`selected ${value}`);
        this.setState({
            sourceChannelTypeCode: value
        })
    }
    render() {
        const { 
                selectedRowKeys,
                tableData,
                total, 
                current, 
                delList, 
                propsAjax, 
                areaData,
                timeDimensionData, 
                originChannelData, 
                dataSources, 
                enterpriseName 
            } = this.state;
        const rowSelection = {
            selectedRowKeys,
            onChange: this.onSelectChange,
            onSelect: (record, selected, selectedRows) => {
                console.log(record, ` selected :${selected}`, `selectedRows:${selectedRows}`);
            },
        };
        let pagination = {
            total: total,
            current: current,
            pageSize: 10,
            hideOnSinglePage: true,
            onChange: (current, pageSize) => {
                this.getListFn(current)
                this.setState({
                    current: current
                }, () => {
                    // console.log(this.state[type])
                })
            },
        }
        return (
            <div className="forData">
                <ul className="form-search clearfix">
                    {/* <li>
                        <label htmlFor="">时间维度：</label>
                        <Select defaultValue={timeDimensionData[0].code} style={{ width: '65%' }} onChange={this.timeHandleChange}>
                            {
                                timeDimensionData.map((v, i) => {
                                    return (
                                        <Option key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li> */}
                    <li className="item-time">
                        <label htmlFor="">数据日期：</label>
                        <RangePicker locale={locale} onChange={this.timeSelect} style={{ width: '73%' }} format="YYYY-MM-DD"/>
                    </li>
                    <li>
                        <label htmlFor="">地理区域：</label>
                        <Cascader 
                            locale={locale}
                            placeholder={'请选择'}
                            style={{ width: '65%' }}  
                            options={areaData}
                            onChange={this.areaFn} 
                            />
                    </li>
                    <li>
                        <label htmlFor="">企业名称：</label>
                        <Input style={{ width: '65%' }} data-type="enterpriseName" onChange={this.getValue} defaultValue={enterpriseName} />
                    </li>
                    {/* <li>
                        <label htmlFor="">来源渠道：</label>
                        <Select defaultValue={originChannelData[0].code} style={{ width: '65%' }} onChange={this.areaHandleChange}>
                            {
                                originChannelData.map((v, i) => {
                                    return (
                                        <Option key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li> */}
                    <li className="item-time">
                        <label htmlFor="">采集日期：</label>
                        <RangePicker locale={locale} onChange={this.dataSelect} style={{ width: '73%' }} format="YYYY-MM-DD"/>
                    </li>
                    <li>
                        <label htmlFor="">数据来源：</label>
                        <Input style={{ width: '65%' }} data-type="dataSources" onChange={this.getValue} defaultValue={dataSources} />
                    </li>
                    <li>
                        <Button type="primary" onClick={this.getListFn.bind(undefined, current)} className="fr" style={{ marginRight: "12px" }} icon="search">检索</Button>
                    </li>
                </ul>
                <div className="btn-type">
                    <NavLink to="/forData/enterpriseMess/enterpriseMessAdd/1"><Button icon="plus">单条数据填报</Button></NavLink>
                    <Button onClick={this.delListFn.bind(undefined, delList)} icon="delete" type="danger" >批量删除</Button>
                    <Button type="primary" icon="download" className="hide">数据模板下载</Button>
                    <Upload {...propsAjax}>
                        <Button icon="upload" className="hide">数据上传</Button>
                    </Upload>
                </div>
                <Table size={"small"} locale={{ emptyText: "暂无数据！" }} pagination={pagination} rowSelection={rowSelection} columns={this.state.columns} dataSource={tableData} />
            </div>
        )
    }
}