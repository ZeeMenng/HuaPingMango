import React,{ Component } from "react";
// import { DataNum, DataType,allData,dayData,sourceData,areaData} from './homeData';
import '../../static/scss/home.scss';
import * as api from '../api/api-home'
import EchartsLine from './home-echarts/echartsLine';
import HomeBar from './home-echarts/homeBar';
import RankBar from './home-echarts/rankBar';
import AreaLine from './home-echarts/areaLine';
export default class Home extends Component{
    constructor(props){
        super(props)
        this.state = {
            numList: [
                { name: '直报数据总量', num: '0', unit: '万条' },
                // { name: '数据指标总量', num: '0', unit: '个' },
                // { name: '指标数据', num: '0', unit: '万条' },
                // { name: '图片', num: '0', unit: '万张' },
                // { name: '文档', num: '0', unit: '万个' },
                // { name: '视频', num: '0', unit: '万个' },
                { name: '舆情关键词', num: '0', unit: '个' },
                { name: '舆情主题', num: '0', unit: '个' },
                { name: '舆情数据', num: '0', unit: '万条' },
                // { name: '数据资源', num: '0', unit: '个' },
            ],
            dataType: [],
            allData:{
                xAxisData:[],
                seriesData:[],
            },
            dayData:{
                xAxisData: [],
                seriesData:[]
            },
            sourceData:{
                yAxisData:[],
                seriesData:[],
                bgData:[]
            },
            areaData:{
                xAxisData:[],
                seriesData:[],
            },
        }
    }
    //  总栏数
    numListFn=()=>{
        let params={
            jsonData:{}
        }
        api.numList.send(params).then(res=>{
            console.log(res)
            if(res.isSuccess){
                this.setState({
                    numList:[
                        { name: '直报数据总量', num: res.data[0].DirectTotal || 0, unit: res.data[0].DirectTotal?'万条':'条' },
                        { name: '舆情关键词', num: res.data[0].KeywordNum || 0, unit: '个' },
                        { name: '舆情主题', num: res.data[0].ThemeNum || 0, unit: '个' },
                        { name: '舆情数据', num: res.data[0].SentimentTotal || 0, unit: res.data[0].SentimentTotal?'万条':'条' },
                    ]
                })
            }
        })
    }
    //  数据总量走势
    dataTotal=()=>{
        let params={
            jsonData:{}
        }
        api.allData.send(params).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                this.setState({
                    allData:{
                        xAxisData:res.data.times,
                        seriesData:res.data.data
                    }
                })
            }
        })
    }
    //  数据来源渠道排名
    sourceRank=()=>{
        let params={
            jsonData:{}
        }
        api.sourceData.send(params).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                let total = res.data.data.reduce((total,value)=> total+=value);
                let seriesData = [],bgData = [];
                res.data.data.forEach((v,i)=>{
                    seriesData[i] = Math.floor(v/total*100)
                    bgData[i] = 100
                })
                this.setState({
                    sourceData:{
                        yAxisData:res.data.times,
                        seriesData:seriesData,
                        bgData:bgData
                    }
                },()=>{
                    // console.log(this.state.sourceData)
                })
            }
        })
    }
    //  日采集数据走势
    dayDataFn=()=>{
        let params={
            jsonData:{}
        }
        api.dayData.send(params).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                this.setState({
                    dayData:{
                        xAxisData:res.data.times,
                        seriesData:res.data.data
                    }
                },()=>{
                    // console.log(this.state.dayData)
                })
            }
        })
    }
    //  数据主题数据统计
    areaDataFn=()=>{
        let params={
            jsonData:{}
        }
        api.areaData.send(params).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                this.setState({
                    areaData:{
                        xAxisData:res.data.times,
                        seriesData:res.data.data
                    }
                },()=>{
                    // console.log(this.state.areaData)
                })
            }
        })
    }
    //  待审核数据
    dataTypeFn=()=>{
        let params={
            jsonData:{}
        }
        api.dataType.send(params).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                this.setState({
                    dataType:res.data
                },()=>{
                    // console.log(this.state.dataType)
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
        //  总栏数
        this.numListFn()
        //  数据总量走势
        this.dataTotal()
        //  数据来源渠道排名
        this.sourceRank()
        //  日采集数据走势
        this.dayDataFn()
        //  数据主题数据统计
        this.areaDataFn()
        //  待审核数据
        this.dataTypeFn()
    }
    render(){
        let {numList,dataType} = this.state;
        return(
            <div>
               	<div className="mainBox clearfix home">
                   <div>
                    <div className="top">
                        <ul className='top-wrap'>
                        {
                           numList.map((v,i)=>{
                               if (i % 5 === 4) {
                                    return (
                                        <li className='noBorder' key={i}>
                                            <span className='list-item'>{v.name}<i className='show-num'>{v.num}</i>{v.unit}</span>
                                        </li>
                                    )
                                } else {
                                    return (
                                        <li key={i}>
                                            <span className='list-item'>{v.name}<i className='show-num'>{v.num}</i>{v.unit}</span>
                                        </li>
                                    )
                                }
                            })
                        }
                        </ul>
                    </div>
                	<div className="bottom">
                        <div className="main-left">
                            <div className="echartsWarp">
                                <div className="title">数据总量走势</div>
                                <EchartsLine data={this.state.allData}/>
                            </div>
                            <div className="echartsWarp">
                                <div className="title">日采集数据走势</div>
                                <HomeBar data={this.state.dayData}/>
                            </div>
                            <div className="echartsWarp">
                                <div className="title">数据来源渠道</div>
                                <RankBar data={this.state.sourceData}/>
                            </div>
                            <div className="echartsWarp">
                                <div className="title">数据主题数据统计</div>
                                    <AreaLine  data={this.state.areaData}/>
                            </div>

                        </div>
                        <div className="main-right">
                            <table className="typeData">
                                <thead className="tHead">
                                    <tr>
                                        <th>数据类型</th>
                                        <th>待审核数据(条)</th>
                                    </tr>
                                </thead>
                                <tbody className="tBody">
                                {
                                    dataType.map((v,i)=>{
                                        return(
                                           <tr key={i}>
                                                <td>{v.text}</td>
                                                <td>{v.num}</td>
                                            </tr>
                                        )
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </div>
               	</div>
            </div>
        )
    }
}