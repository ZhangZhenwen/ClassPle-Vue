package com.zhenwen.mapper;

import com.zhenwen.domain.TaskFile;

/**
 * @author zhenwen
 */
public interface TaskFileMapper {

    /**
     * 删除记录
     * @param key
     * @return
     */
    int deleteByPrimaryKey(TaskFile key);

    /**
     * 根据taskID删除记录
     *
     * @param taskId
     * @return
     */
    int deleteByTaskId(Integer taskId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(TaskFile record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(TaskFile record);
}
