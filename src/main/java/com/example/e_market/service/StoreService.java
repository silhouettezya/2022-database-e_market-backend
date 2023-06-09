package com.example.e_market.service;

import com.example.e_market.entity.Good;
import com.example.e_market.entity.Store;

import java.util.List;

public interface StoreService {
    List<Good> getAllGoods(int sId);

    void updateGood(Good good);

    int regin(Store store);

    Store login(String account, String pwd);

    void addGood(int sId, Good good);


}
