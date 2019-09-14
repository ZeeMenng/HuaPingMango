<template>
<div :class="{h100 : !flag}">
    <Pcdetail v-if="flag" />
    <Appdetail v-else/>
</div>
</template>

<script>
import Pcdetail from '@/components/pcDetail'
import Appdetail from '@/components/appDetail'
export default {
  components: {
    Pcdetail,
    Appdetail
  },
  data () {
    return {
      flag: true,
      maskFa: true
    }
  },
  methods: {
    modalVisibleChange(val){
      this.maskFa = val;
    },
    IsPC () {  
        var userAgentInfo = navigator.userAgent;
        var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
        for (var v = 0; v < Agents.length; v++) {  
            if (userAgentInfo.indexOf(Agents[v]) > 0) { this.flag = false; break; }  
        }
    }
  },
  created () {
    this.IsPC()
  }
}
</script>


<style lang="scss" scoped>
.h100{
  height: 100%;
}
</style>