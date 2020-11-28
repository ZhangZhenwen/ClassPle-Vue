package com.zhenwen.service;

import com.zhenwen.domain.Ad;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface AdService {

    /**
     * 查找所有
     * @return list
     */
    List<Ad> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Ad> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return Ad
     */
    Ad findById(int id);

    /**
     * 添加
     * @param ad ad
     * @return bool
     */
    Boolean insert(Ad ad);

    /**
     * 更新
     * @param ad ad
     * @return bool
     */
    Boolean updateById(Ad ad);

    /**
     * 删除
     * @param id Ad ID
     * @return bool
     */
    Boolean deleteById(int id);
}
