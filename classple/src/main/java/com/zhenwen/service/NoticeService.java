package com.zhenwen.service;

import com.zhenwen.domain.Notice;
import com.zhenwen.utils.Pager;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface NoticeService {

    /**
     * 查找所有
     * @return list
     */
    List<Notice> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<Notice> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return Notice
     */
    Notice findById(int id);

    /**
     * 添加
     * @param notice notice
     * @return bool
     */
    Boolean insert(Notice notice);

    /**
     * 更新
     * @param notice notice
     * @return bool
     */
    Boolean updateById(Notice notice);

    /**
     * 删除
     * @param id Notice ID
     * @return bool
     */
    Boolean deleteById(int id);
}
