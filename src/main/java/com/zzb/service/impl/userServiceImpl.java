//package com.zzb.service.impl;
//
//import com.zzb.entity.user;
//import com.zzb.service.userService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * (HrKquserlist)表服务实现类
// *
// * @author makejava
// * @since 2021-01-20 10:07:51
// */
//@Service("hrKquserlistService")
//public class userServiceImpl implements userService {
//    @Resource
//    private com.zzb.dao.userDao userDao;
//
//    /**
//     * 通过ID查询单条数据
//     *
//     * @param userid 主键
//     * @return 实例对象
//     */
//    @Override
//    public user queryById(Object userid) {
//        return this.userDao.queryById(userid);
//    }
//
//    /**
//     * 查询多条数据
//     *
//     * @param offset 查询起始位置
//     * @param limit 查询条数
//     * @return 对象列表
//     */
//    @Override
//    public List<user> queryAllByLimit(int offset, int limit) {
//        return this.userDao.queryAllByLimit(offset, limit);
//    }
//
//    @Override
//    public List<user> queryAll(user user) {
//        return userDao.queryAll(user);
//    }
//
//    @Override
//    public String getSFidByName(String username) {
//        return userDao.getSFidByName(username);
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param user 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public user insert(user user) {
//        this.userDao.insert(user);
//        return user;
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param user 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public user update(user user) {
//        this.userDao.update(user);
//        return this.queryById(user.getUserid());
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param userid 主键
//     * @return 是否成功
//     */
//    @Override
//    public boolean deleteById(Object userid) {
//        return this.userDao.deleteById(userid) > 0;
//    }
//}