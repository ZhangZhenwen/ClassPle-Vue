package com.zhenwen.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * preparation
 * @author 
 */
@Data
public class Preparation implements Serializable {
    /**
     * 备课ID
     */
    private Integer prepId;

    /**
     * 备课区名称
     */
    private String name;

    /**
     * 备课区介绍
     */
    private String introduction;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}