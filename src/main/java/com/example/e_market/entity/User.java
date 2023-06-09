package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.e_market.dto.input.ReginInput;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private int uId;
    private String uAccount;
    private String uName;
    private String uPwd;
    private String uEmail;
    private String uPhone;
    private int uGender;

    public User(ReginInput input) {
        uAccount = input.getUsername();
        uName = uAccount;
        uPwd = input.getPassword();
        uEmail = input.getEmail();
        uPhone = input.getPhone();
        uGender = input.getGender();
    }

    public User() {
    }
}
