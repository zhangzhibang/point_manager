package com.zzb.controller;

import com.zzb.entity.StUserstage;
import com.zzb.service.StUserstageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (StUserstage)表控制层
 *
 * @author makejava
 * @since 2021-01-15 17:06:34
 */
@RestController
@RequestMapping("stUserstage")
public class StUserstageController {
    /**
     * 服务对象
     */
    @Resource
    private StUserstageService stUserstageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public StUserstage selectOne(Integer id) {
        return this.stUserstageService.queryById(id);
    }

}