package com.zhenwen.mapper;

import com.zhenwen.domain.RoleMenu;

import java.util.List;

/**
 * @author zhenwen
 */
public interface RoleMenuMapper {

    /**
     * 是否有角色拥有该菜单
     *
     * @param menuId
     * @return 用户该菜单的角色数量
     */
    int checkMenuExistRole(Integer menuId);

    /**
     * 根据角色ID删除记录
     *
     * @param roleId
     * @return
     */
    int deleteRoleMenuByRoleId(Integer roleId);

    /**
     * 插入记录
     *
     * @param roleMenuList 记录list
     * @return 影响行数
     */
    int batchRoleMenu(List<RoleMenu> roleMenuList);
}
