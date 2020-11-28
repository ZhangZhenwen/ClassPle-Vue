package com.zhenwen.mapper;

import com.zhenwen.domain.Ad;

/**
 * @author zhenwen
 */
public interface AdMapper {

    /**
     * 删除
     * @param adId ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer adId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Ad record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Ad record);

    /**
     * 选择记录
     * @param adId 记录ID
     * @return 影响行数
     */
    Ad selectByPrimaryKey(Integer adId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Ad record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Ad record);
}
