package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cart")
public class Cart {
    private int uId;
    private int gId;
    private int Amount;
}
