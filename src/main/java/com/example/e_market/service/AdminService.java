package com.example.e_market.service;

import com.example.e_market.entity.Admin;
import com.example.e_market.entity.UserLog;

import java.util.List;

public interface AdminService {
    Admin login(String account, String pwd);

    List<UserLog> getUserLogs();
}
