package com.zhenwen.service.impl;

import com.zhenwen.domain.Role;
import com.zhenwen.domain.User;
import com.zhenwen.mapper.RoleMapper;
import com.zhenwen.mapper.UserCrseMapper;
import com.zhenwen.security.service.LoginService;
import com.zhenwen.service.RoleService;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhenwen
 * @date 2020/11/10
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserCrseMapper userCrseMapper;

    @Autowired
    LoginService loginService;

    @Override
    public Set<String> selectRolePermissionByUserId(Integer userId) {
        List<Role> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();

        for (Role perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleCode().trim().split(",")));
            }
        }

        return permsSet;
    }

    @Override
    public String selectRoleCodeByUserIdCrseId(Integer userId, Integer crseId) {
        return userCrseMapper.selectRoleCodeByUserIdCrseId(userId, crseId);
    }

    @Override
    public Boolean checkRoleInCourse(Integer userId, Integer crseId, String code) {
        return code.equals(selectRoleCodeByUserIdCrseId(userId, crseId));
    }

    @Override
    public Boolean checkRoleInCourse(Integer crseId, String code) {
        User user = (User) loginService.getInfo().get("user");

        int userId = user.getUserId();

        return code.contains(selectRoleCodeByUserIdCrseId(userId, crseId));
    }
}
