import React from 'react';
import img1 from './img1.png';
import img2 from './img2.png';
import img3 from './img3.png';
import img4 from './img4.png';

export default class ManggoProductPlace extends React.Component{
  constructor(props){
    super(props);
    this.boxStyle = {
      color:'#fff',
      fontSize:'16px',
      width:'100%',
      position:'absolute',
      top:'52px'
    };
    this.spanStyle = {
      paddingRight:'10px',display:'block',float:'left',height:'100%',

    };
    this.imgArr = [img1,img2,img3,img4];
    this.state = {
      data:[]
    }
  }

  setData(d){
    this.setState({
      data:d
    })
  }

  addList(){
      //暂时写成假数据
      let data =[
        {"name":"芒果生态长廊","val":5},
        {"name":"万亩绿色晚熟芒果生产基地","val":1},
        {"name":"千亩优质晚熟芒果示范基地","val":6},
        {"name":"百亩科技示范园","val":22}
      ]
    return data.map((item,i)=>{
      return (
        <li key={i} style={{fontSize:'16px',background:i%2===0?'linear-gradient(to right, rgba(53,100,235,0), rgba(53,100,235,1), rgba(53,100,235,0))':'',height:i%2===0?'48px':'60px',lineHeight:i%2===0?'48px':'60px'}}>
          <span style={{float:'left',width:'135px',background:`url(${this.imgArr[i]}) no-repeat 45px center`,display:'block',height:'100%'}}></span>
          <span style={{...this.spanStyle,fontSize:'30px'}}>{item.val}</span>
          <span style={{...this.spanStyle}}>{i<1?'条':'个'}</span>
          <span style={{...this.spanStyle,color:'#46ebff'}}>{item.name}</span>
        </li>
      )
    })
  }

  render(){
    if(this.state.data){
      return(
        <div>
          <ul style={this.boxStyle}>
            {
              this.addList()
            }
          </ul>
        </div>
      )
    }else {
      return(
        <div></div>
      )
    }
  }
}
