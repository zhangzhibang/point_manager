package com.zzb.dao;

import com.zzb.PO.InformationPO;
import com.zzb.entity.StProjectabout;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (StProjectabout)表数据库访问层
 *
 * @author zzbang
 * @since 2021-01-15 17:03:19
 */
@Mapper
@Repository
public interface StProjectaboutDao {

    /**
     * 通过ID查询单条数据
     *
     * @param projectNum 项目号
     * @return 实例对象
     */
    StProjectabout queryByNum(String projectNum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StProjectabout> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stProjectabout 实例对象
     * @return 对象列表
     */
    List<StProjectabout> queryAll(StProjectabout stProjectabout);

    /**
     * 查询总表积分
     * @return
     */
    List<InformationPO> queryAllInformation();


    /**
     * 新增数据
     *
     * @param stProjectabout 实例对象
     * @return 影响行数
     */
    int insert(StProjectabout stProjectabout);

    /**
     * 修改数据
     *
     * @param stProjectabout 实例对象
     * @return 影响行数
     */
    int update(StProjectabout stProjectabout);

/**
 * 删除项目，只更改状态
 */
    int deleteByNum(String projectNum);
    /**
     * 更改项目属性，只更改状态为1的
     *
     * @param stProjectabout 项目
     * @return 影响行数
     */
    int updateByProjrctNum(StProjectabout stProjectabout);

}