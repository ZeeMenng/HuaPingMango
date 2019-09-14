import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Button, Cascader, message } from 'antd';
import * as api from "../api/api-forData-second";
// import Options from "../component/area";
import moment from 'moment';
const Option = Select.Option;
export default class StatisticsDataAdd extends Component {
    constructor(props) {
        super(props)
        this.state = {
            areaData:[],                                //  地理区域数据
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            timeDimensionData:[{text:'全部',code:0}],   //  时间维度下拉列表
            originChannelData:[{text:'全部',code:0}],   //  来源渠道下拉列表
            dataTimeTypeCode:0,                         //  时间维度默认code
            dataTimeTypeText:"全部",                    //  时间维度名称
            sourceChannelTypeCode:0,                    //  来源渠道默认code
            sourceChannelTypeText:"全部",               //  来源渠道默认名称
            regionLevelData:[{text:'全部',code:0}],     //  区域维度下拉列表
            areaLatitudeTypeCode: 0,                    //  区域维度id
            areaLatitudeTypeText: "全部",               //  区域维度名称
            regionId: "",                               //  地理区域id
            regionName:"",                              //  地理区域名称
            name: "",                                   //  名称
            cropBreedData:[{text:'全部',code:0}],       //  产品种类下拉列表
            cropTypeCode: 0,                            //  产品种类code
            cropTypeText: "全部",                       //  产品种类文本
            strainsData:[{text:'全部',code:0}],         //  产品品种下拉列表
            strainsCode: 0,                             //  产品品种code
            strainsText: "全部",                        //  产品品种文本
            productName: "",                            //  产品名称
            importCountryData:[{text:'全部',code:0}],   //  进口国家data
            importCountryCode: 0,                     //  进口国家code
	        importCountryText: "",                //  进口国家名称文本
	        importEnterprise: "",                 //  进口企业
            importTime: "",                             //  进口日期
            importAmount: 0,                            //  进口量
	        importAmountUnit: 0,                        //  通用进口量
	        importAmountUnitCode: 0,                    //  进口量单位code
	        importAmountUnitText: "",                   //  进口量单位文本
            importAmountData:[{text:'全部',code:0}],    //  进口量单位
            importVolume: 0,                            //  进口额
            importVolumeUnit: 0,                        //  通用进口额
            importVolumeUnitCode: 0,                    //  进口额单位code
            importVolumeUnitText: "",                   //  进口额单位文本
            importVolumeData:[{text:'全部',code:0}],    //  进口额
            exportAmount: 0,                            //  出口量
	        exportAmountUnit: 0,                        //  通用出口量
	        exportAmountUnitCode: 0,                    //  出口量单位code
            exportAmountUnitText: "",                   //  出口量单位文本
            exportAmountData:[{text:'全部',code:0}],    //  出口量
	        exportCountryCode: 0,                     //  出口国家code
            exportCountryText: "",                      //  出口国家文本
            exportCountryData:[{text:'全部',code:0}],    //  出口国家
            exportEnterprise: "",                       //  出口企业
            exportTime: "",                             //  出口日期
	        exportVolume: 0,                            //  出口金额
	        exportVolumeUnit: 0,                        //  通用出口金额
	        exportVolumeUnitCode: 0,                    //  出口金额单位code
            exportVolumeUnitText: "",                   //  出口金额单位文本
            exportVolumeData:[{text:'全部',code:0}],    //  出口金额
            dataSources: ""
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
    onChange = (date, dateString) => {
        console.log(date, dateString);
    }
    goBackPage = () => {
        this.props.history.go(-1)
    }
    //  时间维度下拉方法
    initTimeSelect(){
        api.timeDimension.send().then(res=>{
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
            let cropBreedNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    cropBreedNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    cropBreedData:cropBreedNew
                },()=>{
                    // console.log(this.state.regionLevelData)
                })
            }
        })
    }
    //  作物品种下拉方法
    initStatuestrains(){
        api.cropStrains.send().then(res=>{
            // console.log(res)
            let strainsNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    strainsNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    strainsData:strainsNew
                },()=>{
                    // console.log(this.state.regionLevelData)
                })
            }
        })
    }
    initWeightUnit() {
        api.weightUnit.send().then(res=>{
            let processUnitArr = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    processUnitArr[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    importAmountData:processUnitArr
                },()=>{
                    // console.log(this.state.processUnitData)
                })
            }
        })
    }
    //  数据日期
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
             startTime:dateString+' 00:00:00'
        })
    }
    //  进口日期
    importTimeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            importTime:dateString+' 00:00:00'
        })
    }
    //  出口日期
    exportTimeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            exportTime:dateString+' 00:00:00'
        })
    }
    // 国家下拉
    initareaUnit() {
        api.getCountry.send().then(res=>{
            console.log(res)
            let areaUnitArr = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    areaUnitArr[i] = {
                        text:v.chineseName,
                        code:v.isoCode
                    }
                })
                this.setState({
                    exportCountryData:areaUnitArr
                },()=>{
                    // console.log(this.state.processUnitData)
                })
            }
        })
    }
    //  元/斤单位
    initForecastUnit() {
        api.getCountryPrice.send().then(res=>{
            let processUnitArr = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    processUnitArr[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    importVolumeData:processUnitArr
                },()=>{
                    // console.log(this.state.processUnitData)
                })
            }
        })
    }
    initInput=(id)=>{
        api.tradeDataGetModel.send({id:id}).then(res=>{
            console.log(res.data)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    "cropTypeCode": v.cropTypeCode,//产品种类code
                    "cropTypeText": v.cropTypeText,//产品种类文本
                    "areaLatitudeTypeCode": v.daCommonField.areaLatitudeTypeCode,//区域维度id
                    "areaLatitudeTypeText": v.daCommonField.areaLatitudeTypeText,//区域维度名称
                    "startTime": v.daCommonField.startTime,//数据日期
                    "dataSources": v.daCommonField.dataSources,//数据来源
                    "dataTimeTypeCode": v.daCommonField.dataTimeTypeCode,//时间维度id
                    "dataTimeTypeText": v.daCommonField.dataTimeTypeText,//时间维度名称
                    "regionId": v.daCommonField.regionId,//地理区域id
                    "regionName": v.daCommonField.regionName,//地理区域名称
                    "sourceChannelTypeCode": v.daCommonField.sourceChannelTypeCode,//来源渠道id
                    "sourceChannelTypeText": v.daCommonField.sourceChannelTypeText,//来源渠道名称
                    "exportAmount": v.exportAmount,//出口量
                    "exportAmountUnit": v.exportAmountUnit,//通用出口量
                    "exportAmountUnitCode": v.exportAmountUnitCode,//出口量单位code
                    "exportAmountUnitText": v.exportAmountUnitText,//出口量单位文本
                    "exportCountryCode": v.exportCountryCode,//出口国家code
                    "exportCountryText": v.exportCountryText,//出口国家文本
                    "exportEnterprise": v.exportEnterprise,//出口企业
                    "exportPrice": v.exportPrice,//出口价格
                    "exportPriceUnit": v.exportPriceUnit,//通用出口价格
                    "exportPriceUnitCode": v.exportPriceUnitCode,//出口价格单位code
                    "exportPriceUnitText": v.exportPriceUnitText,//出口价格单位文本
                    "exportTime": v.exportTime,//出口日期
                    "exportVolume": v.exportVolume,//出口金额
                    "exportVolumeUnit": v.exportVolumeUnit,//通用出口金额
                    "exportVolumeUnitCode": v.exportVolumeUnitCode,//出口金额单位code
                    "exportVolumeUnitText": v.exportVolumeUnitText,//出口金额单位文本
                    "importAmount": v.importAmount,//进口量
                    "importAmountUnit": v.importAmountUnit,//通用进口量
                    "importAmountUnitCode": v.importAmountUnitCode,//进口量单位code
                    "importAmountUnitText": v.importAmountUnitText,//进口量单位文本
                    "importCountryCode": v.importCountryCode,//进口国家code
                    "importCountryText": v.importCountryText,//进口国家名称文本
                    "importEnterprise": v.importEnterprise,//进口企业
                    "importPrice": v.importPrice,//进口价格
                    "importPriceUnit": v.importPriceUnit,//通用进口价格
                    "importPriceUnitCode": v.importPriceUnitCode,//进口价格单位code
                    "importPriceUnitText": v.importPriceUnitText,//进口价格单位文本
                    "importTime": v.importTime,//进口日期
                    "importVolume": v.importVolume,//进口额
                    "importVolumeUnit": v.importVolumeUnit,//通用进口额
                    "importVolumeUnitCode": v.importVolumeUnitCode,//进口额单位code
                    "importVolumeUnitText": v.importVolumeUnitText,//进口额单位文本
                    "name": v.name, //数据主体名称
                    "productName": v.productName,//产品名称
                    "strainsCode": v.strainsCode,//产品品种code
                    "strainsText": v.strainsText//产品品种文本
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
    // 上报
    formSaveFn = () =>{
        const {
            isAdd,
            dataSources,
            exportPriceUnitText,
            exportPriceUnit,
            exportPriceUnitCode,
            importPriceUnitText,
            importPriceUnitCode,
            exportPrice,
            importPriceUnit,
            importPrice,
            startTime,
            dataTimeTypeCode,
            dataTimeTypeText,
            sourceChannelTypeCode,
            sourceChannelTypeText,
            areaLatitudeTypeCode,
            areaLatitudeTypeText,
            regionId,
            regionName,
            name,
            cropTypeCode,
            cropTypeText,
            strainsCode,
            strainsText,
            productName,
            importCountryCode,
	        importCountryText,
	        importEnterprise,
            importTime,
            importAmount,
	        importAmountUnit,
	        importAmountUnitCode,
	        importAmountUnitText,
            importVolume,
            importVolumeUnit,
            importVolumeUnitCode,
            importVolumeUnitText,
            exportAmount,
	        exportAmountUnit,
	        exportAmountUnitCode,
            exportAmountUnitText,
	        exportCountryCode,
            exportCountryText,
            exportEnterprise,
            exportTime,
	        exportVolume,
	        exportVolumeUnit,
	        exportVolumeUnitCode,
            exportVolumeUnitText
        } = this.state
        let params = {
            "cropTypeCode": cropTypeCode,//产品种类code
            "cropTypeText": cropTypeText,//产品种类文本
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
            "exportAmount": exportAmount,//出口量
            "exportAmountUnit": exportAmountUnit,//通用出口量
            "exportAmountUnitCode": exportAmountUnitCode,//出口量单位code
            "exportAmountUnitText": exportAmountUnitText,//出口量单位文本
            "exportCountryCode": exportCountryCode,//出口国家code
            "exportCountryText": exportCountryText,//出口国家文本
            "exportEnterprise": exportEnterprise,//出口企业
            "exportPrice": exportPrice,//出口价格
            "exportPriceUnit": exportPriceUnit,//通用出口价格
            "exportPriceUnitCode": exportPriceUnitCode,//出口价格单位code
            "exportPriceUnitText": exportPriceUnitText,//出口价格单位文本
            "exportTime": exportTime,//出口日期
            "exportVolume": exportVolume,//出口金额
            "exportVolumeUnit": exportVolumeUnit,//通用出口金额
            "exportVolumeUnitCode": exportVolumeUnitCode,//出口金额单位code
            "exportVolumeUnitText": exportVolumeUnitText,//出口金额单位文本
            "importAmount": importAmount,//进口量
            "importAmountUnit": importAmountUnit,//通用进口量
            "importAmountUnitCode": importAmountUnitCode,//进口量单位code
            "importAmountUnitText": importAmountUnitText,//进口量单位文本
            "importCountryCode": importCountryCode,//进口国家code
            "importCountryText": importCountryText,//进口国家名称文本
            "importEnterprise": importEnterprise,//进口企业
            "importPrice": importPrice,//进口价格
            "importPriceUnit": importPriceUnit,//通用进口价格
            "importPriceUnitCode": importPriceUnitCode,//进口价格单位code
            "importPriceUnitText": importPriceUnitText,//进口价格单位文本
            "importTime": importTime,//进口日期
            "importVolume": importVolume,//进口额
            "importVolumeUnit": importVolumeUnit,//通用进口额
            "importVolumeUnitCode": importVolumeUnitCode,//进口额单位code
            "importVolumeUnitText": importVolumeUnitText,//进口额单位文本
            "name": name, //数据主体名称
            "productName": productName,//产品名称
            "strainsCode": strainsCode,//产品品种code
            "strainsText": strainsText//产品品种文本
        }
        if( Number(isAdd) === 1){
            //  新增
            api.tradeDataAdd.send(params).then(res=>{
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
            api.tradeDataFix.send(params).then(res=>{
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
        //  初始化时间维度
        this.initTimeSelect()
        //  初始化来源渠道
        this.initOriginChannel()
        //  初始化区域维度
        this.initRegionLevel()
         //  作物种类下拉方法
        this.initCropBreed()
        this.initWeightUnit()
        this.initForecastUnit()
        this.initareaUnit()
        this.initStatuestrains()
    }
    render() {
        const {
            dataTimeTypeCode,
            sourceChannelTypeCode,
            areaLatitudeTypeCode,
            name,
            cropTypeCode,
            strainsCode,
            productName,
            importCountryCode,
	        importEnterprise,
            importTime,
            importAmount,
	        importAmountUnitCode,
            exportAmount,
	        exportAmountUnitCode,
	        exportCountryCode,
            exportEnterprise,
            exportTime,
	        exportVolume,
	        exportVolumeUnitCode,
            dataSources,
            timeDimensionData,
            startTime,
            originChannelData,
            regionLevelData,
            cropBreedData,
            strainsData,
            importVolumeData,
            importPriceUnitCode,
            exportCountryData,
            importAmountData,
            importPrice,
            regionName,
            areaData
        } = this.state
        return (
            <div className="forData">

                <ul className="form-search form-aduit2 clearfix">
                    {/* <li>
                        <label htmlFor="">时间维度：</label>
                        <Select value={dataTimeTypeCode} defaultValue={timeDimensionData[0].code} style={{ width: '65%' }} onChange={this.handleSelect}>
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
                        <Select value={sourceChannelTypeCode} defaultValue={originChannelData[0].code} style={{ width: '65%' }} onChange={this.handleSelect}>
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
                    </li>
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
                        <label htmlFor="">名称：</label>
                        <Input onChange={this.inputHandle} data-type="name" style={{ width: '65%' }} value={name} defaultValue={name} />
                    </li> */}
                    <li>
                        <label htmlFor="">产品种类：</label>
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
                        <label htmlFor="">产品品种：</label>
                        <Select defaultValue={strainsData[0].code} value={strainsCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            strainsData.map((v,i)=>{
                                return(
                                    <Option codenum={'strainsCode'} typename={'strainsText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产品名称：</label>
                        <Input  onChange={this.inputHandle} data-type="productName" style={{ width: '65%' }} value={productName} defaultValue={productName} />
                    </li>
                    <li className="position">
                        <label htmlFor="">进口国家：</label>
                        <Select defaultValue={exportCountryData[0].code} value={importCountryCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            exportCountryData.map((v,i)=>{
                                return(
                                    <Option codenum={'importCountryCode'} typename={'importCountryText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">进口企业：</label>
                        <Input  onChange={this.inputHandle} data-type="importEnterprise" style={{ width: '65%' }} value={importEnterprise} defaultValue={importEnterprise} />
                    </li>
                    <li>
                        <label htmlFor="">进口日期：</label>
                        <DatePicker locale={locale} allowClear={false}  value={importTime?moment(new Date(importTime)):null} onChange={this.importTimeSelect} style={{ width: '65%' }} />
                    </li>
                    <li>
                        <label htmlFor="">进口量：</label>
                        <Input onChange={this.inputHandle} data-type="importAmount" style={{ width: '30%' }} value={importAmount} defaultValue={importAmount} />
                        <Select defaultValue={importAmountData[0].code} value={importAmountUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            importAmountData.map((v,i)=>{
                                return(
                                    <Option codenum={'importAmountUnitCode'} typename={'importAmountUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">进口额：</label>
                        <Input onChange={this.inputHandle} data-type="importPrice" style={{ width: '30%' }} value={importPrice} defaultValue={importPrice} />
                        <Select defaultValue={importVolumeData[0].code} value={importPriceUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            importVolumeData.map((v,i)=>{
                                return(
                                    <Option codenum={'importPriceUnitCode'} typename={'importPriceUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">出口国家：</label>
                        <Select defaultValue={exportCountryData[0].code} value={exportCountryCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            exportCountryData.map((v,i)=>{
                                return(
                                    <Option codenum={'exportCountryCode'} typename={'exportCountryText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">出口企业：</label>
                        <Input onChange={this.inputHandle} data-type="exportEnterprise" style={{ width: '65%' }} value={exportEnterprise} defaultValue={exportEnterprise} />
                    </li>
                    <li>
                        <label htmlFor="">出口日期：</label>
                        <DatePicker locale={locale} allowClear={false}  value={exportTime?moment(new Date(exportTime)):null} onChange={this.exportTimeSelect} style={{ width: '65%' }} />
                    </li>
                    <li>
                        <label htmlFor="">出口量：</label>
                        <Input onChange={this.inputHandle} data-type="exportAmount" style={{ width: '30%' }} value={exportAmount} defaultValue={exportAmount} />
                        <Select defaultValue={importAmountData[0].code} value={exportAmountUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            importAmountData.map((v,i)=>{
                                return(
                                    <Option codenum={'exportAmountUnitCode'} typename={'exportAmountUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">出口额：</label>
                        <Input onChange={this.inputHandle} data-type="exportVolume" style={{ width: '30%' }} value={exportVolume} defaultValue={exportVolume} />
                        <Select defaultValue={importVolumeData[0].code} value={exportVolumeUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            importVolumeData.map((v,i)=>{
                                return(
                                    <Option codenum={'exportVolumeUnitCode'} typename={'exportVolumeUnitText'} key={i} value={v.code}>{v.text}</Option>
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