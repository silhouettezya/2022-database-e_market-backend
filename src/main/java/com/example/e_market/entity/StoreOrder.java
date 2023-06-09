package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("store_order_view")
public class StoreOrder {
    private int oId;
    private int sId;
    private int gId;
    private int Amount;
    private String oAddr;
    private String oState;
    private int oPrice;
    private String oTime;
    private String oRece;
    private String oDetail;
    private String gName;
    private int gPrice;
    private String gPic;
}
