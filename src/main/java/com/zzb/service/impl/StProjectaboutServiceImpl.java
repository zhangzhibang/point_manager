package com.zzb.service.impl;

import com.zzb.entity.*;
import com.zzb.dao.StProjectaboutDao;
import com.zzb.service.StProjectaboutService;
import com.zzb.service.StUserstageService;
import com.zzb.utils.dateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (StProjectabout)表服务实现类
 *
 * @author zzbang
 * @since 2021-01-15 17:03:20
 */
@Service("stProjectaboutService")
public class StProjectaboutServiceImpl implements StProjectaboutService {
    @Resource
    private StProjectaboutDao stProjectaboutDao;
    @Autowired
    private StUserstageService stUserstageService;

    BigDecimal a;
    BigDecimal b;


    @Override
    public StProjectabout queryByNum(String projectNum) {
        return stProjectaboutDao.queryByNum(projectNum);
    }

    @Override
    public List<StProjectabout> queryAll(StProjectabout stProjectabout) {
        return stProjectaboutDao.queryAll(stProjectabout);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<StProjectabout> queryAllByLimit(int offset, int limit) {
        return this.stProjectaboutDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询出所有的项目以及人员信息，通过工具类判断年份占比，使用总积分与年份占比乘积得到年份积分；
     *
     * @return
     */
    @Override
    public List<InformationPO> queryAllInformation() {

        List<InformationPO> list=this.stProjectaboutDao.queryAllInformation();
        //查询18年总的项目数
        List<projectUserPO>  projectUserList2018=stUserstageService.getProjectCountByYear("2018","2019");
        //查询19年总的项目数
        List<projectUserPO>  projectUserList2019=stUserstageService.getProjectCountByYear("2019","2020");
        //查询20年总的项目数
        List<projectUserPO>  projectUserList2020=stUserstageService.getProjectCountByYear("2020","2021");
        List<InformationPO> collect = list.stream()
                .map(item -> {
                    try {
                        //遍历集合得到年份占比，精度值进行乘积
                        RadioPO children = getChildren(item, list);
                        item.setRadio2018(children.getRadio2018());
                        item.setRadio2019(children.getRadio2019());
                        item.setRadio2020(children.getRadio2020());
                        a=new BigDecimal(String.valueOf(item.getTotal()));
                        b=new BigDecimal(String.valueOf(children.getRadio2018()));
                        item.setPoint2018(a.multiply(b).doubleValue());
                        b=new BigDecimal(String.valueOf(children.getRadio2019()));
                        item.setPoint2019(a.multiply(b).doubleValue());
                        b=new BigDecimal(String.valueOf(children.getRadio2020()));
                        item.setPoint2020(a.multiply(b).doubleValue());

                        if((projectUserList2018.size()>0)&&!(projectUserList2018.isEmpty())&&(projectUserList2018!=null))
                        {
                            //把18年对应个人的项目数整合
                            for ( projectUserPO temp:projectUserList2018){
                                if(temp.getSFid().equals(item.getSfid())){
                                    item.setProjectCount2018(temp.getProjectCount());
                                    break;
                                }else {
                                    item.setProjectCount2018(0);
                                }
                            }
                        }else {
                            item.setProjectCount2018(0);
                        }

                        if((projectUserList2019.size()>0)&&!(projectUserList2019.isEmpty())&&(projectUserList2018!=null))
                        {
                            for ( projectUserPO temp:projectUserList2019){
                                if(temp.getSFid().equals(item.getSfid())){
                                    item.setProjectCount2019(temp.getProjectCount());
                                    break;
                                }else {
                                    item.setProjectCount2019(0);
                                }
                            }
                        }else {
                            item.setProjectCount2019(0);
                        }

                        if((projectUserList2020.size()>0)&&!(projectUserList2020.isEmpty())&&(projectUserList2018!=null))
                        {
                            for ( projectUserPO temp:projectUserList2020){
                                if(temp.getSFid().equals(item.getSfid())){
                                    item.setProjectCount2020(temp.getProjectCount());
                                    break;
                                }else {
                                    item.setProjectCount2020(0);
                                }
                            }
                        }else {
                            item.setProjectCount2020(0);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return item;
                })
                .collect(Collectors.toList());


        return collect;
    }

    /**
     * 在总记录中找到某个成员在某个项目中的所有所有记录，
     * @param informationPO
     * @param all
     * @return
     * @throws ParseException
     */
    public static RadioPO getChildren(InformationPO informationPO, List<InformationPO> all) throws ParseException {
        RadioPO radioPO = new RadioPO();
        List<InformationPO> collect = all.stream().filter(item ->
                item.getUsername().equals(informationPO.getUsername()) && item.getProjectnum().equals(informationPO.getProjectnum())
        ).sorted(Comparator.comparing(InformationPO::getEnddate))
                .collect(Collectors.toList());
        radioPO=dateUtil.SetRadio(collect);
        //System.out.println(radioPO);
        return radioPO;
    }

    /**
     * 合并同一成员同一个项目的数据，赋值到实体类
     * @param list
     * @return
     */
    @Override
    public List<InformationVO>  sortList(List<InformationPO> list){
        Integer i=0;
        List<InformationVO> informationList=new ArrayList<>();
        List<InformationPO> collect = list.stream().map(item -> {
            InformationVO information = getInformation(item, list);
            informationList.add(information);
            return item;
        }).collect(Collectors.toList());
        Set set=new HashSet(informationList);
        ArrayList<InformationVO> informationVOS = new ArrayList<InformationVO>(set);
        return informationVOS;
    }

    /**
     * 得到同一成员同一个项目的数据，将每个阶段每个年份的值相加，总积分相加
     * @param informationPO
     * @param all
     * @return
     */
    public static InformationVO getInformation(InformationPO informationPO, List<InformationPO> all){

        InformationVO informationVO=new InformationVO();
       informationVO.setProjectNum(informationPO.getProjectnum())
                .setProName(informationPO.getProName())
                .setProjectType(informationPO.getProjecttype())
                .setProjectRank(informationPO.getProjectrank())
                .setScore(informationPO.getScore())
                .setPointBase(informationPO.getPointbase())
                .setUsername(informationPO.getUsername())
                .setDeptName(informationPO.getDeptname())
                .setProjectCount2018(informationPO.getProjectCount2018())
                .setProjectCount2019(informationPO.getProjectCount2019())
                .setProjectCount2020(informationPO.getProjectCount2020());
        List<InformationPO> collect = all.stream().filter(item ->
                item.getUsername().equals(informationPO.getUsername()) && item.getProjectnum().equals(informationPO.getProjectnum())
        ).collect(Collectors.toList());

        double sumTotal = 0;
        double sum2018 = 0;
        double sum2019 = 0;
        double sum2020 = 0;
        for (InformationPO item:collect){
            sumTotal+=item.getTotal();
            sum2018+=item.getPoint2018();
            sum2019+=item.getPoint2019();
            sum2020+=item.getPoint2020();
            if (item.getProjectstage().equals("P1")||item.getProjectstage()=="P1") {
                //System.out.println("P1:"+item.getStageradio());
                informationVO.setP1radio(item.getStageradio());
                informationVO.setP1userRadio(item.getUradio());
            }
            if (item.getProjectstage().equals("P2")||item.getProjectstage()=="P2") {
                //System.out.println("P2:"+item.getStageradio());
                informationVO.setP2radio(item.getStageradio());
                informationVO.setP2userRadio(item.getUradio());
            }
            if (item.getProjectstage().equals("P3")||item.getProjectstage()=="P3") {
                //System.out.println("P3:"+item.getStageradio());
                informationVO.setP3radio(item.getStageradio());
                informationVO.setP3userRadio(item.getUradio());
            }
            if (item.getProjectstage().equals("P4")||item.getProjectstage()=="P4") {
                //System.out.println("P4:"+item.getStageradio());
                informationVO.setP4radio(item.getStageradio());
                informationVO.setP4userRadio(item.getUradio());
            }
            if (item.getProjectstage().equals("P5")||item.getProjectstage()=="P5") {
               // System.out.println("P5:"+item.getStageradio());
                informationVO.setP5radio(item.getStageradio());
                informationVO.setP5userRadio(item.getUradio());
            }
        }
        informationVO.setTotal(sumTotal)
                .setPoint2018(sum2018)
                .setPoint2019(sum2019)
                .setPoint2020(sum2020);
        return informationVO;

    }

    /**
     * 新增数据
     *
     * @param stProjectabout 实例对象
     * @return 实例对象
     */
    @Override
    public StProjectabout insert(StProjectabout stProjectabout) {
        this.stProjectaboutDao.insert(stProjectabout);
        return stProjectabout;
    }

    /**
     * 修改数据
     *
     * @param stProjectabout 实例对象
     * @return 实例对象
     */
    @Override
    public StProjectabout update(StProjectabout stProjectabout) {
        this.stProjectaboutDao.update(stProjectabout);
        return stProjectabout;
    }

    @Override
    public int updateByProjrctNum(StProjectabout stProjectabout) {
        return stProjectaboutDao.updateByProjrctNum(stProjectabout);
    }

    @Override
    public int deleteByNum(String projectNum) {
        return stProjectaboutDao.deleteByNum(projectNum);
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */

}