<template>
  <el-row class="home" :gutter="20" id="quan">
    <el-col :span="12" style="margin-top: 20px">
      <el-card shadow="hover">
        <el-table :data="dingdanData" height="288">
          <el-table-column fixed v-for="(val,key) in dingdanLabel" :key="key" :prop="key"
                           :label="val"></el-table-column>
        </el-table>
      </el-card>
      <el-card shadow="hover" style="height: 280px">
        <!--        <el-table :data="tableData">-->
        <!--          <el-table-column fixed v-for="(val, key) in tableLabel" :key="key" :prop="key" :label="val"></el-table-column>-->

        <!--        </el-table>-->
        <div style="height: 280px" id="chart1"></div>
      </el-card>
    </el-col>
    <el-col :span="12" style="margin-top: 20px">
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card style="height: 200px">
            <xuan-zhuan><p style="font-size: 12px;display: inline-block ;margin-top: 8px;margin-bottom: 10px">今日订单数</p>
              <p>{{ TodayNum }}</p></xuan-zhuan>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card style="height: 200px" class="yingshow">
            <xuan-zhuan><p style="display: inline-block ;margin-top: 5px;margin-bottom: 10px">今日营收</p>
              <p>{{ TodayMoney }}</p></xuan-zhuan>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card style="height: 200px">
            <xuan-zhuan><p style="display: inline-block ;margin-top: 5px;margin-bottom: 10px">今日均价</p>
              <p>{{ AvrMoney }}</p></xuan-zhuan>
          </el-card>
        </el-col>
      </el-row>
      <div style="height: 20px"></div>


      <div class="gragh">

        <el-card style="height: 300px">
          <div style="height: 280px" id="chart2">

          </div>
        </el-card>
      </div>
<!--      <el-button @click="chang">增加菜</el-button>-->
<!--      <el-button @click="dingshi">请求一次</el-button>-->
<!--      <el-button @click="dataOne">eef</el-button>-->
    </el-col>

    <!--    <div class="screen-full">-->
    <!--      <div v-show="!isFullscreen" @click="handleFullScreen">全屏模式</div>-->
    <!--      <div v-show="isFullscreen" @click="handleFullScreen">退出全屏</div>-->
    <!--    </div>-->
  </el-row>

</template>

<script>

import * as echarts from 'echarts';
import XuanZhuan from '../src/components/xuanzhuan'
import screenfull from "screenfull";  //引入依赖
import {getHome} from "../api/data";
import {getOne} from "../api/data";

