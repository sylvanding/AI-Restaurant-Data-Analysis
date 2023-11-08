<template>

  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="用户ID">
      <el-input v-model="formInline.user" placeholder="用户ID"></el-input>
    </el-form-item>
<!--    <el-form-item label="活动区域">-->
<!--      <el-select v-model="formInline.region" placeholder="活动区域">-->
<!--        <el-option label="区域一" value="shanghai"></el-option>-->
<!--        <el-option label="区域二" value="beijing"></el-option>-->
<!--      </el-select>-->
<!--    </el-form-item>-->
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
  </el-form>

</template>
<script>
export default {
  name:'orderquery',
  data() {
    return {
      formInline: {
        user: '',
        region: ''
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    }
  }
}
</script>