package com.zhenwen.service.impl;

import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.domain.Course;
import com.zhenwen.domain.User;
import com.zhenwen.domain.UserCrse;
import com.zhenwen.mapper.CourseMapper;
import com.zhenwen.mapper.UserCrseMapper;
import com.zhenwen.security.service.LoginService;
import com.zhenwen.service.ArchiveService;
import com.zhenwen.service.CourseService;
import com.zhenwen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenwen
 * @date 2020/12/1
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    UserCrseMapper userCrseMapper;

    @Autowired
    CourseService courseService;

    @Autowired
    LoginService loginService;

    @Autowired
    RoleService roleService;

    @Override
    public List<Course> findFiledCoursesByUser() {
        User user = (User) loginService.getInfo().get("user");
        List<Integer> crseIds = new ArrayList<>();

        List<UserCrse> userCrses = userCrseMapper.selectCoursesByUserId(user.getUserId());

        // 删除或退课
        for (UserCrse userCrse : userCrses) {
            if (!(userCrse.getIsDeleted() || userCrse.getIsDropOut())) {
                if (userCrse.getIsFile()) {
                    crseIds.add(userCrse.getCrseId());
                }
            }
        }

        List<Course> courseList = new ArrayList<>();

        if (crseIds.size() == 0) {
            return courseList;
        } else {
            for (Integer id : crseIds) {
                courseList.add(courseService.findById(id));
            }
        }

        return courseList;
    }

    @Override
    public Boolean fileCourseForOne(Integer id) {
        User user = (User) loginService.getInfo().get("user");

        return setCourseFileStatus(user.getUserId(), id, true);
    }

    @Override
    public Boolean fileCourseAll(Integer id) {
        User user = (User) loginService.getInfo().get("user");

        if (roleService.checkRoleInCourse(user.getUserId(), id, UserConstants.TCH)) {
            return false;
        }

        Course course = courseMapper.selectByPrimaryKey(id);
        course.setIsFile(true);

        List<Integer> ids  = userCrseMapper.selectUserIdByCrseId(id);

        for (Integer userId : ids) {
            setCourseFileStatus(userId, id, true);
        }

        return true;
    }

    @Override
    public Boolean cancelFileCourseForOne(Integer id) {
        User user = (User) loginService.getInfo().get("user");

        return setCourseFileStatus(user.getUserId(), id, false);
    }

    @Override
    public Boolean cancelFileCourseAll(Integer id) {
        User user = (User) loginService.getInfo().get("user");

        if (roleService.checkRoleInCourse(user.getUserId(), id, UserConstants.TCH)) {
            return false;
        }

        Course course = courseMapper.selectByPrimaryKey(id);
        course.setIsFile(false);

        List<Integer> ids  = userCrseMapper.selectUserIdByCrseId(id);

        for (Integer userId : ids) {
            setCourseFileStatus(userId, id, false);
        }

        return true;
    }

    private Boolean setCourseFileStatus(Integer userId, Integer crseId, Boolean status) {
        UserCrse userCrse = userCrseMapper.selectByPrimaryKey(userId, crseId);
        userCrse.setIsFile(status);

        return userCrseMapper.updateByPrimaryKey(userCrse) > 0;
    }
}
