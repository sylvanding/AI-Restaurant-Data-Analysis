import Vue from 'vue'
import App from './App.vue'
import { Button, Select, Container, Aside, Header, Main, Menu, Submenu, MenuItem ,MenuItemGroup, Dropdown, DropdownMenu, DropdownItem, Row, Col, Card, Table, TableColumn, Form, FormItem, Input, Message, Pagination} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from '../router'
import store from '../src/store'
import http from 'axios'
import fullscreen from 'vue-fullscreen'
import 'default-passive-events'
Vue.use(fullscreen)
Vue.use(Button);
Vue.use(Select);
Vue.use(Container);
Vue.use(Aside);
Vue.use(Header);
Vue.use(Main);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.use(Dropdown);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(Row);
Vue.use(Col);
Vue.use(Card);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Pagination);

Vue.prototype.$message = Message

Vue.config.productionTip = false
Vue.prototype.$http = http

router.beforeEach((to,from,next)=>{
  store.commit('getToken')
  const token = store.state.user.token
  if(!token && to.name != 'login'){
    next({name:'login'})
  }else if(token && to.name==='login'){
    next({name:'Home'})
  }else {
    next()
  }
})

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
