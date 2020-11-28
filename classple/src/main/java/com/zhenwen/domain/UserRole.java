package com.zhenwen.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * user_role
 * @author zhenwen
 */
@Data
public class UserRole implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public UserRole() {
    }

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
