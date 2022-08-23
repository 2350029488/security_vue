<template>
<div>
  <div>
      <el-upload
      :headers="header"
      :action="filesUpdated"
               :on-success="fileUploadSuccess" ref="upload">
      <el-button  type="primary"  >点击上传</el-button>
     </el-upload>
  </div>

 <div>
   <el-image
   :src="image"
   style="width: 60px; height: 60px ;">
 </el-image></div>
</div>
</template>

<script>
import request from "../../utils/request";
import {getToken} from "../../utils/auth";

export default {
  name: 'Oss',
  data(){
    return{
      filesUpdated:"http://localhost:8099/upload",
      image:'',
    }
  },
  methods:{
    fileUploadSuccess(res){
      this.image=res.model.url
      //清除显示的文件
      this.$refs['upload'].clearFiles()
    },

  },


  computed:{
    header(){
      return{token:getToken()}
    }
  }


}
</script>


<style scoped>

</style>
