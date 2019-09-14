// 影响因素
let FactorBarData = {
    legendData: ['干旱', '冻害', '大风', '暴雨'],
    yName:'次',
    xAxisData: ['花芽分化期',"开花盛花期","幼果、生理落果期","果实膨大期", "果实成熟期"],
    seriesData: {
            dryData:[12, 24, 22, 13,16],
            coldData:[32, 20, 35, 12,15],
            blowingData:[32, 23,20, 12,10],
            rainData:[22, 26, 25, 28,19]
        }
}
//  灾害类型预警走势
let WarningLineData = {
    legendData: ['长期趋势'],
    xAxisData: ['2010', '2011 ', '2012', '2013', '2014', '2015', '2016', '2017'],
    seriesData:[24.8, 29.6, 24.1, 26.3, 26.4, 24.8, 29.6, 24.1]
}
//灾害类型预警信息
let AdisasterTableData = {
    data:[
            {name:'暴雨',date:'2011-05-11'},
            {name:'大风',date:'2012-06-15'},
            {name:'干旱',date:'2013-05-16'},
            {name:'冻害',date:'2014-04-26'},
            {name:'暴雨',date:'2015-05-06'},
            {name:'大风',date:'2016-05-16'}
        ]
}
export { FactorBarData, WarningLineData,AdisasterTableData};