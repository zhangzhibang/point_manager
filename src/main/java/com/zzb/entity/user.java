package com.zzb.entity;

import java.io.Serializable;

/**
 * (HrKquserlist)实体类
 *
 * @author makejava
 * @since 2021-01-20 10:07:51
 */
public class user implements Serializable {
    private static final long serialVersionUID = 334572739380954981L;
    
    private Object userid;
    
    private Object username;
    
    private Object userkqid;
    
    private Object deptname;
    
    private Object dtptid;


    public Object getUserid() {
        return userid;
    }

    public void setUserid(Object userid) {
        this.userid = userid;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getUserkqid() {
        return userkqid;
    }

    public void setUserkqid(Object userkqid) {
        this.userkqid = userkqid;
    }

    public Object getDeptname() {
        return deptname;
    }

    public void setDeptname(Object deptname) {
        this.deptname = deptname;
    }

    public Object getDtptid() {
        return dtptid;
    }

    public void setDtptid(Object dtptid) {
        this.dtptid = dtptid;
    }

}