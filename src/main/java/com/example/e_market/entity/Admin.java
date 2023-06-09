package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {
    private int id;
    private String username;
    private String password;
}
