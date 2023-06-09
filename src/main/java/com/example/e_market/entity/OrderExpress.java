package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_express")
public class OrderExpress {
    private int oId;
    private int eId;
}
