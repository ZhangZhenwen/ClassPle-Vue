import request from '@/utils/request'

/**
 *
 * @author zhenwen
 * @date   2020/12/1
 */

export function getArchive() {
    return request({
        url: '/archive/list',
        method: 'get'
    })
}

export function addArchive(id) {
    return request({
        url: '/archive/addArchive',
        method: 'post',
        data: id
    })
}

export function cancelArchive(id) {
    return request({
        url: '/archive/cancelArchive',
        method: 'post',
        data: id
    })
}

export function addArchiveAll(id) {
    return request({
        url: '/archive/addArchiveAll',
        method: 'post',
        data: id
    })
}

export function cancelArchiveAll(id) {
    return request({
        url: '/archive/cancelArchive',
        method: 'post',
        data: id
    })
}

