package com.zhenwen.common.exception.user;

import com.zhenwen.common.exception.BaseException;

/**
 * @author zhenwen
 * @date 2020/11/10
 */

public class UserException extends BaseException {
    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
