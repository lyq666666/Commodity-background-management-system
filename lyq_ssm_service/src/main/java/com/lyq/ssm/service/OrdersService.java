package com.lyq.ssm.service;

import com.lyq.ssm.domain.orders;

import java.util.List;

public interface OrdersService {

    public List<orders> findAll(int page, int size);

    public orders findById(String id);
}
