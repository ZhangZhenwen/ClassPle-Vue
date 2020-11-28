package com.zhenwen.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * crse_file
 * @author
 */
@Data
public class CrseFile implements Serializable {
    /**
     * 课程ID
     */
    private Integer crseId;

    /**
     * 文件ID
     */
    private Integer fileId;

    private static final long serialVersionUID = 1L;
}
