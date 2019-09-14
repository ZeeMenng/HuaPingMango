import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
// import locale from 'antd/lib/date-picker/locale/zh_CN';
import * as api from "../api/api-forData";
import { Select, message, Input, Button } from 'antd';
import moment from 'moment';
const Option = Select.Option;

export default class FarmerMessAduit extends Component {
    constructor(props){
        super(props)
        this.state = {
            isAdd:props.match.params.id,                //  地址栏传参
            // dataTimeTypeText:"全部",                    //  时间维度名称
            // sourceChannelTypeText:"全部",               //  来源渠道默认名称
            // peasantTypeText:"全部",                     //  农户类型默认名称
            // mobileOperateText:"全部",                   //  手机水平默认名称
	        // areaLatitudeTypeText: "全部",               //  区域维度名称
            // startTime:"",                               //  数据日期
            // dataSources: "",                            //  数据来源
            // address: "",                                //  详细地址
            // userName: "",                               //  户主姓名
            // idNumber: "",                               //  身份证号
            // mobileType: "",                             //  智能手机型号
            // phone:"",                                   //  联系电话
            // regionName:"",                              //  区域名称
            // addTime:"",                                  //  采集时间
            auditStatusCode:1,                              //审核结果
            auditStatusText:'通过',
            // auditerSuggestion:""                        //  审核意见
        }
    }
    //  渲染数据
    initList=(id)=>{
        let v = id.split('-')[0]
        api.FrameMessFixBefore.send({id:v}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    dataTimeTypeText:v.daCommonField.dataTimeTypeText,                   //  时间维度名称
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,              //  来源渠道默认名称
                    peasantTypeText:v.peasantTypeText,                     //  农户类型默认名称
                    mobileOperateText:v.mobileOperateText,                   //  手机水平默认名称
                    areaLatitudeTypeText: v.daCommonField.areaLatitudeTypeText,               //  区域维度名称
                    startTime:v.daCommonField.startTime,                               //  数据日期
                    dataSources: v.daCommonField.dataSources,                            //  数据来源
                    address: v.address,                                //  详细地址
                    userName: v.user.userName,                               //  户主姓名
                    idNumber: v.idNumber,                               //  身份证号
                    mobileType: v.mobileType,                             //  智能手机型号
                    phone:v.user.phone,                                   //  联系电话
                    regionName:v.daCommonField.regionName,                              //  区域名称
                    addTime:v.daCommonField.addTime                                  //  采集时间
                },()=>console.log(this.state))
            }else{
                message.error('信息初始化失败！')
            }
        })
    }
    formSaveFn = () =>{
        const {
            isAdd,
            auditStatusCode,
            auditStatusText,
            auditerSuggestion
        } =this.state
        let id = isAdd.split('-');
        let params = {
            "entity": {
              "daCommonField": {
                "auditStatusCode": auditStatusCode,
                "auditStatusText": auditStatusText,
                "auditerSuggestion": auditerSuggestion,
                "id": id[1]
              }
            },
            "idList": [
                id[0]
            ]
          }
          api.FrameMessAudit.send(params).then(res=>{
              console.log(res)
              if(res.isSuccess){
                  message.success('上报成功！')
                  //   返回上一页
                  this.goBackPage()
              }
          })
    }
    componentDidMount(){
        //  初始化数据
        this.initList(this.state.isAdd)
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
    render() {
        const {
            dataTimeTypeText,                   //  时间维度名称
            sourceChannelTypeText,              //  来源渠道默认名称
            peasantTypeText,                     //  农户类型默认名称
            mobileOperateText,                   //  手机水平默认名称
            areaLatitudeTypeText,               //  区域维度名称
            startTime,                               //  数据日期
            dataSources,                            //  数据来源
            address,                                //  详细地址
            userName,                               //  户主姓名
            idNumber,                               //  身份证号
            mobileType,                             //  智能手机型号
            phone,                                   //  联系电话
            regionName,                              //  区域名称
            addTime
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
                        <span>{startTime ? moment(startTime).format('YYYY-MM-DD') : ''}</span>
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
                        <label htmlFor="">详细地址：</label>
                        <span>{address}</span>
                    </li>
                    <li>
                        <label htmlFor="">姓名：</label>
                        <span>{userName}</span>
                    </li>
                    <li>
                        <label htmlFor="">身份证号：</label>
                        <span>{idNumber}</span>
                    </li>
                    <li>
                        <label htmlFor="">手机水平：</label>
                        <span>{mobileOperateText}</span>
                    </li>
                    <li>
                        <label htmlFor="">智能手机：</label>
                        <span>{mobileType}</span>
                    </li>
                    <li>
                        <label htmlFor="">联系电话：</label>
                        <span>{phone}</span>
                    </li>
                    <li>
                        <label htmlFor="">农户类型：</label>
                        <span>{peasantTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">所属合作社：</label>
                        <span>年</span>
                    </li>

                    <li>
                        <label htmlFor="">采集时间：</label>
                        <span>{addTime}</span>
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
                        <Select onChange={this.handleSelect} defaultValue="1" style={{ width: '65%' }} >
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