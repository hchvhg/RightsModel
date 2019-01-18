package com.bsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoft.bean.User;
import com.bsoft.mapper.UserMapper;
import com.bsoft.service.UserService;
/**
 * UserServiceImpl
 * @description TODO
 * @author zk
 * @date 2019年1月10日 下午3:50:24
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
    @Override
    public List<User> getUserList(String name) {
        return userMapper.getUserList(name);
    }
    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }
    @Override
    public User getUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

}
