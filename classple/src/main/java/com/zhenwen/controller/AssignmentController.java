package com.zhenwen.controller;

import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.domain.Assignment;
import com.zhenwen.service.AssignmentService;
import com.zhenwen.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenwen
 * @date 2020/11/18
 */
@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    TaskService taskService;

    @PostMapping("/post")
    public AjaxResult addAssignment(Assignment assignment, Integer id) {
        assignmentService.insert(assignment);

        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public AjaxResult updateAssignment(Assignment assignment) {
        assignmentService.updateById(assignment);

        return AjaxResult.success();
    }

    @GetMapping("/detail")
    public AjaxResult getAssignment(Integer id) {
        return AjaxResult.success(assignmentService.findById(id));
    }
}
