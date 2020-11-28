package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

/**
 * mooc
 * @author zhenwen
 */
@Data
public class Mooc extends BaseEntity {
    /**
     * 慕课ID
     */
    private Integer moocId;

    /**
     * 慕课名称
     */
    private String moocName;

    /**
     * 慕课图标
     */
    private String iconUrl;

    /**
     * 课程简介
     */
    private String introduction;

    /**
     * 课程大纲
     */
    private String outline;

    /**
     * 主讲教师
     */
    private String lecturerName;

    /**
     * 教师学校
     */
    private String lecturerSchool;

    /**
     * 教师学历
     */
    private String lecturerDegree;

    /**
     * 教师简介
     */
    private String lecturerIntroduction;

    private Integer membersCount;

    private static final long serialVersionUID = 1L;
}
