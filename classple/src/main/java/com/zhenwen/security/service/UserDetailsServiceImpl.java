package com.zhenwen.security.service;

import com.zhenwen.common.constant.UserStatus;
import com.zhenwen.common.exception.BaseException;
import com.zhenwen.domain.User;
import com.zhenwen.security.LoginUser;
import com.zhenwen.service.UserService;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zhenwen
 * @date 2020/11/11
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByAccountTelEmail(username);
        if (StringUtils.isNull(user))
        {
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(User user) {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
