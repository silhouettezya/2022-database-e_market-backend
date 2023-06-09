package com.example.e_market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.e_market.entity.*;
import com.example.e_market.mapper.ExpressCompanyMapper;
import com.example.e_market.mapper.OrderExpressMapper;
import com.example.e_market.mapper.OrderMapper;
import com.example.e_market.service.ExpressCompanyService;
import com.example.e_market.service.UserLogService;
import com.example.e_market.service.ex.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpressCompanyServiceImpl implements ExpressCompanyService {
    @Resource
    ExpressCompanyMapper expressCompanyMapper;

    @Resource
    OrderExpressMapper orderExpressMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    UserLogService userLogService;

    @Override
    public ExpressCompany login(String account, String pwd) {
        LambdaQueryWrapper<ExpressCompany> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExpressCompany::getEAccount, account);
        ExpressCompany result = expressCompanyMapper.selectOne(queryWrapper);
        if (result == null) {
            throw new ServiceException("该用户不存在！");
        }
        if (!pwd.equals(result.getEPwd())) {
            throw new ServiceException("密码错误！");
        }
        userLogService.buildUserLog(result.getEId(), "delivery", "登录", "登录成功！");
        return result;
    }

    @Override
    public List<Order> getAllOrder(int eId) {
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("o_state", "已发货");
        List<Order> orders = orderMapper.selectList(qw);
        QueryWrapper<OrderExpress> qw1 = new QueryWrapper<>();
        qw1.eq("e_id", eId);
        List<OrderExpress> orderExpresses = orderExpressMapper.selectList(qw1);
        for (OrderExpress orderExpress : orderExpresses) {
            orders.add(orderMapper.selectById(orderExpress.getOId()));
        }
        return orders;
    }

    @Override
    public void recvOrder(int eId, int oId) {
        OrderExpress orderExpress = new OrderExpress();
        orderExpress.setEId(eId);
        orderExpress.setOId(oId);
        orderExpressMapper.insert(orderExpress);
        Order order = orderMapper.selectById(oId);
        if (!order.getOState().equals("已发货")) {
            throw new ServiceException("该订单不可被揽收！");
        }
        order.setOState("已揽收");
        orderMapper.updateById(order);
        userLogService.buildUserLog(eId, "delivery", "揽收订单", "已揽收订单，订单号为" + oId);
    }
}