export default {
  name: "Home",
  components: {
    XuanZhuan,
  },

  data() {
    return {
      isFullscreen: false,
      usrimg: require('../src/assets/images/usr.png'),
      tableData: [
        {name: '菜品1', abc: '可以', shenme: 'okok'},
        {name: '菜品2', abc: '不可以', shenme: 'koko'},
      ],
      tableLabel: {
        name: '菜品',
        abc: '可以吗',
        shenme: '其他',
      },
      // dingdanData: [
      //   {id: '001', time: '2022-2-22', price: 5, uid: '0215621', state: 'not ok', machine: 0},
      //   {id: '002', time: '2022-2-22', price: 6, uid: '0215621', state: 'ok', machine: 0},
      //   {id: '003', time: '2022-2-22', price: 2, uid: '0215621', state: 'ok', machine: 0},
      //   {id: '004', time: '2022-2-22', price: 67, uid: '0215621', state: 'ok', machine: 0},
      //   // {id:'005',time:'2022-2-22',price:8,uid:'0215621',state:'ok',machine:0},
      // ],
      dingdanData: [],
      dingdanLabel: {
        id: '订单编号',
        time: '订单时间',
        price: '价格',
        uid: '用户id',
        state: '支付状态',
        machine: '机器编号',
      },
      FiveDate: [],
      FiveMoney: [],
      E1: null,
      E2: null,
      TodayNum: 0,
      TodayMoney: 0,
      AvrMoney: 0,
      Dish: [],
      OneRe: [],
      p:''
    }

  },
  methods: {

    dingshi() {
      let dingdand = {id: '006', time: '2022-2-22', price: 9, uid: '0215621', state: 'ok', machine: 0}
      // if this.dingdanData
    },
    chang() {
      // let oneData = {id: '006', time: '2022-2-22', price: 9, uid: '0215621', state: 'ok', machine: 0}
      let oneData = this.OneRe[0]
      this.changchang()
      this.dingdanData.push(oneData)
      this.changchang()
      this.dingdanData.length > 5 ? this.dingdanData.pop() : this.dingdanData
      this.houxu(oneData.price)
      // this.dingdanData.reverse()
    },
    changchang() {
      this.dingdanData.reverse()
    },
    houxu(price) {
      this.TodayNum++
      this.TodayMoney += price

    },
    handleFullScreen() {
      const element = document.getElementById('quan')
      if (!screenfull.isEnabled) {
        this.$message.info("您的浏览器版本过低，不支持全屏浏览");
        return false;
      }
      screenfull.request(element)
      screenfull.toggle();
    },
    change() {
      this.isFullscreen = screenfull.isFullscreen;
    },
    init() {
      if (screenfull.isEnabled) {
        screenfull.on("change", this.change);
      }
    },
    destroyed(){
      clearInterval();
    },
    destroy() {
      if (screenfull.isEnabled) {
        screenfull.off("change", this.change);
      }
    },
    initEcharts() {
      let that = this
      // 新建一个promise对象
      let newPromise = new Promise((resolve) => {
        resolve()
      })
      //然后异步执行echarts的初始化函数
      newPromise.then(() => {
        //  此dom为echarts图标展示dom
        const chart1 = document.getElementById('chart1')
        const chart2 = document.getElementById('chart2')
        this.E1 = echarts.init(chart1)
        this.E2 = echarts.init(chart2)

      })
    },
    fetchData() {
      getHome().then(res => {
        this.dingdanData = res.data.order
        this.FiveMoney = res.data.Five.money
        this.FiveDate = res.data.Five.date
        this.TodayMoney = res.data.TodayMoney
        this.TodayNum = res.data.TodayNum
        this.AvrMoney = res.data.AvrMoney
        this.Dish = res.data.Dish
        this.E1.setOption({
          title: {text: '近五日营收情况'},
          xAxis: {
            type: 'category',
            // data: ['5', '4', '3', '2', '1']
            data: this.FiveDate,
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              // data: [224, 218, 135, 147, 260],
              data: this.FiveMoney,
              type: 'line'
            }
          ]
        })
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
            data: this.Dish.dishid
          },
          series: [
            {
              type: 'bar',
              barWidth: '10',
              itemStyle: {
                barBorderRadius: 15
              },
              data: this.Dish.cont
            }
          ]
        })
      }).catch(() => {
        this.$loading = false
      })

    },
    dataOne() {
      getOne({'id': this.dingdanData[0].id}).then(res => {
        this.OneRe = res.data.data
        this.dingdanData = this.OneRe
        this.TodayNum = res.data.tn
        this.TodayMoney = res.data.tm
        this.AvrMoney = res.data.av
        // console.log('???')
        //
        // if (this.OneRe[0].id === that.dingdanData[0].id) {
        //   that.dingdanData[0] = this.OneRe[0]
        //   console.log('变了')
        //   console.log(that.OneRe[0])
        // }
        // else {
        //   that.dingdanData[0] = this.OneRe[1]
        //   console.log('二变')
        //   this.chang()
        //
        // }
        // console.log('!!!!')
      })
    }
  },
  mounted() {
    this.initEcharts()
    this.$once('hook:beforeDestroy', () => {//页面关闭
        clearInterval(this.p);//停止
      });

  },
  created() {
    this.fetchData()
    this.p = setInterval(() => {
      setTimeout(() => {

        ///调取接口
        this.dataOne()

      }, 10);
    }, 3000); // 两个小时
  }
}
</script>

<style scoped>
.user {
  text-align: left;
}

.gragh {
  margin-top: 20px;
}

.yingshow {
}

xuan-zhuan {
  margin-left: 25px;
}

</style>