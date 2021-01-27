package com.zzb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (StProjectabout)实体类
 *
 * @author zzbang
 * @since 2021-01-15 17:03:16
 */
@Data
public class StProjectabout implements Serializable {
    private static final long serialVersionUID = 377944164304785397L;
    
    private Integer id;

    private String projectnum;
    /**
    * 项目类型
    */
    private String projecttype;
    /**
    * 项目等级
    */
    private String projectrank;
    /**
    * 评价得分
    */
    private Double score;
    /**
    * 积分基数
    */
    private Double pointbase;

    private  Integer showstatus;




}