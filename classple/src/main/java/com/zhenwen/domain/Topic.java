package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

/**
 * topic
 * @author
 */
@Data
public class Topic extends BaseEntity {
    /**
     * 话题ID
     */
    private Integer topicId;

    /**
     * 话题标题
     */
    private String title;

    /**
     * 话题内容
     */
    private String content;

    /**
     * 课程ID
     */
    private Integer crseId;

    private static final long serialVersionUID = 1L;
}
