import React, { Component } from "react";
import * as api from '../api/api-forData.js';
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker,Cascader, Input, Button, message } from 'antd';
import moment from 'moment';
const Option = Select.Option;
const BMap=window.BMap;
export default class EnterpriseMessAdd extends Component {
    constructor(props) {
        super(props)
        this.state = {
            areaData:[],                                //  地理区域数据
            isAdd: props.match.params.id,
            timeDimensionData: [{ text: '全部', code: 0 }],   //  时间维度下拉列表
            originChannelData: [{ text: '全部', code: 0 }],   //  来源渠道下拉列表
            regionLevelData: [{ text: '全部', code: 0 }],     //  区域维度下拉列表
            enterpriseTypeData: [{ text: '全部', code: 0 }],//  企业类型下拉列表
            dataTimeTypeCode: 0,//  时间维度默认code
            dataTimeTypeText: "全部",
            sourceChannelTypeCode: 0,//来源渠道id
            sourceChannelTypeText: "全部",//来源渠道名称
            dataSources: "",//数据来源
            areaLatitudeTypeCode: 0,//区域维度id
            areaLatitudeTypeText: "全部",//区域维度名称
            regionId: "",//地理区域id
            regionName: "",//地理区域名称
            enterpriseName: "",//企业名称
            societyCreditId: "",//统一社会信用代码
            registrationMark: "",//注册号
            scaleEnterpriseTypeCode: 0,//规模企业类型code
            scaleEnterpriseTypeText: "是",//规模企业类型文本
            industryTypeCode: 0,//企业产业类型code
            industryTypeText: "全部",//企业产业类型文本
            enterpriseTypeCode: 0,//企业类型编码
            enterpriseTypeText: "全部",//企业类型名称
            corporationName: "",//法定代表人
            longdistanceNumber: "",//长途区号
            landlineTelephone: "",//固定电话
            corporationMobile: "",//负责人联系电话
            businessDuetime: "",//营业期限
            linkman: "",//联系人
            linkmanMobile: "",//联系人电话
            administrativeArea: "",//行政区划代码
            address: "",//详细地址
            registerTypeCode: 0,//登记注册类型code
            registerTypeText: "",//登记注册类型名称
            establishDate: "",//成立日期
            administrationNumber: "",//行政代码
            enterpriseControl: "",//企业控制情况
            businessScope: "",//经营范围
            professionalActivity: "",//主要业务活动
            registerStatusCode: 0,//登记状态code
            registerStatusText: "",//登记状态文本
            statisticsDepartmentNumber: "",//统计局代码
            industryActivity: "",//产业活动单位
            isCorporateChampionTypeCode: 0,//是否龙头企业code
            isCorporateChampionTypeText: "",//是否龙头企业文本
            measureLongitude: "",//经度
            measureLatitude: "",//纬度
            registeredCapital: "",//注册资本
            paidinCapital: "",//实收资本
            approvalDate: "", //核准日期
            archivesNumber: "",//档案号
            isCooperativeCode: '',
            isCooperativeText: '',
        }
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
    //修改前渲染
    initInput = (id) => {
        console.log(id)

        api.enterpriseMessBefore.send({ id: id }).then(res => {
            console.log(res)
            if (res.isSuccess) {
                let v = res.data;
                this.setState({
                    address: v.address,
                    administrationNumber: v.administrationNumber,
                    administrativeArea: v.administrativeArea,
                    approvalDate: v.approvalDate,
                    archivesNumber: v.archivesNumber,
                    businessDuetime: v.businessDuetime,
                    businessScope: v.businessScope,
                    corporationMobile: v.corporationMobile,
                    corporationName: v.corporationName,
                    areaLatitudeTypeCode: v.daCommonField.areaLatitudeTypeCode,
                    areaLatitudeTypeText: v.daCommonFieldareaLatitudeTypeText,
                    startTime: v.daCommonField.startTime,
                    dataSources: v.daCommonField.dataSources,
                    dataTimeTypeCode: v.daCommonField.dataTimeTypeCode,
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,
                    regionId: v.daCommonField.regionId,
                    regionName: v.daCommonField.regionName,
                    sourceChannelTypeCode: v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,
                    enterpriseControl: v.enterpriseControl,
                    enterpriseName: v.enterpriseName,
                    enterpriseTypeCode: v.enterpriseTypeCode,
                    enterpriseTypeText: v.enterpriseTypeText,
                    establishDate: v.establishDate,
                    industryActivity: v.industryActivity,
                    industryTypeCode: v.industryTypeCode,
                    industryTypeText: v.industryTypeText,
                    isCorporateChampionTypeCode: v.isCorporateChampionTypeCode,
                    isCorporateChampionTypeText: v.isCorporateChampionTypeText,
                    landlineTelephone: v.landlineTelephone,
                    linkman: v.linkman,
                    linkmanMobile: v.linkmanMobile,
                    longdistanceNumber: v.longdistanceNumber,
                    professionalActivity: v.professionalActivity,
                    registerStatusCode: v.registerStatusCode,
                    registerStatusText: v.registerStatusText,
                    registerTypeCode: v.registerTypeCode,
                    registerTypeText: v.registerTypeText,
                    registrationMark: v.registrationMark,
                    registeredCapital: v.registeredCapital,
                    paidinCapital: v.paidinCapital,
                    scaleEnterpriseTypeCode: v.scaleEnterpriseTypeCode,
                    scaleEnterpriseTypeText: v.scaleEnterpriseTypeText,
                    societyCreditId: v.societyCreditId,
                    statisticsDepartmentNumber: v.statisticsDepartmentNumber,
                    isCooperativeCode: v.isCooperativeCode,
                    isCooperativeText: v.isCooperativeText

                }, () =>
                //  地理区域数据方法
                this.areaDataGet()
            )
            } else {
                message.error('信息初始化失败！')
            }
        })
    }
    //  地理区域数据请求
    areaDataGet(){
        let params = {
            jsonData:{
                "entityRelated":{
                    "level":this.state.areaLatitudeTypeCode || ""
                }
            }
        }
        api.areaData.send(params).then(res=>{
            console.log(res)
            if(res.isSuccess){
                this.setState({
                    areaData:res.data
                })
            }
        })
    }
    //  地理区域选择
    areaFn=(value,selectedOptions)=> {
        console.log(selectedOptions)
        let name =[];
        selectedOptions.forEach((v,i)=>{
            name[i] = v.label
        })
        this.setState({
            regionId:value[value.length-1],
            regionName:name.join('/')
        },()=>{console.log(this.state.regionName)})
    }
    //增加
    addSave = () => {
        const { 
            dataTimeTypeCode, 
            dataTimeTypeText, 
            sourceChannelTypeCode, 
            sourceChannelTypeText, 
            dataSources,
            areaLatitudeTypeCode,
            areaLatitudeTypeText, 
            regionId,
            enterpriseName, 
            societyCreditId, 
            registrationMark,
            corporationName, 
            longdistanceNumber, 
            isCorporateChampionTypeCode, 
            isCorporateChampionTypeText, 
            landlineTelephone, 
            corporationMobile, 
            businessDuetime, 
            linkman, 
            linkmanMobile,
            administrativeArea, 
            address, 
            regionName,
            registerTypeCode, 
            registerTypeText,
            administrationNumber, 
            enterpriseControl, 
            businessScope, 
            professionalActivity,
            registerStatusCode, 
            statisticsDepartmentNumber, 
            isAdd,
            approvalDate,
            archivesNumber,
            establishDate,
            startTime,
            measureLongitude, 
            registeredCapital,
            scaleEnterpriseTypeCode,
            scaleEnterpriseTypeText, 
            paidinCapital,
            industryActivity,
            measureLatitude,
            enterpriseTypeCode,
            enterpriseTypeText,
            isCooperativeCode,
            isCooperativeText
        } = this.state
        let params = {
            "address": address,
            "administrationNumber": administrationNumber,
            "administrativeArea": administrativeArea,
            "approvalDate": approvalDate,
            "archivesNumber": archivesNumber,
            "businessDuetime": businessDuetime,
            "businessScope": businessScope,
            "corporationMobile": corporationMobile,
            "corporationName": corporationName,
            "measureLongitude": measureLongitude,//经度
            "measureLatitude": measureLatitude,//纬度
            "registeredCapital": registeredCapital,//注册资本
            "paidinCapital": paidinCapital,//注册资本
            "daCommonField": {
                "areaLatitudeTypeCode": areaLatitudeTypeCode,
                "areaLatitudeTypeText": areaLatitudeTypeText,
                "startTime": startTime,
                "dataSources": dataSources,
                "dataTimeTypeCode": dataTimeTypeCode,
                "dataTimeTypeText": dataTimeTypeText,
                "regionId": regionId,
                "regionName": regionName,
                "sourceChannelTypeCode": sourceChannelTypeCode,
                "sourceChannelTypeText": sourceChannelTypeText
            },
            "enterpriseControl": enterpriseControl,
            "enterpriseName": enterpriseName,
            "enterpriseTypeCode": enterpriseTypeCode,
            "enterpriseTypeText": enterpriseTypeText,
            "establishDate": establishDate,
            "industryActivity": industryActivity,
            "industryTypeCode": "",
            "industryTypeText": "",
            "isCorporateChampionTypeCode": isCorporateChampionTypeCode,
            "isCorporateChampionTypeText": isCorporateChampionTypeText,
            "landlineTelephone": landlineTelephone,
            "linkman": linkman,
            "linkmanMobile": linkmanMobile,
            "longdistanceNumber": longdistanceNumber,
            "professionalActivity": professionalActivity,
            "registerStatusCode": registerStatusCode,
            "registerStatusText": "",
            "registerTypeCode": registerTypeCode,
            "registerTypeText": registerTypeText,
            "registrationMark": registrationMark,
            "scaleEnterpriseTypeCode": scaleEnterpriseTypeCode,
            "scaleEnterpriseTypeText": scaleEnterpriseTypeText, 
            "societyCreditId": societyCreditId,
            "statisticsDepartmentNumber": statisticsDepartmentNumber,
            "isCooperativeCode": isCooperativeCode,
            "isCooperativeText": isCooperativeText
        }
        if (Number(isAdd) === 1) {
            api.enterpriseMessAdd.send(params).then(res => {
                console.log(res)
                if (res.isSuccess) {
                    message.success('上报成功！');
                    //  返回上一页
                    this.goBackPage();
                } else {
                    message.error('上报失败！');
                }
            })
        } else {
            let id = isAdd.split('-')[0];
            let commonId = isAdd.split('-')[1];
            params.id = id;
            params.daCommonField.id = commonId;
            api.enterpriseMessUpdate.send(params).then(res => {
                console.log(res)
                if (res.isSuccess) {
                    message.success('修改成功！');

                    delete params.daCommonField.id;
                    delete params.id;
                    //  返回上一页
                    this.goBackPage();
                } else {
                    message.error('修改失败！');
                }
            })
        }
    }
    componentDidMount() {

        if (Number(this.state.isAdd) !== 1) {

            let id = this.state.isAdd;
            this.initInput(id.split('-')[0])
        }else{
            //  地理区域数据方法
            this.areaDataGet()
        }
        //  时间维度下拉方法
        this.initTimeSelect()
        //  来源渠道下拉方法
        this.initOriginChannel()
        //  区域为度下拉方法
        this.initRegionLevel()
        //企业类型下拉
        this.initEnterpriseType()
    }
    
    // 数据日期
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
             startTime:dateString+' 00:00:00'
        })
    }
    //  成立日期
    timeSelecteStablish=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            establishDate:dateString+' 00:00:00'
        })
    }
    //  核准日期
    timeSelecteApprovalDate=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            approvalDate:dateString+' 00:00:00'
        })
    }
    //  核准日期
    timeSelecteBusinessDuetime=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            businessDuetime:dateString+' 00:00:00'
        })
    }
    //  下拉获取值通用方法
    handleSelect = (e, option) => {
        // console.log(e,option)
        let type = option.props.typename;
        let code = option.props.codenum;
        let text = option.props.children;
        if(code === "areaLatitudeTypeCode"){
            this.areaDataGet()
        }
        this.setState({
            [type]: text,
            [code]: e
        }, () => {
            console.log(this.state[type],this.state[code])
        })
    }
    //  时间维度下拉方法
    initTimeSelect() {
        api.timeDimension.send().then(res => {
            // console.log(res)
            let timeDimensionNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    timeDimensionNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    timeDimensionData: this.state.timeDimensionData.concat(timeDimensionNew)
                }, () => {
                    // console.log(this.state.timeDimensionData)
                })
            }
        })
    }
    //  来源渠道下拉方法
    initOriginChannel() {
        api.originChannel.send().then(res => {
            // console.log(res)
            let originChannelNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    originChannelNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    originChannelData: this.state.originChannelData.concat(originChannelNew)
                }, () => {
                    // console.log(this.state.originChannelData)
                })
            }
        })
    }
    //  区域为度下拉方法
    initRegionLevel() {
        api.regionLevel.send().then(res => {
            // console.log(res)
            let regionLevelNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    regionLevelNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    regionLevelData: this.state.regionLevelData.concat(regionLevelNew)
                }, () => {
                    // console.log(this.state.regionLevelData)
                })
            }
        })
    }
    //企业类型下拉
    initEnterpriseType() {
        api.enterpriseType.send().then(res => {
            // console.log(res)
            let enterpriseTypeNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    enterpriseTypeNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    enterpriseTypeData: this.state.enterpriseTypeData.concat(enterpriseTypeNew)
                }, () => {
                    // console.log(this.state.regionLevelData)
                })
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
    //  获取经纬度
    getPoint=(address)=>{
        //  输入为空时重置经纬度
        if(!address) {
            this.setState({
                measureLatitude: "",                           //基地坐标纬度
                measureLongitude: "",                          //基地坐标经度
            },
                // ()=>{console.log(this.state.baseLatitude,this.state.baseLongitude)}
            )
            return;
        }
        // 创建地址解析器实例
        let myGeo = new BMap.Geocoder();
        // 获取坐标
        myGeo.getPoint(address, (point)=>{
            let measureLatitude= point?point.lat:'' ;  //  纬度
            let measureLongitude= point?point.lng:'' ; //  经度
            if(point){
                this.setState({
                    measureLatitude: measureLatitude,                           //基地坐标纬度
                    measureLongitude: measureLongitude,                          //基地坐标经度
                },
                    // ()=>{console.log(this.state.baseLatitude,this.state.baseLongitude)}
                )
            }else{
                message.error('请输入有效地址！')
                this.setState({
                    measureLatitude: "",                           //基地坐标纬度
                    measureLongitude: "",                          //基地坐标经度
                },
                    // ()=>{console.log(this.state.baseLatitude,this.state.baseLongitude)}
                )
            }
        });
    }
    render() {
        const { 
            dataTimeTypeCode,
            sourceChannelTypeCode, 
            dataSources, 
            areaLatitudeTypeCode, 
            regionLevelData, 
            timeDimensionData,
            enterpriseName, 
            societyCreditId, 
            registrationMark, 
            corporationName, 
            registeredCapital,
            paidinCapital,
            longdistanceNumber, 
            originChannelData,
            landlineTelephone, 
            corporationMobile, 
            businessDuetime, 
            linkman, 
            linkmanMobile, 
            administrativeArea, 
            startTime,
            address, 
            registerTypeCode, 
            administrationNumber, 
            enterpriseControl, 
            businessScope, 
            professionalActivity,
            registerStatusCode, 
            statisticsDepartmentNumber, 
            measureLatitude, 
            approvalDate,
            archivesNumber,
            industryActivity, 
            measureLongitude, 
            enterpriseTypeCode, 
            enterpriseTypeData,
            scaleEnterpriseTypeCode,
            isCorporateChampionTypeCode,
            establishDate,
            regionName,
            areaData
        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit2 clearfix">
                    {/* <li>
                        <label htmlFor="">时间维度：</label>
                        <Select onChange={this.handleSelect} value={dataTimeTypeCode} defaultValue={timeDimensionData[0].code} style={{ width: '65%' }}>
                            {
                                timeDimensionData.map((v, i) => {
                                    return (
                                        <Option codenum={'dataTimeTypeCode'} typename={'dataTimeTypeText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li> */}
                    <li>
                        <label htmlFor="">数据日期：</label>
                        <DatePicker locale={locale} allowClear={false} value={startTime ? moment(new Date(startTime)) : null} onChange={this.timeSelect} style={{ width: '65%' }} format="YYYY-MM-DD"/>
                    </li>
                    {/* <li>
                        <label htmlFor="">来源渠道：</label>
                        <Select onChange={this.handleSelect} defaultValue={originChannelData[0].code} value={sourceChannelTypeCode} style={{ width: '65%' }}>
                            {
                                originChannelData.map((v, i) => {
                                    return (
                                        <Option codenum={'sourceChannelTypeCode'} typename={'sourceChannelTypeText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li> */}
                    <li className="wid-plus">
                        <label htmlFor="">数据来源：</label>
                        <Input onChange={this.inputHandle} data-type="dataSources" style={{ width: '60%' }} value={dataSources} defaultValue={dataSources} />
                    </li>
                    {/* <li>
                        <label htmlFor="">区域维度：</label>
                        <Select value={areaLatitudeTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                            {
                                regionLevelData.map((v, i) => {
                                    return (
                                        <Option codenum={'areaLatitudeTypeCode'} typename={'areaLatitudeTypeText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li> */}
                    <li>
                        <label htmlFor="">地理区域：</label>
                        <Cascader 
                            locale={locale}
                            placeholder={regionName || '请选择'}
                            style={{ width: '65%' }}  
                            options={areaData} 
                            onChange={this.areaFn} 
                            />
                    </li>
                    <li>
                        <label htmlFor="">企业名称：</label>
                        <Input onChange={this.inputHandle} data-type="enterpriseName" style={{ width: '65%' }} value={enterpriseName} defaultValue={enterpriseName} />
                    </li>
                    <li className="wid-plus">
                        <label htmlFor="">统一社会信用代码：</label>
                        <Input value={societyCreditId} data-type="societyCreditId" onChange={this.inputHandle} style={{ width: '60%' }} defaultValue=" " />
                    </li>
                    <li>
                        <label htmlFor="">注册号：</label>
                        <Input value={registrationMark} data-type="registrationMark" onChange={this.inputHandle} style={{ width: '65%' }} defaultValue=" " />
                    </li>
                    <li>
                        <label htmlFor="">企业类型：</label>
                        <Select defaultValue={enterpriseTypeData[0].code} value={enterpriseTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                            {
                                enterpriseTypeData.map((v, i) => {
                                    return (
                                        <Option codenum={'enterpriseTypeCode'} typename={'enterpriseTypeText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li className="position">
                        <label htmlFor="">注册资本：</label>
                        <Input onChange={this.inputHandle} data-type="registeredCapital" style={{ width: '65%' }} value={registeredCapital} />
                        <i>万元</i>
                    </li>
                    <li className="wid-plus">
                        <label className="small" htmlFor="">法定代表/负责人姓名：</label>
                        <Input value={corporationName} data-type="corporationName" onChange={this.inputHandle} style={{ width: '60%' }} defaultValue=" " />
                    </li>
                    {/* <li>
                        <label htmlFor="">长途区号：</label>
                        <Input value={longdistanceNumber} data-type="longdistanceNumber" onChange={this.inputHandle} style={{ width: '65%' }} defaultValue=" " />
                    </li>
                    <li>
                        <label htmlFor="">固定电话：</label>
                        <Input value={landlineTelephone} data-type="landlineTelephone" onChange={this.inputHandle} style={{ width: '65%' }} defaultValue=" " />
                    </li> */}
                    <li className="position">
                        <label htmlFor="">实收资本：</label>
                        <Input onChange={this.inputHandle} data-type="paidinCapital" style={{ width: '65%' }} value={paidinCapital} />
                        <i>万元</i>
                    </li>
                    <li className="wid-plus">
                        <label className="small" htmlFor="">法定代表/负责人电话：</label>
                        <Input value={corporationMobile} data-type="corporationMobile" onChange={this.inputHandle} style={{ width: '60%' }} defaultValue=" " />
                    </li>
                    <li>
                        <label htmlFor="">营业期限：</label>
                        <DatePicker locale={locale} allowClear={false} value={businessDuetime ? moment(new Date(businessDuetime)) : null} onChange={this.timeSelecteBusinessDuetime} style={{ width: '65%' }} format="YYYY-MM-DD"/>
                    </li>
                    <li>
                        <label htmlFor="">联系人：</label>
                        <Input value={linkman} data-type="linkman" onChange={this.inputHandle} style={{ width: '65%' }} defaultValue=" " />
                    </li>
                    <li>
                        <label htmlFor="">联系人电话：</label>
                        <Input value={linkmanMobile} data-type="linkmanMobile" onChange={this.inputHandle} style={{ width: '65%' }} />
                    </li>
                    {/* <li className="wid-plus">
                        <label htmlFor="">行政区域代码：</label>
                        <Input value={administrativeArea} data-type="administrativeArea" onChange={this.inputHandle} style={{ width: '60%' }} />
                    </li> */}
                    <li>
                        <label htmlFor="">详细地址：</label>
                        <Input value={address} onBlur={this.getPoint.bind(undefined, address)} data-type="address" onChange={this.inputHandle} style={{ width: '65%' }} />
                    </li>
                    <li>
                        <label htmlFor="">经度：</label>
                        <Input value={measureLongitude} data-type="measureLongitude" onChange={this.inputHandle} style={{ width: '65%' }} readOnly />
                    </li><li>
                        <label htmlFor="">纬度：</label>
                        <Input value={measureLatitude} data-type="measureLatitude" onChange={this.inputHandle} style={{ width: '65%' }} readOnly />
                    </li>
                    {/* <li className="wid-plus">
                        <label htmlFor="">登记注册类型：</label>
                        <Input value={registerTypeCode} data-type="registerTypeCode" onChange={this.inputHandle} style={{ width: '60%' }} />
                    </li> */}
                    <li>
                        <label htmlFor="">成立日期：</label>
                        <DatePicker locale={locale} allowClear={false} value={establishDate ? moment(new Date(establishDate)) : null} onChange={this.timeSelecteStablish} style={{ width: '65%' }} format="YYYY-MM-DD"/>
                    </li>
                    {/* <li>
                        <label htmlFor="">行政代码：</label>
                        <Input value={administrationNumber} data-type="administrationNumber" onChange={this.inputHandle} style={{ width: '65%' }} defaultValue=" " />
                    </li> */}
                    <li>
                        <label htmlFor="">开业日期：</label>
                        <DatePicker locale={locale} allowClear={false} value={approvalDate ? moment(new Date(approvalDate)) : null} onChange={this.timeSelecteApprovalDate} style={{ width: '65%' }} format="YYYY-MM-DD"/>
                    </li>
                    {/* <li className="wid-plus">
                        <label htmlFor="">企业控制情况：</label>
                        <Input value={enterpriseControl} data-type="enterpriseControl" onChange={this.inputHandle} style={{ width: '60%' }} />
                    </li> */}
                    <li className="wid-plus2">
                        <label htmlFor="">经营范围：</label>
                        <Input value={businessScope} data-type="businessScope" onChange={this.inputHandle} style={{ width: '85%' }} />
                    </li>
                    {/* <li className="wid-plus">
                        <label htmlFor="">主要业务活动：</label>
                        <Input value={professionalActivity} data-type="professionalActivity" onChange={this.inputHandle} style={{ width: '60%' }}  />
                    </li> */}
                    {/* <li>
                        <label htmlFor="">登记状态：</label>
                        <Input value={registerStatusCode} data-type="registerStatusCode" onChange={this.inputHandle} style={{ width: '65%' }} />
                    </li> */}
                    <li>
                        <label htmlFor="">是否合作社：</label>
                        <Select defaultValue="是" style={{ width: '65%' }} onChange={this.handleChange}>
                            <Option value="0" codenum={'isCooperativeCode'} typename={'isCooperativeText'}>是</Option>
                            <Option value="1" codenum={'isCooperativeCode'} typename={'isCooperativeText'}>否</Option>
                        </Select>
                    </li>
                    {/* <li>
                        <label htmlFor="">统计局代码：</label>
                        <Input value={statisticsDepartmentNumber} data-type="statisticsDepartmentNumber" onChange={this.inputHandle} style={{ width: '65%' }} />
                    </li>
                    <li className="wid-plus">
                        <label htmlFor="">产业活动单位：</label>
                        <Input value={industryActivity} data-type="industryActivity" onChange={this.inputHandle} style={{ width: '60%' }} />
                    </li> */}
                    {/* <li>
                        <label htmlFor="">档案号：</label>
                        <Input value={archivesNumber} data-type="archivesNumber" onChange={this.inputHandle} style={{ width: '60%' }} />
                    </li> */}
                    <li>
                        <label htmlFor="">龙头企业：</label>
                        <Select value={isCorporateChampionTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                            <Option codenum={'isCorporateChampionTypeCode'} typename={'isCorporateChampionTypeText'} value={0}>是</Option>
                            <Option codenum={'isCorporateChampionTypeCode'} typename={'isCorporateChampionTypeText'} value={1}>否</Option>
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">规模企业：</label>
                        <Select value={scaleEnterpriseTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                            <Option codenum={'scaleEnterpriseTypeCode'} typename={'scaleEnterpriseTypeText'} value={0}>是</Option>
                            <Option codenum={'scaleEnterpriseTypeCode'} typename={'scaleEnterpriseTypeText'} value={1}>否</Option>
                        </Select>
                    </li>
                    {/* <li className="wid-plus">
                        <label htmlFor="">所属产业类型：</label>
                        <Select defaultValue="好" style={{ width: '60%' }} onChange={this.handleChange}>
                            <Option value="好">好</Option>
                            <Option value="差">差</Option>
                            <Option value="disabled" disabled>禁用</Option>
                            <Option value="完全不会">完全不会</Option>
                        </Select>
                    </li> */}
                    <li>
                        <Button type="primary" onClick={this.addSave}>上报</Button>
                        <Button onClick={this.goBackPage}>取消</Button>
                    </li>
                </ul>
            </div>
        )
    }
}