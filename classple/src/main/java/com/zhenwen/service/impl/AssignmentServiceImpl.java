package com.zhenwen.service.impl;

import com.zhenwen.domain.AsgnFile;
import com.zhenwen.domain.Assignment;
import com.zhenwen.domain.CrseAsgn;
import com.zhenwen.domain.Task;
import com.zhenwen.mapper.AsgnFileMapper;
import com.zhenwen.mapper.AssignmentMapper;
import com.zhenwen.mapper.CrseAsgnMapper;
import com.zhenwen.service.AssignmentService;
import com.zhenwen.service.FileService;
import com.zhenwen.service.TaskService;
import com.zhenwen.utils.Pager;
import com.zhenwen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Boolean insert(Assignment assignment) {
        assignmentMapper.insert(assignment);

        if (StringUtils.isNotNull(assignment.getParams().get("crseId"))) {
            Integer crseId = (Integer) assignment.getParams().get("crseId");

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
        if (StringUtils.isNotNull(assignment.getFiles())) {
            List<Integer> ids = fileService.upload(assignment.getFiles());

            for (Integer id : ids) {
                AsgnFile asgnFile = new AsgnFile(assignment.getAsgnId(), id);
                asgnFileMapper.insert(asgnFile);
            }
        }

        return true;
    }

    @Override
    public Boolean deleteById(int id) {
        return assignmentMapper.deleteByPrimaryKey(id) > 0;
    }
}
