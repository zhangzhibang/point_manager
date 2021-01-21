package com.zzb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzb.entity.AdProject;
import com.zzb.entity.Result;
import com.zzb.service.AdProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *项目管理相关controller
 */
@RestController
@RequestMapping("adProject")
public class AdProjectController {
    /**
     * 服务对象
     */
    @Resource
    private AdProjectService adProjectService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public AdProject selectOne(Object id) {
        return this.adProjectService.queryById(id);
    }
/**
 * 查询项目列表
 */
    @RequestMapping("selectAll")
    @CrossOrigin
    public Result selectAll(AdProject adProject/*, @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum*/) {
        //PageHelper.startPage(pageNum, 10);
        List<AdProject> list=this.adProjectService.queryAll(adProject);
        //PageInfo pageInfo = new PageInfo<>(list);
        return Result.success(list);
    }

    /**
     * 单条项目更新/插入，当传入对象有id则更新，无id就插入
     * @param adProject
     * @return
     */

@RequestMapping("update")
@CrossOrigin
public Map update(AdProject adProject) {
    System.out.println(adProject);
    Map<String, Object> map = new HashMap<>();
    if(adProject.getProid()!=null){//根据是否有id进行插入判断
        adProjectService.update(adProject);
        map.put("msg","更新完成");
        map.put("data","success");
        return map;
    }
    adProjectService.insert(adProject);
    map.put("msg","插入完成");
    map.put("data","success");
    return map;
}
}