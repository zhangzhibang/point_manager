package com.zzb.controller;

import com.zzb.entity.*;
import com.zzb.service.StProjectaboutService;
import com.zzb.service.StProjectstageService;
import com.zzb.service.StUserstageService;
import com.zzb.service.userService;
import com.zzb.utils.dateUtil;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private userService userService;
    @Autowired
    private StUserstageService stUserstageService;
    @Autowired
    private StProjectstageService stProjectstageService;

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
        Integer pagesize=null;
        Integer pagenumber=null;
        Integer pagecount=0;
        List<InformationPO> list = stProjectaboutService.queryAllInformation();
        List<InformationVO> informationVOS = stProjectaboutService.sortList(list);
        List<InformationVO> subList=new ArrayList<>();
//        int totalcount=informationVOS.size();
//        int m=totalcount%pagesize;
//        if  (m>0){
//            pagecount=totalcount/pagesize+1;
//        }else{
//            pagecount=totalcount/pagesize;
//        }
//
//        if (m==0){
//                 subList= informationVOS.subList(0,0);
//                 return Result.success(ResultCode.RESULT_NULL,subList);
//            }else{
//                if (pagenumber==pagecount){
//                    subList= informationVOS.subList((pagecount-1)*pagesize,totalcount);
//                }else if(pagenumber<pagecount){
//                   subList= informationVOS.subList((pagenumber-1)*pagesize,pagesize*(pagenumber));
//                }else {
//                    subList= informationVOS.subList((pagecount-1)*pagesize,totalcount);
//                }
//
//
//            }

        return Result.success(informationVOS);
    }


    @RequestMapping("/add")
    public Result addProject(@RequestBody Map<String, Object> map) throws JSONException, ParseException {
        StUserstage stUserstage = new StUserstage();
        StProjectstage stProjectstage=new StProjectstage();
        StProjectabout stProjectabout=new StProjectabout();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        JSONObject jsonObject=new JSONObject(map.toString());
        String proNum= jsonObject.getString("proNum");
        stUserstage.setProjectnum(proNum);
        stProjectabout.setProjectnum(proNum);
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
        Date p1startdate = simpleDateFormat.parse(p1cycle.getString("startdate"));
        Date p2startdate = simpleDateFormat.parse(p2cycle.getString("startdate"));
        Date p3startdate = simpleDateFormat.parse(p3cycle.getString("startdate"));
        Date p4startdate = simpleDateFormat.parse(p4cycle.getString("startdate"));
        Date p5startdate = simpleDateFormat.parse(p5cycle.getString("startdate"));
        Date p1enddate = simpleDateFormat.parse(p1cycle.getString("enddate"));
        Date p2enddate = simpleDateFormat.parse(p2cycle.getString("enddate"));
        Date p3enddate = simpleDateFormat.parse(p3cycle.getString("enddate"));
        Date p4enddate = simpleDateFormat.parse(p4cycle.getString("enddate"));
        Date p5enddate = simpleDateFormat.parse(p5cycle.getString("enddate"));
        //插入项目相关
        stProjectaboutService.insert(stProjectabout);
        //插入项目阶段相关
        stProjectstage.setProjectstage("P1");
        stProjectstage.setStageradio(p1radio);
        stProjectstage.setStartdate(p1startdate);
        stProjectstage.setEnddate(p1enddate);
        stProjectstageService.insert(stProjectstage);

        stProjectstage.setProjectstage("P2");
        stProjectstage.setStageradio(p2radio);
        stProjectstage.setStartdate(p2startdate);
        stProjectstage.setEnddate(p2enddate);
        stProjectstageService.insert(stProjectstage);

        stProjectstage.setProjectstage("P3");
        stProjectstage.setStageradio(p3radio);
        stProjectstage.setStartdate(p3startdate);
        stProjectstage.setEnddate(p3enddate);
        stProjectstageService.insert(stProjectstage);

        stProjectstage.setProjectstage("P4");
        stProjectstage.setStageradio(p4radio);
        stProjectstage.setStartdate(p4startdate);
        stProjectstage.setEnddate(p4enddate);
        stProjectstageService.insert(stProjectstage);

        stProjectstage.setProjectstage("P5");
        stProjectstage.setStageradio(p5radio);
        stProjectstage.setStartdate(p5startdate);
        stProjectstage.setEnddate(p5enddate);
        stProjectstageService.insert(stProjectstage);
        //插入用户阶段
        for (int i = 0; i < usernameList.length(); i++) {
          JSONObject user=usernameList.getJSONObject(i);
          String username=user.getString("username");
          String deptName=user.getString("deptName");
          String SFid= userService.getSFidByName(username);
          if(SFid!=null){
             stUserstage.setUserName(username).setSfid(SFid);
             stUserstage.setDeptname(deptName);
              for (int j = 1; j <=5; j++) {
                  stUserstage.setStagenum("P"+j);
                  stUserstageService.insert(stUserstage);
              }
          }
        }


return Result.success();
    }
}