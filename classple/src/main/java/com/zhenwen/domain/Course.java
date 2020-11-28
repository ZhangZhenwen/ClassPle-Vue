package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import com.zhenwen.utils.IdUtils;
import lombok.Data;

/**
 * course
 * @author zhenwen
 */
@Data
public class Course extends BaseEntity {
    /**
     * 课程ID
     */
    private Integer crseId;

    /**
     * 课程编号
     */
    private String crseCode;

    /**
     * 课程名称
     */
    private String crseName;

    /**
     * 课堂名称
     */
    private String className;

    /**
     * 课堂简介
     */
    private String introduction;

    /**
     * 课程学期
     */
    private String crseSemester;

    /**
     * 加入课程是否需要信息
     */
    private Integer crseJoinCode;

    /**
     * 最大成员数
     */
    private Integer maxMember;

    /**
     * 允许退课
     */
    private Boolean allowDropOut;

    /**
     * 是否归档
     */
    private Boolean isFile;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public Course() {
        crseCode = IdUtils.randomCode(6);
        allowDropOut = false;
        isFile = false;
        isDeleted = false;
    }
}
