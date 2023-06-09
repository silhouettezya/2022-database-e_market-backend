package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("good")
public class Good {
    @TableId(type = IdType.AUTO)
    private int gId;
    private String gName;
    private int gPrice;
    private String gPic;
    private String gDes;

    @TableField(exist = false)
    private int Amount;
}
