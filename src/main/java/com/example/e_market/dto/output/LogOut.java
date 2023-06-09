package com.example.e_market.dto.output;

import lombok.Data;

@Data
public class LogOut {
    private String status;

    public LogOut() {
        status = "已成功退出登录！";
    }
}
