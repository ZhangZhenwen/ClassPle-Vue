package com.zhenwen.common.exception.user;

/**
 * @author zhenwen
 * @date 2020/11/10
 */

public class CaptchaException extends UserException {

    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
