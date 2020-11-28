package com.zhenwen.service.impl;

import com.zhenwen.domain.Tag;
import com.zhenwen.service.TagService;
import com.zhenwen.utils.Pager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class TagServiceImpl implements TagService {
    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public Pager<Tag> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Tag findById(int id) {
        return null;
    }

    @Override
    public Boolean insert(Tag tag) {
        return null;
    }

    @Override
    public Boolean updateById(Tag tag) {
        return null;
    }

    @Override
    public Boolean deleteById(int id) {
        return null;
    }
}
