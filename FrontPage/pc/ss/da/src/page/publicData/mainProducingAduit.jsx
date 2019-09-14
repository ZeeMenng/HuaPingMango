import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import { Select, Input, Button, message } from 'antd';
import * as api from "../api/api-publicData";
import moment from 'moment';
const Option = Select.Option;

export default class PriceDataAduit extends Component {
    constructor(props) {
        super(props)
        console.log(props)
        this.state = ({
            id:props.match.params.id.split('-')[0],              //  地址栏传参id
            comId:props.match.params.id.split('-')[1],           //  地址栏传参id
            auditStatusCode:1,                                   // 审核结果
            auditStatusText:'通过',
            formArr:[]
        })
    }
    //  渲染数据
    initList=(id)=>{
        api.MainProducingGet.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                var ss = 0;
                for (let i in v.daGrowYieldList) {
                    ss = Number(v.daGrowYieldList[i].growArea) + ss
                }
                this.setState({
                    "peasantCount": v.daGrowYieldList[0].peasantCount,
                    "productTotal" :v.daGrowYieldList[0].productTotal,
                    "growArea": ss,
                    "formArr": v.daGrowYieldList,
                    "auditStatusCode":v.daCommonField.auditStatusCode,
                    "auditStatusText": v.daCommonField.auditStatusText,
                    "auditerSuggestion": v.daCommonField.auditerSuggestion,
                    "startTime": v.daCommonField.startTime
                })
                var tt = (this.state.productTotal / this.state.growArea).toFixed(2)
                this.setState({
                    dc: tt
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
            }
        }
        api.MainProducingAduit.send(params).then(res=>{
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
        this.initList(this.state.comId)
    }
    render() {
        const {
            dataTimeTypeCode,
            timeDimensionData,
            name,
            formArr,
            auditStatusText,
            auditerSuggestion,
            auditStatusCode,
            growArea,
            productTotal,
            peasantCount,
            strainsData,
            strainsCode,
            startTime,
            dc
        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit clearfix">
                    <li>
                        <label htmlFor="">年份：</label>
                        <span>{startTime ? moment(startTime).format('YYYY') : ''}</span>
                    </li>
                    <li>
                        <label htmlFor="">种植户数：</label>
                        <span>{peasantCount}</span>
                    </li>
                    <li>
                        <label htmlFor="">鲜果总产量（万吨）：</label>
                        <span>{productTotal}</span>
                    </li>
                    <li>
                        <label htmlFor="">种植总面积（万亩）：</label>
                        <span>{growArea}</span>
                    </li>
                    <li>
                        <label htmlFor="">单产：</label>
                        <span>{dc}</span>
                    </li>
                    {
                        formArr.map((item,index)=>{
                            return(
                                <div key={index} style={{ width:'100%', display: "flex" }} >
                                    <li>
                                        <label htmlFor="">芒果品种：</label>
                                        <span>{item.strainsText}</span>
                                    </li>
                                    <li>
                                        <label htmlFor="">种植面积：</label>
                                        <span>{item.growArea}</span>
                                    </li>
                                    <div style={{ height:'30px', lineHeight: "30px", margin: "15px 0" }} onClick={this.formRemove} data-type={index}>-</div>
                                </div>
                            )
                        })
                    }
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