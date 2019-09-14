//首页头部指标的数据
const DataNum = [
    { name: '直报数据总量', num: '50', unit: '万条' },
    { name: '数据指标总量', num: '200', unit: '个' },
    { name: '指标数据', num: '30', unit: '万条' },
    { name: '图片', num: '8', unit: '万张' },
    { name: '文档', num: '8', unit: '万个' },
    { name: '视频', num: '3', unit: '万个' },
    { name: '关键词', num: '2', unit: '个' },
    { name: '主题', num: '50', unit: '个' },
    { name: '数据', num: '5', unit: '万条' },
    { name: '数据资源', num: '20', unit: '个' },
]
//首页底部右侧数据
const DataType = [
    {name:'指标数据',value:'178'},
    {name:'舆情数据',value:'158'},
    {name:'文档数据',value:'138'},
    {name:'图片数据',value:'128'},
    {name:'视频数据',value:'178'},
    {name:'主题数据',value:'98'},
    {name:'产地数据',value:'38'},
    {name:'环境数据',value:'55'},
    {name:'认证数据',value:'68'},
    {name:'生产数据',value:'88'},
    {name:'销售数据',value:'58'},
    {name:'产业数据',value:'18'},
    {name:'价格数据',value:'78'}
]
const allData={
    xAxisData:['1月','2月 ','3月','1月','2月'],
    seriesData:[22, 26, 25, 28, 20],
}
const dayData={
    xAxisData: ['2017-6-21','2017-7-01', '2017-7-06', '2017-7-11', '2017-7-16', '2017-7-21', '2017-7-26', '2017-7-31'],
    seriesData:[120,130,95,124,120,130,95,124],
}
const sourceData={
    yAxisData:['第三方对接采集','政府采集','网络采集','app上报','web上报','其他'],
    seriesData:[35,25,15,13,12,10]
}
const areaData={
    xAxisData:['企业数据','农户数据 ','乡镇数据','区数据','村/组/社区数据','全国数据','全球数据'],
    seriesData:[2,10,1,15,2,4,1],
}
const columnsTab = [{
    title: '所属主题',
    dataIndex: '0',
},
    {
        title: '文章标题',
        dataIndex: '1',
    },
    {
        title: '数据来源',
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
    },];
export {
    DataNum,
    DataType,
    allData,
    dayData,
    sourceData,
    areaData
};