package com.zhenwen.mapper;

import com.zhenwen.domain.Preparation;

/**
 * @author zhenwen
 */
public interface PreparationMapper {

    /**
     * 删除记录
     *
     * @param prepId
     * @return
     */
    int deleteByPrimaryKey(Integer prepId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Preparation record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Preparation record);

    /**
     * 选择记录
     * @param prepId
     * @return
     */
    Preparation selectByPrimaryKey(Integer prepId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Preparation record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Preparation record);
}
