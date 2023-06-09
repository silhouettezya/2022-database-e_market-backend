package com.example.e_market.service;

import com.example.e_market.entity.Cart;
import com.example.e_market.entity.Order;

import java.util.List;

public interface OrderService {
    void buildOrder(int price, Cart cart);

    void confirmOrder(int oId);

    List<Order> getOrders();
}
