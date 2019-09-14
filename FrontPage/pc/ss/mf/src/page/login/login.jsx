import React from 'react';
import './login.scss';
import { Button, Icon, Input, message, Checkbox } from 'antd';
import * as api from '../api/api-login';
export default class ImportExport extends React.Component {
    constructor(props) {
        super(props);
        const me = this;
        this.state = {
            checked: false
        }
    };
    //记住密码
    onChange = (e) => {
        this.setState({
            checked: !this.state.checked
        });
    };
    //  input监听
    inputHandle = (e) => {
        let type = e.target.getAttribute('data-type')
        this.setState({
            [type]: e.target.value
        }, () => {
            //  console.log(this.state[type])
        })
    }
    render () {
        const { userName, passWord, checked } = this.state;
        const me = this;
        //回车时间
        function entreFn (e) {
            if (e.keyCode === 13) {
                submitBtn()
            }
        }
        //点击登录按钮
        function submitBtn () {
            sessionStorage.isLogin = true;
            let loginParams = {
                client_id: "61016ec489463334d638ceb433e8e1d1",
                grant_type: "password",
                username: userName,
                password: passWord
            };
            api.Login.send(loginParams).then((res) => {
                if (res.isSuccess) {
                    let token = JSON.stringify(res.data);
                    if (checked) {
                        localStorage.setItem("username", userName);
                        localStorage.setItem("password", passWord);
                        localStorage.setItem("checked", 0);
                    } else {
                        localStorage.removeItem("username");
                        localStorage.removeItem("password");
                        localStorage.removeItem("checked");
                    }
                    message.success('登录成功！');
                    localStorage.setItem("username", userName);
                    localStorage.setItem("password", passWord);
                    localStorage.setItem("token", token);
                    //点击登录跳转
                    me.props.history.push("/IndustryChain")
                } else {
                    message.info('请输入正确的用户名及密码！');
                    localStorage.removeItem("checked");
                }
            });
        }
        return (
            <div className={'login-wrapper'}>
                <div className={'login-box'}>
                    <form>
                        <div className={'form-header'}>
                            <h4>用户登录</h4>
                            <p className={'bottom-line'}></p>
                        </div>
                        <div className={'form-group'}>
                            <label htmlFor="userName">用户名：</label><br />
                            <Input id={'userName'} type="text" placeholder="请输入您的用户名" data-type="userName" onChange={this.inputHandle} value={this.state.userName} />
                        </div>
                        <div className={'form-group'}>
                            <label htmlFor="passWorld">密码：</label><br />
                            <Input id={'passWorld'} type="password" placeholder={'请输入您的密码'} data-type="passWord" onKeyDown={entreFn} onChange={this.inputHandle} value={passWord} />
                        </div>
                        <div>
                            <Checkbox onChange={this.onChange} checked={this.state.checked} style={{ 'color': '#8ed8ff', 'background': 'transparent' }}>记住密码</Checkbox>
                        </div>
                        <Button className={'login-btn'} type={'button'} onClick={submitBtn}>登录</Button>
                    </form>
                </div>
            </div>
        )
    }
    componentDidMount () {
        if (this.props.match.url === '/') {
            document.querySelector('ul').style.display = 'none';
            document.querySelector('b').style.display = 'none';
        }
        message.config({
            top: 100,
            duration: 2,
            maxCount: 1,
        });
        let tt = localStorage.getItem('token');
        var jz;
        if (tt) {
            jz = new Date(JSON.parse(localStorage.getItem('token')).rdeadTime).getTime();
        }
        let tem = new Date().getTime();
        let checked = localStorage.getItem('checked')
        if (checked) {
            this.setState({
                userName: localStorage.getItem('username'),
                passWord: localStorage.getItem('password'),
                checked: true
            })
            //console.log(jz)
            if (jz >= tem && jz) {
                this.props.history.push("/IndustryChain")
            }
        } else {
            this.setState({
                userName: "",
                passWord: ""
            })
        }
    };
    componentWillUnmount () {//组件将要移除时
        document.querySelector('ul').style.display = 'block';
        document.querySelector('b').style.display = 'block';
    }
}