package com.sell.service;

import com.sell.dataobject.SellerInfo;

public interface SellerService  {

    SellerInfo findByOpenId(String openId);
}
