import React from 'react';
import PriceSurveyClass from './priceSurvey.scss'
//  下拉框
import Select from '../../component/web-team/select/Select'
//小标题
import Title from '../../component/web-team/title/Title'
//切换按钮
import BtnTab from '../../component/web-team/btnTab/BtnTab'

import ThrButton from '../../component/visual-team/other/thr-button/ThrButton';

import WholesalePrice from '../../component/visual-team/other/wholesale-price/WholesalePrice';

//引入模拟数据文件
import {LinkData,MakeSalesData,RiseFallData,RiseFallData2,RiseFallBreedsBarData} from "./marketData";

import SurveyKline from '../../component/web-team/market-price/surveyKline'
import SurveyLink from '../../component/web-team/market-price/surveyLink'
import MakeSales from '../../component/web-team/market-price/surveyLink'
import RiseFall from '../../component/web-team/market-price/riseFallBar'
import RiseFallBreedsBar from '../../component/web-team/market-price/riseFallBreedsBar'

//引入接口文件
import * as api from '../api/api-price-monitor';
/**
 * 市场价格--价格监测
 */
class PriceSurvey extends React.Component {
	constructor(props) {
		super(props);
		const me = this;
		this.arr = [1, 2, 3];
		me.state = {
			id: 0,
			LinkData: LinkData,
			MakeSalesData: MakeSalesData,
			RiseFallData: RiseFallData,
            RiseFallData2: RiseFallData2,
			RiseFallBreedsBarData: RiseFallBreedsBarData,
			K8LineData:'',
			KlineCode:'530723',
			KlineName:'华坪',
			cityRise:'1',
			cityTime:'',
            cityTime2:'',
			cityType:'2',
            cityType2:'2',
			breedsType:'2',
			breedsTime:'',
			lastDate:'',
			SaleName:'北京',
			SaleCode:'110000',
			ProdName:'华坪',
			ProdCode:'530723',
			ProdData:[{name:'华坪县',value:'530723'}],
			timeData:[],
			timeFirst:'2018-06',
			areaFirst:'110000',
			AreaData:['全国'],
			AreaCode:['0'],
			SaleArea:[],
			AreaDataCode:[]
		}
	}
	_tokens = [];
	_clearTokens() {
		this._tokens.forEach(token => token.cancel());
		this._tokens = [];
	};

