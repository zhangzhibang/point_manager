package com.zzb.dao;

import com.zzb.entity.AdProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (AdProject)表数据库访问层
 *
 * @author zzbang
 * @since 2020-10-26 14:49:00
 */
@Mapper
@Repository
public interface AdProjectDao {

    /**
     * 通过ID查询单条数据
     *
     * @param pronum 主键
     * @return 实例对象
     */
    AdProject queryById(Object pronum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AdProject> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param adProject 实例对象
     * @return 对象列表
     */
    List<AdProject> queryAll(AdProject adProject);

    /**
     * 新增数据
     *
     * @param adProject 实例对象
     * @return 影响行数
     */
    int insert(AdProject adProject);

    /**
     * 修改数据
     *
     * @param adProject 实例对象
     * @return 影响行数
     */
    int update(AdProject adProject);

    /**
     * 通过主键删除数据
     *
     * @param pronum 主键
     * @return 影响行数
     */
    int deleteById(Object pronum);

}