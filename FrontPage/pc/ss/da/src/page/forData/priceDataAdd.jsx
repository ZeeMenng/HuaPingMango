import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Button, message, Cascader } from 'antd';
import * as api from "../api/api-forData-second";
import moment from 'moment';
// import Options from "../component/area";
const Option = Select.Option;
export default class PriceDataAdd extends Component {
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
            priceTypeCode: 0,                           //  价格类型code
            priceTypeText: "",                          //  价格类型文本
            priceTypeData:[{text:'全部',code:0}],       //  价格类型
            importVolumeData:[{text:'全部',code:0}],       //  价钱
            averagePriceUnitCode: 0,
            bottomPriceUnitCode: 0,
            topPriceUnitCode: 0,
            perPriceUnitCode: 0
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
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
    onChange = (date, dateString) => {
        console.log(date, dateString);
    }
    goBackPage = () => {
        this.props.history.go(-1)
    }
    //  价格类型
    initPriceType(){
        api.priceType.send().then(res=>{
            let timeDimensionNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    timeDimensionNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    priceTypeData:this.state.priceTypeData.concat(timeDimensionNew)
                },()=>{
                    // console.log(this.state.timeDimensionData)
                })
            }
        })
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
    //  数据日期
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
             startTime:dateString+' 00:00:00'
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
    //  价钱下拉方法
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
        api.daMarketPriceGetModel.send({id:id}).then(res=>{
            console.log(res.data)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    "averagePrice": v.averagePrice,//平均价
                    "averagePriceUnit": v.averagePriceUnit,//通用平均价
                    "averagePriceUnitCode": v.averagePriceUnitCode,//平均价单位code
                    "averagePriceUnitText": v.averagePriceUnitText,//平均价单位文本
                    "bottomPrice": v.bottomPrice,//最低价
                    "bottomPriceUnit": v.bottomPriceUnit,//通用最低价
                    "bottomPriceUnitCode": v.bottomPriceUnitCode,//最低价单位code
                    "bottomPriceUnitText": v.bottomPriceUnitText,//最低价单位文本
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
                    "name": v.name,//数据主体名称
                    "perPrice": v.perPrice,//单价
                    "perPriceUnit": v.perPriceUnit,//通用单价
                    "perPriceUnitCode": v.perPriceUnitCode,//单价单位code
                    "perPriceUnitText": v.perPriceUnitText,//单价单位文本
                    "priceRangeCode": v.priceRangeCode,//价格区间code
                    "priceRangeText": v.priceRangeText,//价格区间文本
                    "priceTypeCode": v.priceTypeCode,//价格类型code
                    "priceTypeText": v.priceTypeText,//价格类型文本
                    "strainsCode": v.strainsCode,//产品品种code
                    "strainsText": v.strainsText,//产品品种文本
                    "topPrice": v.topPrice,//最高价
                    "topPriceUnit": v.topPriceUnit,//通用最高价
                    "topPriceUnitCode": v.topPriceUnitCode,//最高价单位code
                    "topPriceUnitText": v.topPriceUnitText//最高价单位文本
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
            averagePrice,
            averagePriceUnit,
            averagePriceUnitCode,
            averagePriceUnitText,
            bottomPrice,
            bottomPriceUnit,
            bottomPriceUnitCode,
            bottomPriceUnitText,
            cropTypeCode,
            cropTypeText,
            areaLatitudeTypeCode,
            areaLatitudeTypeText,
            startTime,
            dataSources,
            dataTimeTypeCode,
            dataTimeTypeText,
            regionId,
            regionName,
            sourceChannelTypeCode,
            sourceChannelTypeText,
            name,
            perPrice,
            perPriceUnit,
            perPriceUnitCode,
            perPriceUnitText,
            priceRangeCode,
            priceRangeText,
            priceTypeCode,
            priceTypeText,
            strainsCode,
            strainsText,
            topPrice,
            topPriceUnit,
            topPriceUnitCode,
            topPriceUnitText
        } = this.state
        let params = {
            "averagePrice": averagePrice,//平均价
            "averagePriceUnit": averagePriceUnit,//通用平均价
            "averagePriceUnitCode": averagePriceUnitCode,//平均价单位code
            "averagePriceUnitText": averagePriceUnitText,//平均价单位文本
            "bottomPrice": bottomPrice,//最低价
            "bottomPriceUnit": bottomPriceUnit,//通用最低价
            "bottomPriceUnitCode": bottomPriceUnitCode,//最低价单位code
            "bottomPriceUnitText": bottomPriceUnitText,//最低价单位文本
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
            "name": name,//数据主体名称
            "perPrice": perPrice,//单价
            "perPriceUnit": perPriceUnit,//通用单价
            "perPriceUnitCode": perPriceUnitCode,//单价单位code
            "perPriceUnitText": perPriceUnitText,//单价单位文本
            "priceRangeCode": priceRangeCode,//价格区间code
            "priceRangeText": priceRangeText,//价格区间文本
            "priceTypeCode": priceTypeCode,//价格类型code
            "priceTypeText": priceTypeText,//价格类型文本
            "strainsCode": strainsCode,//产品品种code
            "strainsText": strainsText,//产品品种文本
            "topPrice": topPrice,//最高价
            "topPriceUnit": topPriceUnit,//通用最高价
            "topPriceUnitCode": topPriceUnitCode,//最高价单位code
            "topPriceUnitText": topPriceUnitText,//最高价单位文本
        }
        if( Number(isAdd) === 1){
            //  新增
            api.daMarketPriceAdd.send(params).then(res=>{
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
            api.daMarketPriceFix.send(params).then(res=>{
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
        this.initStatuestrains()
        this.initPriceType()
        // 价钱单位
        this.initForecastUnit()
    }
    render() {
        const {
            dataTimeTypeCode,
            timeDimensionData,
            startTime,
            sourceChannelTypeCode,
            originChannelData,
            dataSources,
            regionLevelData,
            areaLatitudeTypeCode,
            cropBreedData,
            cropTypeCode,
            strainsData,
            strainsCode,
            name,
            priceTypeData,
            priceTypeCode,
            perPrice,
            importVolumeData,
            perPriceUnitCode,
            averagePrice,
            averagePriceUnitCode,
            bottomPrice,
            topPrice,
            bottomPriceUnitCode,
            topPriceUnitCode,
            regionName,
            areaData
        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit clearfix">
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
                        <label htmlFor="">价格类型：</label>
                        <Select defaultValue={priceTypeData[0].code} value={priceTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            priceTypeData.map((v,i)=>{
                                return(
                                    <Option codenum={'priceTypeCode'} typename={'priceTypeText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">单价：</label>
                        <Input onChange={this.inputHandle} data-type="perPrice" style={{ width: '30%' }} value={perPrice} defaultValue={perPrice} />
                        <Select defaultValue={importVolumeData[0].code} value={perPriceUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            importVolumeData.map((v,i)=>{
                                return(
                                    <Option codenum={'perPriceUnitCode'} typename={'perPriceUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">平均价：</label>
                        <Input onChange={this.inputHandle} data-type="averagePrice" style={{ width: '30%' }} value={averagePrice} defaultValue={averagePrice} />
                        <Select defaultValue={importVolumeData[0].code} value={averagePriceUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            importVolumeData.map((v,i)=>{
                                return(
                                    <Option codenum={'averagePriceUnitCode'} typename={'averagePriceUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">最低价：</label>
                        <Input onChange={this.inputHandle} data-type="bottomPrice" style={{ width: '30%' }} value={bottomPrice} defaultValue={bottomPrice} />
                        <Select defaultValue={importVolumeData[0].code} value={bottomPriceUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            importVolumeData.map((v,i)=>{
                                return(
                                    <Option codenum={'bottomPriceUnitCode'} typename={'bottomPriceUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">最高价：</label>
                        <Input onChange={this.inputHandle} data-type="topPrice" style={{ width: '30%' }} value={topPrice} defaultValue={topPrice} />
                        <Select defaultValue={importVolumeData[0].code} value={topPriceUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            importVolumeData.map((v,i)=>{
                                return(
                                    <Option codenum={'topPriceUnitCode'} typename={'topPriceUnitText'} key={i} value={v.code}>{v.text}</Option>
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