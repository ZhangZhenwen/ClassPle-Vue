package com.zhenwen.service;

import java.util.Set;

/**
 * @author zhenwen
 * @date 2020/11/10
 */

public interface RoleService {
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Integer userId);

    /**
     * 根据用户ID查询在课程中的角色
     *
     * @param userId 用户ID
     * @param crseId 课程ID
     * @return 角色代码
     */
    String selectRoleCodeByUserIdCrseId(Integer userId, Integer crseId);

    /**
     * 用户在课程中是否扮演该角色
     * @param userId
     * @param crseId
     * @param code
     * @return
     */
    Boolean checkRoleInCourse(Integer userId, Integer crseId, String code);

    /**
     * 登录用户在课程中是否扮演该角色
     * @param crseId
     * @param code
     * @return
     */
    Boolean checkRoleInCourse(Integer crseId, String code);
}
