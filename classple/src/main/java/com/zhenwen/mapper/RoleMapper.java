package com.zhenwen.mapper;

import com.zhenwen.domain.Role;

import java.util.List;

/**
 * @author zhenwen
 */
public interface RoleMapper {

    /**
     * 删除记录
     * @param roleId
     * @return
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Role record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Role record);

    /**
     * 根据用户ID选择角色
     * @param userId
     * @return
     */
    List<Role> selectRolePermissionByUserId(Integer userId);

    /**
     * 选择记录
     * @param roleId
     * @return
     */
    Role selectByPrimaryKey(Integer roleId);

    /**
     * 根据角色代码选择角色
     * @param code
     * @return
     */
    Role selectByCode(String code);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Role record);
}
