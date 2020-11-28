package com.zhenwen.common.web.controller;

import com.zhenwen.common.web.domain.AjaxResult;

/**
 * @author zhenwen
 * @date 2020/11/16
 */

public class BaseController {

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
