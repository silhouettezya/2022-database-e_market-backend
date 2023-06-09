package com.example.e_market.service.impl;

import com.example.e_market.entity.UserLog;
import com.example.e_market.mapper.UserLogMapper;
import com.example.e_market.service.UserLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Resource
    UserLogMapper userLogMapper;

    public String getTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        return time;
    }

    @Override
    public void buildUserLog(int id, String identity, String logType, String logValue) {
        String time = getTime();
        UserLog userLog = new UserLog(id, identity, logType, time, logValue);
        userLogMapper.insert(userLog);
    }


}
