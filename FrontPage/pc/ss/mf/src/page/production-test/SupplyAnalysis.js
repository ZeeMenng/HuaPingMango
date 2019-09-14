import React from 'react';
import * as api from '../api/api-supplyAnalysis';
import { getTimeData} from '../api/api-price-monitor';  //时间下拉框
import SupplyAnalysisClass from './SupplyAnalysis.scss';
//title 组件
import Title from '../../component/web-team/title/Title';
import PredictionLine from "../../component/web-team/SupplyAanlysis/predictionLine";
import MonitorLine from "../../component/web-team/SupplyAanlysis/monitorLine";
import ProcessLine from "../../component/web-team/SupplyAanlysis/processLine";
import Select from '../../component/web-team/select/Select';
import MakeSales from '../../component/web-team/market-price/surveyLink'
import {PredictionData,MakeSalesData,MonitorLineData} from "./productionData";
//产销预测
import Panel from '../../component/visual-team/panel/Panel1';
import CusomForeast from '../../component/visual-team/bar/CusomForeastTwo1';

/**
 * 产销监测--供需分析
 * @author wxy
 */
class SupplyAnalysis extends React.Component {
  constructor() {
    super();
    const me = this;
    this.state = {
      precisionData: {
        xAxisData: [],
        seriesData: [[],[]]
      },
      // freshTop10Data: FreshTop10Data,
      MakeSalesData: MakeSalesData,
      monitorLineData: {
        legendData1: ['','','','','','','','','','','','',''],
        xAxisData1: [],
        seriesData1: [[],[],[],[],[],[],[],[],[],[],[],[],[]]
      },
      processLineData: {
        legendData2: ['','','','','','','','','','','','',''],
        xAxisData2: [],
        seriesData2: [[],[],[],[],[],[],[],[],[],[],[],[],[]]
      },
      timeData: [],
      processNameData: [],
      processCodeData: [],
      monitorNameData: [],
      monitorCodeData: [],
      SaleName:'北京',
      SaleCode:'110000',
      ProdName:'华坪',
      ProdCode:'530723',
      SaleArea:[],
      AreaDataCode:[],
      areaFirst:'110000'
    };

  }
    // 省份下拉
    _initSelectArea = () => {
        let getAreaDataParams = {
            jsonData: {
                "entityRelated":{
                    "category":"1,2,3,4"
                }
            }
        }
        api.getAreaData.send(getAreaDataParams).then((res) => {
            let SaleArea = [] ,areaValue=[];
            res.data.map((item,value)=>{
                let str = item.name;
                (str == '黑龙江省' || str == '内蒙古自治区') ? str=str.slice(0,3) : str=str.slice(0,2)
                SaleArea.push(str),
                    areaValue.push(item.code)
            })
            this.setState({
                SaleArea: SaleArea,
                AreaDataCode: areaValue,
                areaFirst: res.data[0].code
            })
        })
    }
  //时间下拉框初始化
  initTimeSelect = () => {
    let getTimeDataParams = {
      jsonData: {
        "entityRelated" : {
          "viewName" : "year", //视图名，年year，月month，日date，小时hour（默认为年）
          "hasCurrent" : true,
          "pastNum" : "5", //往前推多少
          "afterNum" : "0",//往后推多少
          "isASC" : false
        }
      }
    };
    getTimeData.send(getTimeDataParams).then((res) => {
      // console.log(res)
      this.setState({
        timeData: (res.data),
      });
      this.freshTop(res.data[0]);
      this.Monitor(res.data[0], this.state.strainsCode);
      this.Process(res.data[0], this.state.processStrainsCode);
    })
  };
  //鲜芒果不同品种下拉框
  initMonitorSelect = () => {
    api.selectMonitorData.send().then((res) => {
      let monitorName = [];
      let monitorCode = [];
      // console.log(res)
      res.data.map((item, index) => {
        monitorName[index] = item.text;
        monitorCode[index] = item.code;
      });
      this.setState({
        monitorNameData: monitorName,
        monitorCodeData: monitorCode
      });
      this.Monitor(this.state.year, res.data[0].code);
    })
  };
  //芒果加工品下拉框
  initProcessSelect = () => {
    api.selectProcessData.send().then((res) => {
      let processName = [];
      let processCode = [];
      res.data.map((item, index) => {
        processName[index] = item.text;
        processCode[index] = item.code
      });
      // console.log(processCode)
      this.setState({
        processNameData: processName,
        processCodeData: processCode
      });
      this.Process(this.state.year, res.data[0].code);
    })
  };
  //top10
  _freshTop = (a) => {
    this.freshTop(a.name)
  };
  //鲜芒果
  _monitorYear = (a) => {
    this.Monitor(a.name, this.state.strainsCode);
    this.setState({
      year: a.name
    })
  };
  _monitorVariety = (a) => {
    this.Monitor(this.state.year, a.key);
    this.setState({
      strainsCode: a.key
    })
  };
  //芒果加工产品
  _processYear = (a) => {
    this.Process(a.name,this.state.processStrainsCode);
    this.setState({
      year:a.name
    })
  };
  _processVariety = (a) => {
    // console.log(a.key)
    this.Process(this.state.year,a.key);
    this.setState({
      processStrainsCode:a.key
    })
  };
    //产销价差产地
    _pullDownMesProd = (a) =>{
        this.setState({
            ProdName:a.name,
            ProdCode:a.value
        },()=>{
          this.MakeSales()
        })
    }
    //产销价差销地
    _pullDownMesSale = (a) =>{
        this.setState({
            SaleName:a.name,
            SaleCode:a.value
        },()=>{
          this.MakeSales()
        })
    }
    //产销价差
    MakeSales = ()=>{
        let MakeSalesParams = {
            jsonData: {
                /* "entityRelated": {
                    "prodAreaCode": this.state.ProdCode,
                    "prodAreaName": this.state.ProdName,
                    "saleAreaCode": this.state.SaleCode ? this.state.SaleCode : this.state.areaFirst,
                    "saleAreaName": this.state.SaleName
                },
                "orderList": [{
                    "columnName": "sale_amount",
                    "isASC": true
                }],
                "page": {
                    "pageIndex": 1,
                    "pageSize": 10
                } */
            }
        }
        api.getMakeSalesData.send(MakeSalesParams).then((res) => {
            //console.log(res)
            if (res.isSuccess){
                let xAxisD = res.data.time,
                    legendD = [],
                    seriseData = [];
                   /*  gap = [],
                    prodPrice = [],
                    salePrice = [];
                let saleArea = this.state.SaleName ? this.state.SaleName : '北京',
                    prodArea = this.state.ProdName ? this.state.ProdName : '华坪',
                    legendD = '价差('+ saleArea + '-' + prodArea + ')'; */
                res.data.data.map((item, index) => {
                    //console.log(item)
                    legendD.push(item.RegionName);
                    seriseData.push(item.data);
                    /* gap.push(item.gap);
                    prodPrice.push(item.prod_price);
                    salePrice.push(item.sale_price); */
                })
                this.setState({
                    MakeSalesData: {
                        /* legendData: [legendD, this.state.ProdName, this.state.SaleName],
                        xAxisData: xAxisD,
                        seriesData: [gap, prodPrice, salePrice] */
                        legendData: legendD,
                        xAxisData: xAxisD,
                        seriesData: seriseData
                    }
                })
            }else{
                this.setState({
                    MakeSalesData: []
                })
            }
        })
    }
    //产销预测
    _tokens = [];
    _initLineEcharts(){
        let me = this;
        me._tokens.push(api.PredictionLine.send({
            jsonData: {}
          }).then((res) => {
              //console.log(res)
             let yearData = res.data[0].dateTime;
             let saleData = Array.from(res.data[0].saleY, (x) => (x / 10000000).toFixed(2));
             let yieldData = Array.from(res.data[0].yieldY, (x) => (x / 10000000).toFixed(2));

            let obj = {
              showTooltip: true,
              showTick: true,
              circleArr: ['rgba(0,255,246,1)', 'rgba(255,243,141,1)'],
              showLegend: true,
              itemGap: 25,
              legendName: ['产量', '产量预测','销量','销量预测'],
              xData: yearData,
              yData1: saleData,
              yData2: yieldData,
            };
            me.refs.consumForecast.setData(obj);
          }));
    }






