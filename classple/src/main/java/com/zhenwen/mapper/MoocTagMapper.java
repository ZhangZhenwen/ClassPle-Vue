package com.zhenwen.mapper;

import com.zhenwen.domain.MoocTag;

/**
 * @author zhenwen
 */
public interface MoocTagMapper {

    /**
     * 删除记录
     * @param key ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(MoocTag key);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(MoocTag record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(MoocTag record);
}
