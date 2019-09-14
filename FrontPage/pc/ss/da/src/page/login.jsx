import React, { Component } from "react";
import { Button, Icon,Input,message,Checkbox } from 'antd';
import * as api from "./api/api-login"

export default class LoginIn extends Component{
    constructor(props){
        super(props);
        this.state = {
            checked:false,
            userName:"",
            passWord:"",
        }
    }
    onChange = () =>{
        this.setState({
            checked:!this.state.checked
        },()=>{
            console.log(this.state.checked)
        })
    }
    //  input监听
    inputHandle = (e) =>{
        // console.log(e.target.getAttribute('data-type'))
        let type = e.target.getAttribute('data-type')
        this.setState({
            [type]:e.target.value
        },()=>{
            //  console.log(this.state[type])
        })
    }
    getMenu =()=>{
        api.menu.send().then(res=>{
              console.log(res)
              let parentRoutes = [],childRoute1=[],childRoute2=[];
              if(res.isSuccess){
                res.data[0].child.forEach((v,i)=>{
                    parentRoutes[i] = {
                        path:v.path,
                        name:v.name
                    }
                    if(v.name==="直报数据"){
                        console.log(v.name);
                        childRoute1.push(v.child)
                    }else if(v.name === "舆情数据"){
                        childRoute2.push(v.child)
                    }
                })
                // console.log(parentRoutes)
                sessionStorage.setItem('urlMenu',JSON.stringify(parentRoutes))
                sessionStorage.setItem('urlMenuChild1',JSON.stringify(childRoute1))
                sessionStorage.setItem('urlMenuChild2',JSON.stringify(childRoute2))
              }
            })
    }
    componentDidMount(){
        document.querySelector('.header').style.display = 'none';
        document.querySelector('.footer').style.display = 'none';
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
            if (jz >= tem && jz) {
                this.props.history.push("/home")
            }
        } else {
            this.setState({
                userName: "",
                passWord: ""
            })
        }
      };
      componentWillUnmount() {//组件将要移除时
        document.querySelector('.header').style.display = 'block';
        document.querySelector('.footer').style.display = 'block';
      }
    render(){
        const { userName,passWord,checked } =this.state;
        const me = this;
        //回车时间
        function entreFn(e){
            if(e.keyCode === 13){
                submitBtn()
            }
        }
        //点击登录按钮
        function submitBtn() {

            sessionStorage.isLogin = true;
    
            let loginParams = {
                client_id: "61016ec489463334d638ceb433e8e1d1",
                grant_type: "password",
                username: userName,
                password: passWord
            };
            api.Login.send(loginParams).then((res) => {
                console.log(res)
                if(res.isSuccess) {
                    let token = JSON.stringify(res.data);
                    sessionStorage.setItem("token", res);
                    if(checked){
                        localStorage.setItem("username", userName);
                        localStorage.setItem("password", passWord);
                        localStorage.setItem("checked", 0);
                    }else{
                        localStorage.setItem("username", userName);
                        localStorage.setItem("password", passWord);
                        localStorage.removeItem("checked");
                    }
                    message.success('登录成功！');
                    localStorage.setItem("token", token);
                    //sessionStorage.removeItem("token");
                    //  存储菜单
                    me.getMenu()
                    //点击登录跳转
                    me.props.history.push("/home")
                }else {
                    message.info('请输入正确的用户名及密码！');
                    localStorage.removeItem("checked");
                }
            });
        }
        return(
            <div className="loginPage">
                <div className="logo">
                </div>
                <div className="login-box">
                    <label htmlFor="">用户名：</label>
                    <Input data-type="userName" onChange={this.inputHandle} value={userName} prefix={<Icon type="user" style={{ fontSize:20, color: '#999' }} />}/>
                    <label htmlFor="">密码：</label>
                    <Input  type="password" data-type="passWord" onKeyDown={entreFn} onChange={this.inputHandle} value={passWord} prefix={<Icon type="lock" style={{ fontSize:20, color: '#999' }} />}/>
                    <Checkbox onChange={this.onChange} checked={this.state.checked} style={{marginRight:"10px",fontSize:"14px"}}>记住密码</Checkbox> 
                    {/* <NavLink to="/home"> */}
                        <Button 
                        className="loginBtn" 
                        onClick={submitBtn}
                        style={{
                            width:"100%",
                            height:"35px",
                            marginTop:"30px"
                            }}>登录
                        </Button>
                    {/* </NavLink> */}
                </div>
            </div>
        )
    }
}