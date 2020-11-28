package com.zhenwen.controller;

import com.zhenwen.common.constant.Constants;
import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.domain.User;
import com.zhenwen.security.LoginBody;
import com.zhenwen.security.service.LoginService;
import com.zhenwen.security.service.TokenService;
import com.zhenwen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenwen
 * @date 2020/11/10
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public AjaxResult login(LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();

        String token = loginService.login(
                loginBody.getUsername(),
                loginBody.getPassword(),
                loginBody.getCode(),
                loginBody.getUuid());

        ajax.put(Constants.TOKEN, token);

        return ajax;
    }

    @PostMapping("/register/tch")
    public AjaxResult registerTch(User user) {
        return userService.insertTchUser(user) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/register/stu")
    public AjaxResult registerStu(User user) {
        return userService.insertStuUser(user) ? AjaxResult.success() : AjaxResult.error();
    }
}
