import React from 'react';

//引入接口文件
import * as api from '../api/api-safety-TalkMonitoring';
import { Carousel } from 'antd';
//引入css样式
import TalkMonitoringClass from './TalkMonitoring.scss';
import Select from '../../component/web-team/select/Select';
//title 组件
import Title from '../../component/visual-team/title/Title';
import TalkLine from '../../component/web-team/production-test/talkLine';/*情感分析组件*/
import TalkBar from '../../component/web-team/production-test/talkBar';
import TalkPie from '../../component/web-team/production-test/talkPie';
import { MediaData, MediaTypeData, FeelSpreadData, NegativeMediaData, MediaListData } from "./talkData";
/*import { LocaleProvider } from 'antd';
import zh_CN from 'antd/lib/locale-provider/zh_CN';*/
import 'moment/locale/zh-cn';
import { Radio, DatePicker, Modal } from 'antd';/*单选按钮组件*/
//引入阿里云字体库
require('../planting/component/font/iconfont.js');
require('../planting/component/font/iconfont.css');

const RadioGroup = Radio.Group;
const { RangePicker } = DatePicker;
function onChange (value, dateString) {
    //console.log('Selected Time: ', value);
    //console.log('Formatted Selected Time: ', dateString);
}
function onOk (value) {
    //console.log('onOk: ', value);
}
/**
 * 质量安全--舆情监测
 */
class TalkMonitoring extends React.Component {
    constructor(props) {
        super(props);
        let me = this;
        this.state = {
            /* MediaData: MediaData,
             MediaTypeData: MediaTypeData,
             FeelSpreadData: FeelSpreadData,
             NegativeMediaData: NegativeMediaData,
             MediaListData: MediaListData,*/
            NewPublicData: [],
            NegativePublicData: [],
            nameArr: [],
            valueArr: [],
            keyArr: [],
            visible: false,
            nameOne: "",
            RadioCodeFeel: '3',
            RadioCodeMediatype: '3',
            RadioCodeFeelspread: '3',
            RadioCodeNegative: '3',
            RadioCodeMedialist: '3',
            ver: ''
        }
    }

