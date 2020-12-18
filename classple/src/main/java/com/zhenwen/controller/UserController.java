package com.zhenwen.controller;

import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.service.AssignmentService;
import com.zhenwen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhenwen
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AssignmentService assignmentService;

    @PostMapping("getRoleByAsgnId")
    public AjaxResult getRoleByAsgnId(@RequestBody Integer id) {
        int crseId = assignmentService.findCourseIdByAsgnId(id);

        String code = userService.selectRoleCodeByCrseId(crseId);

        return AjaxResult.success(code);
    }
}
