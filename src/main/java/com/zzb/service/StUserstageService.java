package com.zzb.service;

import com.zzb.TO.UserPointTO;
import com.zzb.entity.DepPointVO;
import com.zzb.entity.StUserstage;
import com.zzb.entity.UserPointVO;
import com.zzb.entity.projectUserPO;

import java.util.List;

/**
 * (StUserstage)表服务接口
 *
 * @author makejava
 * @since 2021-01-15 17:06:34
 */
public interface StUserstageService {

    /**
     *
     */
    List<StUserstage> queryByNameandProject(String username,String projectnum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StUserstage> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param stUserstage 实例对象
     * @return 对象列表
     */
    List<StUserstage> queryAll(StUserstage stUserstage);


    int deleteByNameAndNum(String projectNum,String userName);
    /**
     * 新增数据
     *
     * @param stUserstage 实例对象
     * @return 实例对象
     */
    StUserstage insert(StUserstage stUserstage);

    /**
     * 获取指定年限内的员工的项目数
     * @param startYear
     * @param endYear
     * @return
     */
    List<projectUserPO> getProjectCountByYear(String startYear, String endYear);
    /**
     * 修改数据
     *
     * @param stUserstage 实例对象
     * @return 实例对象
     */
    StUserstage update(StUserstage stUserstage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    int deleteByNum(String projectNum);


    /**
     * 个人积分总计
     * @return
     */
    List<UserPointVO> getUserPoint();

    /**
     * 部门积分总计
     * @return
     */
    List<DepPointVO> getDepPointList();

    /**
     * 查询部门项目总计
     */
    List<DepPointVO> getDepProjectCountList();

    /**
     * 整合项目
     * @param DepName
     * @param list
     * @return
     */
    Integer getDepCount(String DepName,List<DepPointVO> list);

    UserPointTO getSubList(List<UserPointVO> list, int pagesize, int pagenumber);

}