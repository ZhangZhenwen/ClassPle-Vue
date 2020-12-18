package com.zhenwen.controller;

import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.domain.Task;
import com.zhenwen.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public AjaxResult submit(@RequestBody Task task) {
        taskService.insert(task);

        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public AjaxResult updateTask(@RequestBody Task task) {
        taskService.updateById(task);

        return AjaxResult.success();
    }

    @PostMapping("/check")
    public AjaxResult checkTask(@RequestBody Task task) {
        return AjaxResult.success(taskService.checkTask(task));
    }

    @PostMapping("/correct")
    public AjaxResult correct(@RequestBody Integer asgnId) {
        return AjaxResult.success(taskService.findTaskListByAsgnId(asgnId));
    }

    @PostMapping("/detail")
    public AjaxResult detail(@RequestBody Integer id) {
        return AjaxResult.success(taskService.findById(id));
    }

    @PostMapping("/detailByAsgnId")
    public AjaxResult detailByAsgnId(@RequestBody Integer id) {
        return AjaxResult.success(taskService.findByAsgnId(id));
    }
}
