import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Table, Button, Icon, Tooltip, Popconfirm, message, Cascader, Upload } from 'antd';
import * as api from "../api/api-publicData";
import * as api1 from "../api/api-publicData";
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
            delList:[],                                 //  批量删除
            startDate:'',                               //  数据开始日期默认值
            endDate:'',                                 //  数据结束日期默认值
            startTime:'',                               //  采集开始时间默认值
            endTime:'',                                 //  采集结束时间默认值
            name:'',                                    //  名称
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            selectedRowKeys: [], // Check here to configure the default column
            columns: [
                {
                    title: '日期',
                    dataIndex: '1',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY-MM-DD')}</span>;
                        }
                    }
                },
                {
                    title: '地区',
                    dataIndex: '2',
                },
                {
                    title: '市场主体',
                    dataIndex: '3',
                },
                {
                    title: '产品',
                    dataIndex: '4',
                },
                {
                    title: '批发价格(元/公斤)',
                    dataIndex: '5',
                },
                {
                    title: '状态',
                    dataIndex: '6',
                },
                {
                    title: '操作',
                    dataIndex: '7',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="审核">
                                <NavLink to={`/publicData/priceWholesale/priceDataAduit/${text}-${record.key}`}>
                                    <Icon
                                        type="credit-card"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                        />
                                </NavLink>
                            </Tooltip>
                             <Tooltip placement="bottom" title="编辑">
                                <NavLink to={`/publicData/priceWholesale/priceDataAdd/${text}-${record.key}`}>
                                    <Icon
                                        type="form"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                                </NavLink>
                            </Tooltip>
                             <Tooltip placement="bottom" title="删除">
                                <Popconfirm title="确定要删除吗?" onConfirm={this.confirmDel.bind(undefined,text)} onCancel={cancel} okText="确定" cancelText="取消">
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




    //  列表方法
    initAltitudeList=(current)=>{
        const {
            wholePrice1,
            wholePrice2,
            startDate,
            endDate,
            name,
            priceTypeCode
        } = this.state
        let params={
            "jsonData": {
                "entityRelated":{
                    "bottomPrice":wholePrice1, //价格下限
                    "topPrice":wholePrice2,  //价格上限
                    "startDate":startDate,
                    "endDate":endDate,
                    /* "regionId":regionId,
                    "startTime":startTime,
                    "endTime":endTime, */
                    "name":name,  //市场主体
                    "priceTypeCode" : "2"//价格类型
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
        api1.priceWholesaleList.send(params).then(res=>{
            console.log(res)
            let tableData=[];
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    console.log(v)
                    tableData[i] = {
                        key:v.commonFieldId,
                        0:v.dataTimeTypeText,
                        1:v.startTime,
                        5:v.averagePriceUnit,
                        2:v.regionName,
                        4:v.strainsText,
                        3:v.name,
                        9:v.priceTypeText,
                        8:v.sourceChannelTypeText,
                        6:v.auditStatusText,
                        7:v.id,
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
        //  列表方法
        this.initAltitudeList(this.state.current)

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
            areaData
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
                    <li className="item-time">
                        <label htmlFor="">日期：</label>
                        <RangePicker locale={locale} onChange={this.timeSelect} style={{ width: '73%' }} />
                    </li>
                    <li>
                        <label htmlFor="">市场主体：</label>
                        <Input data-type="name" onChange={this.getValue} style={{ width: '65%' }} defaultValue={name} />
                    </li>
                    <li>
                        <label htmlFor="">批发价格：</label>
                        <Input style={{ width: '25%' }} data-type="wholePrice1" onChange={this.getValue} defaultValue={wholePrice1} />
                        <span style={{ width: '5%', color:'#666',display:'inline-block',margin:'0 5px' }}>－</span>
                        <Input style={{ width: '25%' }} data-type="wholePrice2" onChange={this.getValue} defaultValue={wholePrice2} />
                    </li>

                    <li>
                        <Button onClick={this.initAltitudeList.bind(undefined,1)} type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">查询</Button>
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