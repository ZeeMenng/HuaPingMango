import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
// import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, message, Input, Button } from 'antd';
import * as api from "../api/api-forData-second";
const Option = Select.Option;

export default class ECommerceSellDataAduit extends Component {
    constructor(props){
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
        api.shopFormSearch.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    ecommerceType:v.ecommerceType,
                    regionId:v.daCommonField.regionId,
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    saleAmount:v.saleAmount,
                    saleVolume:v.saleVolume,
                    seller:v.seller,
                    tradeTime:v.tradeTime,
                    saleAmountUnitCode:v.saleAmountUnitCode,
                    saleAmountUnitText:v.saleAmountUnitText,
                    areaLatitudeTypeCode:v.daCommonField.areaLatitudeTypeCode,
                    areaLatitudeTypeText:v.daCommonField.areaLatitudeTypeText,
                    cropTypeCode:v.cropTypeCode,
                    cropTypeText:v.cropTypeText,
                    strainsCode:v.strainsCode,
                    saleRegionText:v.saleAreaText,
                    saleAreaText:v.saleAreaText,
                    areaText:v.areaText,
                    orderTime:v.orderTime,
                    orderId:v.orderId,
                    contactPhone:v.contactPhone,
                    courier:v.courier,
                    payAccount:v.payAccount,
                    payTypeText:v.payTypeText,
                    courierNumber:v.courierNumber,
                    courierFeeUnitText:v.courierFeeUnitText,
                    ecommerceText:v.ecommerceText,
                    enterpriseName:v.enterpriseName,
                    actualIncome:v.actualIncome,
                    actualIncomeUnitText:v.actualIncomeUnitText,
                    addressee:v.addressee,
                    deliveryAddress:v.deliveryAddress,
                    strainsText:v.strainsText,
                    sourceChannelTypeCode:v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
                    dataSources:v.daCommonField.dataSources,
                    dataTimeTypeCode:v.daCommonField.dataTimeTypeCode,
                    dataTimeTypeText:v.daCommonField.dataTimeTypeText,
                    auditStatusCode:v.daCommonField.auditStatusCode,          // 审核结果
                    auditStatusText:v.daCommonField.auditStatusText?v.daCommonField.auditStatusText:"未审核"
                },
                // ()=>console.log(this.state)
            )
            }else{
                message.error('信息初始化失败！')
            }
        })
    }
    componentDidMount(){
        //  初始化数据
        this.initList(this.state.id)
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
          api.shopFormAudit.send(params).then(res=>{
            //   console.log(res)
              if(res.isSuccess){
                  message.success('上报成功！')
                  //   返回上一页
                  this.goBackPage()
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
    //  返回上一页
    goBackPage = () =>{
        this.props.history.go(-1)
    }
    //  下拉获取值通用方法
    handleSelect = (e,option) =>{
        console.log(e,option)
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
    render() {
        const {
            dataTimeTypeText,
            sourceChannelTypeText,
            areaLatitudeTypeText,
            saleAmountUnitText,
            cropTypeText,
            strainsText,
            startTime,
            dataSources,
            regionName,
            ecommerceType,
            saleAmount,
            auditStatusText,
            ecommerceText,
            areaText,
            courierFeeUnitText,
            actualIncomeUnitText,
            payTypeText,
            actualIncome,
            enterpriseName,
            orderId,
            deliveryAddress,
            contactPhone,
            courier,
            courierFee,
            courierNumber,
            payAccount,
            addressee,
            orderTime
        } =this.state
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
                        <label htmlFor="">平台名称：</label>
                        <span>{ecommerceType}</span>
                    </li>
                    <li>
                        <label htmlFor="">订单日期：</label>
                        <span>{orderTime}</span>
                    </li>
                    <li>
                        <label htmlFor="">订单号：</label>
                        <span>{orderId}</span>
                    </li>
                    <li>
                        <label htmlFor="">平台号：</label>
                        <span>{ecommerceText}</span>
                    </li>
                    <li>
                        <label htmlFor="">企业名称：</label>
                        <span>{enterpriseName}</span>
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
                        <label htmlFor="">销售数量：</label>
                        <span>{saleAmount}{saleAmountUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">实收金额：</label>
                        <span>{actualIncome}{actualIncomeUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">收件人：</label>
                        <span>{addressee}</span>
                    </li>
                    <li>
                        <label htmlFor="">收件区域：</label>
                        <span>{areaText}</span>
                    </li>
                    <li>
                        <label htmlFor="">收件地址：</label>
                        <span>{deliveryAddress}</span>
                    </li>
                    <li>
                        <label htmlFor="">联系电话：</label>
                        <span>{contactPhone}</span>
                    </li>
                    <li>
                        <label htmlFor="">快递公司：</label>
                        <span>{courier}</span>
                    </li>
                    <li>
                        <label htmlFor="">快递费用：</label>
                        <span>{courierFee}{courierFeeUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">运单号：</label>
                        <span>{courierNumber}</span>
                    </li>
                    <li>
                        <label htmlFor="">支付方式：</label>
                        <span>{payTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">交易账户：</label>
                        <span>{payAccount}</span>
                    </li>
                    <li className="item-line">
                    </li>
                    <li className="item">
                        <label htmlFor="">审核人：</label>
                        <Input style={{ width: '65%' }} defaultValue="" />
                    </li>
                    <li className="item">
                        <label htmlFor="">审核意见：</label>
                        <Input  onChange={this.inputHandle} data-type="auditerSuggestion" style={{ width: '65%' }} defaultValue="" />
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