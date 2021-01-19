package com.zzb.service;

import com.zzb.entity.AdProject;

import java.util.List;

/**
 * (AdProject)表服务接口
 *
 * @author makejava
 * @since 2020-10-26 14:49:00
 */
public interface AdProjectService {

    /**
     * 通过ID查询单条数据
     *
     * @param pronum 主键
     * @return 实例对象
     */
    AdProject queryById(Object pronum);

    List<AdProject> queryAll(AdProject adProject);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AdProject> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param adProject 实例对象
     * @return 实例对象
     */
    AdProject insert(AdProject adProject);

    /**
     * 修改数据
     *
     * @param adProject 实例对象
     * @return 实例对象
     */
    AdProject update(AdProject adProject);

    /**
     * 通过主键删除数据
     *
     * @param pronum 主键
     * @return 是否成功
     */
    boolean deleteById(Object pronum);

}