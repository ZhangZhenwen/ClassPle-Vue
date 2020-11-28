package com.zhenwen.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * role_menu
 * @author
 */
@Data
public class RoleMenu implements Serializable {
    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;

    private static final long serialVersionUID = 1L;
}
