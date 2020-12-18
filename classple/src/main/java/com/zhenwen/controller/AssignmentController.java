package com.zhenwen.controller;

import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.domain.Assignment;
import com.zhenwen.service.AssignmentService;
import com.zhenwen.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public AjaxResult addAssignment(@RequestBody Assignment assignment) {
        assignmentService.insert(assignment);

        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public AjaxResult updateAssignment(@RequestBody Assignment assignment) {
        assignmentService.updateById(assignment);

        return AjaxResult.success();
    }

    @PostMapping("/delete")
    public AjaxResult deleteAssignment(@RequestBody Integer id) {
        return assignmentService.deleteById(id) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/detail")
    public AjaxResult getAssignment(@RequestBody Integer id) {
        return AjaxResult.success(assignmentService.findById(id));
    }

    @PostMapping("/list")
    public AjaxResult getAssignmentList(@RequestBody Integer id) {
        return AjaxResult.success(assignmentService.selectAssignmentByCrseId(id));
    }

    @PostMapping("/userList")
    public AjaxResult getUserList(@RequestBody Integer id) {
        return AjaxResult.success(assignmentService.selectUserCrseByAsgnId(id));
    }
}
