import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Table, Button, Icon, Tooltip, Popconfirm, message,Modal } from 'antd';
const Option = Select.Option;
const { RangePicker } = DatePicker;
function confirm(e) {
    console.log(e);
    message.success('点击了确定');
}

function cancel(e) {
    console.log(e);
    message.error('点击了取消');
}
const data = [];
const dataFalse = [
    {
        resourceType: '地方农业部门',
        resourceName: '华坪农业局',
        project: '项目',
        type: '业务数据',
        description : '直接对接业务系统,链接服务器',
        dates: '2017-03-26',
        name: '张大磊',
        phone: '13333333333',
        operate: 1
    }
]
function auditStateFn(params) {
    if (params === 1) {
        return '通过'
    } else if (params === 2) {
        return '未通过'
    } else {
        return '待审核'
    }
}
for (let i = 0; i < 46; i++) {
    data.push({
        key: i,
        0: dataFalse[0].resourceType,
        1: dataFalse[0].resourceName,
        2: dataFalse[0].project,
        3: dataFalse[0].type,
        4: dataFalse[0].description,
        5: dataFalse[0].dates,
        6: dataFalse[0].name,
        7: dataFalse[0].phone,
        8: dataFalse[0].operate,
        9: auditStateFn(dataFalse[0].auditState),
        10: 1,
    });
}
export default class ResourceManagement extends Component {
    constructor(props) {
        super(props)
        this.state = {
            selectedRowKeys: [], // Check here to configure the default column
            loading: false,
            columns: [
                {
                    title: '资源方类型',
                    dataIndex: '0',
                },
                {
                    title: '资源方名称',
                    dataIndex: '1',
                },
                {
                    title: '合作方式',
                    dataIndex: '2',
                },
                {
                    title: '资源类型',
                    dataIndex: '3',
                },
                {
                    title: '描述',
                    dataIndex: '4',
                },
                {
                    title: '合作日期',
                    dataIndex: '5',
                },
                {
                    title: '负责人',
                    dataIndex: '6',
                },
                {
                    title: '联系电话',
                    dataIndex: '7',
                },
                {
                    title: '操作',
                    dataIndex: '8',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="编辑"  onClick={this.showModal}>

                                    <Icon
                                        type="form"
                                        onClick={this.checkFn.bind(undefined, text, record)}
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />


                            </Tooltip>
                            <Tooltip placement="bottom" title="删除">
                                <Popconfirm title="确定删除吗?" onConfirm={confirm} onCancel={cancel} okText="确定" cancelText="取消">
                                    <Icon
                                        type="delete"
                                        onClick={this.checkFn.bind(undefined, text, record)}
                                        style={{ padding: '0 4px', cursor: 'potainer' }}
                                    />
                                </Popconfirm>
                            </Tooltip>
                        </span>
                    )
                }
            ]
        }
        // console.log(props)
    }
    checkFn = (e, t, y) => {
        console.log(e, t, y)
    }
    handleChange = (value) => {
        console.log(`selected ${value}`);
    }
    //弹窗部分
    showModal = (a) => {
        console.log(a.item)
        this.setState({
            visible: true,
        });
    }
    handleOk = (e) => {
        //console.log(e);
        this.setState({
            visible: false,
        });
    }
    handleCancel = (e) => {
        // console.log(e);
        this.setState({
            visible: false,
        });
    }

    model(a){
        //console.log(a)
        this.showModal();

    }

    start = () => {
        this.setState({ loading: true });
        // ajax request after empty completing
        setTimeout(() => {
            this.setState({
                selectedRowKeys: [],
                loading: false,
            });
        }, 1000);
    }

    onSelectChange = (selectedRowKeys) => {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.setState({ selectedRowKeys });
    }
    render() {
        const { loading, selectedRowKeys } = this.state;
        const rowSelection = {
            selectedRowKeys,
            onChange: this.onSelectChange,
            onSelect: (record, selected, selectedRows) => {
                console.log(record, ` selected :${selected}`, `selectedRows:${selectedRows}`);
            },
        };
        const hasSelected = selectedRowKeys.length > 0;
        return (
            <div className="forData">
                <ul className="form-search clearfix">
                    <li>
                        <label htmlFor="">资源方名称：</label>
                        <Input style={{ width: '65%' }} defaultValue=" " />
                    </li>
                    <li className="clearfix">
                        <Button type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">检索</Button>
                    </li>
                </ul>
                <div className="btn-type">
                   <Button onClick={this.showModal}>新增</Button>
                </div>
                <Table size={"small"} rowSelection={rowSelection} columns={this.state.columns} dataSource={data} />
                <Modal
                    visible={this.state.visible}
                    onOk={this.handleOk}
                    onCancel={this.handleCancel}
                    afterClose={this.afterClose}
                    width={'621px'}
                    title={null}
                    footer={null}
                    bodyStyle={{height:"460px"}}
                    className={'adialogBoxS'}
                >
                    <div className="modalTitle">专题创建</div>
                    <ul className="modalCon">
                        <li>
                            <label htmlFor="">资源方类型：</label>
                            <Select defaultValue="芒果品种" style={{ width: '162px' }} onChange={this.handleChange}>
                                <Option value="jack">台农芒</Option>
                            </Select>
                            <label htmlFor="" className="lableTwo">资源方名称：</label>
                            <Input style={{ width: '162px' }} defaultValue=" " />
                        </li>
                        <li>
                            <label htmlFor="">负责人：</label>
                            <Input style={{ width: '162px' }} defaultValue="芒果品种" />
                            <label htmlFor=""  className="lableTwo">联系电话：</label>
                            <Input style={{ width: '162px' }} defaultValue="" />
                        </li>
                        <li>
                            <label htmlFor="">合作日期：</label>
                            <Input style={{ width: '162px' }} defaultValue="1028-7-10" />
                        </li>
                        <li>
                            <label htmlFor="">合作方式：</label>
                            <Input style={{ width: '162px' }} defaultValue="" />
                        </li>
                        <li>
                            <label htmlFor="">资源类型：</label>
                            <Input style={{ width: '65%' }} defaultValue="" />
                        </li>
                        <li>
                            <label htmlFor="">描述：</label>
                            <Input style={{ width: '65%' }} defaultValue="" />
                        </li>
                        <li className="btnType">
                            <Button type="primary">保存</Button>
                            <Button type="" onClick={this.handleCancel}>取消</Button>
                        </li>
                    </ul>
                </Modal>
            </div>
        )
    }
}