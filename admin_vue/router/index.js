import Vue from 'vue'
import VueRouter from 'vue-router'
// import Hi from '../views/Hi'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Main',
        component: () => import('../views/Main'),
        children: [
            {
                path: '/home',
                name: 'Home',
                component: () => import('../views/Home')
            },
            {
                path: '/order',
                name: 'Order',
                component: () => import('../views/Order/order')
            },
            {
                path: '/ord',
                name: 'Ord',
                component: () => import('../views/Order/orderquery')
            },
            {
                path: '/od/:orderId',
                name: 'Od',
                component: () => import('../views/Order/forMore')
            },
            {
                path: '/remark',
                name: 'Remark',
                component: () => import('../views/Remark/remark')
            },
            {
                path: '/rate',
                name: 'Rate',
                component: () => import('../views/Rate/rate')
            },

        ]
    },
    {
        path: '/user',
        name: 'usr',
        component: () => import('../views/usr')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/Login/login')
    },

]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router