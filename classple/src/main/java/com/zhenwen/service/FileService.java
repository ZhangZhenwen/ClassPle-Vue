package com.zhenwen.service;

import com.zhenwen.common.web.domain.TreeEntity;
import com.zhenwen.domain.File;
import com.zhenwen.utils.Pager;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */

public interface FileService {

    /**
     * 查找所有
     * @return list
     */
    List<File> findAll();

    /**
     * 分页查找
     * @param page 页数
     * @param size 容量
     * @return pager
     */
    Pager<File> findByPager(int page, int size);

    /**
     * 根据ID查找
     * @param id ID
     * @return File
     */
    File findById(int id);

    /**
     * 添加
     * @param file file
     * @return bool
     */
    Boolean insert(File file);

    /**
     * 更新
     * @param file file
     * @return bool
     */
    Boolean updateById(File file);

    /**
     * 删除
     * @param id File ID
     * @return bool
     */
    Boolean deleteById(int id);

    /**
     * 生成文件树
     * @param id 文件ID
     * @return 文件树
     */
    TreeEntity getTree(int id);

    /**
     * 查找资料书
     * @param id 课程ID
     * @return
     */
    List<TreeEntity> findResourcesByCourseId(Integer id);

    /**
     * 上传文件
     * @param files files
     * @return file id
     */
    List<Integer> upload(MultipartFile[] files);

    /**
     * 上传文件
     * @param file
     * @return
     */
    Integer upload(MultipartFile file);

    /**
     * 下载文件
     * @param fileId file id
     * @return 资源
     */
    Boolean download(Integer fileId, OutputStream os);
}
