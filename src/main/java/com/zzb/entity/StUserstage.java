package com.zzb.entity;

import java.io.Serializable;

/**
 * (StUserstage)实体类
 *
 * @author makejava
 * @since 2021-01-15 17:06:34
 */
public class StUserstage implements Serializable {
    private static final long serialVersionUID = 420662891404116596L;
    
    private Integer id;
    
    private String projectnum;
    
    private String sfid;
    
    private String deptname;
    
    private String stagenum;
    
    private Double stageradio;


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

    public String getSfid() {
        return sfid;
    }

    public void setSfid(String sfid) {
        this.sfid = sfid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getStagenum() {
        return stagenum;
    }

    public void setStagenum(String stagenum) {
        this.stagenum = stagenum;
    }

    public Double getStageradio() {
        return stageradio;
    }

    public void setStageradio(Double stageradio) {
        this.stageradio = stageradio;
    }

}