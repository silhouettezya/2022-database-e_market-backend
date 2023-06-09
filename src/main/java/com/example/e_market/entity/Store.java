package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.e_market.dto.input.ReginInput;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("store")
public class Store {
    @TableId(type = IdType.AUTO)
    private int sId;
    private String sAccount;
    private String sPwd;
    private String sName;
    private String sIntro;
    private String sTime;

    public Store(ReginInput input) {
        sAccount = input.getUsername();
        sPwd = input.getPassword();
        sName = input.getUsername();
        sIntro = " ";
        if (input.getPhone() != null) {
            sIntro = sIntro + "phone: " + input.getPhone();
        }
        if (input.getEmail() != null) {
            sIntro = sIntro + "email: " + input.getEmail();
        }
        sTime = getTime();
    }

    public Store() {

    }

    public String getTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
