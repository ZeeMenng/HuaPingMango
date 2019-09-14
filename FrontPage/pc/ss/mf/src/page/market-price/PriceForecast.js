import React from 'react';
import * as api from '../api/api-price-forecast';
import bg from './price-future.png';
import Panel from '../../component/visual-team/panel/Panel';
import Title from '../../component/visual-team/title/Title';
import Select from '../../component/visual-team/other/Select';
import LineFuture from '../../component/visual-team/line/LineFuture1';
import QuadrantDiagram from '../../component/visual-team/d3/QuadrantDiagram';

/**
 * 市场价格--价格预测
 */
class PriceForecast extends React.Component {
  constructor(props) {
    super(props);
    this.wholesaleStyle = {
      width: '540px',
      height: '285px',
      position: 'absolute',
      left: 0,
      top: '48px'
    };
  }

  _pullDownMes(option) {
    let me = this;
    let o = {};
    if (option.name === '全国') {
      o = {
        "regionCode": "0",  	//地区code 0：全国, 530723：华坪县
        "regionName": "全国"
      }
    } else if (option.name === '华坪县') {
      o = {
        "regionCode": "530723",  	//地区code 0：全国, 530723：华坪县
        "regionName": "华坪县"   //地区name 0：全国, 530723：华坪县
      }
    }
    me._tokens.push(api.wholeSaleJson.send({
      jsonData: JSON.stringify({
        "entityRelated": o
      })
    }).then((res) => {
      let data = res.data;
      let xAxisData = data.time.slice();
      let lineData = data.value.slice();
      let len = lineData.length;
      let actualData = lineData.slice(0, len - 1).map((item, i) => {
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
        legendTop: '8%',
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
        /*xAxisData: ['2018-4-16', '2018-4-17', '2018-4-18', '2018-4-19', '2018-4-20', '2018-4-21', '2018-4-22', '2018-4-23', '2018-4-24', '2018-4-25'],*/
        xAxisData: xAxisData,
        // seriesDataOne: [5, 6.5, 5.6, 6, 5.5, 7, 6.5, 4.5],
        // seriesDataTwo: ['', '', '', '', '', '', '', 4.5, 5.5, 6]
        seriesDataOne: actualData,
        seriesDataTwo: predictData
      };
      me.refs.wholeSalePrice.setData(obj);
    }));
  }

  render() {
    let me = this;
    return (
      <div style={{
        width: '1920px',
        height: '1080px',
        background: `url(${bg}) no-repeat center bottom`,
        backgroundSize: '1920px 220px'
      }}>
        <Panel height={335} width={540} left={'1340px'} top={'125px'}>
          <Title left={20} top={20} content={'未来全国芒果批发价格预测'}/>
          {/* <Select _pullDownMes={this._pullDownMes.bind(this)} nameArr={['华坪县', '全国']} width={115} style={{
            position: 'absolute',
            top: '15px',
            right: '14px'
          }}/> */}

          <LineFuture style={this.wholesaleStyle} ref={'wholeSalePrice'}/>

        </Panel>

        <Panel height={335} width={540} left={'1340px'} top={'511px'}>
          <Title left={20} top={20} content={'全国芒果批发价格波动性预测'}/>
          <LineFuture style={this.wholesaleStyle} ref={'countryWholeSalePrice'}/>
        </Panel>

        <QuadrantDiagram ref={ref => {
          me._fourMatrixRef = ref;
        }}/>
      </div>
    )
  }

  _tokens = [];

  _clearTokens() {
    this._tokens.forEach(token => token.cancel());
    this._tokens = [];
  }

  componentDidMount() {
    let me = this;
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
          legendTop: '8%',
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
          /*xAxisData: ['2018-4-16', '2018-4-17', '2018-4-18', '2018-4-19', '2018-4-20', '2018-4-21', '2018-4-22', '2018-4-23', '2018-4-24', '2018-4-25'],*/
          xAxisData: xAxisData,
          // seriesDataOne: [5, 6.5, 5.6, 6, 5.5, 7, 6.5, 4.5],
          // seriesDataTwo: ['', '', '', '', '', '', '', 4.5, 5.5, 6]
          seriesDataOne: actualData,
          seriesDataTwo: predictData
        };
        me.refs.wholeSalePrice.setData(obj);
      }));

    me._tokens.push(api.wholeCountryPredict
      .send({
        jsonData: JSON.stringify({
          "entityRelated": {
            "regionCode": "0",  	//地区code 0：全国
            "regionName": "全国"   //地区name 0：全国
          }
        })
      })
      .then((res) => {
        let data = res.data;
        let xAxisData = data.time.slice();
        let lineData = data.value.slice();
        let len = lineData.length;
        let actualData = lineData.slice(0, len - 1).map((item, i) => {
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
          emptyLine: true,
          legendLeft: '55%',
          legendTop: '8%',
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
          yAxisName: '%',
          color: ['#46ebff', '#fde634'],
          colorTop: ['rgba(0,255,255,1)', 'rgba(253,230,52,1)'],
          colorBottom: ['rgba(0,255,255,0)', 'rgba(253,230,52,0)'],
          /*xAxisData: ['2018-4-16', '2018-4-17', '2018-4-18', '2018-4-19', '2018-4-20', '2018-4-21', '2018-4-22', '2018-4-23', '2018-4-24', '2018-4-25'],*/
          xAxisData: xAxisData,
          //seriesDataOne: [5, 6.5, 5.6, 6, 5.5, 7, 6.5, 4.5, 5.5, 6],
          seriesDataOne: actualData.map((v,i)=>{ return (v*100).toFixed(2)}),
          //seriesDataTwo: [1.2, 2, 3, 3.5, 4, 4.2, 3, 2.5, 1.5, 2]
          seriesDataTwo: predictData.map((v,i)=>{ if(!v) return '-'; else return (v*100).toFixed(2)})
        };
        me.refs.countryWholeSalePrice.setData(obj);
      }));

    me._tokens.push(api.getMatrix
      .send({
        jsonData: JSON.stringify({
          "page": {
            "pageIndex": 1,
            "pageSize": 100
          }
        })
      })
      .then((res) => {
        let data = res.data;
        if (data.length <= 0) {
          return;
        }
        me._fourMatrixRef._setData(data.slice());
      }));
  }
}

export default PriceForecast;
