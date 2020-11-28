package com.zhenwen.mapper;

import com.zhenwen.domain.Mooc;

/**
 * @author zhenwen
 */
public interface MoocMapper {

    /**
     * 删除记录
     * @param moocId ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer moocId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Mooc record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Mooc record);

    /**
     * 选择记录
     * @param moocId ID
     * @return 记录
     */
    Mooc selectByPrimaryKey(Integer moocId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Mooc record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Mooc record);
}
