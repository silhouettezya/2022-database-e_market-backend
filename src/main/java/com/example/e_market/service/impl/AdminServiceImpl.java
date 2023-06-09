package com.example.e_market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.e_market.entity.Admin;
import com.example.e_market.entity.ExpressCompany;
import com.example.e_market.entity.UserLog;
import com.example.e_market.mapper.AdminMapper;
import com.example.e_market.mapper.UserLogMapper;
import com.example.e_market.service.AdminService;
import com.example.e_market.service.UserLogService;
import com.example.e_market.service.ex.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;

    @Resource
    UserLogService userLogService;

    @Resource
    UserLogMapper userLogMapper;


    @Override
    public Admin login(String account, String pwd) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, account);
        Admin result = adminMapper.selectOne(queryWrapper);
        if (result == null) {
            throw new ServiceException("该用户不存在！");
        }
        if (!pwd.equals(result.getPassword())) {
            throw new ServiceException("密码错误！");
        }
        userLogService.buildUserLog(result.getId(), "Admin", "登录", "登录成功");
        return result;
    }

    @Override
    public List<UserLog> getUserLogs() {
        return userLogMapper.selectList(null);
    }
}
