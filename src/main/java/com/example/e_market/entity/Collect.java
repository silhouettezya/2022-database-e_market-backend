package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("collect")
public class Collect {
    private int uId;
    private int gId;
}
