package com.lyq.ssm.service.Impl;

import com.lyq.ssm.dao.RoleDao;
import com.lyq.ssm.domain.Permission;
import com.lyq.ssm.domain.Role;
import com.lyq.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);

    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissionByRoleId(String roleId) {
        return roleDao.findOtherPermissionByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, List<String> permissionIdList) {
        for (String permissionId : permissionIdList) {

        roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
