import React from 'react';
// component
import bg from './mango-homepage.png';
import leftImg from './leftImg.png';
import rightImg from './rightImg.png';
import * as api from '../api/api-home-page';
//title 组件
import Title from '../../component/visual-team/title/Title';
//引入图表组件
import PureLine from '../../component/visual-team/line/PureLine';
import PureBar from '../../component/visual-team/bar/PureBar';
import PureLineChange from '../../component/visual-team/line/PureLineChange';
/*芒果产值*/
import PureBarDouble from '../../component/visual-team/bar/PureBarDouble';
/*精深加工-饼图*/
import PercentPie from '../../component/visual-team/pie/percentPie';

/*芒果消费结构*/
import YearPie from '../../component/visual-team/pie/yearPie';
//首页中间靠上文本框
import DataBox from '../../component/visual-team/data-box/DataBox';

import store from '../../store/Store';

/*** 未来全国芒果批发价格预测   */
import LineFuture from '../../component/visual-team/line/LineFuture2';
/** 下拉框组件 */
import Select from '../../component/visual-team/other/Select';
//田头价 零售价
import CircleSmall from '../../component/visual-team/d3/CircleSmall';
import CircleBig from '../../component/visual-team/d3/CircleBig';
//右下角产销预测
//import PredictionLine from "../../component/visual-team/line/predictionLine";
import Panel from '../../component/visual-team/panel/Panel1';
import CusomForeast from '../../component/visual-team/bar/CusomForeastTwo';

//桑吉图
import EChartsSankey from '../../component/visual-team/san-key/EChartsSankey'
/* import { Map, Marker, NavigationControl, InfoWindow, MarkerList} from 'react-bmap'; */
import { Carousel } from 'antd';
import './industry.scss';
/**
 * 首页
 */
class HomePage extends React.Component {
  constructor() {
    super();
    const me = this;
    this.state = {
      t: '种植面积',
      fresh: '北京',
      process: '上海',
      NowYear:'',
      precisionData: {
        xAxisData: [],
        seriesData: [[],[]]
      },
    };
    this.wholesaleStyle = {
        width: '420px',
        height: '213px',
        position: 'absolute',
        left: 0,
        top: '28px',
        right:'5px'
      };
    me.homePageStyle = {
      width: '1920px',
      height: '1080px',
      background: `url(${bg}) no-repeat center center`,
      backgroundSize: 'contain'
    };
    me.pieStyle = {
      width: '410px',
      height: '205px',
      position: 'absolute',
      top: '578px',
      left: '170px'
    };
    me.saleMangoStyle = {
      width: '400px',
      height: '180px',
      position: 'absolute',
      //top: '135px',
      top: '368px',
      left: '1470px'
    }
  }

  _tokens = [];

  _clearTokens() {
    this._tokens.forEach(token => token.cancel());
    this._tokens = [];
  }

