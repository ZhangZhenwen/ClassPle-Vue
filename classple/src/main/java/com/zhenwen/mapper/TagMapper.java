package com.zhenwen.mapper;

import com.zhenwen.domain.Tag;

/**
 * @author zhenwen
 */
public interface TagMapper {

    /**
     * 删除记录
     * @param tagId
     * @return
     */
    int deleteByPrimaryKey(Integer tagId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Tag record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Tag record);

    /**
     * 选择记录
     * @param tagId
     * @return
     */
    Tag selectByPrimaryKey(Integer tagId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Tag record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Tag record);
}
