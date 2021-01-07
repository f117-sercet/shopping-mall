<template>
  <div>
    <i
      class="el-icon-setting"
      @click="visible_state = true"
      :style="{ color: head_icon_color }"
    />
    <el-drawer
      title="我是标题"
      :with-header="false"
      :visible.sync="visible_state"
    >
      <el-card shadow="never" style="min-height: 100vh">
        <div slot="header" class="clearfix">
          <span>EuiAdmin信息</span>
        </div>
        <div>
          <span>组件库：</span>
          <a
            v-for="(com, index) in com_data"
            :key="index"
            :href="com.com_link"
            target="_blank"
             style="margin-left:10px">{{ com.com_name }}</a
          >
          <p>当前版本：Eui-0.5</p>
        </div>
      </el-card>
    </el-drawer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      visible_state: false,
      head_icon_color: this.$cookies.get("setting").head_icon_color,
      com_data: [
        {
          com_name: "element-ui",
          com_link: "https://element.eleme.cn/",
        },
        {
          com_name: "vue-cookies",
          com_link: "https://www.npmjs.com/package/vue-cookies",
        },
        {
          com_name: "echarts",
          com_link: "https://echarts.apache.org/",
        },
        {
          com_name: "js-xlsx",
          com_link: "https://www.npmjs.com/package/js-xlsx",
        },
      ],
    };
  },
};
</script>