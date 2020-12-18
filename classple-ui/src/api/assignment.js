import request from '@//utils/request'

/**
 *
 * @author zhenwen
 * @date   2020/12/3
 */

export function getAssignmentList(id) {
    return request({
        url: '/assignment/list',
        method: 'post',
        data: id
    })
}

export function getReviewList(id) {
    return request({
        url: '/assignment/reviewList',
        method: 'post',
        data: id
    })
}

export function getSubmitList(id) {
    return request({
        url: '/assignment/submitList',
        method: 'post',
        data: 'id'
    })
}

export function postAssignment(data) {
    return request({
        url: '/assignment/post',
        method: 'post',
        data: data
    })
}

export function editAssignment(data) {
    return request({
        url: '/assignment/edit',
        method: 'post',
        data: data
    })
}

export function getAssignmentDetail(id) {
    return request({
        url: '/assignment/detail',
        method: 'post',
        data: id
    })
}

export function deleteAssignment(id) {
    return request({
        url: '/assignment/delete',
        method: 'post',
        data: id
    })
}
