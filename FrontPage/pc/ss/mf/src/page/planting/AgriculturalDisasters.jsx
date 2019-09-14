import React from 'react';
import rightBorder from './right-border.png';//引入右侧图表的背景图
import factorCharBg from './echarts-bg.png';//引入右侧图表的背景图
//引入下拉框
import Select from '../../component/web-team/select/Select'
//title 组件
import Title from '../../component/visual-team/title/Title';
//引入图表组件
import {  WarningLineData,FactorBarData, AdisasterTableData } from "./plantingData";//引入图表数据
import FactorBar from './component/factorBar'//引入影响因素的图表组件
import WarningLine from './component/warningLine'//引入灾害类型预警图表
/* import { Map, Marker, NavigationControl, InfoWindow, MarkerList} from 'react-bmap'; */
import { Carousel } from 'antd';
//引入接口文件
import * as api from '../api/api-planting-AgriculturalDisasters.js';
import './AgriculturalDisasters.scss';
//引入字体库
require('./component/font/iconfont.js');
require('./component/font/iconfont.css');

/**
 * 种植生产--农业灾害评估
 */
const BMap=window.BMap
class AgriculturalDisasters extends React.Component {
  constructor() {
    super();
      const me = this;
      //this.initMap = this.initMap.bind(this)
      this.state = {
          FactorBarData: {},//引入影响因素的图表数据
          WarningLineData: [],//引入灾害类型预警图表数据
          AdisasterTableData:[],//灾害类型预警信息
          WarningtypeData:[],
          AdisasterNumData:[],
          timeData:[],
          ifShow:'none',
          timeFirst:'',
          typeArrData:[],
          keyArrData:[],
          typeCode:'0'
      }
      me.AdisasterRight= {
          position: 'absolute',
          top: '0',
          right: '40px',
          width: '440px',
          height: '930px',
          background: `url(${rightBorder})`,
          backgroundSize:`100% 100%`
      };
      me.TopmsgClass={
          position: 'absolute',
          top: '160px',
          left: '115px',
          width: '1170px',
          height: '110px',
      }
  }
    _tokens = [];
    _clearTokens(){
        this._tokens.forEach(token => token.cancel());
        this._tokens = [];
    };


    //初始化页面
    _initPage = ()=>{
        //灾害类型预警信息
        this._initAdisasterTable()
        //灾害类型预警走势
        this._initWarningLine()
        //影响因素
        this._initFactorBar()
        //百度地图数据信息
        this._initAdisasterBaiduMap()
    }
    //点击显示解释说明文字
    showExplain = () =>{
        if (this.state.ifShow == 'none') {
            this.setState({
                ifShow: 'block',
            })
        }
        else if (this.state.ifShow == 'block') {
            this.setState({
                ifShow: 'none',
            })
        }
    }
    //时间下拉初始化
      _initSelect = () => {
        let getTimeDataParams = {
          jsonData: {
            "entityRelated": {
              "viewName": "month",
              "hasCurrent": true,
              "pastNum": "5",
              "afterNum": "0",
              "isASC":false
            }
          }
        }
        api.getTimeData.send(getTimeDataParams).then((res) => {
          //console.log(res)
          this.setState({
            timeData: res.data,
            timeFirst: res.data[0]
          },()=>{

            //灾害类型数字统计
            this._initAdisasterNum(this.state.timeFirst)
          })
        })
    }
    //时间下拉点击事件
    _pullDownMestimeFirst = (a) =>{
        this.setState({
            timeFirst:a.name
        },()=>{
            //灾害类型数字统计
            this._initAdisasterNum(this.state.timeFirst)
            this._initPage()
        })
    }
     //灾害类型走势下拉初始化
      _initWarningTyp = () => {
        api.getWarningType.send().then((res) => {
            //console.log(res)
            let typeArr = [],keyArr = [];
            res.data.map((v,i)=>{
              typeArr.push(v.text)
              keyArr.push(v.code)
            })
            this.setState({
              typeArrData: typeArr,
              keyArrData: keyArr
            })
        })
    }
    //预警走势下拉框点击事件
    _pullDownMesType(a){
        //console.log(a)
        this.setState({
            typeCode:a.key
        },()=>{
            this._initPage()
        })
    }

