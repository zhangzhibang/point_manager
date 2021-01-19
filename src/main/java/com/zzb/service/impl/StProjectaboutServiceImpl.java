package com.zzb.service.impl;

import com.zzb.entity.InformationPO;
import com.zzb.entity.RadioPO;
import com.zzb.entity.StProjectabout;
import com.zzb.dao.StProjectaboutDao;
import com.zzb.entity.projectUserPO;
import com.zzb.service.StProjectaboutService;
import com.zzb.service.StUserstageService;
import com.zzb.utils.dateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (StProjectabout)表服务实现类
 *
 * @author makejava
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
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StProjectabout queryById(Integer id) {
        return this.stProjectaboutDao.queryById(id);
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

    @Override
    public List<InformationPO> queryAllInformation() {

        List<InformationPO> list=this.stProjectaboutDao.queryAllInformation();
        List<projectUserPO>  projectUserList2018=stUserstageService.getProjectCountByYear("2018","2019");
        System.out.println("2018++++++++++++++++++++++++"+projectUserList2018);
        List<projectUserPO>  projectUserList2019=stUserstageService.getProjectCountByYear("2019","2020");
        System.out.println("2019++++++++++++++++++++++++"+projectUserList2019);
        List<projectUserPO>  projectUserList2020=stUserstageService.getProjectCountByYear("2020","2021");
        System.out.println("2020++++++++++++++++++++++++"+projectUserList2020);
        List<InformationPO> collect = list.stream()
                .map(item -> {
                    try {
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

                        if(!(projectUserList2018.size()<0)&&!(projectUserList2018.isEmpty())&&(projectUserList2018!=null))
                        {
                            System.out.println("success");
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

                        if(!(projectUserList2019.size()<0)&&!(projectUserList2019.isEmpty())&&(projectUserList2018!=null))
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

                        if(!(projectUserList2020.size()<0)&&!(projectUserList2020.isEmpty())&&(projectUserList2018!=null))
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

    public static RadioPO getChildren(InformationPO informationPO, List<InformationPO> all) throws ParseException {
        RadioPO radioPO = new RadioPO();
        List<InformationPO> collect = all.stream().filter(item ->
                item.getUsername().equals(informationPO.getUsername()) && item.getProjectnum().equals(informationPO.getProjectnum())
        ).collect(Collectors.toList());
        radioPO=dateUtil.SetRadio(collect);
        System.out.println(radioPO);
        return radioPO;
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
        return this.queryById(stProjectabout.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.stProjectaboutDao.deleteById(id) > 0;
    }
}