<template>
 <el-upload
  class="upload-demo"
  drag
  action=""
  :on-change="image_change"
  :auto-upload="false"
  :show-file-list="false">
  <i class="el-icon-upload"></i>
  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
</el-upload>
</template>
<script>
export default {
  data() {
    return {
      file: "",
    };
  },
  methods: {
    image_change(file) {
      this.file = file;
      console.log(this.file)
      this.$message.success('上传图片成功')
    },
  },
};
</script>