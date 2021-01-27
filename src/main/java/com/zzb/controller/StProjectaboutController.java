package com.zzb.controller;

import com.zzb.TO.selectTO;
import com.zzb.entity.*;
import com.zzb.service.StProjectaboutService;
import com.zzb.service.StProjectstageService;
import com.zzb.service.StUserService;
import com.zzb.service.StUserstageService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (StProjectabout)表控制层
 *
 * @author zzbang
 * @since 2021-01-15 17:03:20
 */
@RestController
@RequestMapping("stProjectabout")
@CrossOrigin
public class StProjectaboutController {
    /**
     * 服务对象
     */
    @Resource
    private StProjectaboutService stProjectaboutService;
    @Autowired
    private StUserService userService;
    @Autowired
    private StUserstageService stUserstageService;
    @Autowired
    private StProjectstageService stProjectstageService;



    @GetMapping("selectAll")
    public Result selectAll(int pagesize,int pagenumber,String username) {
        if(StringUtils.isEmpty(username)||username==null){
            Subject subject = SecurityUtils.getSubject();
            //代码方式
            if (subject.hasRole("admin")) {
                List<InformationPO> list = stProjectaboutService.queryAllInformation();
                List<InformationVO> informationVOS = stProjectaboutService.sortList(list);
                List<InformationVO> collect = informationVOS.stream().sorted(Comparator.comparing(InformationVO::getProjectNum)).collect(Collectors.toList());
                selectTO subResult = stProjectaboutService.resultSub(pagesize, pagenumber, collect);
                return  Result.Searchsuccess(subResult,collect.size());
            }else{
                return Result.failure(ResultCode.USER_FORBIDDEN);
            }
        }else {
            List<InformationPO> list = stProjectaboutService.queryAllInformation();
            List<InformationVO> informationVOS = stProjectaboutService.sortList(list);
            List<InformationVO> collect = informationVOS.stream().sorted(Comparator.comparing(InformationVO::getProjectNum)).collect(Collectors.toList());
            List<InformationVO> result = collect.stream().filter(item -> {
                return item.getUsername().equals(username);
            }).collect(Collectors.toList());
            selectTO subResult = stProjectaboutService.resultSub(pagesize, pagenumber, result);
            return  Result.Searchsuccess(subResult,result.size());
        }
    }



