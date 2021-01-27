package com.zzb.VO;

import lombok.Data;

/**
 * @author zzbang
 * @create 2021-01-22  10:21
 */

/**
 * 给用户设置各阶段占比时候接收实体类
 */
@Data
public class UserStageVO {
    private String projectNum;
    private String username;
    private Double p1userRadio;
    private Double p2userRadio;
    private Double p3userRadio;
    private Double p4userRadio;
    private Double p5userRadio;

}
