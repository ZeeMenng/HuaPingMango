import React from 'react';
import './plant.css';
import plant1 from './plant1.png';
import plant2 from './plant2.png';
import plant3 from './plant3.png';
import plant4 from './plant4.png';
import plant5 from './plant5.png';
import plant6 from './plant6.png';
import down from './arrow-down.gif';
import up from './arrow-up.gif';
import Word from '../other/Word';
const data = [
  {
    img: plant1,
    area: '种植面积',
    unit: '万亩'
  },
  {
    img: plant2,
    area: '挂果面积',
    unit: '万亩'
  },
  {
    img: plant3,
    area: '有机认证面积',
    unit: '万亩'
  },
  {
    img: plant4,
    area: '绿色认证面积',
    unit: '万亩'
  },
  {
    img: plant5,
    area: '总产量',
    unit: '万吨'
  },
  {
    img: plant6,
    area: '种植户',
    unit: ' 户'
  }
];
class MangoPlantState extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      '种植面积':10,
      '挂果面积':11,
      '有机认证面积':12,
      '绿色认证面积':13,
      '总产量':14,
      '种植户':15,

      '种植面积%':1,
      '挂果面积%':2,
      '有机认证面积%':3,
      '绿色认证面积%':4,
      '总产量%':5,
      '种植户%':6,
    }
  }
  setData(d){
    //console.log(d)
    this.setState({
      '种植面积':(d.info.areaSum / 6666700).toFixed(2),
      '挂果面积':(d.info.fruitAreaSum / 6666700).toFixed(2),
      '有机认证面积':(d.info.organicAreaSum / 6666700).toFixed(2),
      '绿色认证面积':(d.info.greenAreaSum / 6666700).toFixed(2),
      '总产量':(d.info.productSum / 10000000).toFixed(2),
      '种植户':d.info.peasantCount,

      '种植面积%':d.info.areaSumProportion,
      '挂果面积%':d.info.fruitAreaSumProportion,
      '有机认证面积%':d.info.organicAreaSumProportion,
      '绿色认证面积%':d.info.greenAreaSumProportion,
      '总产量%':d.info.productSumProportion,
      '种植户%':d.info.peasantCountProportion,
    })
  }
  render(){
    function ifShow(n){
        if(n<0){
          return down;
        }else if(n>0){
          return up;
        }else{
            return '';
        }
    }
    return(
      <div className={'plant-state'}>
        {
          data.map((v,i)=>{
            return(
              <ul className={'list'} key={'zll'+i} style={{
                borderBottom: i === 5? '' : '1px solid #001541'
              }}>
                <li className={'img'} style={{
                  background: `url(${ v.img }) no-repeat right top`,
                  backgroundSize: '54px 54px'
                }}>
                </li>
                <li className={'value'}>
                  <p>{v.area}</p>
                  <p>
                    <span style={{fontSize:'34px'}}>{this.state[v.area]}</span>
                    <span>{v.unit}</span>
                  </p>
                </li>
                <li className={'percent'}>
                  <p>同比</p>
                  <p>
                    <Word fontSize={'35px'} num={this.state[v.area+'%']}/>
                    <span className={'distance'}></span>
                    <Word fontSize={'20px'} num={'%'}/>
                    <img src={up} className={'arrow'}/>
                  </p>
                </li>
              </ul>
            )
          })
        }
      </div>
    )
  }
}
export default MangoPlantState;