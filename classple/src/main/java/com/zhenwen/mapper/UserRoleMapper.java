package com.zhenwen.mapper;

import com.zhenwen.domain.UserRole;

/**
 * @author zhenwen
 */
public interface UserRoleMapper {

    /**
     * 删除记录
     * @param key
     * @return
     */
    int deleteByPrimaryKey(UserRole key);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(UserRole record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(UserRole record);
}
