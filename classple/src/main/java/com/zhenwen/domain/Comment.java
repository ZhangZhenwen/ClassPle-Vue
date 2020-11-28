package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

/**
 * comment
 * @author
 */
@Data
public class Comment extends BaseEntity {
    /**
     * 评论ID
     */
    private Integer cmtId;

    /**
     * 评论内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}
