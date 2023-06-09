package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("store_good")
public class StoreGood {
    private int sId;
    private int gId;
    private int Amount;
}
