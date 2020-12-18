package com.zhenwen.service;

import com.zhenwen.domain.Course;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/12/1
 */
public interface ArchiveService {

    /**
     * 查找归档课程
     * @return list
     */
    List<Course> findFiledCoursesByUser();

    /**
     * 归档一人
     *
     * @param id
     * @return
     */
    Boolean fileCourseForOne(Integer id);

    /**
     * 归档全部
     *
     * @param id
     * @return
     */
    Boolean fileCourseAll(Integer id);

    /**
     * 取消归档一人
     * @param id
     * @return
     */
    Boolean cancelFileCourseForOne(Integer id);

    /**
     * 取消归档全部
     * @param id
     * @return
     */
    Boolean cancelFileCourseAll(Integer id);

}
