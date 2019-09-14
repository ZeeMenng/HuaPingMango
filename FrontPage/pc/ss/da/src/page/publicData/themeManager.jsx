import React, { Component } from "react";
import '../../static/scss/publicData/dataMain.scss';
import { Select, Radio, Tooltip, Tag, Input,Table, Icon,Popconfirm,message,Button,Modal} from 'antd';
import * as api from "../api/api-publicData";
const Option = Select.Option;
const RadioGroup = Radio.Group;

function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}
export default class themeManager extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tableData:[],
            total:0,                //  总条数
            current:1,              //   当前页数
            selectedRowKeys: [], // Check here to configure the default column
            keyNameArr: [],
            excludeNameArr: [],
            loading: false,
            visible: false,
            tabVisible: true,
            statusCode: 2,          //  状态
            checkId: "",
            statusText: "开启",     //  状态文字
            keyName: '',
            acquisitionRulesData:[{text:'全部',code:0}],
            themeName: '',
            themeNameS: '',
            ruleName: '全部',
            TagkeyIndex: -1,
            TagexcludeIndex:-1,
            columnsTab:[
                {
                    title: '序号',
                    dataIndex: 'index',
                },
                {
                    title: '主题名称',
                    dataIndex: 'themeName',
                },
                {
                    title: '关键词',
                    dataIndex: 'keyName',
                },
                {
                    title: '排除词',
                    dataIndex: 'excludeName',
                },
                {
                    title: '24小时信息量',
                    dataIndex: 'all24Num',
                },
                {
                    title: '24小时负面信息量',
                    dataIndex: 'negative24Num',
                },
                {
                    title: '采集状态',
                    dataIndex: 'statusText',
                },{
                    title: '操作',
                    dataIndex: '8',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip  onClick={this.showModal.bind(undefined,2,record)} placement="bottom" title="编辑">
                                <Icon
                                    type="form"
                                    // onClick={this.checkFn.bind(undefined,text,record)}
                                    style={{ padding: '0 4px', cursor: 'potainer' }}
                                />
                            </Tooltip>
                            <Tooltip placement="bottom" title="删除">
                                <Popconfirm title="确定删除吗?" onConfirm={this.confirmDel.bind(undefined,record)} onCancel={cancel} okText="确定" cancelText="取消">
                                    <Icon
                                        type="delete"
                                        /*onClick={this.checkFn.bind(undefined, text, record)}*/
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
    confirmDel=(e)=> {
        // console.log(e);
        let params={
            id:e.id
        }
        api.daSentimentThemeDel.send(params).then(res=>{
            console.log(res)
            if(res.isSuccess){
                message.success('删除成功！')
                this.getListFn(this.state.current);
            }else{
                message.info('删除失败！')
            }
        })
    }
    onSelectChange = (selectedRowKeys) => {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.setState({ selectedRowKeys });
    }
    // 采集规则下拉
    acquisitionRulesFn = () =>{
        //  媒体类型下拉请求
        let params = {
            "jsonData":{
                "entityRelated" : {
                    "ruleName" : ''
                },
                "orderList" : [ {
                    "columnName" : "addTime",
                    "isASC" : false
                } ],
                "page" : {
                    "pageIndex" : 1,
                    "pageSize" : 10
                }
            }
        }
        api.acquisitionRules.send(params).then(res=>{
            console.log(res)
            let acquisitionRulesNew = []
            if(res.isSuccess){
                res.data.forEach((v,i)=>{
                    acquisitionRulesNew[i] = {
                        text:v.ruleName,
                        code:v.id
                    }
                })
                this.setState({
                    acquisitionRulesData:this.state.acquisitionRulesData.concat(acquisitionRulesNew)
                },()=>{
                    console.log(this.state.acquisitionRulesData)
                })
            }
        })
    }
    areaHandleChange = (value)=> {
        // console.log(`selected ${value}`);
        this.setState({
            ruleId:value
        })
    }
    //  新增保存
    handleOk = (e) => {
        this.setState({
            excludeName: "",
            keyName: "",
            remark: "",
            ruleId: "",
            statusCode:2,
            statusText:"开启",
            themeName: ""
        })
        let { checkId, isAdd,ruleId,remark,statusCode,statusText,themeName, keyNameArr, excludeNameArr} = this.state;
        if(isAdd){
            let params = {
                "excludeName": excludeNameArr.join(),
                "keyName": keyNameArr.join(),
                "remark": remark,
                "ruleId": ruleId,
                "statusCode": statusCode,
                "statusText": statusText,
                "themeName": themeName
            }
            api.daSentimentThemeAdd.send(params).then(res=>{
                // console.log(res)
                if(res.isSuccess){
                    message.success('新增成功！')
                    this.getListFn(this.state.current)
                }
            })
        }else{
            let params = {
                "id":checkId,
                "excludeName": excludeNameArr.join(),
                "keyName": keyNameArr.join(),
                "remark": remark,
                "ruleId": ruleId,
                "statusCode": statusCode,
                "statusText": statusText,
                "themeName": themeName
            }
            api.daSentimentThemeFix.send(params).then(res=>{
                if(res.isSuccess){
                    message.success('修改成功！')
                    //  刷新页面
                    this.getListFn(this.state.current)
                }
            })
            this.setState({
                excludeName: "",
                keyName: "",
                remark: "",
                ruleId: "",
                statusCode:2,
                statusText:"开启",
                themeName: ""
            },()=>{
                console.log(this.state)
            })
        }
        this.setState({
            visible: false,
        });
    }
    //弹窗部分
    showModal = (a,record) => {
        console.log(record)
        if(Number(a)===1){
            this.setState({
                visible: true,
                checkId:record.id,
                isAdd:true,
                excludeName: "",
                keyName: "",
                remark: "",
                ruleId: 0,
                statusCode: 0,
                statusText: "",
                themeName: "",
                excludeNameArr:[],
                keyNameArr:[]

            },()=>{
                console.log(this.state)
            })
        }else{
            let keySting,excludeString;
            let codeArr = record.key.split('-')
            console.log(codeArr[0])
            if(record.keyName){
                keySting =  record.keyName.split(",");
            } else {
                keySting = [];
            }
            if(record.excludeName){
                excludeString = record.excludeName.split(",");
            } else {
                excludeString = [];
            }
            this.setState({
                visible: true,
                checkId:record.id,
                isAdd:false,
                excludeNameArr: excludeString,
                keyNameArr: keySting,
                remark: record.remark,
                ruleId: codeArr[codeArr.length-1],
                statusCode: Number(codeArr[0]),
                statusText: record.statusText,
                themeName: record.themeName
            },()=>{
                // console.log(this.state)
            })
        }
    }
    // 关键词
    addKeyName = (e) => {
        if(this.state.keyName){
            let newArr = [];
            newArr.push(this.state.keyName)
            this.setState({
                keyNameArr: this.state.keyNameArr.concat(newArr),
                keyName: ""
            });
        }
    }
    // 排除词
    excludeName = (e) => {
        if(this.state.excludeName){
            let newArr = [];
            newArr.push(this.state.excludeName)
            this.setState({
                excludeNameArr: this.state.excludeNameArr.concat(newArr),
                excludeName: ""
            });
        }
    }
    handleCancel = (e) => {
        // console.log(e);
        this.setState({
            visible: false,
        });
    }
    //  状态code，对应数据字典表，3：关闭，2：开启
    radioGroup=(e)=>{
        console.log(e.id)
        this.setState({
            statusCode:e.target.value,
            statusText:e.target.dataName
        })
    }
    //  初始化列表方法
    getListFn =(curPage)=>{

        this.setState({
            current:curPage
        })
        let params = {
            "jsonData":{
                "entityRelated" : {
                    "themeName" : this.state.themeNameS	
                },
                "orderList":[
                    {
                        "columnName":"addTime",
                        "isASC":false
                    }
                ],
                "page":{
                    "pageIndex":curPage || 1,
                    "pageSize":10
                }
            }
        }
        console.log(params)
        api.daSentimentThemeList.send(params).then(res=>{
            console.log(res)
            if(res.isSuccess){
                this.setState({
                    total:res.totalCount
                })
                let tableData = [];
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:`${v.statusCode}-${i}-${v.ruleId}`,
                        index:curPage*10-10+i+1,
                        themeName:v.themeName,
                        keyName:v.keyName,
                        negative24Num:v.negative24Num,
                        remark:v.remark,
                        statusText:v.statusText,
                        addTime:v.addTime,
                        id:v.id,
                        all24Num:v.all24Num,
                        excludeName:v.excludeName
                    }
                })
                this.setState({
                    tableData: tableData,
                })
            }else{
                this.setState({
                    tableData: [],
                    total:0
                })
            }
        })
    }
    //  input监听
    inputHandle = (e) =>{
        // console.log(e.target.getAttribute('data-type'))
        let type = e.target.getAttribute('data-type')
        this.setState({
            [type]:e.target.value
        },()=>{
            console.log(this.state)
        })
    }
    handleKeyClose = (removedTag,arr) => {
        arr.splice(removedTag,1);
    }
    handleClose = (removedTag,arr) => {
        arr.splice(removedTag,1);
    }
    searchFn = ()=>{
        this.getListFn(this.state.current)
    }
    componentDidMount(){
        //  未登录跳转到登录页
        if(!localStorage.token){
            this.props.history.push("/login")
            return
        }
        //  初始化列表
        this.getListFn(this.state.current)
        this.acquisitionRulesFn();
    }
    render() {
        const { statusCode, total, current, keyNameArr, excludeNameArr, acquisitionRulesData} = this.state;
        let pagination = {
            total: total,
            current: current,
            pageSize: 10,
            hideOnSinglePage:true,
            onChange:(current, pageSize) => {
                this.getListFn(current);
                this.setState({
                    current:current
                })
            },
        }
        return (
            <div className="dataMain">
                <div className="screenData">
                    <div className="classify" style={{position:'relative'}}>
                        <label htmlFor="">主题名称：</label>
                        <Input style={{ width: '165px' }} data-type="themeNameS" onChange={this.inputHandle} value=""  />
                        <Button onClick={this.searchFn} type="primary" className="fr" style={{position:'absolute',right:0,color:"#fff", marginRight: "12px" }} icon="search">检索</Button>
                    </div>
                </div>
                <div className="btn-type">
                    <Button type="primary" style={{color:'#fff'}} onClick={this.showModal.bind(undefined,1,'')}>新建主题</Button>
                    <Modal
                        visible={this.state.visible}
                        title={'专题创建'}
                        onOk={this.handleOk}
                        onCancel={this.handleCancel}
                        okText={'保存'}
                        cancelText={'取消'}
                        afterClose={this.afterClose}
                        width={'700px'}
                        bodyStyle={{height:"500px"}}
                    >
                        <div style={{letterSpacing:"1px",wordWarp:"break-word",fontSize:"14px",height:"450px",lineHeight:"35px"}}>
                            <ul className="modal-cover">
                                <li>
                                    <label htmlFor="">主题名称：</label>
                                    <Input style={{ width: "78%" }} data-type="themeName" value={this.state.themeName} onChange={this.inputHandle} defaultValue={this.state.themeName} />
                                </li>
                                <li className="positionLi">
                                    <label htmlFor="" className="blue" onClick={this.addKeyName}>
                                        <Icon type="plus-circle-o" style={{marginRight:'6px'}} />
                                        添加关键词：</label>
                                    <Input style={{ width: "78%" }} data-type="keyName" value={this.state.keyName} onChange={this.inputHandle} defaultValue={this.state.keyName} />
                                    <p>设置监测关键词(每个主体最多可设置10组关键词，每组关键词字数建议在30个以内)</p>
                                </li>
                                <li>
                                    <label htmlFor=""></label>
                                    {
                                        keyNameArr.map((item,i) => (
                                            <Tag
                                                closable
                                                key={i}
                                                onClose={() => this.handleKeyClose(i,keyNameArr)}>
                                                {item}
                                            </Tag>
                                        ))
                                    }
                                </li>
                                <li className="positionLi">
                                    <label htmlFor="" className="blue" onClick={this.excludeName}>
                                        <Icon type="plus-circle-o" style={{marginRight:'6px'}} />
                                        添加关键词：</label>
                                    <Input style={{ width: "78%" }} data-type="excludeName" value={this.state.excludeName} onChange={this.inputHandle} defaultValue={this.state.excludeName} />
                                    <p>设置排除词(每个主体最多可设置15组关键词，每组关键词字数建议在10个以内)</p>
                                </li>
                                <li>
                                    <label htmlFor=""></label>
                                    {
                                        excludeNameArr.map((item,i) => (
                                            <Tag
                                                closable
                                                key={i}
                                                onClose={() => this.handleClose(i,excludeNameArr)}>
                                                {item}
                                            </Tag>
                                        ))
                                    }
                                </li>
                                <li className="halfLi">
                                    <label htmlFor="">数据采集规则：</label>
                                    <Select defaultValue={acquisitionRulesData[0].code} style={{ width: '56%' }} onChange={this.areaHandleChange}>
                                    {
                                        acquisitionRulesData.map((v,i)=>{
                                            return(
                                                <Option codenum={'ruleId'}  typename={'ruleName'} key={i} value={v.code}>{v.text}</Option>
                                            )
                                        })
                                    }
                                    </Select>
                                </li>
                                <li>
                                    <label htmlFor="">状态：</label>
                                    <RadioGroup name="radiogroup" onChange={this.radioGroup}  value={statusCode} >
                                        <Radio value={2} dataName="开启" >开启</Radio>
                                        <Radio value={3} dataName="关闭">关闭</Radio>
                                    </RadioGroup>
                                </li>
                            </ul>
                        </div>
                    </Modal>
                </div>
                <Table
                    size={"small"} 
                    // rowSelection={rowSelection} 
                    pagination={pagination} 
                    locale={{emptyText:"暂无数据！"}}
                    columns={this.state.columnsTab} 
                    dataSource={this.state.tableData}
                 />
            </div>
        )
    }
}