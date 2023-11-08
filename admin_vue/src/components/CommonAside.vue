<template>
  <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
           :collapse="isCollapse">
    <h3 @click="toHome()" class="home">{{isCollapse ? '后台':'后台管理界面'}}</h3>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span slot="title">订单相关</span>
      </template>
      <el-menu-item-group >
<!--        <span slot="title">分组一</span>-->
        <el-menu-item index="1-1" class="erji" @click="toOrder()">近期订单相关</el-menu-item>
<!--        <el-menu-item index="1-2" class="erji" @click="toOrd()">订单查询</el-menu-item>-->
      </el-menu-item-group>
<!--      <el-menu-item-group title="分组2">-->
<!--        <el-menu-item index="1-3">选项3</el-menu-item>-->
<!--      </el-menu-item-group>-->
<!--      <el-submenu index="1-4">-->
<!--        <span slot="title">选项4</span>-->
<!--        <el-menu-item index="1-4-1">选项1</el-menu-item>-->
<!--      </el-submenu>-->
    </el-submenu>
    <el-menu-item index="2">
      <i class="el-icon-menu"></i>
      <span slot="title" @click="toRemark()">评价处理</span>
    </el-menu-item>
    <el-menu-item index="3">
      <i class="el-icon-document"></i>
      <span slot="title" @click="toRate()">菜品评分</span>
    </el-menu-item>
<!--    <el-menu-item index="4">-->
<!--      <i class="el-icon-setting"></i>-->
<!--      <span slot="title">导航四</span>-->
<!--    </el-menu-item>-->
  </el-menu>
</template>

<script>
export default {
  name: "CommonAside",
  data() {
    return {
      // isCollapse: false
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    toHome() {
      this.$router.push({
        name:'Home',
      })
    },
    toOrder(){
      this.$router.push({
        name:'Order'
      })
    },
    toOrd(){
      this.$router.push({
        name:'Ord'
      })
    },
    toRemark(){
      this.$router.push({
        name:'Remark'
      })
    },
    toRate(){
      this.$router.push({
        name:'Rate'
      })
    }
  },
  computed: {
    isCollapse(){
      return this.$store.state.tab.isCollapse
    }
  }
}
</script>

<style lang="less" scoped>
.el-menu h3{
  padding: 0px;
  text-align: center;
  color: cornflowerblue;
  font-size: 18px;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  min-height: 400px;

}

.el-submenu {
  padding: 0px;
}

.erji {
  font-size: 12px;
  margin-left: 20px;
}

.home:hover{
  cursor: pointer;
}
</style>