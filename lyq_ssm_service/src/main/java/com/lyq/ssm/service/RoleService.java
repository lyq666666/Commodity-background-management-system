package com.lyq.ssm.service;

import com.lyq.ssm.domain.Permission;
import com.lyq.ssm.domain.Role;

import java.util.List;

public interface RoleService {


    public List<Role> findAll();

    public void save(Role role);

    public Role findById(String roleId);

    public List<Permission> findOtherPermissionByRoleId(String roleId);

    public void addPermissionToRole(String roleId, List<String> permissionIdList);
}
