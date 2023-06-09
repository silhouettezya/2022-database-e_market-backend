package com.example.e_market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.e_market.entity.*;
import com.example.e_market.mapper.*;
import com.example.e_market.service.OrderService;
import com.example.e_market.service.ex.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;

    @Resource
    UserOrderMapper userOrderMapper;

    @Resource
    OrderGoodMapper orderGoodMapper;

    @Resource
    StoreGoodMapper storeGoodMapper;

    @Resource
    GoodMapper goodMapper;

    @Resource
    ReceiveMapper receiveMapper;

    @Override
    public void buildOrder(int price, Cart cart) {
        //TODO 同一商店的创建在一个订单中
        int uId = cart.getUId();
        int gId = cart.getGId();
        int Amount = cart.getAmount();
        Good good = goodMapper.selectById(gId);

        if (gId == -1) {
            return;
        }

        Receive receive = receiveMapper.selectById(uId);
        if (receive == null) {
            throw new ServiceException("未填写收件人信息！");
        }

        // 创建新的订单
        Order order = new Order();
        order.setORece(receive.getRName());
        order.setOAddr(receive.getRAddr());
        order.setOPrice(price);
        order.setOState("未发货");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        order.setOTime(time);
        String orderDetail = String.format("%s*%d", good.getGName(), Amount);
        order.setODetail(orderDetail);
        orderMapper.insert(order);

        // 写用户订单表user_order
        int oId = order.getOId();
        UserOrder userOrder = new UserOrder();
        userOrder.setOId(oId);
        userOrder.setUId(uId);
        userOrderMapper.insert(userOrder);

        // 更改商品库存 update store_good
        QueryWrapper<StoreGood> qw = new QueryWrapper<>();
        qw.eq("g_id", gId);
        StoreGood storeGood = storeGoodMapper.selectOne(qw);
        int sId = storeGood.getSId();
        int newAmount = storeGood.getAmount() - Amount;
        storeGood.setAmount(newAmount);
        UpdateWrapper<StoreGood> uw = new UpdateWrapper<>();
        uw.eq("g_id", gId);
        storeGoodMapper.update(storeGood, uw);

        // 写店家商品订单表 insert store_order
        OrderGood orderGood = new OrderGood();
        orderGood.setOId(oId);
        orderGood.setGId(gId);
        orderGood.setSId(sId);
        orderGood.setAmount(Amount);
        orderGoodMapper.insert(orderGood);
    }

    @Override
    public void confirmOrder(int oId) {
        Order order = orderMapper.selectById(oId);
        order.setOState("已收货");
        orderMapper.updateById(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderMapper.selectList(null);
    }
}
