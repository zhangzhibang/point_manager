package com.zzb.dao;

import com.zzb.entity.StUserstage;
import com.zzb.entity.projectUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (StUserstage)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-15 17:06:34
 */
@Mapper
@Repository
public interface StUserstageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StUserstage queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StUserstage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询指定用户参与的项目列表
     * @param startYear
     * @param endYear
     * @return
     */
    List<projectUserPO> getProjectCountByYear(String startYear, String endYear);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stUserstage 实例对象
     * @return 对象列表
     */
    List<StUserstage> queryAll(StUserstage stUserstage);

    /**
     * 新增数据
     *
     * @param stUserstage 实例对象
     * @return 影响行数
     */
    int insert(StUserstage stUserstage);

    /**
     * 修改数据
     *
     * @param stUserstage 实例对象
     * @return 影响行数
     */
    int update(StUserstage stUserstage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}