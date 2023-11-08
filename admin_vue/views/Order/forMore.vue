<template>
  <div>
    <el-table
        :data="tableOrder"
        border
        style="width: 100%">
      <el-table-column
          fixed
          prop="id"
          label="订单编号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="time"
          label="时间"
          width="200">
      </el-table-column>
      <el-table-column
          prop="price"
          label="价格"
          width="100">
      </el-table-column>
      <el-table-column
          prop="uid"
          label="用户"
          width="100">
      </el-table-column>
      <el-table-column
          prop="state"
          label="状态"
          width="100">
      </el-table-column>
      <el-table-column
          prop="machine"
          label="机器"
          width="100">
      </el-table-column>
      <el-table-column

          label="图片"
          width="150">
        <template slot-scope="scope">
          <a :href="'http://food-detect.sylvanding.online/img/food/'+scope.row.picture" target="_blank"
             class="buttonText">{{ scope.row.picture }}</a>
        </template>
      </el-table-column>
      <el-table-column
          prop="rate"
          label="评分"
          width="100">
      </el-table-column>
    </el-table>
    <div style="height: 150px"></div>
    <el-table
        :data="tableDish"
        border
        style="width: 100%">
      <el-table-column
          fixed
          prop="id"
          label="订单编号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="name"
          label="菜名"
          width="140">
      </el-table-column>
      <el-table-column
          label="图片"
          width="100">
        <template slot-scope="scope">
          <a :href="'http://food-detect.sylvanding.online/img/food/'+scope.row.picture" target="_blank"
             class="buttonText">{{ scope.row.picture }}</a>
        </template>
      </el-table-column>
      <el-table-column
          prop="state"
          label="状态"
          width="80">
      </el-table-column>
      <el-table-column
          prop="category"
          label="种类"
          width="80">
      </el-table-column>
      <el-table-column
          prop="calorie"
          label="卡路里"
          width="90">
      </el-table-column>
      <el-table-column
          prop="carbohydrate"
          label="卡式评分"
          width="80">
      </el-table-column>
      <el-table-column
          prop="fat"
          label="脂肪"
          width="80">
      </el-table-column>
      <el-table-column
          prop="protein"
          label="蛋白质"
          width="90">
      </el-table-column>
      <el-table-column
          prop="vitamin"
          label="维他命"
          width="100">
      </el-table-column>
      <el-table-column
          prop="amount"
          label="数量"
          width="100">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {getMore} from "../../api/data"

export default {
  name: "forMore",
  data() {
    return {
      id: this.$route.params.orderId,
      tableOrder: [],
      tableDish: [],
    }
  },
  mounted() {
    getMore({'id': this.id}).then(res => {
      this.tableOrder = res.data.tab1
      this.tableDish = res.data.food_tab
    })
  },
  created() {

  },
  methods: {
    mo() {
      console.log(this.id)
    }
  }
}
</script>

<style scoped>

</style>