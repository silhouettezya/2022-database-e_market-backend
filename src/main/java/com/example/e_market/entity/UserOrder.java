package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_order")
public class UserOrder {
    private int uId;
    private int oId;
}
