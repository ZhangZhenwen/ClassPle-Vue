package com.zhenwen.service;

import com.zhenwen.domain.Mooc;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface MoocService {

    /**
     * 查找所有
     * @return list
     */
    List<Mooc> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Mooc> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return Mooc
     */
    Mooc findById(int id);

    /**
     * 添加
     * @param mooc mooc
     * @return bool
     */
    Boolean insert(Mooc mooc);

    /**
     * 更新
     * @param mooc mooc
     * @return bool
     */
    Boolean updateById(Mooc mooc);

    /**
     * 删除
     * @param id Mooc ID
     * @return bool
     */
    Boolean deleteById(int id);
}
