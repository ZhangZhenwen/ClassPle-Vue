package com.zhenwen.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * crse_asgn
 * @author
 */
@Data
public class CrseAsgn implements Serializable {
    private Integer crseId;

    private Integer asgnId;

    private static final long serialVersionUID = 1L;

    public CrseAsgn() {
    }

    public CrseAsgn(Integer crseId, Integer asgnId) {
        this.crseId = crseId;
        this.asgnId = asgnId;
    }
}
