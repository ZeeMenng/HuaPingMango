import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select,Cascader, DatePicker,message,  Input, Button } from 'antd';
import moment from 'moment';
import * as api from '../api/api-forData.js';
const Option = Select.Option;
export default class FarmerMessAdd extends Component {
    constructor(props){
        super(props)
        this.state={
            areaData:[],                                //  地理区域数据
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            timeDimensionData:[{text:'全部',code:0}],   //  时间维度下拉列表
            originChannelData:[{text:'全部',code:0}],   //  来源渠道下拉列表
            frameTypeData:[{text:'全部',code:0}],       //  农户类型下拉列表
            mobileOperateData:[{text:'全部',code:0}],   //  手机水平下拉列表
            regionLevelData:[{text:'全部',code:0}],     //  区域维度下拉列表
            dataTimeTypeCode:0,                         //  时间维度默认code
            dataTimeTypeText:"全部",                    //  时间维度名称
            sourceChannelTypeCode:0,                    //  来源渠道默认code
            sourceChannelTypeText:"全部",                    //  来源渠道默认名称
            peasantTypeCode:0,                          //  农户类型默认code
            peasantTypeText:"全部",                     //  农户类型默认名称
            mobileOperateCode:0,                        //  手机水平默认code
            mobileOperateText:"全部",                   //  手机水平默认名称
            areaLatitudeTypeCode: 0,                    //  区域维度id
	        areaLatitudeTypeText: "全部",               //  区域维度名称
            startTime:"",                               //  数据日期
            dataSources: "",                            //  数据来源
            address: "",                                //  详细地址
            userName: "",                               //  户主姓名
            idNumber: "",                               //  身份证号
            mobileType: "",                             //  智能手机型号
            phone:"",                                   //  联系电话
            regionId:"" ,                                //  区域id
            regionName:"",                              //  区域名称
            enterpriseName:""
        }
    }
    //  修改时重新渲染数据
    initInput=(id)=>{
        console.log(id)

        api.FrameMessFixBefore.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    address:v.address,
                    mobileOperateCode:v.mobileOperateCode,
                    mobileOperateText:v.mobileOperateText,
                    mobileType:v.mobileType,
                    peasantTypeCode:v.peasantTypeCode,
                    peasantTypeText:v.peasantTypeText,
                    userName:v.user.userName,
                    phone:v.user.phone,
                    idNumber:v.idNumber,
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
                    enterpriseName: v.enterpriseName
                },()=>
                //  地理区域数据方法
                this.areaDataGet()
            )
            }else{
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
        })
    }
    //  时间维度下拉方法
    initTimeSelect(){
        api.timeDimension.send().then(res=>{
            // console.log(res)
            let timeDimensionNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    timeDimensionNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    timeDimensionData:this.state.timeDimensionData.concat(timeDimensionNew)
                },()=>{
                    // console.log(this.state.timeDimensionData)
                })
            }
        })
    }
    //  来源渠道下拉方法
    initOriginChannel(){
        api.originChannel.send().then(res=>{
            // console.log(res)
            let originChannelNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    originChannelNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    originChannelData:this.state.originChannelData.concat(originChannelNew)
                },()=>{
                    // console.log(this.state.originChannelData)
                })
            }
        })
    }
    //  农户类型下拉方法
    initFrameType(){
        api.frameType.send().then(res=>{
            // console.log(res)
            let frameTypeNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    frameTypeNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    frameTypeData:this.state.frameTypeData.concat(frameTypeNew)
                },()=>{
                    // console.log(this.state.frameTypeData)
                })
            }
        })
    }
    //  手机水平下拉方法
    initMobileOperate(){
        api.mobileOperate.send().then(res=>{
            // console.log(res)
            let mobileOperateNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    mobileOperateNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    mobileOperateData:this.state.mobileOperateData.concat(mobileOperateNew)
                },()=>{
                    // console.log(this.state.mobileOperateData)
                })
            }
        })
    }
    //  区域为度下拉方法
    initRegionLevel(){
        api.regionLevel.send().then(res=>{
            // console.log(res)
            let regionLevelNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    regionLevelNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    regionLevelData:this.state.regionLevelData.concat(regionLevelNew)
                },()=>{
                    // console.log(this.state.regionLevelData)
                })
            }
        })
    }
    componentDidMount(){
        if(Number(this.state.isAdd) !== 1){
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
        //  农户类型下拉方法
        this.initFrameType()
        //  手机水平下拉方法
        this.initMobileOperate()
        //  区域为度下拉方法
        this.initRegionLevel()
    }
    //  新增保存
    addSave = () =>{
        const {
            address,
            startTime,
            userName,
            phone,
            areaLatitudeTypeCode,
            areaLatitudeTypeText,
            mobileOperateCode,
            mobileOperateText,
            mobileType,
            idNumber,
            sourceChannelTypeCode,
            sourceChannelTypeText,
            dataSources,
            regionId,
            regionName,
            peasantTypeCode,
            peasantTypeText,
            dataTimeTypeCode,
            dataTimeTypeText,
            isAdd,
            enterpriseName
        } = this.state
        let params = {
            "address": address,
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
            "user": {
              "userName": userName,
              "phone":phone
            },
            "enterpriseId": "string",
            "idNumber": idNumber,
            "mobileOperateCode": mobileOperateCode,
            "mobileOperateText": mobileOperateText,
            "mobileType": mobileType,
            "peasantTypeCode": peasantTypeCode,
            "peasantTypeText": peasantTypeText,
            "enterpriseName": enterpriseName
          }
          if( Number(isAdd) === 1){
            api.FrameMessAdd.send(params).then(res=>{
                // console.log(res)
                if(res.isSuccess){
                    message.success('上报成功！');
                    //  返回上一页
                    this.goBackPage();
                }else{
                    message.error('上报失败！');
                }
            })
          }else{
              let id = isAdd.split('-')[0];
              let commonId = isAdd.split('-')[1];
              params.id = id;
              params.daCommonField.id = commonId;
              api.FrameMessFix.send(params).then(res=>{
                  console.log(res)
                if(res.isSuccess){
                    message.success('修改成功！');

                    delete params.daCommonField.id;
                    delete params.id;
                    //  返回上一页
                    this.goBackPage();
                }else{
                    message.error('修改失败！');
                }
            })
          }
    }
    //  下拉获取值通用方法
    handleSelect = (e,option) =>{
        // console.log(e,option)
        let type = option.props.typename;
        let code = option.props.codenum;
        let text = option.props.children;
        if(code === "areaLatitudeTypeCode"){
            this.areaDataGet()
        }
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
    //  数据日期
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
             startTime:dateString+' 00:00:00'
        })
    }
    handleChange = (value) => {
        // console.log(`selected ${value}`);
    }
    goBackPage = () => {
        this.props.history.go(-1)
    }
    render() {
        const {
            timeDimensionData,
            originChannelData,
            frameTypeData,
            mobileOperateData,
            regionLevelData,
            dataTimeTypeCode,
            areaLatitudeTypeCode,
            sourceChannelTypeCode,
            mobileOperateCode,
            peasantTypeCode,
            idNumber,
            mobileType,
            phone,
            startTime,
            userName,
            dataSources,
            address,
            regionName,
            areaData,
            enterpriseName
        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit clearfix">
                    {/* <li>
                        <label htmlFor="">时间维度：</label>
                        <Select onChange={this.handleSelect} value={dataTimeTypeCode}  defaultValue={timeDimensionData[0].code}  style={{ width: '65%' }}>
                        {
                            timeDimensionData.map((v,i)=>{
                                return(
                                    <Option codenum={'dataTimeTypeCode'} typename={'dataTimeTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li> */}
                    <li>
                        <label htmlFor="">数据日期：</label>
                        <DatePicker locale={locale} allowClear={false}  value={startTime?moment(new Date(startTime)):null} onChange={this.timeSelect} style={{ width: '65%' }} format="YYYY-MM-DD"/>
                    </li>
                    {/* <li>
                        <label htmlFor="">来源渠道：</label>
                        <Select onChange={this.handleSelect} defaultValue={originChannelData[0].code} value={sourceChannelTypeCode} style={{ width: '65%' }}>
                        {
                            originChannelData.map((v,i)=>{
                                return(
                                    <Option codenum={'sourceChannelTypeCode'} typename={'sourceChannelTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li> */}
                    <li>
                        <label htmlFor="">数据来源：</label>
                        <Input  onChange={this.inputHandle} data-type="dataSources" style={{ width: '65%' }} value={dataSources} defaultValue={dataSources} />
                    </li>
                    {/* <li>
                        <label htmlFor="">区域维度：</label>
                        <Select defaultValue={regionLevelData[0].code} value={areaLatitudeTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            regionLevelData.map((v,i)=>{
                                return(
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
                        <label htmlFor="">详细地址：</label>
                        <Input onChange={this.inputHandle} data-type="address" style={{ width: '65%' }} value={address} defaultValue={address} />
                    </li>
                    <li>
                        <label htmlFor="">户主姓名：</label>
                        <Input onChange={this.inputHandle} data-type="userName" style={{ width: '65%' }} value={userName} defaultValue={userName} />
                    </li>
                    <li>
                        <label htmlFor="">身份证号：</label>
                        <Input  onChange={this.inputHandle} data-type="idNumber" style={{ width: '65%' }} value={idNumber} defaultValue={idNumber} />
                    </li>
                    <li>
                        <label htmlFor="">手机水平：</label>
                        <Select defaultValue={mobileOperateData[0].code} value={mobileOperateCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            mobileOperateData.map((v,i)=>{
                                return(
                                    <Option codenum={'mobileOperateCode'} typename={'mobileOperateText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">智能手机：</label>
                        <Input  onChange={this.inputHandle} data-type="mobileType" style={{ width: '65%' }} value={mobileType} defaultValue={mobileType} />
                    </li>
                    <li>
                        <label htmlFor="">联系电话：</label>
                        <Input  onChange={this.inputHandle} data-type="phone" style={{ width: '65%' }} value={phone} defaultValue={phone} />
                    </li>
                    <li>
                        <label htmlFor="">农户类型：</label>
                        <Select defaultValue={frameTypeData[0].code} value={peasantTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            frameTypeData.map((v,i)=>{
                                return(
                                    <Option codenum={'peasantTypeCode'} typename={'peasantTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">所属合作社：</label>
                        {/* <Select defaultValue="选择合作社企业" style={{ width: '65%' }} onChange={this.handleChange}>
                            <Option value="示范户">示范户</Option>
                            <Option value="其他">其他</Option>
                        </Select> */}
                        <Input  onChange={this.inputHandle} data-type="enterpriseName"  style={{ width: '65%' }} value={enterpriseName} defaultValue={enterpriseName}/>
                    </li>
                    <li>
                        <Button type="primary"  onClick={this.addSave} htmlType="submit">上报</Button>
                        <Button onClick={this.goBackPage}>取消</Button>
                    </li>
                </ul>
            </div>
        )
    }
}