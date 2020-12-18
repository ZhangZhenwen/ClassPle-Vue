package com.zhenwen.service.impl;

import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.common.constant.UserStatus;
import com.zhenwen.domain.Role;
import com.zhenwen.domain.User;
import com.zhenwen.domain.UserRole;
import com.zhenwen.mapper.RoleMapper;
import com.zhenwen.mapper.UserMapper;
import com.zhenwen.mapper.UserRoleMapper;
import com.zhenwen.security.service.LoginService;
import com.zhenwen.service.RoleService;
import com.zhenwen.service.UserService;
import com.zhenwen.utils.Pager;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    LoginService loginService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public Pager<User> findByPager(int page, int size) {
        return null;
    }

    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean insert(User user) {
        return insert(user, null);
    }

    private Boolean insert(User user, String roleCode) {
        int result = -1;

        user.setAccount("ktp" + user.getTel());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.OK.getCode());

        result = userMapper.insertSelective(user);

        if (result <= 0) {
            return false;
        }

        if (StringUtils.isNull(roleCode)) {
            return true;
        }

        Role role;
        UserRole userRole;

        switch (roleCode) {
            case UserConstants.TCH:
                role = roleMapper.selectByCode(UserConstants.TCH);
                userRole = new UserRole(user.getUserId(), role.getRoleId());

                result = userRoleMapper.insert(userRole);

                return result > 0;
            case UserConstants.STU:
                role = roleMapper.selectByCode(UserConstants.STU);

                userRole = new UserRole(user.getUserId(), role.getRoleId());

                result = userRoleMapper.insert(userRole);

                return result > 0;
            default:
                return false;
        }
    }

    @Override
    public Boolean insertTchUser(User user) {
        return insert(user, UserConstants.TCH);
    }

    @Override
    public Boolean insertStuUser(User user) {
        return insert(user, UserConstants.STU);
    }

    @Override
    public Boolean updateById(User user) {
        int result = userMapper.updateByPrimaryKey(user);
        return result > 0;
    }

    @Override
    public Boolean deleteById(int id) {
        int result = userMapper.deleteByPrimaryKey(id);
        return result > 0;
    }

    @Override
    public User selectByAccountTelEmail(String account) {
        return userMapper.selectByAccountTelEmail(account);
    }

    @Override
    public String selectRoleCodeByCrseId(Integer crseId) {
        User user = (User) loginService.getInfo().get("user");

        return roleService.selectRoleCodeByUserIdCrseId(user.getUserId(), crseId);
    }
}
