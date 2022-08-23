<template>
<div>
  <el-menu
    router
    :default-active="path"
    class="el-menu-vertical-demo"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">

    <el-menu-item index="/User">
      <i class="el-icon-menu"></i>
      <span slot="title">用户信息</span>
    </el-menu-item>

    <el-menu-item index="/Book">
      <i class="el-icon-setting"></i>
      <span slot="title">书籍信息</span>
    </el-menu-item>
    <el-menu-item index="/Oss">
      <i class="el-icon-setting"></i>
      <span slot="title">文件上传</span>
    </el-menu-item>
  </el-menu>
</div>
</template>

<script>
export default {
  name: 'Aside',
  data(){
    return{
      path:'',
    }
  },
  methods:{

  },
  created() {
    this.path=this.$route.path
  }

}
</script>


<style scoped>
.el-aside .el-menu{
  border-right: none;
}
.el-menu{
  min-height: calc(100vh - 56px);

}
</style>
