package com.zhenwen.service;

import com.zhenwen.domain.Course;
import com.zhenwen.domain.User;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface CourseService {

    /**
     * 查找正常课程
     * @param user user ID
     * @return list
     */
    List<Course> findCommonCoursesByUserId(User user);

    /**
     * 查找正常课程
     * @return list
     */
    List<Course> findCommonCoursesByUser();

    /**
     * 查找归档课程
     * @param user user ID
     * @return list
     */
    List<Course> findFiledCoursesByUserId(User user);

    /**
     * 查找归档课程
     * @return list
     */
    List<Course> findFiledCoursesByUser();

    /**
     * 查找所有
     * @return list
     */
    List<Course> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Course> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return Course
     */
    Course findById(int id);

    /**
     * 添加
     * @param course course
     * @return bool
     */
    Boolean insert(Course course);

    /**
     * 更新
     * @param course course
     * @return bool
     */
    Boolean updateById(Course course);

    /**
     * 删除
     * @param id Course ID
     * @return bool
     */
    Boolean deleteById(int id);

    /**
     * 加入课程
     * @param code 课程码
     * @return bool
     */
    Boolean joinCourse(String code);

    /**
     * 邀请加入课程
     * @param id 课程ID
     * @param code 邀请名单
     * @return bool
     */
    Boolean inviteCourse(Integer id, String code);

    /**
     * 查找学生数量
     * @param id ID
     * @return 学生数量
     */
    Integer findStuNum(Integer id);

    /**
     * 查找老师数量
     * @param id id
     * @return 老师数量
     */
    Integer findTchNum(Integer id);
}