  componentWillMount() {
    let me = this;
    // 经深加工
    me._tokens.push(api.dataBox.send({//全国数据
        jsonData: JSON.stringify({
          "entityRelated": {"year": "2018", "regionId": "530723"},
          "orderList": [{"columnName": "", "isASC": true}],
          "page": {"pageIndex": 1, "pageSize": 10}
        })
      }
    )/* .then((res1) => {
      //console.log(res1)
      me._tokens.push(api.dataBox.send({//华坪数据
        jsonData: JSON.stringify({
          "entityRelated": {"year": "2018", "regionId": "530723"},
          "orderList": [{"columnName": "", "isASC": true}],
          "page": {"pageIndex": 1, "pageSize": 10}
        })
      }) */.then((res) => {
        let obj = {
          areaSum: res.data.areaSum*0.0015/10000,//华坪面积
          productSum: res.data.productSum/10000000,//华坪产量
          outputSum: res.data.outputSum/100000000,//华坪产值
        };
        store.dispatch({type: 'box', data: obj})
        this.setState({
          areaSumDate:`${res.data.year}年华坪芒果面积`,//华坪时间
          productSumDate:`${res.data.year}年华坪芒果产量`,//华坪时间
          outputSumDate: `${res.data.year}年华坪芒果产值`,//全国时间
        })
      }));

    // 批发价field  电商价retail
    me._tokens.push(api.priceIndex
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {
            "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
          },
          "orderList": [{
            "columnName": "",
            "isASC": true
          }],
          "page": {
            "pageIndex": 1,
            "pageSize": 10
          }
        })
      }).then((res) => {
        //console.log(res)
        let data = res.data;
        let fieldPrice = {};
        let field = [];
        let retailPrice = {};
        let retail = [];
        data.forEach((item, i) => {
          for (let n in item) {
            if (n.indexOf('field') > -1) {
              field = item[n].map((it, index) => {
                for (let m in it) {
                  if (it[m].toString().indexOf('.') > -1) {
                    it[m] = it[m].toFixed(2);
                    return it;
                  } else {
                    return it;
                  }
                }
              });
            } else if (n.indexOf('retail') > -1) {
              retail = item[n].map((it, index) => {
                for (let m in it) {
                  if (it[m].toString().indexOf('.') > -1) {
                    it[m] = it[m].toFixed(2);
                    return it;
                  } else {
                    return it;
                  }
                }
              });
            }
          }
        });
        field.forEach((item, i) => {
          //console.log(item)
          for (let n in item) {
            if (n.toLowerCase().indexOf('day') > -1) {
              fieldPrice.data = item[n];
            } else if (n.toLowerCase().indexOf('month') > -1) {
              fieldPrice.month = item[n];
            } else if (n.toLowerCase().indexOf('year') > -1) {
              fieldPrice.year = item[n];
            }
          }
        });
        retail.forEach((item, i) => {
          //console.log(item)
          for (let n in item) {
            if (n.toLowerCase().indexOf('day') > -1) {
              retailPrice.data = item[n];
            } else if (n.toLowerCase().indexOf('month') > -1) {
              retailPrice.month = item[n];
            } else if (n.toLowerCase().indexOf('year') > -1) {
              retailPrice.year = item[n];
            }
          }
        });
        let lastData = {
          "wholeSale": fieldPrice,
          "retail": retailPrice
        };
        store.dispatch({type: 'price', data: lastData});
      }));

  }
    //灾害类型预警信息
  //   _initAdisasterTable = () => {
  //     let AdisasterTableParams = {
  //           jsonData: {
  //             "entityRelated":{},
  //             "orderList":[{
  //                 "columnName":"",
  //                 "isASC":true
  //             }],
  //             "page":{
  //                 "pageIndex":1,
  //                 "pageSize":10
  //             }
  //         }
  //     }
  //     api.getAdisasterTableData.send(AdisasterTableParams).then((res) => {
  //       //console.log(res)
  //       if (res.isSuccess){
  //             this.setState({
  //                 AdisasterTableData:res.data
  //          })
  //       }else{
  //             this.setState({
  //                 AdisasterTableData:[]
  //          })
  //       }

  //     })

  // }
  _pullDownMes(a) {
    let me = this;
    me._tokens.push(api.plantSpace.send({
      jsonData: JSON.stringify({
        "entityRelated": {
          "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
        },
        "orderList": [{
          "columnName": "",
          "isASC": true
        }],
        "page": {
          "pageIndex": 1,
          "pageSize": 10
        }
      })
    }).then((res) => {
      let data = res.data;
      let areas = data.areas; //种植面积
      let products = data.products; // 产量
      let outputs = data.outputs; //产值
      let xAxisData = data.xData;
      /*公公样式*/
      let title = '';
      let unitArr = [];
      let legendName = [];
      let yData1 = [];
      let yData2 = [];
      let yData3 = [];

      switch (a.name) {
        case '种植面积':
          /*种植面积-亩*/
          let greenArea = []; //绿色认证面积
          let organicArea = [];//有机认证面积
          let totalArea = [];//总面积
          for (let i in areas) {
            if (i.indexOf('green') > -1) {
              greenArea = areas[i];
            } else if (i.indexOf('organic') > -1) {
              organicArea = areas[i];
            } else if (i.indexOf('total') > -1) {
              totalArea = areas[i];
            }
          }
          title = '万亩';
          unitArr = ['万亩', '万亩', '万亩'];
          legendName = ['总种植面积', '有机产品认证面积', '绿色认证面积'];
          yData1 = totalArea.map((item, i) => {
            let d = item * 0.0015;
            return (d / 10000).toFixed(2);
          });
          yData2 = organicArea.map((item, i) => {
            let d = item * 0.0015;
            return (d / 10000).toFixed(2);
          });
          yData3 = greenArea.map((item, i) => {
            let d = item * 0.0015;
            return (d / 10000).toFixed(2);
          });
          break;
        case '产量':
          /*总产量-吨*/
          let greenProduct = []; //绿色认证产量
          let organicProduct = [];//有机认证产量
          let totalProduct = [];//总产量
          for (let i in products) {
            if (i.indexOf('green') > -1) {
              greenProduct = products[i];
            } else if (i.indexOf('organic') > -1) {
              organicProduct = products[i];
            } else if (i.indexOf('total') > -1) {
              totalProduct = products[i];
            }
          }
          title = '万吨';
          unitArr = ['万吨', '万吨', '万吨'];
          legendName = ['总产量', '有机认证产量', '绿色认证产量'];
          yData1 = totalProduct.map((item, i) => {
            return (item / 10000000).toFixed(2);
          });
          yData2 = organicProduct.map((item, i) => {
            return (item / 10000000).toFixed(2);
          });
          yData3 = greenProduct.map((item, i) => {
            return (item / 10000000).toFixed(2);
          });
          break;
        case '产值':
          let greenOutput = []; //绿色认证产量
          let organicOutput = [];//有机认证产量
          let totalOutput = [];//总产量
          for (let i in outputs) {
            if (i.indexOf('green') > -1) {
              greenOutput = outputs[i];
            } else if (i.indexOf('organic') > -1) {
              organicOutput = outputs[i];
            } else if (i.indexOf('total') > -1) {
              totalOutput = outputs[i];
            }
          }
          title = '亿元';
          unitArr = ['亿元', '亿元', '亿元'];
          legendName = ['总产值', '有机认证产值', '绿色认证产值'];
          yData1 = totalOutput.map((item, i) => {
            return (item / 100000000).toFixed(2);
          });
          yData2 = organicOutput.map((item, i) => {

            return (item / 100000000).toFixed(2);
          });
          yData3 = greenOutput.map((item, i) => {
            return (item / 100000000).toFixed(2);
          });
          break;
        default:
          break;
      }

      /*总产值-亿元*/

      let obj = {
        showTitle: true,
        title: title,
        titleTop: 6,
        showTooltip: true,
        showTick: true,
        unitArr: unitArr,
        circleArr: ['#00ffff', '#fde634', '#2af594'],
        lineColor: ['#00ffff', '#fde634', '#2af594'],
        showLegend: true,
        itemGap: 10,
        legendLeft: 55,
        legendTop: 10,
        legendName: legendName,
        gridLeft: '8%',
        gridRight: '4%',
        gridTop: '20%',
        gridBottom: '15%',
        smooth: true,
        xData: xAxisData.slice(),
        yData1: yData1,
        yData2: yData2,
        yData3: yData3
      };
      me.refs.greenPlant.setData(obj);
    }));
  }

  _changePieHandle(a) {
    let me = this;
    let year = '';
    a.forEach((item, i) => {
      year = item.year;
    });
    let obj = {
      titleShow: false,
      legendShow: false,
      seriesRadius: ['40%', '70%'],
      seriesCenter: ['50%', '57%'],
      seriesData: a,
      year: year,
      colorTop: ['#11e0ff', '#2af594', '#fed645'],
      colorBottom: ['#00b4ff', '#12d578', '#eea21f']
    };
    me.refs.saleMangoPie.setData(obj);
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
//右下角供求预测折线图
_initLineEcharts(){
    // 供求预测
    /* let PredictionLineParams = {
        jsonData: {}
    };
    api.PredictionLine.send(PredictionLineParams).then((res) => {
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
    }); */
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
//未来全国芒果批发价格预测
_initFuture(){


}

  render() {
    //灾害类型预警信息
    let TableData= this.state.AdisasterTableData;
    const me = this;
    const {areaSumDate,productSumDate,outputSumDate} =this.state;
    return (
      <div ref={ref => me.container = ref} style={this.homePageStyle} className="disasterWarp">
         <div style={{width: '410px', height: '245px', position: 'absolute', top: '86px', left: '1470px'}} className="right-item">
                    <Title content={'灾害类型预警信息'}  top={'0.2rem'} left={'0'}/>
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

                        }) : ''
                    }
                    </Carousel>
                    </ul>
         </div>
         <PercentPie ref={'deepProcessingPie'} centerY={'0.65'} style={me.pieStyle} title={'精深加工'}/>

       <YearPie ref={'saleMangoPie'} centerY={'0.57'} style={me.saleMangoStyle} title={'芒果消费结构'}/>
{/*芒果消费结构折线图*/}
         <div style={{width: '420px', height: '245px', position: 'absolute', top: '550px', left: '1470px'}}>
          <PureLineChange style={{width: '420px', height: '220px', position: 'absolute', top: '22px'}}
                          changePieHandle={me._changePieHandle.bind(me)}
                          ref={'mangoConsumInfo'}/>
        </div>
        <div style={{width: '410px', height: '235px', position: 'absolute', top: '807px', left: '168px'}}>
          <Title content={'平均单产趋势预测'}/>
          <PureLine style={{width: '410px', height: '213px', position: 'absolute', top: '22px'}}
                    ref={'aveYieldForecast'}/>
        </div>

        <div style={{width: '420px', height: '235px', position: 'absolute', top: '807px', left: '580px'}}>
          <Title content={'未来全国芒果批发价格预测'}/>
          {/* <PureLine style={{width: '420px', height: '213px', position: 'absolute', top: '22px'}} ref={'allStateSub'}/> */}
            <LineFuture style={this.wholesaleStyle} ref={'wholeSalePrice'}/>
        </div>

        <div style={{width: '420px', height: '200px', position: 'absolute', top: '150px', left: '168px'}}>
          <Title content={'种植生产'}/>
          <PureLine style={{width: '400px', height: '180px', position: 'absolute', top: '22px'}} ref={'greenPlant'}/>
        </div>

         {/* <div style={{width: '400px', height: '215px', position: 'absolute', top: '580px', left: '1480px'}}>
          <Title content={'芒果产值'}/>
          <PureBarDouble ref={'manggoValue'}
                         style={{width: '400px', height: '195px', position: 'absolute', top: '22px'}}/>
        </div> */}

        <div style={{width: '400px', height: '195px', position: 'absolute', top: '365px', left: '170px'}}>
          <Title content={'种植结构'}/>
          <PureBar ref={'plantForm'} style={{width: '400px', height: '175px', position: 'absolute', top: '10px'}}/>
        </div>

        <div style={{width: '400px', height: '235px', position: 'absolute', top: '807px', left: '1480px'}} className="predictionLine">
           {/*<p style={{color: '#46ebff', marginTop: '35px', fontSize: '14px'}}>预测2020年华坪芒果销售量将达到13.21万吨</p>
          <PureLine style={{width: '400px', height: '170px', position: 'absolute', top: '60px'}} ref={'mangoSaleCalc'}/>
            <PredictionLine style={{width: '400px', position: 'absolute', top: '60px'}} data={me.state.precisionData}/>*/}
            <Panel height={'100%'} width={400} style={{position: 'absolute', top: '60px',background:'none'}}>
                <Title content={'芒果产销预测'}  style={{position: 'absolute', top: '20px',left:'20px'}}/>
                <div style={{
                    cursor: 'pointer',
                    position: 'absolute',
                    zIndex: '3',
                    left: '180px',
                    top: '35px',
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
                    marginRight:'10px',
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
                    marginRight:'5px',
                    }}>销量预测值</span>
                </div>
                <CusomForeast ref={'consumForecast'} style={{width: '400px', height: '190px', position: 'absolute', top: '20px'}}/>
            </Panel>

        </div>

        <DataBox positions={{left: '615px', top: '127px'}} boxColor={'#46ebff'} icon={true} title={areaSumDate} unit={'万亩'}
                 typeData={'areaSum'}/>
        <DataBox positions={{left: '910px', top: '127px'}} boxColor={'#46ebff'} icon={true} title={productSumDate}  unit={'万吨'}
                 typeData={'productSum'}/>
        <DataBox positions={{left: '1200px', top: '127px'}} boxColor={'#2af594'} icon={false} color1={'#afffd9'}
                 color2={'#2af594'} title={outputSumDate} typeData={'outputSum'} unit={'亿元'}/>

        <Select _pullDownMes={this._pullDownMes.bind(this)}
                nameArr={['种植面积', '产量', '产值']} width={80}
                style={{
                  position: 'absolute',
                  top: '145px',
                  left: '450px'
                }}/>
        <div style={{width: '880px', height: '550px', position: 'absolute', top: '235px', left: '580px'}}>
          <div style={{width: '35px', height: '170px', position: 'absolute', top: '120px', left: '-2px', background: `url(${leftImg}) no-repeat center center`}}></div>
          <CircleBig/>

          <CircleSmall style={{position: 'absolute', top: '200px', left: '50px'}}
                       typeData={'wholeSale'} times={'month'}
                       propsMarke={'monthPriceBlue'}/>
          {/* 下方修改了 propsMarke的值 dataPriceBlue =》yearPriceBlue */}
          <CircleSmall style={{position: 'absolute', top: '200px', left: '290px'}}
                       typeData={'wholeSale'} times={'year'}
                       propsMarke={'yearPriceBlue'}/>
          <CircleSmall style={{position: 'absolute', top: '200px', left: '460px'}}
                       typeData={'retail'} times={'month'}
                       propsMarke={'monthPriceGreen'}/>
          {/* 下方修改了 propsMarke的值 dataPriceGreen =》yearPriceGreen */}
          <CircleSmall style={{position: 'absolute', top: '200px', left: '700px'}}
                       typeData={'retail'} times={'year'}
                       propsMarke={'yearPriceGreen'}/>
          <div style={{width: '35px', height: '170px', position: 'absolute', top: '120px', right: '0px',background: `url(${rightImg}) no-repeat center center`,}}></div>
        </div>

        <div style={{width: '400px', height: '232px', position: 'absolute', left: '1045px', top: '804px'}}>
          <Title content={'产销流向'}/>
          <p style={{color: '#46ebff', marginTop: '35px', fontSize: '14px'}}>
            {me.state.NowYear}年华坪鲜果主要销往{me.state.fresh}，加工品主要销往{me.state.process}</p>
          <EChartsSankey width={400} height={180} left={0} top={56}
                         ref={ref => me._echartsSankeyRef = ref}/>
        </div>

      </div>
    )
  }

  componentDidMount() {
    let me = this;
    // 经深加工
    me._tokens.push(api.deepProcessing
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {
            "year": "2018", //年份
            "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
          },
          "orderList": [{
            "columnName": "",
            "isASC": true
          }],
          "page": {
            "pageIndex": 1,
            "pageSize": 10
          }
        })
      })
      .then((res) => {
          //console.log(res)
        let data = res.data.resultList.slice();
        let bestSale = res.data.maxTrainsText;
        let titleYear = res.data.year;
        let totalSum = res.data.totalSum;
        let titleValue = (totalSum/100000000).toFixed(2);
        let lastData = data.map((item, index) => {
          return {
            names: item.strainsText,
            //per: parseFloat(item.processProportion) * 100,
            vals: (item.valueSum / totalSum).toFixed(2) || 0
          }
        });
        let colorsTop = ['#1093f5', '#11e0ff', '#2fffe4', '#2af594', '#fed645', '#5e6dff', '#007eff', '#00c1ff', '#02e9fa', '#50e3c2', '#05f791', '#f0ff59', '#fbca24', '#fd8918', '#ff4c22', '#ff0e71'];
        let colorsBottom = ['#1779ff', '#00b4ff', '#14e6ff', '#12d578', '#eea21f', '#5e6dff', '#007eff', '#00c1ff', '#02e9fa', '#50e3c2', '#05f791', '#f0ff59', '#fbca24', '#fd8918', '#ff4c22', '#ff0e71'];
        let obj = {
          titleShow: true,
          titleText: titleYear + '年华坪芒果加工品产值' + titleValue +'亿元，其中' + bestSale + '销量最好',
          legendShow: false,
          seriesName: '精深加工',
          seriesRadius: ['35%', '65%'],
          seriesCenter: ['50%', '65%'],
          seriesData: lastData,
          colorTop: colorsTop.concat(colorsTop),
          colorBottom: colorsBottom.concat(colorsBottom)
        };
        me.refs.deepProcessingPie.setData(obj);
      }));
    // 平均单产预测
    me._tokens.push(api.aveYieldForecast
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {},
          "orderList": [{
            "columnName": "",
            "isASC": true
          }],
          "page": {
            "pageIndex": 1,
            "pageSize": 10
          }
        })
      })
      .then((res) => {
        let data = res.data.slice();
        let xAxisData = [];
        let forecastData = [];
        let actualData = [];
        data.forEach((item, i) => {
          xAxisData.push(item.dateTime);
          forecastData.push((item.forecastVolume).toFixed(2));
          actualData.push((item.actualVolume).toFixed(2));
        });
        let obj = {
          showTitle: true,
          title: '单产(公斤/亩)',
          titleTop: 6,
          showTooltip: true,
          showTick: true,
          unitArr: ['公斤/亩', '公斤/亩'],
          circleArr: ['#00ffff', '#fde634'],
          lineColor: ['#00ffff', '#fde634'],
          showLegend: true,
          itemGap: 25,
          legendLeft: 150,
          legendTop: 10,
          legendName: ['实际单产', '预测单产'],
          gridLeft: '10%',
          gridTop: '20%',
          gridBottom: '20%',
          smooth: true,
          gridRight: '12%',
          //xData: [2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017],
          xData: xAxisData.slice(),
          //yData1: [1.3, 2.3, 4.7, 2.4, 1.5, 5.6, 3.3, 1.5],
          yData1: actualData.slice(),
          //yData2: [4.3, 3.3, 2.7, 5.4, 5.5, 2.6, 5.3, 1]
          yData2: forecastData.slice()
        };
        me.refs.aveYieldForecast.setData(obj);
      }));
