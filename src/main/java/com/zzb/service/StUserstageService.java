package com.zzb.service;

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
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StUserstage queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StUserstage> queryAllByLimit(int offset, int limit);

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


    List<UserPointVO> getUserPoint();

}