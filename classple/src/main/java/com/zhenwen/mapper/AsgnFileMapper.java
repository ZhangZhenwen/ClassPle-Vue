package com.zhenwen.mapper;

import com.zhenwen.domain.AsgnFile;

/**
 * @author zhenwen
 */
public interface AsgnFileMapper {

    /**
     * 删除记录
     * @param key 主键
     * @return 影响行数
     */
    int deleteByPrimaryKey(AsgnFile key);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(AsgnFile record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(AsgnFile record);

    /**
     * 删除记录
     * @param asgnId 记录ID
     * @return 影响行数
     */
    int deleteByAsgnId(int asgnId);
}
