package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("store_order")
public class OrderGood {
    private int oId;
    private int gId;
    private int sId;
    private int Amount;
}
