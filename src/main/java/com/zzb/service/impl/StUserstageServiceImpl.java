package com.zzb.service.impl;

import com.zzb.entity.*;
import com.zzb.dao.StUserstageDao;
import com.zzb.service.StProjectaboutService;
import com.zzb.service.StUserstageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (StUserstage)表服务实现类
 *
 * @author makejava
 * @since 2021-01-15 17:06:34
 */
@Service("stUserstageService")
public class StUserstageServiceImpl implements StUserstageService {
    @Resource
    private StUserstageDao stUserstageDao;
    @Autowired
    StProjectaboutService stProjectaboutService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StUserstage queryById(Integer id) {
        return this.stUserstageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<StUserstage> queryAllByLimit(int offset, int limit) {
        return this.stUserstageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param stUserstage 实例对象
     * @return 实例对象
     */
    @Override
    public StUserstage insert(StUserstage stUserstage) {
        this.stUserstageDao.insert(stUserstage);
        return stUserstage;
    }

    @Override
    public List<projectUserPO> getProjectCountByYear(String startYear, String endYear) {

        return stUserstageDao.getProjectCountByYear(startYear,endYear);
    }

    /**
     * 修改数据
     *
     * @param stUserstage 实例对象
     * @return 实例对象
     */
    @Override
    public StUserstage update(StUserstage stUserstage) {
        this.stUserstageDao.update(stUserstage);
        return this.queryById(stUserstage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.stUserstageDao.deleteById(id) > 0;
    }

    /**
     * 获取员工积分报表
     *
     * 每个子项获取一次他的总记录，最后去重
     * @return
     */
    @Override
    public List<UserPointVO> getUserPoint() {
        List<InformationPO> list = stProjectaboutService.queryAllInformation();
        List<UserPointVO> userPointVOList=new ArrayList<>();
        list.stream().map(item->{
            UserPointVO total = getTotal(item.getSfid(), item.getUsername(), list);
            userPointVOList.add(total);
            return item;
        }).collect(Collectors.toList());

        HashSet<UserPointVO> totallist = new HashSet<UserPointVO>(userPointVOList);
        ArrayList<UserPointVO> userPointVOS = new ArrayList<>(totallist);
        return userPointVOS;
    }

    /**
     * 获取部门积分总计
     * @return
     */
    @Override
    public List<DepPointVO> getDepPointList() {
        //TODO 增加查询部门项目数量SQL以及相关文件，整个得到部门报表
        return stUserstageDao.getDepPointList();
    }

    /**
     * 查询部门项目总计
     * @return
     */
    @Override
    public List<DepPointVO> getDepProjectCountList() {
        return stUserstageDao.getDepProjectCountList();
    }

    /**
     * 整个部门积分和项目数
     * @param DepName
     * @param list
     * @return
     */
    @Override
    public Integer getDepCount(String DepName, List<DepPointVO> list){
        List<DepPointVO> collect = list.stream().filter(item -> {
            return item.getDeptName().equals(DepName);
        }).collect(Collectors.toList());
        if(!(collect.size()<0)&&!(collect.isEmpty())&&(collect!=null)){
            return collect.get(0).getProjectCount();
        }
        return null;
    }

    /**
     * 获取指定员工的所有项目以及积分，进行累加之后得到一个中间数据UserPointVO，
     * 封装了该用户的总积分以及每年的对应积分
     * @param SFid
     * @param name
     * @param list
     * @return
     */

    public static UserPointVO getTotal(String SFid,String name,List<InformationPO> list){
        double total=0,point2018=0,point2019=0,point2020=0;
        UserPointVO userPointVO = new UserPointVO();
        List<InformationPO> collect = list.stream().filter(item -> {
            return item.getSfid().equals(SFid);
        }).collect(Collectors.toList());
        for (InformationPO info:collect){
            total+=info.getTotal();
            point2018+=info.getPoint2018();
            point2019+=info.getPoint2019();
            point2020+=info.getPoint2020();
        }
        userPointVO.setSFid(SFid)
                .setName(name)
                .setTotal(total)
                .setPoint2018(point2018)
                .setPoint2019(point2019)
                .setPoint2020(point2020);
        return userPointVO;
    }
}