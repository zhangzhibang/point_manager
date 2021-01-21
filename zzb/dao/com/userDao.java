package com;

import com.user;

public interface userDao {
    int deleteByPrimaryKey(String userid);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
}