
<template>

  <div class="container">
    <div class="text" >
      <div class="login"   >欢迎登录吃寿司</div>
      <el-form ref="form" :model="form" label-width="100px"  >
        <el-form-item   >
          <el-input prefix-icon="el-icon-user-solid"
                    v-model="form.userName" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item   >
          <!--         show-password  表示是密码 会有显示眼睛-->
          <el-input  prefix-icon="el-icon-lock"
                     v-model="form.password" style="width: 80%" show-password></el-input>
        </el-form-item>
        <el-form-item >
          <el-button style="width: 80%" type="primary" @click="login()">登录</el-button>
        </el-form-item>
        <el-form-item >
          <el-button style="width: 80%" type="primary" @click="$router.push('/Register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import request from "../../utils/request";
import {getToken, removeToken, setToken} from "../../utils/auth";

export default {
  name: "Login",
  data() {
    return {
      form:{
        userName:'1234',
        password:'1234'
      },

    }
  },
  methods: {
    login(){
      //登录时不携带token
      removeToken()
      request.post("/user/login",this.form).then(res=>{
        setToken(res.model.token)
        this.$router.push("/Layout");
      })
    },

  }
}

</script>

<style scoped>
.container{
  width: 100%; height: 100vh; background-color: #1e1ed6;overflow: hidden
}
.text{
  width: 400px; height:500px;margin: 100px auto;background-color: #eec5c5
}
.login{
  color: white;font-size: 30px;text-align: center;padding-bottom: 20px
}
</style>
