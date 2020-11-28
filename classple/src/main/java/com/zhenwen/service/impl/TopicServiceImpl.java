package com.zhenwen.service.impl;

import com.zhenwen.domain.Topic;
import com.zhenwen.service.TopicService;
import com.zhenwen.utils.Pager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Override
    public List<Topic> findAll() {
        return null;
    }

    @Override
    public Pager<Topic> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Topic findById(int id) {
        return null;
    }

    @Override
    public Boolean insert(Topic topic) {
        return null;
    }

    @Override
    public Boolean updateById(Topic topic) {
        return null;
    }

    @Override
    public Boolean deleteById(int id) {
        return null;
    }
}
