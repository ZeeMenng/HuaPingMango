import React, { Component } from 'react';
import { createElement, setStyles, UiComponent } from '@jusfoun-vis/common';
import MapType1 from './MapType1';
import GeoJSON_140600 from './hua.json';

/**
 * React地图。
 * @author Molay
 * 修改 xf
 */
class ReactCityMap extends Component {
  isRealUpdate = false;

  constructor(props) {
    super(props);
    this.state = { data: [{ key: [] }] };
  }

  render() {
    let me = this;
    let props = me.props;
    return (
      <div ref={'domElement'} style={{ ...props.style }}>
        <div>
          <p>{me.state.title}</p>
          <div onMouseOut={(e)=>{ console.log(this.refs.wrapRef); this.refs.wrapRef.style.display = 'none';
    }} ref={'wrapRef'} style={{ position: 'absolute', width:'1px',height:'2px',zIndex: 10,  }} >{me.state.node}</div>
        </div>
      </div>
    );
  }

  getData(data) {
    this.setState({ data });
  }

  creatTooltip(event) {
    let name = event.feature.properties.name;
    // console.log(event);
    let x = event.position.x;
    let y = event.position.y;
    let data = '';
    this.state.data.forEach((s, i) => {
      if (s.name == name) {
        data = s;
      }
    });
    let style = {
      display: 'flex',
      width: 180,
      padding:0
    };
    let styleP = {
      width: 80,
      minWidth:'80px',
      lineHeight:'16px',
      margin:5,
    }
    let node = (
      <div style={{
        width: 200,
        backgroundColor: 'rgba(25,31,106,.7)',
        padding: 10,
        borderRadius: '5px',
        transform: `translate(${x}px,${y}px)`,
        transition: 'transform .5s',
        pointerEvents: 'none'
      }}>
        <p style={{
          fontSize: 18,
          color: '#46ebff'
        }}>{data.name}</p>
        <ul style={{
          display: 'flex',
          flexDirection: 'column',
          color: '#fff',
          fontSize: 16,
          margin:2,
        }}>
          <li style={style}>
            <p style={styleP}>种植户：</p>
            <p style={styleP}>{data.peasantCount}户</p>
          </li>
          <li style={style}>
            <p style={styleP}>种植面积：</p>
            <p style={styleP}>{data.organicIdentAreaSum}万亩</p>
          </li>
          {/* <li style={style}>
            <p style={styleP}>种植品种：</p>
            <p style={{
              flex: 1,
              width: 80,
              minWidth:'80px',
              lineHeight:'16px',
              margin:2,
            }}>{data.strainsText}</p>
          </li> */}
          <li style={style}>
            <p style={styleP}>产量：</p>
            <p style={styleP}>{(data.outputSum / 10000).toFixed(2)}万吨</p>
          </li>
        </ul>
      </div>
    );
    this.setState({ node })
  }

  componentDidUpdate() {
    const me = this;
  }