    _tokens = [];
    _clearTokens () {
        this._tokens.forEach(token => token.cancel());
        this._tokens = [];
    };
    //初始化页面
    _initPage = () => {
        this.feelChange();//情感分析
        this.mediatypeChange();//媒体类型覆盖
        this.feelspreadChange();//情感分布
        this.negativeChange();//负面信息来源
        this.medialistChange();//媒体来源排行
        this.newPublic();
        this.negativePublic();
    }
    //主题名称
    _pullDownMes = (a) => {
        this.setState({
            nameOne: a.value
        }, () => {
            this._initPage()
        });

    };
    //情感分析
    _feelChange = (a) => {
        //console.log(a)
        this.setState({
            RadioCodeFeel: a.target.value
        }, () => {
            this.feelChange()
        });

    };
    //媒体类型覆盖
    _mediatypeChange = (a) => {
        //console.log(a)
        this.setState({
            RadioCodeMediatype: a.target.value
        }, () => {
            this.mediatypeChange()
        });

    };
    //情感分布
    _feelspreadChange = (a) => {
        // console.log(a)
        this.setState({
            RadioCodeFeelspread: a.target.value
        }, () => {
            this.feelspreadChange()
        });

    };
    //负面信息来源
    _negativeChange = (a) => {
        this.setState({
            RadioCodeNegative: a.target.value
        }, () => {
            this.negativeChange()
        });

    };
    //媒体来源排行
    _medialistChange = (a) => {
        this.setState({
            RadioCodeMedialist: a.target.value
        }, () => {
            this.medialistChange()
        });

    }
    //主题名称
    pullDownMes = () => {
        let motifNameParams = {
            jsonData: {
                "entityRelated": { "statusCode": "2" },
                "orderList": [{ "columnName": "addTime", "isASC": false }],
                "page": { "pageIndex": 1, "pageSize": 100 }
            }
        };
        api.motifName.send(motifNameParams).then((res) => {
            //console.log(res.data[0].id)
            if (res.isSuccess) {
                let nameArr = [], valueArr = [], keyArr = [];
                res.data.map((item, index) => {
                    nameArr[index] = item.themeName;
                    valueArr[index] = item.id;
                    keyArr[index] = item.keyName;
                });
                this.setState({
                    nameArr: nameArr,
                    valueArr: valueArr,
                    keyArr: keyArr,
                    nameOne: res.data[0].id
                }, () => {
                    this._initPage()
                });
                // console.log(this.state.valueArr)
            } else {
                this.setState({
                    nameArr: [],
                    valueArr: [],
                    keyArr: [],
                    nameOne: []
                });
            }
        })

    };
    //情感分析
    feelChange = () => {
        let FeelAnalysisParams = {
            jsonData: {
                "entityRelated": {
                    "themeId": this.state.nameOne,
                    "timeTypeCode": this.state.RadioCodeFeel
                },
                "orderList": [{
                    "columnName": "publish_time",
                    "isASC": true
                }],
                "page": {
                    "pageIndex": 1,
                    "pageSize": 10
                }
            }
        }
        api.getFeelAnalysis.send(FeelAnalysisParams).then((res) => {
            // console.log(res.data)
            if (res.isSuccess) {
                this.setState({
                    MediaData: {
                        xAxisData: res.data[0].times,
                        legendData: ['负面', '中立', '正面'],
                        seriesData: [res.data[1].sentimentTypeCode1, res.data[1].sentimentTypeCode2, res.data[1].sentimentTypeCode3]
                    }
                })
            } else {
                []
            }
        })
    }
    //媒体类型覆盖
    mediatypeChange = () => {
        let mediaParams = {
            jsonData: {
                "entityRelated": {
                    "themeId": this.state.nameOne,
                    "timeTypeCode": this.state.RadioCodeMediatype,
                    "sentimentTypeCode": "1"
                },
                "orderList": [{
                    "columnName": "publish_time",
                    "isASC": true
                }],
                "page": {
                    "pageIndex": 1, "pageSize": 10
                }
            }
        }
        api.getMediaType.send(mediaParams).then((res) => {
            //console.log(res)
            if (res.isSuccess) {
                let mediaArr = [];
                res.data.map((item) => {
                    //console.log(item)
                    let medianum = item.num.replace("%", "");
                    mediaArr.push({
                        value: medianum,
                        name: item.mediaTypeText,
                    })
                });
                //console.log(mediaArr)
                this.setState({
                    MediaTypeData: {
                        seriesData: mediaArr,
                        pieColor: ['#167ffd', '#068bff', '#05c3ff', '#17e9fb', '#39ddcd', '#23ec8c', '#25e943', '#fbda3e', '#facb3d', '#fa993c'],
                    }

                })
            } else {
                []
            }
        })
    };
    //情感分布
    feelspreadChange = () => {
        let feelSpreadParams = {
            jsonData: {
                "entityRelated": {
                    "themeId": this.state.nameOne,
                    "timeTypeCode": this.state.RadioCodeFeelspread,
                    "sentimentTypeCode": "1"
                },
                "orderList": [{
                    "columnName": "publish_time",
                    "isASC": true
                }],
                "page": {
                    "pageIndex": 1,
                    "pageSize": 10
                }
            }
        };
        api.feelSpread.send(feelSpreadParams).then((res) => {
            // console.log(res)
            if (res.isSuccess) {
                let f1 = res.data.sentimentTypeCode1.replace("%", "");
                let f2 = res.data.sentimentTypeCode2.replace("%", "");
                let f3 = res.data.sentimentTypeCode3.replace("%", "");
                let feel = [
                    { value: f2, name: '中性' },
                    { value: f3, name: '正面' },
                    { value: f1, name: '负面' },
                ];
                this.setState({
                    FeelSpreadData: {
                        seriesData: feel,
                        pieColor: ['#25e943', '#17e9fb', '#fbda3e'],
                    }
                })
            } else {
                []
            }
        })
    };
    //负面信息来源
    negativeChange = () => {
        let negativeParams = {
            jsonData: {
                "entityRelated": {
                    "themeId": this.state.nameOne,
                    "timeTypeCode": this.state.RadioCodeNegative,
                    "sentimentTypeCode": "1"
                },
                "orderList": [{ "columnName": "publish_time", "isASC": true }],
                "page": { "pageIndex": 1, "pageSize": 10 }
            }
        };
        api.negativeMedia.send(negativeParams).then((res) => {
            let negativeArr = [];
            if (res.isSuccess) {
               console.log(res)
                res.data.map((item) => {
                    //console.log(item)
                    negativeArr.push({
                        value: item.num,
                        name: item.threadStarter
                    })
                });
                this.setState({
                    NegativeMediaData: {
                        seriesData: negativeArr,
                        pieColor: ['#167ffd', '#068bff', '#05c3ff', '#17e9fb', '#39ddcd', '#23ec8c', '#25e943', '#fbda3e', '#facb3d', '#fa993c'],
                    }
                })
            } else {
                this.setState({
                    NegativeMediaData: {
                        seriesData: [{value:' ',name:'无数据'}],
                        pieColor: ['#167ffd', '#068bff', '#05c3ff', '#17e9fb', '#39ddcd', '#23ec8c', '#25e943', '#fbda3e', '#facb3d', '#fa993c'],
                    }
                })
            }
        })
    };
    /*媒体来源排行*/
    medialistChange = () => {
        let MediaListParmas = {
            jsonData: {
                "entityRelated": {
                    "themeId": this.state.nameOne,
                    "timeTypeCode": this.state.RadioCodeMedialist
                },
                "orderList": [{
                    "columnName": "num",
                    "isASC": false
                }],
                "page": {
                    "pageIndex": 1, "pageSize": 10
                }
            }
        };
        api.MediaList.send(MediaListParmas).then((res) => {
            //console.log(res);
            if (res.isSuccess) {
                let ress = res.data;
                let xData = [];
                let serseData = [];
                ress.list.map((item) => {
                    xData.push(item.threadStarter);
                    serseData.push(item.num)

                });
                //console.log(xData,serseData)
                this.setState({
                    MediaListData: {
                        xAxisData: xData,
                        seriesData: serseData,
                    }
                })
            } else {
                []
            }
        })
    }
    //最新舆情
    newPublic = () => {
        let newParmas = {
            jsonData: {
                "entityRelated": {
                    "themeId": this.state.nameOne,
                    "timeTypeCode": "5"
                },
                "orderList": [
                    {
                        "columnName": "publishTime",
                        "isASC": false
                    }],
                "page": {
                    "pageIndex": 1,
                    "pageSize": '30'
                }
            }
        };
        api.negativePublic.send(newParmas).then((res) => {
            if (res.isSuccess) {
                // console.log(res)
                this.setState({
                    NewPublicData: res.data
                })
            } else {
                []
            }
        });
    }
    //负面舆情
    negativePublic = () => {
        //console.log(this.state.nameOne)
        let negativeParmas = {
            jsonData: {
                "entityRelated": {
                    "themeId": this.state.nameOne,
                    "timeTypeCode": "5",
                    "sentimentTypeCode": "1"
                },
                "orderList": [
                    {
                        "columnName": "publishTime",
                        "isASC": false
                    }],
                "page": {
                    "pageIndex": 1,
                    "pageSize": '30'
                }
            }
        };
        api.negativePublic.send(negativeParmas).then((res) => {
            if (res.isSuccess) {
                let data = res.data;
                console.log(typeof data[0].txt)
                this.setState({
                    NegativePublicData: data
                })
            } else {
                []
            }
        })

    }
    //弹窗部分
     showModal = (a) => {
        //console.log(a)
        this.setState({
            visible: true,
            txt: (a.item.txt).replace("\n", "\\n"),
            articleName: a.item.articleName,
            threadStarter: a.item.threadStarter,
            publishTime: a.item.publishTime,
            url: a.item.url,
        });
    }

