package com.example.e_market.dto.output;

import com.example.e_market.entity.Order;
import com.example.e_market.entity.StoreOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserOrdersOut {
    List<UserOrderOut> orders;

    public UserOrdersOut(List<Order> orderList, String user) {
        orders = new ArrayList<>();
        for (Order order : orderList) {
            UserOrderOut orderOut = new UserOrderOut(order);
            orders.add(orderOut);
        }
    }

    public UserOrdersOut(List<StoreOrder> orderList) {
        orders = new ArrayList<>();
        for (StoreOrder order : orderList) {
            UserOrderOut orderOut = new UserOrderOut(order);
            orders.add(orderOut);
        }
    }
}
