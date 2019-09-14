import React, { Component } from 'react';
import Header from './page/component/header';
import Footer from './page/component/footer';
import { message } from 'antd';
import { HashRouter as Router,Route,Switch,Redirect } from 'react-router-dom'
import PropTypes from 'prop-types';
//  登录页
import LoginIn from './page/login';
import Home from './page/home/home';
import ForData from './page/forData/router/router';
import IndexReport from './page/indexReport/router/router';
import ViewData from './page/viewData/router/router';
import PublicData from './page/publicData/router/router';
import ThingsInternet from './page/thingsInternet/router/router';
// import DataResource from './page/dataResource/router/router';
// import { LeftBar } from './page/component/leftBar';
import 'antd/dist/antd.css';
import './App.css';


class App extends Component {
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
    goLogin=()=>{
        // this.props.history.push("/login")
        return <Redirect from={`*`} to="/login"/>
    }
    render(){

        return (
        <Router>
            <div>

                <Header></Header>
                <Switch>
                    <Route exact path="/login" component={LoginIn} />
                    <Route path={`/home`} component={ Home } />
                    <Route path={`/forData`} component={ ForData } />
                    <Route path={`/indexReport`} component={ IndexReport } />
                    <Route path={`/viewData`} component={ ViewData } />
                    <Route path={`/publicData`} component={ PublicData } />
                    <Route path={`/thingsInternet`} component={ ThingsInternet } />
                    {/* <Route path={`/dataResource`} component={ DataResource } /> */}
                    <Redirect from={`/`} to="/login"/>
                    <Route to="/login" />
                </Switch>
                <Footer></Footer>
            </div>
        </Router>
        );
  }
}


export default App;
