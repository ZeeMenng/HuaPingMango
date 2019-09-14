<template>
  <div class="content">
    <div class="search-input">
      <div class="search-content">
        <el-input placeholder="溯源码" v-model="input5" class="input-with-select">
          <el-button slot="append" icon="el-icon-search" @click="searchBtn">溯源查询</el-button>
        </el-input>
      </div>
    </div>
    <h3 class="search-title">溯源查询方法</h3>
    <div class="search-wrap">
      <ul class="search-container clearfix">
        <li v-for="(item, $index) in lists" :key="$index" @click="handleClick($index)" :class="{ active : myIndex === $index }">
          <div :class="mYclass + $index" class="sIcon"></div>
          <div>{{item.name}}</div>
        </li>
      </ul>
      <div class="stails">
        <div :class="'st' + myIndex"></div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      input5: '',
      mYclass: 'sic',
      myIndex: 0,
      lists: [
        {name: '网上查询', url: require('../assets/img/steps.png')},
        {name: '微信扫码查询', url: require('../assets/img/steps.png')},
        {name: 'APP查询', url: require('../assets/img/steps.png')}
      ],
      cLists: [
        {name: '生产信息'},
        {name: '加工信息'},
        {name: '仓储信息'},
        {name: '物流流向'},
        {name: '检验检测'},
        {name: '物联网监测信息'}
      ]
    }
  },
  methods: {
    handleClick (val) {
      this.myIndex = val
    },
    searchBtn () {
      let dest = /^HP[0-9A-Z]{4}[12]\d{3}[\d]{10}$/
      if (dest.test(this.input5)) {
        this.$router.push({path: '/searchDetail', query: {traceabilityCode: this.input5}})
      } else {
        this.$alert('请输入正确的溯源码', '提示', {
          confirmButtonText: '确定'
        })
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.content{
  min-width: 1280px;
  .search-input{
    height: 200px;
    padding-top: 150px;
    text-align: center;
    background: url('../assets/img/bg.png') no-repeat;
    background-size: cover;
    background-position: center;
    .search-content{
        margin-top: 30px;
        width: 531px;
        height: 40px;
        display: inline-block;
        
    }
  }
  .search-wrap{
    width: 1200px;
    margin: 0 auto;
  }
  .search-title{
    font-size: 16px;
    line-height: 54px;
    font-weight: 600;
    color: rgb(16, 142, 233);
    text-align: left;
    padding-left: 25px;
    background: url('../assets/img/titlt.png') 0px center no-repeat;
    background-position: 0px center;
    width: 1175px;
    margin: 0 auto;
  }
  .search-container{
    box-sizing: border-box;
    background-color: rgb(255, 255, 255);
    text-align: center;
    margin-bottom: 20px;
    li{
      position: relative;
      cursor: pointer;
      display: inline-block;
      box-sizing: border-box;
      float: left;
      margin-right: 172px;
      width: 285px;
      height: 190px;
      background-color: rgb(241, 241, 241);
      border-width: 1px;
      border-style: solid;
      border-color: rgb(204, 204, 204);
      border-image: initial;
      border-radius: 5px;
      .sIcon{
        background: url('../assets/img/steps.png') no-repeat;
        height: 150px;
      }
      .sic0{
        background-position: 30px 0;
      }
      .sic1{
        background-position: -215px 0px;
      }
      .sic2{
        background-position: -944px 0px;
      }
    }
    .active{
      position: relative;
      box-shadow: rgb(73, 169, 238) 0px 0px 5px 2px;
      border-width: 1px;
      border-style: solid;
      border-color: rgb(73, 169, 238);
      border-image: initial;
      :after{
        content: "";
        position: absolute;
        width: 0px;
        height: 0px;
        bottom: -22px;
        left: 46%;
        border-width: 8px;
        border-style: solid;
        border-color: transparent transparent rgb(73, 169, 238);
        border-image: initial;
      }
    }
    li:last-child {
      margin-right: 0px;
    }
  }
  .stails {
    height: 246px;
    margin: 0 auto;
    border-width: 2px 1px 1px;
    border-style: solid;
    border-color: rgb(73, 169, 238) rgb(204, 204, 204) rgb(204, 204, 204);
    border-image: initial;
    border-top: 2px solid rgb(73, 169, 238);
    padding: 20px;
    margin-bottom: 30px;
    .st0{
      height: 210px;
      width: 100%;
      background: url('../assets/img/netSearch.png') 50% center no-repeat;
    }
    .st1 {
      height: 210px;
      width: 100%;
      background: url('../assets/img/scanCodeSearch.png') 50% center no-repeat;
    }
    .st2 {
      height: 210px;
      width: 100%;
      background: url('../assets/img/appSearch.png') 50% center no-repeat;
    }
  }
}
</style>