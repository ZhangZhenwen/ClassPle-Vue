package com.zhenwen.common.web.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenwen
 * @date 2020/11/19
 */
@Data
public class TreeEntity extends BaseEntity {

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 排序ID
     */
    private Integer orderNum;

    /**
     * 节点
     */
    private Object node;

    /**
     * 子节点序列
     */
    private List<?> children = new ArrayList<>();

}
