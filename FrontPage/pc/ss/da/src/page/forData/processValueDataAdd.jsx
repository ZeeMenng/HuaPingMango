import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Button, Cascader, message } from 'antd';
import * as api from "../api/api-forData-second";
// import Options from "../component/area";
import moment from 'moment';
const Option = Select.Option;
export default class ProcessValueDataAdd extends Component {
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
            cropBreedData:[{text:'全部',code:0}],       //  作物种类下拉列表
            cropTypeCode: 0,                            //  作物种类code
            cropTypeText: "全部",                       //  作物种类文本
            processStrainsData:[{text:'全部',code:0}],  //  加工品种下拉列表
            processStrainsCode:0,                       //  加工品种默认code
            processStrainsText: "全部",                  //  加工品种文本
            processUnitData:[{text:'全部',code:0}],     //  产量下拉列表
            processUnitCode: 0,                         //  产量默认code
            yieldText: "全部",                          //   产量文本
            forecastUnitData:[{text:'全部',code:0}],    //  产值下拉列表
            forecastUnitCode: 0,                         //  产值默认code
            startTime:"",                               //  数据日期
            dataSources: "",                            //  数据来源
            name: "",                                  //  名称
            yieldName: "",                                  // 产量
            outputValue: "",                             // 产值
            outputValueText: "",                        // 产值文本
            regionId: "",                               //  地理区域id
            regionName:""                              //  地理区域名称
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
    initInput=(id)=>{
        api.daIndustryProcess.send({id:id}).then(res=>{
            console.log(res.data)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    name:v.name,
                    regionId:v.daCommonField.regionId,
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    yieldName:v.yield,
                    outputValue:v.outputValue,
                    forecastUnitCode:v.outputValueCode,
                    outputValueText:v.outputValueText,
                    processUnitCode:v.yieldCode,
                    yieldText:v.yieldText,
                    proportion:v.proportion,
                    areaLatitudeTypeCode:v.daCommonField.areaLatitudeTypeCode,
                    areaLatitudeTypeText:v.daCommonField.areaLatitudeTypeText,
                    cropTypeCode:v.cropTypeCode,
                    cropTypeText:v.cropTypeText,
                    areaCode:v.areaCode,
                    areaText:v.areaText,
                    strainsCode:v.strainsCode,
                    strainsText:v.strainsText,
                    sourceChannelTypeCode:v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
                    dataSources:v.daCommonField.dataSources,
                    area:v.area,
                    dataTimeTypeCode:v.daCommonField.dataTimeTypeCode,
                    dataTimeTypeText:v.daCommonField.dataTimeTypeText,
                    processStrainsCode:v.processStrainsCode
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
    //  数据日期
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
             startTime:dateString+' 00:00:00'
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
    //  加工品种下拉方法
    initProcessStrains(){
        api.processStrains.send().then(res=>{
            // console.log(this.state.processStrainsData)
            let processStrainsArr = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    processStrainsArr[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    processStrainsData:processStrainsArr
                },()=>{
                    // console.log(this.state.processStrainsData)
                })
            }
        })
    }
    // 产品产量下拉
    initProcessUnit() {
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
                    processUnitData:processUnitArr
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
                    forecastUnitData:processUnitArr
                },()=>{
                    // console.log(this.state.processUnitData)
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
    // 上报
    formSaveFn = () =>{
        const {
            dataTimeTypeCode,
            dataTimeTypeText,
            startTime,
            sourceChannelTypeCode,
            sourceChannelTypeText,
            dataSources,
            areaLatitudeTypeCode,
            areaLatitudeTypeText,
            regionId,
            regionName,
            name,
            cropTypeCode,
            cropTypeText,
            processStrainsCode,
            processStrainsText,
            yieldName,
            processUnitCode,
            yieldText,
            outputValue,
            forecastUnitCode,
            outputValueText,
            isAdd       
        } = this.state
        let params = {
            "cropTypeCode": cropTypeCode,//作物种类code
            "cropTypeText": cropTypeText,//作物种类文本
            "processStrainsCode":processStrainsCode,//加工品种code
            "processStrainsText":processStrainsText,//加工品种文本
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
            "outputValue": outputValue,//产值
            "outputValueCode": forecastUnitCode,//产值单位单位code
            "outputValueText": outputValueText,//产值单位文本
            "strainsCode": processStrainsCode,//作物品种code
            "strainsText": processStrainsText,//作物品种文本
            "yield": yieldName,//产量
            "yieldCode": processUnitCode,//产量单位code
            "yieldText": yieldText,//产量单位文本
        }
        if( Number(isAdd) === 1){
            //  新增
            api.processValueDataAdd.send(params).then(res=>{
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
            api.processValueDataFix.send(params).then(res=>{
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
         //  加工品种下拉方法
        this.initProcessStrains()
        //   产量初始化
        this.initProcessUnit()
        //  产值初始化
        this.initForecastUnit()
    }
    render() {
        const {
            dataTimeTypeCode,
            sourceChannelTypeCode,
            timeDimensionData,
            originChannelData,
            regionLevelData,
            areaLatitudeTypeCode,
            cropBreedData,
            cropTypeCode,
            processStrainsData,
            processStrainsCode,
            processUnitData,
            processUnitCode,
            forecastUnitData,
            forecastUnitCode,
            startTime,
            dataSources,
            name,
            yieldName,
            outputValue,
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
                        <label htmlFor="">加工品种：</label>
                        <Select defaultValue={processStrainsData[0].code} value={processStrainsCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            processStrainsData.map((v,i)=>{
                                return(
                                    <Option codenum={'processStrainsCode'} typename={'processStrainsText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产量：</label>
                        <Input onChange={this.inputHandle} data-type="yieldName" style={{ width: '30%' }} value={yieldName} defaultValue={yieldName} />
                        <Select defaultValue={processUnitData[0].code} value={processUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            processUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'processUnitCode'} typename={'processUnitCode'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">产值：</label>
                        <Input onChange={this.inputHandle} data-type="outputValue" style={{ width: '30%' }} value={outputValue} defaultValue={outputValue} />
                        <Select defaultValue={forecastUnitData[0].code} value={forecastUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                        {
                            forecastUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'forecastUnitCode'} typename={'forecastUnitCode'} key={i} value={v.code}>{v.text}</Option>
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