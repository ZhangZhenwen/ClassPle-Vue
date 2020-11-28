package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * notice
 * @author
 */
@Data
public class Notice extends BaseEntity {
    /**
     * 通知ID
     */
    private Integer noticeId;

    /**
     * 通知对象ID
     */
    private Integer userId;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 网站路径
     */
    private String url;

    /**
     * 是否已读
     */
    private String isRead;

    /**
     * 已读时间
     */
    private Date readTime;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;
}
