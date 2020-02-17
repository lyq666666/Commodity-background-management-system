package com.lyq.ssm.controller;


import com.lyq.ssm.domain.Permission;
import com.lyq.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll()
    {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");

        return mv;
    }

    /*添加权限*/
    @RequestMapping("/save.do")
    public String save(Permission permission)
    {
        permissionService.save(permission);
        return "redirect:findAll.do";

    }
}
