package com.example.e_market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.e_market.entity.*;
import com.example.e_market.mapper.*;
import com.example.e_market.service.GoodService;
import com.example.e_market.service.UserLogService;
import com.example.e_market.service.ex.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl implements GoodService {
    @Resource
    GoodMapper goodMapper;

    @Resource
    StoreGoodMapper storeGoodMapper;

    @Resource
    CollectMapper collectMapper;

    @Resource
    CartMapper cartMapper;

    @Resource
    OrderGoodMapper orderGoodMapper;

    @Resource
    UserLogService userLogService;

    @Override
    public List<Good> searchGoods(String key) {
        QueryWrapper<Good> qw = new QueryWrapper<>();
        qw.like("g_name", key);
        List<Good> goods = goodMapper.selectList(qw);
        for (Good good : goods) {
            QueryWrapper<StoreGood> qw2 = new QueryWrapper<>();
            qw.like("g_id", good.getGId());
            StoreGood storeGood = storeGoodMapper.selectOne(qw2);
            good.setAmount(storeGood.getAmount());
        }
        return goods;
    }

    @Override
    public Good getGood(int gId) {
        return goodMapper.selectById(gId);
    }

    @Override
    public List<Good> getSortedGoods(String order) {
        List<StoreGood> storeGoods = storeGoodMapper.selectList(null);
        List<Good> goods = new ArrayList<>();
        for (StoreGood storeGood : storeGoods) {
            Good good = goodMapper.selectById(storeGood.getGId());
            good.setAmount(storeGood.getAmount());
            goods.add(good);
        }
        if (order.equals("price-up")) {
            goods = goods.stream().sorted(Comparator.comparing(Good::getGPrice,
                    Comparator.nullsFirst(Integer::compareTo))).collect(Collectors.toList());
        } else if (order.equals("price-down")) {
            goods = goods.stream().sorted(Comparator.comparing(Good::getGPrice,
                    Comparator.nullsFirst(Integer::compareTo).reversed())).collect(Collectors.toList());
        } else if (order.equals("inventory-up")) {
            goods = goods.stream().sorted(Comparator.comparing(Good::getAmount,
                    Comparator.nullsFirst(Integer::compareTo))).collect(Collectors.toList());
        } else if (order.equals("inventory-down")) {
            goods = goods.stream().sorted(Comparator.comparing(Good::getAmount,
                    Comparator.nullsFirst(Integer::compareTo).reversed())).collect(Collectors.toList());
        }
        return goods;
    }

    @Override
    public List<Good> getCollections(int uId) {
        QueryWrapper<Collect> qw = new QueryWrapper<>();
        qw.eq("u_id", uId);
        List<Collect> collects = collectMapper.selectList(qw);
        List<Good> goods = new ArrayList<>();
        for (Collect collect : collects) {
            Good good = goodMapper.selectById(collect.getGId());
            goods.add(good);
        }
        for (Good good : goods) {
            QueryWrapper<StoreGood> qw2 = new QueryWrapper<>();
            qw2.eq("g_id", good.getGId());
            StoreGood storeGood = storeGoodMapper.selectOne(qw2);
            good.setAmount(storeGood.getAmount());
        }
        userLogService.buildUserLog(uId, "normal_user", "查看收藏", "查看收藏");
        return goods;
    }

    /*@Override
    public void dropGood(int gId) {
        if (gId == -1) {
            throw new ServiceException("该商品已被删除！");
        }
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("g_id", gId);
        // 删购物车
        List<Cart> carts = cartMapper.selectList(qw);
        for (Cart cart : carts) {
            Cart newCart = new Cart();
            newCart.setUId(cart.getUId());
            newCart.setGId(-1);
            newCart.setAmount(cart.getAmount());
            UpdateWrapper<Cart> qw1 = new UpdateWrapper<>();
            qw1.eq("g_id", gId);
            qw1.eq("u_id", cart.getUId());
            cartMapper.update(newCart, qw1);
        }
        // 删收藏
        QueryWrapper<Collect> qw2 = new QueryWrapper<>();
        qw.eq("g_id", gId);
        List<Collect> collects = collectMapper.selectList(qw2);
        for (Collect collect : collects) {
            Collect newCollect = new Collect();
            newCollect.setUId(collect.getUId());
            newCollect.setGId(-1);
            UpdateWrapper<Collect> qw1 = new UpdateWrapper<>();
            qw1.eq("g_id", gId);
            qw1.eq("u_id", collect.getUId());
            collectMapper.update(newCollect, qw1);
        }
        // 删storeGood
        QueryWrapper<StoreGood> qw3 = new QueryWrapper<>();
        qw.eq("g_id", gId);
        List<StoreGood> storeGoods = storeGoodMapper.selectList(qw3);
        for (StoreGood storeGood : storeGoods) {
            StoreGood newStoreGood = new StoreGood();
            newStoreGood.setAmount(0);
            newStoreGood.setSId(storeGood.getSId());
            newStoreGood.setGId(-1);
            UpdateWrapper<StoreGood> qw1 = new UpdateWrapper<>();
            qw1.eq("g_id", gId);
            qw1.eq("s_id", storeGood.getSId());
            storeGoodMapper.update(newStoreGood, qw1);
        }
        // 删orderGood
        QueryWrapper<OrderGood> qw4 = new QueryWrapper<>();
        qw.eq("g_id", gId);
        List<OrderGood> orderGoods = orderGoodMapper.selectList(qw4);
        for (OrderGood orderGood : orderGoods) {
            OrderGood newOrderGood = new OrderGood();
            newOrderGood.setAmount(orderGood.getAmount());
            newOrderGood.setOId(orderGood.getOId());
            newOrderGood.setSId(orderGood.getSId());
            newOrderGood.setGId(-1);
            UpdateWrapper<OrderGood> qw1 = new UpdateWrapper<>();
            qw1.eq("g_id", gId);
            qw1.eq("o_id", orderGood.getOId());
            orderGoodMapper.update(newOrderGood, qw1);
        }
        // 删good
        goodMapper.deleteById(gId);
    }*/

    @Override
    public void dropGood(int gId) {
        if (gId == -1) {
            throw new ServiceException("该商品已被删除！");
        }
        QueryWrapper<Cart> qw1 = new QueryWrapper<>();
        qw1.eq("g_id", gId);
        // 删购物车
        cartMapper.delete(qw1);
        // 删收藏
        QueryWrapper<Collect> qw2 = new QueryWrapper<>();
        qw2.eq("g_id", gId);
        collectMapper.delete(qw2);
        // 删storeGood
        QueryWrapper<StoreGood> qw3 = new QueryWrapper<>();
        qw3.eq("g_id", gId);
        storeGoodMapper.delete(qw3);
        // 删orderGood
        QueryWrapper<OrderGood> qw4 = new QueryWrapper<>();
        qw4.eq("g_id", gId);
        orderGoodMapper.delete(qw4);
        // 删good
        goodMapper.deleteById(gId);
    }
}
