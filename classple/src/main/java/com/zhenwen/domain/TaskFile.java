package com.zhenwen.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * task_file
 * @author
 */
@Data
public class TaskFile implements Serializable {
    /**
     * 作业ID
     */
    private Integer taskId;

    /**
     * 文件ID
     */
    private Integer fileId;

    private static final long serialVersionUID = 1L;

    public TaskFile() {
    }

    public TaskFile(Integer taskId, Integer fileId) {
        this.taskId = taskId;
        this.fileId = fileId;
    }
}
