//广告图
function getimgBanner() {
  var submitData = {
    "entityRelated": {
      "domainId": "032769fd7e376c04fb13c66419a72598",
      "spaceCode": "3"
    },
    "page": {
      "pageIndex": 1,
      "pageSize": 1
    }
  };
  ajaxGet("mango/extend/swagger/pi/piAdvertising/getListByCode", submitData, rendomImg);
}
getimgBanner();
//渲染广告位
function rendomImg(val) {
  var html = '';
 // console.log(val)
  if (val.isSuccess) {
    //标题部分
    var targets = val.data.targetCode == 2 ? '_blank' : '_self';
    var width = val.data.width ? val.data.width : '100%';
    var height = val.data.height ? val.data.height : '500px';
    var imgTitle = val.data.title ? val.data.title : '';
    html +=
    '<a href="###'
   // + val.data.targetUrl + '" target="' + targets
    + '">'
       +'<img  style="width:' + width + ';"   src="' + val.data.resourcePathArray[0] + '" title="' + imgTitle + '">'
       + '</a>';
    $(".slider-play").html(html);
  } else {
    $(".slider-play").html('<div class="noData">暂无数据</div>');
  }
}
$(".slider-play").click(function () {
  getimgBanner();
})
//  种植初始化
initPlant();

function initPlant() {
  var params = {
    "entityRelated": {
      "regionId": "530723",
      "breed": "0"
    },
    "orderList": [{
      "columnName": "",
      "isASC": true
    }],
    "page": {
      "pageIndex": 1,
      "pageSize": 10
    }
  }
  ajaxGet('mango/extend/swagger/da/daGrowYield/getMangoGrowDetails', params, initPlantFn);

}
//  种植回调
function initPlantFn(res) {
  //console.log(res)
  if (res.isSuccess) {
    initTemplate({
      data: res.data,
      idScript: 'tpl',
      idHtml: 'tendency',
    });
  }
}

//  品质初始化
initQuality();

function initQuality() {
  ajaxGet('mango/extend/swagger/da/daIdentificationInfo/getListByJsonData', {}, initQualityFn);
}
//  品质回调
function initQualityFn(res) {
  console.log(res)
  if (res.isSuccess) {
    var seriesData = [],
      regionsData = [];
    res.data.entNameList.forEach(function (v, i) {
      regionsData[i] = {
        name: v.regionName,
        itemStyle: {
          areaColor: {
            color: '#d5ffab',
            borderColor: '#a2d6f3',
            borderWidth: 1
          }
        }
      };
      seriesData[i] = {
        name: v.name,
        value: [v.measureLongitude, v.measureLatitude]
      }
    });
    //  三品一标品质保障
    initTemplate({
      data: res.data,
      idScript: 'tpl-quality',
      idHtml: 'quality',
    });

    //  华坪地图初始化
    cityMap({
      el: '.city-map',
      seriesData: seriesData
    })
  }
}