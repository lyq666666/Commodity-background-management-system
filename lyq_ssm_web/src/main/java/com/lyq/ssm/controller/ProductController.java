package com.lyq.ssm.controller;

import com.lyq.ssm.domain.product;
import com.lyq.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;




    //新建一个产品
    @RequestMapping("/save.do")
   /* @RolesAllowed("ADMIN")//只有管理员权限ADMIN的用户可以查询产品信息*/
    public String save(product pro) throws Exception {
        productService.save(pro);
        return "redirect:findAll.do";
    }



    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")//只有管理员权限ADMIN的用户可以查询产品信息
    public ModelAndView findAll()
    {
        ModelAndView mv = new ModelAndView();
        List<product> ps = productService.findAll();
        System.out.println("ps_size:"+ps.size());
        //将数据存放到下面的对象之中
        mv.addObject("productList",ps);
        //设置要跳转的视图名，交给视图解析器进行页面的响应跳转
        mv.setViewName("product-list1");
        return mv;
    }
}
