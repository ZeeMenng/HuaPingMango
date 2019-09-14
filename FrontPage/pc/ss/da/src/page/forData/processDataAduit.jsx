import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
// import locale from 'antd/lib/date-picker/locale/zh_CN';
import * as api from "../api/api-forData-second";
import { Select,Input, Button, message } from 'antd';
const Option = Select.Option;

export default class ProcessDataAduit extends Component {
    constructor(props) {
        super(props)
        this.state = ({
            id: props.match.params.id.split('-')[0],              //  地址栏传参id
            comId: props.match.params.id.split('-')[1],           //  地址栏传参id
            auditStatusCode: 1,                                   // 审核结果
            auditStatusText: '通过'
        })
    }
    //  修改时重新渲染数据
    initInput = (id) => {
        // console.log(id)
        api.processDataSearch.send({ id: id }).then(res => {
            console.log(res)
            if (res.isSuccess) {
                let v = res.data;
                this.setState({
                    name: v.name,//数据主体名称
                    consumeTotal: v.consumeTotal, //原材料消耗总量
                    consumeTotalCode: v.consumeTotalCode, //原材料消耗总量单位
                    consumeTotalText: v.consumeTotalText,//原材料消耗总量单位文本
                    consumeTotalUnit: v.consumeTotalUnit, //通用原材料消耗总量
                    cropTypeCode: v.cropTypeCode,//作物种类code
                    cropTypeText: v.cropTypeText,//作物种类文本
                    areaLatitudeTypeCode: v.daCommonField.areaLatitudeTypeCode,//区域维度id
                    areaLatitudeTypeText: v.areaLatitudeTypeText,//区域维度名称
                    startTime: v.startTime,//数据日期
                    dataSources: v.dataSources,//数据来源
                    dataTimeTypeCode: v.dataTimeTypeCode,//时间维度id
                    dataTimeTypeText: v.dataTimeTypeText,//时间维度名称
                    regionId: v.regionId,//地理区域id
                    regionName: v.regionName,//地理区域名称
                    sourceChannelTypeCode: v.sourceChannelTypeCode,//来源渠道id
                    sourceChannelTypeText: v.sourceChannelTypeText,//来源渠道名称
                    outputValue: v.outputValue,//产值
                    outputValueCode: v.outputValueCode,//产值单位code
                    outputValueText: v.outputValueText,//产值单位文本
                    strainsCode: v.strainsCode,//作物品种code
                    strainsText: v.strainsText,//作物品种文本
                    processStrainsCode: v.processStrainsCode,//加工品品种code
                    processStrainsText: v.processStrainsText,//加工品品种文本
                    processTime: v.processTime,//加工时间
                    productName: v.productName, //产品名称文本
                    productTotal: v.productTotal,//总产量
                    productTotalCode: v.productTotalCode,//总产量单位code
                    productTotalText: v.productTotalText,//总产量单位文本
                    productTotalUnit: v.productTotalUnit//通用总产量
                },
                    // ()=>console.log(this.state)
                )
            } else {
                message.error('信息初始化失败！')
            }
        })
    }
    componentDidMount() {
        //  初始化数据
        this.initInput(this.state.id)
    }
    //  上报
    formSaveFn = () => {
        const {
            id,
            comId,
            auditStatusCode,
            auditStatusText,
            auditerSuggestion
        } = this.state
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
        api.processDataAudit.send(params).then(res => {
            console.log(res)
            if (res.isSuccess) {
                message.success('上报成功！')
                //   返回上一页
                this.goBackPage()
            }
        })
    }
    //  input监听
    inputHandle = (e) => {
        // console.log(e.target.getAttribute('data-type'))
        let type = e.target.getAttribute('data-type')
        this.setState({
            [type]: e.target.value
        }, () => {
            //  console.log(this.state[type])
        })
    }
    //  返回上一页
    goBackPage = () => {
        this.props.history.go(-1)
    }
    //  下拉获取值通用方法
    handleSelect = (e, option) => {
        console.log(e, option)
        let type = option.props.typename;
        let code = option.props.codenum;
        let text = option.props.children;
        this.setState({
            [type]: text,
            [code]: e
        }, () => {
            // console.log(this.state[type],this.state[code])
        })
    }
    render() {
        const {
            dataTimeTypeText, startTime, sourceChannelTypeText, dataSources, areaLatitudeTypeText, regionName,
            name, cropTypeText, processStrainsText, productName, processTime, consumeTotal, productTotal, outputValue,
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
                        <label htmlFor="">作物种类：</label>
                        <span>{cropTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">加工品种：</label>
                        <span>{processStrainsText}</span>
                    </li>
                    <li>
                        <label htmlFor="">产品名称：</label>
                        <span>{productName}</span>
                    </li>
                    <li>
                        <label htmlFor="">产品量：</label>
                        <span>{productTotal}</span>
                    </li>
                    <li>
                        <label htmlFor="">产值：</label>
                        <span>{outputValue}</span>
                    </li>
                    <li>
                        <label htmlFor="">加工时间：</label>
                        <span>{processTime}</span>
                    </li>
                    <li>
                        <label htmlFor="" style={{ fontSize: '12px' }}>原材料消耗量：</label>
                        <span>{consumeTotal}</span>
                    </li>
                    <li className="item-line">
                    </li>
                    <li className="item">
                        <label htmlFor="">审核人：</label>
                        <Input style={{ width: '65%' }} defaultValue="" />
                    </li>
                    <li className="item">
                        <label htmlFor="">审核意见：</label>
                        <Input onChange={this.inputHandle} data-type="auditerSuggestion" style={{ width: '65%' }} defaultValue="" />
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