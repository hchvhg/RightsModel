package com.bsoft.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * UserController
 * @description TODO
 * @author zk
 * @date 2019年1月11日 下午3:53:53
 * @version 1.0
 */

import com.bsoft.bean.User;
import com.bsoft.service.UserService;
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/getUserList")
    public Map<String,Object> getUserList(@RequestParam Map<String,String> paramMap){
        String username = paramMap.get("username");
        List<User> userList = userService.getUserList(username);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", "请求成功");
        resultMap.put("code", 200);
        resultMap.put("data", userList);
        return resultMap;
    }
    
    @GetMapping("/getUserById")
    public Map<String,Object> getUserById(@RequestParam Map<String,String> paramMap){
        String id = paramMap.get("userId");
        Long userId = null;
        if(StringUtils.isNotBlank(id)){
            userId = Long.parseLong(id);
        }
        User user = userService.getUserById(userId);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", "请求成功");
        resultMap.put("code", 200);
        resultMap.put("data", user);
        return resultMap;
    }
    
    @PostMapping("/addUser")
    public Map<String,Object> addUser(@RequestParam Map<String,String> paramMap){
        String username = paramMap.get("username");
        String loginName = paramMap.get("loginName");
        String loginPassword = paramMap.get("loginPassword");
        String description = paramMap.get("description");
        User user = new User();
        user.setUserId(null);
        user.setUsername(username);
        user.setLoginName(loginName);
        user.setLoginPassword(loginPassword);
        user.setDescription(description);
//        user.setCreateUser(null);
        user.setCreateTime(new Date());
//        user.setIsAdmin(null);
        short sfyx = 1;
        user.setSfyx(sfyx);
        userService.addUser(user);
        Map<String,Object> resMap = new HashMap<String,Object>();
        resMap.put("code", 200);
        resMap.put("msg", "新增成功！");
        return resMap;
    }
    
    @PostMapping("/updateUser")
    public Map<String,Object> updateUser(@RequestParam Map<String,String> paramMap){
        Long userId = Long.parseLong(paramMap.get("userId"));
        String username = paramMap.get("username");
        String loginName = paramMap.get("loginName");
        String loginPassword = paramMap.get("loginPassword");
        String description = paramMap.get("description");
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setLoginName(loginName);
        user.setLoginPassword(loginPassword);
        user.setDescription(description);
        userService.updateUser(user);
        Map<String,Object> resMap = new HashMap<String,Object>();
        resMap.put("code", 200);
        resMap.put("msg", "修改成功！");
        return resMap;
    }
    
    @PostMapping("/deleteUser")
    public Map<String,Object> deleteUser(@RequestParam Map<String,String> paramMap){
        Long userId = Long.parseLong(paramMap.get("userId"));
        User user = new User();
        user.setUserId(userId);
        short sfyx = 0;
        user.setSfyx(sfyx);
        userService.updateUser(user);
        Map<String,Object> resMap = new HashMap<String,Object>();
        resMap.put("code", 200);
        resMap.put("msg", "删除成功！");
        return resMap;
    }
}
