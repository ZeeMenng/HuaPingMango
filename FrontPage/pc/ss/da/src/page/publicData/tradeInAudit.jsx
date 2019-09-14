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
            auditStatusText:'通过'
        })
    }
    //  渲染数据
    initList=(id)=>{
        api.TradeOutGet.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    auditStatusText: v.auditStatusText,
                    auditerSuggestion: v.auditerSuggestion,
                    dataTimeTypeText: v.dataTimeTypeText,
                    timeDimensionData: v.timeDimensionData,
                    exportVolume: v.importVolume,
                    remark: v.daCommonField.remark,
                    exportAmount: v.importAmount,
                    exportPrice: v.importPrice,
                    exportCountryText: v.importCountryText,
                    auditStatusCode: v.auditStatusCode,
                    startTime: v.daCommonField.startTime
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
        api.TradeOutAduit.send(params).then(res=>{
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
            auditStatusText,
            auditerSuggestion,
            dataTimeTypeText,
            auditStatusCode,
            timeDimensionData,
            exportVolume,
            remark,
            exportAmount,
            exportPrice,
            exportCountryText,
            startTime
        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit clearfix">
                <li>
                        <label htmlFor="">年度：</label>
                        <span>{startTime ? moment(startTime).format('YYYY') : ''}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口国家：</label>
                        <span>{exportCountryText}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口金额（万美元）：</label>
                        <span>{exportVolume}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口量（万吨）：</label>
                        <span>{exportAmount}</span>
                    </li>
                    <li>
                        <label htmlFor="">出口价格（美元/公斤）：</label>
                        <span>{exportPrice}</span>
                    </li>
                    <li>
                        <label htmlFor="">备注：</label>
                        <span>{remark}</span>
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