import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Button, message, Cascader } from 'antd';
import * as api from "../api/api-publicData";
import moment from 'moment';
import Options from "../component/area";
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
            yearData: [],
            bottomPriceUnitCode: 0,
            topPriceUnitCode: 0,
            perPriceUnitCode: 0,
            formArr: [{"cropTypeCode":"1","cropTypeText":"芒果","growAreaCode":"2","growAreaText":"万亩","growArea": ''}],
            growArea: '',
            productTotal: '',
            peasantCount: '',
            cropStrainsData:[{text:'全部',code:0}],      //  作物品种下拉列表
            dc: ''
        }
        this.formAdd = this.formAdd.bind(this)
        this.formRemove=this.formRemove.bind(this)
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
        let type = e.target.getAttribute('data-type')
        console.log(type)
        this.setState({
            [type]:e.target.value
        },()=>{
            //  console.log(this.state[type])
            if (type == 'productTotal' && this.state.growArea) {
                var tt = (this.state.productTotal / this.state.growArea).toFixed(2)
                this.setState({
                    dc: tt
                })
            }
        })
    }
    //  input监听
    inputHandle1 = (e) =>{
        let type = e.target.getAttribute('data-type')
        let name = e.target.getAttribute('name')
        let newArr = this.state.formArr
        newArr[type][name] = e.target.value
        var ss = 0;
        for (let i in newArr) {
            ss = Number(newArr[i].growArea) + ss
        }
        this.setState({
            growArea: ss
        },()=>{
            console.log(this.state.growArea)
            if (this.state.productTotal && this.state.growArea) {
                var tt = (this.state.productTotal / this.state.growArea).toFixed(2)
                this.setState({
                    dc: tt
                })
            }
        })
    }
    handleSelect1 = (e,option) =>{
        console.log(option)
        let type = option.props.typename;
        let code = option.props.codenum;
        let text = option.props.children;
        let ttt = option.props.cc
        let newArr = this.state.formArr
        newArr[ttt][type] = text
        newArr[ttt][code] = e
        this.setState({
            [type]:text,
            [code]:e
        },()=>{
            // console.log(this.state[type],this.state[code])
        })
        console.log(this.state.formArr)
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
        api.MainProducingGet.send({id:id}).then(res=>{
            console.log(res.data)
            if(res.isSuccess){
                let v = res.data;
                var ss = 0;
                for (let i in v.daGrowYieldList) {
                    ss = Number(v.daGrowYieldList[i].growArea) + ss
                }
                this.setState({
                    "peasantCount": v.daGrowYieldList[0].peasantCount,
                    "productTotal" :v.daGrowYieldList[0].productTotal,
                    "growArea": ss,
                    "formArr": v.daGrowYieldList,
                    "auditStatusCode":v.daCommonField.auditStatusCode,
                    "auditStatusText": v.daCommonField.auditStatusText,
                    "auditerSuggestion": v.daCommonField.auditerSuggestion,
                    "startTime": v.daCommonField.startTime,
                    "regionId": v.daCommonField.regionId,
                    "regionName":v.daCommonField.regionName
                },
                ()=>
                //  地理区域数据方法
                this.areaDataGet()
            )
            var tt = (this.state.productTotal / this.state.growArea).toFixed(2)
                this.setState({
                    dc: tt
                })
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
    formAdd(){
        let newArr = [];
        newArr.push({"cropTypeCode":"1","cropTypeText":"芒果","growAreaCode":"2","growAreaText":"万亩","growArea": ''});
        this.setState({
            formArr: this.state.formArr.concat(newArr)
        })
    }
    formRemove(e) {
        let newArr = this.state.formArr
        console.log(this.state.formArr)
        let index = e.target.getAttribute('data-type')
        console.log(index)
        newArr.splice(index,1)
        console.log(this.state.formArr)
        this.setState({
            formArr: this.state.formArr
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
            dataTimeTypeCode,
            timeDimensionData,
            name,
            formArr,
            auditStatusText,
            auditerSuggestion,
            auditStatusCode,
            growArea,
            productTotal,
            peasantCount,
            startTime,
            endTime,
            regionId,
            regionName
        } = this.state
        let params = {
                "growAreaCode":"2",
                "growAreaText":"万亩",
                "cropTypeCode":"1",
                "cropTypeText":"芒果",
                "strainsCode":"0",
                "strainsText":"全部",
                "productTotal":productTotal,//产量
                "productTotalCode":"4",
                "productTotalText":"万吨",
                "peasantCount":peasantCount,//种植户数
                "daCommonField":{
                    "dataTimeTypeCode" : "1",
                    "dataTimeTypeText" : "年",
                    "areaLatitudeTypeCode" : "4",//1：国家，2：省，3：市，4：县/区，5：乡/镇，6：村/组/社区，7：农户个体，8：企业个体',
                    "areaLatitudeTypeText" : "县/区",//根据所选的地区来填写
                    "regionId" : regionId,//地区编码
                    "regionName" : regionName,//地区名称
                    "sourceChannelTypeCode" : "7",
                    "sourceChannelTypeText" : "企业web填报",
                    "auditStatusCode" : auditStatusCode,//0：待审核，1:审核通过，2：审核不通过'
                    "auditStatusText" : auditStatusText,
                    "startTime" : startTime+ '-01-01 00:00:00',//年度
                    "endTime" :endTime,//年度
                    "auditerSuggestion" : auditerSuggestion//审核意见
                },
                "daGrowYieldList":formArr
        }
        if( Number(isAdd) === 1){
            //  新增
            console.log(params)
            api.MainProducingAdd.send(params).then(res=>{
                console.log(res)
                this.goBackPage();
            })
          }else{
            //  修改
            let id = isAdd.split('-')[0];
            let commonId = isAdd.split('-')[1];
            params.id = id;
            params.daCommonField.id = commonId;
            api.MainProducingFix.send(params).then(res=>{
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
    timeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            startTime:date
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
    componentDidMount(){
        if(Number(this.state.isAdd) !== 1){
            let id = this.state.isAdd;
            this.initInput(id.split('-')[1])
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
        this.initYear()
    }
    render() {
        const {
            dataTimeTypeCode,
            timeDimensionData,
            name,
            formArr,
            auditStatusText,
            auditerSuggestion,
            auditStatusCode,
            growArea,
            productTotal,
            peasantCount,
            strainsData,
            strainsCode,
            yearData,
            startTime,
            dc,
            regionName
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
                    </li>
                    <li>
                        <label htmlFor="">地区：</label>
                        <Input onChange={this.inputHandle} data-type="name" style={{ width: '65%' }} value={name} defaultValue={name} />
                    </li> */}
                    <li>
                        <label htmlFor="">年份：</label>
                        <Select style={{ width: '65%' }} onChange={this.timeSelect} value={startTime}>
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
                        <label htmlFor="">种植户数：</label>
                        <Input onChange={this.inputHandle} data-type="peasantCount" style={{ width: '65%' }} value={peasantCount} defaultValue={peasantCount} />
                    </li>
                    <li>
                        <label htmlFor="">鲜果总产量（万吨）：</label>
                        <Input onChange={this.inputHandle} data-type="productTotal" style={{ width: '65%' }} value={productTotal} defaultValue={productTotal} />
                    </li>
                    <li>
                        <label htmlFor="">种植总面积（万亩）：</label>
                        <Input onChange={this.inputHandle} data-type="growArea" style={{ width: '65%' }} value={growArea} defaultValue={growArea} disabled/>
                    </li>
                    <li>
                        <label htmlFor="">单产：</label>
                        <Input onChange={this.inputHandle} data-type="dc" style={{ width: '65%' }} value={dc} defaultValue={dc} disabled />
                    </li>
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
                    <li className="item-line">
                    </li>
                    <div style={{border: '1px solid #ccc', padding: '0 5px', borderRadius: '5px', cursor: 'pointer'}} onClick={this.formAdd}>+添加</div>
                    {
                        formArr.map((item,index)=>{
                            return(
                                <div key={index} style={{ width:'100%', display: "flex" }} >
                                    <li>
                                        <label htmlFor="">芒果品种：</label>
                                        <Select defaultValue={item.code} value={item.strainsCode} style={{ width: '65%' }} onChange={this.handleSelect1}>
                                        {
                                            strainsData.map((v,i)=>{
                                                return(
                                                    <Option codenum={'strainsCode'} typename={'strainsText'} cc={index} key={i} value={v.code}>{v.text}</Option>
                                                )
                                            })
                                        }
                                        </Select>
                                    </li>
                                    <li>
                                        <label htmlFor="">种植面积：</label>
                                        <Input onChange={this.inputHandle1} style={{ width: '65%' }} data-type={index} name="growArea" defaultValue={item.growArea}/>
                                    </li>
                                    <div style={{ height:'30px', lineHeight: "30px", margin: "15px 0" }} onClick={this.formRemove} data-type={index}>-</div>
                                </div>
                            )
                        })
                    }
                    <li className="item-line">
                    </li>
                    <li className="item">
                        <label htmlFor="">审核意见：</label>
                        <Input value={auditerSuggestion} onChange={this.inputHandle} data-type="auditerSuggestion" style={{ width: '65%' }} defaultValue="" />
                    </li>
                    <li className="item">
                        <label htmlFor="">审核结果：</label>
                        <Select onChange={this.handleSelect} value={auditStatusText} style={{ width: '65%' }} >
                            <Option codenum={'auditStatusCode'} typename={'auditStatusText'} value="1">通过</Option>
                            <Option codenum={'auditStatusCode'} typename={'auditStatusText'} value="2">不通过</Option>
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