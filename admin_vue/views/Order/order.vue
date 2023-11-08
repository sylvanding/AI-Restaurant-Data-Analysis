<template>
  <div>
    <el-table
        :data="tableNow"
        border
        style="width: 100%">
      <el-table-column
          fixed
          prop="id"
          label="订单编号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="date"
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
          <a  :href="'http://food-detect.sylvanding.online/img/order/'+scope.row.picture" target="_blank" class="buttonText">{{scope.row.picture}}</a>
        </template>
      </el-table-column>
      <el-table-column
          prop="rate"
          label="评分"
          width="100">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="150">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">
            <router-link :to="'/Od/'+scope.row.id">查看</router-link>
          </el-button>
<!--          <el-button type="text" size="small">编辑</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
<!--      <img src="http://food-detect.sylvanding.online/img/food/222.jpg" style="width: 80px;height: 80px" alt="">-->
<!--      <span class="demonstration">直接前往</span>-->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage3"
          :page-size="10"
          layout="prev, pager, next, jumper"
          :total=total>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {getOrder} from "../../api/data";

export default {
  name: "order",
  methods: {
    handleClick(row) {
      console.log(row);
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      let that = this;
      console.log(`当前页: ${val}`);
      this.tableNow.length = 0;

      this.tableData.forEach(function (item, index, array) {
        if ((val * 10) > index && (val - 1) * 10 <= index) {
          that.tableNow.push(array[index])
        }
      });
      if (this.tableData.length < 10 * (val - 1)) {
        this.tableNow = []
      }
    }
  },
  data() {
    return {
      tableNow: [],
      tableData: [],
      currentPage3: 1,
      total: 0,
    }
  },
  mounted() {
    getOrder().then(res => {
      this.tableData = res.data[0]
      this.total = res.data[1]
      let that = this
      if (this.tableData.length > 10) {
        this.tableData.forEach(function (item, index, array) {
          if (index < 10) {
            that.tableNow.push(array[index])
          }
        })
      } else {
        this.tableNow = this.tableData
      }
    })
  },
}
</script>

<style scoped>

</style>