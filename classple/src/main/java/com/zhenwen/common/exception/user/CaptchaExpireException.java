package com.zhenwen.common.exception.user;

/**
 * @author zhenwen
 * @date 2020/11/10
 */

public class CaptchaExpireException extends UserException {

    public CaptchaExpireException() {
        super("user.captcha.expire", null);
    }
}
