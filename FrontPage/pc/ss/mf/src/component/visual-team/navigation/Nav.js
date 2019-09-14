import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import PropTypes from "prop-types";
import './nav.css';
import navNormal1 from './img/nav-normal1.png';
import navNormal2 from './img/nav-normal2.png';
import navNormal3 from './img/nav-normal3.png';
import navNormal4 from './img/nav-normal4.png';
import navNormal5 from './img/nav-normal5.png';
import navNormal6 from './img/nav-normal6.png';
import navNormal7 from './img/nav-normal7.png';
import navNormal8 from './img/nav-normal8.png';

import navClick1 from './img/nav-click1.png';
import navClick2 from './img/nav-click2.png';
import navClick3 from './img/nav-click3.png';
import navClick4 from './img/nav-click4.png';
import navClick5 from './img/nav-click5.png';
import navClick6 from './img/nav-click6.png';
import navClick7 from './img/nav-click7.png';
import navClick8 from './img/nav-click8.png';

import quit from './img/quit.png';
import logo from './img/logo-icon.png';
//引入登录接口
import * as api from '../../../page/api/api-login';
import { message } from 'antd';
const navList = [
  {
    name: '产业链全景图',
    path: '/IndustryChain',
    index: 0
  },
  {
    name: '种植生产',
    path: '/productionActuality',
    children: [
      {
        name: '生产现状',
        path: '/productionActuality'
      },
      {
        name: '物联网监控',
        path: '/internetThing'
      },
      {
        name: '农业灾害评估',
        path: '/agriculturalDisasters'
      },
      {
        name: '不同主产区对比',
        path: '/productionComparison'
      },
    ],
    index: 1
  },
  {
    name: '精深加工',
    path: '/deepProcessing',
    index: 2
  },
  {
    name: '市场消费',
    path: '/marketSale',
    index: 3
  },
  {
    name: '进出口贸易',
    path: '/importExport',
    index: 4
  },
  {
    name: '市场价格',
    path: '/priceSurvey',
    children: [
      {
        name: '价格监测',
        path: '/priceSurvey'
      },
      {
        name: '波动规律',
        path: '/waveLaw'
      },
      {
        name: '价格预测',
        path: '/priceForecast'
      }
    ],
    index: 5
  },
  {
    name: '产销监测',
    path: '/supplyAnalysis',
    children: [
      {
        name: '产销分析',
        path: '/supplyAnalysis'
      },
      {
        name: '滞销预警',
        path: '/unmarketableWarning'
      },
      {
        name: '招商引资',
        path: '/attractInvestment'
      }
    ],
    index: 6
  },
  {
    name: '质量安全',
    path: '/qualitySupervision',
    children: [
      {
        name: '质量监管',
        path: '/qualitySupervision'
      },
      {
        name: '舆情监测',
        path: '/talkMonitoring'
      }
    ],
    index: 7
  }
];

class Nav extends Component {
  static contextTypes = {
    router: PropTypes.object.isRequired,
}
  constructor(props) {
    super(props);
    //console.error(props);
    this.state = {
      show: true,
      count: 0,
      index: '',
      userName:"",
      passWord:"",
    };

    this.normalImg = [navNormal1, navNormal2, navNormal3, navNormal4, navNormal5, navNormal6, navNormal7, navNormal8];
    this.clickImg = [navClick1, navClick2, navClick3, navClick4, navClick5, navClick6, navClick7, navClick8];
    this.listName = [
      '产业链全景图', '生产现状', '物联网监控', '农业灾害评估', '不同主产区对比', '精深加工', '市场消费', '进出口贸易', '价格监测',
      '波动规律', '价格预测', '产销分析', '滞销预警', '招商引资', '质量监管', '舆情监测'
    ];
    this.urlList = [
      '/IndustryChain', '/productionActuality', '/internetThing', '/agriculturalDisasters', '/productionComparison', '/deepProcessing', '/marketSale',
      '/importExport', '/priceSurvey', '/waveLaw', '/priceForecast', '/supplyAnalysis', '/unmarketableWarning', '/attractInvestment',
      '/qualitySupervision', '/talkMonitoring'
    ];
    this.triangle = {
      display: 'inline-block',
      width: '10px',
      height: '10px',
      backgroundColor: '#181c6c',
      position: 'absolute',
      left: '-5px',
      marginTop: '-5px',
      top: '50%',
      transform: 'rotate(45deg)'
    }
   this.LogOut = this.LogOut.bind(this);
    // this.handle = this.handle.bind(this);
  }

