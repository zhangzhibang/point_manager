package com.zzb.controller;

import com.zzb.TO.UserPointTO;
import com.zzb.VO.DepPointVO;
import com.zzb.VO.UserPointVO;
import com.zzb.VO.UserStageVO;
import com.zzb.entity.*;
import com.zzb.service.StUserstageService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
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
 * @author zzbang
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
     * 获取部门的积分
     * @return
     */
    @GetMapping("/getDepReportList")
    @RequiresRoles("admin")
    public Result getDepReportList(){

        List<DepPointVO> depPointList = stUserstageService.getDepPointList();
        List<DepPointVO> depProjectCountList = stUserstageService.getDepProjectCountList();
        List<DepPointVO> collect = depPointList.stream().map(item -> {
            Integer depCount = stUserstageService.getDepCount(item.getDeptName(), depProjectCountList);
            item.setProjectCount(depCount);
            return item;
        }).collect(Collectors.toList());
        return  Result.success(collect);
    }

    /**
     * 获取所有用户的积分
     * @param pagesize
     * @param pagenumber
     * @return
     */
    @GetMapping("/getUserPointReportList")
    public Result getUserPointReportList(int pagesize,int pagenumber,String username){
        if(StringUtils.isEmpty(username)||username==null) {
            Subject subject = SecurityUtils.getSubject();
            //代码方式
            if (subject.hasRole("admin")) {
                List<UserPointVO> userPoint = stUserstageService.getUserPoint();
                UserPointTO userPointTO = stUserstageService.getSubList(userPoint, pagesize, pagenumber);
                return Result.Searchsuccess(userPointTO.getList(),userPointTO.getTotal());
            } else {
                return Result.failure(ResultCode.USER_FORBIDDEN);
            }
        }else {
            List<UserPointVO> userPoint = stUserstageService.getUserPoint();
            List<UserPointVO> collect = userPoint.stream().filter(item -> item.getName().equals(username)).collect(Collectors.toList());
            UserPointTO userPointTO = stUserstageService.getSubList(collect, pagesize, pagenumber);
            return Result.Searchsuccess(userPointTO.getList(),userPointTO.getTotal());
        }

    }

    /**
     * 编辑用户阶段占比
     * @param userStageVO
     * @return
     */
    @RequestMapping("/setUserRadio")
    @RequiresRoles("admin")
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
    @RequiresRoles("admin")
    public Result deleteUserStage(@RequestBody StUserstage stUserstage){
        System.out.println(stUserstage);
        if(stUserstage==null){
            return Result.failure(ResultCode.PARM_IS_BLANK,"传入数据为空");
        }else {
            if(stUserstage.getUserName()!=null&&stUserstage.getProjectnum()!=null){
                stUserstageService.deleteByNameAndNum(stUserstage.getProjectnum(),stUserstage.getUserName());
            }else {
                return Result.failure(ResultCode.PARM_IS_BLANK,"用户名或项目号为空");
            }
        }
        return Result.success();
    }

}