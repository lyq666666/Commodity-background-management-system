package com.lyq.ssm.controller;


import com.lyq.ssm.domain.Role;
import com.lyq.ssm.domain.UserInfo;
import com.lyq.ssm.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class userController {


    @Autowired
    private userService userService;


    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id)
    {
        ModelAndView mv = new ModelAndView();
        UserInfo user=userService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show1");
        return mv;
    }




    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='ssm'")//只有ssm用户可以添加用户
    public String save(UserInfo userInfo)
    {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")//所有admin权限的用户都可以查看用户列表
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos=userService.findAll();

        mv.addObject("userList", userInfos);
        mv.setViewName("user-list");
        return mv;
    }


    //查询对应用户的id和此用户可以添加的角色List
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String userId) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> roleList=userService.findOtherRole(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }


    //添加角色信息到用户关联角色表并加以展示
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) List<String> roleIds)
    {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }



}
