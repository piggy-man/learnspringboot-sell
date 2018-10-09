package com.sell.service.serviceImpl;

import com.sell.dataobject.SellerInfo;
import com.sell.repository.SellerInfoRepository;
import com.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findByOpenId(String openId) {

        return repository.findByOpenId(openId);
    }
}
