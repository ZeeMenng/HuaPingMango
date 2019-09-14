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
            cropBreedData:[{text:'全部',code:0}],       //  作物种类下拉列表
            cropTypeCode: 0,                            //  作物种类code
            cropTypeText: "全部",                       //  作物种类文本
            strainsData:[{text:'全部',code:0}],         //  作物品种下拉列表
            strainsCode: 0,                             //作物品种code
            strainsText: "全部",                      //作物品种文本
            growArea: 0,                                //种植面积
            growAreaCode: 0,                            //种植面积单位code
            growAreaText: "",                     //种植面积单位文本
            areaUnitData:[{text:'全部',code:0}],         // 种植面积单位下拉列表
            growHousehold: 0,                           //种植户数
            organicArea: 0,                             //有机认证面积
            organicAreaCode: 0,                         //有机认证面积code
            organicAreaText: "",                  //有机认证面积文本
            fruithangingArea: 0,                        //挂果面积
            fruithangingAreaCode: 0,                    //挂果面积单位code
            fruithangingAreaText: "",             //挂果面积单位文本
            output: 0,                                  //产量
            outputPer: 0,                               //面积产量
            outputPerTotalCode: 0,                      //面积产量单位code
            outputPerTotalText: "",               //面积产量单位文本
            outputPerTotalData:[{text:'全部',code:0}],       //  面积产量下拉列表
            outputTotalCode: 0,                         //产量单位code
            outputTotalText: "",                  //产量单位文本
            outputTotalData:[{text:'全部',code:0}],       //  产量下拉列表
            outputValue: 0,                             //产值
            outputValueCode: 0,                         //产值单位code
            outputValueText: "",                  //产值单位文本
            outputValueData:[{text:'全部',code:0}],       //  产值下拉列表
            wholesaleSales: 0,                          //批发销售额
            wholesaleSalesCode: 0,                      //批发销售额单位code
            wholesaleSalesText: "",               //批发销售额单位文本
            onlineSaleamount: 0,                        //电商销售量
            onlineSaleamountCode: 0,                    //电商销售量单位code
            onlineSaleamountText: "",             //电商销售量单位文本
            onlineSales: 0,                             //电商销售额
            onlineSalesCode: 0,                         //电商销售额单位code
            onlineSalesText: "",                  //电商销售额单位文本
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
                    outputTotalData:processUnitArr
                },()=>{
                    // console.log(this.state.processUnitData)
                })
            }
        })
    }
     // 面积产量下拉
     initProcessUnit() {
        api.processUnit.send().then(res=>{
            let processUnitArr = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    processUnitArr[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    outputPerTotalData:processUnitArr
                },()=>{
                    // console.log(this.state.processUnitData)
                })
            }
        })
    }
    // 产值下拉
    initForecastUnit() {
        api.priceUnit.send().then(res=>{
            let processUnitArr = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    processUnitArr[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    outputValueData:processUnitArr
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
    // 种植面积
    initareaUnit() {
        api.areaUnit.send().then(res=>{
            console.log(res)
            let areaUnitArr = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    areaUnitArr[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    areaUnitData:areaUnitArr
                },()=>{
                    // console.log(this.state.processUnitData)
                })
            }
        })
    }
    initInput=(id)=>{
        api.dIStatisticsDataGetModel.send({id:id}).then(res=>{
            console.log(res.data)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    cropTypeCode: v.cropTypeCode,
                    cropTypeText: v.cropTypeText,
                    areaLatitudeTypeCode: v.daCommonField.areaLatitudeTypeCode,
                    areaLatitudeTypeText: v.daCommonField.areaLatitudeTypeText,
                    startTime: v.daCommonField.startTime,
                    dataSources: v.daCommonField.dataSources,
                    dataTimeTypeCode: v.daCommonField.dataTimeTypeCode,
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,
                    regionId: v.daCommonField.regionId,
                    regionName: v.daCommonField.regionName,
                    sourceChannelTypeCode: v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,
                    fruithangingArea: v.fruithangingArea,
                    fruithangingAreaCode: v.fruithangingAreaCode,
                    fruithangingAreaText: v.fruithangingAreaText,
                    fruithangingAreaUnit: v.fruithangingAreaUnit,
                    growArea: v.growArea,
                    growAreaCode: v.growAreaCode,
                    growAreaText: v.growAreaText,
                    growHousehold: v.growHousehold,
                    name: v.name,
                    onlineSaleamount: v.onlineSaleamount,
                    onlineSaleamountCode: v.onlineSaleamountCode,
                    onlineSaleamountText: v.onlineSaleamountText,
                    onlineSales: v.onlineSales,
                    onlineSalesCode: v.onlineSalesCode,
                    onlineSalesText: v.onlineSalesText,
                    organicArea: v.organicArea,
                    organicAreaCode: v.organicAreaCode,
                    organicAreaText: v.organicAreaText,
                    output: v.output,
                    outputValue: v.outputValue,
                    outputValueCode: v.outputValueCode,
                    outputValueText: v.outputValueText,
                    outputPer: v.outputPer,
                    outputPerTotalCode: v.outputPerTotalCode,
                    outputPerTotalText: v.outputPerTotalText,
                    outputTotalCode: v.outputTotalCode,
                    outputTotalText: v.outputTotalText,
                    strainsCode: v.strainsCode,
                    strainsText: v.strainsText,
                    wholesaleSales: v.wholesaleSales,
                    wholesaleSalesCode: v.wholesaleSalesCode,
                    wholesaleSalesText: v.wholesaleSalesText,
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
            fruithangingArea,
            fruithangingAreaCode,
            fruithangingAreaText,
            growArea,
            growAreaCode,
            growAreaText,
            growHousehold,
            name,
            onlineSaleamount,
            onlineSaleamountCode,
            onlineSaleamountText,
            onlineSales,
            onlineSalesCode,
            onlineSalesText,
            organicArea,
            organicAreaCode,
            organicAreaText,
            output,
            outputValue,
            outputValueCode,
            outputValueText,
            outputPer,
            outputPerTotalCode,
            outputPerTotalText,
            outputTotalCode,
            outputTotalText,
            strainsCode,
            strainsText,
            wholesaleSales,
            wholesaleSalesCode,
            wholesaleSalesText,
            isAdd
        } = this.state
        let params = {
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
            "fruithangingArea": fruithangingArea,//挂果面积
            "fruithangingAreaCode": fruithangingAreaCode,//挂果面积单位code
            "fruithangingAreaText": fruithangingAreaText,//挂果面积单位文本
            "growArea": growArea,//种植面积
            "growAreaCode": growAreaCode,//种植面积单位code
            "growAreaText": growAreaText,//种植面积单位文本
            "growHousehold": growHousehold,//种植户数
            "name": name,//数据主体名称
            "onlineSaleamount": onlineSaleamount,//电商销售量
            "onlineSaleamountCode": onlineSaleamountCode,//电商销售量单位code
            "onlineSaleamountText": onlineSaleamountText,//电商销售量单位文本
            "onlineSales": onlineSales,//电商销售额
            "onlineSalesCode": onlineSalesCode,//电商销售额单位code
            "onlineSalesText": onlineSalesText,//电商销售额单位文本
            "organicArea": organicArea,//有机认证面积
            "organicAreaCode": organicAreaCode,//有机认证面积code
            "organicAreaText": organicAreaText,//有机认证面积文本
            "output": output,//产量
            "outputPer": outputPer,//面积产量
            "outputPerTotalCode": outputPerTotalCode,//面积产量单位code
            "outputPerTotalText": outputPerTotalText,//面积产量单位文本
            "outputTotalCode": outputTotalCode,//产量单位code
            "outputTotalText": outputTotalText,//产量单位文本
            "outputValue": outputValue,//产值
            "outputValueCode": outputValueCode,//产值单位code
            "outputValueText": outputValueText,//产值单位文本
            "strainsCode": strainsCode,//作物品种code
            "strainsText": strainsText,//作物品种文本
            "wholesaleSales": wholesaleSales,//批发销售额
            "wholesaleSalesCode": wholesaleSalesCode,//批发销售额单位code
            "wholesaleSalesText": wholesaleSalesText,//批发销售额单位文本
        }
        if( Number(isAdd) === 1){
            //  新增
            api.dIStatisticsDataAdd.send(params).then(res=>{
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
            api.dIStatisticsDataUpdate.send(params).then(res=>{
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
        //   产量初始化
        this.initProcessUnit()
        //  产值初始化
        this.initForecastUnit()
        //  种类
        this.initCropBreed()
        //  品种
        this.initStatuestrains()
        this.initWeightUnit()
        this.initareaUnit()
    }
    render() {
        const {
            dataTimeTypeCode,
            timeDimensionData,
            startTime,
            sourceChannelTypeCode,
            dataSources,
            areaLatitudeTypeCode,
            name,
            output,
            outputTotalCode,
            outputValue,
            outputValueCode,
            originChannelData,
            regionLevelData,
            cropBreedData,
            outputTotalData,
            outputValueData,
            cropTypeCode,
            strainsData,
            strainsCode,
            areaUnitData,
            growAreaCode,
            organicAreaCode,
            fruithangingAreaCode,
            outputPerTotalCode,
            wholesaleSalesCode,
            onlineSalesCode,
            growArea,
            growHousehold,
            organicArea,
            fruithangingArea,
            outputPer,
            wholesaleSales,
            onlineSales,
            outputPerTotalData,
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
                        <label htmlFor="">种植面积：</label>
                        <Input  onChange={this.inputHandle} data-type="growArea" style={{ width: '30%' }} value={growArea} defaultValue={growArea} />
                        <Select defaultValue={areaUnitData[0].code} value={growAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            areaUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'growAreaCode'} typename={'growAreaText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li className="position">
                        <label htmlFor="">种植户数：</label>
                        <Input  onChange={this.inputHandle} data-type="growHousehold" style={{ width: '65%' }} value={growHousehold} defaultValue={growHousehold} />
                        <i>个</i>
                    </li>
                    <li>
                        <label htmlFor="" style={{fontSize:'12px'}}>有机认证面积：</label>
                        <Input  onChange={this.inputHandle} data-type="organicArea" style={{ width: '30%' }} value={organicArea} defaultValue={organicArea} />
                        <Select defaultValue={areaUnitData[0].code} value={organicAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            areaUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'organicAreaCode'} typename={'organicAreaText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">挂果面积：</label>
                        <Input  onChange={this.inputHandle} data-type="fruithangingArea" style={{ width: '30%' }} value={fruithangingArea} defaultValue={fruithangingArea} />
                        <Select defaultValue={areaUnitData[0].code} value={fruithangingAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            areaUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'fruithangingAreaCode'} typename={'fruithangingAreaText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产量：</label>
                        <Input onChange={this.inputHandle} data-type="output" style={{ width: '30%' }} value={output} defaultValue={output} />
                        <Select defaultValue={outputTotalData[0].code} value={outputTotalCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            outputTotalData.map((v,i)=>{
                                return(
                                    <Option codenum={'outputTotalCode'} typename={'outputTotalText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">面积产量：</label>
                        <Input onChange={this.inputHandle} data-type="outputPer" style={{ width: '30%' }} value={outputPer} defaultValue={outputPer} />
                        <Select defaultValue={outputPerTotalData[0].code} value={outputPerTotalCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            outputPerTotalData.map((v,i)=>{
                                return(
                                    <Option codenum={'outputPerTotalCode'} typename={'outputPerTotalText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产值：</label>
                        <Input onChange={this.inputHandle} data-type="outputValue" style={{ width: '30%' }} value={outputValue} defaultValue={outputValue} />
                        <Select defaultValue={outputValueData[0].code} value={outputValueCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            outputValueData.map((v,i)=>{
                                return(
                                    <Option codenum={'outputValueCode'} typename={'outputValueText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">批发销售额：</label>
                        <Input onChange={this.inputHandle} data-type="wholesaleSales" style={{ width: '30%' }} value={wholesaleSales} defaultValue={wholesaleSales} />
                        <Select defaultValue={outputValueData[0].code} value={wholesaleSalesCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            outputValueData.map((v,i)=>{
                                return(
                                    <Option codenum={'wholesaleSalesCode'} typename={'wholesaleSalesText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">电商销售额：</label>
                        <Input onChange={this.inputHandle} data-type="onlineSales" style={{ width: '30%' }} value={onlineSales} defaultValue={onlineSales} />
                        <Select defaultValue={outputValueData[0].code} value={onlineSalesCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            outputValueData.map((v,i)=>{
                                return(
                                    <Option codenum={'onlineSalesCode'} typename={'onlineSalesText'} key={i} value={v.code}>{v.text}</Option>
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