//未来全国芒果批发价格预测
      me._tokens.push(api.wholeSaleJson
        .send({
          jsonData: JSON.stringify({
            "entityRelated": {
              "regionCode": "0",  	//地区code 0：全国, 530723：华坪县
              "regionName": "全国"   //地区name 0：全国, 530723：华坪县
            }
          })
        })
        .then((res) => {
          let data = res.data;
          let xAxisData = data.time.slice();
          let lineData = data.value.slice();
          let actualData = lineData.slice(0, 7).map((item, i) => {
            return Number(item).toFixed(2);
          });
          let predictData = lineData.slice().map((item, i) => {
            return Number(item).toFixed(2);
          });

          lineData.forEach((item, i) => {
            if (i < lineData.length - 2) {
              predictData[i] = '';
            }
          });
          let obj = {
            legendShow: true,
            // emptyLine: true,
            legendLeft: '45%',
            legendTop: '1%',
            legendData: ['实际', '预测'],
            legendOrient: 'horizontal',
            legendIcon: 'rect',
            legendItemWidth: 30,
            legendItemHeight: 5,
            legendItemGap: 60,
            dataZoomShow: true,
            gridLeft: '5%',
            gridTop: '23%',
            gridRight: '3%',
            gridBottom: '18%',
            seriesLineWidth: 2,
            seriesShadowColor: 'rgba(0,0,0,0.2)',
            xAxisBoundaryGap: false,
            yAxisName: '元 / 公斤',
            color: ['#46ebff', '#fde634'],
            colorTop: ['rgba(0,255,255,1)', 'rgba(253,230,52,1)'],
            colorBottom: ['rgba(0,255,255,0)', 'rgba(253,230,52,0)'],
            xAxisData: xAxisData,
            seriesDataOne: actualData,
            seriesDataTwo: predictData
          };
          me.refs.wholeSalePrice.setData(obj);
        }));

