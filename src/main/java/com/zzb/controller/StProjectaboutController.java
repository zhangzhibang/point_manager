package com.zzb.controller;

import com.zzb.entity.InformationPO;
import com.zzb.entity.Result;
import com.zzb.entity.StProjectabout;
import com.zzb.entity.StProjectstage;
import com.zzb.service.StProjectaboutService;
import com.zzb.utils.dateUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * (StProjectabout)表控制层
 *
 * @author makejava
 * @since 2021-01-15 17:03:20
 */
@RestController
@RequestMapping("stProjectabout")
public class StProjectaboutController {
    /**
     * 服务对象
     */
    @Resource
    private StProjectaboutService stProjectaboutService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public StProjectabout selectOne(Integer id) {
        return this.stProjectaboutService.queryById(id);
    }


    @GetMapping("selectAll")
    public Result selectAll() {

        return Result.success(stProjectaboutService.queryAllInformation());
    }
}