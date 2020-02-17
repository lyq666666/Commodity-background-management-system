package com.lyq.ssm.service;

import com.lyq.ssm.domain.Role;
import com.lyq.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface userService extends UserDetailsService {


    List<UserInfo> findAll();

    public void save(UserInfo userInfo);

    public UserInfo findById(String id);

    public List<Role> findOtherRole(String userId);

    public void addRoleToUser(String userId, List<String> roleIds);
}
