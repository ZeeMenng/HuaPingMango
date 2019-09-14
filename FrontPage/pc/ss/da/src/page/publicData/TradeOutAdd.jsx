import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Button, message, Cascader } from 'antd';
import * as api from "../api/api-publicData";
import moment from 'moment';
// import Options from "../component/area";
const Option = Select.Option;
export default class PriceDataAdd extends Component {
    constructor(props) {
        super(props)
        this.state = {
            areaData:[],                                //  地理区域数据
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            remark: '',
            dataTimeTypeCode: 0,
            timeDimensionData: [{text:'全部',code:0}],
            exportVolume: '',
            name: '',
            exportAmount: '',
            exportPrice: '',
            dataTimeTypeText: '',
            exportCountryText: '',
            exportCountryCode: 0,                     //  出口国家code
            exportCountryText: "",                      //  出口国家文本
            exportCountryData:[{text:'全部',code:0}],    //  出口国家
            yearData: [],
            startTime: ''
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
    initareaUnit() {
        api.getCountry.send().then(res=>{
            console.log(res)
            let areaUnitArr = []
            if(res.isSuccess){
                console.log(res.data)
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
    initInput=(id)=>{
        api.TradeOutGet.send({id:id}).then(res=>{
            console.log(res.data)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,
                    dataTimeTypeCode: v.daCommonField.dataTimeTypeCode,
                    exportVolume: v.exportVolume,
                    remark: v.daCommonField.remark,
                    exportAmount: v.exportAmount,
                    exportPrice: v.exportPrice,
                    dataTimeTypeText: v.dataTimeTypeText,
                    startTime: v.daCommonField.startTime,
                    exportCountryText: v.exportCountryText,
                    exportCountryCode: v.exportCountryCode
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
    initYear (){
        var date=new Date;
        var year=date.getFullYear();
        year = year - 3;
        var newArr = [];
        for (var i=0;i<10;i++){
            year ++;
            newArr.push(year)
            this.state.yearData = newArr;
        }
    }
    //  采集时间选择
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            startTime:date
        })
    }
    // 上报
    formSaveFn = () =>{
        const {
            isAdd,
            dataTimeTypeCode,
            timeDimensionData,
            exportVolume,
            name,
            remark,
            exportAmount,
            exportPrice,
            dataTimeTypeText,
            exportCountryText,
            exportCountryCode,
            startTime
        } = this.state
        let params = {
            "cropTypeCode":"1",
			"cropTypeText":"芒果",
			"strainsCode":"0",
			"strainsText":"全部",
			"exportCountryCode":exportCountryCode,//进口国家编码
			"exportCountryText":exportCountryText,//进口国家名称
			"exportVolume":exportVolume,//进口金额
			"exportVolumeUnitCode":"5",//进口金额类型编码
			"exportVolumeUnitText":"万美元",//进口金额类型名称
			"exportAmount":exportAmount,//进口量
			"exportAmountUnitCode":"2",//进口量类型编码
			"exportAmountUnitText":"吨",//进口量类型名称
			"exportPrice":exportPrice,//进口价格
			"exportPriceUnitCode":"4",//进口价格类型编码
			"exportPriceUnitText":"美元/公斤",//进口价格类型名称
			"daCommonField":{
				"dataTimeTypeCode" : "1",
				"dataTimeTypeText" : dataTimeTypeText,
				"areaLatitudeTypeCode" : "1",
				"areaLatitudeTypeText" : "国家",
				"regionId" : "031000000",
				"regionName" : "中国",
				"sourceChannelTypeCode" : "7",
				"sourceChannelTypeText" : "企业web填报",
				"auditStatusCode" : "0",
				"auditStatusText" : "待审核",
				"startTime" : startTime+'-01-01 00:00:00',//年度
				"endTime" : "",//年度
				"remark" : remark//备注
			}
        }
        if( Number(isAdd) === 1){
            //  新增
            api.TradeOutAdd.send(params).then(res=>{
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
            api.TradeOutFix.send(params).then(res=>{
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
        this.initareaUnit()
        this.initYear()
    }
    render() {
        const {
            dataTimeTypeCode,
            dataTimeTypeText,
            timeDimensionData,
            exportVolume,
            name,
            remark,
            exportAmount,
            exportPrice,
            exportCountryText,
            yearData,
            exportCountryCode,
            exportCountryData,
            startTime
        } = this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit2 clearfix">
                    <li>
                        <label htmlFor="">年度：</label>
                        <Select placeholder={'请选择'} style={{ width: '65%' }} onChange={this.timeSelect} value={startTime}>
                        {
                            yearData.map((v,i)=>{
                                return(
                                    <Option codenum={'v'} typename={'v'} key={v} value={v}>{v}</Option>
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
                    <li className="position">
                        <label htmlFor="">出口金额：</label>
                        <Input onChange={this.inputHandle} data-type="exportVolume" style={{ width: '65%' }} value={exportVolume} defaultValue={exportVolume} />
                        <i>万美元</i>
                    </li>
                    <li className="position">
                        <label htmlFor="">出口量：</label>
                        <Input onChange={this.inputHandle} data-type="exportAmount" style={{ width: '65%' }} value={exportAmount} defaultValue={exportAmount} />
                        <i>万吨</i>
                    </li>
                    <li className="position">
                        <label htmlFor="">出口价格：</label>
                        <Input onChange={this.inputHandle} data-type="exportPrice" style={{ width: '65%' }} value={exportPrice} defaultValue={exportPrice} />
                        <i>美元/公斤</i>
                    </li>
                    <li>
                        <label htmlFor="">备注：</label>
                        <Input onChange={this.inputHandle} data-type="remark" style={{ width: '65%' }} value={remark} defaultValue={remark} />
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