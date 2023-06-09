package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("receive")
public class Receive {
    @TableId
    private int id;
    private String rName;
    private String rTel;
    private String rProvince;
    private String rAddr;
    private String note;
}
