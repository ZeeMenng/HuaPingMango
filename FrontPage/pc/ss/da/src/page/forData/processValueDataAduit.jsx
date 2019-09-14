import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import { Select, Input, Button, message } from 'antd';
import * as api from "../api/api-forData-second";
const Option = Select.Option;

export default class ProcessValueDataAduit extends Component {
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
        api.daIndustryProcess.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,
                    startTime: v.daCommonField.startTime,
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,
                    dataSources: v.daCommonField.dataSources,
                    areaLatitudeTypeText: v.daCommonField.areaLatitudeTypeText,
                    regionName: v.daCommonField.regionName,
                    name: v.name,
                    cropTypeText: v.cropTypeText,
                    strainsText: v.processStrainsText,
                    yieldUnit: v.yieldUnit,
                    outputValueUnit: v.outputValueUnit,
                    yieldText:v.yieldText,
                    outputValueText: v.outputValueText,
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
        api.processValueDataAudit.send(params).then(res=>{
        //   console.log(res)
            if(res.isSuccess){
                message.success('上报成功！')
                //   返回上一页
                this.goBackPage()
            }
        })
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
            dataTimeTypeText,
            startTime,
            sourceChannelTypeText,
            dataSources,
            areaLatitudeTypeText,
            regionName,
            name,
            cropTypeText,
            strainsText,
            yieldUnit,
            outputValueUnit,
            yieldText,
            outputValueText,
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
                        <label htmlFor="">加工品种：</label>
                        <span>{strainsText}</span>
                    </li>
                    <li>
                        <label htmlFor="">产量：</label>
                        <span>{yieldUnit}{yieldText}</span>
                    </li>
                    <li>
                        <label htmlFor="">产值：</label>
                        <span>{outputValueUnit}{outputValueText}</span>
                    </li>
                    <li className="item-line">
                    </li>
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