package com.zhenwen.service.impl;

import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.domain.Course;
import com.zhenwen.domain.Role;
import com.zhenwen.domain.User;
import com.zhenwen.domain.UserCrse;
import com.zhenwen.mapper.CourseMapper;
import com.zhenwen.mapper.CrseFileMapper;
import com.zhenwen.mapper.UserCrseMapper;
import com.zhenwen.security.service.LoginService;
import com.zhenwen.service.CourseService;
import com.zhenwen.service.RoleService;
import com.zhenwen.service.UserService;
import com.zhenwen.utils.Pager;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    RoleService roleService;

    @Autowired
    LoginService loginService;

    private List<Course> findCommonCoursesByUserId(User user) {
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

        List<Course> courseList = new ArrayList<>();

        for (Integer id : crseIds) {
            courseList.add(findById(id, user));
        }

        return courseList;
    }

    @Override
    public List<Course> findCommonCoursesByUser() {
        User user = (User) loginService.getInfo().get("user");
        return findCommonCoursesByUserId(user);
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
        User user = (User) loginService.getInfo().get("user");

        String roleCode = roleService.selectRoleCodeByUserIdCrseId(user.getUserId(), id);

        return putRoleCodeInCourse(id, roleCode);
    }

    public Course findById(int id, User user) {
        String roleCode = roleService.selectRoleCodeByUserIdCrseId(user.getUserId(), id);

        return putRoleCodeInCourse(id, roleCode);
    }

    private Course putRoleCodeInCourse(int id, String roleCode) {
        Course course = courseMapper.selectByPrimaryKey(id);

        HashMap<String, Object> params = new HashMap<>();
        Integer adminId = userCrseMapper.selectUserIdByCrseIdRoleCode(id, "admin").get(0);

        params.put("memberCount", userCrseMapper.selectUserCountByCrseId(id));
        params.put("role", roleCode);
        params.put("admin", userService.findById(adminId).getUserName());

        course.setParams(params);
        return course;
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
        userCrse.setSortIndex(userCrseMapper.selectUserCountByCrseId(course.getCrseId()) + 1);

        return userCrseMapper.insert(userCrse) > 0;
    }

    @Override
    public Boolean inviteCourse(Integer id, String code) {
        Course course = courseMapper.selectByPrimaryKey(id);

        String[] codes = code.trim().split("\n");

        for (String s : codes) {
            User user = userService.selectByAccountTelEmail(s);

            if (user.getRoles().get(0).getRoleCode().equals(UserConstants.STU)) {
                return false;
            }

            UserCrse userCrse = new UserCrse(user);
            userCrse.setUserId(user.getUserId());
            userCrse.setSortIndex(userCrseMapper.selectCoursesByUserId(user.getUserId()).size() + 1);
            userCrse.setUserRole(UserConstants.TCH);
            userCrse.setCrseId(course.getCrseId());

            userCrseMapper.insert(userCrse);
        }

        return true;
    }

    @Override
    public Boolean dropoutCourse(Integer id) {
        User user = (User) loginService.getInfo().get("user");
        Course course = courseMapper.selectByPrimaryKey(id);

        if (!course.getAllowDropOut()) {
            return false;
        }

        UserCrse userCrse = userCrseMapper.selectByPrimaryKey(user.getUserId(), id);
        userCrse.setOutDate(new Date());
        userCrse.setIsDropOut(true);
        userCrseMapper.updateByPrimaryKey(userCrse);
        return true;
    }

    @Override
    public Integer findStuNum(Integer id) {
        return findRoleCodeNum(id, UserConstants.STU);
    }

    @Override
    public Integer findTchNum(Integer id) {
        return findRoleCodeNum(id, UserConstants.TCH);
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

    @Override
    public List<UserCrse> selectUserListByCrseId(Integer crseId) {
        List<UserCrse> userCrseList = userCrseMapper.selectUserCrseByCrseId(crseId);

        for (UserCrse userCrse : userCrseList) {
            User user = userService.findById(userCrse.getUserId());

            HashMap<String, Object> params = new HashMap<>(2);

            params.put("account", user.getAccount());

            userCrse.setParams(params);
        }

        return userCrseList;
    }
}
