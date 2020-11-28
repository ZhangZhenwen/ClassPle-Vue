package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

/**
 * ad
 * @author zhenwen
 */
@Data
public class Ad extends BaseEntity {
    /**
     * 广告ID
     */
    private Integer adId;

    /**
     * 广告标题
     */
    private String adTitle;

    /**
     * 广告内容
     */
    private String adContent;

    /**
     * 广告链接
     */
    private String adUrl;

    /**
     * 是否展示
     */
    private Boolean isShown;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;
}