  //鲜芒果不同品种产销率监测
  Monitor = (year, strainsCode) => {
    let MonitorParams = {
      jsonData: {
        "entitySale":{
          //"year": year || new Date().getFullYear(),
          //"strainsCode": "0"
        }
      }
    };
    api.MonitorLine.send(MonitorParams).then((res) => {
      //console.log(res)
      if(res.isSuccess){
        let xAxisD = res.data[0].date,legendD = [],seriseData = [];
              res.data.map((item, index) => {
                legendD[index] = item.name
                seriseData[index] = item.proSaleRateList
              });
              this.setState ({
                monitorLineData:{
                  legendData1: legendD,
                  xAxisData1: xAxisD,
                  seriesData1: seriseData
                }
              })
      }else{
              this.setState({
                monitorLineData:{
                  legendData1: ['','','','','','','','','','','','',''],
                  xAxisData1: [],
                  seriesData1: [[],[],[],[],[],[],[],[],[],[],[],[],[]]
                }
              })
      }

    });
  };

  //芒果加工品产销率监测
  Process = (year, processStrainsCode) => {
    let ProcessParams = {
      jsonData: {
        "entitySale":{
          //"year": year || new Date().getFullYear(),
          //"processStrainsCode": processStrainsCode || "0"
        }
      }
    };
    api.ProcessLine.send(ProcessParams).then((res) => {
      // console.log(res);
     /*  let monthData = [];
      let proSaleRate = [];
      res.data.map((item, index) => {
        monthData.push(item.month);
        proSaleRate.push(item.proSaleRate)
      });
      this.setState ({
        processLineData: {
          xAxisData: monthData,
          seriesData: proSaleRate
        }
      }); */

      if(res.isSuccess){
        let xAxisD = res.data[0].date,legendD = [],seriseData = [];
              res.data.map((item, index) => {
                legendD[index] = item.name
                seriseData[index] = item.proSaleRateList
              });
              this.setState ({
                processLineData:{
                  legendData2: legendD,
                  xAxisData2: xAxisD,
                  seriesData2: seriseData
                }
              },
              // ()=>console.log(this.state.processLineData)
              )
      }else{
              this.setState({
                processLineData:{
                  legendData2: ['','','','','','','','','','','','',''],
                  xAxisData2: [],
                  seriesData2: [[],[],[],[],[],[],[],[],[],[],[],[],[]]
                }
              })
      }
    })
  };

