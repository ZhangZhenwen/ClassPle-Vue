package com.zhenwen.mapper;

import com.zhenwen.domain.CrseAsgn;

/**
 * @author zhenwen
 */
public interface CrseAsgnMapper {

    /**
     * 根据作业ID选择课程ID
     * @param asgnId
     * @return
     */
    Integer selectCrseIdByAsgnId(Integer asgnId);

    /**
     * 删除记录
     * @param key
     * @return
     */
    int deleteByPrimaryKey(CrseAsgn key);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(CrseAsgn record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(CrseAsgn record);
}
