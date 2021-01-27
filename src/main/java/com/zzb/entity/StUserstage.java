package com.zzb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (StUserstage)实体类
 *
 * @author zzbang
 * @since 2021-01-15 17:06:34
 */
@Data
public class StUserstage implements Serializable {
    private static final long serialVersionUID = 420662891404116596L;
    
    private Integer id;
    
    private String projectnum;
    
    private String sfid;
    
    private String deptname;
    
    private String stagenum;
    
    private Double stageradio;
    private String userName;
    private Integer showstatus;

}