package com.lyq.ssm.dao;

import com.lyq.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface permissionDao {


    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{roleId})")
    public List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    public List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);
}
