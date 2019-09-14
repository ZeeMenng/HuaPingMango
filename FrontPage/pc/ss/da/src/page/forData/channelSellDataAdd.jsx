import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, message, Cascader, DatePicker, Input, Button } from 'antd';
import * as api from "../api/api-forData-second";
import moment from 'moment';
import Options from "../component/area";
const Option = Select.Option;
export default class ChannelSellDataAdd extends Component {
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
            weightUnitData:[{text:'全部',code:0}],      //  重量单位下拉列表
            channlDisData:[{text:'全部',code:1}],       //  销售渠道下拉列表
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
            regionId: "",                               //  地理区域id
            regionName:"",                              //  地理区域名称
            saleAmountUnitCode: 1,                     //  交易量单位code
            saleAmountUnitText: "千克",                //  交易量单位文本
            saleVolumeUnitCode: 1,                     //  交易额code
            saleVolumeUnitText: "元",                  //  交易额文本
            saleChannelTypeCode: 1,                     //  销售渠道code
            saleChannelTypeText: "全部",                    //  销售渠道文本
            startTime:"",                               //  数据日期
            saleAmount:"",                             //  交易量
            saleVolume:"",                             //  交易额
            dataSources:"",                             //  数据来源
            seller: "",                                   //  销售商
	        saleAreaCode: "",                           //  销售城市code
	        saleAreaText: "",                           //  销售城市文本
            saleRegionCode: "",                         //  销售区域code
            saleRegionText: "",                         //  销售区域文本
            saleTime:"",                                //  销售日期
            name:""
        }
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
     //  修改时重新渲染数据
     initInput=(id)=>{
        // console.log(id)

        api.channelSellSearch.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    name:v.name,
                    regionId:v.daCommonField.regionId,
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    saleAmount:v.saleAmount,
                    saleVolume:v.saleVolume,
                    seller:v.seller,
                    saleTime:v.saleTime,
                    saleVolumeUnitCode:v.saleVolumeUnitCode,
                    saleVolumeUnitText:v.saleVolumeUnitText,
                    saleChannelTypeCode:v.saleChannelTypeCode,
                    saleChannelTypeText:v.saleChannelTypeText,
                    saleAmountUnitCode:v.saleAmountUnitCode,
                    saleAmountUnitText:v.saleAmountUnitText,
                    areaLatitudeTypeCode:v.daCommonField.areaLatitudeTypeCode,
                    areaLatitudeTypeText:v.daCommonField.areaLatitudeTypeText,
                    cropTypeCode:v.cropTypeCode,
                    cropTypeText:v.cropTypeText,
                    strainsCode:v.strainsCode,
                    strainsText:v.strainsText,
                    sourceChannelTypeCode:v.daCommonField.sourceChannelTypeCode,
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
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
    //  地理区域选择
    areaFn1=(value,selectedOptions)=> {
        console.log(selectedOptions)
        let name =[];
        selectedOptions.forEach((v,i)=>{
            name[i] = v.label
        })
        this.setState({
            saleRegionCode:value[value.length-1],
            saleRegionText:name.join('/')
        })
    }
    //  地理区域选择
    areaFn2=(value,selectedOptions)=> {
        console.log(selectedOptions)
        let name =[];
        selectedOptions.forEach((v,i)=>{
            name[i] = v.label
        })
        this.setState({
            saleAreaCode:value[value.length-1],
            saleAreaText:name.join('/')
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
            saleChannelTypeCode,
            saleChannelTypeText,
            dataSources,
            regionId,
            regionName,
            dataTimeTypeCode,
            dataTimeTypeText,
            seller,
            strainsCode,
            strainsText,
            saleAreaCode,
            saleAreaText,
            saleRegionCode,     //销售区域code
            saleRegionText,     //销售区域文本
            saleAmount,
            saleAmountUnitCode,    //交易量单位code
            saleAmountUnitText,    //交易量单位文本
            saleTime,
            saleVolume,
            saleVolumeUnitCode,//交易额code
            saleVolumeUnitText,//交易额文本
            isAdd
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
                  "sourceChannelTypeText": sourceChannelTypeText//来源渠道名称
          
            },
            "name": name,//农户姓名
            "seller": seller,//销售商
            "saleAreaCode": saleAreaCode,//销售城市code
            "saleAreaText": saleAreaText,//销售城市文本
            "saleRegionCode": saleRegionCode,//销售区域code
            "saleRegionText": saleRegionText,//销售区域文本
            "strainsCode": strainsCode,//产品品种code
            "strainsText": strainsText,//产品品种文本
            "saleChannelTypeCode":saleChannelTypeCode,
            "saleChannelTypeText":saleChannelTypeText,
            "saleAmount": saleAmount,//交易量
            "saleAmountUnitCode": saleAmountUnitCode,//交易量单位code
            "saleAmountUnitText": saleAmountUnitText,//交易量单位文本
            "saleTime": saleTime,//销售日期
            "saleVolume": saleVolume,//交易额
            "saleVolumeUnitCode": saleVolumeUnitCode,//交易额code
            "saleVolumeUnitText": saleVolumeUnitText,//交易额文本
          }
          if( Number(isAdd) === 1){
            //  新增
            api.channelSellAdd.send(params).then(res=>{
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
            api.channelSellFix.send(params).then(res=>{
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
        //  重量单位下拉方法
        this.initWeightUnit()
        //  销售渠道下拉方法
        this.initChannelDis()
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
    //  重量单位下拉方法
    initWeightUnit(){
        api.weightUnit.send().then(res=>{
            // console.log(res)
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
    //  销售渠道方法
    initChannelDis(){
        api.channlDistributione.send().then(res=>{
            // console.log(res)
            let channlDisNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    channlDisNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    channlDisData:channlDisNew
                },()=>{
                    // console.log(this.state.channlDisData)
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
    //  销售日期
    tradeSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            saleTime:dateString+' 00:00:00'
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
            saleAmountUnitCode,
            saleVolumeUnitCode,
            cropTypeCode,
            strainsCode,
            saleChannelTypeCode,
            timeDimensionData,
            originChannelData,
            regionLevelData,
            cropBreedData,
            cropStrainsData,
            priceUnitData,
            weightUnitData,
            channlDisData,
            startTime,
            dataSources,
            name,
            saleAmount,
            saleVolume,
            seller,
            saleTime,
            regionName,
            areaData
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
                        <label htmlFor="">销售量：</label>
                        <Input onChange={this.inputHandle} data-type="saleAmount" value={saleAmount}  style={{ width: '30%' }}/>
                        <Select onChange={this.handleSelect} value={saleAmountUnitCode}  defaultValue={saleAmountUnitCode} style={{ marginLeft: '2%', width: '33%' }}>
                        {
                            weightUnitData.map((v,i)=>{
                                return(
                                    <Option codenum={'saleAmountUnitCode'} typename={'saleAmountUnitText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">销售额：</label>
                        <Input onChange={this.inputHandle} data-type="saleVolume" style={{ width: '30%' }} value={saleVolume} />
                        <Select value={saleVolumeUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'saleVolumeUnitCode'} typename={'saleVolumeUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">销售区域：</label>
                        <Cascader 
                            locale={locale}
                            placeholder={'请选择'}
                            style={{ width: '65%',padding:0,verticalAlign: "baseline" }}  
                            options={Options} 
                            onChange={this.areaFn1} 
                            />
                    </li>
                    <li>
                        <label htmlFor="">销售城市：</label>
                        <Cascader 
                            locale={locale}
                            placeholder={'请选择'}
                            style={{ width: '65%',padding:0,verticalAlign: "baseline" }}  
                            options={Options} 
                            onChange={this.areaFn2}
                            />
                    </li>
                    <li>
                        <label htmlFor="">销售商：</label>
                        <Input onChange={this.inputHandle} data-type="seller" style={{ width: '65%' }} defaultValue={seller} />
                    </li>
                    <li>
                        <label htmlFor="">销售日期：</label>
                        <DatePicker locale={locale}  value={saleTime?moment(new Date(saleTime)):null} onChange={this.tradeSelect} style={{ width: '65%' }} />
                    </li>
                    <li>
                        <label htmlFor="">销售渠道：</label>
                        <Select value={Number(saleChannelTypeCode)} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {console.log(channlDisData)}
                        {
                            channlDisData.map((v,i)=>{
                                return(
                                    <Option codenum={'saleChannelTypeCode'} typename={'saleChannelTypeText'} key={i} value={v.code}>{v.text}</Option>
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