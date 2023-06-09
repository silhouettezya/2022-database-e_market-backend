package com.example.e_market.service;

import com.example.e_market.entity.Good;

import java.util.List;

public interface GoodService {
    List<Good> searchGoods(String key);

    Good getGood(int gId);

    List<Good> getSortedGoods(String order);

    List<Good> getCollections(int uId);

    void dropGood(int gId);
}
