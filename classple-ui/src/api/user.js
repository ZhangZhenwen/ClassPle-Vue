import request from '@/utils/request'

/**
 *
 * @author zhenwen
 * @date   2020/11/28
 */

export function registerTeacher(data) {
    return request({
        url: '/register/tch',
        method: 'post',
        data: data,
    })
}

export function registerStudent(data) {
    return request({
        url: '/register/stu',
        method: 'post',
        data: data,
    })
}

export function getUserInfo() {
    return request({
        url: '/user/getInfo',
        method: 'get',
    })
}

export function getUserListByAsgnId(id) {
    return request({
        url: '/assignment/userList',
        method: 'post',
        data: id
    })
}

export function getRoleByAsgnId(id) {
    return request({
        url: '/user/getRoleByAsgnId',
        method: 'post',
        data: id
    })
}
