package com.lyq.ssm.controller;

import com.lyq.ssm.domain.Permission;
import com.lyq.ssm.domain.Role;
import com.lyq.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {


    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Role> roleList = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }


    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //根据角色id查询角色和此角色可以添加的权限集合

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) {


        ModelAndView mv = new ModelAndView();
        Role role=roleService.findById(roleId);
        List<Permission> perList=roleService.findOtherPermissionByRoleId(roleId);

        mv.addObject("role",role);
        mv.addObject("permissionList", perList);
        mv.setViewName("role-permission-add");
        return mv;
    }


    //为对应Id的role添加选中的权限

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,@RequestParam(name = "ids",required = true)List<String> permissionIdList)
    {

        roleService.addPermissionToRole(roleId,permissionIdList);
        return "redirect:findAll.do";
    }


}
