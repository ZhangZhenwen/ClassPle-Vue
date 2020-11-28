package com.zhenwen.mapper;

import com.zhenwen.domain.Notice;

/**
 * @author zhenwen
 */
public interface NoticeMapper {

    /**
     * 删除记录
     * @param noticeId
     * @return
     */
    int deleteByPrimaryKey(Integer noticeId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(Notice record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Notice record);

    /**
     * 选择记录
     *
     * @param noticeId
     * @return
     */
    Notice selectByPrimaryKey(Integer noticeId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Notice record);
}
