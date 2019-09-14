import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, Tabs, Button, message, Icon, Tooltip, Table, Popconfirm, Input } from 'antd';
import * as api from "../api/api-publicData";
import moment from 'moment';
import { NavLink } from "react-router-dom";
import Gdlink from './Shareholder'
import Xzlink from './administrative'
import QyLink from './Assetinformation'
import FyLink from './Courtdecision'
// import Options from "../component/area";
function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}
const Option = Select.Option;
const TabPane = Tabs.TabPane;
export default class PriceDataAdd extends Component {
    constructor(props) {
        super(props)
        this.state = {
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            enterpriseName: '',
            societyCreditId: '',
            organizationCode: '',
            enterpriseTypeText: '',
            corporationName: '',
            foundDate: '',
            registeredCapital: '',
            regionText: '',
            enterprisePhone: '',
            enterpriseAddress: '',
            tradeText: '',
            businessScope: '',
            description: '',
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            auditStatusCode: 0,
            auditStatusText: "",
            auditerSuggestion: "",
            columns: [
                {
                    title: '序号',
                    dataIndex: '0',
                },
                {
                    title: '股东',
                    dataIndex: '1',
                },
                {
                    title: '持股比例',
                    dataIndex: '2',
                },
                {
                    title: '认缴出资额（万元）',
                    dataIndex: '3',
                },
                {
                    title: '认缴出资日期',
                    dataIndex: '4',
                },
                {
                    title: '股东类型',
                    dataIndex: '5',
                },
                {
                    title: '操作',
                    dataIndex: '10',
                    key: 'check',
                    render: (text, record) => (
                        <span>
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
    //  input监听
    inputHandle = (e) =>{
        // console.log(e.target.getAttribute('data-type'))
        let type = e.target.getAttribute('data-type')
        this.setState({
            [type]:e.target.value
        },()=>{
            //  console.log(this.state[type])
        })
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
    onChange = (date, dateString) => {
        console.log(date, dateString);
    }
    goBackPage = () => {
        this.props.history.go(-1)
    }
    initInput=(id)=>{
        api.MerchantsGet.send({id:id}).then(res=>{
            console.log(res.data)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    enterpriseName: v.enterpriseName,
                    societyCreditId: v.societyCreditId,
                    organizationCode: v.organizationCode,
                    enterpriseTypeText: v.enterpriseTypeText,
                    corporationName: v.corporationName,
                    foundDate: v.foundDate,
                    registeredCapital: v.registeredCapital,
                    regionText: v.regionText,
                    enterprisePhone: v.enterprisePhone,
                    enterpriseAddress: v.enterpriseAddress,
                    tradeText: v.tradeText,
                    businessScope: v.businessScope,
                    description: v.description,
                    auditStatusCode: v.daCommonField.auditStatusCode,
                    auditStatusText: v.daCommonField.auditStatusText,
                    auditerSuggestion: v.daCommonField.auditerSuggestion
                })
            }else{
                message.error('信息初始化失败！')
            }
        })
    }
    //  上报
    formSaveFn = () =>{
        const {
            id,
            comId,
            auditStatusCode,
            auditStatusText,
            auditerSuggestion
        } =this.state
        let params = {
            "entity": {
              "daCommonField": {
                "auditStatusCode": auditStatusCode,
                "auditStatusText": auditStatusText,
                "auditerSuggestion": auditerSuggestion
              }
            },
            "idList": [
                id
            ]
        }
        api.MerchantsAudit.send(params).then(res=>{
        //   console.log(res)
            if(res.isSuccess){
                message.success('上报成功！')
                //   返回上一页
                this.goBackPage()
            }
        })
    }
    componentDidMount(){
        if(Number(this.state.isAdd) !== 1){
            let id = this.state.isAdd;
            this.initInput(id.split('-')[0])
        }
    }
    render() {
        const {
            enterpriseName,
            societyCreditId,
            organizationCode,
            enterpriseTypeText,
            corporationName,
            foundDate,
            registeredCapital,
            regionText,
            enterprisePhone,
            enterpriseAddress,
            tradeText,
            businessScope,
            description,
            current,
            total,
            tableData,
            auditStatusText,
            auditerSuggestion,
            isAdd
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
            <div className="forData priceDataAdd">
                <ul className="form-search form-aduit clearfix">
                    <li>
                        <label htmlFor="">企业名称：</label>
                        <span>{enterpriseName}</span>
                    </li>
                    <li>
                        <label htmlFor="">统一社会信用代码：</label>
                        <span>{societyCreditId}</span>
                    </li>
                    <li>
                        <label htmlFor="">组织机构代码：</label>
                        <span>{organizationCode}</span>
                    </li>
                    <li>
                        <label htmlFor="">产业链环节：</label>
                        <span>{enterpriseTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">法定代表人：</label>
                        <span>{corporationName}</span>
                    </li>
                    <li>
                        <label htmlFor="">成立日期：</label>
                        <span>{foundDate}</span>
                    </li>
                    <li>
                        <label htmlFor="">注册资本：</label>
                        <span>{registeredCapital}</span>
                    </li>
                    <li>
                        <label htmlFor="">所在区域：</label>
                        <span>{regionText}</span>
                    </li>
                    <li>
                        <label htmlFor="">联系电话：</label>
                        <span>{enterprisePhone}</span>
                    </li>
                    <li>
                        <label htmlFor="">详细地址：</label>
                        <span>{enterpriseAddress}</span>
                    </li>
                    <li>
                        <label htmlFor="">所属行业：</label>
                        <span>{tradeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">经营范围：</label>
                        <span>{businessScope}</span>
                    </li>
                    <li>
                        <label htmlFor="">简介：</label>
                        <span>{description}</span>
                    </li>
                </ul>
                <div className="card-container">
                    <Tabs type="card">
                        <TabPane tab="股东信息" key="1">
                            <Gdlink id={isAdd}/>
                        </TabPane>
                        <TabPane tab="行政许可" key="2">
                            <Xzlink id={isAdd}/>
                        </TabPane>
                        <TabPane tab="融资信息" key="3">
                            <p>暂无数据</p>
                        </TabPane>
                        <TabPane tab="招聘信息" key="4">
                            <p>暂无数据</p>
                        </TabPane>
                        <TabPane tab="企业资产状况信息" key="5">
                            <QyLink id={isAdd}/>
                        </TabPane>
                        <TabPane tab="法院判决" key="6">
                            <FyLink id={isAdd}/>
                        </TabPane>
                    </Tabs>
                </div>
                <ul className="form-search form-aduit clearfix">
                    <li className="item">
                        <label htmlFor="">审核人：</label>
                        <Input style={{ width: '65%' }} defaultValue=" " />
                    </li>
                    <li className="item">
                        <label htmlFor="">审核意见：</label>
                        <Input value={auditerSuggestion} onChange={this.inputHandle} data-type="auditerSuggestion" style={{ width: '65%' }} defaultValue="" />
                    </li>
                    <li className="item">
                        <label htmlFor="">审核结果：</label>
                        <Select onChange={this.handleSelect} value={auditStatusText} style={{ width: '65%' }} >
                            <Option codenum={'auditStatusCode'} typename={'auditStatusText'} value="1">通过</Option>
                            <Option codenum={'auditStatusCode'} typename={'auditStatusText'} value="2">不通过</Option>
                        </Select>
                    </li>
                    <li>
                        <Button onClick={this.formSaveFn} type="primary">上报</Button>
                        <Button onClick={this.goBackPage}>取消</Button>
                    </li>
                </ul>
            </div>
        )
    }
}