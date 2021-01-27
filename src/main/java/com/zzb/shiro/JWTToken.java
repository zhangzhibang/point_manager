package com.zzb.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zzbang
 * @create 2021-01-26  19:16
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}