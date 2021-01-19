package com.zzb.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AdProject)实体类
 *项目对象实体类
 * @author zzbang
 * @since 2020-10-26 14:48:59
 */
@Data
public class AdProject implements Serializable {
    private static final long serialVersionUID = 724656453205370953L;

    private Long proid;

    private Object pronum;

    private Object proname;

    private Object prouserid;

    private Object prousername;

    private Object deptid;

    private Object deptname;

    private Object year;

    private Double budgetamount;

    private Date createdatetime;

    private Object createuser;

    private Object validflag;

    private Object showdept;

    private Object isforall;



}