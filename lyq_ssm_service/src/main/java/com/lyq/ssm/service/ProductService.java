package com.lyq.ssm.service;

import com.lyq.ssm.domain.product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<product> findAll();

    public void save(product pro)throws Exception;
}
