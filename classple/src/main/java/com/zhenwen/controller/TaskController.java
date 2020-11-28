package com.zhenwen.controller;

import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.domain.Task;
import com.zhenwen.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenwen
 * @date 2020/11/27
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/submit")
    public AjaxResult submit(Task task) {
        taskService.insert(task);

        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public AjaxResult updateTask(Task task) {
        taskService.updateById(task);

        return AjaxResult.success();
    }

    @GetMapping("/correct")
    public AjaxResult correct(Integer crseId, Integer asgnId) {
        return AjaxResult.success(taskService.findByAsgnId(crseId, asgnId));
    }

    @GetMapping("/detail")
    public AjaxResult detail(Integer id) {
        return AjaxResult.success(taskService.findById(id));
    }
}
