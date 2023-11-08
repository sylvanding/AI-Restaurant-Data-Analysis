import Cookie from 'js-cookie'
export default {
    state:{
        isCollapse: false
    },
    mutations:{
        collapseMenu(state){
            state.isCollapse=!state.isCollapse
        }
    },
    // setMenu(state,val){
    //     state.menu = val
    //     Cookie.set('menu',JSON.stringify(val))
    // },
    // clearMenu(state){
    //     state.menu = []
    //     Cookie.remove('menu')
    // },
    // addMenu(state,val){
    //     if (!Cookie.get('menu')){
    //         return
    //     }
    //     const menu = JSON.parse(Cookie.get('menu'))
    //     state.menu = menu
    //     const menuArray = []
    //     menu.forEach
    // }
}