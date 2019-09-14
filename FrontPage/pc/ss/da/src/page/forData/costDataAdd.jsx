import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import { Select,message, Cascader, DatePicker, Input, Button } from 'antd';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import * as api from "../api/api-forData";
import moment from 'moment';
// import Options from "../component/area";
const Option = Select.Option;
export default class CostDataAdd extends Component {
    constructor(props){
        super(props)
        this.state={
            areaData:[],                                //  地理区域数据
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            timeDimensionData:[{text:'全部',code:0}],   //  时间维度下拉列表
            originChannelData:[{text:'全部',code:0}],   //  来源渠道下拉列表
            regionLevelData:[{text:'全部',code:0}],     //  区域维度下拉列表
            cropBreedData:[{text:'全部',code:0}],       //  作物种类下拉列表
            cropStrainsData:[{text:'全部',code:0}],     //  作物品种下拉列表
            priceUnitData:[{text:'全部',code:0}],       //  价钱单位下拉列表
            costUnitData:[{text:'全部',code:0}],        //  成本单位下拉列表
            impExpPriceData:[{text:'全部',code:0}],        //  市斤成本单位下拉列表
            dataTimeTypeCode:0,                         //  时间维度默认code
            dataTimeTypeText:"全部",                    //  时间维度名称
            sourceChannelTypeCode:0,                    //  来源渠道默认code
            sourceChannelTypeText:"全部",               //  来源渠道默认名称
            areaLatitudeTypeCode: 0,                    //  区域维度id
            areaLatitudeTypeText: "全部",               //  区域维度名称
            cropTypeCode: 0,                            //  作物种类code
            cropTypeText: "全部",                       //  作物种类文本
            strainsCode: 0,                             //  作物品种code
            strainsText: "全部",                        //  作物品种文本
            acreCostUnitCode: 1,                        //  亩成本单位code
            acreCostUnitText: "元/亩",                 //  亩成本单位文本
            acreCost: "",                               //亩成本
            regionId: "",                               //  地理区域id
            regionName:"",                              //  地理区域名称
            cattyCostUnitCode: 1,                        //  市斤单位code
            cattyCostUnitText: "元/每斤",                //  市斤单位文本
            startTime:"",                               //  数据日期
            dataSources:"",                             //  数据来源
            name:""
        }
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
     //  修改时重新渲染数据
     initInput=(id)=>{
        // console.log(id)

        api.costDataSearch.send({id:id}).then(res=>{
            console.log(res)
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
                    acreCost: v.acreCost,//亩成本
                    acreCostUnitCode: v.acreCostUnitCode,//亩成本单位code
                    acreCostUnitText: v.acreCostUnitText,//亩成本单位文本
                    strainsText:v.strainsText,
                    artificialCost:v.artificialCost,
                    cattyCost: v.cattyCost,//市斤成本
                    cattyCostUnitCode: v.cattyCostUnitCode,//市斤成本单位code
                    cattyCostUnitText: v.cattyCostUnitText,//市斤成本单位文本
                    artificialCostUnitCode:v.artificialCostUnitCode,
                    artificialCostUnitText:v.artificialCostUnitText,
                    otherCost: v.otherCost,//其他总成本
                    otherCostUnitCode:v.otherCostUnitCode,//其他总成本单位code
                    otherCostUnitText: v.otherCostUnitText,//其他总成本单位文本
                    sourceChannelTypeCode:v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
                    inputCost: v.inputCost,//投入品总成本
                    inputCostUnitCode: v.inputCostUnitCode,//投入品总成本单位code
                    inputCostUnitText: v.inputCostUnitText,//投入品总成本单位文本
                    totalCost: v.totalCost,//总成本
                    totalCostUnitCode: v.totalCostUnitCode,//总成本单位code
                    totalCostUnitText: v.totalCostUnitText,//总成本单位文本
                    dataSources:v.daCommonField.dataSources,
                    dataTimeTypeCode:v.daCommonField.dataTimeTypeCode,
                    dataTimeTypeText:v.daCommonField.dataTimeTypeText,
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
            acreCost,//亩成本
            acreCostUnitCode,//亩成本单位code
            acreCostUnitText,//亩成本单位文本
            dataTimeTypeCode,
            dataTimeTypeText,
            artificialCost,
            artificialCostUnitCode,
            artificialCostUnitText,
            cattyCost,
            commonFieldId,
            inputCost,
            otherCost,
            totalCost,
            totalCostUnitCode,
            totalCostUnitText,
            cattyCostUnitText,
            cattyCostUnitCode,
            inputCostUnitCode,
            inputCostUnitText,
            otherCostUnitCode,
            otherCostUnitText,
            strainsCode,
            strainsText,
            isAdd
        } = this.state
        let params = {
            "acreCost": acreCost,//亩成本
            "acreCostUnitCode": acreCostUnitCode,//亩成本单位code
            "acreCostUnitText": acreCostUnitText,//亩成本单位文本
            "artificialCost": artificialCost,//人工总成本
            "artificialCostUnitCode": artificialCostUnitCode,//人工总成本单位code
            "artificialCostUnitText": artificialCostUnitText,//人工总成本单位文本
            "cattyCost": cattyCost,//市斤成本
            "cattyCostUnitCode": cattyCostUnitCode,//市斤成本单位code
            "cattyCostUnitText": cattyCostUnitText,//市斤成本单位文本
            "commonFieldId": commonFieldId,
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
                  "sourceChannelTypeText": sourceChannelTypeText,//来源渠道名称
            },
            "inputCost": inputCost,//投入品总成本
            "inputCostUnitCode": inputCostUnitCode,//投入品总成本单位code
            "inputCostUnitText": inputCostUnitText,//投入品总成本单位文本
            "name": name,//农户姓名
            "otherCost": otherCost,//其他总成本
            "otherCostUnitCode": otherCostUnitCode,//其他总成本单位code
            "otherCostUnitText": otherCostUnitText,//其他总成本单位文本
            "strainsCode": strainsCode,//作物品种code
            "strainsText": strainsText,//作物品种文本
            "totalCost": totalCost,//总成本
            "totalCostUnitCode": totalCostUnitCode,//总成本单位code
            "totalCostUnitText": totalCostUnitText//总成本单位文本
          }
          if( Number(isAdd) === 1){
            //  新增
            api.costDataAdd.send(params).then(res=>{
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
            api.costDataFix.send(params).then(res=>{
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
        //  价钱下拉方法
        this.initPriceUnit()
        //  成本单位下拉方法
        this.initCostUnit()
        //  市斤成本单位下拉方法
        this.initImpExpPrice()
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
    //  I市斤成本单位下拉方法
    initImpExpPrice(){
        api.impExpPrice.send().then(res=>{
            console.log(res)
            let impExpPriceNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    impExpPriceNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    impExpPriceData:impExpPriceNew
                },()=>{
                    // console.log(this.state.impExpPriceData)
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
            dataTimeTypeCode,
            sourceChannelTypeCode,
            areaLatitudeTypeCode,
            acreCostUnitCode,
            cattyCostUnitCode,
            totalCostUnitCode,
            otherCostUnitCode,
            artificialCostUnitCode,
            inputCostUnitCode,
            cropTypeCode,
            strainsCode,
            timeDimensionData,
            originChannelData,
            costUnitData,
            regionLevelData,
            cropBreedData,
            cropStrainsData,
            impExpPriceData,
            priceUnitData,
            startTime,
            dataSources,
            inputCost,
            artificialCost,
            otherCost,
            totalCost,
            acreCost,
            cattyCost,
            regionName,
            areaData,
            name
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
                        <label htmlFor="">农户姓名：</label>
                        <Input onChange={this.inputHandle} data-type="name" style={{ width: '65%' }} value={name} defaultValue={name} />
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
                        <label htmlFor="">投入总成本：</label>
                        <Input onChange={this.inputHandle} data-type="inputCost" value={inputCost}  style={{ width: '30%' }}/>
                        <Select value={inputCostUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'inputCostUnitCode'} typename={'inputCostUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">人工总成本：</label>
                        <Input onChange={this.inputHandle} data-type="artificialCost" value={artificialCost}  style={{ width: '30%' }}/>
                        <Select value={artificialCostUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'artificialCostUnitCode'} typename={'artificialCostUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">其他总成本：</label>
                        <Input onChange={this.inputHandle} data-type="otherCost" value={otherCost}  style={{ width: '30%' }}/>
                        <Select value={otherCostUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'otherCostUnitCode'} typename={'otherCostUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">总成本：</label>
                        <Input onChange={this.inputHandle} data-type="totalCost" value={totalCost}  style={{ width: '30%' }}/>
                        <Select value={totalCostUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'totalCostUnitCode'} typename={'totalCostUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">亩成本：</label>
                        <Input onChange={this.inputHandle} data-type="acreCost" value={acreCost}  style={{ width: '30%' }}/>
                        <Select value={acreCostUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                costUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'acreCostUnitCode'} typename={'acreCostUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">市斤成本：</label>
                        <Input onChange={this.inputHandle} data-type="cattyCost" value={cattyCost}  style={{ width: '30%' }}/>
                        <Select value={cattyCostUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                impExpPriceData.map((v,i)=>{
                                    return(
                                        <Option codenum={'cattyCostUnitCode'} typename={'cattyCostUnitText'} key={i} value={v.code}>{v.text}</Option>
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