    model (a) {
        this.showModal(a);

    }
    trick () {
        this.setState(); //改变组件的状态
    }

    componentDidMount () {
        this.pullDownMes();//主题
        this.timer = setInterval(
            () => this.trick(),
            60000
        );
        /* this.feelChange(); //情感分析饼图
        this.mediatypeChange(); //媒体类型覆盖
        this.feelspreadChange(); //情感分布
        this.negativeChange(); //负面信息来源
        this.medialistChange(); //媒体来源排行
        this.newPublic();//最新舆情
        this.negativePublic();//负面舆情*/
    }

    render () {
        const me = this;
        //const html = this.state.txt.replace("\\n","\n\n\n");
        return (
            <div className="TalkMonitoringClass">
                <div className="mainName">
                    主题名称：
                    <Select _pullDownMes={this._pullDownMes}
                        nameArr={me.state.nameArr}
                        keyArr={me.state.keyArr}
                        valueArr={me.state.valueArr}
                        style={{
                            width: '152px',
                        }} />
                </div>
                <div className="feelTop">
                    {/*情感分析*/}
                    <div className="feelAnalysis">
                        <div className="chart-title-wrap">
                            <Title content={'情感分析'} top={'0.1rem'} />
                            <div className="mediaDate">
                                <RadioGroup name="radiogroup" onChange={this._feelChange} defaultValue={3} >
                                    <Radio value={5} style={{ color: "white", width: '77px' }}>24h</Radio>
                                    <Radio value={4} style={{ color: "white", width: '77px' }}>一周</Radio>
                                    <Radio value={3} style={{ color: "white", width: '77px' }}>一月</Radio>
                                </RadioGroup>
                            </div>
                        </div>
                        <div className="echarts-wrap">
                            <TalkLine data={this.state.MediaData} />
                        </div>
                    </div>
                    {/*媒体类型覆盖*/}
                    <div className="mediaType">
                        <div className="chart-title-wrap">
                            <Title content={'媒体类型覆盖'} top={'0.1rem'} />
                            <div className="mediaDate">
                                <RadioGroup name="radiogroup" onChange={this._mediatypeChange} defaultValue={3}>
                                    <Radio value={5} style={{ color: "white", width: '70px' }}>24h</Radio>
                                    <Radio value={4} style={{ color: "white", width: '70px' }}>一周</Radio>
                                    <Radio value={3} style={{ color: "white", width: '70px' }}>一月</Radio>
                                </RadioGroup>
                            </div>
                        </div>
                        <div className="echarts-wrap">
                            <TalkPie data={me.state.MediaTypeData} />
                        </div>
                    </div>
                </div>
                <div className="feelMid">
                    {/*情感分布*/}
                    <div className="feelSpread">
                        <div className="chart-title-wrap">
                            <Title content={'情感分布'} top={'0.1rem'} />
                            <div className="mediaDate">
                                <RadioGroup name="radiogroup" onChange={this._feelspreadChange} defaultValue={3}>
                                    <Radio value={5} style={{ color: "white", width: '70px' }}>24h</Radio>
                                    <Radio value={4} style={{ color: "white", width: '70px' }}>一周</Radio>
                                    <Radio value={3} style={{ color: "white", width: '70px' }}>一月</Radio>
                                </RadioGroup>
                            </div>
                        </div>
                        <div className="echarts-wrap">
                            <TalkPie data={this.state.FeelSpreadData} />
                        </div>
                    </div>
                    {/*负面信息媒体来源*/}
                    <div className="negativeMedia">
                        <div className="chart-title-wrap">
                            <Title content={'负面信息媒体来源'} top={'0.1rem'} />
                            <div className="mediaDate">
                                <RadioGroup name="radiogroup" onChange={this._negativeChange} defaultValue={3} >
                                    <Radio value={5} style={{ color: "white", width: '70px' }}>24h</Radio>
                                    <Radio value={4} style={{ color: "white", width: '70px' }}>一周</Radio>
                                    <Radio value={3} style={{ color: "white", width: '70px' }}>一月</Radio>
                                </RadioGroup>
                            </div>
                        </div>

                        <div className="echarts-wrap">
                            <TalkPie data={this.state.NegativeMediaData} />
                        </div>

                    </div>
                    {/*媒体来源排行*/}
                    <div className="mediaList">
                        <div className="chart-title-wrap">
                            <Title content={'媒体来源排行'} top={'0.1rem'} />
                            <div className="mediaDate">
                                <RadioGroup name="radiogroup" onChange={this._medialistChange} defaultValue={3} >
                                    <Radio value={5} style={{ color: "white", width: '70px' }}>24h</Radio>
                                    <Radio value={4} style={{ color: "white", width: '70px' }}>一周</Radio>
                                    <Radio value={3} style={{ color: "white", width: '70px' }}>一月</Radio>
                                </RadioGroup>
                            </div>
                        </div>

                        <div className="echarts-wrap">
                            <TalkBar data={this.state.MediaListData} />
                        </div>
                    </div>
                </div>
                <div className="feelBottom">
                    {/*最新舆情*/}
                    <div className="newPublic">
                        <div className="chart-title-wrap">
                            <Title content={'最新舆情'} top={'0.1rem'} />
                        </div>
                        <ul>
                            {/*autoplayInterval={1000}*/}
                            <Carousel autoplay vertical dots="false">
                                {
                                    this.state.NewPublicData.map(function (item, index) {
                                        //console.log(item)
                                        var className = "positive";
                                        if (item.sentimentTypeCode == 1) {
                                            className = "negative";
                                        }
                                        if (item.sentimentTypeCode == 2) {
                                            className = "neutral";
                                        }
                                        return (
                                            <li key={index} onClick={me.showModal.bind(this, { item })} >
                                                <a className="negativeName">{item.articleName}</a>
                                                <span className={className}>{item.sentimentTypeText}</span>
                                            </li>
                                        )
                                    })
                                }
                            </Carousel>
                        </ul>
                    </div>
                    {/*负面舆情*/}
                    <div className="negativePublic">
                        <div className="chart-title-wrap">
                            <Title content={'负面舆情'} top={'0.1rem'} />
                        </div>
                        <ul className="negative-ul">
                            <Carousel autoplay vertical dots="false">
                                {
                                    this.state.NegativePublicData.map((item, index) => {
                                        //this.state.text = item.txt;
                                        if (item.sentimentTypeCode == 1) {
                                            return (
                                                <li key={index} onClick={me.showModal.bind(this, { item })} >
                                                    <a className="negativeName">{item.articleName}</a>
                                                    <span className="negative">{item.sentimentTypeText}</span>
                                                </li>)
                                        }
                                    })
                                }
                            </Carousel>
                        </ul>
                        <Child url={this.state.url} publishTime={this.state.publishTime} txt={this.state.txt} ver={this.state.visible} articleName={this.state.articleName} threadStarter={this.state.threadStarter}/>
                    </div>
                </div>
            </div>
        )
    }
    componentWillUnmount () {
        this._clearTokens();
        this.flagError = false;
        //组件从页面卸载前执行的方法
        clearInterval(this.timer);

    }

}
class Child extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            visible: false
        }
    }
    handleOk = (e) => {
        //console.log(e);
        this.setState({
            visible: "",
        });
    }
    handleCancel = (e) => {
        this.setState({
            visible: false,
        });
    }
    componentWillReceiveProps(nextProps) {
        this.setState({
            visible: nextProps.ver
        })
    }
    render() {
      const {visible} = this.state
      const txts = this.props.txt ? (this.props.txt).replace("\n", "<br/>") : '';
      return <Modal
        visible={visible}
        onOk={this.handleOk}
        onCancel={this.handleCancel}
        afterClose={this.afterClose}
        width={'1300px'}
        title={null}
        footer={null}
        className={'adialogBoxS'}
        bodyStyle={{ height: "530px", overflow: 'hidden' }}
        overflowY={"hidden"}
    >
      <div style={{ letterSpacing: "1px", wordWarp: "break-word", fontSize: "18px", height: "450px", marginTop: "30px", padding: "0px 70px 120px 80px", lineHeight: "35px" }}>
          <a className={'publicTxtname'} href={this.state.url} target="_blank" style={{ fontSize: "24px" }}> {this.props.articleName}</a>
          <p className={'publicFrom'}>来源：<a href={this.state.url} target="_blank">{this.props.threadStarter}</a><span>{this.props.publishTime}</span></p>
          <div style={{ background: "transparent", border: "none", width: "100%", height: "100%", overflow: 'auto' }}  dangerouslySetInnerHTML={{__html: `${txts}`}}>

          </div>
      </div>
  </Modal>
    }
}
export default TalkMonitoring;
