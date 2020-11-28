package com.zhenwen.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * mooc_tag
 * @author 
 */
@Data
public class MoocTagKey implements Serializable {
    /**
     * 慕课ID
     */
    private Integer moocId;

    /**
     * 标签ID
     */
    private Integer tagId;

    private static final long serialVersionUID = 1L;
}