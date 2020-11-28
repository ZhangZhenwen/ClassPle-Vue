package com.zhenwen.service;

import com.zhenwen.domain.Topic;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface TopicService {

    /**
     * 查找所有
     * @return list
     */
    List<Topic> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Topic> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return Topic
     */
    Topic findById(int id);

    /**
     * 添加
     * @param topic topic
     * @return bool
     */
    Boolean insert(Topic topic);

    /**
     * 更新
     * @param topic topic
     * @return bool
     */
    Boolean updateById(Topic topic);

    /**
     * 删除
     * @param id Topic ID
     * @return bool
     */
    Boolean deleteById(int id);
}
