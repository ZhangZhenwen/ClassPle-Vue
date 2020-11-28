package com.zhenwen.service.impl;

import com.zhenwen.common.constant.UserConstants;
import com.zhenwen.domain.Task;
import com.zhenwen.domain.TaskFile;
import com.zhenwen.domain.User;
import com.zhenwen.mapper.TaskFileMapper;
import com.zhenwen.mapper.TaskMapper;
import com.zhenwen.security.service.TokenService;
import com.zhenwen.service.FileService;
import com.zhenwen.service.RoleService;
import com.zhenwen.service.TaskService;
import com.zhenwen.service.UserService;
import com.zhenwen.utils.Pager;
import com.zhenwen.utils.ServletUtils;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    FileService fileService;

    @Autowired
    TokenService tokenService;

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
    public List<Task> findByAsgnId(Integer crseId, Integer asgnId) {
        User user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();

        String roleCode = roleService.selectRoleCodeByUserIdCrseId(user.getUserId(), crseId);

        if (roleCode.equals(UserConstants.TCH)) {
            return taskMapper.selectByAsgnId(asgnId);
        }

        return taskMapper.selectByAsgnIdUserId(asgnId, user.getUserId());
    }

    @Override
    public Boolean insert(Task task) {
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
        updateFiles(task);

        return taskMapper.updateByPrimaryKey(task) > 0;
    }

    private Boolean updateFiles(Task task) {
        taskFileMapper.deleteByTaskId(task.getTaskId());

        if (StringUtils.isNotNull(task.getFiles())) {
            List<Integer> ids = fileService.upload(task.getFiles());

            for (Integer id : ids) {
                TaskFile taskFile = new TaskFile(task.getTaskId(), id);
                taskFileMapper.insert(taskFile);
            }
        }

        return true;
    }
}
