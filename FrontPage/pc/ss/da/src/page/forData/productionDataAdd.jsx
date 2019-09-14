import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import moment from 'moment';
import { Select, Cascader, DatePicker, Input,message, Button } from 'antd';
import * as api from "../api/api-forData";
const Option = Select.Option;

export default class ProductionDataAdd extends Component {
    constructor(props){
        super(props)
        this.state={
            areaData:[],                                //  地理区域数据
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            timeDimensionData:[{text:'全部',code:0}],   //  时间维度下拉列表
            originChannelData:[{text:'全部',code:0}],   //  来源渠道下拉列表
            regionLevelData:[{text:'全部',code:0}],     //  区域维度下拉列表
            cropBreedData:[{text:'全部',code:0}],        //  作物种类下拉列表
            produceSummationData:[{text:'全部',code:0}],  //  产品产量单位下拉列表
            cropStrainsData:[{text:'全部',code:0}],      //  作物品种下拉列表
            priceUnitData:[{text:'全部',code:0}],        //  价钱单位下拉列表
            inputTypeData:[{text:'全部',code:0}],        //  投入品类型下拉列表
            weightUnitData:[{text:'全部',code:0}],      //  重量单位下拉列表
            costUnitData:[{text:'全部',code:0}],        //  成本单位下拉列表
            processUnitData:[],                         //  产品产量单位下拉列表
            dataTimeTypeCode:0,                         //  时间维度默认code
            dataTimeTypeText:"全部",                    //  时间维度名称
            sourceChannelTypeCode:0,                    //  来源渠道默认code
            sourceChannelTypeText:"全部",               //  来源渠道默认名称
            areaLatitudeTypeCode: 0,                    //  区域维度id
            areaLatitudeTypeText: "全部",               //  区域维度名称
            perCostUnitCode: 1,                         //  成本单位id
            perCostUnitText: "元/亩",                    //  成本单位名称
            cropTypeCode: 0,                            //  作物种类code
            cropTypeText: "全部",                       //  作物种类文本
            perPriceUnitCode: 1,                        //  单价code
            perPriceUnitText: "元",                     //  单价文本
            useAmountUnitCode: 1,                       //  使用量单位code
            useAmountUnitText: "千克/亩",               //  使用量单位文本
            procurementTotalUnitCode: 1,                //  重量单位code
            procurementTotalUnitText: "千克",           //  重量单位文本
            strainsCode: 0,                             //  作物品种code
            strainsText: "全部",                        //  作物品种文本
            regionId: "",                               //  地理区域id
            regionName:"",                              //  地理区域名称
            inputTypeCode: 1,                           //  投入品类型code
	        inputTypeText: "种子",                      //  投入品类型文本
            startTime:"",                               //  数据日期
            dataSources:"",                             //  数据来源
            baseName: "",                               //  认证基地名称
            procurementTotal:"",                        //  采购总量
            perCost:"",                                 //  成本
            perPrice:"",                                 //  单价
            useAmount:"",                                 //  使用量
            purchasePoint:"",                            //  采购点
            inputName:"",                               //  投入品名称
            brandName:"",                               //  品牌名称
            name:""
        }
    }

    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
     //  修改时重新渲染数据
     initInput=(id)=>{
        // console.log(id)

        api.inputOneData.send({id:id}).then(res=>{
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    name:v.name,
                    regionId:v.daCommonField.regionId,
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    areaLatitudeTypeCode:v.daCommonField.areaLatitudeTypeCode,
                    areaLatitudeTypeText:v.daCommonField.areaLatitudeTypeText,
                    cropTypeCode:v.cropTypeCode,
                    cropTypeText:v.cropTypeText,
                    strainsCode:v.strainsCode,
                    strainsText:v.strainsText,
                    useAmountUnitCode:v.useAmountUnitCode,
                    useAmountUnitText:v.useAmountUnitText,
                    baseName:v.baseName,
                    inputName:v.inputName,
                    perPrice:v.perPrice,
                    perCost:v.perCost,
                    useAmount:v.useAmount,
                    brandName:v.brandName,
                    purchasePoint:v.purchasePoint,
                    inputTypeCode:v.inputTypeCode,
                    inputTypeText:v.inputTypeText,
                    procurementTotal:v.procurementTotal,
                    sourceChannelTypeCode:v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
                    dataSources:v.daCommonField.dataSources,
                    dataTimeTypeCode:v.daCommonField.dataTimeTypeCode,
                    dataTimeTypeText:v.daCommonField.dataTimeTypeText,
                    perPriceUnitText: v.perPriceUnitText,
                    procurementTotalUnitText: v.procurementTotalUnitText,
                    perPriceUnitCode:v.perPriceUnitCode,
                    procurementTotalUnitCode: v.procurementTotalUnitCode
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
            baseName,
            cropTypeCode,
            cropTypeText,
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
            useAmountUnitCode,
            useAmountUnitText,
            brandName,
            strainsCode,
            strainsText,
            inputName,
            inputTypeCode,
            inputTypeText,
            perCostUnitCode,
            perCostUnitText,
            perPriceUnitCode,
            perPriceUnitText,
            procurementTotalUnitCode,
            procurementTotalUnitText,
            perPrice,
            procurementTotal,
            purchasePoint,
            perCost,
            useAmount,
            isAdd
        } = this.state
        let params = {
            "baseName": baseName,//基地名称
            "brandName": brandName,//品牌名称
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
            "inputName": inputName,//投入品名称
            "inputTypeCode": inputTypeCode,//投入品类型code
            "inputTypeText": inputTypeText,//投入品类型文本
            "name": name,//数据主体名称
            "perCost": perCost,//成本
            "perCostUnitCode": perCostUnitCode,//成本单位code
            "perCostUnitText": perCostUnitText,//成本单位文本
            "perPrice": perPrice,//单价
            "perPriceUnitCode": perPriceUnitCode,//单价code
            "perPriceUnitText": perPriceUnitText,//单价文本
            "procurementTotal": procurementTotal,//采购总量
            "procurementTotalUnitCode": procurementTotalUnitCode,//采购总量单位code
            "procurementTotalUnitText": procurementTotalUnitText,//采购总量单位文本
            "purchasePoint": purchasePoint,//采购点名称
            "strainsCode": strainsCode,//作物品种code
            "strainsText": strainsText,//作物品种文本
            "useAmount": useAmount,//使用量
            "useAmountUnitCode": useAmountUnitCode,//使用量单位code
            "useAmountUnitText": useAmountUnitText////使用量单位文本
          }
          if( Number(isAdd) === 1){
            //  新增
            api.inputAdd.send(params).then(res=>{
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
            api.inputFix.send(params).then(res=>{
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
        //  产品产量单位下拉方法
        this.initProcessUnit()
        //  价钱下拉方法
        this.initPriceUnit()
        //  产品产量单位下拉方法
        this.initProcessUnit()
        //  投入品类型下拉方法
        this.initInputType()
        //  重量单位下拉方法
        this.initWeightUnit()
        //  成本单位下拉方法
        this.initCostUnit()
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
    
    //  价钱下拉方法
    initPriceUnit(){
        api.priceUnit.send().then(res=>{
            // console.log(res)
            let priceUnitNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    priceUnitNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    priceUnitData:priceUnitNew
                },()=>{
                    // console.log(this.state.priceUnitData)
                })
            }
        })
    }
    //  产品产量单位下拉方法
    initProcessUnit(){
        api.processUnit.send().then(res=>{
            // console.log(res)
            let processUnitNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    processUnitNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    processUnitData:processUnitNew
                },()=>{
                    // console.log(this.state.processUnitData)
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
    //  投入品类型下拉方法
    initInputType(){
        api.inputType.send().then(res=>{
            // console.log(res)
            let inputTypeNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    inputTypeNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    inputTypeData:inputTypeNew
                },()=>{
                    // console.log(this.state.inputTypeData)
                })
            }
        })
    }
    //  成本单位下拉方法
    initCostUnit(){
        api.costUnit.send().then(res=>{
            console.log(res)
            let costUnitNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    costUnitNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    costUnitData:costUnitNew
                },()=>{
                    // console.log(this.state.costUnitData)
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
    //  返回上一页
    goBackPage = () => {
        this.props.history.go(-1)
    }
    render() {
        const {
            timeDimensionData,
            originChannelData,
            cropBreedData,
            priceUnitData,
            inputTypeData,
            costUnitData,
            cropStrainsData,
            regionLevelData,
            weightUnitData,
            processUnitData,
            dataTimeTypeCode,
            areaLatitudeTypeCode,
            sourceChannelTypeCode,
            procurementTotalUnitCode,
            strainsCode,
            cropTypeCode,
            procurementTotal,
            perPriceUnitCode,
            perCostUnitCode,
            inputTypeCode,
            produceSummationUnitCode,
            useAmountUnitCode,
            dataSources,
            perCost,
            perPrice,
            useAmount,
            brandName,
            purchasePoint,
            inputName,
            baseName,
            name,
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
                        <label htmlFor="">名称：</label>
                        <Input onChange={this.inputHandle} data-type="name" style={{ width: '65%' }} value={name} defaultValue={name} />
                    </li>
                    {/* <li>
                        <label htmlFor="">基地名称：</label>
                        <Input  onChange={this.inputHandle} data-type="baseName" style={{ width: '65%' }} value={baseName} />
                    </li> */}
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
                        <label htmlFor="">投入品类型：</label>
                        <Select value={inputTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            inputTypeData.map((v,i)=>{
                                return(
                                    <Option codenum={'inputTypeCode'} typename={'inputTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">投入品名称：</label>
                        <Input onChange={this.inputHandle} data-type="inputName" style={{ width: '65%' }} value={inputName} />
                    </li>
                    <li>
                        <label htmlFor="">规格：</label>
                        <Input onChange={this.inputHandle} data-type="brandName" style={{ width: '65%' }} value={brandName} />
                    </li>
                    <li>
                        <label htmlFor="">生产厂家：</label>
                        <Input onChange={this.inputHandle} data-type="purchasePoint" style={{ width: '65%' }} value={purchasePoint} />
                    </li>
                    <li>
                        <label htmlFor="">采购总量：</label>
                        <Input onChange={this.inputHandle} data-type="procurementTotal" style={{ width: '30%' }} value={procurementTotal} />
                        <Select onChange={this.handleSelect} value={procurementTotalUnitCode}  defaultValue={procurementTotalUnitCode} style={{ marginLeft: '2%', width: '33%' }}>
                        {
                            weightUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'procurementTotalUnitCode'} typename={'procurementTotalUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">使用量：</label>
                        <Input onChange={this.inputHandle} data-type="useAmount" style={{ width: '30%' }} value={useAmount} />
                        <Select onChange={this.handleSelect} value={useAmountUnitCode}  defaultValue={produceSummationUnitCode} style={{ marginLeft: '2%', width: '33%' }}>
                        {
                            processUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'useAmountUnitCode'} typename={'useAmountUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">单价：</label>
                        <Input onChange={this.inputHandle} data-type="perPrice" style={{ width: '30%' }} value={perPrice} />
                        <Select value={perPriceUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'perPriceUnitCode'} typename={'perPriceUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">成本：</label>
                        <Input onChange={this.inputHandle} data-type="perCost" style={{ width: '30%' }} value={perCost} />
                        <Select value={perCostUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleChange}>
                            {
                                costUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'perCostUnitCode'} typename={'perCostUnitText'} key={i} value={v.code}>{v.text}</Option>
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