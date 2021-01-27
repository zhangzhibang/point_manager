package com.zzb.service.impl;

import com.zzb.entity.StProjectstage;
import com.zzb.dao.StProjectstageDao;
import com.zzb.service.StProjectstageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StProjectstage)表服务实现类
 *
 * @author makejava
 * @since 2021-01-15 17:06:10
 */
@Service("stProjectstageService")
public class StProjectstageServiceImpl implements StProjectstageService {
    @Resource
    private StProjectstageDao stProjectstageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StProjectstage queryById(Integer id) {
        return this.stProjectstageDao.queryById(id);
    }

    @Override
    public StProjectstage queryByNum(String projectNum,String stage){
        return stProjectstageDao.queryByNum(projectNum,stage);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<StProjectstage> queryAllByLimit(int offset, int limit) {
        return this.stProjectstageDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<StProjectstage> selectAll(StProjectstage stProjectstage) {
        return stProjectstageDao.queryAll(stProjectstage);
    }

    /**
     * 新增数据
     *
     * @param stProjectstage 实例对象
     * @return 实例对象
     */
    @Override
    public StProjectstage insert(StProjectstage stProjectstage) {
        this.stProjectstageDao.insert(stProjectstage);
        return stProjectstage;
    }

    /**
     * 修改数据
     *
     * @param stProjectstage 实例对象
     * @return 实例对象
     */
    @Override
    public StProjectstage update(StProjectstage stProjectstage) {
        this.stProjectstageDao.update(stProjectstage);
        return this.queryById(stProjectstage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.stProjectstageDao.deleteById(id) > 0;
    }

    @Override
    public int deleteByNum(String projectnum) {
        return stProjectstageDao.deleteByNum(projectnum);
    }
}