  componentDidMount() {
    let me = this;
    let props = me.props;

    // me.refs.domElement.appendChild(me.creatTooltip());

    let showPath = props.showPath;
    let mapCallBack = props.mapCallBack || function (e) {
      // console.log(e);
    };
    let unShowText = props.unShowText;
    let map = new MapType1({
      clickCallBack: mapCallBack,
      unShowText
    });

    map.geoJson = GeoJSON_140600;
    map.width = props.width;
    map.height = props.height;
    map.numSandwiches = 2;  //层数
    map.sandwichGap = 20;  //每层间距
    map.fillTexture = '#179ffb';  //纹理填充
    map.fillTextureOpacity = 0.7;
    // 自动缩放模式
    map.autoScale = true;
    map.aspectRatio = 0.9;  //宽高比
    map.padding = 30;  //边距
    // 手动设置尺寸模式
    // map.width = 400;
    // map.height = 800;
    // map.scaleX = 500;
    // map.scaleY = 600;
    me.refs.domElement.appendChild(map.domElement);
    map.validateNow();

    //鼠标事件
    map.on('hover', function (event) {
      me.refs.wrapRef.style.display = 'block';
      me.creatTooltip(event)
      // console.log(event)
    });

    map.on('mouseout', function (event) {
      me.refs.wrapRef.style.display = 'none';
    });

    let path1 = [
      [101.267724, 26.500499],
      [101.284072, 26.586449],
      [101.282275, 26.588581],
      [101.282275, 26.588516],
      [101.281844, 26.589227],
      [101.281988, 26.590778],
      [101.283928, 26.591812],
      [101.282994, 26.593815],
      [101.281844, 26.595494],
      [101.283856, 26.604927],
      [101.292684, 26.629926],
      [101.303856, 26.634927],
      [101.312684, 26.649926]
    ];

    // 测试路径数据
    if (showPath) map.pathData = [
      {
        name: '道路1',
        coordinates: path1
      },
      {
        name: '道路2',
        coordinates: [
          [112.302397, 40.249045],
          [112.334592, 40.192632],
          [112.375986, 40.171465],
          [112.403151, 40.077152],
          [112.444545, 40.042693],
          [112.468692, 40.002026],
          [112.465242, 39.971068],
          [112.491688, 39.948062],
          [112.505486, 39.932129],
          [112.505486, 39.84798],
          [112.556079, 39.818725],
          [112.576776, 39.800102],
          [112.585974, 39.773488],
          [112.635417, 39.68559],
          [112.669912, 39.646487],
          [112.697508, 39.606473],
          [112.738902, 39.586013],
          [112.804442, 39.558426],
          [112.805592, 39.555755],
          [112.851585, 39.53617],
          [112.872282, 39.532608],
          [112.910227, 39.472034],
          [112.945872, 39.44975],
          [112.99589, 39.363473],
          [113.008538, 39.32508]
        ]
      },
      {
        name: '道路3',
        coordinates: [
          [112.152488, 39.731773],
          [112.198481, 39.69625],
          [112.20768, 39.616257],
          [112.260572, 39.582454],
          [112.290468, 39.582454],
          [112.285869, 39.545073],
          [112.262872, 39.514798],
          [112.295067, 39.45599],
          [112.311165, 39.393563],
          [112.324963, 39.338224],
          [112.354858, 39.284628],
          [112.318064, 39.238145],
          [112.295067, 39.222047],
          [112.235276, 39.188051]
        ]
      },
      {
        name: '道路4',
        coordinates: [
          [111.924689, 39.607113],
          [112.001214, 39.608905],
          [112.088098, 39.609142],
          [112.219178, 39.591351],
          [112.290468, 39.580674],
          [112.290468, 39.580674],
          [112.467542, 39.626928],
          [112.515835, 39.586013],
          [112.623919, 39.561096],
          [112.644616, 39.577115],
          [112.704407, 39.587792],
          [112.833188, 39.573555],
          [112.899878, 39.571776],
          [112.95967, 39.589572],
          [112.984966, 39.582454],
          [113.02176, 39.577115],
          [113.136744, 39.589572],
          [113.258626, 39.589572],
          [113.306919, 39.561096],
          [113.378208, 39.571776],
          [113.454887, 39.58871]
        ]
      },
      {
        name: '道路5',
        coordinates: [
          [113.185036, 39.925047],
          [113.088451, 39.850639],
          [113.060855, 39.808084],
          [113.010262, 39.779699],
          [113.010262, 39.779699],
          [112.835488, 39.648265],
          [112.835488, 39.639376],
          [112.82169, 39.521923],
          [112.812491, 39.504109],
          [112.794094, 39.486291],
          [112.82169, 39.463121],
          [112.800993, 39.407837],
          [112.794094, 39.323936],
          [112.791794, 39.230991],
          [112.859784, 39.196209]
        ]
      }
    ];

    // const color = ['#0E52BC', '#1370D9', '#1B9AD9', '#20A5C9'];

    map.idField = 'id';

    me.map = map;

    // if (showPath) me.formatterData(map.pathData);
  }

  formatterData(arr) {
    const me = this;
    const props = me.props;
    const len = arr.length;
    if (!len) return false;

    for (let i = 0; i < len; i++) {
      let item = arr[i].coordinates;
      // if (i === 0) me.formatterPosition(item);
      me.formatterPosition(item);
    }
  }

  formatterPosition(arr) {
    const me = this;
    let totalLength = 0;
    let positions = [];
    let len = arr.length;

    for (let i = 0; i < len; i++) {
      let curPosition = me.getPosition(...arr[i]);
      if (i === 0) {
        curPosition.accumulateLength = 0;
      } else {
        let prevPosition = me.getPosition(...arr[i - 1]);
        totalLength += Math.sqrt(Math.pow(curPosition.x - prevPosition.x, 2) + Math.pow(curPosition.y - prevPosition.y, 2));
        curPosition.accumulateLength = totalLength;
      }
      positions.push(curPosition);
    }

    for (let i = 0; i < len; i++) {
      let item = positions[i];
      item.percent = item.accumulateLength / totalLength;
    }

    me.drawEffect(positions);
  }

