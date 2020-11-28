package com.zhenwen.controller;

import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.common.web.controller.BaseController;
import com.zhenwen.service.MoocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenwen
 * @date 2020/11/16
 */

@RestController
@RequestMapping("/mooc")
public class MoocController extends BaseController {

    @Autowired
    MoocService moocService;

    @PostMapping("/list")
    @PreAuthorize("@ss.hasPermi('mooc:list')")
    public AjaxResult getAllMooc() {
        return AjaxResult.success(moocService.findAll());
    }

    @PostMapping("/getMooc")
    public AjaxResult getMooc(Integer id) {
        return AjaxResult.success(moocService.findById(id));
    }

}
