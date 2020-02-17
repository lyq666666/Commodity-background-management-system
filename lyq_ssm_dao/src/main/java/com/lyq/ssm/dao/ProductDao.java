package com.lyq.ssm.dao;

import com.lyq.ssm.domain.orders;
import com.lyq.ssm.domain.product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层接口
 */

public interface ProductDao {



    @Select("select * from product where id=#{id}")
    public product findById(String Id);

    @Select("select * from product")
    public List<product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(product pro)throws  Exception;



}
