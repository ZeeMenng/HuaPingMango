import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
// import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, message, Input, Button } from 'antd';
import * as api from "../api/api-forData";
const Option = Select.Option;

export default class BaseDataAduit extends Component {
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
        api.baseDataSearch.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    name:v.name,
                    baseName:v.baseName,
                    address:v.address,
                    baseLatitude:v.baseLatitude,
                    baseLongitude:v.baseLongitude,
                    baseTypeCode:v.baseTypeCode,
                    baseTypeText:v.baseTypeText,
                    owner:v.owner,
                    baseCode:v.baseCode,
                    baseAreaUnit:v.baseAreaUnit,
                    baseArea:v.baseArea,
                    regionId:v.daCommonField.regionId,
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    areaLatitudeTypeCode:v.daCommonField.areaLatitudeTypeCode,
                    areaLatitudeTypeText:v.daCommonField.areaLatitudeTypeText,
                    sourceChannelTypeCode:v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
                    dataSources:v.daCommonField.dataSources,
                    dataTimeTypeCode:v.daCommonField.dataTimeTypeCode,
                    dataTimeTypeText:v.daCommonField.dataTimeTypeText,
                    auditStatusCode:v.daCommonField.auditStatusCode,          // 审核结果
                    auditStatusText:v.daCommonField.auditStatusText || "未审核",
                    identificationTypeText: v.identificationTypeText
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
          api.baseDataAudit.send(params).then(res=>{
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
            startTime,
            dataSources,
            regionName,
            name,
            identificationTypeText,
            owner,
            baseArea,
            baseTypeText,
            baseLongitude,
            baseLatitude,
            address,
            baseName,
            baseCode,
            auditStatusText
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
                        <label htmlFor="">所属企业名称：</label>
                        <span>{name}</span>
                    </li>
                    <li>
                        <label htmlFor="">基地编码：</label>
                        <span>{baseCode}</span>
                    </li>
                    <li>
                        <label htmlFor="">基地名称：</label>
                        <span>{baseName}</span>
                    </li>
                    <li>
                        <label htmlFor="">详细地址：</label>
                        <span>{address}</span>
                    </li>
                    <li>
                        <label style={{ fontSize: '12px' }} htmlFor="">基地坐标经度：</label>
                        <span>{baseLongitude}</span>
                    </li>
                    <li>
                        <label style={{ fontSize: '12px' }} htmlFor="">基地坐标纬度：</label>
                        <span>{baseLatitude}</span>
                    </li>
                    <li>
                        <label htmlFor="">基地类型：</label>
                        <span>{baseTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">基地面积：</label>
                        <span>{baseArea}</span>
                    </li>
                    {/* <li>
                        <label style={{ fontSize: '12px' }} htmlFor="">所属主体名称：</label>
                        <span>{owner}</span>
                    </li> */}
                    <li>
                        <label htmlFor="">认证类型：</label>
                        <span>{identificationTypeText}</span>
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