package com.lyq.ssm.service;

import com.lyq.ssm.domain.Permission;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PermissionService {

    public List<Permission> findAll();

    public void save(Permission permission);
}
