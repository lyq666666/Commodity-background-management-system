package com.lyq.ssm.dao;

import com.lyq.ssm.domain.Member;
import com.lyq.ssm.domain.orders;
import com.lyq.ssm.domain.product;
import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property ="orderNum" ,column ="orderNum" ),
            @Result(property ="orderTime" ,column ="orderTime" ),
            @Result(property ="orderTimeStr" ,column ="orderTimeStr" ),
            @Result(property ="orderStatus" ,column ="orderStatus" ),
            @Result(property ="peopleCount" ,column ="peopleCount" ),
            @Result(property ="payType" ,column ="payType" ),
            @Result(property ="orderDesc" ,column ="orderDesc" ),
            @Result(property ="product" ,column ="productId",javaType =product.class,one=@One(select = "com.lyq.ssm.dao.ProductDao.findById")),
    })
    public List<orders> findAll();

    @Select("select * from orders where id=#{ordersid}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderTimeStr", column = "orderTimeStr"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = product.class, one = @One(select = "com.lyq.ssm.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one=@One(select="com.lyq.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many =@Many(select = "com.lyq.ssm.dao.TravellerDao.findByordersId")),
    })
    public orders findById(String ordersid);


    public static void main(String[] args) {

    }
}
