import React, { Component } from "react";
import '../../static/scss/publicData/dataMain.scss';
import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, Radio, DatePicker, Input, Button, Checkbox, Row, Col } from 'antd';
const { RangePicker } = DatePicker;
const RadioGroup = Radio.Group;
const Option = Select.Option;
function onChange(checkedValues) {
    console.log('checked = ', checkedValues);
}
function onChangeDate(date, dateString) {
    console.log(date, dateString);
}
export default class acquisitionAdd extends Component {
    render() {

        return (
            <div className="publicData-form" style={{padding:'0 260px'}}>
                <ul>
                    <li>
                        <label htmlFor="">规则编号：</label>
                        <Input defaultValue={'系统生成'} style={{ width: '80%' }} disabled />
                    </li>
                    <li>
                        <label htmlFor="">规则名称：</label>
                        <Input defaultValue={12} style={{width:'80%'}}/>
                    </li>
                    <li>
                        <label htmlFor="">采集频率：</label>
                        <Input defaultValue="" style={{ width: '36%',marginRight:'6%' }} />
                        <Select defaultValue="lucy" style={{ width: "38%" }}>
                            <Option value="全部">全部</Option>
                        </Select>
                    </li>
                    <li>
                        <label htmlFor="">采集时间区域：</label>
                        <RangePicker locale={locale} onChange={onChangeDate} style={{ width: '80%' }} />
                    </li>
                    <li>
                        <label htmlFor="">规则描述：</label>
                        <Input defaultValue={12} style={{ width: '80%' }} />
                    </li>
                    <li>
                        <label htmlFor="">采集频率：</label>
                        <Checkbox.Group style={{ width: '80%' }} onChange={onChange}>
                            <Row>
                                <Col span={3}><Checkbox value="0">新闻</Checkbox></Col>
                                <Col span={3}><Checkbox value="1">微信</Checkbox></Col>
                                <Col span={3}><Checkbox value="2">微博</Checkbox></Col>
                                <Col span={3}><Checkbox value="3">贴吧</Checkbox></Col>
                                <Col span={3}><Checkbox value="4">论坛</Checkbox></Col>
                                <Col span={3}><Checkbox value="5">博客</Checkbox></Col>
                            </Row>
                        </Checkbox.Group>
                    </li>
                    <li>
                        <label htmlFor="">情感类型：</label>
                        <RadioGroup name="radiogroup" style={{ width: '80%' }} defaultValue={1} >
                            <Radio value={1} >开启</Radio>
                            <Radio value={2} >关闭</Radio>
                        </RadioGroup>
                    </li>
                    <li style={{ textAlign: 'center', marginTop: '50px' }}>
                        <Button
                            type="primary"
                            style={{
                                marginRight: "20px",
                                color: '#fff',
                            }}
                        >保存</Button>
                        <Button
                            style={{
                                marginRight: "20px",
                                color: '#333',
                            }}
                            onClick={() => { this.props.history.go(-1) }}
                        >取消</Button>
                    </li>
                </ul>
            </div>
        )
    }
}