    //灾害类型数字统计
      _initAdisasterNum = (date) => {
        let AdisasterNumParams = {
          jsonData: {
                "entityRelated":{
                    "date":date || ''
                },
                "orderList":[{
                    "columnName":"",
                    "isASC":true
                }],
                "page":{
                    "pageIndex":1,
                    "pageSize":10
                }
            }
        }
        api.getAdisasterNumData.send(AdisasterNumParams).then((res) => {
            if (res.isSuccess){
                    this.setState({
                        AdisasterNumData:res.data
                 },
                 ()=>console.log(this.state.AdisasterNumData)
                 )
              }else{
                    this.setState({
                        AdisasterNumData:[]
                 })
              }
        })
    }
    //百度地图数据信息
      _initAdisasterBaiduMap = () => {
        let AdisasterBaiduMapParams = {
          jsonData: {
              "entityRelated":{},
              "orderList":[
                  {"columnName":"",
                  "isASC":true
                }],
                "page":{
                    "pageIndex":1,
                    "pageSize":10
                }
            }
        }
        api.getAdisasterBaiduMap.send(AdisasterBaiduMapParams).then((res) => {
          let data_info = res.data;
          let ZH = ["冻害","干旱","大风","暴雨"];
          let rain = `${require("./rain.png")}`,
              blowing = `${require("./blowing.png")}`,
              cold = `${require("./cold.png")}`,
              dry = `${require("./dry.png")}`;
                data_info.map((v,i)=>{
                    if(v.typeText == '大风'){
                        v.iconIme = blowing
                    }else if(v.typeText == '冻害'){
                        v.iconIme = cold
                    }else if(v.typeText == '干旱'){
                        v.iconIme = dry
                    }else if(v.typeText == '暴雨'){
                        v.iconIme = rain
                    }
                })
        // 百度地图API功能
        let map = new BMap.Map("BaiduMap");    // 创建Map实例
        map.centerAndZoom(new BMap.Point(101.276683, 26.636164), 11);  // 初始化地图,设置中心点坐标和地图级别
        for(var i=0;i<data_info.length;i++){
          let p = data_info[i].point.split(",");
            var myIcon = new BMap.Icon(
                data_info[i].iconIme,
                new BMap.Size(60, 60),
                {
                    imageSize: new BMap.Size(60, 60),imageOffset: new BMap.Size(0, 0)
                }
            );
            var marker = new BMap.Marker(new BMap.Point(p[0],p[1]),{icon:myIcon});  // 创建标注
            var sContent =
                    "<div className='bMapInfo' style='height:100px;padding-top:10px'><h4 style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>"+data_info[i].time+"</h4>" +
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> "+data_info[i].bascName+"</p>" +
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 生育期："+data_info[i].lifeCycle+"</p>" +
                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'> 灾害类型："+data_info[i].typeText+"</p>" +
                    "</div>";
            map.addOverlay(marker);               // 将标注添加到地图中
            addClickHandler(sContent,marker);
        }

        function addClickHandler(sContent,marker){
            marker.addEventListener("click",function(e){
                openInfo(sContent,e)}
            );
        }
        function openInfo(sContent,e){
            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
            map.openInfoWindow(infoWindow,point); //开启信息窗口
        }

        let styleJson = [{"label":"水域","featureType":"water","elementType":"all","stylers":{"color":"#397ed8"}},{"label":"公路填充","featureType":"highway","elementType":"geometry.fill","stylers":{"color":"#157da4"}},{"label":"公路线条","featureType":"highway","elementType":"geometry.stroke","stylers":{"color":"#254cb4"}},{"label":"主干道填充","featureType":"arterial","elementType":"geometry.fill","stylers":{"color":"#254cb4"}},{"label":"主干道线条","featureType":"arterial","elementType":"geometry.stroke","stylers":{"color":"#157da4"}},{"label":"局部?","featureType":"local","elementType":"geometry","stylers":{"color":"#2672f3"}},{"label":"陆地","featureType":"land","elementType":"all","stylers":{"color":"#2f53bb"}},{"label":"铁路填充","featureType":"railway","elementType":"geometry.fill","stylers":{"color":"#08304b"}},{"label":"铁路线条","featureType":"railway","elementType":"geometry.stroke","stylers":{"color":"#000000"}},{"label":"建筑填充","featureType":"building","elementType":"geometry.fill","stylers":{"color":"#021736"}},{"label":"建筑默认","featureType":"building","elementType":"geometry","stylers":{"color":"#021736"}},{"label":"标签填充","featureType":"all","elementType":"labels.text.fill","stylers":{"color":"#66ccff","font-size":"38px"}},{"label":"标签线条","featureType":"all","elementType":"labels.text.stroke","stylers":{"weight":"normal","color":"#1f41a0"}},{"label":"绿化","featureType":"green","elementType":"geometry","stylers":{"color":"#2b8284"}},{"label":"边界","featureType":"boundary","elementType":"all","stylers":{"color":"#05365a"}},{"label":"人造物","featureType":"manmade","elementType":"all","stylers":{"color":"#05365a"}}]
        map.setMapStyle({ styleJson: styleJson });
        map.setCurrentCity("华坪县");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放


  })

 }
    //灾害类型预警信息
      _initAdisasterTable = () => {
        let AdisasterTableParams = {
              jsonData: {
                "entityRelated":{},
                "orderList":[{
                    "columnName":"",
                    "isASC":true
                }],
                "page":{
                    "pageIndex":1,
                    "pageSize":10
                }
            }
        }
        api.getAdisasterTableData.send(AdisasterTableParams).then((res) => {
          //console.log(res)
          if (res.isSuccess){
                this.setState({
                    AdisasterTableData:res.data
             })
          }else{
                this.setState({
                    AdisasterTableData:[]
             })
          }

        })

    }
    //影响因素
      _initFactorBar = () => {
        let FactorBarParams = {
         jsonData: {"entityRelated":{},"orderList":[{"columnName":"","isASC":true}],"page":{"pageIndex":1,"pageSize":10}}
        }
        api.getFactorBarData.send(FactorBarParams).then((res) => {
          //console.log(res)
            if (res.isSuccess){
                let xAxisData=res.data[0]['disasterText']
                let xAxisArr = [];
                let xD =[];
                xAxisData.forEach((v,i1)=>{
                    xAxisArr[i1]=[]
                    res.data.forEach((k,i2)=>{
                         xAxisArr[i1][i2]=k.disasterCount[i1]
                    })
                })
               res.data.map((item,index)=>{
                    xD[index] = item.cycleText.split('（')[0]+'\n\n（'+item.cycleText.split('（')[1]
                })
                this.setState({
                    FactorBarData: {
                        legendData: xAxisData,
                        xAxisData:xD,
                        seriesData: {
                            dryData:xAxisArr[0],
                            coldData:xAxisArr[1],
                            blowingData:xAxisArr[2],
                            rainData:xAxisArr[3]
                        }
                    }
                })
            }else{
                this.setState({
                    FactorBarData: []
                })
            }
        })
    }
    //灾害类型预警走势
      _initWarningLine = () => {
        let WarningLineParams = {
              jsonData: {
                "entityRelated":{
                    "typeCode":this.state.typeCode
                },
                "orderList":[{
                    "columnName":"",
                    "isASC":true
                }],
                "page":{
                    "pageIndex":1,
                    "pageSize":10
                }
            }
        }
        api.getWarningLineData.send(WarningLineParams).then((res) => {
          //console.log(res)
           if (res.isSuccess){
                this.setState({
                   WarningLineData:{
                        xAxisData: res.data.Xdata,
                        seriesData: res.data.count,
                        warningType: res.data.text
                   }
                })
            }else{
                this.setState({
                    WarningLineData:{
                        xAxisData: [],
                        seriesData: []
                   }
                })
            }
        })
    }

