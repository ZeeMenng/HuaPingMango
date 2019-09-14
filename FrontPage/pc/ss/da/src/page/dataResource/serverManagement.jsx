import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import '../../static/scss/forData/mainData.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, DatePicker, Input, Table, Button, Icon, Tooltip, Popconfirm, message,Modal } from 'antd';
const Option = Select.Option;
const { RangePicker } = DatePicker;
function onChange(date, dateString) {
    console.log(date, dateString);
}
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
        collect: '年',
        collectDate: '2018-01-12',
        areaHelp: '农户',
        area: '云南省丽江市华坪县',
        dataDate: '2018-01-12',
        cardID: '110234567876543',
        name: '张大磊',
        dataSources: '农户上报',
        sourceTo: '农户app填报',
        auditState: 1,       //  1 通过，2 未通过，3 待审核
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
        0: dataFalse[0].collect,
        1: dataFalse[0].collectDate,
        2: dataFalse[0].areaHelp,
        3: dataFalse[0].area,
        4: dataFalse[0].dataDate,
        5: dataFalse[0].cardID,
        6: dataFalse[0].name,
        7: dataFalse[0].dataSources,
        8: dataFalse[0].sourceTo,
        9: auditStateFn(dataFalse[0].auditState),
        10: 1,
    });
}
export default class ServerManagement extends Component {
    constructor(props) {
        super(props)
        this.state = {
            selectedRowKeys: [], // Check here to configure the default column
            loading: false,
            columns: [
                {
                    title: '采集维度',
                    dataIndex: '0',
                },
                {
                    title: '采集日期',
                    dataIndex: '1',
                },
                {
                    title: '区域维度',
                    dataIndex: '2',
                },
                {
                    title: '地理区域',
                    dataIndex: '3',
                },
                {
                    title: '数据日期',
                    dataIndex: '4',
                },
                {
                    title: '身份证号',
                    dataIndex: '5',
                },
                {
                    title: '姓名',
                    dataIndex: '6',
                },
                {
                    title: '数据来源',
                    dataIndex: '7',
                },
                {
                    title: '来源渠道',
                    dataIndex: '8',
                },
                {
                    title: '审核状态',
                    dataIndex: '9',
                },
                {
                    title: '操作',
                    dataIndex: '10',
                    key: 'check',
                    render: (text, record) => (
                        <span>
                            <Tooltip placement="bottom" title="编辑" onClick={this.showModal}>
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
                        <label htmlFor="">名称：</label>
                        <Input style={{ width: '65%' }} defaultValue=" " />
                    </li>
                    <li>
                        <label htmlFor="">来源渠道：</label>
                        <Select defaultValue="lucy" style={{ width: '65%' }} onChange={this.handleChange}>
                            <Option value="jack">Jack</Option>
                            <Option value="lucy">Lucy</Option>
                            <Option value="disabled" disabled>Disabled</Option>
                            <Option value="Yiminghe">yiminghe</Option>
                        </Select>
                    </li>
                    <li>
                        <Button type="primary" className="fr" style={{ marginRight: "12px" }} icon="search">检索</Button>
                    </li>
                </ul>
                <div className="btn-type">
                   <Button icon="plus" onClick={this.showModal}>新增</Button>
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
                <ul className="serverModal">
                    <li>
                        <label htmlFor="">资源方名称：</label>
                        <Select defaultValue="芒果品种" style={{ width: '162px' }} onChange={this.handleChange}>
                            <Option value="jack">台农芒</Option>
                        </Select>
                        <label htmlFor="" className="lableTwo">业务系统名称：</label>
                        <Input style={{ width: '162px' }} defaultValue=" " />
                    </li>
                    <li>
                        <label htmlFor="">服务器名称：</label>
                        <Input style={{ width: '162px' }} defaultValue="芒果品种" />
                        <label htmlFor=""  className="lableTwo">服务器状态：</label>
                        <Input style={{ width: '162px' }} defaultValue="" />
                    </li>
                    <li>
                        <label htmlFor="">服务器地址：</label>
                        <Input style={{ width: '162px' }} defaultValue="1028-7-10" />
                        <label htmlFor="" className="lableTwo">端口号：</label>
                        <Input style={{ width: '162px' }} defaultValue="1028-7-10" />
                    </li>
                    <li>
                        <label htmlFor="">服务器地址：</label>
                        <Input style={{ width: '162px' }} defaultValue="" />
                    </li>
                    <li>
                        <label htmlFor="">类型说明：</label>
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