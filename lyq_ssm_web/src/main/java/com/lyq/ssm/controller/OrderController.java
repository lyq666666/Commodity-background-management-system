package com.lyq.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.lyq.ssm.domain.orders;
import com.lyq.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrdersService ordersService;


    //分页查询订单基本信息
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {
        List<orders> ordersList = ordersService.findAll(page, size);
        ModelAndView mv = new ModelAndView();
        //pageInfo就是一个分页的bean对象
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    //查询订单详细信息
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        orders orders = ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }





    //查询所有订单信息
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll()
    {
        ModelAndView mv=new ModelAndView();

        List<orders> orderlists = ordersService.findAll();
        System.out.println("size:"+orderlists.size());
        mv.addObject("ordersList", orderlists);
        mv.setViewName("orders-list");
        return mv;
    }*/

}
