package com.zhenwen.service;

import com.zhenwen.domain.Assignment;
import com.zhenwen.domain.UserCrse;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface AssignmentService {

    /**
     * 查找所有
     *
     * @return list
     */
    List<Assignment> findAll();

    /**
     * 分页查找
     *
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Assignment> findByPager(int page, int size);

    /**
     * 根据ID查找
     *
     * @param id ID
     * @return Assignment
     */
    Assignment findById(int id);

    /**
     * 根据课程ID查找作业
     *
     * @param crseId
     * @return
     */
    List<Assignment> findByCourseId(Integer crseId);

    /**
     * 根据作业ID 查找对应课程ID
     * @param asgnId
     * @return
     */
    Integer findCourseIdByAsgnId(Integer asgnId);

    /**
     * 添加
     *
     * @param assignment assignment
     * @return bool
     */
    Boolean insert(Assignment assignment);

    /**
     * 更新
     *
     * @param assignment assignment
     * @return bool
     */
    Boolean updateById(Assignment assignment);

    /**
     * 删除
     *
     * @param id Assignment ID
     * @return bool
     */
    Boolean deleteById(int id);

    /**
     * 查找课程中的作业
     * @param crseId
     * @return
     */
    List<Assignment> selectAssignmentByCrseId(Integer crseId);

    /**
     * 查找课程用户列表
     * @param asgnId
     * @return
     */
    List<UserCrse> selectUserCrseByAsgnId(Integer asgnId);
}
