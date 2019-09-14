
import React, { Component } from "react";
import '../../static/scss/publicData/dataMain.scss';
import { Select, Radio, Tooltip, Input, Table, Icon, Popconfirm, message,  Button, Modal } from 'antd';
import * as api from "../api/api-publicData";
import moment from 'moment';
const Option = Select.Option;
const RadioGroup = Radio.Group;

function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}

export default class PublicData extends Component {
    constructor(props) {
        super(props);
        this.state = {
            selectedRowKeys: [], // Check here to configure the default column
            loading: false,
            visible: false,     //  新增弹出框显示隐藏
            mediaTypeAddList:[  //  新增媒体类型下初始化数据
                {text:'全部',code:0}
            ],
            tableData:[],
            total:0,                //  总条数
            current:1,              //   当前页数
            threadStarterSerach:'',
            checkId:'',             //  编辑id
            isAdd:true,             //    新增：true   编辑：false
            mediuIndex:0,           //  默认选中：全部
            mediuCode:0,            //  默认类型：全部
            mediaTypeCode: 0,       //  新增媒体类型code
            mediaTypeText: "全部",  //  新增媒体类型text
            remark: "",             //  描述
            statusCode: 2,          //  状态
            statusText: "开启",     //  状态文字
            threadStarter: "",      //  资源名称
            website: "",            //  网址

            tabVisible: true,
            columnsTab: [
            {
                title: '序号',
                dataIndex: 'index',
            },
            {
                title: '媒体类型',
                dataIndex: 'mediaTypeText',
            },
            {
                title: '资源名称',
                dataIndex: 'threadStarter',
            },
            {
                title: '网址',
                dataIndex: 'website',
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
                dataIndex: 'id',
                key: 'check',
                render: (text, record) => (
                    <span>
                        {/* <Tooltip onClick={this.openFn} placement="bottom" title="开启">
                            <Icon
                                type="check-circle-o"
                                // onClick={this.checkFn.bind(undefined,text,record)}
                                style={{ padding: '0 4px', cursor: 'potainer' }}
                            />
                        </Tooltip>
                        <Tooltip onClick={this.closeFn} placement="bottom" title="关闭">
                            <Icon
                                type="close-circle-o"
                                // onClick={this.checkFn.bind(undefined,text,record)}
                                style={{ padding: '0 4px', cursor: 'potainer' }}
                            />
                        </Tooltip> */}
                        <Tooltip onClick={this.showModal.bind(undefined,2,record)} placement="bottom" title="编辑">
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
    //  媒体类型方法
    getMediaTypeFn = () =>{
        //  媒体类型下拉请求
        api.mediaType.send().then(res=>{
            // console.log(res)
            if(res.isSuccess){
                this.setState({
                    mediaTypeAddList:res.data
                })
            }
        })
    }
    //  初始化列表方法
    getListFn =(curPage)=>{

        this.setState({
            current:curPage
        })
        let params = {
            "jsonData":{
                "entityRelated":{
                    "threadStarter":this.state.threadStarterSerach,
                    "mediaTypeCode":this.state.mediuCode || ''
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
        api.dataManager.send(params).then(res=>{
            console.log(res)
            if(res.isSuccess){
                this.setState({
                    total:res.totalCount
                })
                let tableData = [];
                res.data.forEach((v,i)=>{
                    tableData[i] = {
                        key:`${v.statusCode}-${i}-${v.mediaTypeCode}`,
                        index:curPage*10-10+i+1,
                        mediaTypeText:v.mediaTypeText,
                        threadStarter:v.threadStarter,
                        website:v.website,
                        remark:v.remark,
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
    //  检索重新获得列表
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
        //  初始化媒体类型
        this.getMediaTypeFn()
    }
    showModal = (a,record) => {
        // console.log(a)
        // console.log(record)
        // console.log(record.id || 'r')
        if(Number(a)===1){
            this.setState({
                visible: true,
                checkId:record.id,
                isAdd:true,
                mediaTypeCode:0,
                statusCode:2,
                remark:'',
                threadStarter:'',
                website:''
            },()=>{
                console.log(this.state)
            })
        }else{
            let codeArr = record.key.split('-')
            this.setState({
                visible: true,
                checkId:record.id,
                isAdd:false,
                mediaTypeCode:Number(codeArr[codeArr.length-1]),
                statusCode:Number(codeArr[0]),
                remark:record.remark,
                threadStarter:record.threadStarter,
                website:record.website
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
        api.FrameMessDel.send(params).then(res=>{
            console.log(res)
            if(res.isSuccess){
                message.success('删除成功！')
                this.getListFn(this.state.current);
            }else{
                message.info('删除失败！')
            }
        })
    }
    // 媒体类型下拉选择
    mediaTypeSelect =(code,text)=>{
        this.setState({
            mediaTypeCode:text.props.value,
            mediaTypeText:text.props.children
        })
    }
    //  新增保存
    handleOk = (e) => {
        this.setState({
            mediaTypeCode:0,
            mediaTypeText:"全部",
            statusCode:2,
            statusText:"开启",
            remark:'',
            threadStarter:'',
            website:''
        })
        let {isAdd,checkId,mediaTypeCode,mediaTypeText,remark,statusCode,statusText,threadStarter,website} = this.state;
        if(isAdd){
            let params = {
                "mediaTypeCode": mediaTypeCode,
                "mediaTypeText": mediaTypeText,
                "remark": remark,
                "statusCode": statusCode,
                "statusText": statusText,
                "threadStarter": threadStarter,
                "website": website
            }
            api.FrameMessAdd.send(params).then(res=>{
                // console.log(res)
                if(res.isSuccess){
                    message.success('新增成功！')
                    this.getListFn(this.state.current);
                }
            })
        }else{//  修改
            let params = {
                'id':checkId,
                "mediaTypeCode": mediaTypeCode,
                "mediaTypeText": mediaTypeText,
                "remark": remark,
                "statusCode": statusCode,
                "statusText": statusText,
                "threadStarter": threadStarter,
                "website": website
            }
            api.FrameMessUpdate.send(params).then(res=>{
                if(res.isSuccess){
                    message.success('修改成功！')
                    //  刷新页面
                    this.getListFn(this.state.current);

                }
            })
            this.setState({
                mediaTypeCode:0,
                mediaTypeText: '全部',
                statusCode:2,
                statusText: '开启',
                remark:'',
                threadStarter:'',
                website:''
            },()=>{
                // console.log(this.state)
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
        console.log(e.id)
        this.setState({
            statusCode:e.target.value,
            statusText:e.target.dataName
        })
    }
    handleCancel = (e) => {
        // console.log(e);
        this.setState({
            visible: false,
            mediaTypeCode:0,
            mediaTypeText: '全部',
            statusCode:2,
            statusText: '开启',
            remark:'',
            threadStarter:'',
            website:''
        });
    }
    //  查询媒体类型
    selectType = (e) => {
        // console.log(e.target.getAttribute('data-index'))
        // console.log(e.target.innerHTML)
        let code = e.target.getAttribute('data-code')
        let index = e.target.getAttribute('data-index')
        let text = e.target.innerHTML
        this.setState({
            mediuCode: code,
            mediuIndex: index
        })
        // message.info(`选择了-${code}-${text}`);
    }
    render() {
        const me = this;
        const { mediuIndex,statusCode, mediaTypeAddList,total,current, mediaTypeCode } = this.state;
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
                    <div className="classify">
                        <label htmlFor="">媒体类型：</label>
                        <ul className="ulData mediaType clearfix" style={{width:'90%'}}>
                            {
                                mediaTypeAddList.map((v, i) => {
                                    return (
                                        <li 
                                            key={i} 
                                            onClick={this.selectType} 
                                            className={Number(mediuIndex) === i ? 'active' : ''} 
                                            data-code={v.code}
                                            data-index={i}
                                        >{v.text}</li>
                                    )
                                })
                            }
                        </ul>
                    </div>
                    <div className="classify" style={{ position: 'relative' }}>
                        <label htmlFor="">资源名称：</label>
                        <Input  data-type="threadStarterSerach" onChange={this.inputHandle} style={{ width: '165px' }} defaultValue="" />
                        <Button type="primary" onClick={this.searchFn} className="fr" style={{ position: 'absolute', right: 0, color: "#fff", marginRight: "12px" }} icon="search">检索</Button>
                    </div>
                </div>
                <div className="btn-type">
                    <Button type="primary" style={{ color: '#fff' }} onClick={me.showModal.bind(undefined,1,'')}>新增</Button>
                    <Modal
                        visible={this.state.visible}
                        title={'数据资源维护'}
                        onOk={this.handleOk}
                        onCancel={this.handleCancel}
                        okText={'保存'}
                        cancelText={'取消'}
                        afterClose={this.afterClose}
                        width={'700px'}
                        bodyStyle={{ height: "400px" }}
                    >
                        <div style={{ letterSpacing: "1px", wordWarp: "break-word", fontSize: "14px", height: "450px", lineHeight: "35px" }}>
                            <ul className="modal-cover">
                                <li>
                                    <label htmlFor="">媒体类型：</label>
                                    <Select onSelect={this.mediaTypeSelect} value={mediaTypeCode} style={{ width: "78%" }}>
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
                                    <label htmlFor="">资源名称：</label>
                                    <Input style={{ width: "78%" }} data-type="threadStarter" value={this.state.threadStarter} onChange={this.inputHandle} defaultValue={this.state.threadStarter} />
                                </li>
                                <li>
                                    <label htmlFor="">网址：</label>
                                    <Input style={{ width: "78%" }} data-type="website" value={this.state.website} onChange={this.inputHandle} defaultValue={this.state.website} />
                                </li>
                                <li>
                                    <label htmlFor="">描述：</label>
                                    <Input style={{ width: "78%" }} data-type="remark" value={this.state.remark} onChange={this.inputHandle} defaultValue={this.state.remark} />
                                </li>
                                {/* <li >
                                    <label htmlFor="">维护时间：</label>
                                    <DatePicker locale={locale} style={{ width: '78%' }} />
                                </li> */}
                                <li>
                                    <label htmlFor="">状态：</label>
                                    <RadioGroup name="radiogroup" onChange={this.radioGroup} value={statusCode} >
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