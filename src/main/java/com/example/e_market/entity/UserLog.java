package com.example.e_market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_log")
public class UserLog {
    @TableId(type = IdType.AUTO)
    private int id;
    private int userId;
    private String identity;
    private String logType;
    private String logDate;
    private String logValue;

    public UserLog(int id, String identity, String logType, String logDate, String logValue) {
        userId = id;
        this.identity = identity;
        this.logType = logType;
        this.logDate = logDate;
        this.logValue = logValue;
    }
}
