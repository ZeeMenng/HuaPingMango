// 供求现状
let ActualityData = {
  xAxisData: ['2007', "2008", "2009", "2010", "2011"],
  seriesData: [
    [200, 300, 100, 330, 350],
    [500, 100, 400, 300, 600],
    [10, 50, 80, 70, 70],
    [50, 30, 70, 50, 20]
  ]
};
// 供求预测
let PredictionData = {
  xAxisData: [],
  seriesData: [[],[]]
};
//  产销价差

let MakeSalesData =  {
    legendData: [],
    xAxisData: [],
    seriesData: []
}

// 鲜芒果不同品种产销率监测
let MonitorLineData = {
  xAxisData: ['1月','2月 ','3月','1月','2月','3月','4月','5月','6月','7月','8月','9月'],
  seriesData: [22, 26, 25, 28, 20, 26, 22, 24, 23, 26, 21,22]
};

export {
  ActualityData,
  PredictionData,
  MonitorLineData,
    MakeSalesData
};