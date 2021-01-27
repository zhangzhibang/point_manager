package com.zzb.VO;

import lombok.Data;

/**
 * @author zzbang
 * 查询部门报表返回实体类
 */
@Data
public class DepPointVO {
    /**
     * 部门名称
     */
    private String DeptName;
    /**
     * 总积分
     */
    private Double totalPoint;
    /**
     * 总项目数
     */
    private Integer projectCount;
}
