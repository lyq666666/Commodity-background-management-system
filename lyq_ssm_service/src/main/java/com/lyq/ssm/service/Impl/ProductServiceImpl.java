package com.lyq.ssm.service.Impl;

import com.lyq.ssm.dao.ProductDao;
import com.lyq.ssm.domain.product;
import com.lyq.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;



    public List<product> findAll() {
        List<product> list = productDao.findAll();
        return productDao.findAll();
    }

    @Override
    public void save(product pro) throws Exception {
        productDao.save(pro);
    }
}
