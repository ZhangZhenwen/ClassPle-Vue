package com.zhenwen.service.impl;

import com.zhenwen.domain.Comment;
import com.zhenwen.service.CommentService;
import com.zhenwen.utils.Pager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Pager<Comment> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Comment findById(int id) {
        return null;
    }

    @Override
    public Boolean insert(Comment comment) {
        return null;
    }

    @Override
    public Boolean updateById(Comment comment) {
        return null;
    }

    @Override
    public Boolean deleteById(int id) {
        return null;
    }
}
