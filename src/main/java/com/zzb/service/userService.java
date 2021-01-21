package com.zzb.service;

import com.zzb.entity.user;

import java.util.List;

/**
 * (HrKquserlist)表服务接口
 *
 * @author makejava
 * @since 2021-01-20 10:07:51
 */
public interface userService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    user queryById(Object userid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<user> queryAllByLimit(int offset, int limit);


    List<user> queryAll(user user);

    String getSFidByName(String username);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    user insert(user user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    user update(user user);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(Object userid);

}