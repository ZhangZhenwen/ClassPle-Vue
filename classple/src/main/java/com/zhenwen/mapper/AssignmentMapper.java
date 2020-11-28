package com.zhenwen.mapper;

import com.zhenwen.domain.Assignment;

import java.util.List;

/**
 * @author zhenwen
 */
public interface AssignmentMapper {

    /**
     * 删除记录
     * @param asgnId 记录ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer asgnId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Assignment record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Assignment record);

    Assignment selectByPrimaryKey(Integer asgnId);

    /**
     * 根据课程ID查找作业
     *
     * @param crseId
     * @return
     */
    List<Assignment> selectByCourseId(Integer crseId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Assignment record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Assignment record);
}
