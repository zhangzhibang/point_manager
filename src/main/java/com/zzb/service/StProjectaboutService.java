package com.zzb.service;

import com.zzb.TO.selectTO;
import com.zzb.PO.InformationPO;
import com.zzb.VO.InformationVO;
import com.zzb.entity.StProjectabout;

import java.util.List;

/**
 * (StProjectabout)表服务接口
 *
 * @author makejava
 * @since 2021-01-15 17:03:19
 */
public interface StProjectaboutService {

    /**
     * 通过ID查询单条数据
     *
     * @param projectNum 主键
     * @return 实例对象
     */
    StProjectabout queryByNum(String projectNum);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stProjectabout 实例对象
     * @return 对象列表
     */
    List<StProjectabout> queryAll(StProjectabout stProjectabout);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StProjectabout> queryAllByLimit(int offset, int limit);


    List<InformationPO> queryAllInformation();

    List<InformationVO>  sortList(List<InformationPO> list);

    /**
     * 新增数据
     *
     * @param stProjectabout 实例对象
     * @return 实例对象
     */
    StProjectabout insert(StProjectabout stProjectabout);

    /**
     * 修改数据
     *
     * @param stProjectabout 实例对象
     * @return 实例对象
     */
    StProjectabout update(StProjectabout stProjectabout);

    /**
     *
     *
     *
     * @return 是否成功
     */
    int updateByProjrctNum(StProjectabout stProjectabout);


    int deleteByNum(String projectNum);

    selectTO resultSub(int pagesize, int pagenumber,List<InformationVO> collect);

}