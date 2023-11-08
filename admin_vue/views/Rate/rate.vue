<template>
  <div>
    <div style="width: 1000px; height: 700px" id="chart2"></div>
  </div>
</template>

<script>
import {getRate} from "../../api/data";
import * as echarts from "echarts";

export default {
  name: "rate",
  data() {
    return {
      data: [],
      dishId:[],
      cont:[],
    }
  },
  methods: {
    initEcharts() {
      let that = this
      // 新建一个promise对象
      let newPromise = new Promise((resolve) => {
        resolve()
      })
      //然后异步执行echarts的初始化函数
      newPromise.then(() => {
        //  此dom为echarts图标展示dom
        const chart2 = document.getElementById('chart2')
        this.E2 = echarts.init(chart2)
      })
    },
    fetchData() {
      getRate().then(res => {
        this.data = res.data
        this.dishId = res.data.dishid
        this.cont = res.data.cont
        console.log(this.dishId)
        console.log(this.cont)
        this.E2.setOption({
          title: {
            text: '菜品购买情况'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {},
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
          },
          yAxis: {
            type: 'category',
            data: this.dishId,
          },
          series: [
            {
              type: 'bar',
              barWidth: '10',
              itemStyle: {
                barBorderRadius: 15
              },
              data: this.cont
            }
          ]
        })
      }).catch(() => {
        this.$loading = false
      })

    }

  },
  mounted() {
    this.initEcharts()
  },
  created() {
    this.fetchData()
  }
}
</script>

<style scoped>

</style>