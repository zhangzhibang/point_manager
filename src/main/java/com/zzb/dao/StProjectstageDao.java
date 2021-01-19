package com.zzb.dao;

import com.zzb.entity.StProjectstage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (StProjectstage)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-15 17:06:10
 */
@Mapper
@Repository
public interface StProjectstageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StProjectstage queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StProjectstage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stProjectstage 实例对象
     * @return 对象列表
     */
    List<StProjectstage> queryAll(StProjectstage stProjectstage);

    /**
     * 新增数据
     *
     * @param stProjectstage 实例对象
     * @return 影响行数
     */
    int insert(StProjectstage stProjectstage);

    /**
     * 修改数据
     *
     * @param stProjectstage 实例对象
     * @return 影响行数
     */
    int update(StProjectstage stProjectstage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}