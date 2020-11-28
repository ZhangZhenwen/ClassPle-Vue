package com.zhenwen.mapper;

import com.zhenwen.domain.Comment;

/**
 * @author zhenwen
 */
public interface CommentMapper {

    /**
     * 删除记录
     * @param cmtId 记录ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer cmtId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Comment record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Comment record);

    /**
     * 选择记录
     * @param cmtId 记录ID
     * @return 影响行数
     */
    Comment selectByPrimaryKey(Integer cmtId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Comment record);
}
