package com.example.e_market.service;

import com.example.e_market.entity.ExpressCompany;
import com.example.e_market.entity.Order;

import java.util.List;

public interface ExpressCompanyService {
    ExpressCompany login(String account, String pwd);

    List<Order> getAllOrder(int eId);

    void recvOrder(int eId, int oId);
}
