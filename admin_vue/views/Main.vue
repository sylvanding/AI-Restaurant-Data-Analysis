<template>
  <el-container>
<!--    <el-aside >-->
    <common-aside></common-aside>
<!--    </el-aside>-->
    <el-container>
      <el-header>
        <common-header></common-header>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CommonAside from "@/components/CommonAside";
import CommonHeader from "@/components/CommonHeader";
export default {
  name: "Home",
  components:{
    CommonAside,
    CommonHeader
  }
}
</script>

<style lang="less" scoped>
.el-header {
  background-color: gray;
  width: 100%;
}

</style>