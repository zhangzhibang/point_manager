package com.zzb.VO;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登陆返回的实体类
 * @author zzbang
 * @create 2021-01-27  10:46
 */
@Data
@Accessors(chain = true)
public class LoginVO {
    private String token;
    private String username;
    private int admin;
}
