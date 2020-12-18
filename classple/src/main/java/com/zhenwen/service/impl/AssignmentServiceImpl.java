package com.zhenwen.service.impl;

import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.domain.*;
import com.zhenwen.mapper.AsgnFileMapper;
import com.zhenwen.mapper.AssignmentMapper;
import com.zhenwen.mapper.CrseAsgnMapper;
import com.zhenwen.security.service.LoginService;
import com.zhenwen.service.*;
import com.zhenwen.utils.Pager;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    AssignmentMapper assignmentMapper;

    @Autowired
    AsgnFileMapper asgnFileMapper;

    @Autowired
    CrseAsgnMapper crseAsgnMapper;

    @Autowired
    FileService fileService;

    @Autowired
    TaskService taskService;

    @Autowired
    RoleService roleService;

    @Autowired
    LoginService loginService;

    @Autowired
    CourseService courseService;

    @Override
    public List<Assignment> findAll() {
        return null;
    }

    @Override
    public Pager<Assignment> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Assignment findById(int id) {
        return assignmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Assignment> findByCourseId(Integer crseId) {
        return assignmentMapper.selectByCourseId(crseId);
    }

    @Override
    public Integer findCourseIdByAsgnId(Integer asgnId) {
        return crseAsgnMapper.selectCrseIdByAsgnId(asgnId);
    }

    @Override
    public Boolean insert(Assignment assignment) {
        assignment.setReleasedDate(new Date());
        assignmentMapper.insert(assignment);

        if (StringUtils.isNotNull(assignment.getParams().get("crseId"))) {
            Integer crseId = Integer.parseInt((String) assignment.getParams().get("crseId"));

            CrseAsgn crseAsgn = new CrseAsgn(crseId, assignment.getAsgnId());
            crseAsgnMapper.insert(crseAsgn);
        }

        return insertFileList(assignment);
    }

    @Override
    public Boolean updateById(Assignment assignment) {
        assignmentMapper.updateByPrimaryKey(assignment);
        asgnFileMapper.deleteByAsgnId(assignment.getAsgnId());

        return insertFileList(assignment);
    }

    private Boolean insertFileList(Assignment assignment) {
        if (StringUtils.isNotNull(assignment.getParams().get("files"))) {
            ArrayList<Integer> ids = (ArrayList<Integer>) assignment.getParams().get("files");

            for (Integer id : ids) {
                AsgnFile asgnFile = new AsgnFile(assignment.getAsgnId(), id);

                asgnFileMapper.insert(asgnFile);
            }

        }

        return true;
    }

    @Override
    public Boolean deleteById(int id) {
        Assignment assignment = assignmentMapper.selectByPrimaryKey(id);

        assignment.setIsDeleted(true);

        return assignmentMapper.updateByPrimaryKey(assignment) > 0;
    }

    @Override
    public List<Assignment> selectAssignmentByCrseId(Integer crseId) {
        //扮演老师
        if (roleService.checkRoleInCourse(crseId, UserConstants.TCH + UserConstants.ADMIN)) {
            return selectAssignmentForTch(crseId);
        } else {
            return selectAssignmentForStu(crseId);
        }
    }

    private List<Assignment> selectAssignmentForTch(Integer crseId) {
        List<Assignment> assignmentList = assignmentMapper.selectByCourseId(crseId);

        for (Assignment assignment : assignmentList) {
            HashMap<String, Object> params = new HashMap<>();

            int submitTaskCount = taskService.selectTaskCountByAsgnId(assignment.getAsgnId());

            int checkTaskCount = taskService.selectTaskHaveScoreCountByAsgnId(assignment.getAsgnId());
            int uncheckTaskCount = submitTaskCount - checkTaskCount;
            int unpaidTaskCount = courseService.findStuNum(crseId) - submitTaskCount;

            params.put("check", checkTaskCount);
            params.put("uncheck", uncheckTaskCount);
            params.put("unpaid", unpaidTaskCount);

            assignment.setParams(params);
        }

        return assignmentList;
    }

    private List<Assignment> selectAssignmentForStu(Integer crseId) {
        User user = (User) loginService.getInfo().get("user");
        List<Assignment> assignmentList = assignmentMapper.selectByCourseId(crseId);

        for (Assignment assignment : assignmentList) {
            HashMap<String, Object> params = new HashMap<>();

            List<Task> taskList = taskService.findTaskListByAsgnId(assignment.getAsgnId());

            if (StringUtils.isNotNull(taskList) && taskList.size() != 0) {
                if (StringUtils.isNotNull(taskList.get(0).getScore())) {
                    params.put("status", taskList.get(0).getScore());
                } else {
                    params.put("status", "已提交");
                }
            }

            assignment.setParams(params);
        }

        return assignmentList;
    }

    @Override
    public List<UserCrse> selectUserCrseByAsgnId(Integer asgnId) {
        Integer crseId = findCourseIdByAsgnId(asgnId);

        List<UserCrse> userCrseList = courseService.selectUserListByCrseId(crseId);

        return userCrseList;
    }
}
