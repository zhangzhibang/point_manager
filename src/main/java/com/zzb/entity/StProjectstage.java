package com.zzb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (StProjectstage)实体类
 *
 * @author makejava
 * @since 2021-01-15 17:06:10
 */

public class StProjectstage implements Serializable {
    private static final long serialVersionUID = 289187322998197225L;
    
    private Integer id;
    
    private String projectnum;
    /**
    * 项目阶段
    */
    private String projectstage;
    /**
    * 阶段占比
    */
    private Double stageradio;
    
    private Date startdate;
    
    private Date enddate;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getProjectstage() {
        return projectstage;
    }

    public void setProjectstage(String projectstage) {
        this.projectstage = projectstage;
    }

    public Double getStageradio() {
        return stageradio;
    }

    public void setStageradio(Double stageradio) {
        this.stageradio = stageradio;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}