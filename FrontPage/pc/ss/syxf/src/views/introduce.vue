<template>
  <div class="c1 fr">
     <img class="top-img" src="../assets/img/p5.jpg"/>
     <div class="sub-title">
       <span class="title">联系我们</span>
     </div>
     <ul class="str">
       <li class="list">公司名称：华坪县尚农农业科技开发投资有限责任公司</li>
       <li class="list">公司地址：云南省丽江市华坪县中心镇柳溪社区1组</li>
       <li class="list">邮政编码：674899</li>
       <li class="list">联系电话：0888-6121708</li>
       <li class="list">电子邮箱：hpxsngs@163.com</li>
     </ul>
  </div>
</template>
<script>
  export default {
    name: 'Introduce'
  }
</script>

<style lang="scss" scoped>
.c1{
    min-width: 1020px;
    min-height:calc(110vh - 180px);
    //background: greenyellow;
    width: 900px;
}
.top-img{
    width: 1020px;
    height: 240px;
    margin-bottom: 1px;
}
.sub-title{
    font-size: 16px;
    color: #49a9ee;
    line-height: 24px;
    padding-left: 20px;
    border-bottom: 1px solid #49a9ee;
    background: url(../assets/img/titlt.png) no-repeat left center;
    margin: 0 auto;
    padding-top: 5px;
}
.str{
    padding-top: 25px;
    margin-top: 30px;
    .list{
      font-size: 14px;
      color: #5e5e5e;
      margin-top: 10px;
    }
}
</style>

