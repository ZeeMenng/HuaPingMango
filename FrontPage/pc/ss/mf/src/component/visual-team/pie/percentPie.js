import React, {Component} from 'react';
import echart from 'echarts';

class Pie extends Component {
  constructor(props) {
    super(props);
    let me = this;
    this._flag = false;
    me.echartStyle = {
      ...me.props.style
    };
    me.h2 = {
      height: '40px',
      fontSize: '22px',
      margin: 0,
      color: '#46ebff'
    };
    me.state = {
      per: 0,
      data: undefined
    };
  }

  echarts = undefined;
  _flag = undefined;

  setData(d) {
    this._flag = true;
    this.setState({
      data: d
    })
  }

  render() {
    let me = this;
    return (
      <div style={me.echartStyle}>
        <h2 style={me.h2}>
          <p style={{
            height: '22px',
            lineHeight: '22px',
            borderLeft: '3px solid #46ebff',
            paddingLeft: '8px'
          }}>{me.props.title}</p>
        </h2>
        <div ref={'echarts'} style={{
          width: me.echartStyle.width,
          height: parseInt(me.echartStyle.height) - 40 + 'px'
        }}>
        </div>
        <div ref={'pieTipRef'}
             style={{
               width: '100px',
               height: '40px',
               zIndex: 9,
               display: 'none',
               textAlign: 'center',
               fontSize: '16px',
               lineHeight: '40px',
               position: 'absolute',
               left: parseFloat(me.echartStyle.width) / 2 - 50 + 'px',
               top: (parseFloat(me.echartStyle.height) - 40) * me.props.centerY + 20 + 'px'
             }}>
          {me.state.per}
        </div>
      </div>
    )
  }

  componentDidMount() {
    let me = this;
    let box = me.refs.echarts;
    me.echarts = echart.init(box);

    me.echarts.on('mouseover', function (e) {
      me.refs.pieTipRef.style.color = e.color.colorStops[0].color;
      me.refs.pieTipRef.style.display = 'block';
      me.setState({
        per: e.percent + '%'
      });
    });
    me.echarts.on('mouseout', function (e) {
      me.refs.pieTipRef.style.display = 'none';
    });
  }

  componentWillUnmount() {
    let me = this;
    if (me.echarts) {
      me.echarts.dispose();
      me.echarts = undefined;
    }
  }

  componentDidUpdate() {
    let me = this;
    if (!me._flag) return;

    let config = {
      seriesName: me.state.data.seriesName,
      titleShow: me.state.data.titleShow,
      titleText: me.state.data.titleText,
      legendShow: me.state.data.legendShow,
      legendData: me.state.data.legendData,
      seriesRadius: me.state.data.seriesRadius,
      seriesCenter: me.state.data.seriesCenter,
      seriesData: me.state.data.seriesData,
      colorTop: me.state.data.colorTop,
      colorBottom: me.state.data.colorBottom
    };
    let option = {
      title: {
        show: config.titleShow,
        text: config.titleText,
        textStyle: {
          color: '#46ebff',
          fontSize: 14,
          align: 'left'
        }
      },
      /* tooltip: {
         trigger: 'item',
         formatter: "{a} <br/>{b}: {c} ({d}%)"
       },*/
      legend: {
        show: config.legendShow,
        orient: 'vertical',
        x: 'left',
        data: config.legendData || []
      },
      series: [
        {
          name: config.seriesName || '芒果消费结构',
          type: 'pie',
          radius: config.seriesRadius,
          center: config.seriesCenter,
          hoverOffset: 5,
          selectedOffset: 5,
          label: {
            color: '#fff',
            fontSize: 16,
            padding: 10
          },
          // emphasis:{
          //   itemStyle:{
          //     borderColor: 'transparent',
          //     borderWidth: 10
          //   }
          // },
          labelLine: {
            length: 15,
            length2: 25,
            lineStyle: {
              color: '#30b4fc'
            }
          },
          data: []
        }
      ]
    };

    let arr = config.seriesData;

    arr.map((v, i) => {
      option.series[0].data.push({
        value: v.vals,
        name: v.names,
        itemStyle: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [{
              offset: 0, color: config.colorTop[i] // 0% 处的颜色
            }, {
              offset: 1, color: config.colorBottom[i] // 100% 处的颜色
            }],
            globalCoord: false // 缺省为 false
          }
        }
      })
    });

    me.echarts.setOption(option, true);

  }
}

export default Pie;
