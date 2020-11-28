package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

/**
 * file
 * @author zhenwen
 */
@Data
public class File extends BaseEntity {
    /**
     * 文件表ID
     */
    private Integer fileId;

    /**
     * 父文件ID
     */
    private Integer parentId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小
     */
    private Double fileSize;

    /**
     * 是否可下载
     */
    private Boolean downloadable;

    /**
     * 下载次数
     */
    private Integer downloadCount;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public File() {
        downloadable = true;
        downloadCount = 0;
        isDeleted = false;
    }
}
