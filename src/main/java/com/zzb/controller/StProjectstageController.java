package com.zzb.controller;

import com.zzb.entity.Result;
import com.zzb.entity.StProjectstage;
import com.zzb.service.StProjectstageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StProjectstage)表控制层
 *
 * @author zzbang
 * @since 2021-01-15 17:06:10
 */
@RestController
@RequestMapping("stProjectstage")
@CrossOrigin
public class StProjectstageController {
    /**
     * 服务对象
     */
    @Resource
    private StProjectstageService stProjectstageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public StProjectstage selectOne(Integer id) {
        return this.stProjectstageService.queryById(id);
    }

    @GetMapping("selectAll")
    public Result selectAll() {
        List<StProjectstage> list=this.stProjectstageService.selectAll(new StProjectstage());
        return Result.success(list);
    }

}