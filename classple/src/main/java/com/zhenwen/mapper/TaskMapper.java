package com.zhenwen.mapper;

import com.zhenwen.domain.Task;

import java.util.List;

/**
 * @author zhenwen
 */
public interface TaskMapper {

    /**
     * 删除记录
     * @param taskId
     * @return
     */
    int deleteByPrimaryKey(Integer taskId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Task record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Task record);

    /**
     * 选择记录
     * @param taskId
     * @return
     */
    Task selectByPrimaryKey(Integer taskId);

    /**
     * 根据作业ID查找记录
     * @param asgnId
     * @return
     */
    List<Task> selectByAsgnId(Integer asgnId);

    /**
     * 根据作业ID 用户ID查找记录
     * @param asgnId
     * @param userId
     * @return
     */
    List<Task> selectByAsgnIdUserId(Integer asgnId, Integer userId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Task record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Task record);
}
