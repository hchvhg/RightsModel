package com.bsoft.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsoft.bean.User;
import com.bsoft.service.UserService;

/**
 * LoginController
 * @description TODO
 * @author zk
 * @date 2019年1月10日 下午4:01:34
 * @version 1.0
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public Map<String,Object> login(@RequestParam Map<String,String> paramMap) throws Exception{
        Map<String,Object> resMap = new HashMap<String,Object>();
        String userName = paramMap.get("username");
        String password = paramMap.get("password");
        if(StringUtils.isBlank(userName)){
            resMap.put("code", "FAIL");
            resMap.put("msg", "用户名不能为空！");
            return resMap;
        }
        if(StringUtils.isBlank(password)){
            resMap.put("code", "FAIL");
            resMap.put("msg", "密码不能为空！");
            return resMap;
        }
        //根据用户名和密码查询数据
        User user = userService.getUserByName(userName);
        if(user != null){
            if(StringUtils.equals(password, user.getLoginPassword())){
                resMap.put("userId", user.getUserId());
                resMap.put("userName", user.getLoginName());
                //更新登陆信息
                user.setLastLoginTime(new Date());
                userService.updateUser(user);
                System.out.println("登陆成功！");
            }else{
                resMap.put("return_code", "FAIL");
                resMap.put("return_msg", "密码错误！");
            }
        }else{
            resMap.put("return_code", "FAIL");
            resMap.put("return_msg", "没有该用户！");
        }
        return resMap;
    }
}