	//下面四个折线图初始化
	_initPage = () =>{
		//芒果品种涨跌幅监测
		this._RiseFallBreeds()
		//芒果城市涨跌幅TOP10
		this._initRiseFallCity()
        this._initRiseFallCity2()
		//产销价差
		this._initMakeSales()
		//各环节价差
		this._initLinkData()
	}
	//地区下拉选择
	/* _pullDownMesCheckArea = (a) =>{
		this.setState({
			KlineName: a.name,
			KlineCode: a.value
		},()=>{
			this._initKlineDataFn()
		})

	} */
	//右侧tab切换
	fetchInfo(i) {
		this.setState({
			id: i
		},()=>{
			//K线图的渲染方法
			this._initKlineDataFn()
		})

	}
	 //  时间下拉初始化
	  _initSelect = () => {
	    let getTimeDataParams = {
	      jsonData: {
	        "entityRelated": {
	          "viewName": "month",
	          "hasCurrent": true,
	          "pastNum": "5",
	          "afterNum": "0",
	          "isASC":false
	        }
	      }
	    }
	    api.getTimeData.send(getTimeDataParams).then((res) => {
	      //console.log(res.data[0])
	      this.setState({
	        timeData: res.data,
	        timeFirst: res.data[0]
	      },()=>{
	      	this._initPage()
	       })
	    })

	}
	// 省份下拉
	_initSelectArea = () => {
	    let getAreaDataParams = {
	      jsonData: {
	      	"entityRelated":{
	      		"category":"1,2,3,4"
	      	}
	      }
	    }
	    api.getAreaData.send(getAreaDataParams).then((res) => {
	      //console.log(res)
	      let SaleArea = [] ,areaValue=[];
	      res.data.map((item,value)=>{
	      	let str = item.name;
	      	(str == '黑龙江省' || str == '内蒙古自治区') ? str=str.slice(0,3) : str=str.slice(0,2)
	      	SaleArea.push(str),
	      	areaValue.push(item.code)
	      })
	      this.setState({
	        SaleArea: SaleArea,
	        AreaDataCode: areaValue,
	        areaFirst: res.data[0].code
	      })
	    })
	}
	/*//品种涨跌幅监测下拉
	_pullDownMesBreedsType = (a) =>{
		//console.log(a)
		this.setState({
			breedsType:a.index*1+1
		},()=>{
			this._initPage()
		})

	}*/
	//品种时间下拉
	_pullDownMesBreedsDate = (a) =>{
		this.setState({
			breedsTime:a.name
		},()=>{
			this._initPage()
		})

	}
	//城市涨跌幅时间下拉
	_pullDownMesCityDate = (a) =>{
		//console.log(a)
		this.setState({
			cityTime:a.name
		},()=>{
			this._initPage()
		})
	}
    _pullDownMesCityDate2 = (a) =>{
        //console.log(a)
        this.setState({
            cityTime2:a.name
        },()=>{
            this._initPage()
        })
    }
	//城市涨跌幅区域下拉
	_pullDownMesCityType = (a) =>{
		this.setState({
			cityType:a.index*1+2
		},()=>{
			this._initPage()
		})
	}
    _pullDownMesCityType2 = (a) =>{
        this.setState({
            cityType2:a.index*1+2
        },()=>{
            this._initPage()
        })

    }
	//城市涨跌幅涨跌下拉
	/*_pullDownMesZD =(a)=> {
		//console.log(a)
		this.setState({
			cityRise:a.index*1+1
		},()=>{
			this._initPage()
		})

	}*/
	//产销价差产地
	_pullDownMesProd = (a) =>{
		//console.log(a)
		this.setState({
			ProdName:a.name,
			ProdCode:a.value
		},()=>{
			this._initPage()
		})
	}
	//产销价差销地
	_pullDownMesSale = (a) =>{
		//console.log(a)
		this.setState({
			SaleName:a.name,
			SaleCode:a.value
		},()=>{
			this._initPage()
		})
	}
		//K线图封装
	_initKlineDataFn = () => {
		let kLineParams = {
			jsonData: {
				"entityRelated":{
					"queryType":(this.state.id)*1+2,
					"regionCode":(this.state.id)*1 == 1 ? this.state.KlineCode : '530723',
					"regionName":this.state.KlineName
				},
				"orderList":[{
					"columnName":"sale_amount",
					"isASC":true
				}],
				"page":{
					"pageIndex":1,
					"pageSize":100
				}
			}
		}
		api.getPriceKline.send(kLineParams).then((res) => {
			//console.log(res)
			if (res.isSuccess){
				let arr = res.data[0].Kdata.time;
				let h = res.data[0].Kdata.time.length;
				var data=JSON.stringify(res.data)
				this.setState({K8LineData:data,lastDate:arr[h-1]})
			}else{
				this.setState({K8LineData:'',lastDate:'暂无更新'})
			}
		})
	}
	//芒果品种涨幅监测
	_RiseFallBreeds = ()=>{
		let RiseFallBreedsParams = {
			jsonData: {
				"entityRelated": {
					"queryType": this.state.breedsType,
					"queryTime": this.state.breedsTime ? this.state.breedsTime :this.state.timeFirst
				},
				"orderList": [{
					"columnName": "sale_amount",
					"isASC": true
				}],
				"page": {
					"pageIndex": 1,
					"pageSize": 10
				}
			}

		}
		api.getRiseFallBreeds.send(RiseFallBreedsParams).then((res) => {
			//console.log(res)
			if (res.isSuccess){
				let xAxisD = [],
					seriesD = [];
				res.data.map((item, index) => {
					xAxisD.push(item.strains_text);
					seriesD.push(item.chg);
				})
				this.setState({
					RiseFallBreedsBarData: {
						xAxisData: xAxisD,
						seriesData: seriesD
					}
				})
			}else{
				this.setState({
					RiseFallBreedsBarData: []
				})
			}
		})
	}

