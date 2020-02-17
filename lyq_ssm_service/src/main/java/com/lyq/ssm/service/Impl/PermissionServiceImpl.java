package com.lyq.ssm.service.Impl;

import com.lyq.ssm.dao.permissionDao;
import com.lyq.ssm.domain.Permission;
import com.lyq.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private permissionDao permissionDao;

    @Override
    public List<Permission> findAll() {

        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