    componentDidMount(){
        //初始化页面
        this._initPage()
        //时间下拉
        this._initSelect()
        //灾害类型走势下拉初始化
        this._initWarningTyp()

    }
  render() {
    const me = this;
        //灾害次数
        const { AdisasterNumData } = this.state;
        console.log(AdisasterNumData)
        //灾害类型预警信息
        let TableData= this.state.AdisasterTableData;

    return (
        <div className='AgriculturalDisastersClass'>
            <Select _pullDownMes={this._pullDownMestimeFirst} nameArr={this.state.timeData} width={110} style={{
                position: 'absolute',
                top: '0',
                left: '80px',
            }} />
            <div className='TopmsgClass'>

           {
                AdisasterNumData.map((item,index)=>(
                           <div key={index} className="msg-item">
                                <p>{item.name}预警次数</p>
                                <div className={`color${index}`}>
                                    <span style={{ fontSize: '30px', marginRight: '15px' }}>{ item.number }</span>
                                    <span>次</span>
                                    <span className={Number(item.flag) === 1 ? 'markUp' : 'markDown'} style={{marginRight:Number(item.number)===0?"10px":"11px"}}></span>
                                </div>
                            </div>
                        )

                 )
            }
                {/*<div className="msg-item">
                    <p>总预警次数</p>
                    <div className="msg-num color2">
                        <span style={{ fontSize: '0.38rem', marginRight: '0.15rem' }}>63</span><span>次</span>
                        <span className='markDown'></span>
                    </div>
                </div>
                <div className="msg-item">
                    <p>冻害预警次数</p>
                    <div className="msg-num color2">
                        <span style={{ fontSize: '0.38rem', marginRight: '0.15rem' }}>15</span><span>次</span>
                        <span className='markDown'></span>
                    </div>
                </div>
                <div className="msg-item">
                    <p>暴雨预警次数</p>
                    <div className="msg-num color3">
                        <span style={{ fontSize: '0.38rem', marginRight: '0.15rem' }}>12</span><span>次</span>
                        <span className='markUp'></span>
                    </div>
                </div>
                <div className="msg-item">
                    <p>干旱预警次数</p>
                    <div className="msg-num color4">
                        <span style={{ fontSize: '0.38rem', marginRight: '0.15rem' }}>18</span><span>次</span>
                        <span className='markUp'></span>
                    </div>
                </div>
                <div className="msg-item">
                    <p>大风预警次数</p>
                    <div className="msg-num color4">
                        <span style={{ fontSize: '0.38rem', marginRight: '0.15rem' }}>10</span><span>次</span>
                        <span className='markUp'></span>
                    </div>
                </div>*/}
            </div>
            <div className="AdisasterBaiduMap" id="BaiduMap"> </div>
            <div style={me.AdisasterRight} className="AdisasterRight">
                <div className="right-item">
                    <Title content={'灾害类型预警信息'}  top={'0.2rem'} left={'0.2rem'}/>
                    <ul className="AdisasterTable" style={me.listStyle} id='scrollList'>
                     <Carousel vertical autoplay dots="false">
                    {
                        TableData?TableData.map((item,index)=>{
                            if(item.typeText =='大风'){
                                return (
                                    <li key={index} className="TableRow">
                                        <span><i className="iconfont icon-dafeng"></i></span>
                                        <span title={item.bascName}>{item.bascName}</span>
                                        <span title={item.deviceName}>{item.deviceName}</span>
                                        <span>发布<i className='disastersType'>{item.typeText}</i>预警</span>
                                        <span>{item.time}</span>
                                    </li>
                                )
                            }
                            if(item.typeText =='暴雨'){
                               return (
                                    <li key={index} className="TableRow">
                                        <span><i className="iconfont icon-baoyu"></i></span>
                                        <span title={item.bascName}>{item.bascName}</span>
                                        <span title={item.deviceName}>{item.deviceName}</span>
                                        <span>发布<i className='disastersType'>{item.typeText}</i>预警</span>
                                        <span>{item.time}</span>
                                    </li>
                                )
                            }
                            if(item.typeText =='冻害'){
                               return (
                                    <li key={index} className="TableRow">
                                        <span><i className="iconfont icon-shuangdongyujing"></i></span>
                                        <span title={item.bascName}>{item.bascName}</span>
                                        <span title={item.deviceName}>{item.deviceName}</span>
                                        <span>发布<i className='disastersType'>{item.typeText}</i>预警</span>
                                        <span>{item.time}</span>
                                    </li>
                                )
                            }
                            if(item.typeText =='干旱'){
                               return (
                                    <li key={index} className="TableRow">
                                        <span><i className="iconfont icon-ganhan"></i></span>
                                        <span title={item.bascName}>{item.bascName}</span>
                                        <span title={item.deviceName}>{item.deviceName}</span>
                                        <span>发布<i className='disastersType'>{item.typeText}</i>预警</span>
                                        <span>{item.time}</span>
                                    </li>
                                )
                            }

                        }): ''
                    }
                    </Carousel>
                    </ul>
                </div>
                <div className="right-item">
                    <Title content={'影响因素'} top={'0.2rem'} left={'0.2rem'}/>
                    {/*<p className="factorExplain" onClick={me.showExplain.bind(this)}><i className="iconfont icon-gantanhao"></i></p>
                    <div className="echartsExplain">芒果不同生育期受气象因素影响程度不同，花期冻害、果实膨 大期干旱、果实成熟期连阴雨对单产影响程度大</div>*/}
                    {/* <div className ='ExplainBox' style={{display: this.state.ifShow}}>
                        图表说明
                        图中每一根柱子代表此种灾害历年来对各地区单产造成的实际影响大小，柱子越高，说明实际影响越大。
                        模型说明
                        以华坪芒果单产为因变量，气象条件（包括气象要素与气象灾害指数）、投入成本等为自变量，对所有变量进行标准化后拟合多元线性回归，各变量的回归系数即为对单产的实际影响程度。这种影响程度的实际含义为：自变量每改变一个标准差，因变量将改变多少个标准差。
                        气象灾害指数计算方法
                        低温指数：该时期日均温最低5天的温度平均值的倒数
                        干旱指数：该时期无降水（降水量小于1mm）天数除以该时期降水量
                        连阴雨指数：该时期连续三天及以上降水天数除以该时期无降
                    </div> */}
                    <div className="echartsWarp bgClass" style={me.FactorBarClass}>
                        <FactorBar data={me.state.FactorBarData}/>
                    </div>
                </div>
                <div className="right-item">
                    <Title content={'灾害类型预警走势'} top={'0.2rem'} left={'0.2rem'}/>
                    <div className="echartsExplain">
                    <span className="factorType">灾害类型：</span>
                        <Select _pullDownMes={this._pullDownMesType.bind(this)} nameArr={this.state.typeArrData} keyArr={this.state.keyArrData} width={110} style={{
                            position: 'absolute',
                            top: '0px',
                            left: '85px',
                        }} />
                    </div>
                    <div className="echartsWarp">
                        <WarningLine data={me.state.WarningLineData} />
                    </div>
                </div>
            </div>
      </div>
    )
  }
  componentWillUnmount() {
    this._clearTokens();
  }
}
export default AgriculturalDisasters;
