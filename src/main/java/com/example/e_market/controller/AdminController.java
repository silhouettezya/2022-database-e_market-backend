package com.example.e_market.controller;

import com.example.e_market.dto.output.LogListOut;
import com.example.e_market.dto.output.UserOrdersOut;
import com.example.e_market.entity.Order;
import com.example.e_market.service.AdminService;
import com.example.e_market.service.OrderService;
import com.example.e_market.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Resource
    OrderService orderService;

    @Resource
    AdminService adminService;

    @GetMapping("get_orders")
    public JsonResult<UserOrdersOut> getOrders(int id) {
        List<Order> orders = orderService.getOrders();
        return new JsonResult<UserOrdersOut>(new UserOrdersOut(orders, "user"));
    }

    @GetMapping("get_logs")
    public JsonResult<LogListOut> getLogs() {
        return new JsonResult<>(new LogListOut(adminService.getUserLogs()));
    }
}
