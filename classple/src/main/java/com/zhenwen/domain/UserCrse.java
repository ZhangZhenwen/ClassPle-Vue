package com.zhenwen.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user_crse
 * @author zhenwen
 */
@Data
public class UserCrse implements Serializable {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 课程ID
     */
    private Integer crseId;

    /**
     * 用户角色
     */
    private String userRole;

    /**
     * 在班级中的姓名
     */
    private String name;

    /**
     * 学号
     */
    private String schoolNo;

    /**
     * 加课时间
     */
    private Date joinDate;

    /**
     * 退课时间
     */
    private Date outDate;

    /**
     * 排序下标
     */
    private Integer sortIndex;

    /**
     * 是否退课
     */
    private Boolean isDropOut;

    /**
     * 是否归档
     */
    private Boolean isFile;

    /**
     * 是否退课
     */
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public UserCrse() {
        this(null, null, null, null, null, new Date(),
                null, null, false, false, false);
    }

    public UserCrse(User user) {
        this(user.getUserId(), null, null, user.getUserName(), user.getSchoolNo(), new Date(),
                null, null, false, false, false);
    }

    public UserCrse(Integer userId, Integer crseId, String userRole, String name, String schoolNo, Date joinDate,
                    Date outDate, Integer sortIndex, Boolean isDropOut, Boolean isFile, Boolean isDeleted) {
        this.userId = userId;
        this.crseId = crseId;
        this.userRole = userRole;
        this.name = name;
        this.schoolNo = schoolNo;
        this.joinDate = joinDate;
        this.outDate = outDate;
        this.sortIndex = sortIndex;
        this.isDropOut = isDropOut;
        this.isFile = isFile;
        this.isDeleted = isDeleted;
    }
}
