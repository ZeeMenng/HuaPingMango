import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import moment from 'moment';
import { Select, DatePicker,Cascader, Input,message, Button } from 'antd';
import * as api from "../api/api-forData";
const Option = Select.Option;
export default class AuthenticationDataAdd extends Component {
    constructor(props){
        super(props)
        console.log()
        this.state={
            areaData:[],                                //  地理区域数据
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            timeDimensionData:[{text:'全部',code:0}],   //  时间维度下拉列表
            originChannelData:[{text:'全部',code:0}],   //  来源渠道下拉列表
            regionLevelData:[{text:'全部',code:0}],     //  区域维度下拉列表
            cropBreedData:[{text:'全部',code:0}],        //  作物种类下拉列表
            identificationTypeData:[{text:'全部',code:0}],  //  认证类型下拉列表
            cropStrainsData:[{text:'全部',code:0}],      //  作物品种下拉列表
            areaUnitData:[],                            //  面积单位下拉列表
            weightUnitData:[{text:'全部',code:0}],                         //  重量单位下拉列表
            dataTimeTypeCode:0,                         //  时间维度默认code
            dataTimeTypeText:"全部",                    //  时间维度名称
            sourceChannelTypeCode:0,                    //  来源渠道默认code
            sourceChannelTypeText:"全部",               //  来源渠道默认名称
            areaLatitudeTypeCode: 0,                    //  区域维度id
            areaLatitudeTypeText: "全部",               //  区域维度名称
            cropTypeCode: 0,                            //  作物种类code
            cropTypeText: "全部",                       //  作物种类文本
            identificationAreaCode: 1,                  //  认证面积单位code
            identificationAreaText: "亩",              //  认证面积单位文本
            identificationTypeCode: 0,                  //  认证类型code
            identificationTypeText: "全部",             //  认证类型文本
            procurementTotalUnitCode: 1,                //  重量单位code
            procurementTotalUnitText: "重量",           //  重量单位文本
            strainsCode: 0,                             //  作物品种code
            strainsText: "全部",                         //  作物品种文本
            startTime:"",                               //  数据日期
            baseName: "",                               //  认证基地名称
            identificationArea: "",                     //  认证面积
            processTime: "",                            //  时间
            produceName: "",                            //  产品名称
            produceSummation: "",                       //  产品产量
        }
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
    onChange = (date, dateString) => {
        console.log(date, dateString);
    }
    //  修改时重新渲染数据
    initInput=(id)=>{
        // console.log(id)

        api.altitudeOneData.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    name:v.name,
                    regionId:v.daCommonField.regionId,
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    areaLatitudeTypeCode:v.daCommonField.areaLatitudeTypeCode,
                    cropTypeCode:v.cropTypeCode,
                    strainsCode:v.strainsCode,
                    identificationArea:v.identificationArea,
                    identificationAreaCode:v.identificationAreaCode,
                    identificationTypeCode:v.identificationTypeCode,
                    produceSummationUnitCode:v.produceSummationUnitCode,
                    baseName:v.baseName,
                    processTime:v.processTime,
                    produceName:v.produceName,
                    produceSummation:v.produceSummation,
                    // areaLatitudeTypeText:v.daCommonField.areaLatitudeTypeText,
                    sourceChannelTypeCode:v.daCommonField.sourceChannelTypeCode,
                    // sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
                    dataSources:v.daCommonField.dataSources,
                    dataTimeTypeCode:v.daCommonField.dataTimeTypeCode,
                    // dataTimeTypeText:v.daCommonField.dataTimeTypeText
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
    //  保存与修改方法
    formSaveFn = () =>{
        const {
            address,
            baseName,
            cropTypeCode,
            cropTypeText,
            startTime,
            name,
            areaLatitudeTypeCode,
            areaLatitudeTypeText,
            sourceChannelTypeCode,
            sourceChannelTypeText,
            identificationAreaCode,
            identificationAreaText,
            identificationArea,
            dataSources,
            regionId,
            regionName,
            dataTimeTypeCode,
            dataTimeTypeText,
            identificationTypeCode,
            identificationTypeText,
            processTime,
            produceName,
            produceSummation,
            produceSummationUnitCode,
            produceSummationUnitText,
            strainsCode,
            strainsText,
            isAdd
        } = this.state
        let params = {
            "address": address,//产地地址：
            "baseName": baseName,//认证基地名称
            "cropTypeCode": cropTypeCode,//作物种类code
            "cropTypeText": cropTypeText,//作物种类文本
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
                "sourceChannelTypeText": sourceChannelTypeText//来源渠道名称
            },
            "identificationArea": identificationArea,//认证面积
            "identificationAreaCode": identificationAreaCode,//认证面积单位code
            "identificationAreaText": identificationAreaText,//认证面积单位文本
            "identificationTypeCode": identificationTypeCode,//认证类型code
            "identificationTypeText": identificationTypeText,//认证类型文本
            "name": name,//获证单位名称
            "processTime": processTime, //  证书有效期
            "produceName": produceName,//产品名称
            "produceSummation": produceSummation,//产品产量
            "produceSummationUnitCode": produceSummationUnitCode,//产品产量单位code
            "produceSummationUnitText": produceSummationUnitText,//产品产量单位文本
            "strainsCode": strainsCode,//作物品种code
            "strainsText": strainsText//作物品种文本
          }
          if( Number(isAdd) === 1){
            //  新增
            api.authenticationAdd.send(params).then(res=>{
                console.log(res)
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
            api.authenticationFix.send(params).then(res=>{
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
        //  作物种类下拉方法
        this.initCropBreed()
        //  作物品种下拉方法
        this.initCropStrains()
        //  认证类型下拉方法
        this.initIdentificationType()
        //  面积单位下拉方法
        this.initAreaUnit()
        //  重量单位下拉方法
        this.initWeightUnit()
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
    //  作物种类下拉方法
    initCropBreed(){
        api.cropBreed.send().then(res=>{
            // console.log(res)
            let cropBreedNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    cropBreedNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    cropBreedData:this.state.cropBreedData.concat(cropBreedNew)
                },()=>{
                    // console.log(this.state.regionLevelData)
                })
            }
        })
    }
    //  作物品种下拉方法
    initCropStrains(){
        api.cropStrains.send().then(res=>{
            // console.log(res)
            let cropStrainsNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    cropStrainsNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    cropStrainsData:cropStrainsNew
                },()=>{
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
                })
            }
        })
    }
    //  认证类型下拉方法
    initIdentificationType(){
        api.identificationType.send().then(res=>{
            // console.log(res)
            let identificationTypeNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    identificationTypeNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    identificationTypeData:identificationTypeNew
                },()=>{
                    // console.log(this.state.identificationTypeData)
                })
            }
        })
    }
    //  重量单位下拉方法
    initWeightUnit(){
        api.weightUnit.send().then(res=>{
            console.log(res)
            let weightUnitNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    weightUnitNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    weightUnitData:weightUnitNew
                },()=>{
                    // console.log(this.state.weightUnitData)
                })
            }
        })
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
    //  证书有效期日期
    timeSelect1=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            processTime:dateString+' 00:00:00'
        })
    }
    //  返回上一页
    goBackPage = () => {
        this.props.history.go(-1)
    }
    render() {
        const {
            timeDimensionData,
            originChannelData,
            cropBreedData,
            areaUnitData,
            identificationTypeData,
            cropStrainsData,
            regionLevelData,
            weightUnitData,
            dataTimeTypeCode,
            areaLatitudeTypeCode,
            sourceChannelTypeCode,
            strainsCode,
            cropTypeCode,
            identificationTypeCode,
            identificationAreaCode,
            produceSummationUnitCode,
            dataSources,
            produceName,
            baseName,
            name,
            processTime,
            identificationArea,
            produceSummation,
            startTime,
            regionName,
            areaData
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
                        <DatePicker locale={locale}  value={startTime?moment(new Date(startTime)):null} onChange={this.timeSelect} style={{ width: '65%' }} />
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
                    <label htmlFor="" style={{ fontSize: '12px' }}>获证单位名称：</label>
                        <Input onChange={this.inputHandle} data-type="name" style={{ width: '65%' }} value={name} defaultValue={name} />
                    </li>
                    <li>
                        <label htmlFor="" style={{ fontSize: '12px' }}>认证基地名称：</label>
                        <Input  onChange={this.inputHandle} data-type="baseName" style={{ width: '65%' }} value={baseName} />
                    </li>
                    <li>
                        <label htmlFor="">作物种类：</label>
                        <Select defaultValue={cropBreedData[0].code} value={cropTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            cropBreedData.map((v,i)=>{
                                return(
                                    <Option codenum={'cropTypeCode'} typename={'cropTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">作物品种：</label>
                        <Select defaultValue={cropStrainsData[0].code} value={strainsCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            cropStrainsData.map((v,i)=>{
                                return(
                                    <Option codenum={'strainsCode'} typename={'strainsText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产品名称：</label>
                        <Input  onChange={this.inputHandle} data-type="produceName" style={{ width: '65%' }} value={produceName} />
                    </li>
                    <li>
                        <label htmlFor="">认证类型：</label>
                        <Select defaultValue={identificationTypeData[0].code} value={identificationTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            identificationTypeData.map((v,i)=>{
                                return(
                                    <Option codenum={'identificationTypeCode'} typename={'identificationTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">认证面积：</label>
                        <Input  onChange={this.inputHandle} data-type="identificationArea" style={{ width: '30%' }} value={identificationArea} />
                        <Select onChange={this.handleSelect} value={identificationAreaCode}  defaultValue={identificationAreaCode} style={{ marginLeft: '2%', width: '33%' }}>
                        {
                            areaUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'identificationAreaCode'} typename={'identificationAreaText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产品产量：</label>
                        <Input  onChange={this.inputHandle} data-type="produceSummation" style={{ width: '30%' }} value={produceSummation} />
                        <Select onChange={this.handleSelect} value={produceSummationUnitCode}  defaultValue={produceSummationUnitCode} style={{ marginLeft: '2%', width: '33%' }}>
                        {
                            weightUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'produceSummationUnitCode'} typename={'produceSummationUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">证书有效期：</label>
                        <DatePicker allowClear={false}  value={processTime?moment(new Date(processTime)):null} locale={locale} onChange={this.timeSelect1} style={{ width: '65%' }} />
                    </li>
                    <li>
                        <Button onClick={this.formSaveFn.bind(undefined,1)} type="primary">上报</Button>
                        <Button onClick={this.goBackPage}>取消</Button>
                    </li>
                </ul>
            </div>
        )
    }
}