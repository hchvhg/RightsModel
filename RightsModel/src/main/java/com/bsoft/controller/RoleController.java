package com.bsoft.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsoft.bean.Role;
import com.bsoft.service.RoleService;

/**
 * RoleController
 * @description TODO
 * @author zk
 * @date 2019年1月14日 上午10:44:35
 * @version 1.0
 */
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("getRoleList")
    public Map<String,Object> getRoleList(@RequestParam Map<String,String> paramMap){
        String rolename = paramMap.get("rolename");
        List<Role> roleList = roleService.getRoleList(rolename);
        Map<String,Object> resMap = new HashMap<String, Object>();
        resMap.put("code", 200);
        resMap.put("msg", "请求成功！");
        resMap.put("data", roleList);
        return resMap;
    }
    
    @GetMapping("getRoleData")
    public Map<String,Object> getRoleData(@RequestParam Map<String,String> paramMap){
        String id = paramMap.get("userId");
        Long userId = null;
        if(StringUtils.isNotBlank(id)){
            userId = Long.parseLong(id);
        }
        //获取当前用户的当前角色集
        List<Role> rolesByUserId = roleService.getRolesByUserId(userId);
        //获取所有角色
        List<Role> allRoles = roleService.getRoleList(null);
        Map<String,Object> resMap = new HashMap<String, Object>();
        resMap.put("code", 200);
        resMap.put("msg", "请求成功！");
        resMap.put("data1", rolesByUserId);
        resMap.put("data2", allRoles);
        return resMap;
    }
    
    @PostMapping("setUserRoles")
    public Map<String,Object> setUserRoles(@RequestParam Map<String,String> paramMap){
        String id = paramMap.get("userId");
        Long userId = Long.valueOf(id);
        String roles = paramMap.get("roleIds");
        roleService.deleteRolesByUserId(userId);
        if(StringUtils.isNotBlank(roles)){
            String[] roleArr = roles.split(",");
            List<Long> roleIdList = new ArrayList<Long>();
            for (String idStr : roleArr) {
                roleIdList.add(Long.valueOf(idStr));
            }
            roleService.addUserRoles(userId,roleIdList);
        }
        Map<String,Object> resMap = new HashMap<String, Object>();
        resMap.put("code", 200);
        resMap.put("msg", "操作成功！");
        return resMap;
    }
    
    @PostMapping("addRole")
    public Map<String,Object> addRole(@RequestParam Map<String,String> paramMap){
        String rolename = paramMap.get("rolename");
        String description = paramMap.get("description");
        Role role = new Role();
        role.setRoleName(rolename);
        role.setDescription(description);
        short status = 1;
        role.setStatus(status);
        roleService.addRole(role);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", 200);
        resultMap.put("msg", "操作成功");
        return resultMap;
    }
    
    @PostMapping("updateRole")
    public Map<String,Object> updateRole(@RequestParam Map<String,String> paramMap){
        String id = paramMap.get("roleId");
        Long roleId = null;
        if(StringUtils.isNoneBlank(id)){
            roleId = Long.valueOf(id);
        }
        String rolename = paramMap.get("rolename");
        String description = paramMap.get("description");
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(rolename);
        role.setDescription(description);
        roleService.updateRole(role);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", 200);
        resultMap.put("msg", "操作成功！");
        return resultMap;
    }
    
    @PostMapping("deleteRole")
    public Map<String,Object> deleteRole(@RequestParam Map<String,String> paramMap){
        String id = paramMap.get("roleId");
        Long roleId = null;
        if(StringUtils.isNotBlank(id)){
            roleId = Long.valueOf(id);
        }
        roleService.deleteRoleById(roleId);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", 200);
        resultMap.put("msg", "请求成功！");
        return resultMap;
    }
    
}
