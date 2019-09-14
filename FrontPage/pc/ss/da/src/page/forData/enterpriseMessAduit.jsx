import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import * as api from '../api/api-forData.js';
import { Select, Input, Button, message } from 'antd';
const Option = Select.Option;

export default class EnterpriseMessAduit extends Component {
    constructor(props) {
        super(props)
        this.state = {
            isAdd: props.match.params.id,
            auditStatusCode: 1,                              //审核结果
            auditStatusText: '通过',
        }
    }
    initInput = (id) => {
        console.log(id)
        let prId = id.split('-')[0];
        api.enterpriseMessBefore.send({ id: prId }).then(res => {
            console.log(res)
            if (res.isSuccess) {
                let v = res.data;
                this.setState({
                    address: v.address,
                    administrationNumber: v.administrationNumber,
                    administrativeArea: v.administrativeArea,
                    approvalDate: "2018-07-25 54:59:00",
                    archivesNumber: "string",
                    businessDuetime: v.businessDuetime,
                    businessScope: v.businessScope,
                    corporationMobile: v.corporationMobile,
                    corporationName: v.corporationName,
                    areaLatitudeTypeCode: v.daCommonField.areaLatitudeTypeCode,
                    areaLatitudeTypeText: v.daCommonFieldareaLatitudeTypeText,
                    startTime: "2018-07-25 54:59:00",
                    dataSources: v.daCommonField.dataSources,
                    dataTimeTypeCode: v.daCommonField.dataTimeTypeCode,
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,
                    regionId: v.daCommonField.regionId,
                    regionName: "string",
                    sourceChannelTypeCode: v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,
                    enterpriseControl: v.enterpriseControl,
                    enterpriseName: v.enterpriseName,
                    enterpriseTypeCode: 0,
                    enterpriseTypeText: "string",
                    establishDate: "2018-07-25 54:59:00",
                    industryActivity: v.industryActivity,
                    industryTypeCode: 0,
                    industryTypeText: "string",
                    isCorporateChampionTypeCode: 0,
                    isCorporateChampionTypeText: "string",
                    landlineTelephone: v.landlineTelephone,
                    linkman: v.linkman,
                    linkmanMobile: v.linkmanMobile,
                    longdistanceNumber: v.longdistanceNumber,
                    professionalActivity: v.professionalActivity,
                    registerStatusCode: v.registerStatusCode,
                    registerStatusText: "string",
                    registerTypeCode: v.registerTypeCode,
                    registerTypeText: "string",
                    registrationMark: v.registrationMark,
                    scaleEnterpriseTypeCode: 0,
                    scaleEnterpriseTypeText: "string",
                    societyCreditId: v.societyCreditId,
                    statisticsDepartmentNumber: v.statisticsDepartmentNumber

                }, () => console.log(this.state))
            } else {
                message.error('信息初始化失败！')
            }
        })
    }
    formSaveFn = () => {
        const {
            isAdd,
            auditStatusCode,
            auditStatusText,
            auditerSuggestion
        } = this.state
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
        api.enterpriseMessAduit.send(params).then(res => {
            console.log(res)
            if (res.isSuccess) {
                message.success('上报成功！')
                //   返回上一页
                this.goBackPage()
            }
        })
    }
    componentDidMount() {
        //  初始化数据
        this.initInput(this.state.isAdd)
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
    goBackPage = () => {
        this.props.history.go(-1)
    }
    render() {
        const { dataTimeTypeText, startTime, sourceChannelTypeText, dataSources, areaLatitudeTypeText, societyCreditId,
            enterpriseName, registrationMark, scaleEnterpriseTypeText, corporationName, corporationMobile, administrativeArea, address, establishDate,
            longdistanceNumber, landlineTelephone, businessDuetime, linkman, linkmanMobile, administrationNumber, businessScope,
            professionalActivity, registerTypeText, enterpriseControl, registerStatusText, statisticsDepartmentNumber, industryActivity,
            approvalDate, archivesNumber, industryTypeText, isCorporateChampionTypeText

        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit form-detail clearfix">
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
                        <span>云南省丽江市华坪县</span>
                    </li>
                    <li>
                        <label className="small" htmlFor="">统一社会信用代码：</label>
                        <span>{societyCreditId}</span>
                    </li>
                    <li>
                        <label htmlFor="">企业名称：</label>
                        <span>{enterpriseName}</span>
                    </li>
                    <li>
                        <label htmlFor="">注册号：</label>
                        <span>{registrationMark}</span>
                    </li>
                    <li>
                        <label htmlFor="">企业类型：</label>
                        <span>{scaleEnterpriseTypeText}</span>
                    </li>
                    <li>
                        <label className="small" htmlFor="">法定代表/负责人：</label>
                        <span>{corporationName}</span>
                    </li>
                    <li>
                        <label className="small" htmlFor="">负责人电话：</label>
                        <span>{corporationMobile}</span>
                    </li>
                    <li>
                        <label htmlFor="">注册资本：</label>
                        <span>12万元</span>
                    </li>
                    <li>
                        <label htmlFor="">实收资本：</label>
                        <span>12万元</span>
                    </li>
                    <li>
                        <label htmlFor="">行政区域代码：</label>
                        <span>{administrativeArea}</span>
                    </li>
                    <li>
                        <label htmlFor="">详细地址：</label>
                        <span>{address}</span>
                    </li>
                    <li>
                        <label htmlFor="">成立日期：</label>
                        <span>{establishDate}</span>
                    </li>
                    <li>
                        <label htmlFor="">长途区号：</label>
                        <span>{longdistanceNumber}</span>
                    </li>
                    <li>
                        <label htmlFor="">固定电话：</label>
                        <span>{landlineTelephone}</span>
                    </li>
                    <li>
                        <label htmlFor="">营业期限：</label>
                        <span>{businessDuetime}</span>
                    </li>
                    <li>
                        <label htmlFor="">联系人：</label>
                        <span>{linkman}</span>
                    </li>
                    <li>
                        <label htmlFor="">联系人电话：</label>
                        <span>{linkmanMobile}</span>
                    </li>
                    <li>
                        <label htmlFor="">行政代码：</label>
                        <span>{administrationNumber}</span>
                    </li>
                    <li>
                        <label htmlFor="">经营范围：</label>
                        <span>{businessScope}</span>
                    </li>
                    <li>
                        <label htmlFor="">主要活动业务：</label>
                        <span>{professionalActivity}</span>
                    </li>
                    <li>
                        <label htmlFor="">登记注册类型：</label>
                        <span>{registerTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">企业控制情况：</label>
                        <span>{enterpriseControl}</span>
                    </li>
                    <li>
                        <label htmlFor="">开业日期：</label>
                        <span>2017-11-01</span>
                    </li>
                    <li>
                        <label htmlFor="">登记状态：</label>
                        <span>{registerStatusText}</span>
                    </li>
                    <li>
                        <label htmlFor="">是否合作社：</label>
                        <span>2017-11-01</span>
                    </li>
                    <li>
                        <label htmlFor="">统计局代码：</label>
                        <span>{statisticsDepartmentNumber}</span>
                    </li>
                    <li>
                        <label htmlFor="">产业活动单位：</label>
                        <span>{industryActivity}</span>
                    </li>
                    <li>
                        <label htmlFor="">核准日期：</label>
                        <span>{approvalDate}</span>
                        
                    </li>
                    <li>
                        <label htmlFor="">档案号：</label>
                        <span>{archivesNumber}</span>
                    </li>
                    <li>
                        <label htmlFor="">企业产业类型：</label>
                        <span>{industryTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">是否龙头企业：</label>
                        <span>{isCorporateChampionTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">是否规模企业：</label>
                        <span>2017-11-01</span>
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