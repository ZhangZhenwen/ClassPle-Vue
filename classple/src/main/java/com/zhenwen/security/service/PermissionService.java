package com.zhenwen.security.service;

import com.zhenwen.domain.User;
import com.zhenwen.service.MenuService;
import com.zhenwen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhenwen
 * @date 2020/11/10
 */
@Component
public class PermissionService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色信息权限
     */
    public Set<String> getRolePermission(User user) {
        Set<String> roles = new HashSet<>();

        if (isAdmin(user)) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }

        return roles;
    }

    public Set<String> getMenuPermission(User user) {
        Set<String> perms = new HashSet<>();

        if (isAdmin(user)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }

        return perms;
    }

    private boolean isAdmin(User user) {
        return user.getUserId() != null && user.getUserId() == 1;
    }


}
