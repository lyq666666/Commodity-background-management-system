package com.lyq.ssm.dao;

import com.lyq.ssm.domain.Permission;
import com.lyq.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "rolename"),
            @Result(property = "roleDesc", column = "roledesc"),
            @Result(property = "permissions", column = "id", many = @Many(select = "com.lyq.ssm.dao.permissionDao.findByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;


    @Select("select * from role")
    public List<Role> findAll();


    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role);

    @Select("select * from role where id=#{roleId}")
    public Role findById(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findOtherPermissionByRoleId(String roleId);


    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    public void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId")String permissionId);
}