  _listClick(i) {
    this.setState({
      count: i
    });
    localStorage.name = i;
  }
  _listMouseOver=(i)=> {
   // console.log(i)
    if(Number(localStorage.name) !== i){
      let refI = `mouseOver${i}`
      let refD = `disNone${i}`
      let refH = `height${i}`
      this.refs[refI].setAttribute('src',this.clickImg[i])
      this.refs[refD].style.display = 'block'
      this.refs[refH].style.height = '132px'
    }

  }
  _listMouseOut=(i)=> {
    if(Number(localStorage.name) !== i){
      let refI = `mouseOver${i}`
      let refD = `disNone${i}`
      let refH = `height${i}`
      this.refs[refI].setAttribute('src',this.normalImg[i])
      this.refs[refD].style.display = 'none'
      this.refs[refH].style.height = '77px'
    }
  }
  _childrenList(i, e) {

    this.setState({
      index: i
    });
    // e.preventDefault();
    // e.stopPropagation();
  }

  createList(children, i) {
    let me = this;
    if (!children) return;
    return <ul style={{
      padding: '0 10px',
      position: 'absolute',
      left: '70px',
      top: '-10px',
      backgroundColor: '#181c6c',
      borderRadius: '5px',
    }} className={'second-list'}>
      <span style={me.triangle}></span>
      {
        children.map((v, i) => {
          return (
            <Link to={v.path} key={i} style={{textDecoration:'none'}}>
              <li style={{
                height: '30px',
                lineHeight: '30px',
                textDecoration:'none'
                // color: me.state.index === i ? '#fff' : '#46b9ff'
              }}
                  onClick={me._childrenList.bind(me, i)}>
                <span className={'list-circle'}></span>
                {v.name}
              </li>
            </Link>
          )
        })
      }
    </ul>

  }
  componentWillMount(){
      //获取本地存储的用户名密码
    let password = localStorage.getItem("passWord");
    let userName = localStorage.getItem("userName");
    this.setState({
        userName: userName,
        password: password
      });
    let hash = window.location.hash;
    hash = hash.split('/');
    let lName =  hash[hash.length-1];
    if(lName==="IndustryChain"){
      localStorage.name = 0;
    }
  }
  //点击退出
  LogOut=(e)=>{
    api.LogOut.send().then((res) => {
        console.log(res.isSuccessCode)
        //debugger
        if (res.isSuccessCode == 0) {
          localStorage.removeItem("token");
          this.context.router.history.push("/");
        } else {
          message.info('退出失败');
        }
    });
    localStorage.setItem('name', 0);
  }
  render() {
    return (
      <div style={{width: '110px', position: 'absolute', left: '20px', top: '230px', zIndex: 1000}}>
        <div style={{position: 'absolute', left: '0px', top: '-200px', width: '640px', height: '55px'}}>
          <span style={{
            float: 'left',
            width: '65px',
            height: '55px',
            background: `url(${logo}) no-repeat center 45%`,
            backgroundSize: '120px 120px'
          }}> </span>
          <span style={{
            float: 'right',
            width: '550px',
            height: '55px',
            lineHeight: '55px',
            color: '#fff',
            fontSize: '40px',
            fontWeight: 700,
            borderLeft: '1px solid #fff',
            paddingLeft: '15px'
          }}>芒果产业监测预警大数据平台</span>
        </div>
        <b onClick={this.LogOut}>
          <span style={{
            position: 'absolute',
            left: '1840px',
            top: '-200px',
            width: '32px',
            height: '32px',
            display: 'inline-block'
          }}>
          <img src={quit} style={{width: '100%', height: '100%'}}/>
          </span>
        </b>
        <ul>
          {
            navList.map((v, i) => {
              return (
                <Link to={v.path} key={i}>
                  <li ref={`height${i}`}
                  style={{
                    fontSize: '16px',
                    color: '#fff',
                    height: Number(localStorage.name) === i ? '132px' : '77px',
                    position: 'relative'
                  }}
                  onClick={this._listClick.bind(this, i)}
                  onMouseOver={this._listMouseOver.bind(undefined,i)}
                  onMouseOut={this._listMouseOut.bind(undefined,i)}
                  className={'first-list'}>
                    <p style={{width: '100%', textAlign: 'center'}}>
                      <img ref={`mouseOver${i}`} src={ Number(localStorage.name) === i ? this.clickImg[i] : this.normalImg[i]}/>
                    </p>
                    <p ref={`disNone${i}`}
                    style={{
                      width: '100%',
                      textAlign: 'center',
                      height: '40px',
                      lineHeight: '40px',
                      textDecoration:'none',
                      display:  Number(localStorage.name) === i ? 'block' : 'none'
                    }}>{v.name}</p>

                    {
                      this.createList(v.children, i)
                    }
                  </li>
                </Link>
              )
            })
          }
        </ul>
      </div>
    )
  }
}

export default Nav;
