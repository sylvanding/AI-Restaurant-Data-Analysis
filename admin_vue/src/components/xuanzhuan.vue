<template>
  <div class="module_box">
    <div class="box2">
      <i><span></span></i>
      <p>
        <slot></slot>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "xuanzhuan"
}
</script>

<style lang="less" scoped>
.module_box {
  display: flex;
}

.box2 {
  margin-left: 10px;
  margin-top: 20px;
  width: 100px;
  height: 100px;
  border: 5px solid pink;
  border-radius: 50%;

  position: relative;
}

.box2 i {
  width: 100px;
  height: 100px;
  position: absolute;
  left: 0;
  top: 0;
  animation: circleRoate 3s infinite linear;
  border-radius: 50%;
  //background-color: #505458;


}

.box2 i span {
  width: 8px;
  height: 8px;
  position: absolute;
  border-radius: 50%;
  left: 10px;
  top: 20px;
  background: rgb(139, 214, 233);
}

/* 必须要有 */

p {
  text-align: center;
}

@keyframes circleRoate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>