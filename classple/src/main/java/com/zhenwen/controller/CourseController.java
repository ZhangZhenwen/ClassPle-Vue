package com.zhenwen.controller;

import com.zhenwen.common.constant.HttpStatus;
import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.domain.Course;
import com.zhenwen.security.service.AuthorizeService;
import com.zhenwen.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenwen
 * @date 2020/11/17
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    AuthorizeService authorizeService;

    @GetMapping("/CommonCourses")
    public AjaxResult getCommonCourses() {
        AjaxResult ajax = AjaxResult.success();

        ajax.put(AjaxResult.DATA_TAG, courseService.findCommonCoursesByUser());

        return ajax;
    }

    @GetMapping("/FiledCourses")
    public AjaxResult getFiledCourses() {
        return AjaxResult.success(courseService.findFiledCoursesByUser());
    }

    @PreAuthorize("@ss.hasPermi('course:create')")
    @PostMapping("/create")
    public AjaxResult createCourse(Course course) {
        return courseService.insert(course) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/join")
    public AjaxResult joinCourse(String code) {
        return courseService.joinCourse(code) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/invite")
    public AjaxResult inviteCourse(Integer id, String code) {
        return courseService.inviteCourse(id, code) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/edit")
    public AjaxResult editCourse(Course course) {
        if (authorizeService.hasRole(UserConstants.TCH, course.getCrseId())) {
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, "权限不足");
        }

        return courseService.updateById(course) ? AjaxResult.success() : AjaxResult.error();
    }

    @GetMapping("/count/stu")
    public AjaxResult studentCount(Integer id) {
        return AjaxResult.success(courseService.findStuNum(id));
    }

    @GetMapping("/count/tch")
    public AjaxResult teacherCount(Integer id) {
        return AjaxResult.success(courseService.findTchNum(id));
    }
}
