import React, {Component} from 'react';
import ResizeManager from './component/visual-team/common/ResizeManager';
import './App.css';
import 'antd/dist/antd.css';
// nav
import Navigation from './component/visual-team/navigation/Nav';

import {Provider} from 'react-redux';
import store from './store/Store';
import PropTypes from 'prop-types'
import {HashRouter, Route, Redirect} from 'react-router-dom';
//产业链全景图
import IndustryChain from './page/industry-chain/IndustryChain';
//种植生产--生产现状
import ProductionActuality from './page/planting/ProductionActuality';
//种植生产--物联网监控
import InternetThing from './page/planting/InternetThing';
//种植生产--农业灾害评估
import AgriculturalDisasters from './page/planting/AgriculturalDisasters';
//种植生产--不同主产区对比
import ProductionComparison from './page/planting/ProductionComparison';
//精深加工
import DeepProcessing from './page/deep-processing/DeepProcessing';
//市场消费
import MarketSale from './page/market-sale/MarketSale';
//进出口贸易
import ImportExport from './page/import-export/ImportExport';
//市场价格--价格监测
import PriceSurvey from './page/market-price/PriceSurvey';
//市场价格--波动规律
import WaveLaw from './page/market-price/WaveLaw';
//市场价格--价格预测
import PriceForecast from './page/market-price/PriceForecast';
//产销监测--供需分析
import SupplyAnalysis from './page/production-test/SupplyAnalysis';
//产销监测--滞销预警
import UnmarketableWarning from './page/production-test/UnmarketableWarning';
//产销监测--招商引资
import AttractInvestment from './page/production-test/AttractInvestment';
//质量安全--质量监管
import QualitySupervision from './page/safety-quality/QualitySupervision';
//质量安全--舆情监测
import TalkMonitoring from './page/safety-quality/TalkMonitoring';
import LoginPage from './page/login/login'

/**
 * 渲染逻辑
 */
class App extends Component {
  constructor(props) {
    super(props);
    const me = this;
  }
  componentDidMount() {
    let tt = localStorage.getItem('token');
    var jz;
    if (tt) {
        jz = new Date(JSON.parse(localStorage.getItem('token')).rdeadTime).getTime();
    }
    let tem = new Date().getTime();
    if (jz < tem) {
        return (<Redirect to="/login" />)
    }
  }
  render() {
    const width = 1920;
    const height = 1080;
    const mode = window.resizeManagerMode || ResizeManager.MODE_DEBUG;
    return (
      <Provider store={store}>
        <HashRouter>
          <div className="app" style={{width, height}}>
            <ResizeManager fullWidth={width} fullHeight={height} mode={mode}/>
                <Navigation/>
            <Route exact path='/' component={LoginPage} />
            <Route path='/IndustryChain' render={()=>{
                    if (localStorage.token){
                        return <IndustryChain/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/productionActuality' render={()=>{
                    if (localStorage.token){
                        return <ProductionActuality/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/internetThing' render={()=>{
                    if (localStorage.token){
                        return <InternetThing/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/agriculturalDisasters' render={()=>{
                    if (localStorage.token){
                        return <AgriculturalDisasters/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/productionComparison' render={()=>{
                    if (localStorage.token){
                        return <ProductionComparison/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/deepProcessing' render={()=>{
                    if (localStorage.token){
                        return <DeepProcessing/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/marketSale' render={()=>{
                    if (localStorage.token){
                        return <MarketSale/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/importExport' render={()=>{
                    if (localStorage.token){
                        return <ImportExport/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/priceSurvey' render={()=>{
                    if (localStorage.token){
                        return <PriceSurvey/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/waveLaw' render={()=>{
                    if (localStorage.token){
                        return <WaveLaw/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/priceForecast' render={()=>{
                    if (localStorage.token){
                        return <PriceForecast/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/supplyAnalysis' render={()=>{
                    if (localStorage.token){
                        return <SupplyAnalysis/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/unmarketableWarning' render={()=>{
                    if (localStorage.token){
                        return <UnmarketableWarning/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/attractInvestment' render={()=>{
                    if (localStorage.token){
                        return <AttractInvestment/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/qualitySupervision' render={()=>{
                    if (localStorage.token){
                        return <QualitySupervision/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
            <Route path='/talkMonitoring' render={()=>{
                    if (localStorage.token){
                        return <TalkMonitoring/>
                    }else{
                        return <Redirect to="/"/>
                    }
                }} />
          </div>
        </HashRouter>
      </Provider>
    );
  }
}

export {App, store};
