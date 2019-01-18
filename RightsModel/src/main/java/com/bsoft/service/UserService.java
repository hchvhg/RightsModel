package com.bsoft.service;

import java.util.List;

import com.bsoft.bean.User;

/**
 * UserService
 * @description TODO
 * @author zk
 * @date 2019年1月10日 下午3:48:40
 * @version 1.0
 */
public interface UserService {
    public User getUserByName(String name);

    public void updateUser(User user);

    public List<User> getUserList(String username);

    public void addUser(User user);
    
    public User getUserById(Long userId);
}
