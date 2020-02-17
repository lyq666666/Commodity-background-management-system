package com.lyq.ssm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lyq.ssm.dao.OrdersDao;
import com.lyq.ssm.domain.orders;
import com.lyq.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;




    @Override
    public List<orders> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public orders findById(String id) {
        return ordersDao.findById(id);
    }
}
