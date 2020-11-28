package com.zhenwen.service.impl;

import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.domain.*;
import com.zhenwen.mapper.CourseMapper;
import com.zhenwen.mapper.CrseFileMapper;
import com.zhenwen.mapper.UserCrseMapper;
import com.zhenwen.security.service.LoginService;
import com.zhenwen.service.CourseService;
import com.zhenwen.service.UserService;
import com.zhenwen.utils.Pager;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CrseFileMapper crseFileMapper;

    @Autowired
    UserService userService;

    @Autowired
    UserCrseMapper userCrseMapper;

    @Autowired
    LoginService loginService;

    @Override
    public List<Course> findCommonCoursesByUserId(User user) {
        List<Integer> crseIds = new ArrayList<>();

        List<UserCrse> userCrses = userCrseMapper.selectCoursesByUserId(user.getUserId());

        for (UserCrse userCrse : userCrses) {
            if (userCrse.getIsDeleted() || userCrse.getIsFile() || userCrse.getIsDropOut()) {
                continue;
            }
            crseIds.add(userCrse.getCrseId());

        }

        if (crseIds.size() == 0) {
            return new ArrayList<>();
        }

        return courseMapper.findCommonCoursesByCrseIds(crseIds);
    }

    @Override
    public List<Course> findCommonCoursesByUser() {
        User user = (User) loginService.getInfo().get("user");
        return findCommonCoursesByUserId(user);
    }

    @Override
    public List<Course> findFiledCoursesByUserId(User user) {
        List<Integer> crseIds = new ArrayList<>();

        List<UserCrse> userCrses = userCrseMapper.selectCoursesByUserId(user.getUserId());

        // 删除或退课
        for (UserCrse userCrse : userCrses) {
            if (userCrse.getIsDeleted() || userCrse.getIsDropOut()) {
                continue;
            }

            crseIds.add(userCrse.getCrseId());

        }

        if (crseIds.size() == 0) {
            return new ArrayList<>();
        }

        return courseMapper.findFiledCoursesByCrseIds(crseIds);
    }

    @Override
    public List<Course> findFiledCoursesByUser() {
        User user = (User) loginService.getInfo().get("user");
        return findFiledCoursesByUserId(user);
    }

    @Override
    public List<Course> findAll() {
        return courseMapper.findAllCourse();
    }

    @Override
    public Pager<Course> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Course findById(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean insert(Course course) {
        User user = (User) loginService.getInfo().get("user");
        int count = findCommonCoursesByUser().size();
        courseMapper.insertSelective(course);

        UserCrse userCrse = new UserCrse(user);
        userCrse.setUserId(user.getUserId());
        userCrse.setUserRole(UserConstants.ADMIN);
        userCrse.setCrseId(course.getCrseId());
        userCrse.setSortIndex(count + 1);

        userCrseMapper.insert(userCrse);

        return true;
    }

    @Override
    @PreAuthorize("@ss.hasRole('tch', course.crseId)")
    public Boolean updateById(Course course) {
        int result = courseMapper.updateByPrimaryKeySelective(course);
        return result > 0;
    }

    @Override
    public Boolean deleteById(int id) {
        int result = courseMapper.deleteByPrimaryKey(id);
        return result > 0;
    }

    @Override
    public Boolean joinCourse(String code) {
        User user = (User) loginService.getInfo().get("user");
        Course course = courseMapper.findCourseByCourseCode(code);
        int stuNum = findStuNum(course.getCrseId());

        // 加课人数超过上限
        if (StringUtils.isNotNull(course.getMaxMember()) && course.getMaxMember() > 0
                && course.getMaxMember() <= stuNum) {
            return false;
        }

        UserCrse userCrse = new UserCrse(user);
        userCrse.setUserId(user.getUserId());
        userCrse.setUserRole(UserConstants.STU);
        userCrse.setCrseId(course.getCrseId());

        return userCrseMapper.insert(userCrse) > 0;
    }

    @Override
    public Boolean inviteCourse(Integer id, String code) {
        Course course = courseMapper.selectByPrimaryKey(id);

        String[] codes = code.trim().split("\n");

        for (String s : codes) {
            User user = userService.selectByAccountTelEmail(s);

            UserCrse userCrse = new UserCrse(user);
            userCrse.setUserId(user.getUserId());
            userCrse.setUserRole(UserConstants.TCH);
            userCrse.setCrseId(course.getCrseId());

            userCrseMapper.insert(userCrse);
        }

        return null;
    }

    @Override
    public Integer findStuNum(Integer id) {
        return findRoleCodeNum(id, UserConstants.STU);
    }

    @Override
    public Integer findTchNum(Integer id) {
        return findRoleCodeNum(id, UserConstants.TCH) + 1;
    }

    private Integer findRoleCodeNum(Integer id, String code) {
        int count = 0;

        List<Integer> ids = userCrseMapper.selectUserIdByCrseId(id);

        for (Integer i : ids) {
            User user = userService.findById(i);
            Role role = user.getRoles().get(0);

            if (role.getRoleCode().equals(code)) {
                count++;
            }
        }

        return count;
    }
}
