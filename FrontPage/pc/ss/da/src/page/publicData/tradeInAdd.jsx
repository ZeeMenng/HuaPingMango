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
            isAdd:props.match.params.id,                //  地址栏传参判断是新增：1 || 修改
            remark: '',
            exportVolume: '',
            exportAmount: '',
            exportPrice: '',
            dataTimeTypeText: '',
            importCountryCode: 0,                     //  进口国家code
            importCountryText: "",                //  进口国家名称文本
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
    initInput=(id)=>{
        api.TradeInGet.send({id:id}).then(res=>{
            console.log(res.data)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    importCountryText:v.importCountryText,
                    importCountryCode:v.importCountryCode,
                    importPrice:v.importPrice,
                    importVolume:v.importVolume,
                    importAmount:v.importAmount,
                    remark: v.daCommonField.remark,
                    auditStatusText: v.daCommonField.remark,
                    auditerSuggestion: v.daCommonField.remark,
                    auditStatusCode: v.daCommonField.remark,
                    startTime: v.daCommonField.startTime
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
    // 国家下拉
    initareaUnit() {
        api.getCountry.send().then(res=>{
            console.log(res)
            let areaUnitArr = []
            if(res.isSuccess){
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
            importCountryCode,
            remark,
            importCountryText,
            importVolume,
            importAmount,
            importPrice,
            startTime
        } = this.state
        let params = {
            "cropTypeCode":"1",
			"cropTypeText":"芒果",
			"strainsCode":"0",
			"strainsText":"全部",
			"importCountryCode":importCountryCode,//进口国家编码
			"importCountryText":importCountryText,//进口国家名称
			"importVolume":importVolume,//进口金额
			"importVolumeUnitCode":"5",//进口金额类型编码
			"importVolumeUnitText":"万美元",//进口金额类型名称
			"importAmount":importAmount,//进口量
			"importAmountUnitCode":"2",//进口量类型编码
			"importAmountUnitText":"吨",//进口量类型名称
			"importPrice":importPrice,//进口价格
			"importPriceUnitCode":"4",//进口价格类型编码
			"importPriceUnitText":"美元/公斤",//进口价格类型名称
			"daCommonField":{
				"dataTimeTypeCode" : "1",
				"dataTimeTypeText" : "年",
				"areaLatitudeTypeCode" : "1",
				"areaLatitudeTypeText" : "国家",
				"regionId" : "031000000",
				"regionName" : "中国",
				"sourceChannelTypeCode" : "7",
				"sourceChannelTypeText" : "企业web填报",
				"auditStatusCode" : "0",
				"auditStatusText" : "待审核",
				"startTime" : startTime + '-01-01 00:00:00',//年度
				"endTime" : '',//年度
				"remark" : remark//备注
			}

        }
        if( Number(isAdd) === 1){
            //  新增
            api.TradeInAdd.send(params).then(res=>{
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
            api.TradeInFix.send(params).then(res=>{
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
        }
        //  初始化时间维度
        this.initareaUnit()
        this.initYear()
    }
    render() {
        const {
            importCountryCode,
            remark,
            importCountryText,
            importVolume,
            importAmount,
            importPrice,
            startTime,
            exportCountryData,
            yearData
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
                        <Select defaultValue={exportCountryData[0].code} value={importCountryCode} style={{ width: '65%' }} onChange={this.handleSelect}>
                        {
                            exportCountryData.map((v,i)=>{
                                return(
                                    <Option codenum={'importCountryCode'} typename={'importCountryText'} key={i} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li className="position">
                        <label htmlFor="">出口金额：</label>
                        <Input onChange={this.inputHandle} data-type="importVolume" style={{ width: '65%' }} value={importVolume} defaultValue={importVolume} />
                        <i>万美元</i>
                    </li>
                    <li className="position">
                        <label htmlFor="">出口量：</label>
                        <Input onChange={this.inputHandle} data-type="importAmount" style={{ width: '65%' }} value={importAmount} defaultValue={importAmount} />
                        <i>万吨</i>
                    </li>
                    <li className="position">
                        <label htmlFor="">出口价格：</label>
                        <Input onChange={this.inputHandle} data-type="importPrice" style={{ width: '65%' }} value={importPrice} defaultValue={importPrice} />
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