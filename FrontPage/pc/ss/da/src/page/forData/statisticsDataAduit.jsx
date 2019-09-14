import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import { Select, Input, Button, message } from 'antd';
import * as api from "../api/api-forData-second";
const Option = Select.Option;

export default class StatisticsDataAduit extends Component {
    constructor(props) {
        super(props)
        this.state = ({
            id:props.match.params.id.split('-')[0],              //  地址栏传参id
            comId:props.match.params.id.split('-')[1],           //  地址栏传参id
            auditStatusCode:1,                                   // 审核结果
            auditStatusText:'通过'
        })
    }
    //  渲染数据
    initList=(id)=>{
        api.dIStatisticsDataGetModel.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    cropTypeText: v.cropTypeText,
                    areaLatitudeTypeText: v.daCommonField.areaLatitudeTypeText,
                    startTime: v.daCommonField.startTime,
                    dataSources: v.daCommonField.dataSources,
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,
                    regionName: v.daCommonField.regionName,
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,
                    fruithangingArea: v.fruithangingArea,
                    fruithangingAreaText: v.fruithangingAreaText,
                    fruithangingAreaUnit: v.fruithangingAreaUnit,
                    growArea: v.growArea,
                    growAreaText: v.growAreaText,
                    growHousehold: v.growHousehold,
                    name: v.name,
                    onlineSaleamount: v.onlineSaleamount,
                    onlineSaleamountText: v.onlineSaleamountText,
                    onlineSales: v.onlineSales,
                    onlineSalesText: v.onlineSalesText,
                    organicArea: v.organicArea,
                    organicAreaText: v.organicAreaText,
                    output: v.output,
                    outputValue: v.outputValue,
                    outputValueText: v.outputValueText,
                    outputPer: v.outputPer,
                    outputPerTotalText: v.outputPerTotalText,
                    outputTotalText: v.outputTotalText,
                    strainsText: v.strainsText,
                    wholesaleSales: v.wholesaleSales,
                    wholesaleSalesText: v.wholesaleSalesText,
                    auditStatusCode:v.daCommonField.auditStatusCode,          // 审核结果
                    auditStatusText:v.daCommonField.auditStatusText,
                    auditerSuggestion:v.daCommonField.auditerSuggestion,
                })
            }else{
                message.error('信息初始化失败！')
            }
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
    //  下拉获取值通用方法
    handleSelect = (e,option) =>{
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
                "auditerSuggestion": auditerSuggestion,
                "id": comId
              }
            },
            "idList": [
                id
            ]
        }
        api.dIStatisticsDataAudit.send(params).then(res=>{
        //   console.log(res)
            if(res.isSuccess){
                message.success('上报成功！')
                //   返回上一页
                this.goBackPage()
            }
        })
    }
    componentDidMount(){
        //  初始化数据
        this.initList(this.state.id)
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
    render() {
        const {
            dataTimeTypeText,
            startTime,
            sourceChannelTypeText,
            dataSources,
            areaLatitudeTypeText,
            regionName,
            name,
            cropTypeText,
            strainsText,
            growHousehold,
            organicArea,
            organicAreaText,
            fruithangingArea,
            fruithangingAreaText,
            output,
            outputTotalText,
            outputPer,
            outputPerTotalText,
            outputValue,
            outputValueText,
            wholesaleSales,
            wholesaleSalesText,
            onlineSales,
            onlineSalesText,
            growArea,
            growAreaText,
            auditStatusText,
            auditerSuggestion
        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit clearfix">
                    {/* <li>
                        <label htmlFor="">时间维度：</label>
                        <span>{dataTimeTypeText}</span>
                    </li> */}
                    <li>
                        <label htmlFor="">数据日期：</label>
                        <span>{startTime}</span>
                    </li>
                    <li>
                        <label htmlFor="">来源渠道：</label>
                        <span>{sourceChannelTypeText}</span>
                    </li>
                    {/* <li>
                        <label htmlFor="">数据来源：</label>
                        <span>{dataSources}</span>
                    </li> */}
                    {/* <li>
                        <label htmlFor="">区域维度：</label>
                        <span>{areaLatitudeTypeText}</span>
                    </li> */}
                    <li>
                        <label htmlFor="">地理区域：</label>
                        <span>{regionName}</span>
                    </li>
                    <li>
                        <label htmlFor="">名称：</label>
                        <span>{name}</span>
                    </li>
                    <li>
                        <label htmlFor="">作物种类：</label>
                        <span>{cropTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">作物品种：</label>
                        <span>{strainsText}</span>
                    </li>
                    <li>
                        <label htmlFor="">种植面积</label>
                        <span>{growArea}{growAreaText}</span>
                    </li>
                    <li>
                        <label htmlFor="">种植户数：</label>
                        <span>{growHousehold}</span>
                    </li>
                    <li>
                        <label htmlFor="">有机认证面积：</label>
                        <span>{organicArea}{organicAreaText}</span>
                    </li>
                    <li>
                        <label htmlFor="">挂果面积：</label>
                        <span>{fruithangingArea}{fruithangingAreaText}</span>
                    </li>
                    <li>
                        <label htmlFor="">产量：</label>
                        <span>{output}{outputTotalText}</span>
                    </li>
                    <li>
                        <label htmlFor="">面积产量：</label>
                        <span>{outputPer}{outputPerTotalText}</span>
                    </li>
                    <li>
                        <label htmlFor="">产值：</label>
                        <span>{outputValue}{outputValueText}</span>
                    </li>
                    <li>
                        <label htmlFor="">批发销售额：</label>
                        <span>{wholesaleSales}{wholesaleSalesText}</span>
                    </li>
                    <li>
                        <label htmlFor="">电商销售额：</label>
                        <span>{onlineSales}{onlineSalesText}</span>
                    </li>
                    <li className="item-line">
                    </li>
                    <li className="item">
                        <label htmlFor="">审核人：</label>
                        <Input style={{ width: '65%' }} defaultValue="" />
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