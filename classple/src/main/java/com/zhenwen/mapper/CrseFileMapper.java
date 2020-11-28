package com.zhenwen.mapper;

import com.zhenwen.domain.CrseFile;

import java.util.List;

/**
 * @author zhenwen
 */
public interface CrseFileMapper {

    /**
     * 删除记录
     * @param key ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(CrseFile key);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(CrseFile record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(CrseFile record);

    /**
     * 根据课程ID查找文件ID
     *
     * @param id 课程ID
     * @return 文件ID
     */
    List<Integer> findFileIdsByCrseId(Integer id);
}
