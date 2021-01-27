package com.zzb.service;

import com.zzb.entity.StProjectstage;
import java.util.List;

/**
 * (StProjectstage)表服务接口
 *
 * @author makejava
 * @since 2021-01-15 17:06:10
 */
public interface StProjectstageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StProjectstage queryById(Integer id);

    StProjectstage queryByNum(String projectNum,String stage);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StProjectstage> queryAllByLimit(int offset, int limit);

    List<StProjectstage> selectAll(StProjectstage stProjectstage);

    /**
     * 新增数据
     *
     * @param stProjectstage 实例对象
     * @return 实例对象
     */
    StProjectstage insert(StProjectstage stProjectstage);

    /**
     * 修改数据
     *
     * @param stProjectstage 实例对象
     * @return 实例对象
     */
    StProjectstage update(StProjectstage stProjectstage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    int deleteByNum(String projectnum);

}