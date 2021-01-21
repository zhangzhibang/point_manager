package com.zzb.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InformationVO {
    private Integer sort;
    private String ProjectNum;
    private String ProName;
    private String ProjectType;
    private String ProjectRank;
    private Double Score;
    private Double PointBase;
    private Double P1radio;
    private Double P2radio;
    private Double P3radio;
    private Double P4radio;
    private Double P5radio;
    private String username;
    private String deptName;
    private Double P1userRadio;
    private Double P2userRadio;
    private Double P3userRadio;
    private Double P4userRadio;
    private Double P5userRadio;
    private Double total;
    /**
     * 2018年积分
     */
    private Double point2018;
    /**
     * 2019年积分
     */
    private Double point2019;
    /**
     * 2020年积分
     */
    private Double point2020;

    /**
     * 2018年项目数
     */
    private Integer projectCount2018;
    /**
     * 2019项目数量
     */
    private Integer projectCount2019;
    /**
     * 2020项目数量
     */
    private Integer projectCount2020;
}
