package com.zhenwen.security.service;

import com.zhenwen.common.exception.CustomException;
import com.zhenwen.common.exception.user.UserPasswordNotMatchException;
import com.zhenwen.domain.User;
import com.zhenwen.security.LoginUser;
import com.zhenwen.utils.ServletUtils;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhenwen
 * @date 2020/11/10
 */
@Component
public class LoginService {

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    PermissionService permissionService;

    /**
     * 登录验证
     *
     * @param username 账户
     * @param password 密码
     * @param code 验证码
     * @param uuid 标识符
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
//        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
//        String captcha = redisCache.getCacheObject(verifyKey);
//        redisCache.deleteObject(verifyKey);
//
//        if (captcha == null) {
//            throw new CaptchaExpireException();
//        }
//
//        if (!code.equalsIgnoreCase(captcha)) {
//            throw new CaptchaException();
//        }

        Authentication authentication = null;

        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e){
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(loginUser);
    }

    /**
     * 获取用户信息
     * @return info
     */
    public Map<String, Object> getInfo() {
        Map<String, Object> infos = new HashMap<>(4);

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        if (StringUtils.isNotNull(loginUser)) {
            User user = loginUser.getUser();
            // 角色集合
            Set<String> roles = permissionService.getRolePermission(user);
            // 权限集合
            Set<String> permissions = permissionService.getMenuPermission(user);

            infos.put("user", user);
            infos.put("roles", roles);
            infos.put("permissions", permissions);
        }

        return infos;
    }
}
