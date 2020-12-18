import request from "@/utils/request";

/**
 *
 * @author zhenwen
 * @date   2020/12/6
 */


export function upload(data) {
    return request({
        url: '/resources/upload',
        method: 'post',
        data: data,
    })
}

export function download(id) {
    return request({
        url: '/resources/download',
        method: 'get',
        data: id
    })
}
