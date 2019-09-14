import React, { Component } from "react";
import '../../static/scss/forData/mainData.scss';
// import locale from 'antd/lib/date-picker/locale/zh_CN';
import { Select, message, Input, Button } from 'antd';
import * as api from "../api/api-forData-second";
const Option = Select.Option;

export default class IndustryDataAduit extends Component {
    constructor(props){
        super(props)
        this.state = ({
            id:props.match.params.id.split('-')[0],              //  地址栏传参id
            comId:props.match.params.id.split('-')[1],           //  地址栏传参id
            auditStatusCode:1,                                   // 审核结果
            auditStatusText:'通过'
        })
    }
    //  渲染数据
    initList=(id)=>{
        api.industryDataSearch.send({id:id}).then(res=>{
            console.log(res)
            if(res.isSuccess){
                let v = res.data;
                this.setState({
                    name:v.name,
                    cropTotalAreaText: v.cropTotalAreaText,                      //  作物总面积单位文本
                    cropFruitArea: v.cropFruitArea,                          //作物挂果面积
                    cropFruitAreaText: v.cropFruitAreaText,                      //作物挂果面积单位文本
                    cropGraftArea: v.cropGraftArea,                          //作物嫁接面积
                    cropGraftAreaText: v.cropGraftAreaText,                      //作物嫁接面积文本
                    cropTotalArea: v.cropTotalArea,                          //作物总面积
                    developVariety:v.developVariety,                          //拟发展品种
                    growArea: v.growArea,                                //海拔1500米以下准备种植品种面积
                    growAreaText: v.growAreaText,                         //海拔1500米以下准备种植品种面积单位文本
                    landArea: v.landArea,                               //海拔1500米以下土地面积
                    landAreaText: v.landAreaText,                     //海拔1500米以下土地面积单位文本
                    ngraftedTreeArea: v.ngraftedTreeArea,                       //未嫁接大树面积
                    ngraftedTreeAreaText: v.ngraftedTreeAreaText,                 //未嫁接大树面积文本
                    ngraftedYoungtreeArea: v.ngraftedYoungtreeArea,                  //未嫁接幼树面积
                    ngraftedYoungtreeAreaText: v.ngraftedYoungtreeAreaText, 
                    regionName:v.daCommonField.regionName,
                    startTime:v.daCommonField.startTime,
                    areaLatitudeTypeText:v.daCommonField.areaLatitudeTypeText,
                    cropTypeText:v.cropTypeText,
                    strainsText:v.strainsText,
                    sourceChannelTypeText:v.daCommonField.sourceChannelTypeText,
                    dataSources:v.daCommonField.dataSources,
                    dataTimeTypeText:v.daCommonField.dataTimeTypeText,
                    auditStatusCode:v.daCommonField.auditStatusCode,
                    auditStatusText:v.daCommonField.auditStatusText,
                },
                // ()=>console.log(this.state)
            )
            }else{
                message.error('信息初始化失败！')
            }
        })
    }
    componentDidMount(){
        //  初始化数据
        this.initList(this.state.id)
    }
    //  上报
    formSaveFn = () =>{
        const {
            id,
            comId,
            auditStatusCode,
            auditStatusText,
            auditerSuggestion
        } =this.state
        let params = {
            "entity": {
              "daCommonField": {
                "auditStatusCode": auditStatusCode,
                "auditStatusText": auditStatusText,
                "auditerSuggestion": auditerSuggestion,
                "id": comId
              }
            },
            "idList": [
                id
            ]
          }
          api.industryDataAudit.send(params).then(res=>{
            //   console.log(res)
              if(res.isSuccess){
                  message.success('上报成功！')
                  //   返回上一页
                  this.goBackPage()
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
            //  console.log(this.state[type])
        })
    }
    //  返回上一页
    goBackPage = () =>{
        this.props.history.go(-1)
    }
    //  下拉获取值通用方法
    handleSelect = (e,option) =>{
        console.log(e,option)
        let type = option.props.typename;
        let code = option.props.codenum;
        let text = option.props.children;
        this.setState({
            [type]:text,
            [code]:e
        },()=>{
            // console.log(this.state[type],this.state[code])
        })
    }
    render() {
        const {
            dataTimeTypeText,
            sourceChannelTypeText,
            areaLatitudeTypeText,
            cropTypeText,
            strainsText,
            cropTotalAreaText,
            startTime,
            dataSources,
            name,
            regionName,
            cropTotalArea,
            developVariety,
            growArea,
            growAreaText,
            landArea,
            landAreaText,
            ngraftedYoungtreeArea,
            ngraftedYoungtreeAreaText,
            ngraftedTreeArea,
            ngraftedTreeAreaText,
            cropGraftArea,
            cropGraftAreaText,
            cropFruitArea,
            cropFruitAreaText,
            auditStatusText
        } =this.state
        return (
            <div className="forData">
                <ul className="form-search form-aduit clearfix">
                    {/* <li>
                        <label htmlFor="">时间维度：</label>
                        <span>{dataTimeTypeText}</span>
                    </li> */}
                    <li>
                        <label htmlFor="">数据日期：</label>
                        <span>{startTime}</span>
                    </li>
                    <li>
                        <label htmlFor="">来源渠道：</label>
                        <span>{sourceChannelTypeText}</span>
                    </li>
                    {/* <li>
                        <label htmlFor="">数据来源：</label>
                        <span>{dataSources}</span>
                    </li> */}
                    {/* <li>
                        <label htmlFor="">区域维度：</label>
                        <span>{areaLatitudeTypeText}</span>
                    </li> */}
                    <li>
                        <label htmlFor="">地理区域：</label>
                        <span>{regionName}</span>
                    </li>
                    <li>
                        <label htmlFor="">名称：</label>
                        <span>{name}</span>
                    </li>
                    <li>
                        <label htmlFor="">作物种类：</label>
                        <span>{cropTypeText}</span>
                    </li>
                    <li>
                        <label htmlFor="">作物品种：</label>
                        <span>{strainsText}</span>
                    </li>
                    <li>
                        <label htmlFor="">作物总面积：</label>
                        <span>{cropTotalArea}{cropTotalAreaText}</span>
                    </li>
                    <li>
                        <label htmlFor="" style={{ fontSize: '12px' }}>作物挂果面积：</label>
                        <span>{cropFruitArea}{cropFruitAreaText}</span>
                    </li>
                    <li>
                        <label htmlFor="" style={{ fontSize: '12px' }}>作物嫁接面积：</label>
                        <span>{cropGraftArea}{cropGraftAreaText}</span>
                    </li>
                    <li className="doubleLine">
                        <label htmlFor="">未来嫁接大树面积：</label>
                        <span>{ngraftedTreeArea}{ngraftedTreeAreaText}</span>
                    </li>
                    <li className="doubleLine">
                        <label htmlFor="">未来嫁接幼苗面积：</label>
                        <span>{ngraftedYoungtreeArea}{ngraftedYoungtreeAreaText}</span>
                    </li>
                    <li className="doubleLine">
                        <label htmlFor="">海拔1500米以下土地面积：</label>
                        <span>{landArea}{landAreaText}</span>
                    </li>
                    <li className="doubleLine">
                        <label htmlFor="">海拔1500米以下准备种植品种面积：</label>
                        <span>{growArea}{growAreaText}</span>
                    </li>
                    <li>
                        <label htmlFor="">拟发展品种：</label>
                        <span>{developVariety}</span>
                    </li>
                    <li className="item-line">
                    </li>
                    <li className="item">
                        <label htmlFor="">审核人：</label>
                        <Input style={{ width: '65%' }} defaultValue="" />
                    </li>
                    <li className="item">
                        <label htmlFor="">审核意见：</label>
                        <Input  onChange={this.inputHandle} data-type="auditerSuggestion" style={{ width: '65%' }} defaultValue="" />
                    </li>
                    <li className="item">
                        <label htmlFor="">审核结果：</label>
                        <Select onChange={this.handleSelect} value={auditStatusText} style={{ width: '65%' }} >
                            <Option codenum={'auditStatusCode'} typename={'auditStatusText'} value="1">通过</Option>
                            <Option codenum={'auditStatusCode'} typename={'auditStatusText'} value="2">不通过</Option>
                        </Select>
                    </li>
                    <li>
                        <Button onClick={this.formSaveFn} type="primary">上报</Button>
                        <Button onClick={this.goBackPage}>取消</Button>
                    </li>
                </ul>
            </div>
        )
    }
}