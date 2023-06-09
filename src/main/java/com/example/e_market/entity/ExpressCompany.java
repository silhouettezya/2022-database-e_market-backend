package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("express_company")
public class ExpressCompany {
    private int eId;
    private String eName;
    private String ePwd;
    private String eEmail;
    private String eAccount;
    private String ePhone;
}
