package com.bsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsoft.bean.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> getRoleList(@Param("rolename") String rolename);

    List<Role> getRolesByUserId(@Param("userId") Long userId);
    
    void deleteRolesByUserId(@Param("userId") Long userId);

    void addUserRoles(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);
}