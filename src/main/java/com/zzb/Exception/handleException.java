package com.zzb.Exception;

import com.zzb.entity.Result;
import com.zzb.entity.ResultCode;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class handleException {

    @ExceptionHandler(UnauthenticatedException.class)
    public Result  handUnauthenticatedException(Exception e){
        e.printStackTrace();
        return Result.failure(ResultCode.USER_NOT_LOGGED_IN);
    }

    @ExceptionHandler(Exception.class)
    public Result  handleException(Exception e){
        e.printStackTrace();
        return Result.failure(ResultCode.UNKNOWN_ERROR);
    }
}
