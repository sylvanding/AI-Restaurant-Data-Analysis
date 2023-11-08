<template>
  <el-table :data="data" border>
          <el-table-column fixed v-for="(val,key) in tableLabel" :key="key" :prop="key"
                           :label="val"></el-table-column>
        </el-table>
</template>

<script>
import {getRemark} from "../../api/data";

export default {
  name: "remark",
  data(){
    return{
      data:[],
      tableLabel: {
        id: '编号',
        uid: '用户UID',
        order_id: '订单编号',
        content:'评价内容',
        result:'是否处理',

      }
    }
  },
  mounted() {
    getRemark().then(res => {
      this.data = res.data
    })
  }
}
</script>

<style scoped>

</style>