package com.zzb.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询数据库得到每个员工在某一年的项目数中间对象
 */
@Data
@Accessors(chain = true)
public class projectUserPO {

    private String SFid;

    private Integer projectCount;
}