  drawEffect(arr) {
    const me = this;
    const props = me.props;

    let canvasElement = createElement('canvas');
    setStyles(canvasElement, {
      position: 'absolute',
      left: '0',
      top: '0'
    });
    canvasElement.width = props.width;
    canvasElement.height = props.height;
    me.refs.domElement.appendChild(canvasElement);

    let context = canvasElement.getContext('2d');

    let t = 0;
    let dt = 1 / 500;
    let alpha = 1;
    let len = arr.length;
    let drawCircle = [];
    let drawCount = 20;

    const animate = function () {
      let v0, v1;
      for (let i = len - 1; i >= 0; i--) {
        const v = arr[i];
        if (v.percent <= t) {
          v0 = v;
          break;
        }
      }
      for (let i = 0; i < len; i++) {
        const v = arr[i];
        if (v.percent >= t) {
          v1 = v;
          break;
        }
      }

      const percent = (t - v0.percent) / (v1.percent - v0.percent);
      const v = {
        x: v0.x + (v1.x - v0.x) * percent,
        y: v0.y + (v1.y - v0.y) * percent
      };

      drawCircle.push(v);
      if (drawCircle.length > drawCount) {
        drawCircle.shift();
      }

      context.clearRect(0, 0, props.width, props.height);
      for (let i = 0; i < drawCircle.length; i++) {
        let _v = drawCircle[i];
        context.restore();
        context.beginPath();
        context.fillStyle = `rgba(255,255,255,${alpha * i / (drawCount - 1)})`;
        context.arc(_v.x, _v.y, 5, 0, 2 * Math.PI);
        context.fill();
        context.closePath();
        context.save();
      }

      t += dt;
      if (t > 1) t -= 1;
      requestAnimationFrame(animate);
    };
    animate();
  }

  getPosition(lng, lat) {
    return this.map.lonLatToLocal(lng, lat);
  }

  nameCpMap = {};

  getPositionWithName(areaName) {
    const me = this;
    if (me.isEmpty(this.nameCpMap)) {
      const geoJson = GeoJSON_140600;
      geoJson.features.map(t => {
        me.nameCpMap[t.properties.name] =
          {
            position: me.getPosition(...t.properties.cp),
            feature: {
              id: t.id,
              properties: {
                name: t.properties.name
              }
            }
          };
      });
    }

    return me.nameCpMap[areaName];
  }

  /* 加载地图数据 */
  _updateMark(res) {
    let me = this;
    const _data = [
      {
        name: '小南山生态公园',
        value: parseInt(Math.random() * 100),
        coord: [112.321513, 39.472479],
        isUp: true
      },
      {
        name: '桑干河生态湿地公园',
        value: parseInt(Math.random() * 100),
        coord: [112.844112, 39.476044],
        isUp: false
      }
    ];
    // let data = res || _data;
    // console.log(GeoJSON_140600.features);
    let data = GeoJSON_140600.features;
    let len = data.length;
    for (let i = 0; i < len; i++) {
      let item = data[i].properties;
      let position = me.getPosition(...item.cp);
      data[i].position = position;
      data[i].name = item.name;
      // data[i].aaa = item.name;
    }
    // console.log(GeoJSON_140600);
    me.props.markPositionCallBack(data);
  }

  // componentDidUpdate() {
  //   if (this.isRealUpdate) this.map.data = this.state.data;
  // }

  componentWillUnmount() {
  }

  _update(d) {
    // let color = ['#20A5C9', '#1B9AD9', '#1370D9', '#0E52BC'];
    // let data = d || [
    //   {
    //     id: '140602',
    //     name: '朔城区',
    //     color: color[parseInt(Math.random() * 3)]
    //   },
    //   {
    //     id: '140603',
    //     name: '平鲁区',
    //     color: color[parseInt(Math.random() * 3)]
    //   },
    //   {
    //     id: '140624',
    //     name: '怀仁县',
    //     color: color[parseInt(Math.random() * 3)]
    //   },
    //   {
    //     id: '140621',
    //     name: '山阴县',
    //     color: color[parseInt(Math.random() * 3)]
    //   },
    //   {
    //     id: '140622',
    //     name: '应县',
    //     color: color[parseInt(Math.random() * 3)]
    //   },
    //   {
    //     id: '140623',
    //     name: '右玉县',
    //     color: color[parseInt(Math.random() * 3)]
    //   }
    // ];
    // this.isRealUpdate = true;
    // console.log(data)
    // this.setState({ data });
  }

  isEmpty(obj) {
    let item = null;
    for (item in obj) {
      if (hasOwnProperty.call(obj, item)) {
        return false;
      }
    }
    return true;
  }
}

export default ReactCityMap;