// 各环节差价
    me._tokens.push(api.allStateSub
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {
            "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
          },
          "orderList": [{
            "columnName": "",
            "isASC": true
          }],
          "page": {
            "pageIndex": 1,
            "pageSize": 10
          }
        })

      })
      .then((res) => {
          //console.log(res)
        let data = res.data.slice();
        let fieldData = []; //田头价
        let retailData = []; //零售价
        let dispatchData = []; //批发价
        let field = [];
        let retail = [];
        let dispatch = [];
        let xAxisData = [];
        data.forEach((item, index) => {
          //console.log(item)
          for (let i in item) {
            if (i.indexOf('field') > -1) {
              fieldData = item[i];
            } else if (i.indexOf('ecommerce') > -1) {
              retailData = item[i];
            } else if (i.indexOf('dispatch') > -1) {
              dispatchData = item[i];
            }
          }
        });
         fieldData.forEach((item, i) => {
          //xAxisData.push(item.times.substring(5));
          field.push(item.perPrice);
        });
        retailData.forEach((item, i) => {
          //console.log(item)
          xAxisData.push(item.times);
          retail.push(item.perPrice);
        });

        dispatchData.forEach((item, i) => {
          dispatch.push(item.perPrice);
        });
        let obj = {
          showTitle: true,
          title: '元/公斤',
          titleTop: 6,
          showTooltip: true,
          showTick: true,
          unitArr: [ '元/公斤', '元/公斤'],
          circleArr: [ '#2af594', '#fde634','#00ffff'],
          lineColor: [ '#2af594', '#fde634','#00ffff'],
          showLegend: true,
          itemGap: 25,
          legendLeft: 70,
          legendTop: 10,
          legendName: ['批发价', '电商价'],
          gridLeft: '8%',
          gridRight: '5%',
          gridTop: '20%',
          gridBottom: '20%',
          smooth: true,
          //xData: [2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017],
          xData: xAxisData.slice(),
          //yData1: [1.3, 2.3, 4.7, 2.4, 1.5, 5.6, 3.3, 1.5],
          //yData1: field.slice().reverse(),
          //yData2: [4.3, 3.3, 2.7, 5.4, 5.5, 2.6, 5.3, 1],
          yData2: retail.slice(),
          //yData3: [3.3, 2.3, 2.2, 5.9, 5.4, 2.1, 2.3, 1.9]
          yData1: dispatch.slice()
        };
        me.refs.allStateSub.setData(obj);
        //console.log(obj)
      }));

    // 芒果销量趋势预测
    me._tokens.push(api.mangoSaleCalc
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {},
          "orderList": [{
            "columnName": "",
            "isASC": true
          }],
          "page": {
            "pageIndex": 1,
            "pageSize": 10
          }
        })
      })
      .then((res) => {
        let data = res.data[0];
        let saleData = [];
        let xAxisData = [];
        //console.log(data)
        for (let i in data) {
          if (i.indexOf('date') > -1) {
            xAxisData = data[i];
          } else if (i.indexOf('sale') > -1) {
            //单位转换 孟宪志
            saleData = data[i].map((item, i) => {
              //let d = item * 0.0015;
              let d = item * 0.001;
              return (d / 10000).toFixed(2);
            });
            /*saleData.forEach((item, a) => {
              saleData[a] = (item / 10000000).toFixed(2);
            });*/
          }
        }
        /*for(let j = 0; j < saleData.length; j++){
            saleData[j] = saleData[j] / 1000;
        }*/
        let obj = {
          showTitle: true,
          title: '万吨',
          titleTop: 4,
          showTooltip: true,
          showTick: true,
          unitArr: [''],
          circleArr: ['#fde634'],
          lineColor: ['#fde634'],
          showLegend: false,
          itemGap: 25,
          legendLeft: 70,
          legendTop: 10,
          legendName: ['销量'],
          gridLeft: '7%',
          gridTop: '20%',
          gridBottom: '20%',
          gridRight: '8%',
          smooth: false,
          symbolSize: 6,
          // xData: [2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020],
          xData: xAxisData.slice(),
          //yData1: [144, 134, 125, 156, 160, 180, 189, 196],
          yData1: saleData.slice(),
          flag: 1,
          colorStartOne: 'rgba(42,245,148,1)',
          colorEndOne: 'rgba(42,245,148,0)'
        };
        me.refs.mangoSaleCalc.setData(obj);
      }));
    //芒果消费结构
    me._tokens.push(api.mangoConsumInfo
      .send({
        jsonData: JSON.stringify({
            "entityRelated": {
              "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
            },
            "orderList": [{
              "columnName": "",
              "isASC": true
            }],
            "page": {
              "pageIndex": 1,
              "pageSize": 10
            }
          }
        )
      })
      .then((res) => {
        let data = res.data.slice();
        let fresh = [];
        let freshList = [];
        let process = [];
        let processList = [];
        let wastage = [];
        let wastageList = [];
        let xAxisData = [];
        data.forEach((item, index) => {
          for (let i in item) {
            if (i.indexOf('fresh') > -1) {
              fresh = item[i];
            } else if (i.indexOf('process') > -1) {
              process = item[i];
            } else if (i.indexOf('wastage') > -1) {
              wastage = item[i];
            }
          }
        });
        fresh.forEach((item, i) => {
          //console.log(item)
          if (item.saleSum === "--") {
            freshList.push(item.saleSum)
          } else {
            freshList.push((item.saleSum / (1000 * 10000)).toFixed(2));
          }
          xAxisData.push(item.time);
        });
        process.forEach((item, i) => {
          if (item.saleSum === "--") {
            processList.push(item.saleSum)
          } else {
            processList.push((item.saleSum / (1000 * 10000)).toFixed(2));
          }
        });
        /* wastage.forEach((item, i) => {
          wastageList.push((item.wasteSum / (1000 * 10000)).toFixed(2));
        }); */
        let obj = {
          showTitle: true,
          title: '万吨',
          titleTop: 6,
          showTooltip: true,
          showTick: true,
          unitArr: ['万吨', '万吨', '万吨'],
          circleArr: ['#00ffff', '#fde634', '#2af594'],
          lineColor: ['#00ffff', '#fde634', '#2af594'],
          showLegend: true,
          itemGap: 25,
          legendLeft: 90,
          legendTop: 10,
          legendName: ['鲜果销量', '加工品销量'/* , '损耗量' */],
          gridLeft: '6%',
          gridTop: '20%',
          gridBottom: '10%',
          gridRight: '10%',
          smooth: true,
          //xData: [2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017],
          xData: xAxisData.slice(),
          //yData1: [30, 40, 45, 50, 65, 70, 75, 80],
          yData1: freshList.slice(),
          //yData2: [20, 34, 47, 34, 45, 56, 67, 77],
          yData2: processList.slice(),
          // yData3: [10, 15, 20, 22, 18, 23, 25, 28]
          yData3: wastageList.slice()
        };
        me.refs.mangoConsumInfo.setData(obj);
        let pieD = [
          {
            names: "鲜果销量",
            vals: freshList[freshList.length - 1]
          }, {
            names: "加工品销量",
            vals: processList[processList.length - 1]
          }/* , {
            names: "损耗量",
            vals: wastageList[wastageList.length - 1]
          } */
        ];
        let pieData = {
          titleShow: false,
          legendShow: false,
          seriesRadius: ['45%', '75%'],
          seriesCenter: ['50%', '57%'],
          seriesData: pieD,
          year: xAxisData[xAxisData.length - 1],
          colorTop: ['#11e0ff', '#2af594', '#fed645'],
          colorBottom: ['#00b4ff', '#12d578', '#eea21f']
        };
        me.refs.saleMangoPie.setData(pieData);
      }));

    //芒果产值
    me._tokens.push(api.manggoValue
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {
            "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
          },
          "orderList": [{
            "columnName": "",
            "isASC": true
          }],
          "page": {
            "pageIndex": 1,
            "pageSize": 10
          }
        })
      })
      .then((res) => {
        let data = res.data.slice();
        //console.log(data)
        let barData1 = data[0].freshList.slice();
        let barData2 = data[1].processList.slice();
        let xAxisData = [];
        let bar1 = [];
        let bar2 = [];
        barData1.forEach((item, i) => {
          xAxisData.push(item.YEAR);
          bar1.push((item.outputSum / 100000000).toFixed(2));
        });
        barData2.forEach((item, i) => {
          bar2.push((item.outputSum / 100000000).toFixed(2));
        });
        let obj = {
          showTitle: true,
          title: '亿元',
          titleTop: 6,
          titleLeft: 12,
          showTooltip: false,
          showTick: true,
          unitArr: ['亿元', '亿元'],
          circleArr: ['rgba(0,255,246,1)', 'rgba(255,243,141,1)'],
          showLegend: true,
          itemGap: 25,
          legendLeft: 125,
          legendTop: 10,
          legendName: ['鲜芒果', '芒果加工品'],
          gridLeft: '2%',
          gridTop: 0,
          gridBottom: 0,
          gridRight: '8%',
          smooth: true,
          num: 2,
          yNum: 1,
          //xData: [2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018],
          xData: xAxisData,
          //yData1: [30, 40, 45, 50, 65, 70, 75, 80, 90],
          yData1: bar1,
          //yData2: [20, 34, 47, 34, 45, 56, 67, 77, 88],
          yData2: bar2,
          colorStartOne: 'rgba(0,255,246,1)',
          colorEndOne: 'rgba(0,255,246,0.3)',
          colorStartTwo: 'rgba(255,243,141,1)',
          colorEndTwo: 'rgba(255,243,141,0.3)'
        };
        me.refs.manggoValue.setData(obj);
      }));

    //种植结构
    me._tokens.push(api.plantForm
      .send({
        jsonData: JSON.stringify({
            "entityRelated": {
              "year": "2018", //年份
              "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
            },
            "orderList": [{
              "columnName": "",
              "isASC": true
            }],
            "page": {
              "pageIndex": 1,
              "pageSize": 10
            }
          }
        )
      })
      .then((res) => {
        let data = res.data.slice();
        let xAxisData = [];
        let barData = [];
        let percentArr = [];
        data.forEach(function (item, index) {
          xAxisData.push(item.strainsText);
          barData.push({
            value: (item.growArea / 6670000).toFixed(2),
            percent: item.growProportion
          });
          percentArr.push(item.growProportion);
        });
        let obj = {
          showTitle: false,
          title: '万亩',
          titleTop: 6,
          showTooltip: true,
          showTick: true,
          unitArr: ['万亩'],
          circleArr: ['rgba(0,255,246,1)'],
          showLegend: false,
          itemGap: 25,
          legendLeft: 125,
          legendTop: 0,
          legendName: [''],
          gridLeft: '4%',
          gridTop: 0,
          gridBottom: 0,
          gridRight: '4%',
          smooth: true,
          num: 1,
          yNum: 1,
          // xData: ['凯特芒', '圣心芒', '象牙芒', '鹰嘴芒', '爱文芒', '台农芒', '澳芒', '金煌芒'],
          xData: xAxisData,
          // yData1: [30, 40, 45, 50, 65, 70, 75, 88],
          yData1: barData,
          percentData: percentArr,
          colorStartOne: 'rgba(0,204,255,1)',
          colorEndOne: 'rgba(0,204,255,0.3)'
        };
        me.refs.plantForm.setData(obj);
      }));

    /*let data = {
      nodes: [
        {name: '华坪产量', level: '0', value: 10},

        {name: '鲜芒果', level: '1', value: 5},
        {name: '加工品', level: '1', value: 5},

        {name: '北京', level: '2-1', value: 1.5},
        {name: '上海', level: '2-1', value: 0.5},
        {name: '浙江', level: '2-1', value: 1},
        {name: '江苏', level: '2-1', value: 1},
        {name: '河南', level: '2-1', value: 1},

        {name: '北京', level: '2-2', value: 2},
        {name: '江苏', level: '2-2', value: 1.5},
        {name: '山东', level: '2-2', value: 0.5},
        {name: '河北', level: '2-2', value: 1},
      ],
      links: [
        {source: 0, target: 1, value: 5},
        {source: 0, target: 2, value: 5},

        {source: 1, target: 3, value: 1.5},
        {source: 1, target: 4, value: 0.5},
        {source: 1, target: 5, value: 1},
        {source: 1, target: 6, value: 1},
        {source: 1, target: 7, value: 1},

        {source: 2, target: 8, value: 2},
        {source: 2, target: 9, value: 1.5},
        {source: 2, target: 10, value: 0.5},
        {source: 2, target: 11, value: 1},
      ]
    };*/
    //me._echartsSankeyRef.setData(data)
    /*产销流向*/
    me._tokens.push(api.produceSale
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {
            //"year": "2018", //年份
            "regionId": "530723", //地区region_id :华坪县530723 百色451000 攀枝花 510400
            "limit": "5"	//各需要展示几条数据
          },
          "orderList": [{
            "columnName": "",
            "isASC": true
          }],
          "page": {
            "pageIndex": 1,
            "pageSize": 10
          }
        })
      })
      .then((res) => {
        //console.log(res)
        let data = res.data;
        let total = undefined;
        let processTotal = undefined;
        let freshTotal = undefined;
        let fresh = [];//鲜果销售数据
        let process = []; //加工品销售数据
        data.forEach((item, index) => {
          for (let i in item) {
            if (i.toLowerCase().indexOf('total') > -1) {
              total = item[i];
            } else if (i.toLowerCase().indexOf('fresh') > -1) {
              fresh = item[i];
            } else if (i.toLowerCase().indexOf('process') > -1) {
              process = item[i];
            }
          }
        });
        let nodes = [
          {name: '华坪产量', index: 0, level: '0', value: (total / (1000 * 10000)).toFixed(2)}
        ];
        let freshSum = 0;
        let processSum = 0;
        fresh.forEach((item, i) => {
          nodes.push({
            name: item.regionText,
            level: '2-1',
            names: '鲜芒果',
            marketName:item.marketName,
            index: 300,
            value: (item.saleSum / 10000000).toFixed(2)
          });
          freshSum += item.saleSum / 10000000;
        });
        process.forEach((item, i) => {
          nodes.push({
            name: item.regionText,
            level: '2-2',
            names: '加工品',
            marketName:item.marketName,
            index: 400,
            value: (item.saleSum / (1000 * 10000)).toFixed(2)
          });
          processSum += item.saleSum / (1000 * 10000);
        });
        nodes.push({
          name: '鲜芒果',
          level: '1',
          index: 1,
          value: freshSum.toFixed(1)
        });
        nodes.push({
          name: '加工品',
          level: '1',
          index: 2,
          value: processSum.toFixed(1)
        });
        nodes.sort((a, b) => {
          return a.index - b.index;
        });
        let lastNodes = nodes.map((item, i) => {
          return {
            name: item.name,
            level: item.level,
            value: item.value,
            marketName:item.marketName
          }
        });

        let links = [];
        nodes.forEach((item, i) => {
          if (i > 0 && item.index === i) {
            links.push({
              source: 0,
              target: i,
              value: item.value,
              marketName:item.marketName
            });
          } else if (item.names === '鲜芒果') {
            links.push({
              source: 1,
              target: i,
              value: item.value,
              marketName:item.marketName
            });
          } else if (item.names === '加工品') {
            links.push({
              source: 2,
              target: i,
              value: item.value,
              marketName:item.marketName
            });
          }
        });
        let freshCopy = fresh.slice();
        let processCopy = process.slice();
        freshCopy.sort((a, b) => {
          return b.value - a.value;
        });
        processCopy.sort((a, b) => {
          return b.value - a.value;
        });

        me._echartsSankeyRef.setData({
          nodes: lastNodes,
          links: links
        });
        me.setState({
          fresh: freshCopy[0].regionText,
          process: processCopy[0].regionText,
          NowYear:res.data[0].year
        });
      }));

    //绿色种植
    me._tokens.push(api.plantSpace
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {
            "regionId": "530723" //地区region_id :华坪县530723 百色451000 攀枝花 510400
          },
          "orderList": [{
            "columnName": "",
            "isASC": true
          }],
          "page": {
            "pageIndex": 1,
            "pageSize": 10
          }
        })
      })
      .then((res) => {
        //console.log(res)
        let data = res.data;
        let areas = data.areas;

        let greenArea = []; //绿色认证面积
        let organicArea = [];//有机认证面积
        let totalArea = [];//总面积
        let xAxisData = data.xData;

        for (let i in areas) {
          if (i.indexOf('green') > -1) {
            greenArea = areas[i];
          } else if (i.indexOf('organic') > -1) {
            organicArea = areas[i];
          } else if (i.indexOf('total') > -1) {
            totalArea = areas[i];
          }
        }

        let title = '万亩';
        let unitArr = ['万亩', '万亩', '万亩'];
        let legendName = ['总种植面积', '有机产品认证面积', '绿色认证面积'];
        //let yData1 = [30, 40, 45, 50, 65, 70, 75, 80];
        let yData1 = totalArea.map((item, i) => {
          let d = item * 0.0015;
          // return Math.round(d);
          return (d / 10000).toFixed(2)
          /*if (d.toString().indexOf('.') > -1) {
            return d.toFixed(1);
          } else {
            return d
          }*/
        });
        //let yData2 = [20, 34, 47, 34, 45, 56, 67, 77];
        let yData2 = organicArea.map((item, i) => {
          //console.log(item)
          let d = item * 0.0015;
          // return Math.round(d);
          return (d / 10000).toFixed(2)
        });
        //let yData3 = [10, 15, 20, 22, 18, 23, 25, 28];
        let yData3 = greenArea.map((item, i) => {
          let d = item * 0.0015;
          // return Math.round(d);
          return (d / 10000).toFixed(2)
        });
        let obj = {
          showTitle: true,
          title: title,
          titleTop: 6,
          showTooltip: true,
          showTick: true,
          unitArr: unitArr,
          circleArr: ['#00ffff', '#fde634', '#2af594'],
          lineColor: ['#00ffff', '#fde634', '#2af594'],
          showLegend: true,
          itemGap: 10,
          legendLeft: 55,
          legendTop: 10,
          legendName: legendName,
          gridLeft: '8%',
          gridRight: '4%',
          gridTop: '20%',
          gridBottom: '15%',
          smooth: true,
          //xData: [2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017],
          xData: xAxisData.slice(),
          yData1: yData1,
          yData2: yData2,
          yData3: yData3
        };
        me.refs.greenPlant.setData(obj);
      }));

      //灾害类型预警信息
      this._initAdisasterTable()
      //右下角供求预测
      this._initLineEcharts()
      //未来全国芒果批发价格预测
      this._initFuture()

  }

  componentDidUpdate() {

  }

  componentWillUnmount() {
    this._clearTokens();
  }

}

export default HomePage;
