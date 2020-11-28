package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

/**
 * tag
 * @author
 */
@Data
public class Tag extends BaseEntity {
    /**
     * 标签ID
     */
    private Integer tagId;

    /**
     * 标签名字
     */
    private String tagName;

    private static final long serialVersionUID = 1L;
}
