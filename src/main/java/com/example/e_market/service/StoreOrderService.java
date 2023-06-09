package com.example.e_market.service;

import com.example.e_market.entity.StoreOrder;

import java.util.List;

public interface StoreOrderService {
    List<StoreOrder> getAllOrder(int Sid);

    StoreOrder getOrder(int oId);

    void setState(int oId, String oState);
}
