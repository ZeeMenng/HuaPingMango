import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import echarts from 'echarts'
import './assets/common.scss'
import VideoPlayer from 'vue-video-player'
import '../node_modules/video.js/dist/video-js.css'
import '../node_modules/vue-video-player/src/custom-theme.css'
Vue.use(VideoPlayer);

Vue.config.productionTip = false
Vue.prototype.$echarts = echarts 

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
