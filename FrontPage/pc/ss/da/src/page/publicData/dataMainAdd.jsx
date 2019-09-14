import React, { Component } from "react";
import '../../static/scss/publicData/dataMain.scss';
import { Select, message,Button,Radio,Input} from 'antd';
import * as api from '../api/api-publicData.js';
import ReactQuill from "react-quill";
// import stylesheet
import 'react-quill/dist/quill.snow.css';
const Option = Select.Option;
const RadioGroup = Radio.Group;
export default class DataMain extends Component {
    constructor(props){
        super(props)
        this.state = {
            id:this.props.match.params.id,
            keywordStr:['农作物', '玉米', '小麦','病虫害','水稻'],
            articleName:"",                     //  文章名称
            sentimentTypeCode:3,                //  情感类型
            sentimentTypeText:"正面",           //
            mediaTypeCode:"",                   //媒体类型
            mediaTypeText:"",
            txt:"",                             //文章内容
        }
    }

    componentDidMount(){
        //  初始化页面
        const {id} = this.state
        api.dataMainSearch.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data
                let keyArr = v.keyName.split(' ')
                console.log(keyArr)
                this.setState({
                    keywordStr:keyArr,
                    articleName:v.articleName,
                    themeName:v.themeName,
                    threadStarter:v.threadStarter,
                    sentimentTypeCode:v.sentimentTypeCode,
                    sentimentTypeText:v.sentimentTypeText,
                    addTime:v.addTime,
                    mediaTypeText:v.mediaTypeText,
                    txt:v.daSentimentContent.txt,
                    artId:v.daSentimentContent.id,
                    mediaTypeCode:v.mediaTypeCode,
                    updateStatusCode:v.updateStatusCode
                })
            }
        })
    }
    // 保存
    saveFn = ()=>{
        const { 
            articleName,
            mediaTypeCode,
            mediaTypeText,
            sentimentTypeCode,
            sentimentTypeText,
            artId,
            id,
            txt
        } = this.state;
        let params ={
                "id": id,
                "articleName": articleName,
                "mediaTypeCode": mediaTypeCode,
                "mediaTypeText": mediaTypeText,
                "sentimentTypeCode": sentimentTypeCode,
                "sentimentTypeText": sentimentTypeText,
                "daSentimentContent": {
                    "id": artId,
                    "txt": txt,
                }
        }
        api.dataMainFix.send(params).then(res=>{
            console.log(res)
            if(res.isSuccess){
                message.success('修改成功！');

                // 返回上页
                this.goBackPage();
            }else{
                message.success('修改失败！');
            }
        })
    }
    //  返回上一页
    goBackPage = () => {
        this.props.history.go(-1)
    }
    //  情感类型
    radioFn=(e)=>{
        // console.log(e)
        let txt;
        if(Number(e) === 1){
            txt = "负面"
        }else if(Number(e) === 2){
            txt = "中性"
        }else{
            txt = "正面"
        }
        this.setState({
            sentimentTypeCode:e.target.value,
            sentimentTypeText:txt
        })
    }
    // 数据来源方法
    popularDataFn = () =>{
        //  媒体类型下拉请求
        api.popularData.send().then(res=>{
            // console.log(res)
            if(res.isSuccess){
                this.setState({
                    popularData:res.data
                })
            }
        })
    }
    // 媒体类型下拉选择
    mediaTypeSelect =(code,text)=>{
        this.setState({
            mediaTypeCode:code,
            mediaTypeText:text.props.children
        })
    }
    // 审核状态下拉选择
    typeFn =(code,text)=>{
        
        this.setState({
            updateStatusCode:code
        })
    }
    
    //  富文本框编辑内容
    txtOnChange=(html)=> {
        // console.log(html)
        this.setState({
            txt:html
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
    render() {
        const {
            keywordStr,
            articleName,
            themeName,
            threadStarter,
            mediaTypeText,
            updateStatusCode,
            sentimentTypeCode,
            txt,
            addTime
        } =this.state;

        const toolbarOptions = ['bold', 'italic', 'underline', 'strike'];

        const options = {
            theme: 'snow',
            modules: {
                toobar: toolbarOptions
            }
        }

        const events = {
            'text-change': delta => {
                console.log(delta)
            }
        }
        return (
            <div className="dataMain">
                <div className="screenData">
                    <div className="classify">
                        <label htmlFor="">关键词：</label>
                        <ul className="ulData keyword keywordss" style={{ position: "static" }}>
                            {
                                keywordStr.map((v, i) => {
                                    return (
                                        <li key={i}>{v}</li>
                                    )
                                })
                            }
                        </ul>
                    </div>
                    <div className="classify classify-4">
                        <div className="item">
                            <label htmlFor="">主题名称：</label>
                            <span>{themeName}</span>
                        </div>
                        <div className="item">
                            <label htmlFor="">媒体类型：</label>
                            <span>{mediaTypeText}</span>
                        </div>
                        <div className="item">
                            <label htmlFor="">数据来源：</label>
                            <span>{threadStarter}</span>
                        </div>
                    </div>
                    <div className="classify">
                    <label htmlFor="">文章标题：</label>
                            <Input value={articleName} data-type="articleName" onChange={this.inputHandle} style={{width:"55%"}}/>
                    </div>
                    <div className="classify">
                        <label htmlFor="">审核状态：</label>
                        <Select value={updateStatusCode} onChange={this.typeFn}  style={{width:"55%"}}>
                            <Option value={0}>原文</Option>
                            <Option value={1}>审核文章</Option>
                            <Option value={2}>其他</Option>
                        </Select>
                    </div>
                    <div className="classify">
                        <label htmlFor="">情感类型：</label>
                        <RadioGroup name="radiogroup" onChange={this.radioFn} value={sentimentTypeCode} >
                            <Radio value={3} style={{ color: "white", width: '77px' }}>正面</Radio>
                            <Radio value={2} style={{ color: "white", width: '77px' }}>中性</Radio>
                            <Radio value={1} style={{ color: "white", width: '77px' }}>负面</Radio>
                        </RadioGroup>
                    </div>
                    <div className="classify classify-4">
                        <div className="item">
                            <label htmlFor="">采集时间：</label>
                            <span>{addTime}</span>
                        </div>
                    </div>
                </div>
                <div> 
                    <ReactQuill
                    style={{ minHeight: '200px', marginBottom: '20px' }}
                    value={txt}
                    options={options}
                    events={events}
                    onChange={this.txtOnChange}
                    ref='editor' />
                </div>
                <div style={{textAlign:'center',marginTop:'30px'}}>
                    <Button
                        type="primary"
                        style={{
                            marginRight: "12px",
                            color: '#fff',
                        }}
                        onClick={this.saveFn}
                    >保存</Button>
                    <Button
                        style={{
                            marginRight: "12px",
                            color: '#333',
                        }}
                        onClick={() => { this.props.history.go(-1)}}
                    >取消</Button>
                </div>
            </div>
        )
    }
}