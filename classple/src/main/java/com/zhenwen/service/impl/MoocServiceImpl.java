package com.zhenwen.service.impl;

import com.zhenwen.domain.Mooc;
import com.zhenwen.mapper.MoocMapper;
import com.zhenwen.service.MoocService;
import com.zhenwen.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class MoocServiceImpl implements MoocService {

    @Autowired
    MoocMapper moocMapper;

    @Override
    public List<Mooc> findAll() {
        return null;
    }

    @Override
    public Pager<Mooc> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Mooc findById(int id) {
        return moocMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean insert(Mooc mooc) {
        return null;
    }

    @Override
    public Boolean updateById(Mooc mooc) {
        return null;
    }

    @Override
    public Boolean deleteById(int id) {
        return null;
    }
}
