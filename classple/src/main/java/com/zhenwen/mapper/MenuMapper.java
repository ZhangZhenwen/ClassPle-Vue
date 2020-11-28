package com.zhenwen.mapper;

import com.zhenwen.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhenwen
 */
public interface MenuMapper {

    /**
     * 删除记录
     * @param menuId ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer menuId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Menu record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Menu record);

    /**
     * 选择记录
     * @param menuId ID
     * @return 记录
     */
    Menu selectMenuById(Integer menuId);

    List<Menu> selectMenuList(Menu menu);

    List<Menu> selectMenuTreeAll(Menu menu);

    /**
     * 根据用户ID查找菜单
     *
     * @param menu 菜单
     * @return list
     */
    List<Menu> selectMenuListByUserId (Menu menu);

    /**
     * 根据用户ID查找菜单树
     *
     * @param userId 用户ID
     * @return 菜单树
     */
    List<Menu> selectMenuTreeByUserId(Integer userId);

    /**
     * 根据角色ID查找菜单
     *
     * @param roleId 角色ID
     * @return list
     */
    List<Integer> selectMenuListByRoleId(Integer roleId);

    /**
     * 选择菜单perms
     *
     * @return list
     */
    List<String> selectMenuPerms();

    /**
     * 根据用户ID选择perms
     *
     * @param userId 用户ID
     * @return list
     */
    List<String> selectMenuPermsByUserId(Integer userId);

    /**
     * 有无子菜单
     *
     * @param menuId 菜单ID
     * @return 子菜单数量
     */
    int hasChildByMenuId(Integer menuId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Menu record);

    /**
     * 检查菜单名是否重复
     *
     * @param menuName
     * @param parentId
     * @return
     */
    Menu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Integer parentId);
}
