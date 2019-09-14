import React, { Component } from "react";
import '../../static/scss/publicData/dataMain.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { NavLink } from "react-router-dom";
import { Select,Radio, DatePicker,Tooltip, Input,Table, Icon,Popconfirm,message,Button,} from 'antd';
import * as api from '../api/api-publicData.js';
import moment from 'moment';
const Option = Select.Option;
const RadioGroup = Radio.Group;
const { RangePicker } = DatePicker;


function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}

export default class DataMain extends Component {
    constructor(props) {
        super(props);
        this.state = {
            current:1,                                  //  当前页
            total:0,                                    //  总条数
            tableData:[],                               //  列表数据
            themeName:"",             //  主题名称
            keyName:"",               //  关键词
            mediaTypeCode:0,            //  媒体类型
            timeTypeCode:5,             //  时间维度    1：年，2：季度，3：月，4：周，5：日，6：实时，7：自定义（开始时间2018-05-22结束时间2018-05-23）
            sentimentTypeCode:0,        //  情感类型    1负面，2中性，3正面，如果查询全部就不传
            columnName:"publishTime",     //  排序类型  viewCount：相关度，publishTime：发布时间
            stratTime:'',                 //  开始时间
            endTime:'',                   //  结束时间
            mediuIndex:0,
            rangePicker:true,              //禁用启用时间选择
            mediuType: [
                {
                    text:'全部',
                    code:0
                }
            ],
            keyword: ['农作物', '玉米', '水稻', '病虫害', '小麦', '水稻', '病虫害', '小麦', '水稻', '病虫害', '小麦'],
            columnsTab:[
                {
                    title: '序号',
                    dataIndex: '0',
                },{
                title: '所属主题',
                dataIndex: '1',
                },
                {
                    title: '文章标题',
                    dataIndex: '2',
                },
                {
                    title: '数据来源',
                    dataIndex: '3',
                },
                {
                    title: '相关度',
                    dataIndex: '4',
                },
                {
                    title: '采集时间',
                    dataIndex: '5',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY-MM-DD')}</span>;
                        }
                    }
                },
                {
                    title: '发布时间',
                    dataIndex: '6',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY-MM-DD')}</span>;
                        }
                    }
                },
                {
                    title: '审核状态',
                    dataIndex: '7',
                },
                {
                    title: '情感类型',
                    dataIndex: '8',
                },{
                    title: '操作',
                    dataIndex: '9',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="编辑">
                                <NavLink to={`/publicData/dataMaintaining/dataMainAdd/${text}`}>
                                    <Icon
                                        type="form"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                                </NavLink>
                            </Tooltip>
                            <Tooltip placement="bottom" title="删除">
                                <Popconfirm title="确定删除吗?" onConfirm={this.confirmDel.bind(undefined,text)} onCancel={cancel} okText="确定" cancelText="取消">
                                    <Icon
                                        type="delete"
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                                </Popconfirm>
                            </Tooltip>
                        </span>
                    )
                }
            ]
        }
    }
    //  媒体类型方法
    getMediaTypeFn = () =>{
        //  媒体类型下拉请求
        api.mediaType.send().then(res=>{
            // console.log(res)
            if(res.isSuccess){
                this.setState({
                    mediuType:res.data
                })
            }
        })
    }
    //删除
    confirmDel = (id)=>{
        console.log(id)
        api.dataMainDel.send({id:id}).then(res=>{
            console.log(res)
        })
    }
    //  获取table列表方法
    getTableList =(currentPage)=>{
        console.log(currentPage)
        const {
            themeName,
            keyName,
            mediaTypeCode,
            timeTypeCode,
            columnName,
            sentimentTypeCode,
            stratTime,
            endTime
        } = this.state;
        let params = {
            jsonData: {
                "entityRelated":{
                    "themeName":themeName,
                    "keyName":keyName, 
                    "mediaTypeCode":Number(mediaTypeCode) === 0?'':mediaTypeCode,
                    "timeTypeCode":timeTypeCode,
                    "sentimentTypeCode":Number(sentimentTypeCode) === 0?'':sentimentTypeCode,
                    "stratTime":stratTime,
                    "endTime":endTime
                },
                "orderList":[
                    {"columnName":columnName || "publishTime","isASC":false}
                ],
                "page":{
                    "pageIndex":currentPage || 1,
                    "pageSize":10
                }
            }
        }
        api.dataMaintain.send(params).then(res=>{
            // console.log(res)
            
            let tableData = [];
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:i,
                        0:currentPage*10-10+i+1,
                        1:v.themeName,
                        2:v.articleName,
                        3:v.threadStarter,
                        4:v.viewCount,
                        5:v.addTime,
                        6:v.publishTime,
                        7:v.publishTime,
                        8:v.sentimentTypeText,
                        9:v.id,
                    }
                })
                this.setState({
                    tableData:tableData,
                    total:res.totalCount
                })
            }else{
                this.setState({
                    tableData:[],
                    total:0
                })
            }
            
        })
    }
    componentDidMount(){
        //  未登录跳转到登录页
        if(!localStorage.token){
            this.props.history.push("/login")
            return
        }
        //  关键词ui宽度
        // this.refs.keyword.style.width = this.state.keyword.length*(95)+'px';
        //  初始化table
        this.getTableList(this.state.current)
        //  初始化媒体类型
        this.getMediaTypeFn()
    }
    //  媒体类型
    selectType = (e)=>{
        // console.log(e.target.getAttribute('data-index'))
        // console.log(e.target.innerHTML)
        let index = e.target.getAttribute('data-index')
        let code = e.target.getAttribute('data-code')
        let text = e.target.innerHTML
        this.setState({
            mediuIndex:e.target.getAttribute('data-index'),
            mediaTypeCode:code
        })
        message.info(`点击了-${index}-${code}-${text}`);
    }
    //  input监听
    inputHandle = (e) =>{
        // console.log(e.target.getAttribute('data-type'))
        let type = e.target.getAttribute('data-type')
        this.setState({
            [type]:e.target.value
        },()=>{
             console.log(this.state[type])
        })
    }
    // 情感类型下拉选择
    mediaTypeSelect =(text,value)=>{
        // console.log(text,value)
        this.setState({
            sentimentTypeCode:text
        })
    }
    //  时间维度
    radioGroup=(e)=>{
        // console.log(e.target)
        this.setState({
            timeTypeCode:e.target.value
        },()=>{
            // console.log(e.target.value)
        })
        if(Number(e.target.value) === 7){
            this.setState({
                rangePicker:false
            })
        }else{
            this.setState({
                rangePicker:true
            })
        }
    }
    //  时间
    onChangeTime = (date, dateString)=>{
        // console.log(date,dateString)
        this.setState({
            stratTime:dateString[0],
            endTime:dateString[1]
        })
    }
    render() {
        const { 
            total,  //总条数
            current,//  当前页
            tableData, //   table数据
            mediuIndex, //媒体类型下标
            mediuType, //媒体类型列表
            timeTypeCode,    //  时间维度
            rangePicker,     // 禁用启用时间选择
        } = this.state;
        //  分页配置
        let pagination = {
            total: total,
            current: current,
            pageSize: 10,
            hideOnSinglePage:true,
            onChange:(current, pageSize) => {
                this.getTableList(current)
                this.setState({
                    current:current
                })
            },
        }
        return (
            <div className="dataMain">
                <div className="screenData">
                    <div className="classify">
                        <label htmlFor="">媒体类型：</label>
                        <ul className="ulData mediaType clearfix">
                       {
                                mediuType.map((v,i)=>{
                                    return (
                                        <li key={i} onClick={this.selectType} className={Number(mediuIndex) === i ? 'active' : ''} data-code={v.code} data-index={i}>{v.text}</li>
                                    )
                                })
                       }
                       </ul>
                    </div>
                    <div className="classify">
                       <div className="classify-1">
                            <label htmlFor="">主题名称：</label>
                            <Input onChange={this.inputHandle} data-type="themeName"  style={{ width: '165px' }} defaultValue="" />
                       </div>
                       <div className="classify-1">
                            <label htmlFor="">情感类型：</label>
                            <Select  onSelect={this.mediaTypeSelect} defaultValue="全部" style={{ width: '165px' }}>
                                <Option value="3">正面</Option>
                                <Option value="2">中性</Option>
                                <Option value="1">负面</Option>
                            </Select>
                       </div>
                       <div className="classify-1">
                            <label htmlFor="" style={{ verticalAlign: 'top' }}>关键词：</label>
                            <Input onChange={this.inputHandle} data-type="keyName"  style={{ width: '165px' }} defaultValue="" />
                            
                       </div>
                    </div>
                    <div className="classify">
                        <RadioGroup onChange={this.radioGroup} className="radioCss" defaultValue={timeTypeCode} style={{width:'32%'}}>
                            <Radio value={5} style={{ textAlign: 'right', width: '77px' }}>24小时</Radio>
                            <Radio value={4} style={{ textAlign: 'right', width: '77px' }}>一周</Radio>
                            <Radio value={3} style={{ textAlign: 'right',width: '77px' }}>一个月</Radio>
                            <Radio value={7} style={{ textAlign: 'right',width: '77px' }}>自定义时间:</Radio>
                        </RadioGroup>
                        <RangePicker locale={locale} onChange={this.onChangeTime} disabled={rangePicker} style={{ width: '247px' }} />
                    </div>
                    <div className="classify"
                        style={{ position: 'relative'}}
                    >
                        <label htmlFor="">文章排序：</label>
                        <Button
                         className="time-btn" 
                         onClick={
                             ()=>{
                                 this.setState({
                                     columnName:"publishTime"
                                    },()=>{
                                        // console.log(this.state.columnName)
                                    })
                                }
                             }>发布时间</Button>
                        <Button 
                        className="about-btn" 
                        onClick={
                            ()=>{
                                    this.setState({
                                        columnName:"viewCount"
                                    },()=>{
                                        // console.log(this.state.columnName)
                                    })
                                }
                            }>相关度</Button>
                        <Button 
                            type="primary" 
                            className="fr" 
                            onClick={this.getTableList.bind(undefined,current)}
                            style={{ 
                                marginRight: "12px", 
                                color: '#fff',
                                position:'absolute',
                                right:0 
                            }} 
                            icon="search"
                        >检索</Button>
                    </div>
                </div>
                <Table 
                    size={"small"} 
                    locale={{emptyText:"暂无数据！"}}
                    pagination={pagination} 
                    columns={this.state.columnsTab} 
                    dataSource={tableData} />
            </div>
        )
    }
}