	//芒果城市涨跌幅TOP10
	_initRiseFallCity = ()=>{
		let RiseFallParams = {
			jsonData: {
				"entityRelated": {
					"queryPattern": 1,
					"queryType": this.state.cityType,
					"queryTime": this.state.cityTime ? this.state.cityTime :this.state.timeFirst
				},
				"orderList":[{
					"columnName":"sale_amount",
					"isASC":true
				}],
				"page": {
					"pageIndex": 1,
					"pageSize": 5
				}
			}
		}

		api.getRiseFallData.send(RiseFallParams).then((res) => {
			//console.log(res)
			if (res.isSuccess){
				let xAxisD = [],
					seriesD = [],
					cityParam = [];
				cityParam.push(res.data[0].queryType,res.data[0].queryTime,res.data[0].queryPattern);
				res.data.map((item, index) => {
					xAxisD.push(item.region_name);
					seriesD.push(item.chg);
				})
				this.setState({
					RiseFallData: {
						xAxisData: xAxisD,
						seriesData: seriesD,
						cityParam:cityParam
					}
				})
			}else{
				this.setState({
					RiseFallData: {
						xAxisData: [],
						seriesData: [],
						cityParam:[]
					}
				})
			}
		})

	}
    _initRiseFallCity2 = ()=>{
        let RiseFallParams2 = {
            jsonData: {
                "entityRelated": {
                    "queryPattern": 2,
                    "queryType": this.state.cityType2,
                    "queryTime": this.state.cityTime2 ? this.state.cityTime2 :this.state.timeFirst
                },
                "orderList":[{
                    "columnName":"sale_amount",
                    "isASC":true
                }],
                "page": {
                    "pageIndex": 1,
                    "pageSize": 5
                }
            }
        }
        api.getRiseFallData.send(RiseFallParams2).then((res) => {
            //console.log(res)
            if (res.isSuccess){
                let xAxisD = [],
                    seriesD = [],
                    cityParam = [];
                cityParam.push(res.data[0].queryType,res.data[0].queryTime,res.data[0].queryPattern);
                res.data.map((item, index) => {
                    xAxisD.push(item.region_name);
                    seriesD.push(item.chg);
                })
                this.setState({
                    RiseFallData2: {
                        xAxisData: xAxisD,
                        seriesData: seriesD,
                        cityParam:cityParam
                    }
                })
            }else{
                this.setState({
                    RiseFallData2: {
                        xAxisData: [],
                        seriesData: [],
                        cityParam:[]
                    }
                })
            }
        })
    }
	//产销价差
	_initMakeSales = ()=>{
		let MakeSalesParams = {
			jsonData: {
				"entityRelated": {
					"prodAreaCode": this.state.ProdCode,
					"prodAreaName": this.state.ProdName,
					"saleAreaCode": this.state.SaleCode ? this.state.SaleCode : this.state.areaFirst,
					"saleAreaName": this.state.SaleName
				},
				"orderList": [{
					"columnName": "sale_amount",
					"isASC": true
				}],
				"page": {
					"pageIndex": 1,
					"pageSize": 10
				}
			}
		}
		api.getMakeSalesData.send(MakeSalesParams).then((res) => {
			//console.log(res)
			if (res.isSuccess){
				let xAxisD = [],
					gap = [],
					prodPrice = [],
					salePrice = [];
				let saleArea = this.state.SaleName ? this.state.SaleName : '北京',
					prodArea = this.state.ProdName ? this.state.ProdName : '华坪',
					legendD = '价差('+ saleArea + '-' + prodArea + ')';
				res.data.map((item, index) => {
					//console.log(item)
					xAxisD.push(item.times);
					gap.push(item.gap);
					prodPrice.push(item.prod_price);
					salePrice.push(item.sale_price);
				})
				this.setState({
					MakeSalesData: {
						legendData: [legendD, this.state.ProdName, this.state.SaleName],
						xAxisData: xAxisD,
						seriesData: [gap, prodPrice, salePrice]
					}
				})
			}else{
				this.setState({
						legendData: ['','', ''],
						xAxisData: [],
						seriesData: [[], []]
				})
			}
		})
	}
	//各环节价差
	_initLinkData = ()=>{
		let LinkDataParams = {
			jsonData: {
				"entityRelated": {},
				"orderList": [{
					"columnName": "sale_amount",
					"isASC": true
				}],
				"page": {
					"pageIndex": 1,
					"pageSize": 10
				}
			}
		}
		api.getLinkDataData.send(LinkDataParams).then((res) => {
			console.log(res)
			if (res.isSuccess){
				this.setState({
					LinkData: {
						legendData: [ '田头价', '批发价', '电商价'],
						xAxisData: res.data[0].time,
						seriesData: [res.data[1].shougou,  res.data[2].pifa, res.data[3].lingshou]
					}
				})
			}else{
				this.setState({
						legendData: ['', ''],
						xAxisData: [],
						seriesData: [ [], []]
				})
			}
		})
	}
	componentDidMount() {
		//K线的数据
		// this._initKlineDataFn()
		//初始化下面四个图表
		this._initPage()
		//  时间下拉
    	this._initSelect()
    	// 省份下拉
    	this._initSelectArea()
	}
	render() {
		const me = this;
		let list=''
		this.state.K8LineData.length>0?list=JSON.parse(this.state.K8LineData):[]
		//console.log(this.state.RiseFallData)
		return (
			<div>
				<ThrButton left={'1690px'} top={'117px'} fetchInfo={this.fetchInfo.bind(this)}/>
				
				<WholesalePrice display={this.state.id===0?'block':'none'}/>
				<div className="bottomEcharts">
					<div className="echarts-item">
						<div className="chart-title-wrap">
							<Title  content={'芒果品种涨跌幅度监测'} top={'0.1rem'}/>
						</div>
						<div className="chart-select">
							<Select _pullDownMes={this._pullDownMesBreedsType} nameArr={['批发价','电商价']} width={110}
							style={{
								position: 'absolute',
								top: '0',
								left: '20px'
							}}/>
							<Select _pullDownMes={this._pullDownMesBreedsDate} nameArr={this.state.timeData} width={110}
							style={{
								position: 'absolute',
								top: '0',
								left: '140px'
							}}/>
						</div>
						<div className="echarts-wrap">
							<RiseFallBreedsBar data={me.state.RiseFallBreedsBarData}/>
						</div>
					</div>

					<div className="echarts-item">
						<div className="chart-title-wrap">
							<Title  content={'城市涨幅度TOP5'} top={'0.1rem'}/>
						</div>
						<div className="chart-select">
							<Select _pullDownMes={this._pullDownMesCityType} nameArr={['批发价','电商价']} width={110}
							style={{
								position: 'absolute',
								top: '0',
								left: '20px'
							}}/>
							<Select _pullDownMes={this._pullDownMesCityDate} nameArr={this.state.timeData} width={110}
							style={{
								position: 'absolute',
								top: '0',
								left: '140px'
							}}/>
						</div>
						<div className="echarts-wrap">
							<RiseFall data={this.state.RiseFallData}/>
						</div>
					</div>
					<div className="echarts-item">
						<div className="chart-title-wrap">
							<Title  content={'城市跌幅度TOP5'} top={'0.1rem'}/>
						</div>
						<div className="chart-select">
							<Select _pullDownMes={this._pullDownMesCityType2} nameArr={['批发价','电商价']} width={110}
									style={{
                                        position: 'absolute',
                                        top: '0',
                                        left: '20px'
                                    }}/>
							<Select _pullDownMes={this._pullDownMesCityDate2} nameArr={this.state.timeData} width={110}
									style={{
                                        position: 'absolute',
                                        top: '0',
                                        left: '140px'
                                    }}/>
						</div>
						<div className="echarts-wrap">
							<RiseFall data={this.state.RiseFallData2}/>
						</div>
					</div>
{/*					<div className="echarts-item">
						<div className="chart-title-wrap">
							<Title  content={'产销价差'} top={'0.1rem'}/>
							<span className="subhead">销地{this.state.saleA}比产地{this.state.prodA}价格大约高6-10元/公斤</span>
						</div>
						<div className="chart-select">
							<p className="select-type1">产地：</p>
							<Select _pullDownMes={this._pullDownMesProd} nameArr={['华坪']} valueArr={['530723']} width={110}
							style={{
								position: 'absolute',
								top: '0',
								left: '70px'
							}}/>
							<p className="select-type2">销地：</p>
							<Select _pullDownMes={this._pullDownMesSale} nameArr={this.state.SaleArea} valueArr={this.state.AreaDataCode} width={110}
							style={{
								position: 'absolute',
								top: '0',
								left: '280px'
							}}/>
						</div>
						<div className="echarts-wrap">
							<MakeSales data={me.state.MakeSalesData}/>
						</div>
					</div>*/}
					<div className="echarts-item">
						<div className="chart-title-wrap">
							<Title  content={'各环节价差'} top={'0.1rem'}/>
						</div>
						<div className="echarts-wrap">
							<SurveyLink data={me.state.LinkData}/>
						</div>
					</div>
				</div>
				<div style={{display:this.state.id===0?'none':'block'}} className="PriceSurveyClass">
					<div className="top">
						<div className="top-left"  style={{display:this.state.id===0?'none':'block'}}>
							{/* <Select _pullDownMes={this._pullDownMesCheckArea} nameArr={this.state.AreaData} valueArr={this.state.AreaCode} width={110}
							style={{
								position: 'absolute',
								top: '25px',
								left: '0'
							}}/> */}
							<div className="refresh-time">更新时间：<span>{this.state.lastDate}</span></div>
						</div>
					</div>
					<div className="bottomKline">
						{
							list?list.map(function (item,index) {
                			return (
		                        	<div key={index} className="echarts-item Kecharts">
			                            <div className="chart-title-wrap">
											<Title content={item.name[0]} top={'0.1rem'}/>
										</div>
										<div className="echarts-wrap">
											<SurveyKline data={item.Kdata}/>
										</div>
									</div>
		                        )
				             }):''
						}
					</div>
				</div>
			</div>
		)
	}
	componentWillUnmount() {
	    this._clearTokens();
	}
}

export default PriceSurvey;