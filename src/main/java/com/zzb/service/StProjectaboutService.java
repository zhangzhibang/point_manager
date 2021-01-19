package com.zzb.service;

import com.zzb.entity.InformationPO;
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
     * @param id 主键
     * @return 实例对象
     */
    StProjectabout queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StProjectabout> queryAllByLimit(int offset, int limit);


    List<InformationPO> queryAllInformation();

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
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}