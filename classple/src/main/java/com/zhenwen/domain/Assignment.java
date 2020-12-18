package com.zhenwen.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date releasedDate;

    /**
     * 截止日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date deadline;

    /**
     * 满分值
     */
    private Integer maxScore;

    /**
     * 是否差重
     */
    private Boolean isRepeat;

    /**
     * 差重预警
     */
    private Integer repeatRate;

    /**
     * 打回值
     */
    private Integer returnRate;

    private Boolean isDeleted;

    private List<File> files;

    private static final long serialVersionUID = 1L;

    public Assignment() {
        this.isDeleted = false;
        this.releasedDate = new Date();
    }
}
