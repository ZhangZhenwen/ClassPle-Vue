package com.zhenwen.service;

import com.zhenwen.domain.Task;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface TaskService {

    /**
     * 查找所有
     * @return list
     */
    List<Task> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Task> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return Task
     */
    Task findById(int id);

    /**
     * 根据作业ID查找作业
     *
     * @param asgnId
     * @param crseId
     * @return
     */
    List<Task> findByAsgnId(Integer crseId, Integer asgnId);

    /**
     * 添加
     * @param task task
     * @return bool
     */
    Boolean insert(Task task);

    /**
     * 更新
     * @param task task
     * @return bool
     */
    Boolean updateById(Task task);

    /**
     * 删除
     * @param id Task ID
     * @return bool
     */
    Boolean deleteById(int id);
}
