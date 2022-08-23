<template>
<div>
<div>
  <el-button @click="tests()">点击</el-button>
</div>
</div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: 'User',

  methods:{
    tests(){
      request.get("/test").then(res=>{
      console.log(res)
      })
    }
  }
}
</script>


<style scoped>

</style>
