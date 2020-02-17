package com.lyq.ssm.dao;

import com.lyq.ssm.domain.Traveller;
import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByordersId(String ordersId);

}
