package com.zzb.entity;
/**
 * @author zzbang
 */

public enum ResultCode {
    /*
    成功
     */
    SUCCESS(200,"成功"),
    RESULT_NULL(201,"查询成功但查询结果为null"),
    /*
    参数相关问题。
     */
    PARM_IS_INVILAD(1001,"参数无效"),
    PARM_IS_BLANK(1002,"参数为空"),
    PARM_TYPE_BIND_ERROR(1003,"参数类型错误"),
    PARM_NOT_COMPLETE(1004,"参数缺失"),
/*
用户相关问题。
 */
    USER_NOT_LOGGED_IN(2001,"用户未登录"),
    USER_LOGIN_ERROR(2002,"账号密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"账户被禁用"),
    USER_NOT_EXISTS(2004,"用户不存在"),
    USER_HAS_EXISTS(2005,"用户已存在");
    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
