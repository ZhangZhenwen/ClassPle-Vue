package com.zhenwen.service;

import com.zhenwen.domain.Comment;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface CommentService {

    /**
     * 查找所有
     * @return list
     */
    List<Comment> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Comment> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return Comment
     */
    Comment findById(int id);

    /**
     * 添加
     * @param comment comment
     * @return bool
     */
    Boolean insert(Comment comment);

    /**
     * 更新
     * @param comment comment
     * @return bool
     */
    Boolean updateById(Comment comment);

    /**
     * 删除
     * @param id Comment ID
     * @return bool
     */
    Boolean deleteById(int id);
}
