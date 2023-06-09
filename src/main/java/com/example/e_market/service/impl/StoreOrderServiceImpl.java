package com.example.e_market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.e_market.entity.Order;
import com.example.e_market.entity.StoreGood;
import com.example.e_market.entity.StoreOrder;
import com.example.e_market.mapper.OrderMapper;
import com.example.e_market.mapper.StoreOrderMapper;
import com.example.e_market.mapper.UserMapper;
import com.example.e_market.service.StoreOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreOrderServiceImpl implements StoreOrderService {
    @Resource
    private StoreOrderMapper storeOrderMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<StoreOrder> getAllOrder(int sId) {
        LambdaQueryWrapper<StoreOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StoreOrder::getSId, sId);
        return storeOrderMapper.selectList(wrapper);
    }

    @Override
    public StoreOrder getOrder(int oId) {
        LambdaQueryWrapper<StoreOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StoreOrder::getOId, oId);
        return storeOrderMapper.selectOne(wrapper);
    }

    @Override
    public void setState(int oId, String oState) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("o_id", oId).set("o_state", oState);
        orderMapper.update(null, updateWrapper);
    }


}
