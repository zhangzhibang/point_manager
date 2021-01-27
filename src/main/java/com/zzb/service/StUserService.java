package com.zzb.service;

import com.zzb.entity.StUser;

import java.util.List;

/**
 * (StUser)表服务接口
 *
 * @author makejava
 * @since 2021-01-25 13:38:53
 */
public interface StUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StUser> queryAllByLimit(int offset, int limit);

    List<StUser> queryAll(StUser stUser);

    String getSFidByName(String username);

    StUser queryBySF(String sfid);

    /**
     * 新增数据
     *
     * @param stUser 实例对象
     * @return 实例对象
     */
    StUser insert(StUser stUser);

    /**
     * 修改数据
     *
     * @param stUser 实例对象
     * @return 实例对象
     */
    StUser update(StUser stUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}