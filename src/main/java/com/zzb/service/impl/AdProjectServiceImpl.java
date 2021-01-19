package com.zzb.service.impl;

import com.zzb.dao.AdProjectDao;
import com.zzb.entity.AdProject;
import com.zzb.service.AdProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AdProject)表服务实现类
 *
 * @author makejava
 * @since 2020-10-26 14:49:01
 */
@Service("adProjectService")
public class AdProjectServiceImpl implements AdProjectService {
    @Resource
    private AdProjectDao adProjectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param pronum 主键
     * @return 实例对象
     */
    @Override
    public AdProject queryById(Object pronum) {
        return this.adProjectDao.queryById(pronum);
    }

    @Override
    public List<AdProject> queryAll(AdProject adProject) {
        return this.adProjectDao.queryAll(adProject);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<AdProject> queryAllByLimit(int offset, int limit) {
        return this.adProjectDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param adProject 实例对象
     * @return 实例对象
     */
    @Override
    public AdProject insert(AdProject adProject) {
        this.adProjectDao.insert(adProject);
        return adProject;
    }

    /**
     * 修改数据
     *
     * @param adProject 实例对象
     * @return 实例对象
     */
    @Override
    public AdProject update(AdProject adProject) {
        this.adProjectDao.update(adProject);
        return this.queryById(adProject.getPronum());
    }

    /**
     * 通过主键删除数据
     *
     * @param pronum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object pronum) {
        return this.adProjectDao.deleteById(pronum) > 0;
    }
}