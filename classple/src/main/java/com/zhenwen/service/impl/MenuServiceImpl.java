package com.zhenwen.service.impl;

import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.domain.Menu;
import com.zhenwen.mapper.MenuMapper;
import com.zhenwen.mapper.RoleMenuMapper;
import com.zhenwen.service.MenuService;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhenwen
 * @date 2020/11/3
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public List<Menu> selectMenuList(Integer userId) {
        return selectMenuList(new Menu(), userId);
    }

    @Override
    public List<Menu> selectMenuList(Menu menu, Integer userId) {

        List<Menu> menuList = null;

        menu.getParams().put("userId", userId);
        menuList = menuMapper.selectMenuListByUserId(menu);

        return menuList;
    }

    @Override
    public Set<String> selectMenuPermsByUserId(Integer userId) {

        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);

        Set<String> permsSet = new HashSet<>();

        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }

        return permsSet;
    }

    @Override
    public List<Menu> selectMenuTreeByUserId(Integer userId) {

        List<Menu> menuList = null;

        menuList = menuMapper.selectMenuTreeByUserId(userId);

        return menuList;
    }

    @Override
    public List<Integer> selectMenuListByRoleId(Integer roleId) {
        return menuMapper.selectMenuListByRoleId(roleId);
    }

    @Override
    public Menu selectMenuById(Integer menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    @Override
    public boolean hasChildByMenuId(Integer menuId) {

        int result = menuMapper.hasChildByMenuId(menuId);

        return result > 0;
    }

    @Override
    public boolean checkMenuExistRole(Integer menuId) {

        int result = roleMenuMapper.checkMenuExistRole(menuId);

        return result > 0;
    }

    @Override
    public int insertMenu(Menu menu) {
        return menuMapper.insertSelective(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int deleteMenuById(Integer menuId) {
        return menuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public String checkMenuNameUnique(Menu menu) {
        Integer menuId = StringUtils.isNull(menu.getMenuId()) ? -1 : menu.getMenuId();
        Menu info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());

        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }

        return UserConstants.UNIQUE;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(Menu menu)
    {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }
}
