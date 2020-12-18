package com.zhenwen.security.handle;

import com.alibaba.fastjson.JSON;
import com.zhenwen.common.constant.HttpStatus;
import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.security.LoginUser;
import com.zhenwen.security.service.TokenService;
import com.zhenwen.utils.ServletUtils;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhenwen
 * @date 2020/11/10
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);

        if (StringUtils.isNotNull(loginUser)) {
            String username = loginUser.getUsername();
            tokenService.delLoginUser(loginUser.getToken());
        }

        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
