package com.bsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsoft.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User getUserByName(@Param("name") String name);

    List<User> getUserList(@Param("name") String name);
}