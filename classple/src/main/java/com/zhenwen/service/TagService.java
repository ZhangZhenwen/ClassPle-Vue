package com.zhenwen.service;

import com.zhenwen.domain.Tag;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface TagService {

    /**
     * 查找所有
     * @return list
     */
    List<Tag> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Tag> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return Tag
     */
    Tag findById(int id);

    /**
     * 添加
     * @param tag tag
     * @return bool
     */
    Boolean insert(Tag tag);

    /**
     * 更新
     * @param tag tag
     * @return bool
     */
    Boolean updateById(Tag tag);

    /**
     * 删除
     * @param id Tag ID
     * @return bool
     */
    Boolean deleteById(int id);
}
