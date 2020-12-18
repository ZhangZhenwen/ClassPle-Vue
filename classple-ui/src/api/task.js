import request from '@/utils/request'

/**
 *
 * @author zhenwen
 * @date   2020/12/3
 */


export function submitTask(data) {
    return request({
        url: '/task/submit',
        method: 'post',
        data: data
    })
}

export function correctTask(data) {
    return request({
        url: '/task/correct',
        method: 'post',
        data: data
    })
}

export function editTask(data) {
    return request({
        url: '/task/edit',
        method: 'post',
        data: data
    })
}

export function getTaskDetail(id) {
    return request({
        url: '/task/detailByAsgnId',
        method: 'post',
        data: id
    })
}

export function checkTask(data) {
    return request({
        url: '/task/check',
        method: 'post',
        data: data
    })
}
