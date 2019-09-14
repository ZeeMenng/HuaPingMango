import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, message, Cascader,DatePicker, Input, Button } from 'antd';
import * as api from "../api/api-forData-second";
import moment from 'moment';
// import Options from "../component/area";
const Option = Select.Option;
export default class IndustryDataAdd extends Component {
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
            areaUnitData:[{text:'亩',code:1}],        //  面积单位下拉列表
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
            startTime:"",                               //  数据日期
            dataSources:"",                             //  数据来源
            cropTotalAreaCode: 1,                       //  作物总面积单位code
            cropTotalAreaText: "亩",                      //  作物总面积单位文本
            cropFruitArea: "",                          //作物挂果面积
            cropFruitAreaCode: 1,                      //作物挂果面积单位code
            cropFruitAreaText: "亩",                      //作物挂果面积单位文本
            cropGraftArea: "",                          //作物嫁接面积
            cropGraftAreaCode: 1,                      //作物嫁接面积code
            cropGraftAreaText: "亩",                      //作物嫁接面积文本
            cropTotalArea: "",                          //作物总面积
            developVariety:"",                          //拟发展品种
            growArea: "",                                //海拔1500米以下准备种植品种面积
            growAreaCode: 1,                            //海拔1500米以下准备种植品种面积单位code
            growAreaText: "亩",                         //海拔1500米以下准备种植品种面积单位文本
            landArea: "",                               //海拔1500米以下土地面积
            landAreaCode: 1,                        //海拔1500米以下土地面积单位code
            landAreaText: "亩",                     //海拔1500米以下土地面积单位文本
            ngraftedTreeArea: "",                       //未嫁接大树面积
            ngraftedTreeAreaCode: 1,                //未嫁接大树面积code
            ngraftedTreeAreaText: "亩",                 //未嫁接大树面积文本
            ngraftedYoungtreeArea: "",                  //未嫁接幼树面积
            ngraftedYoungtreeAreaCode: 1,               //未嫁接幼树面积code
            ngraftedYoungtreeAreaText: "亩",                //未嫁接幼树面积文本
            name:""
        }
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
     //  修改时重新渲染数据
     initInput=(id)=>{
        // console.log(id)

        api.industryDataSearch.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    name:v.name,
                    cropTotalAreaCode: v.cropTotalAreaCode,                       //  作物总面积单位code
                    cropTotalAreaText: v.cropTotalAreaText,                      //  作物总面积单位文本
                    cropFruitArea: v.cropFruitArea,                          //作物挂果面积
                    cropFruitAreaCode: v.cropFruitAreaCode,                      //作物挂果面积单位code
                    cropFruitAreaText: v.cropFruitAreaText,                      //作物挂果面积单位文本
                    cropGraftArea: v.cropGraftArea,                          //作物嫁接面积
                    cropGraftAreaCode: v.cropGraftAreaCode,                      //作物嫁接面积code
                    cropGraftAreaText: v.cropGraftAreaText,                      //作物嫁接面积文本
                    cropTotalArea: v.cropTotalArea,                          //作物总面积
                    developVariety:v.developVariety,                          //拟发展品种
                    growArea: v.growArea,                                //海拔1500米以下准备种植品种面积
                    growAreaCode: v.growAreaCode,                            //海拔1500米以下准备种植品种面积单位code
                    growAreaText: v.growAreaText,                         //海拔1500米以下准备种植品种面积单位文本
                    landArea: v.landArea,                               //海拔1500米以下土地面积
                    landAreaCode: v.landAreaCode,                        //海拔1500米以下土地面积单位code
                    landAreaText: v.landAreaText,                     //海拔1500米以下土地面积单位文本
                    ngraftedTreeArea: v.ngraftedTreeArea,                       //未嫁接大树面积
                    ngraftedTreeAreaCode: v.ngraftedTreeAreaCode,                //未嫁接大树面积code
                    ngraftedTreeAreaText: v.ngraftedTreeAreaText,                 //未嫁接大树面积文本
                    ngraftedYoungtreeArea: v.ngraftedYoungtreeArea,                  //未嫁接幼树面积
                    ngraftedYoungtreeAreaCode: v.ngraftedYoungtreeAreaCode,               //未嫁接幼树面积code
                    ngraftedYoungtreeAreaText: v.ngraftedYoungtreeAreaText,                //未嫁接幼树面积文本
                    regionId:v.daCommonField.regionId,
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
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
            cropTotalAreaCode,
            cropTotalAreaText,
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
            strainsCode,
            strainsText,
            cropTotalArea,
            developVariety,
            growArea,
            growAreaCode,
            growAreaText,
            landArea,
            landAreaCode,
            landAreaText,
            ngraftedYoungtreeArea,
            ngraftedYoungtreeAreaCode,
            ngraftedYoungtreeAreaText,
            ngraftedTreeArea,
            ngraftedTreeAreaCode,
            ngraftedTreeAreaText,
            cropGraftArea,
            cropGraftAreaCode,
            cropGraftAreaText,
            cropFruitArea,
            cropFruitAreaCode,
            cropFruitAreaText,
            isAdd
        } = this.state
        let params = {
            "cropFruitArea": cropFruitArea,//作物挂果面积
            "cropFruitAreaCode": cropFruitAreaCode,//作物挂果面积单位code
            "cropFruitAreaText": cropFruitAreaText,//作物挂果面积单位文本
            "cropGraftArea": cropGraftArea,//作物嫁接面积
            "cropGraftAreaCode": cropGraftAreaCode,//作物嫁接面积code
            "cropGraftAreaText": cropGraftAreaText,//作物嫁接面积文本
            "cropTotalArea": cropTotalArea,//作物总面积
            "cropTotalAreaCode": cropTotalAreaCode,//作物总面积单位code
            "cropTotalAreaText": cropTotalAreaText,//作物总面积单位文本
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
            "developVariety": developVariety,//拟发展品种
            "growArea": growArea,//海拔1500米以下准备种植品种面积
            "growAreaCode": growAreaCode,//海拔1500米以下准备种植品种面积单位code
            "growAreaText": growAreaText,//海拔1500米以下准备种植品种面积单位文本
            "landArea": landArea,//海拔1500米以下土地面积
            "landAreaCode": landAreaCode,//海拔1500米以下土地面积单位code
            "landAreaText": landAreaText,//海拔1500米以下土地面积单位文本
            "name": name,//数据主体名称
            "ngraftedTreeArea": ngraftedTreeArea,//未嫁接大树面积
            "ngraftedTreeAreaCode": ngraftedTreeAreaCode,//未嫁接大树面积code
            "ngraftedTreeAreaText": ngraftedTreeAreaText,//未嫁接大树面积文本
            "ngraftedYoungtreeArea": ngraftedYoungtreeArea,//未嫁接幼树面积
            "ngraftedYoungtreeAreaCode": ngraftedYoungtreeAreaCode,//未嫁接幼树面积code
            "ngraftedYoungtreeAreaText": ngraftedYoungtreeAreaText,//未嫁接幼树面积文本
            "strainsCode": strainsCode,//作物品种code
            "strainsText": strainsText//作物品种文本
          }
          if( Number(isAdd) === 1){
            //  新增
            api.industryDataAdd.send(params).then(res=>{
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
            api.industryDataFix.send(params).then(res=>{
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
        //  面积单位下拉方法
        this.initAreaUnit()
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
    
    //  面积单位下拉方法
    initAreaUnit(){
        api.areaUnit.send().then(res=>{
            console.log(res)
            let areaUnitNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    areaUnitNew[i] = {
                        text:v.text,
                        code:v.code
                    }
                })
                this.setState({
                    areaUnitData:areaUnitNew
                },()=>{
                    // console.log(this.state.areaUnitData)
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
            cropTypeCode,
            strainsCode,
            cropTotalAreaCode,
            timeDimensionData,
            areaUnitData,
            originChannelData,
            regionLevelData,
            cropBreedData,
            cropStrainsData,
            startTime,
            dataSources,
            name,
            cropTotalArea,
            developVariety,
            growArea,
            growAreaCode,
            landArea,
            landAreaCode,
            ngraftedYoungtreeArea,
            ngraftedYoungtreeAreaCode,
            ngraftedTreeArea,
            ngraftedTreeAreaCode,
            cropGraftArea,
            cropGraftAreaCode,
            cropFruitArea,
            cropFruitAreaCode,
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
                        <label htmlFor="">作物总面积：</label>
                        <Input onChange={this.inputHandle} data-type="cropTotalArea" value={cropTotalArea}  style={{ width: '30%' }}/>
                        <Select value={cropTotalAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                areaUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'cropTotalAreaCode'} typename={'cropTotalAreaText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="" style={{ fontSize: '12px' }}>作物挂果面积：</label>
                        <Input onChange={this.inputHandle} data-type="cropFruitArea" value={cropFruitArea}  style={{ width: '30%' }}/>
                        <Select value={cropFruitAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                areaUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'cropFruitAreaCode'} typename={'cropFruitAreaText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="" style={{ fontSize: '12px' }}>作物嫁接面积：</label>
                        <Input onChange={this.inputHandle} data-type="cropGraftArea" value={cropGraftArea}  style={{ width: '30%' }}/>
                        <Select value={cropGraftAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                areaUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'cropGraftAreaCode'} typename={'cropGraftAreaText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li className="doubleLine">
                        <label htmlFor="">未来嫁接大树面积：</label>
                        <Input onChange={this.inputHandle} data-type="ngraftedTreeArea" value={ngraftedTreeArea}  style={{ width: '30%' }}/>
                        <Select value={ngraftedTreeAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                areaUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'ngraftedTreeAreaCode'} typename={'ngraftedTreeAreaText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li className="doubleLine">
                        <label htmlFor="">未来嫁接幼苗面积：</label>
                        <Input onChange={this.inputHandle} data-type="ngraftedYoungtreeArea" value={ngraftedYoungtreeArea}  style={{ width: '30%' }}/>
                        <Select value={ngraftedYoungtreeAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                areaUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'ngraftedYoungtreeAreaCode'} typename={'ngraftedYoungtreeAreaText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li className="doubleLine">
                        <label htmlFor="">海拔1500米以下土地面积：</label>
                        <Input onChange={this.inputHandle} data-type="landArea" value={landArea}  style={{ width: '30%' }}/>
                        <Select value={landAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                areaUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'landAreaCode'} typename={'landAreaText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li className="doubleLine">
                        <label htmlFor="">海拔1500米以下准备种植品种面积：</label>
                        <Input onChange={this.inputHandle} data-type="growArea" value={growArea}  style={{ width: '30%' }}/>
                        <Select value={growAreaCode} style={{ marginLeft: '2%', width: '33%' }} onChange={this.handleSelect}>
                            {
                                areaUnitData.map((v,i)=>{
                                    return(
                                        <Option codenum={'growAreaCode'} typename={'growAreaText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">拟发展品种：</label>
                        <Input  onChange={this.inputHandle} data-type="developVariety" style={{ width: '65%' }} value={developVariety} />
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