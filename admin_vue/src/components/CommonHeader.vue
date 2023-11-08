<template>
  <div>
    <div class="l-content">
      <el-button plain icon="el-icon-menu" size="mini" class="elb" @click="handleMenu"></el-button>
<!--      <h3>首页</h3>-->
    </div>
    <div class="r-content">
      <el-dropdown trigger="click" size="mini">
      <span>
        <img :src="usrimg" class="user">
      </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人中心</el-dropdown-item>
          <el-dropdown-item @click.native="logOut">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommonHeader",
  data() {
    return {
      usrimg: require('../assets/images/usr.png')
    }
  },
  methods:{
    handleMenu(){
      this.$store.commit('collapseMenu')
    },
    logOut(){
      this.$store.commit('clearToken')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.l-content {
  float: left;
  text-align: center;
  padding-left: 0px;
  margin-top: 15px;
}

.elb {
  float: left;
}

h3 {
  text-decoration: none;
  float: left;
  padding-left: 3px;
  margin: 0px;
  }

.r-content {
  float: right;
}

.r-content .user {
  margin-top: 9px;
  padding-right: 0px;
  width: 40px;
  height: 40px;
}

.r-content .user:hover{
  cursor: pointer;
}
</style>