package com.bsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoft.bean.Role;
import com.bsoft.mapper.RoleMapper;
import com.bsoft.service.RoleService;

/**
 * RoleServiceImpl
 * @description TODO
 * @author zk
 * @date 2019年1月14日 上午10:47:24
 * @version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList(String rolename) {
        return roleMapper.getRoleList(rolename);
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public void deleteRolesByUserId(Long userId) {
        roleMapper.deleteRolesByUserId(userId);
    }

    @Override
    public void addUserRoles(Long userId, List<Long> roleIdList) {
        roleMapper.addUserRoles(userId,roleIdList);
    }

    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

}
