import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select,message, Cascader,  DatePicker, Input, Button } from 'antd';
import * as api from "../api/api-forData-second";
import moment from 'moment';
import Options from "../component/area";
const Option = Select.Option;
export default class ECommerceSellDataAdd extends Component {
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
            ebusinessTypeData:[{text:'请选择',code:0}],   //  电商平台下拉列表
            modeOfPaymentData:[{text:'请选择',code:0}],   //  支付方式下拉列表
            processUnitData:[],                         //  产品产量单位下拉列表
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
            saleAmountUnitCode: 1,                    //  交易量单位code
            saleAmountUnitText: "千克",                 //  交易量单位文本
            actualIncomeUnitCode: 1,                    //  实收金额code
            actualIncomeUnitText: "元",                    //  实收金额文本
            ecommerceCode: 0,                           //  电商平台code
            ecommerceText: "请选择",                        //  电商平台文本
            startTime:"",                               //  数据日期
            saleAmount:"",                             //  销售量
            actualIncome:"",                             //  实收金额
            dataSources:"",                             //  数据来源
            enterpriseName: "",                         //  企业名称
	        saleAreaCode: "",                           //  销售城市code
	        saleAreaText: "",                           //  销售城市文本
            saleRegionCode: "",                         //  销售区域code
            saleRegionText: "",                         //  销售区域文本
            areaCode:"",                                //  收件区域id
            areaText:"",                                 //  收件区域文本
            courierFeeUnitCode: 1,                      //  快递费用单纯code
            courierFeeUnitText: "元",                     //  快递费用单位文本
            payTypeCode: 1,                             //  支付方式code
            payTypeText: "",                            //  支付方式文本
            orderTime:"",                               //  订单日期
            orderId:"",                                 //  订单号
            addressee:"",                               //  收件人
            deliveryAddress:"",                         //  收件地址
            contactPhone:"",                         //  联系电话
            courier:"",                                 //  快递公司
            courierFee:"",                              //  快递费用
            courierNumber:"",                           //  运单号
            payAccount:"",                              //  交易账户
            ecommerceType:""
        }
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
     //  修改时重新渲染数据
     initInput=(id)=>{
        // console.log(id)

        api.shopFormSearch.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    ecommerceType:v.ecommerceType,
                    regionId:v.daCommonField.regionId,
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    saleAmount:v.saleAmount,
                    addressee:v.addressee,
                    contactPhone:v.contactPhone,                         //  联系电话
                    courier:v.courier,                                 //  快递公司
                    deliveryAddress:v.deliveryAddress,
                    actualIncome:v.actualIncome,
                    ecommerceCode: v.ecommerceCode,                           //  电商平台code
                    ecommerceText: v.ecommerceText,                        //  电商平台文本
                    payTypeCode: v.payTypeCode,                             //  支付方式code
                    payTypeText: v.payTypeText,                            //  支付方式文本
                    enterpriseName:v.enterpriseName,
                    orderTime:v.orderTime,
                    orderId:v.orderId,
                    courierFeeUnitCode: v.courierFeeUnitCode,                      //  快递费用单纯code
                    courierFeeUnitText: v.courierFeeUnitText,                     //  快递费用单位文本
                    courierFee:v.courierFee,                              //  快递费用
                    courierNumber:v.courierNumber,                           //  运单号
                    payAccount:v.payAccount,                              //  交易账户
                    areaCode:v.areaCode,//收件区域id
                    areaText:v.areaText,//收件区域文本
                    actualIncomeUnitCode:v.actualIncomeUnitCode,
                    actualIncomeUnitText:v.actualIncomeUnitText,
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
            areaCode:value[value.length-1],
            areaText:name.join('/')
        })
    }
    //  保存与修改方法
    formSaveFn = () =>{
        const {
            cropTypeCode,
            cropTypeText,
            startTime,
            ecommerceType,
            areaLatitudeTypeCode,
            areaLatitudeTypeText,
            sourceChannelTypeCode,
            sourceChannelTypeText,
            courierFeeUnitCode ,                      //  快递费用单纯code
            courierFeeUnitText ,                     //  快递费用单位文本
            dataSources,
            addressee,
            payTypeText,
            payTypeCode,
            deliveryAddress,
            regionId,
            regionName,
            dataTimeTypeCode,
            dataTimeTypeText,
            enterpriseName,
            strainsCode,
            strainsText,
            contactPhone,                         //  联系电话
            courier,                                 //  快递公司
            ecommerceCode ,                           //  电商平台code
            ecommerceText ,                        //  电商平台文本
            courierFee ,                              //  快递费用
            courierNumber ,                           //  运单号
            payAccount ,                              //  交易账户
            saleAreaCode,
            saleAreaText,
            areaCode,               //收件区域id
            areaText,               //收件区域文本
            saleRegionCode,     //销售区域code
            saleRegionText,     //销售区域文本
            saleAmount,
            saleAmountUnitCode,    //销售量单位code
            saleAmountUnitText,    //销售量单位文本
            orderTime,
            orderId,
            actualIncome,
            actualIncomeUnitCode,//实收金额code
            actualIncomeUnitText,//实收金额文本
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
            "ecommerceType": ecommerceType,//平台名称
            "enterpriseName": enterpriseName,//企业名称
            "saleAreaCode": saleAreaCode,//销售城市code
            "saleAreaText": saleAreaText,//销售城市文本
            "saleRegionCode": saleRegionCode,//销售区域code
            "saleRegionText": saleRegionText,//销售区域文本
            "courierFeeUnitCode": courierFeeUnitCode,                      //  快递费用单纯code
            "courierFeeUnitText": courierFeeUnitText,                     //  快递费用单位文本
            "payTypeCode": payTypeCode,                             //  支付方式code
            "payTypeText": payTypeText,                            //  支付方式文本
            "contactPhone":contactPhone,                         //  联系电话
            "courier":courier,                                 //  快递公司
            "ecommerceCode": ecommerceCode,                           //  电商平台code
            "ecommerceText": ecommerceText,             //  电商平台文本
            "areaCode":areaCode,        //收件区域id
            "areaText":areaText,        //收件区域文本                     
            "strainsCode": strainsCode,//产品品种code
            "strainsText": strainsText,//产品品种文本
            "saleAmount": saleAmount,//销售量
            "addressee": addressee,//收件人
            "courierFee":courierFee,                              //  快递费用
            "courierNumber":courierNumber,                           //  运单号
            "payAccount":payAccount,                              //  交易账户
            "deliveryAddress": deliveryAddress,//收件地址
            "saleAmountUnitCode": saleAmountUnitCode,//销售量单位code
            "saleAmountUnitText": saleAmountUnitText,//销售量单位文本
            "orderTime": orderTime,//订单日期
            "orderId":orderId,      //订单号 
            "actualIncome": actualIncome,//实收金额
            "actualIncomeUnitCode": actualIncomeUnitCode,//实收金额code
            "actualIncomeUnitText": actualIncomeUnitText,//实收金额文本
          }
          if( Number(isAdd) === 1){
            //  新增
            api.shopFormAdd.send(params).then(res=>{
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
            api.shopFormFix.send(params).then(res=>{
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
        //  电商平台下拉方法
        this.initeBusinessType()
        //  支付方式下拉方法
        this.initeModeOfPayment()
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
    //  电商平台下拉方法
    initeBusinessType(){
        api.ebusinessType.send().then(res=>{
            // console.log(res)
            let ebusinessTypeNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    ebusinessTypeNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    ebusinessTypeData:ebusinessTypeNew
                },()=>{
                    // console.log(this.state.ebusinessTypeData)
                })
            }
        })
    }
    //  电商平台下拉方法
    initeModeOfPayment(){
        api.modeOfPayment.send().then(res=>{
            // console.log(res)
            let modeOfPaymentNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    modeOfPaymentNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    modeOfPaymentData:modeOfPaymentNew
                },()=>{
                    // console.log(this.state.modeOfPaymentData)
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
    //  订单日期
    orderSelect=(date, dateString)=> {
        // console.log(date, dateString);
        this.setState({
            orderTime:dateString+' 00:00:00'
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
            ecommerceCode,
            saleAmountUnitCode,
            courierFeeUnitCode,
            actualIncomeUnitCode,
            cropTypeCode,
            payTypeCode,
            strainsCode,
            timeDimensionData,
            originChannelData,
            regionLevelData,
            ebusinessTypeData,
            cropBreedData,
            cropStrainsData,
            modeOfPaymentData,
            priceUnitData,
            weightUnitData,
            startTime,
            dataSources,
            ecommerceType,
            saleAmount,
            actualIncome,
            enterpriseName,
            orderId,
            deliveryAddress,
            contactPhone,
            courier,
            courierFee,
            courierNumber,
            payAccount,
            addressee,
            orderTime,
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
                            options={Options} 
                            onChange={this.areaFn} 
                            />
                    </li>
                    <li>
                        <label htmlFor="">平台名称：</label>
                        <Input onChange={this.inputHandle} data-type="ecommerceType" style={{ width: '65%' }} value={ecommerceType} defaultValue={ecommerceType} />
                    </li>
                    <li>
                        <label htmlFor="">订单日期：</label>
                        <DatePicker allowClear={false}  value={orderTime?moment(new Date(orderTime)):null} onChange={this.orderSelect}  locale={locale} style={{ width: '65%' }} />
                    </li>
                    <li>
                        <label htmlFor="">订单号：</label>
                        <Input onChange={this.inputHandle} data-type="orderId"  style={{ width: '65%' }} value={orderId} />
                    </li>
                    <li>
                        <label htmlFor="">平台号：</label>
                        <Select defaultValue={ebusinessTypeData[0].code} value={ecommerceCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            ebusinessTypeData.map((v,i)=>{
                                return(
                                    <Option codenum={'ecommerceCode'} typename={'ecommerceText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">企业名称：</label>
                        <Input onChange={this.inputHandle} data-type="enterpriseName"  style={{ width: '65%' }} value={enterpriseName} />
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
                        <label htmlFor="">销售数量：</label>
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
                        <label htmlFor="">实收金额：</label>
                        <Input onChange={this.inputHandle} data-type="actualIncome" style={{ width: '30%' }} value={actualIncome} />
                        <Select value={actualIncomeUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'actualIncomeUnitCode'} typename={'actualIncomeUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">收件人：</label>
                        <Input onChange={this.inputHandle} data-type="addressee"  style={{ width: '65%' }} value={addressee} />
                    </li>
                    <li>
                        <label htmlFor="">收件区域：</label>
                        <Cascader 
                            locale={locale}
                            placeholder={'请选择'}
                            style={{ width: '65%',padding:0,verticalAlign: "baseline" }}  
                            options={Options} 
                            onChange={this.areaFn1}
                            />
                    </li>
                    <li>
                        <label htmlFor="">收件地址：</label>
                        <Input onChange={this.inputHandle} data-type="deliveryAddress"  style={{ width: '65%' }} value={deliveryAddress} />
                    </li>
                    <li>
                        <label htmlFor="">联系电话：</label>
                        <Input onChange={this.inputHandle} data-type="contactPhone"  style={{ width: '65%' }} value={contactPhone} />
                    </li>
                    <li>
                        <label htmlFor="">快递公司：</label>
                        <Input onChange={this.inputHandle} data-type="courier"  style={{ width: '65%' }} value={courier} />
                    </li>
                    <li>
                        <label htmlFor="">快递费用：</label>
                        <Input onChange={this.inputHandle} data-type="courierFee"  style={{ width: '30%' }} value={courierFee} />
                        <Select value={courierFeeUnitCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                priceUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'courierFeeUnitCode'} typename={'courierFeeUnitText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">运单号：</label>
                        <Input onChange={this.inputHandle} data-type="courierNumber"  style={{ width: '65%' }} value={courierNumber} />
                    </li>
                    <li>
                        <label htmlFor="">支付方式：</label>
                        <Select value={Number(payTypeCode)} style={{ width: '65%' }} onChange={this.handleSelect}>
                            {
                                modeOfPaymentData.map((v,i)=>{
                                    return(
                                        <Option codenum={'payTypeCode'} typename={'payTypeText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">交易账户：</label>
                        <Input onChange={this.inputHandle} data-type="payAccount"  style={{ width: '65%' }} value={payAccount} />
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