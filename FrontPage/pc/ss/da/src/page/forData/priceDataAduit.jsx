import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import { Select, Input, Button, message } from 'antd';
import * as api from "../api/api-forData-second";
const Option = Select.Option;

export default class PriceDataAduit extends Component {
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
        api.daMarketPriceGetModel.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    averagePrice: v.averagePrice,//平均价
                    averagePriceUnit: v.averagePriceUnit,//通用平均价
                    averagePriceUnitText: v.averagePriceUnitText,//平均价单位文本
                    bottomPrice: v.bottomPrice,//最低价
                    bottomPriceUnit: v.bottomPriceUnit,//通用最低价
                    bottomPriceUnitText: v.bottomPriceUnitText,//最低价单位文本
                    cropTypeText: v.cropTypeText,//产品种类文本
                    areaLatitudeTypeText: v.daCommonField.areaLatitudeTypeText,//区域维度名称
                    startTime: v.daCommonField.startTime,//数据日期
                    dataSources: v.daCommonField.dataSources,//数据来源
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,//时间维度名称
                    regionName: v.daCommonField.regionName,//地理区域名称
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,//来源渠道名称
                    name: v.name,//数据主体名称
                    perPrice: v.perPrice,//单价
                    perPriceUnit: v.perPriceUnit,//通用单价
                    perPriceUnitText: v.perPriceUnitText,//单价单位文本
                    priceRangeText: v.priceRangeText,//价格区间文本
                    priceTypeText: v.priceTypeText,//价格类型文本
                    strainsText: v.strainsText,//产品品种文本
                    topPrice: v.topPrice,//最高价
                    topPriceUnit: v.topPriceUnit,//通用最高价
                    topPriceUnitText: v.topPriceUnitText,//最高价单位文本
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
        api.daMarketPriceAudit.send(params).then(res=>{
        //   console.log(res)
            if(res.isSuccess){
                message.success('上报成功！')
                //   返回上一页
                this.goBackPage()
            }
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
    componentDidMount(){
        //  初始化数据
        this.initList(this.state.id)
    }
    render() {
        const {
            averagePrice,
            averagePriceUnitText,
            bottomPrice,
            bottomPriceUnitText,
            cropTypeText,
            areaLatitudeTypeText,
            startTime,
            dataSources,
            dataTimeTypeText,
            regionName,
            sourceChannelTypeText,
            name,
            perPrice,
            perPriceUnitText,
            priceTypeText,
            strainsText,
            topPrice,
            topPriceUnitText,
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
                    {/* <li>
                        <label htmlFor="">来源渠道：</label>
                        <span>{sourceChannelTypeText}</span>
                    </li> */}
                    <li>
                        <label htmlFor="">数据来源：</label>
                        <span>{dataSources}</span>
                    </li>
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
                        <label htmlFor="">产品种类：</label>
                        <span>{cropTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">产品品种：</label>
                        <span>{strainsText}</span>
                    </li>
                    <li>
                        <label htmlFor="">价格类型：</label>
                        <span>{priceTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">单价：</label>
                        <span>{perPrice}{perPriceUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">平均价：</label>
                        <span>{averagePrice}{averagePriceUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">最低价：</label>
                        <span>{bottomPrice}{bottomPriceUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">最高价：</label>
                        <span>{topPrice}{topPriceUnitText}</span>
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