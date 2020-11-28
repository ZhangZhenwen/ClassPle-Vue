package com.zhenwen.service.impl;

import com.zhenwen.domain.Notice;
import com.zhenwen.service.NoticeService;
import com.zhenwen.utils.Pager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Override
    public List<Notice> findAll() {
        return null;
    }

    @Override
    public Pager<Notice> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Notice findById(int id) {
        return null;
    }

    @Override
    public Boolean insert(Notice notice) {
        return null;
    }

    @Override
    public Boolean updateById(Notice notice) {
        return null;
    }

    @Override
    public Boolean deleteById(int id) {
        return null;
    }
}
