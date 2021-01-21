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
import org.codehaus.jettison.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (StProjectabout)表控制层
 *
 * @author makejava
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
        List<InformationVO> collect = informationVOS.stream().sorted(Comparator.comparing(InformationVO::getProjectNum)).collect(Collectors.toList());
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

        return Result.success(collect);
    }


    @RequestMapping("/add")
    public Result addProject(@RequestBody String str) throws JSONException, ParseException {
        StUserstage stUserstage = new StUserstage();
        stUserstage.setShowstatus(1);
        StProjectstage stProjectstage=new StProjectstage();
        StProjectabout stProjectabout=new StProjectabout();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        JSONObject jsonObject=new JSONObject(str);
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
        Date p1startdate = new Date();
        Date p2startdate = new Date();
        Date p3startdate = new Date();
        Date p4startdate = new Date();
        Date p5startdate = new Date();
        Date p1enddate = new Date();
        Date p2enddate = new Date();
        Date p3enddate = new Date();
        Date p4enddate = new Date();
        Date p5enddate = new Date();

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

        if(startdate1!=null&& !"".equals(startdate1)&&enddate1!=null&&!"".equals(enddate1)){
            p1startdate=simpleDateFormat.parse(startdate1);
            p1enddate=simpleDateFormat.parse(enddate1);
        }
        if(startdate2!=null&& !"".equals(startdate2)&&enddate2!=null&&!"".equals(enddate2)){
            p1startdate=simpleDateFormat.parse(startdate2);
            p1enddate=simpleDateFormat.parse(enddate2);
        }
        if(startdate3!=null&& !"".equals(startdate3)&&enddate3!=null&&!"".equals(enddate3)){
            p1startdate=simpleDateFormat.parse(startdate3);
            p1enddate=simpleDateFormat.parse(enddate3);
        }
        if(startdate4!=null&& !"".equals(startdate4)&&enddate4!=null&&!"".equals(enddate4)){
            p1startdate=simpleDateFormat.parse(startdate4);
            p1enddate=simpleDateFormat.parse(enddate4);
        }
        if(startdate5!=null&& !"".equals(startdate5)&&enddate5!=null&&!"".equals(enddate5)){
            p1startdate=simpleDateFormat.parse(startdate5);
            p1enddate=simpleDateFormat.parse(enddate5);
        }
        //插入项目关
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
              stUserstage.setStageradio(0.0);
              for (int j = 1; j <=5; j++) {
                  stUserstage.setStagenum("P"+j);
                  stUserstageService.insert(stUserstage);

              }
          }
        }


return Result.success();
    }
}