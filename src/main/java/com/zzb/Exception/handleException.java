package com.zzb.Exception;

import com.zzb.entity.Result;
import com.zzb.entity.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.zzb.controller"})
public class handleException {

//    @ExceptionHandler(NullPointerException.class)
//    public Result  handleException(){
//        return Result.failure(ResultCode.)
//    }
}
