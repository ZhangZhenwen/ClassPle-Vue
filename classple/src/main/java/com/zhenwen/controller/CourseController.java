package com.zhenwen.controller;

import com.zhenwen.common.constant.HttpStatus;
import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.domain.Course;
import com.zhenwen.security.service.AuthorizeService;
import com.zhenwen.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author zhenwen
 * @date 2020/11/17
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("/list")
    public AjaxResult getCommonCourses() {
        AjaxResult ajax = AjaxResult.success();

        ajax.put(AjaxResult.DATA_TAG, courseService.findCommonCoursesByUser());

        return ajax;
    }

    @PreAuthorize("@ss.hasPermi('course:create')")
    @PostMapping("/create")
    public AjaxResult createCourse(@RequestBody Course course) {
        return courseService.insert(course) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/join")
    public AjaxResult joinCourse(@RequestBody String code) {
        return courseService.joinCourse(code) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/invite")
    public AjaxResult inviteCourse(@RequestBody HashMap<String, Object> params) {
        Integer id = Integer.parseInt((String) params.get("id"));
        String code = (String) params.get("code");
        return courseService.inviteCourse(id, code) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/exit")
    public AjaxResult exitCourse(@RequestBody Integer id) {
        return courseService.dropoutCourse(id) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/edit")
    public AjaxResult editCourse(@RequestBody Course course) {
        if (authorizeService.hasRole(UserConstants.TCH, course.getCrseId())) {
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, "权限不足");
        }

        return courseService.updateById(course) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/detail")
    public AjaxResult getDetail(@RequestBody Integer id) {
        return AjaxResult.success(courseService.findById(id));
    }

    @PostMapping("/count/stu")
    public AjaxResult studentCount(@RequestBody Integer id) {
        return AjaxResult.success(courseService.findStuNum(id));
    }

    @PostMapping("/count/tch")
    public AjaxResult teacherCount(@RequestBody Integer id) {
        return AjaxResult.success(courseService.findTchNum(id));
    }

    @PostMapping("/userList")
    public AjaxResult getUserList(@RequestBody Integer crseId) {
        return AjaxResult.success(courseService.selectUserListByCrseId(crseId));
    }
}
