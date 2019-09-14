<template>
  <div>
    <div class="picture">
       <img class="top-img" src="../assets/img/p1.jpg" />
    </div>
    <div class="box">
      <span class="sub-title">公司介绍</span>
       <div class="box1">
         <p class="txt-box">华坪县尚农农业科技开发投资有限责任公司（以下简称公司）成立于2017年5月，公司位于华坪县中心镇华兴社区迎宾路46号，公司注册资本为人民币一亿元。是经华坪县人民政府批准并出资设立的国有独资公司，华坪县人民政府授权国有资产监督管理机构履行出资人职责，公司隶属于华坪县农业局。根据公司章程相关规定，设执行董事兼总经理一名，监事两名，副经理两名，下设综合行政部、资金财务部、信息技术部、工程技术部、营销投资部五个部门。现有人员10名。公司经营范围：受县人民政府委托的农田水利基础设施建设；农业项目申报及投融资业务；农业农村基础设施和农业产业园区投资、建设及运营；投资特色农业产业发展及农产品深加工；农用物资及农产品开发及销售、农业农村资产管理及投资；农业技术研发、推广及转让；农产品的仓储；农业大数据平台建设；工程信息咨询，认证咨询，商务信息咨询，行业综合化解决方案咨询，市场调查等；水果、蔬菜、药材（国家允许种植的）、食用菌及农副产品收购、销售；经济林果种植；进出口贸易；电子商务；其他经批准的业务。</p>
       </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Contact'
  }
</script>

<style lang="scss" scoped>
.picture{
  margin-bottom: 40px;
}
.top-img{
    width:1020px;
    height: 240px;
    margin-bottom: 1px;
}
.sub-title{
    width: 98%;
    position: relative;
    text-align: left;
    font-size: 16px;
    color: #49a9ee;
    line-height: 25px;
    padding-left: 20px;
    border-bottom: 1px solid #49a9ee;
    background: url(../assets/img/titlt.png) no-repeat left center;
    margin-bottom: 5px;
    display: inline-block;
}

.box{
    width:1020px;
    .txt-box{
     line-height: 30px;
     text-indent:2em;
     text-indent: 30px;
     font-size: 14px;
     color: #5e5e5e;
     width: 1020px;
     padding-top: 20px;
   }
}
</style>