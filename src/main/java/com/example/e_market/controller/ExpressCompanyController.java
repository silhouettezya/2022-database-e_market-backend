package com.example.e_market.controller;

import com.example.e_market.dto.input.ExpressGetOrder;
import com.example.e_market.dto.input.IdInput;
import com.example.e_market.dto.output.UserOrdersOut;
import com.example.e_market.entity.Order;
import com.example.e_market.service.ExpressCompanyService;
import com.example.e_market.service.OrderService;
import com.example.e_market.service.StoreOrderService;
import com.example.e_market.service.UserLogService;
import com.example.e_market.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("deliver")
public class ExpressCompanyController extends BaseController{
    @Resource
    ExpressCompanyService expressCompanyService;

    @Resource
    StoreOrderService storeOrderService;

    @Resource
    OrderService orderService;

    @Resource
    UserLogService userLogService;

    @GetMapping("get_orders")
    public JsonResult<UserOrdersOut> getOrders(int id) {
        List<Order> orders = expressCompanyService.getAllOrder(id);
        return new JsonResult<UserOrdersOut>(new UserOrdersOut(orders, "user"));
    }

    @PostMapping("recv_order")
    public JsonResult<Void> recvOrder(@RequestBody ExpressGetOrder input) {
        int oId = input.getO_id();
        int eId = input.getD_id();
        expressCompanyService.recvOrder(eId, oId);
        return new JsonResult<>();
    }

    @PostMapping("sent_order")
    public JsonResult<Void> sentOrder(@RequestBody IdInput input) {
        int oId = input.getId();
        storeOrderService.setState(oId, "已送达");
        return new JsonResult<>();
    }


    /*@PostMapping("updateOrderState")
    public void setOrderState(int oId, String oState) {
        storeOrderService.setState(oId, oState);
    }*/
}
