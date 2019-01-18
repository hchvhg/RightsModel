package com.bsoft.service;

import java.util.List;

import com.bsoft.bean.Role;

/**
 * RoleService
 * @description TODO
 * @author zk
 * @date 2019年1月14日 上午10:46:28
 * @version 1.0
 */
public interface RoleService {
    List<Role> getRoleList(String rolename);

    List<Role> getRolesByUserId(Long userId);

    void deleteRolesByUserId(Long userId);

    void addUserRoles(Long userId, List<Long> roleIdList);

    void addRole(Role role);

    void deleteRoleById(Long roleId);

    void updateRole(Role role);
}
