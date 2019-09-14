import React from 'react';
import { PublicAngleData,countryData,weatherData,priceData,unmarketBarData} from './unmarketableData.js';
//引入接口文件
import * as api from '../api/api-unmarketableWarning';
//title 组件
import Title from '../../component/visual-team/title/Title';
import smallBorder from './small-border.png';
import bigBorder from './big-border.png';
//  下拉框
import Select from '../../component/web-team/select/Select';
import { getTimeData} from '../api/api-price-monitor';
import UnmarketBarline from '../../component/web-team/production-test/unmarketBars';
import PriceLine from '../../component/web-team/production-test/priceLine';
import PublicLine from '../../component/web-team/production-test/publicLine';
import WeatherLine from '../../component/web-team/production-test/weatherLine';




/**
 * 市场价格--价格监测
 */
class UnmarketableWarning extends React.Component {
    constructor(props) {
        super(props);
        const me = this;
        me.state = {
            KlineData:'12',
            PublicAngleData:PublicAngleData(),
            countryData:countryData(),
            weatherData:weatherData(),
            priceData:priceData(),
            unmarketBarData:unmarketBarData(),
            timeData:[],
            yValue:'',
            weatherType:'0',//气象角度温度湿度参数
            weatherName:'温度'//气象角度温度湿度
        }
        me.UnmarketableSupply = {
            position: 'absolute',
            top: '106px',
            left: '160px',
            width: '848px',
            height: '460px',
            background: `url(${bigBorder}) no-repeat center center`,
            backgroundSize: '100% 100%',
        };
        me.UnmarketablePrice = {
            position: 'absolute',
            top: '596px',
            left: '160px',
            width: '848px',
            height: '460px',
            background: `url(${bigBorder}) no-repeat center center`,
            backgroundSize: '100% 100%',
        };
        me.UnmarketablePublic = {
            position: 'absolute',
            top: '106px',
            right: '30px',
            width: '848px',
            height: '460px',
            background: `url(${smallBorder}) no-repeat center center`,
            backgroundSize: '100% 100%',
        };
        me.UnmarketableCountry = {
            position: 'absolute',
            top: '596px',
            right: '30px',
            width: '848px',
            height: '460px',
            background: `url(${smallBorder}) no-repeat center center`,
            backgroundSize: '100% 100%',
        };
        me.UnmarketableWeather= {
            position: 'absolute',
            top: '754px',
            right: '40px',
            width: '663px',
            height: '300px',
            background: `url(${smallBorder}) no-repeat center center`,
        };
    }
    _tokens = [];
    _clearTokens(){
        this._tokens.forEach(token => token.cancel());
        this._tokens = [];
    };
   //时间下拉初始化
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
            this.initSupply(res.data[0]);
        })
    };
    /* 气象角度  */
    _pullDownMesWeatherType = (a) =>{
        console.log(a)
        this.setState({
            weatherType:a.index*1,
            weatherName:a.name
        },()=>{
           this.initWeather(this.state.weatherName)
        })
    }
    /*气象角度*/
    initWeather = (legengFirst) =>{
        api.WeatherLine.send({
            jsonData:{
                "entityRelated" : {
                    "sensorType": this.state.weatherType //0为温度 1 为降雨量
                }
            }
        }).then((res) => {
            console.log(res)
            if (res.isSuccess) {
                let v1 = res.data.weatherThresholdList;
                let v2 = res.data.warningNum;
                let legendN = [legengFirst,'预警值'];
                /* res.data.weatherThresholdList.map((item, index)=>{
                  v2.push(res.data.warningNum)
                }) */
                this.setState({
                    weatherData: {
                        xAxisData: res.data.dateTime,
                        seriesData: [v1, v2],
                        legendData:legendN
                    },
                    txt:res.data.warningMsg == 1 ? '*可能存在气象原因导致的滞销' : ''
                },()=>console.log(this.state.weatherData))
            }else{
                this.setState({
                    weatherData: {
                        xAxisData: [],
                        seriesData: [[], []],
                        legendData:['','']
                    }
                })
            }
        })
    }
    //供求角度下拉
    _supplySelect= (a) => {
        this.initSupply(a.name)
    }
    //全国供求角度
    initSupply = (year) =>{
        let unmarketParams = {
            jsonData: {
                "entitySale":{
                    //"year": year || new Date().getFullYear()
                }
            }
        };
        api.unmarketBarline.send(unmarketParams).then((res) => {
            if (res.isSuccess) {
                console.log(res)
                let xData = [];
                let u1 = [];
                let u2 = [];
                let u3 = [];

                res.data.map((item, index) => {
                    //console.log(item),
                    xData.push(item.dateTime);
                    u1.push(item.saleAmount);
                    u2.push(item.productTotal);
                    u3.push(item.productTotal);
                });
                this.setState({
                    unmarketBarData: {
                        xAxisData: xData,
                        seriesData: [u1, u2, u3],
                    }
                })
            }else{
                this.setState({
                    unmarketBarData: {
                        xAxisData: [],
                        seriesData: [[], [], []],
                    }
                })
            }
        })

    }
    componentDidMount() {
        this.initSupply(); //供求角度
        this.initTimeSelect();//时间下拉
        this.initWeather('温度'); //气象角度
        /* 舆情角度*/
        let publicParams = {
            jsonData:{"entityRelated":{"themeId":"fsdf34t81h84158h148141g481g34gg3","timeTypeCode":"4"},"orderList":[{"columnName":"","isASC":true}],"page":{"pageIndex":1,"pageSize":10}}
        };
        api.UnmarketablePublic.send(publicParams).then((res) => {
            this.setState({
                PublicAngleData: {
                    xAxisData:res.data[0].times,
                    seriesData: [res.data[1].sentimentTypeCode1,res.data[1].sentimentTypeCode2,res.data[1].sentimentTypeCode3],
                    legendName:['正面传播','中立传播','负面传播'],
                    yName:"%"
                }
            })
        })
    /*国际角度*/
        api.countryLine.send({jsonData:{}}).then((res) => {
           // console.log(res)
            if (res.isSuccess) {
                let xData = [];
                let c1 = [];
                let c2 = [];
                res.data.map((item, index) => {
                    //console.log(item),
                    xData.push(item.month);
                    c1.push(item.saleAmount);
                    c2.push(item.importAmount);
                });
                //console.log(c2)
                this.setState({
                    countryData: {
                        xAxisData: xData,
                        seriesData: [c1, c2],
                        legendName: ['芒果进口量', '华坪芒果销售量'],
                        yName: "万吨",
                        yValue: function (value, index) {
                            var value;
                            value = value / 10000;
                            return value
                        }
                    }
                })
            }else{
                this.setState({
                    countryData:[]
                } )
            }
        })
      /*  全国价格角度*/
        api.priceLine.send({jsonData:{}}).then((res) => {
            if (res.isSuccess) {
                console.log(res)
                let xData = [];
                let p1 = [];
                let p2 = [];
                res.data.map((item, index) => {
                    //console.log(item),
                    xData.push(item.dateTime);
                    p1.push(item.fieldPrice);
                    p2.push(item.costPrice);
                });
                //console.log(p2)
                this.setState({
                    priceData: {
                        xAxisData: xData,
                        seriesData: [p1, p2],
                    }
                })
            }else{
                this.setState({
                    priceData:{
                        xAxisData: [],
                        seriesData: [[], []],
                    }
                })
            }
        })



    }

    render() {
        const me = this;
        return (
            <div   style={this.UnmarketableWarningClass}>
        {/*供求角度*/}
        <div style={me.UnmarketableSupply}>
           {/* <Select _pullDownMes={this._supplySelect} nameArr={this.state.timeData} width={110} style={{
                position: 'absolute',
                top: '18px',
               left: '860px',
                zIndex: 999999
            }}/> */}
            <Title content={'全国供求角度'}  top={'0.2rem'} left={'0.2rem'}/>
            <UnmarketBarline data={this.state.unmarketBarData}/>
        </div>
        {/*价格角度*/}
        <div style={me.UnmarketablePrice}>
            <Title content={'全国价格角度'}  top={'0.2rem'} left={'0.2rem'}/>
            <PriceLine data={this.state.priceData}/>
        </div>
        {/*舆情角度*/}
        <div style={me.UnmarketablePublic}>
            <Title content={'舆情角度---芒果滞销'}  top={'0.2rem'} left={'0.2rem'}/>
            <PublicLine data={this.state.PublicAngleData}/>
        </div>
            {/*国际角度*/}
         <div style={me.UnmarketableCountry}>
            <Title content={'国际角度'}  top={'0.2rem'} left={'0.2rem'}/>
            <PublicLine data={this.state.countryData}/>
        </div>
        {/*气象角度*/}
        {/* <div style={me.UnmarketableWeather}>
            <Title content={'气象角度'}  top={'0.2rem'} left={'0.2rem'}/>
            <div style={{position:"absolute",left:"1.5rem",top:"0.2rem",color:'#fff',fontSize:'0.16rem'}}
             dangerouslySetInnerHTML={{__html: this.state.txt}}></div>
            <Select _pullDownMes={this._pullDownMesWeatherType} nameArr={['温度','降雨量']} width={110}
									style={{
                                        position: 'absolute',
                                        top: '18px',
                                        right: '40px'
                                    }}/>
            <WeatherLine data={this.state.weatherData}/>
         </div> */}
        </div>
    )
    }
    componentWillUnmount() {
        this._clearTokens();
        this.flagError = false;
    }

}
export default UnmarketableWarning;
