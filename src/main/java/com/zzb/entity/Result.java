package com.zzb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzbang
 */
@Data
public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return  result;
    }
    public static Result success(ResultCode resultCode,Object data){
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMessage(result.getMessage());
        result.setData(data);
        return  result;
    }

    public static Result failure(ResultCode resultCode){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result;
    }


    public static Result failure(ResultCode resultCode,Object data){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return  result;
    }

}
