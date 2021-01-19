package com.zzb.entity;

import java.io.Serializable;

/**
 * (StProjectabout)实体类
 *
 * @author makejava
 * @since 2021-01-15 17:03:16
 */
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectnum() {
        return projectnum;
    }

    public void setProjectnum(String projectnum) {
        this.projectnum = projectnum;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getProjectrank() {
        return projectrank;
    }

    public void setProjectrank(String projectrank) {
        this.projectrank = projectrank;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getPointbase() {
        return pointbase;
    }

    public void setPointbase(Double pointbase) {
        this.pointbase = pointbase;
    }

}