    @RequestMapping("/add")
    @Transactional
    @RequiresRoles("admin")
    public Result addProject(@RequestBody String str) throws JSONException, ParseException {
        List<StUser> nameList = new ArrayList<>();
        StUserstage stUserstage = new StUserstage();
        stUserstage.setShowstatus(1);
        StProjectstage stProjectstage=new StProjectstage();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        JSONObject jsonObject=new JSONObject(str);
        String proNum= jsonObject.getString("proNum");
        Boolean isAdd=jsonObject.getBoolean("isAdd");
        StProjectabout stProjectabout=new StProjectabout();
        if(isAdd==false){
            if(stProjectaboutService.queryByNum(proNum)==null){
                return Result.failure(ResultCode.INFORMATION_NOT_EXISTS);
            }
            stProjectabout=stProjectaboutService.queryByNum(proNum);
        }
        stUserstage.setProjectnum(proNum);
        stProjectabout.setProjectnum(proNum);
        stProjectabout.setShowstatus(1);
        if(!stProjectaboutService.queryAll(stProjectabout).isEmpty()&&isAdd){
            return Result.failure(ResultCode.INFORMATION_HAS_EXISTS);
        }
        stProjectstage.setProjectnum(proNum);
        String projectType= jsonObject.getString("projectType");
        stProjectabout.setProjecttype(projectType);
        String projectRank= jsonObject.getString("projectRank");
        stProjectabout.setProjectrank(projectRank);
        Double score= jsonObject.getDouble("score");
        stProjectabout.setScore(score);
        Double pointBase= jsonObject.getDouble("pointBase");
        stProjectabout.setPointbase(pointBase);

        JSONArray usernameList=jsonObject.getJSONArray("usernameList");
        JSONObject proportion=jsonObject.getJSONObject("proportion");
        JSONObject p1=proportion.getJSONObject("p1");
        JSONObject p2=proportion.getJSONObject("p2");
        JSONObject p3=proportion.getJSONObject("p3");
        JSONObject p4=proportion.getJSONObject("p4");
        JSONObject p5=proportion.getJSONObject("p5");
        Double p1radio= p1.getDouble("ratio");
        Double p2radio= p2.getDouble("ratio");
        Double p3radio= p3.getDouble("ratio");
        Double p4radio= p4.getDouble("ratio");
        Double p5radio= p5.getDouble("ratio");
        JSONObject p1cycle=p1.getJSONObject("cycle");
        JSONObject p2cycle=p2.getJSONObject("cycle");
        JSONObject p3cycle=p3.getJSONObject("cycle");
        JSONObject p4cycle=p4.getJSONObject("cycle");
        JSONObject p5cycle=p5.getJSONObject("cycle");

        String startdate1=p1cycle.getString("startdate");
        String startdate2=p2cycle.getString("startdate");
        String startdate3=p3cycle.getString("startdate");
        String startdate4=p4cycle.getString("startdate");
        String startdate5=p5cycle.getString("startdate");
        String enddate1=p1cycle.getString("enddate");
        String enddate2=p2cycle.getString("enddate");
        String enddate3=p3cycle.getString("enddate");
        String enddate4=p4cycle.getString("enddate");
        String enddate5=p5cycle.getString("enddate");
        //插入项目关
        if(isAdd){
            stProjectaboutService.insert(stProjectabout);
        }else {
            stProjectaboutService.updateByProjrctNum(stProjectabout);
        }

        //插入项目阶段相关
        if(isAdd==false){
            stProjectstage= stProjectstageService.queryByNum(proNum,"P1");
        }
        stProjectstage.setProjectstage("P1");
        stProjectstage.setStageradio(p1radio);
        if(isAdd){
            stProjectstage.setStartdate(startdate1);
            stProjectstage.setEnddate(enddate1);
            stProjectstageService.insert(stProjectstage);
        }else {
            stProjectstageService.update(stProjectstage);
        }


        if(isAdd==false){
            stProjectstage= stProjectstageService.queryByNum(proNum,"P2");
        }
        stProjectstage.setProjectstage("P2");
        stProjectstage.setStageradio(p2radio);
        if(isAdd){
            stProjectstage.setStartdate(startdate2);
            stProjectstage.setEnddate(enddate2);
            stProjectstageService.insert(stProjectstage);
        }else {
            stProjectstageService.update(stProjectstage);
        }

        if(isAdd==false){
            stProjectstage= stProjectstageService.queryByNum(proNum,"P3");
        }
        stProjectstage.setProjectstage("P3");
        stProjectstage.setStageradio(p3radio);
        if(isAdd){
            stProjectstage.setStartdate(startdate3);
            stProjectstage.setEnddate(enddate3);
            stProjectstageService.insert(stProjectstage);
        }else {
            stProjectstageService.update(stProjectstage);
        }

        if(isAdd==false){
            stProjectstage= stProjectstageService.queryByNum(proNum,"P4");
        }
        stProjectstage.setProjectstage("P4");
        stProjectstage.setStageradio(p4radio);
        if(isAdd){
            stProjectstage.setStartdate(startdate4);
            stProjectstage.setEnddate(enddate4);
            stProjectstageService.insert(stProjectstage);
        }else {
            stProjectstageService.update(stProjectstage);
        }

        if(isAdd==false){
            stProjectstage= stProjectstageService.queryByNum(proNum,"P5");
        }
        stProjectstage.setProjectstage("P5");
        stProjectstage.setStageradio(p5radio);
        if(isAdd){
            stProjectstage.setStartdate(startdate5);
            stProjectstage.setEnddate(enddate5);
            stProjectstageService.insert(stProjectstage);
        }else {
            stProjectstageService.update(stProjectstage);
        }
        //插入用户阶段

            if(isAdd==false){
                stUserstageService.deleteByNum(proNum);
            }
       // if(isAdd){
            for (int i = 0; i < usernameList.length(); i++) {
                JSONObject user=usernameList.getJSONObject(i);
                String username=user.getString("username");
                String deptName=user.getString("deptName");
                String SFid= userService.getSFidByName(username);
                if(SFid!=null){
                    stUserstage.setUserName(username);
                    stUserstage.setSfid(SFid);
                    stUserstage.setDeptname(deptName);
                    stUserstage.setStageradio(0.0);
                    for (int j = 1; j <=5; j++) {
                        stUserstage.setStagenum("P"+j);
                        stUserstageService.insert(stUserstage);
                    }
                }
            }
       // }


return Result.success();
    }

    @RequestMapping("/delete")
    @Transactional
    @RequiresRoles("admin")
    public Result deleteProject(@RequestBody List<String> list){
        StProjectabout stProjectabout = new StProjectabout();
        if(list==null){
            return Result.failure(ResultCode.PARM_IS_BLANK,"要删除的数据为空");
        }
        for (String num : list) {
            stProjectabout.setProjectnum(num);
            stProjectaboutService.queryAll(stProjectabout);
            if(stProjectabout!=null){
                stProjectaboutService.deleteByNum(num);
                stUserstageService.deleteByNum(num);
            }else {
                return Result.failure(ResultCode.INFORMATION_NOT_EXISTS,"要删除的项目不存在");
            }
        }
        return Result.success(ResultCode.SUCCESS);
    }




}