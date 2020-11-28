package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

/**
 * role
 * @author zhenwen
 */
@Data
public class Role extends BaseEntity {
    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleCode;

    /**
     * 角色状态
     */
    private String status;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 菜单组
     */
    private Integer[] menuIds;

    private static final long serialVersionUID = 1L;
}
