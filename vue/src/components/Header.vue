<template>
<div>
  <el-menu  class="el-menu-demo" mode="horizontal">
    <el-row>
      <el-col span="2" offset="1">
        <el-menu-item index="">XXXX</el-menu-item>
      </el-col>
      <el-col span="2" offset="1">
        <el-menu-item index="2">XXXX</el-menu-item>
      </el-col>
      <el-col span="2" offset="8">
        <el-menu-item index="3" @click="exit()">退出系统</el-menu-item>
      </el-col>

    </el-row>

  </el-menu>
</div>
</template>

<script>
import {removeToken} from "../../utils/auth";
import request from "../../utils/request";

export default {
  name: 'Header',
  data(){
    return{

    }
  },
  methods:{
    exit(){
      request.delete("/user/exit").then(res=>{
        if (res.code==100){
          this.$message({
            type:"success",
            message:"退出成功"
          })
          removeToken();
          this.$router.push("/")
        }
      })
    }
  }

}
</script>


<style scoped>

</style>
