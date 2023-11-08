import axios from "./axios";

export const getMenu = (param) => {
    return axios.request({
        url:'/login',
        method:'post',
        data:param,

    })
}

export const getHome = () => {
    return axios.request({
        url:'/home',
        method:'get',
    })
}

export const getOne = (param) => {
    return axios.request({
        url:'/one',
        method:'post',
        data:param,
    })
}

export const getOrder = () => {
    return axios.request({
        url:'/sec',
        method:'get'
    })
}

export const getMore = (param) => {
    return axios.request({
        url:'/more',
        method:'post',
        data:param,
    })
}

export const getRemark = () => {
    return axios.request({
        url:'/remark',
        method:'get',
    })
}

export const getRate = () => {
    return axios.request({
        url:'/rate',
        method:'get',
    })
}