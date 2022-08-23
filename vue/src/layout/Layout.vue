<template>
<div>
  <el-container>
    <el-header style="width: 100%">
      <Header/>
    </el-header>

    <el-container>
      <el-aside style="width: 15%" >
        <Aside/>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</div>
</template>

<script>
import Aside from "../components/Aside";
import Header from "../components/Header";

export default {
  name: 'Layout',
  components:{
    Aside,Header
  }

}
</script>


<style scoped>
.el-main {
  padding: 0 !important;

}
</style>