  componentDidMount() {
      //时间下拉
      this.initTimeSelect();
      //省份下拉
      this._initSelectArea()
      //不同品种下拉框
      this.initMonitorSelect();
      //芒果加工品下拉框
      this.initProcessSelect();
      this.Monitor();
      this.MakeSales();
      this.Process();
      //产销预测
    this._initLineEcharts();

      // 供求预测
      let PredictionLineParams = {
          jsonData: {}
      };
      api.PredictionLine.send(PredictionLineParams).then((res) => {
          //console.log(res.data[0])
          let yearData = [];
          let saleData = [];
          let yieldData = [];
          yearData = res.data[0].dateTime;
          saleData = Array.from(res.data[0].saleY, (x) => (x / 10000000).toFixed(2));
          yieldData = Array.from(res.data[0].yieldY, (x) => (x / 10000000).toFixed(2));
          this.setState({
              precisionData: {
                  xAxisData: yearData,
                  seriesData: [saleData, yieldData]
              }
          })
      });
  }
  render() {
    const me = this;
    return (
      <div className="homePageStyle">
        {/*产销现状*/}
        <div className="conLeft">
          <div className="actualityStyle">
            <div className="chart-title-wrap">
              <Title content={'产销差价'}/>
            </div>
            <p className="select-type1">产销差价=销地批发价-产地田头价</p>
            {/* <p className="select-type1">产地：</p>
            <Select _pullDownMes={this._pullDownMesProd} nameArr={['华坪']} valueArr={['530723']} width={110}
                    style={{
                        position: 'absolute',
                        top: '20px',
                        right: '240px',
                        width: '110px',
                        zIndex: '9999999'
                    }}/>
            <p className="select-type2">销地：</p>
            <Select _pullDownMes={this._pullDownMesSale} nameArr={this.state.SaleArea} valueArr={this.state.AreaDataCode} width={110}
                    style={{
                        position: 'absolute',
                        top: '20px',
                        right: '35px',
                        width: '110px',
                        zIndex: '9999999'
                    }}/> */}
            <div className="echarts-wrap">
                <MakeSales data={me.state.MakeSalesData}/>
            </div>
          </div>
          <div className="monitor">
            <div className="chart-title-wrap">
              <Title content={'鲜芒果不同品种产销率监测'}/>
            </div>
             {/* <Select _pullDownMes={this._monitorYear} nameArr={this.state.timeData} style={{
               position: 'absolute',
               top: '20px',
               right: '35px',
               width: '110px',
               zIndex: '9999999'
               }}/> */}
               {/* <Select _pullDownMes={this._monitorVariety} keyArr={this.state.monitorCodeData} nameArr={this.state.monitorNameData} width={110} style={{
               position: 'absolute',
               top: '20px',
               right: '35px',
               width: '110px',
               zIndex: '9999999'
               }}/> */}
               <MonitorLine data={me.state.monitorLineData}/>
          </div>
        </div>
        <div className="conRight">
            {/*产销预测*/}
          <div className="predictionStyle">
              <Title content={'产销预测'}/>
            {/* <PredictionLine data={me.state.precisionData}/> */}
            <Panel height={'90%'} width={'95%'} top={'10px'}>
                <div style={{
                    cursor: 'pointer',
                    position: 'absolute',
                    zIndex: '3',
                    left: '350px',
                    top: '55px',
                    height: '20px',
                    display:'flex',
                    flexDirection:'row',
                }}>
                    <span style={{
                    display: 'block',
                    width: '16px',
                    height: '12px',
                    marginRight:'5px',
                    border: '3px dashed rgba(0,255,246,1)'
                    }}></span>
                    <span style={{
                    fontSize: '12px',
                    lineHeight: '12px',
                    color: '#dbfcff',
                    display: 'block',
                    marginRight:'25px',
                    }}>产量预测值</span>
                    <span style={{
                    display: 'block',
                    width: '16px',
                    height: '12px',
                    marginRight:'5px',
                    border: '3px dashed rgba(250,250,150,1)'
                    }}></span>
                    <span style={{
                    fontSize: '12px',
                    lineHeight: '12px',
                    color: '#dbfcff',
                    display: 'block',
                    marginRight:'20px',
                    }}>销量预测值</span>
                </div>
                <CusomForeast ref={'consumForecast'} style={{width: '100%', height: '100%', position: 'absolute', top: '10%'}}/>
            </Panel>
          </div>

          <div className="process">
            <div className="chart-title-wrap">
                <Title content={'芒果加工品产销率监测'}/>
            </div>
           {/* <Select _pullDownMes={this._processYear} nameArr={this.state.timeData} style={{
                position: 'absolute',
                top: '20px',
                right: '35px',
                width: '110px',
                zIndex: '9999999'
            }}/> */}
            {/* <Select _pullDownMes={this._processVariety} keyArr={this.state.processCodeData} nameArr={this.state.processNameData} width={110} style={{
                position: 'absolute',
                top: '20px',
                right: '35px',
                width: '110px',
                zIndex: '9999999'
            }}/> */}
           <ProcessLine data={me.state.processLineData}/>
          </div>
        </div>
      </div>
    )
  }
}

export default SupplyAnalysis;
