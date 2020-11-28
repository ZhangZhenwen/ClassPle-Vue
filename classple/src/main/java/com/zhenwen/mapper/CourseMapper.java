package com.zhenwen.mapper;

import com.zhenwen.domain.Course;

import java.util.List;

/**
 * @author zhenwen
 */
public interface CourseMapper {

    /**
     * 删除记录
     * @param crseId 记录ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer crseId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Course record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Course record);

    /**
     * 选择记录
     * @param crseId 记录ID
     * @return 影响行数
     */
    Course selectByPrimaryKey(Integer crseId);

    /**
     * 根据课程码查找课程
     *
     * @param code 课程码
     * @return 课程
     */
    Course findCourseByCourseCode(String code);

    /**
     * 查找所有课程
     *
     * @return list
     */
    List<Course> findAllCourse();

    /**
     * 根据ID list查找课程
     *
     * @param crseId 课程ID
     * @return list
     */
    List<Course> findCoursesByCrseIds(List<Integer> crseId);

    /**
     * 查找普通课程
     *
     * @param crseId 课程ID
     * @return list
     */
    List<Course> findCommonCoursesByCrseIds(List<Integer> crseId);

    /**
     * 查找归档课程
     *
     * @param crseId 课程ID
     * @return list
     */
    List<Course> findFiledCoursesByCrseIds(List<Integer> crseId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Course record);
}
