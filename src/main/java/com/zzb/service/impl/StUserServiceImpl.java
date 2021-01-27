package com.zzb.service.impl;

import com.zzb.entity.StUser;
import com.zzb.dao.StUserDao;
import com.zzb.service.StUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StUser)表服务实现类
 *
 * @author makejava
 * @since 2021-01-25 13:38:53
 */
@Service("stUserService")
public class StUserServiceImpl implements StUserService {
    @Resource
    private StUserDao stUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StUser queryById(Integer id) {
        return this.stUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<StUser> queryAllByLimit(int offset, int limit) {
        return this.stUserDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<StUser> queryAll(StUser stUser) {
        return stUserDao.queryAll(stUser);
    }

    @Override
    public String getSFidByName(String username) {
        return stUserDao.getSFidByName(username);
    }

    @Override
    public StUser queryBySF(String sfid) {
        return stUserDao.queryBySF(sfid);
    }

    /**
     * 新增数据
     *
     * @param stUser 实例对象
     * @return 实例对象
     */
    @Override
    public StUser insert(StUser stUser) {
        this.stUserDao.insert(stUser);
        return stUser;
    }

    /**
     * 修改数据
     *
     * @param stUser 实例对象
     * @return 实例对象
     */
    @Override
    public StUser update(StUser stUser) {
        this.stUserDao.update(stUser);
        return this.queryById(stUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.stUserDao.deleteById(id) > 0;
    }
}