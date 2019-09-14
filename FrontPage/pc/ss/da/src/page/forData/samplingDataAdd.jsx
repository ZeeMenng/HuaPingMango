import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
import * as api from "../api/api-forData-second";
import moment from "moment";
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, Cascader, DatePicker, Input, Button, message } from 'antd';
const Option = Select.Option;
export default class SamplingDataAdd extends Component {
    constructor(props) {
        super(props)
        this.state = {
            areaData:[],                                //  地理区域数据
            isAdd: props.match.params.id,
            timeDimensionData: [{ text: '全部', code: 0 }],   //  时间维度下拉列表
            originChannelData: [{ text: '全部', code: 0 }],   //  来源渠道下拉列表
            regionLevelData: [{ text: '全部', code: 0 }],     //  区域维度下拉列表
            cropBreedData: [{ text: '全部', code: 0 }],        //  作物种类下拉列表
            cropStrainsData: [{ text: '全部', code: 0 }],      //  作物品种下拉列表
            dataTimeTypeCode: 0,                         //  时间维度默认code
            dataTimeTypeText: "全部",                    //  时间维度名称
            sourceChannelTypeCode: 0,                    //  来源渠道默认code
            sourceChannelTypeText: "全部",               //  来源渠道默认名称
            areaLatitudeTypeCode: 0,                    //  区域维度id
            areaLatitudeTypeText: "全部",               //  区域维度名称
            cropTypeCode: 0,                            //  作物种类code
            cropTypeText: "全部",                       //  作物种类文本
            strainsCode: 0,                             //  作物品种code
            strainsText: "全部",                        //  作物品种文本
            startTime: "",                              //数据日期
            checkProject: "",                           //检测项目
            sampleDate: "",                             //采样时间
            sampleNo: "",                               //样品编号
            sampleName: "",                             //样品名称
            samplePersonnel: "",                        //采样人员
            samplePlace: "",                            //采样地点
            remark: "",                                 //备注
            name: "",                                   //数据主体名称
            montionData: [{ text: '全部', code: 0 }],
            checkProjectCode: 0
        }
    }
    //  修改时重新渲染数据
    initInput = (id) => {
        // console.log(id)

        api.samplingDataSearch.send({ id: id }).then(res => {
            console.log(res.data)
            if (res.isSuccess) {
                let v = res.data;
                this.setState({
                    name: v.name,//数据主体名称
                    checkProject: v.checkProject,//检测项目
                    commonFieldId: v.commonFieldId,
                    cropTypeCode: v.cropTypeCode,//样品种类code
                    cropTypeText: v.cropTypeText,//样品种类文本
                    areaLatitudeTypeCode: v.daCommonField.areaLatitudeTypeCode,//区域维度id
                    areaLatitudeTypeText: v.daCommonField.areaLatitudeTypeText,//区域维度名称
                    startTime: v.daCommonField.startTime,//数据日期
                    dataSources: v.daCommonField.dataSources,//数据来源
                    dataTimeTypeCode: v.daCommonField.dataTimeTypeCode,//时间维度id
                    dataTimeTypeText: v.daCommonField.dataTimeTypeText,//时间维度名称
                    regionId: v.daCommonField.regionId,//地理区域id
                    regionName: v.daCommonField.regionName,//地理区域名称
                    sourceChannelTypeCode: v.daCommonField.sourceChannelTypeCode,//来源渠道id
                    remark: v.daCommonField.remark,//备注
                    sourceChannelTypeText: v.daCommonField.sourceChannelTypeText,//来源渠道名称
                    sampleDate: v.sampleDate,//采样时间
                    sampleNo: v.sampleNo,//样品编号
                    sampleName: v.sampleName,//样品名称
                    samplePersonnel: v.samplePersonnel,//采样人员
                    samplePlace: v.samplePlace,//采样地点
                    strainsCode: v.strainsCode,//样品品种code
                    strainsText: v.strainsText,//样品品种文本
                    checkProject: v.checkProjectCode,
                    checkProjectCode: v.checkProjectCode
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
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
    onChange = (date, dateString) => {
        console.log(date, dateString);
    }
    goBackPage = () => {
        this.props.history.go(-1)
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
        const { isAdd, checkProject, commonFieldId, cropTypeCode, cropTypeText, areaLatitudeTypeCode, areaLatitudeTypeText,
            startTime, dataSources, dataTimeTypeCode, dataTimeTypeText, regionId, regionName, sourceChannelTypeCode,
            remark, sourceChannelTypeText, name, sampleDate, sampleNo, sampleName, samplePersonnel, samplePlace, strainsCode, strainsText,checkProjectCode
        } = this.state
        let params = {
            "checkProject": checkProject,//检测项目
            "commonFieldId": commonFieldId,
            "cropTypeCode": cropTypeCode,//样品种类code
            "cropTypeText": cropTypeText,//样品种类文本
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
                "remark": remark,//备注
                "sourceChannelTypeText": sourceChannelTypeText//来源渠道名称
            },
            "name": name,//数据主体名称
            "sampleDate": sampleDate,//采样时间
            "sampleNo": sampleNo,//样品编号
            "sampleName": sampleName,//样品名称
            "samplePersonnel": samplePersonnel,//采样人员
            "samplePlace": samplePlace,//采样地点
            "strainsCode": strainsCode,//样品品种code
            "strainsText": strainsText,//样品品种文本
            "checkProjectCode": checkProjectCode
        }
        if (Number(isAdd) === 1) {
            //  新增
            api.samplingDataAdd.send(params).then(res => {
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
            api.samplingDataFix.send(params).then(res => {
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
    //  采样日期
    sampleSelect = (date, dateString) => {
        // console.log(date, dateString);
        this.setState({
            sampleDate: dateString + ' 00:00:00'
        })
    }
    montionSel() {
        api.montionGet.send().then(res => {
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
                    montionData: timeDimensionNew
                }, () => {
                    // console.log(this.state.timeDimensionData)
                })
            }
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
        this.montionSel()
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
        api.cropStrains.send().then(res => {
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
            cropBreedData, 
            cropTypeCode, 
            cropStrainsData, 
            strainsCode,
            sampleName, 
            sampleNo, 
            samplePlace, 
            sampleDate, 
            checkProject, 
            samplePersonnel, 
            remark,
            regionName,
            areaData,
            checkProjectCode,
            montionData
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
                            options={areaData} 
                            onChange={this.areaFn} 
                            />
                    </li>
                    <li>
                        <label htmlFor="">样品种类：</label>
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
                        <label htmlFor="">样品品种：</label>
                        <Select defaultValue={cropStrainsData[0].code} value={strainsCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                            {
                                cropStrainsData.map((v, i) => {
                                    return (
                                        <Option codenum={'strainsCode'} typename={'strainsText'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">样品名称：</label>
                        <Input onChange={this.inputHandle} data-type="sampleName" style={{ width: '65%' }} value={sampleName} defaultValue={sampleName} />
                    </li>
                    <li>
                        <label htmlFor="">样品编号：</label>
                        <Input onChange={this.inputHandle} data-type="sampleNo" style={{ width: '65%' }} value={sampleNo} defaultValue={sampleNo} />
                    </li>
                    <li>
                        <label htmlFor="">采样时间：</label>
                        <DatePicker locale={locale} allowClear={false} value={sampleDate ? moment(new Date(sampleDate)) : null} onChange={this.sampleSelect} style={{ width: '65%' }} />
                    </li>
                    <li>
                        <label htmlFor="">采样地点：</label>
                        <Input onChange={this.inputHandle} data-type="samplePlace" style={{ width: '65%' }} value={samplePlace} defaultValue={samplePlace} />
                    </li>
                    <li>
                        <label htmlFor="">检测项目：</label>
                        <Select onChange={this.handleSelect} value={checkProjectCode} defaultValue={montionData[0].code} style={{ width: '65%' }}>
                            {
                                montionData.map((v, i) => {
                                    return (
                                        <Option codenum={'checkProjectCode'} typename={'checkProject'} key={i} value={v.code}>{v.text}</Option>
                                    )
                                })
                            }
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">采样人员：</label>
                        <Input onChange={this.inputHandle} data-type="samplePersonnel" style={{ width: '65%' }} value={samplePersonnel} defaultValue={samplePersonnel} />
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