import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Table, Button, Icon, Tooltip, Popconfirm, message, Cascader, Upload } from 'antd';
import * as api from "../api/api-thingsInternet";
import AreaLine from './echarts/areaLine';
// import Options from "../component/area";
const Option = Select.Option;
const { RangePicker } = DatePicker;

function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}

export default class ProcessValueData extends Component {
    constructor(props) {
        super(props)
        this.state = {
            loading: false,
            originChannelData:[{text:'全部',code:0,unit:''}],   //  来源渠道下拉列表
            type: 769,
            text: '湿度',
            unit: '%',
            areaData:{
                xAxisData:[],
                seriesData:[],
                title:'',
                yName:''
            },
            isAdd:props.match.params.id,
        }
        // console.log(props)
    }

    handleChange = (value) => {
        console.log(`selected ${value}`);
    }

    //  来源渠道下拉方法
    initOriginChannel(){
        api.jkType.send().then(res=>{
            console.log(res)
            let originChannelNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    originChannelNew[i] = {
                        text:v.displayName,
                        code:v.type,
                        unit:v.unit
                    }
                })
                this.setState({
                    originChannelData:originChannelNew
                },()=>{
                    // console.log(this.state.originChannelData)
                })
            }
        })
    }
    //  来源渠道
    areaHandleChange = (value, option)=> {
        this.setState({
            type:value,
            text:option.props.children,
            unit:option.key
        })
        this.areaDataFn()
    }
    //  图表
    areaDataFn=()=>{
        const {
            type,
            isAdd,
            text,
            unit
        } = this.state
        let params={
            "hid": isAdd,//设备主机编码
            "type": type.toString()//监控类型
        }
        api.zsEchart.send(params).then(res=>{
            let x1 = [];
            let y1 = [];
            let data = res.data;
            console.log(this.state.text)
            if(res.isSuccess){
                for (let i in data) {
                    x1.push(data[i].time.split(' ')[1])
                    y1.push(data[i].val)
                }
                this.setState({
                    areaData:{
                        xAxisData:x1,
                        seriesData:y1,
                        title:this.state.text,
                        yName:this.state.unit,
                    }
                },()=>{
                    console.log(this.state.areaData)
                })
            }
        })
    }
    componentDidMount(){
        //  未登录跳转到登录页
        if(!localStorage.token){
            this.props.history.push("/login")
            return
        }
        //  初始化来源渠道
        this.initOriginChannel()
        //  数据主题数据统计
        this.areaDataFn()
    }
    render() {
        const {
            originChannelData,
            type
        } = this.state;
        return (
            <div className="forData">
                <ul className="form-search clearfix">
                    <li>
                        <label htmlFor="">监控项：</label>
                        <Select defaultValue={originChannelData[0].code} style={{ width: '65%' }} onChange={this.areaHandleChange} value={type}>
                        {
                            originChannelData.map((v,i)=>{
                                return(
                                    <Option key={v.unit} value={v.code}>{v.text}</Option>
                                )
                            })
                        }
                        </Select>
                    </li>
                    <li>
                        <Button /* onClick={this.initAltitudeList.bind(undefined,1)} */  type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">返回</Button>
                    </li>
                </ul>
                <div className="echartsWarp area-line">
                    <AreaLine  data={this.state.areaData}/>
                </div>

            </div>
        )
    }
}