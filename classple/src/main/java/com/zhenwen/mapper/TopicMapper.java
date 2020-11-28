package com.zhenwen.mapper;

import com.zhenwen.domain.Topic;

/**
 * @author zhenwen
 */
public interface TopicMapper {

    /**
     * 删除记录
     * @param topicId
     * @return
     */
    int deleteByPrimaryKey(Integer topicId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Topic record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Topic record);

    /**
     * 选择记录
     * @param topicId
     * @return
     */
    Topic selectByPrimaryKey(Integer topicId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Topic record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Topic record);
}
