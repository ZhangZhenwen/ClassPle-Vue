package com.zhenwen.service.impl;

import com.zhenwen.domain.Task;
import com.zhenwen.domain.TaskFile;
import com.zhenwen.domain.User;
import com.zhenwen.domain.UserCrse;
import com.zhenwen.mapper.TaskFileMapper;
import com.zhenwen.mapper.TaskMapper;
import com.zhenwen.mapper.UserCrseMapper;
import com.zhenwen.security.service.LoginService;
import com.zhenwen.service.*;
import com.zhenwen.utils.ApplicationUtils;
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
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TaskFileMapper taskFileMapper;

    @Autowired
    UserCrseMapper userCrseMapper;

    @Autowired
    FileService fileService;

    @Autowired
    LoginService loginService;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Pager<Task> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Task findById(int id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Task> findTaskListByAsgnId(Integer asgnId) {
        User user = (User) loginService.getInfo().get("user");

        Integer crseId = assignmentService.findCourseIdByAsgnId(asgnId);

        String roleCode = roleService.selectRoleCodeByUserIdCrseId(user.getUserId(), crseId);

        // 学生直接返回自己的作业
        if (!ApplicationUtils.isTch(roleCode)) {
            return taskMapper.selectByAsgnIdUserId(asgnId, user.getUserId());
        }

        List<Task> taskList = taskMapper.selectByAsgnId(asgnId);

        for (Task task : taskList) {
            UserCrse userCrse = userCrseMapper.selectByPrimaryKey(task.getUserId(), crseId);

            HashMap<String, Object> params = new HashMap<>(2);

            params.put("name", userCrse.getName());
            params.put("schoolNo", userCrse.getSchoolNo());

            task.setParams(params);
        }

        return taskList;
    }

    @Override
    public Boolean insert(Task task) {
        User user = (User) loginService.getInfo().get("user");

        task.setUserId(user.getUserId());
        task.setUpdatedCount(0);
        task.setRepeatRate(0);
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());

        task.setIsDeleted(false);
        task.setIsReturn(false);

        updateFiles(task);

        taskMapper.insert(task);

        return updateFiles(task);
    }

    @Override
    public Boolean deleteById(int id) {
        taskFileMapper.deleteByTaskId(id);
        return taskMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean updateById(Task task) {
        task.setUpdatedCount(task.getUpdatedCount() + 1);

        task.setUpdateTime(new Date());
        updateFiles(task);

        return taskMapper.updateByPrimaryKey(task) > 0;
    }

    private Boolean updateFiles(Task task) {
        taskFileMapper.deleteByTaskId(task.getTaskId());

        if (StringUtils.isNotNull(task.getParams().get("files"))) {
            List<Integer> ids = (ArrayList<Integer>) task.getParams().get("files");

            if (ids.size() > 0) {
                for (Integer id : ids) {
                    if (id == null) {
                        continue;
                    }

                    TaskFile taskFile = new TaskFile(task.getTaskId(), id);
                    taskFileMapper.insert(taskFile);
                }
            }
        }

        return true;
    }

    @Override
    public Integer selectTaskCountByAsgnId(Integer asgnId) {
        return taskMapper.selectTaskCountByAsgnId(asgnId);
    }

    @Override
    public Integer selectTaskHaveScoreCountByAsgnId(Integer asgnId) {
        return taskMapper.selectTaskHaveScoreCountByAsgnId(asgnId);
    }

    @Override
    public Task findByAsgnId(Integer asgnId) {
        User user = (User) loginService.getInfo().get("user");


        List<Task> taskList = taskMapper.selectByAsgnIdUserId(asgnId, user.getUserId());

        if (taskList.size() == 0) {
            return null;
        }

        return taskList.get(0);
    }

    @Override
    public Boolean checkTask(Task task) {
        return taskMapper.updateByPrimaryKeySelective(task) > 0;
    }
}
