import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home'
import About from './views/About'
import Proposal from './views/proposal'
import Search from './views/search'
import SerDetarl from './views/searchDetail'
import Crops from './views/Croprecords'
import Process from './views/Processing'
import Enterpri from './views/Enterpriseinformation'
import Inspection from './views/Inspection'
import Information from './views/Monitoringinformation'
import Logistics from './views/Logistics'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '首页',
      component: Home
    },
    {
      path: '/searchDetail',
      component: SerDetarl,
      name: '溯源查询详情',
      hidden: true
    },
    {
      path: '/search',
      component: Search,
      name: '溯源查询'
    },
    {
      path: '/proposal',
      name: '投诉建议',
      component: Proposal
    },
    {
      path: '/about',
      name: '关于我们',
      component: About
    },
    {
      path: '/croprecords',
      component: Crops,
      name: '种植档案',
      hidden: true
    },
    {
      path: '/processing',
      component: Process,
      name: '加工档案',
      hidden: true
    },
    {
      path: '/enterpriseinformation',
      component: Enterpri,
      name: '企业信息',
      hidden: true
    },
    {
      path: '/inspection',
      component: Inspection,
      name: '检验检测',
      hidden: true
    },
    {
      path: '/information',
      component: Information,
      name: '物联网检测信息',
      hidden: true
    },
    {
      path: '/logistics',
      component: Logistics,
      name: '物流',
      hidden: true
    }
  ]
})
