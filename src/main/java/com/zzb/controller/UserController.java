package com.zzb.controller;

import com.zzb.entity.Result;
import com.zzb.entity.user;
import com.zzb.service.userService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HrKquserlist)表控制层
 *
 * @author makejava
 * @since 2021-01-20 10:07:51
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private userService userService;


    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.success(userService.queryAll(new user()));
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public user selectOne(Object id) {
        return this.userService.queryById(id);
    }

}