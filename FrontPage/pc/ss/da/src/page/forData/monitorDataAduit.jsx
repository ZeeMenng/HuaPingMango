import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
// import locale from 'antd/lib/date-picker/locale/zh_CN';
import * as api from "../api/api-forData-second";
import { Select, Input, Button, message } from 'antd';
const Option = Select.Option;

export default class MonitorDataAduit extends Component {
    constructor(props) {
        super(props)
        this.state = {
            isAdd: props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            auditStatusCode: 1,                          //审核结果
            auditStatusText: '通过',
        }
    }
    //  渲染数据
    initInput = (id) => {
        console.log(id)
        let prId = id.split('-')[0];
        api.monitorDataSearch.send({ id: prId }).then(res => {
            console.log(res)
            if (res.isSuccess) {
                let v = res.data;
                this.setState({
                    checkDate: v.checkDate,//检测时间
                    checkOrg: v.checkOrg,//检测机构
                    checkPersonnel: v.checkPersonnel,//检测人员
                    checkProject: v.checkProject,//检测项目
                    checkResult: v.checkResult,//检测结果
                    cropTypeCode: v.cropTypeCode,//样品种类code
                    cropTypeText: v.cropTypeText,//样品种类文本
                    areaLatitudeTypeCode: v.daCommonField.areaLatitudeTypeCode,//区域维度id
                    areaLatitudeTypeText: v.daCommonField.areaLatitudeTypeText,//区域维度名称
                    startTime: v.daCommonField.startTime,//数据日期
                    dataSources: v.daCommonField.dataSources,//数据来源
                    dataTimeTypeCode: v.daCommonField.dataTimeTypeCode,//时间维度id
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,//时间维度名称
                    regionId: v.daCommonField.regionId,//地理区域id
                    regionName: v.daCommonField.regionName,//地理区域名称
                    sourceChannelTypeCode: v.daCommonField.sourceChannelTypeCode,//来源渠道id
                    remark: v.daCommonField.remark,//备注
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,//来源渠道名称
                    name: v.name,//数据主体名称
                    sampleNo: v.sampleNo,//样品编号
                    sampleName: v.sampleName,//样品名称
                    strainsCode: v.strainsCode,//样品品种code
                    strainsText: v.strainsText//样品品种文本
                }, () => console.log(this.state))
            } else {
                message.error('信息初始化失败！')
            }
        })
    }
    //  保存方法
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
        api.monitorDataAudit.send(params).then(res => {
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
    render() {
        const {
            dataTimeTypeText, startTime, sourceChannelTypeText, dataSources, areaLatitudeTypeText,
            cropTypeText, strainsText, sampleName, sampleNo, checkDate, checkProject, checkResult,
            checkOrg, checkPersonnel, auditStatusText
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
                        <span>云南省丽江市华坪县</span>
                    </li>
                    <li>
                        <label htmlFor="">样品种类：</label>
                        <span>{cropTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">样品品种：</label>
                        <span>{strainsText}</span>
                    </li>
                    <li>
                        <label htmlFor="">样品名称：</label>
                        <span>{sampleName}</span>
                    </li>
                    <li>
                        <label htmlFor="">样品编号：</label>
                        <span>{sampleNo}</span>
                    </li>
                    <li>
                        <label htmlFor="">检测时间：</label>
                        <span>{checkDate}</span>
                    </li>
                    <li>
                        <label htmlFor="">检测项目：</label>
                        <span>{checkProject}</span>
                    </li>
                    <li>
                        <label htmlFor="">检测结果：</label>
                        <span>{checkResult}</span>
                    </li>
                    <li>
                        <label htmlFor="">检测机构：</label>
                        <span>{checkOrg}</span>
                    </li>
                    <li>
                        <label htmlFor="">检测人员：</label>
                        <span>{checkPersonnel}</span>
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