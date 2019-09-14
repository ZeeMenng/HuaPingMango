import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
// import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, message, Input, Button } from 'antd';
import * as api from "../api/api-forData";
const Option = Select.Option;

export default class ProductionDataAduit extends Component {
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
        api.inputOneData.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    name:v.name,                            //获证单位名称
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    areaLatitudeTypeText:v.daCommonField.areaLatitudeTypeText,
                    cropTypeText:v.cropTypeText,
                    strainsText:v.strainsText,
                    baseName:v.baseName,
                    inputTypeText:v.inputTypeText,
                    inputName:v.inputName,
                    brandName:v.brandName,
                    perCostUnitText:v.perCostUnitText,
                    useAmount:v.useAmount,
                    useAmountUnitText:v.useAmountUnitText,
                    purchasePoint:v.purchasePoint,
                    procurementTotal:v.procurementTotal,
                    procurementTotalUnitText:v.procurementTotalUnitText,
                    perPrice:v.perPrice,
                    perPriceUnitText:v.perPriceUnitText,
                    perCost:v.perCost,
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
                    dataSources:v.daCommonField.dataSources,
                    dataTimeTypeText:v.daCommonField.dataTimeTypeText,
                    auditStatusText:v.daCommonField.auditStatusText
                },()=>{console.log(this.state)})
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
          console.log(params)
          api.inputAudit.send(params).then(res=>{
              console.log(res)
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
            areaLatitudeTypeText,
            sourceChannelTypeText,
            procurementTotalUnitText,
            strainsText,
            cropTypeText,
            procurementTotal,
            perPriceUnitText,
            perCostUnitText,
            inputTypeText,
            useAmountUnitText,
            dataSources,
            perCost,
            perPrice,
            useAmount,
            brandName,
            purchasePoint,
            inputName,
            regionName,
            baseName,
            name,
            startTime,
            auditStatusText
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
                        <label htmlFor="">基地名称：</label>
                        <span>{baseName}</span>
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
                        <label htmlFor="">投入品类型：</label>
                        <span>{inputTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">投入品名称：</label>
                        <span>{inputName}</span>
                    </li>
                    <li>
                        <label htmlFor="">品牌名称：</label>
                        <span>{brandName}</span>
                    </li>
                    <li>
                        <label htmlFor="">生产厂家：</label>
                        <span>{purchasePoint}</span>
                    </li>
                    <li>
                        <label htmlFor="">采购总量：</label>
                        <span>{procurementTotal}{procurementTotalUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">使用量：</label>
                        <span>{useAmount}{useAmountUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">单价：</label>
                        <span>{perPrice}{perPriceUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">成本：</label>
                        <span>{perCost}{perCostUnitText}</span>
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
                        <Select onChange={this.handleSelect} defaultValue={auditStatusText} style={{ width: '65%' }} >
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