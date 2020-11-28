package com.zhenwen.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * asgn_file
 * @author
 */
@Data
public class AsgnFile implements Serializable {
    /**
     * 作业ID
     */
    private Integer asgnId;

    /**
     * 文件ID
     */
    private Integer fileId;

    private static final long serialVersionUID = 1L;

    public AsgnFile() {
        this(null, null);
    }

    public AsgnFile(Integer asgnId, Integer fileId) {
        this.asgnId = asgnId;
        this.fileId = fileId;
    }
}
