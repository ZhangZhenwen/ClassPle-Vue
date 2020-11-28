package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * assignment
 * @author zhenwen
 */
@Data
public class Assignment extends BaseEntity {
    /**
     * 作业ID
     */
    private Integer asgnId;

    /**
     * 作业名称
     */
    private String asgnName;

    /**
     * 作业简介
     */
    private String asgnIntroduction;

    /**
     * 作业类型
     */
    private String asgnType;

    /**
     * 发布日期
     */
    private Date releasedDate;

    /**
     * 截止日期
     */
    private Date deadline;

    /**
     * 满分值
     */
    private Integer maxScore;

    /**
     * 是否差重
     */
    private Byte isRepeat;

    /**
     * 差重预警
     */
    private Integer repeatRate;

    /**
     * 打回值
     */
    private Integer returnRate;

    private static final long serialVersionUID = 1L;
}
