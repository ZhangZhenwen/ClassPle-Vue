package com.zhenwen.mapper;

import com.zhenwen.domain.File;

import java.util.List;

/**
 * @author zhenwen
 */
public interface FileMapper {

    /**
     * 删除记录
     * @param fileId ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer fileId);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insert(File record);

    /**
     * 插入记录
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(File record);

    /**
     * 查找记录
     * @param fileId ID
     * @return 文件
     */
    File selectByPrimaryKey(Integer fileId);

    /**
     * 查找文件树
     * @param fileId 父ID
     * @return list
     */
    List<File> findFileTree(Integer fileId);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(File record);

    /**
     * 更新记录
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(File record);
}
