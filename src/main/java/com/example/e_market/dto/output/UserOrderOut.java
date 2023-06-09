package com.example.e_market.dto.output;

import com.example.e_market.entity.Order;
import com.example.e_market.entity.StoreOrder;
import lombok.Data;

@Data
public class UserOrderOut {
    private int id;
    private String ComDetail;
    private String receiver;
    private String display_time;
    private int payment;
    private String status;

    public UserOrderOut(Order order) {
        id  = order.getOId();
        ComDetail = order.getODetail();
        receiver = order.getORece();
        display_time = order.getOTime();
        payment = order.getOPrice();
        status = order.getOState();
    }

    public UserOrderOut(StoreOrder storeOrder) {
        id = storeOrder.getOId();
        ComDetail = storeOrder.getODetail();
        receiver = storeOrder.getORece();
        display_time = storeOrder.getOTime();
        payment = storeOrder.getOPrice();
        status = storeOrder.getOState();
    }
}
