import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import { Select, Input, Button, message } from 'antd';
import * as api from "../api/api-forData-second";
const Option = Select.Option;

export default class TradeDataAduit extends Component {
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
        api.tradeDataGetModel.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    cropTypeText: v.cropTypeText,//产品种类文本
                    areaLatitudeTypeText: v.daCommonField.areaLatitudeTypeText,//区域维度名称
                    startTime: v.daCommonField.startTime,//数据日期
                    dataSources: v.daCommonField.dataSources,//数据来源
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,//时间维度名称
                    regionName: v.daCommonField.regionName,//地理区域名称
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,//来源渠道名称
                    exportAmount: v.exportAmount,//出口量
                    exportAmountUnit: v.exportAmountUnit,//通用出口量
                    exportAmountUnitText: v.exportAmountUnitText,//出口量单位文本
                    exportCountryText: v.exportCountryText,//出口国家文本
                    exportEnterprise: v.exportEnterprise,//出口企业
                    exportPrice: v.exportPrice,//出口价格
                    exportPriceUnit: v.exportPriceUnit,//通用出口价格
                    exportPriceUnitText: v.exportPriceUnitText,//出口价格单位文本
                    exportTime: v.exportTime,//出口日期
                    exportVolume: v.exportVolume,//出口金额
                    exportVolumeUnit: v.exportVolumeUnit,//通用出口金额
                    exportVolumeUnitText: v.exportVolumeUnitText,//出口金额单位文本
                    importAmount: v.importAmount,//进口量
                    importAmountUnit: v.importAmountUnit,//通用进口量
                    importAmountUnitText: v.importAmountUnitText,//进口量单位文本
                    importCountryText: v.importCountryText,//进口国家名称文本
                    importEnterprise: v.importEnterprise,//进口企业
                    importPrice: v.importPrice,//进口价格
                    importPriceUnit: v.importPriceUnit,//通用进口价格
                    importPriceUnitText: v.importPriceUnitText,//进口价格单位文本
                    importTime: v.importTime,//进口日期
                    importVolume: v.importVolume,//进口额
                    importVolumeUnit: v.importVolumeUnit,//通用进口额
                    importVolumeUnitText: v.importVolumeUnitText,//进口额单位文本
                    name: v.name, //数据主体名称
                    productName: v.productName,//产品名称
                    strainsText: v.strainsText,//产品品种文本
                    auditStatusCode:v.daCommonField.auditStatusCode,          // 审核结果
                    auditStatusText:v.daCommonField.auditStatusText,
                    auditerSuggestion:v.daCommonField.auditerSuggestion,
                    auditerUserId:v.daCommonField.auditerUserId, 
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
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
    onChange = (date, dateString) => {
        console.log(date, dateString);
    }
    goBackPage = () => {
        this.props.history.go(-1)
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
        api.tradeDataAduit.send(params).then(res=>{
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
    render() {
        const {
            dataTimeTypeText,
            startTime,
            sourceChannelTypeCode,
            dataSources,
            areaLatitudeTypeText,
            regionName,
            name,
            cropTypeText,
            strainsText,
            productName,
            importCountryText,
            importEnterprise,
            importTime,
            importAmount,
            importPrice,
            exportCountryText,
            exportEnterprise,
            exportTime,
            exportAmount,
            exportVolume,
            importAmountUnitText,
            exportVolumeUnitText,
            importPriceUnitText,
            exportAmountUnitText,
            auditStatusText,
            auditerSuggestion,
            auditerUserId
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
                        <span>{sourceChannelTypeCode}</span>
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
                        <label htmlFor="">产品种类：</label>
                        <span>{cropTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">产品品种：</label>
                        <span>{strainsText}</span>
                    </li>
                    <li>
                        <label htmlFor="">产品名称：</label>
                        <span>{productName}</span>
                    </li>
                    <li>
                        <label htmlFor="">进口国家：</label>
                        <span>{importCountryText}</span>
                    </li>
                    <li>
                        <label htmlFor="">进口企业：</label>
                        <span>{importEnterprise}</span>
                    </li>
                    <li>
                        <label htmlFor="">进口日期：</label>
                        <span>{importTime}</span>
                    </li>
                    <li>
                        <label htmlFor="">进口量：</label>
                        <span>{importAmount}{importAmountUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">进口额：</label>
                        <span>{importPrice}{importPriceUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口国家：</label>
                        <span>{exportCountryText}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口企业：</label>
                        <span>{exportEnterprise}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口日期：</label>
                        <span>{exportTime}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口量：</label>
                        <span>{exportAmount}{exportAmountUnitText}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口额：</label>
                        <span>{exportVolume}{exportVolumeUnitText}</span>
                    </li>
                    <li className="item-line">
                    </li>
                    <li className="item">
                        <label htmlFor="">审核人：</label>
                        <Input style={{ width: '65%' }} value={auditerUserId}/>
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