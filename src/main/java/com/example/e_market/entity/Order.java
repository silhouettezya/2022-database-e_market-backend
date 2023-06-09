package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`order`")
public class Order {
    @TableId(type = IdType.AUTO)
    private int oId;
    private String oAddr;
    private String oState;
    private int oPrice;
    private String oTime;
    private String oDetail;
    private String oRece;


}
