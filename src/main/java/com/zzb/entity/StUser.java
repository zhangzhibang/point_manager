package com.zzb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (StUser)实体类
 *
 * @author zzbang
 * @since 2021-01-25 13:38:53
 */
@Data
public class StUser implements Serializable {
    private static final long serialVersionUID = 107015959448700703L;
    
    private Integer id;
    
    private String sfId;
    
    private String username;
    
    private String password;
    /**
    * 1是管理员，0普通员工
    */
    private Integer admin;
    
    private String depname;
    
    private String salt;
    
    private String ext2;
    
    private String ext3;


}