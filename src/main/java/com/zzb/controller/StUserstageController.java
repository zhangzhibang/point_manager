package com.zzb.controller;

import com.zzb.entity.DepPointVO;
import com.zzb.entity.Result;
import com.zzb.entity.StUserstage;
import com.zzb.entity.UserPointVO;
import com.zzb.service.StUserstageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @GetMapping("/getDepReportList")
    public Result getDepReportList(){

        List<DepPointVO> depPointList = stUserstageService.getDepPointList();
        List<DepPointVO> depProjectCountList = stUserstageService.getDepProjectCountList();
        System.out.println(depPointList);
        System.out.println(depProjectCountList);
        List<DepPointVO> collect = depPointList.stream().map(item -> {
            Integer depCount = stUserstageService.getDepCount(item.getDeptName(), depProjectCountList);
            item.setProjectCount(depCount);
            return item;
        }).collect(Collectors.toList());
        return  Result.success(collect);
    }

    @GetMapping("/getUserPointReportList")
    public Result getUserPointReportList(){
        List<UserPointVO> userPoint = stUserstageService.getUserPoint();
        return Result.success(userPoint);
    }
    @RequestMapping("/setUserRadio")
    public Result updateUser(@RequestBody Map<String,Object> map){

        return Result.success();

    }

}