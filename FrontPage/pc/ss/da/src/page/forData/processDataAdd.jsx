import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import * as api from "../api/api-forData-second";
import moment from "moment";
import { Select, DatePicker, Cascader, Input, Button, message } from 'antd';
import Options from "../component/area";
const Option = Select.Option;
export default class ProcessDataAdd extends Component {
    constructor(props) {
        super(props)
        this.state = {
            areaData:[],                                    //  地理区域数据
            isAdd: props.match.params.id,                     //  地址栏传参判断是新增：1 || 修改
            timeDimensionData: [{ text: '全部', code: 0 }],   //  时间维度下拉列表
            originChannelData: [{ text: '全部', code: 0 }],   //  来源渠道下拉列表
            regionLevelData: [{ text: '全部', code: 0 }],     //  区域维度下拉列表
            cropBreedData: [{ text: '全部', code: 0 }],        //  作物种类下拉列表
            cropStrainsData: [{ text: '全部', code: 0 }],      //  作物品种下拉列表
            areaUnitData: [{ text: '请选择', code: 0 }],       //  面积单位下拉列表
            priceUnitData: [{ text: '全部', code: 0 }],        //  价钱单位下拉列表
            weightUnitData: [{ text: '全部', code: 0 }],        //重量单位下拉列表
            consumeTotal: "", //原材料消耗总量
            consumeTotalCode: 0, //原材料消耗总量单位
            consumeTotalText: "全部",//原材料消耗总量单位文本
            consumeTotalUnit: "", //通用原材料消耗总量
            cropTypeCode: 0, //作物种类code
            cropTypeText: "全部", //作物种类文本
            areaLatitudeTypeCode: 0,//区域维度id
            areaLatitudeTypeText: "全部",//区域维度名称
            startTime: "",//数据日期
            dataSources: "",//数据来源
            dataTimeTypeCode: 0,//时间维度id
            dataTimeTypeText: "全部",//时间维度名称
            regionId: "",//地理区域id
            regionName: "",//地理区域名称
            sourceChannelTypeCode: 0,//来源渠道id
            sourceChannelTypeText: "全部",//来源渠道名称
            name: "", //数据主体名称
            outputValue: 0,//产值
            outputValueCode: 0,//产值单位code
            outputValueText: "全部",//产值单位文本
            outputValueUnit: 0,//通用产值
            processStrainsCode: 0,//加工品品种code
            processStrainsText: "全部",//加工品品种文本
            processTime: "",//加工时间
            productName: "", //产品名称文本
            productTotal: "",//总产量
            productTotalCode: 0,//总产量单位code
            productTotalText: "全部",//总产量单位文本
            productTotalUnit: ""//通用总产量
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
    //  修改时重新渲染数据
    initInput = (id) => {
        // console.log(id)
        api.processDataSearch.send({ id: id }).then(res => {
            console.log(res)
            if (res.isSuccess) {
                let v = res.data;
                this.setState({
                    name: v.name,//数据主体名称
                    consumeTotal: v.consumeTotal, //原材料消耗总量
                    consumeTotalCode: v.consumeTotalCode, //原材料消耗总量单位
                    consumeTotalText: v.consumeTotalText,//原材料消耗总量单位文本
                    consumeTotalUnit: v.consumeTotalUnit, //通用原材料消耗总量
                    cropTypeCode: v.cropTypeCode,//作物种类code
                    cropTypeText: v.cropTypeText,//作物种类文本
                    areaLatitudeTypeCode: v.daCommonField.areaLatitudeTypeCode,//区域维度id
                    areaLatitudeTypeText: v.areaLatitudeTypeText,//区域维度名称
                    startTime: v.daCommonField.startTime,//数据日期
                    dataSources: v.dataSources,//数据来源
                    dataTimeTypeCode: v.dataTimeTypeCode,//时间维度id
                    dataTimeTypeText: v.dataTimeTypeText,//时间维度名称
                    regionId: v.regionId,//地理区域id
                    regionName: v.regionName,//地理区域名称
                    sourceChannelTypeCode: v.sourceChannelTypeCode,//来源渠道id
                    sourceChannelTypeText: v.sourceChannelTypeText,//来源渠道名称
                    outputValue: v.outputValue,//产值
                    outputValueCode: v.outputValueCode,//产值单位code
                    outputValueText: v.outputValueText,//产值单位文本
                    strainsCode: v.strainsCode,//作物品种code
                    strainsText: v.strainsText,//作物品种文本
                    processStrainsCode: v.processStrainsCode,//加工品品种code
                    processStrainsText: v.processStrainsText,//加工品品种文本
                    processTime: v.processTime,//加工时间
                    productName: v.productName, //产品名称文本
                    productTotal: v.productTotal,//总产量
                    productTotalCode: v.productTotalCode,//总产量单位code
                    productTotalText: v.productTotalText,//总产量单位文本
                    productTotalUnit: v.productTotalUnit//通用总产量
                },
                    ()=>
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
        })
    }
    //  保存与修改方法
    formSaveFn = () => {
        const { name, cropTypeCode, cropTypeText, areaLatitudeTypeCode, areaLatitudeTypeText, startTime, dataSources,
            dataTimeTypeCode, dataTimeTypeText, regionId, regionName, sourceChannelTypeCode, sourceChannelTypeText,
            consumeTotal, consumeTotalCode, consumeTotalText, consumeTotalUnit, outputValue, outputValueCode,
            outputValueText, productTotal, productTotalCode, productTotalText, isAdd, outputValueUnit,
            processStrainsCode, processStrainsText, processTime, productName, productTotalUnit,strainsCode,strainsText
        } = this.state
        let params = {
            "name": name,//数据主体名称
            "consumeTotal": consumeTotal, //原材料消耗总量
            "consumeTotalCode": consumeTotalCode, //原材料消耗总量单位
            "consumeTotalText": consumeTotalText,//原材料消耗总量单位文本
            "consumeTotalUnit": consumeTotalUnit, //通用原材料消耗总量
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
            "outputValue": outputValue,//产值
            "outputValueCode": outputValueCode,//产值单位code
            "outputValueText": outputValueText,//产值单位文本
            "outputValueUnit": outputValueUnit,//通用产值
            "processStrainsCode": processStrainsCode,//加工品品种code
            "processStrainsText": processStrainsText,//加工品品种文本
            "processTime": processTime,//加工时间
            "productName": productName, //产品名称文本
            "productTotal": productTotal,//总产量
            "productTotalCode": productTotalCode,//总产量单位code
            "productTotalText": productTotalText,//总产量单位文本
            "productTotalUnit": productTotalUnit//通用总产量
        }
        if (Number(isAdd) === 1) {
            //  新增
            api.processDataAdd.send(params).then(res => {
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
            //  修改
            let id = isAdd.split('-')[0];
            let commonId = isAdd.split('-')[1];
            params.id = id;
            params.daCommonField.id = commonId;
            api.processDataFix.send(params).then(res => {
                // console.log(res)
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


    //  数据日期
    timeSelect = (date, dateString) => {
        // console.log(date, dateString);
        this.setState({
            startTime: dateString + ' 00:00:00'
        })
    }
    // 加工时间
    timeProcess = (date, dateString) => {
        // console.log(date, dateString);
        this.setState({
            processTime: dateString + ' 00:00:00'
        })
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
        //  作物种类下拉方法
        this.initCropBreed()
        //  作物品种下拉方法
        this.initCropStrains()
        //面积单位下拉
        this.initAreaUnit()
        //  产品产量单位下拉方法
        // this.initProcessUnit()
        //  价钱下拉方法
        this.initPriceUnit()
        //重量下拉单位方法
        this.initWeightUnit()
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
    //  重量单位下拉方法
    initWeightUnit() {
        api.weightUnit.send().then(res => {
            // console.log(res)
            let weightUnitNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    weightUnitNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    weightUnitData: weightUnitNew
                }, () => {
                    // console.log(this.state.weightUnitData)
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
    //  区域维度下拉方法
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
    //  作物种类下拉方法
    initCropBreed() {
        api.cropBreed.send().then(res => {
            // console.log(res)
            let cropBreedNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    cropBreedNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    cropBreedData: this.state.cropBreedData.concat(cropBreedNew)
                }, () => {
                    // console.log(this.state.regionLevelData)
                })
            }
        })
    }
    //  作物品种下拉方法
    initCropStrains() {
        api.processStrains.send().then(res => {
            // console.log(res)
            let cropStrainsNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    cropStrainsNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    cropStrainsData: cropStrainsNew
                }, () => {
                })
            }
        })
    }
    //  面积单位下拉方法
    initAreaUnit() {
        api.areaUnit.send().then(res => {
            // console.log(res)
            let areaUnitNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    areaUnitNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    areaUnitData: areaUnitNew
                }, () => {
                    // console.log(this.state.priceUnitData)
                })
            }
        })
    }
    // //  产品产量单位下拉方法
    // initProcessUnit() {
    //     api.processUnit.send().then(res => {
    //         // console.log(res)
    //         let processUnitNew = []
    //         if (res.isSuccess) {
    //             res.data.forEach((v, i) => {
    //                 processUnitNew[i] = {
    //                     text: v.text,
    //                     code: v.code
    //                 }
    //             })
    //             this.setState({
    //                 processUnitData: processUnitNew
    //             }, () => {
    //                 // console.log(this.state.processUnitData)
    //             })
    //         }
    //     })
    // }
    //  价钱下拉方法
    initPriceUnit() {
        api.priceUnit.send().then(res => {
            // console.log(res)
            let priceUnitNew = []
            if (res.isSuccess) {
                res.data.forEach((v, i) => {
                    priceUnitNew[i] = {
                        text: v.text,
                        code: v.code
                    }
                })
                this.setState({
                    priceUnitData: priceUnitNew
                }, () => {
                    // console.log(this.state.priceUnitData)
                })
            }
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
            dataTimeTypeCode, 
            timeDimensionData, 
            startTime, 
            originChannelData, 
            sourceChannelTypeCode,
            dataSources, 
            regionLevelData, 
            areaLatitudeTypeCode, 
            name, 
            cropBreedData, 
            cropTypeCode, 
            cropStrainsData,
            strainsCode, 
            productName,  
            productTotal, 
            productTotalCode, 
            priceUnitData, 
            outputValueCode,
            outputValue, 
            processTime, 
            consumeTotal, 
            consumeTotalCode, 
            weightUnitData,
            regionName,
            areaData,
            processStrainsCode
        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit clearfix">
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
                        <DatePicker locale={locale} allowClear={false} value={startTime ? moment(new Date(startTime)) : null} onChange={this.timeSelect} style={{ width: '65%' }} />
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
                    <li>
                        <label htmlFor="">数据来源：</label>
                        <Input onChange={this.inputHandle} data-type="dataSources" style={{ width: '65%' }} value={dataSources} defaultValue={dataSources} />
                    </li>
                    {/* <li>
                        <label htmlFor="">区域维度：</label>
                        <Select defaultValue={regionLevelData[0].code} value={areaLatitudeTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
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
                            options={Options} 
                            onChange={this.areaFn} 
                            />
                    </li>
                    <li>
                        <label htmlFor="">名称：</label>
                        <Input onChange={this.inputHandle} data-type="name" style={{ width: '65%' }} value={name} defaultValue={name} />
                    </li>
                    <li>
                        <label htmlFor="">作物种类：</label>
                        <Select defaultValue={cropBreedData[0].code} value={cropTypeCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                            {
                                cropBreedData.map((v, i) => {
                                    return (
                                        <Option codenum={'cropTypeCode'} typename={'cropTypeText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">加工品种：</label>
                        <Select defaultValue={cropStrainsData[0].code} value={processStrainsCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                            {
                                cropStrainsData.map((v, i) => {
                                    return (
                                        <Option codenum={'processStrainsCode'} typename={'processStrainsText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产品名称：</label>
                        <Input onChange={this.inputHandle} data-type="productName" style={{ width: '65%' }} value={productName} defaultValue={productName} />
                    </li>
                    <li>
                        <label htmlFor="">产品量：</label>
                        <Input onChange={this.inputHandle} data-type="productTotal" style={{ width: '30%' }} defaultValue={productTotal} value={productTotal} />
                        <Select defaultValue={weightUnitData[0].code} value={productTotalCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                weightUnitData.map((v, i) => {
                                    return (
                                        <Option codenum={'productTotalCode'} typename={'productTotalCode'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产值：</label>
                        <Input onChange={this.inputHandle} data-type="outputValue" style={{ width: '30%' }} defaultValue={outputValue} value={outputValue} />
                        <Select defaultValue={priceUnitData[0].code} value={outputValueCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v, i) => {
                                    return (
                                        <Option codenum={'outputValueCode'} typename={'outputValueText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">加工时间：</label>
                        <DatePicker locale={locale} allowClear={false} value={processTime ? moment(new Date(processTime)) : null} onChange={this.timeProcess} style={{ width: '65%' }} />
                    </li>
                    <li>
                        <label htmlFor="" style={{ fontSize: '12px' }}>原材料消耗量：</label>
                        <Input onChange={this.inputHandle} data-type="consumeTotal" style={{ width: '30%' }} defaultValue={consumeTotal} value={consumeTotal} />
                        <Select defaultValue={weightUnitData[0].code} value={consumeTotalCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                weightUnitData.map((v, i) => {
                                    return (
                                        <Option codenum={'consumeTotalCode'} typename={'consumeTotalText'} key={i} value={v.code}>{v.text}</Option>
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