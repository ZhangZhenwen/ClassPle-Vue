import request from '@/utils/request'

/**
 *
 * @author zhenwen
 * @date   2020/11/28
 */

export function getCourse() {
  return request({
    url: '/course/list',
    method: 'get',
  })
}

export function addCourse(data) {
  return request({
    url: '/course/create',
    method: 'post',
    data: data
  })
}

export function joinCourse(code) {
  return request({
    url: '/course/join',
    method: 'post',
    data: code
  })
}

export function inviteCourse(data) {
  return request({
    url: '/course/invite',
    method: 'post',
    data: data
  })
}


export function dropoutCourse(id) {
  return request({
    url: '/course/exit',
    method: 'post',
    data: id
  })
}

export function getCourseDetail(id) {
  return request({
    url: '/course/detail',
    method: 'post',
    data: id
  })
}

export function getStudentCount(id) {
  return request({
    url: '/course/count/stu',
    method: 'post',
    data: id
  })
}

export function getTeacherCount(id) {
  return request({
    url: '/course/count/tch',
    method: 'post',
    data: id
  })
}

export function getUserList(id) {
  return request({
    url: '/course/userList',
    method: 'post',
    data: id
  })
}
