import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, message, Cascader,DatePicker, Input, Button } from 'antd';
import * as api from "../api/api-forData";
import moment from 'moment';
// import Options from "../component/area";
const Option = Select.Option;
const BMap=window.BMap;
export default class BaseDataAdd extends Component {
    constructor(props){
        super(props)
        this.state={
            areaData:[],                                //  地理区域数据
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            timeDimensionData:[{text:'全部',code:0}],   //  时间维度下拉列表
            originChannelData:[{text:'全部',code:0}],   //  来源渠道下拉列表
            regionLevelData:[{text:'全部',code:0}],     //  区域维度下拉列表
            areaUnitData:[{text:'亩',code:1}],          //  面积单位下拉列表
            baseTypeData:[{text:'大棚',code:1}],          //  基地类型下拉列表
            identificaData:[{text:'无公害认证',code:1}],  //  认证类型下拉列表
            dataTimeTypeCode:0,                         //  时间维度默认code
            dataTimeTypeText:"全部",                    //  时间维度名称
            sourceChannelTypeCode:0,                    //  来源渠道默认code
            sourceChannelTypeText:"全部",               //  来源渠道默认名称
            areaLatitudeTypeCode: 0,                    //  区域维度id
            areaLatitudeTypeText: "全部",               //  区域维度名称
            identificationTypeCode: 1,                 //  认证类型code
            dentificationTypeText: "无公害认证",       //  认证类型文本
            baseAreaUnit: 1,                            //  面积单位code
            baseTypeCode: 1,                            //  基地类型code
            baseTypeText: "大棚",                         //  基地类型文本
            regionId: "",                               //  地理区域id
            regionName:"",                              //  地理区域名称
            startTime:"",                               //  数据日期
            dataSources:"",                             //  数据来源
            address:"",                                 //  详细地址
		    baseLatitude: "",                           //基地坐标纬度
		    baseLongitude: "",                          //基地坐标经度
            name:"",
        }
    }
     //  修改时重新渲染数据
     initInput=(id)=>{
        // console.log(id)

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
                    identificationTypeCode: v.identificationTypeCode,
                    identificationTypeText: v.identificationTypeText
                },
                ()=>
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
        let name =[];
        selectedOptions.forEach((v,i)=>{
            name[i] = v.label
        })
        this.setState({
            regionId:value[value.length-1],
            regionName:name.join('/')
        })
      }
    //  保存与修改方法
    formSaveFn = () =>{
        const {
            startTime,
            name,
            areaLatitudeTypeCode,
            areaLatitudeTypeText,
            sourceChannelTypeCode,
            sourceChannelTypeText,
            dataSources,
            regionId,
            regionName,
            dataTimeTypeCode,
            dataTimeTypeText,
            identificationTypeCode,
            identificationTypeText,
            baseTypeCode,
            baseTypeText,
            baseLatitude,
            baseLongitude,
            baseArea,
            baseCode,
            baseName,
            address,
            owner,//所属主体名称
            baseAreaUnit, //    面积code
            isAdd
        } = this.state
        let params = {
            "address": address, //详细地址
            "baseArea": baseArea,     //基地面积
            "baseAreaUnit": baseAreaUnit, //基地面积单位
            "baseCode": baseCode,   //基地编码
            "baseLatitude": baseLatitude,//基地坐标纬度
            "baseLongitude": baseLongitude,//基地坐标经度
            "baseName": baseName, //基地名称
            "baseTypeCode": baseTypeCode,    //基地类型code 1：大棚，2：其他
            "baseTypeText": baseTypeText,//基地类型文本 1：大棚，2：其他
            "daCommonField": {
                "areaLatitudeTypeCode": areaLatitudeTypeCode,//区域维度id
                  "areaLatitudeTypeText": areaLatitudeTypeText,//区域维度名称
                  "startTime": startTime,//数据日期
                  "dataSources": dataSources,//数据来源
                  "dataTimeTypeCode": dataTimeTypeCode,//时间维度id
                  "dataTimeTypeText": dataTimeTypeText,//时间维度名称
                  "regionId": regionId,//地理区域id
                  "regionName": regionName,//地理区域名称
                  "sourceChannelTypeCode": sourceChannelTypeCode,//来源渠道id
                  "sourceChannelTypeText": sourceChannelTypeText,//来源渠道名称
            },
            "identificationTypeCode": identificationTypeCode, //认证类型code1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
            "identificationTypeText": identificationTypeText,//认证类型文本 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
            "name": name, //主体名称
            "owner": owner,//所属主体名称
          }
          if( Number(isAdd) === 1){
            //  新增
            api.baseDataAdd.send(params).then(res=>{
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
            //  修改
            let id = isAdd.split('-')[0];
            let commonId = isAdd.split('-')[1];
            params.id = id;
            params.daCommonField.id = commonId;
            api.baseDataFix.send(params).then(res=>{
                // console.log(res)
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
        //  区域为度下拉方法
        this.initRegionLevel()
        //  面积单位下拉方法
        this.initAreaUnit()
        //  基地类型下拉方法
        this.initBaseType()
        //  认证类型下拉方法
        this.initIdentifica()
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
            let originChannelNew = [];
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
    //  区域维度下拉方法
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
    
    //  面积单位下拉方法
    initAreaUnit(){
        api.areaUnit.send().then(res=>{
            // console.log(res)
            let areaUnitNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    areaUnitNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    areaUnitData:areaUnitNew
                },()=>{
                    // console.log(this.state.areaUnitData)
                })
            }
        })
    }
    //  基地类型下拉方法
    initBaseType(){
        api.baseType.send().then(res=>{
            // console.log(res)
            let baseTypeNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    baseTypeNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    baseTypeData:baseTypeNew
                },()=>{
                    // console.log(this.state.baseTypeData)
                })
            }
        })
    }
    //  认证类型下拉方法
    initIdentifica(){
        api.identificationType.send().then(res=>{
            // console.log(res)
            let identificaNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    identificaNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    identificaData:identificaNew
                },()=>{
                    // console.log(this.state.identificaData)
                })
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
    //  数据日期
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
             startTime:dateString+' 00:00:00'
        })
    }
    //  获取经纬度
    getPoint=(address)=>{
        //  输入为空时重置经纬度
        if(!address) {
            this.setState({
                baseLatitude: "",                           //基地坐标纬度
                baseLongitude: "",                          //基地坐标经度
            },
                // ()=>{console.log(this.state.baseLatitude,this.state.baseLongitude)}
            )
            return;
        }
        // 创建地址解析器实例
        let myGeo = new BMap.Geocoder();
        // 获取坐标
        myGeo.getPoint(address, (point)=>{
            let baseLatitude= point?point.lat:'' ;  //  纬度
            let baseLongitude= point?point.lng:'' ; //  经度
            if(point){
                this.setState({
                    baseLatitude: baseLatitude,                           //基地坐标纬度
                    baseLongitude: baseLongitude,                          //基地坐标经度
                },
                    // ()=>{console.log(this.state.baseLatitude,this.state.baseLongitude)}
                )
            }else{
                message.error('请输入有效地址！')
                this.setState({
                    baseLatitude: "",                           //基地坐标纬度
                    baseLongitude: "",                          //基地坐标经度
                },
                    // ()=>{console.log(this.state.baseLatitude,this.state.baseLongitude)}
                )
            }
        });
    }
    //  返回上一页
    goBackPage = () => {
        this.props.history.go(-1)
    }

    render() {
        const {
            dataTimeTypeCode,
            sourceChannelTypeCode,
            areaLatitudeTypeCode,
            baseTypeCode,
            timeDimensionData,
            originChannelData,
            regionLevelData,
            baseTypeData,
            areaUnitData,
            identificaData,
            startTime,
            address,
		    baseLatitude,                           //基地坐标纬度
		    baseLongitude,                          //基地坐标经度
            dataSources,
            name,
            baseAreaUnit,
            baseCode,
            baseArea,
            owner,
            identificationTypeCode,
            regionName,
            areaData,
            baseName,
            identificationTypeText
        } =this.state
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
                        <DatePicker locale={locale} allowClear={false}  value={startTime?moment(new Date(startTime)):null} onChange={this.timeSelect} style={{ width: '65%' }} />
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
                        <label htmlFor="">所属企业名称：</label>
                        <Input onChange={this.inputHandle} data-type="name" style={{ width: '65%' }} value={name} defaultValue={name} />
                    </li>
                    <li>
                        <label htmlFor="">基地编码：</label>
                        <Input  onChange={this.inputHandle} data-type="baseCode" style={{ width: '65%' }} value={baseCode} />
                    </li>
                    <li>
                        <label htmlFor="">基地名称：</label>
                        <Input onChange={this.inputHandle} data-type="baseName" style={{ width: '65%' }} value={baseName} />
                    </li>
                    <li>
                        <label htmlFor="">详细地址：</label>
                        <Input onChange={this.inputHandle} onBlur={this.getPoint.bind(undefined,address)} data-type="address" style={{ width: '65%' }} value={address} />
                    </li>
                    <li>
                        <label style={{fontSize:'12px'}} htmlFor="">基地坐标经度：</label>
                        <Input style={{ width: '65%' }} value={baseLongitude} readOnly />
                    </li>
                    <li>
                        <label style={{ fontSize: '12px' }} htmlFor="">基地坐标纬度：</label>
                        <Input style={{ width: '65%' }} value={baseLatitude} readOnly />
                    </li>
                    <li>
                        <label htmlFor="">基地类型：</label>
                        <Select  value={baseTypeCode} style={{ width: '65%'  }} onChange={this.handleSelect}>
                        {
                            baseTypeData.map((v,i)=>{
                                return(
                                    <Option codenum={'baseTypeCode'} typename={'baseTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">基地面积：</label>
                        <Input  onChange={this.inputHandle} data-type="baseArea" style={{ width: '30%' }} value={baseArea} />
                        <Select  value={baseAreaUnit} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            areaUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'baseAreaUnit'} typename={'baseAreaUnit'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    {/* <li>
                        <label style={{ fontSize: '12px' }} htmlFor="">所属主体名称：</label>
                        <Input  onChange={this.inputHandle} data-type="owner" style={{ width: '65%' }} value={owner} />
                    </li> */}
                    <li>
                        <label htmlFor="">认证类型：</label>
                        <Select  value={identificationTypeText} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            identificaData.map((v,i)=>{
                                return(
                                    <Option codenum={'identificationTypeCode'} typename={'identificationTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
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