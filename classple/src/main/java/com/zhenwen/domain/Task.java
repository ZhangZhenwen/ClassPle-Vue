package com.zhenwen.domain;

import com.zhenwen.common.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * task
 * @author zhenwen
 */
@Data
public class Task extends BaseEntity {
    /**
     * 作业ID
     */
    private Integer taskId;

    /**
     * 作业ID
     */
    private Integer asgnId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 差重值
     */
    private Integer repeatRate;

    /**
     * 更新次数
     */
    private Integer updatedCount;

    /**
     * 是否打回
     */
    private Boolean isReturn;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    private List<File> files;

    private static final long serialVersionUID = 1L;

    public Task() {
        updatedCount = 0;
        repeatRate = 0;
        isDeleted = false;
        isReturn = false;
    }
}
