package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * user
 * @author zhenwen
 */
@Data
public class User extends BaseEntity {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 入学年月
     */
    private Date enrolledDate;

    /**
     * 学校编号
     */
    private String schoolNo;

    /**
     * 学院
     */
    private String institute;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;

    private List<Role> roles;

    private static final long serialVersionUID = 1L;
}
