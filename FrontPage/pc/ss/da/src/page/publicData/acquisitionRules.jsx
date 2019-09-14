import React, { Component } from "react";
import * as api from "../api/api-publicData";
import '../../static/scss/publicData/dataMain.scss';
import { Tooltip, Input, Table, Icon, Popconfirm, message,Select, Button,Radio, Modal} from 'antd';
import moment from 'moment';
const RadioGroup = Radio.Group;
const Option = Select.Option;

function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}

export default class acquisitionRules extends Component {
    constructor(props) {
        super(props);
        this.state = {
            selectedRowKeys: [], // Check here to configure the default column
            frequency: 100,//采集频率
            mediaTypeAddList:[  //  新增媒体类型下初始化数据
                {text:'全部',code:0}
            ],
            mediaTypeCodeSet: 0,//媒体类型code
            mediaTypeTextSet: "全部",//媒体类型
            remark: "",//描述
            ruleName: "",//规则名称
            statusCode: 2,//状态
            statusText: "启用",//code
            isAdd:true,   //    新增：true   编辑：false
            checkId:'',
            tableData:[],
            total:0,            //  总条数，默认0
            current:1,          //   当前页数，默认第1页
            ruleNameSearch:'',
            columnsTab: [
                {
                    title: '序号',
                    dataIndex: 'index',
                },{
                    title: '规则名称',
                    dataIndex: 'ruleName',
                },
                {
                    title: '采集频率',
                    dataIndex: 'frequency',
                },
                {
                    title: '描述',
                    dataIndex: 'remark',
                },
                {
                    title: '状态',
                    dataIndex: 'statusText',
                },
                {
                    title: '维护时间',
                    dataIndex: 'addTime',
                    render: (text, row, index) => {
                        if (text) {
                          return <span>{moment(text).format('YYYY-MM-DD')}</span>;
                        }
                    }
                },
                {
                    title: '操作',
                    dataIndex: '5',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="编辑" onClick={this.showModal.bind(undefined,2,record)}>
                                {/*<NavLink to="/publicData/acquisitionRules/acquisitionAdd">*/}
                                    <Icon
                                        type="form"
                                        // onClick={this.checkFn.bind(undefined,text,record)}
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                               {/* </NavLink>*/}
                            </Tooltip>
                            <Tooltip placement="bottom" title="删除">
                                <Popconfirm title="确定删除吗?" onConfirm={this.confirmDel.bind(undefined,record)}  onCancel={cancel} okText="确定" cancelText="取消">
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
    //弹窗部分
    showModal = (a,record) => {
        console.log(record)
        if(Number(a)===1){
            this.setState({
                visible: true,
                checkId:record.id,
                isAdd:true,
                frequency: 100,//采集频率
                mediaTypeCodeSet: 0,//媒体`类型code
                mediaTypeTextSet: "全部",//媒体类型
                remark: "",//描述
                ruleName: "",//规则名称
                statusCode:2,
                statusText: "启用",
            },()=>{
                // console.log(this.state)
            })
        }else{
            this.setState({
                visible: true,
                checkId:record.id,
                isAdd:false,
                frequency: record.frequency,
                mediaTypeTextSet: record.mediaTypeTextSet.split('-')[0],
                mediaTypeCodeSet: record.mediaTypeTextSet.split('-')[1],
                remark: record.remark,
                ruleName: record.ruleName,
                statusText: record.statusText,
                statusCode: record.statusText ==="启用"?2:3,
            },()=>{
                // console.log(this.state)
            })
        }
    }
    //  删除数据
    confirmDel=(e)=> {
        // console.log(e);
        let params={
            id:e.id
        }
        api.acquisitionRulesDel.send(params).then(res=>{
            // console.log(res)
            if(res.isSuccess){
                message.success('删除成功！')
                //  刷新页面
                this.getListFn(this.state.current);
            }else{
                message.info('删除失败！')
            }
        })
    }
    //  保存
    handleOk = (e) => {
        //console.log(e);
        this.setState({
            visible: false,
            frequency: "",//采集频率
            mediaTypeCodeSet: "",//媒体类型code
            mediaTypeTextSet: "",//媒体类型
            remark: "",//描述
            ruleName: "",//规则名称
            statusCode:2,
            statusText: "启用",
        });
        const {isAdd,frequency,checkId,mediaTypeCodeSet,mediaTypeTextSet,remark,ruleName,statusCode,statusText} = this.state
        console.log(this.state)
        if(isAdd) {
            let params = {
                "frequency": frequency,
                "mediaTypeCodeSet":mediaTypeCodeSet,
                "mediaTypeTextSet": mediaTypeTextSet,
                "remark": remark,
                "ruleName": ruleName,
                "statusCode": statusCode,
                "statusText": statusText
            }
            api.acquisitionRulesAdd.send(params).then(res => {
                console.log(res)
                if (res.isSuccess) {
                    message.success('新增成功！')
                    this.getListFn(this.state.current);
                }
            })
        }else{
            let params = {
                "frequency": frequency,
                'id':checkId,
                "mediaTypeCodeSet": mediaTypeCodeSet,
                "mediaTypeTextSet": mediaTypeTextSet,
                "remark": remark,
                "ruleName": ruleName,
                "statusCode": statusCode,
                "statusText": statusText
            }
            api.acquisitionRulesUpdate.send(params).then(res => {
                console.log(res)
                if (res.isSuccess) {
                    message.success('修改成功！')
                    this.getListFn(this.state.current);
                }
            })
        }
        this.setState({
            visible: false,
        });
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
    //  状态code，对应数据字典表，3：关闭，2：开启
    radioGroup=(e)=>{
        console.log(e)
        this.setState({
            statusCode:e.target.value,
            statusText:e.target.dataName
        },()=>{
            console.log(this.state.statusCode,this.state.statusText)
        })
    }
    //  点击检索按钮重新获得列表
    searchFn = ()=>{
        this.setState({
            current:1
        })
        this.getListFn(1)
    }
    handleCancel = (e) => {
        // console.log(e);
        this.setState({
            visible: false,
            frequency: 100,//采集频率
            mediaTypeCodeSet: 0,//媒体类型code
            mediaTypeTextSet: "全部",//媒体类型
            remark: "",//描述
            ruleName: "",//规则名称
            statusCode:2,
            statusText: "启用",
        });
    }
    //  table列表方法
    getListFn =(curPage)=>{
        this.setState({
            current:curPage
        })
        let params = {
            "jsonData":{
                "entityRelated" : {
                    "ruleName" : this.state.ruleNameSearch
                },
                "orderList" : [ {
                    "columnName" : "addTime",
                    "isASC" : false
                } ],
                "page" : {
                    "pageIndex" : curPage || 1,
                    "pageSize" : 10
                }
            }
        }
        api.acquisitionRules.send(params).then(res=>{
           console.log(res)
            if(res.isSuccess){
                this.setState({
                    total:res.totalCount
                })
                let tableData = [];
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:i,
                        mediaTypeTextSet:`${v.mediaTypeTextSet}-${v.mediaTypeCodeSet}`,
                        index:curPage*10-10+i+1,
                        website:v.website,
                        remark:v.remark,
                        ruleName:v.ruleName,
                        frequency:v.frequency,
                        statusText:v.statusText,
                        addTime:v.addTime,
                        id:v.id
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
    // 媒体类型下拉选择
    mediaTypeSelect =(code,text)=>{
        this.setState({
            mediaTypeCodeSet:code,
            mediaTypeTextSet:text.props.children
        },
            // ()=>{console.log(this.state.mediaTypeCodeSet,this.state.mediaTypeTextSet)}
        )
    }
    //  媒体类型方法
    getMediaTypeFn = () =>{
        //  媒体类型下拉请求
        api.mediaType.send().then(res=>{
            console.log(res)
            if(res.isSuccess){
                this.setState({
                    mediaTypeAddList:res.data
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
        //  初始化列表
        this.getListFn(this.state.current)
        //  媒体类型下拉
        this.getMediaTypeFn()
    }
    render() {
        const {frequency,total,current,mediaTypeAddList,ruleName,remark,statusCode,mediaTypeTextSet } = this.state;
        let pagination = {
            total: total,
            current: current,
            pageSize: 10,
            hideOnSinglePage:true,
            onChange:(current, pageSize) => {
                this.getListFn(current)
            },
        }
        // const hasSelected = selectedRowKeys.length > 0;
        return (
            <div className="dataMain">
                <div className="screenData">
                    <div className="classify" style={{ position: 'relative' }}>
                        <label htmlFor="">规则名称：</label>
                        <Input style={{ width: '165px' }} onChange={this.inputHandle} data-type="ruleNameSearch"  defaultValue="" />
                        <Button
                            type="primary"
                            className="fr"
                            onClick={this.searchFn}
                            style={{
                                marginRight: "12px",
                                color: '#fff',
                                position: 'absolute',
                                right: 0
                            }}
                            icon="search"
                        >检索</Button>
                    </div>
                </div>
                <div className="btn-type">
                  {/*  <NavLink to="/publicData/acquisitionRules/acquisitionAdd">*/}
                        <Button type="primary" style={{ color: '#fff' }} onClick={this.showModal.bind(undefined,1,'')}>新增</Button>
                  {/*  </NavLink>*/}
                </div>
                <Table
                size={"small"}
                pagination={pagination}
                columns={this.state.columnsTab}
                locale={{emptyText:"暂无数据！"}}
                dataSource={this.state.tableData}
                />
                <Modal
                    visible={this.state.visible}
                    title={'采集规则'}
                    onOk={this.handleOk}
                    onCancel={this.handleCancel}
                    okText={'保存'}
                    cancelText={'取消'}
                    afterClose={this.afterClose}
                    width={'700px'}
                    bodyStyle={{height:"400px"}}
                >
                    <div style={{letterSpacing:"1px",wordWarp:"break-word",fontSize:"14px",height:"450px",lineHeight:"35px"}}>
                        <ul className="modal-cover">
                            <li>
                                <label htmlFor="">规则名称：</label>
                                <Input style={{ width: "78%" }} data-type="ruleName" value={ruleName} defaultValue={ruleName} onChange={this.inputHandle}/>
                            </li>
                            <li>
                                <label htmlFor="">采集频率：</label>
                                <Input style={{ width: "165px" }} data-type="frequency" value={frequency} defaultValue={frequency} onChange={this.inputHandle}/>
                                <span style={{paddingLeft:"10px"}}>条/时</span>
                            </li>
                            <li>
                                <label htmlFor="">规则描述：</label>
                                <Input style={{ width: "78%" }} data-type="remark" value={remark} defaultValue={remark} onChange={this.inputHandle}/>
                            </li>
                            <li>
                                <label htmlFor="">媒体类型：</label>
                                    <Select onSelect={this.mediaTypeSelect} value={mediaTypeTextSet} style={{ width: "78%" }}>
                                        {
                                            mediaTypeAddList.map((v,i)=>{
                                                return(
                                                    <Option key={i} value={v.code}>{v.text}</Option>
                                                )
                                            })
                                        }

                                    </Select>
                            </li>
                            <li>
                                <label htmlFor="">情感类型：</label>
                                <RadioGroup name="radiogroup" style={{ width: '80%' }} defaultValue={statusCode}  value={statusCode} onChange={this.radioGroup}>
                                    <Radio dataName="开启" value={2} >开启</Radio>
                                    <Radio dataName="关闭" value={3} >关闭</Radio>
                                </RadioGroup>
                            </li>
                        </ul>
                    </div>
                </Modal>
            </div>
        )
    }
}