import React, {Component} from 'react';
import bg from './img/fourIndex.png';
import BallCharts from '../ball/Ball3d';
import Category from '../ball/Category';

class QuadrantDiagram extends Component {
  constructor(props) {
    super(props);
    let me = this;

    me.state = {
      arr: [
        {
          name: "苹果",
          districtName: "111",
          id: "AF01001",
          quadrant: 'a',
          x: 200,
          y: 200
        }, {
          name: "鸡蛋",
          districtName: "222",
          id: "AL05001",
          x: -200,
          y: 200
        }, {
          name: "猪肉",
          districtName: "333",
          id: "AL01002",
          x: 200,
          y: -200
        }, {
          name: "猪肉",
          districtName: "444",
          id: "AL01002",
          x: -200,
          y: -200
        }, {
          name: "猪肉",
          districtName: "555",
          id: "AL01004",
          x: 0,
          y: 0
        }, {
          name: "猪肉",
          districtName: "666",
          id: "AL01005",
          x: -100,
          y: -100
        }, {
          name: "活草鱼",
          districtName: "777",
          id: "AM01002",
          x: 100,
          y: 100
        }]
    };
  }

  _flag = false;

  _setData(d) {
    let me = this;
    me._flag = true;
    me.setState({
      data: d
    });
  }

  randomCoordinate(n) {
    if (n > 0) {
      return Math.random() * 200;
    } else if (n < 0) {
      return Math.random() * 200 * (-1);
    } else {
      return 0;
    }
  }

  render() {
    let me = this;
    return (
      <div style={{width: '1125px', height: '800px', position: 'absolute', left: '180px', top: '114px'}}>
        <div style={{width: '306px', height: '267px', position: 'absolute', left: '0px', top: '123px'}}>
          <img src={bg} style={{width: '100%', height: '100%'}}/>
        </div>

        <div id={'box'} style={{width: '1125px', height: '800px'}}>
        </div>

      </div>
    )
  }

  componentDidUpdate() {
    let me = this;
    if (me._flag) {
      let data = me.state.data.slice();
      let matrixData = data.map((item, i) => {
        return {
          districtId: item.districtId,
          districtName: item.districtName,
          increase: item.increase,
          speed: item.speed,
          x: me.randomCoordinate(item.increase),
          y: me.randomCoordinate(item.speed)
        }
      });
      me.ballObj.show(matrixData)
      me._flag = false;
    }
  }

  componentDidMount() {
    let me = this;
    me.box = document.getElementById('box');

    me.ballObj = BallCharts(me.box, function (info) {

    });
    me.ballObj.init();

    me.ballObj.show(me.state.arr);
  }
}

export default QuadrantDiagram;
