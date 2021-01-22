package com.zzb.controller;

import com.zzb.entity.*;
import com.zzb.service.StUserstageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
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
     * @param
     * @return 单条数据
     */
//    @GetMapping("selectOne")
//    public StUserstage selectOne(Integer id) {
//        return this.stUserstageService.queryById(id);
//    }


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
    public Result updateUser(@RequestBody UserStageVO userStageVO){
//TODO 编辑用户各阶段比例接口
        StUserstage stUserstage = new StUserstage();
        if (userStageVO!=null){
            List<StUserstage> list= stUserstageService.queryByNameandProject(userStageVO.getUsername(), userStageVO.getProjectNum());
            if(!list.isEmpty()&&list!=null){
                for (StUserstage item : list) {
                    if(item.getStagenum()==null){
                        break;
                    }

                    if("P1".equals(item.getStagenum())){
                        stUserstage=item;
                        item.setStageradio(userStageVO.getP1userRadio());
                    }else if("P2".equals(item.getStagenum())){
                        stUserstage=item;
                        item.setStageradio(userStageVO.getP2userRadio());
                    }else if("P3".equals(item.getStagenum())){
                        stUserstage=item;
                        item.setStageradio(userStageVO.getP3userRadio());
                    }else if("P4".equals(item.getStagenum())){
                        stUserstage=item;
                        item.setStageradio(userStageVO.getP4userRadio());
                    }else if("P5".equals(item.getStagenum())){
                        stUserstage=item;
                        item.setStageradio(userStageVO.getP5userRadio());
                    }
                    stUserstageService.update(stUserstage);
                }
            }
        }else {
            return Result.failure(ResultCode.PARM_IS_BLANK,"查询为空！");
        }
        return Result.success();

    }

    @RequestMapping("/deleteUser")
    public Result deleteUserStage(StUserstage stUserstage){
        if(stUserstage==null){
            return Result.failure(ResultCode.PARM_IS_BLANK,"传入数据为空");
        }else {
            if(stUserstage.getUserName()!=null&&stUserstage.getProjectnum()!=null){
                stUserstageService.deleteByNameAndNum(stUserstage.getProjectnum(),stUserstage.getUserName());
            }else {
                return Result.failure(ResultCode.PARM_IS_BLANK,"用户名或项目号为空");
            }
        }
        return Result.success(ResultCode.SUCCESS,"删除用户成功！");
    }

}