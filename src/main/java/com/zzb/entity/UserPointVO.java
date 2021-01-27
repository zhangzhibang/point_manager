package com.zzb.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserPointVO {
    private String SFid;
    private String name;
    private Double total;
    private Integer count;
    private String deptname;
    private Double point2018;
    private Double point2019;
    private Double point2020;

}
