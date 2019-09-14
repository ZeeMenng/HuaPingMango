import React,{Component} from 'react';
import echart from 'echarts';

class Pie extends Component {
  constructor(props){
    super(props);
    let me = this;
    this._flag = false;
    me.echartStyle = {
      ...me.props.style
    };
    this.t = '';
  }

  _flag = undefined;

  setData(d){
    this._flag = true;
    this.setState({
      data: d
    })
  }

  render(){
    let me = this;
    if(me._flag){
      return(
        <div>
          <div ref={'logo'} style={{color:'#8ffbfe',textAlign:'center',fontSize:'18px',lineHeight:'30px',left:'210px',top:'112px',position:'absolute',width:'60px',height:'30px'}}>{this.t}</div>
          <div style={me.echartStyle} ref={'echarts'}>
          </div>
        </div>

      )
    }else{
      return(
        <div></div>
      )
    }
  }

  componentDidMount(){
  }
  componentDidUpdate(){
    let me = this;
    if(!me._flag) return;
    let box = me.refs.echarts;
    let echarts = echart.init(box);
    let config = {
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
      title:{
        show: config.titleShow,
        text: config.titleText,
        top: 15,
        textStyle:{
          color: '#46ebff',
          fontSize: 16,
          align: 'left'
        }
      },
      // tooltip: {
      //   trigger: 'item',
      //   formatter: "{a} <br/>{b}: {c} ({d}%)"
      // },
      legend: {
        show: config.legendShow,
        orient: 'vertical',
        x: 'left',
        data: config.legendData || []
      },
      series : [
        {
          name: '访问来源',
          type: 'pie',
          radius: config.seriesRadius,
          center: config.seriesCenter,
          hoverOffset: 5,
          selectedOffset: 5,
          label:{
            color: '#fff',
            fontSize: 16,
            padding: 10,
            formatter: function(d){
              return d.name + '\n' + d.value + config.seriesData[0].unit || '万吨' +' '+ d.percent + '%'
            }
          },
          // emphasis:{
          //   itemStyle:{
          //     borderColor: 'transparent',
          //     borderWidth: 10
          //   }
          // },
          labelLine: {
            length: 5,
            length2: 15,
            lineStyle:{
              color: '#30b4fc'
            }
          },
          data: []
        }
      ]
    };

    let arr = config.seriesData;

    arr.map((v,i)=>{
      option.series[0].data.push({
        value: v.vals,
        name: v.names,
        itemStyle:{
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
              //返回的种类过多时颜色不够用，大于五种后随机取颜色 孟宪志
            colorStops: [{
              offset: 0, color: config.colorTop[i>4?parseInt(Math.random()*(4+1),10):i] // 0% 处的颜色
            }, {
              offset: 1, color: config.colorBottom[i>4?parseInt(Math.random()*(4+1),10):i] // 100% 处的颜色
            }],
            globalCoord: false // 缺省为 false
          }
        }
      })
    });

    echarts.setOption(option);
    echarts.on("mouseover", function (params) {
      me.refs.logo.innerHTML = params.percent + '%';
    });
    echarts.on("mouseout", function () {
      me.refs.logo.innerHTML = '';
    });
  }
}
export default Pie;
