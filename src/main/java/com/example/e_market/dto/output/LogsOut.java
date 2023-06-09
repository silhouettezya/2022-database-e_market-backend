package com.example.e_market.dto.output;

import com.example.e_market.entity.UserLog;
import lombok.Data;

@Data
public class LogsOut {
    private int user_id;
    private String identity;
    private String log_type;
    private String log_date;
    private String log_value;

    public LogsOut(UserLog userLog) {
        user_id = userLog.getUserId();
        identity = userLog.getIdentity();
        log_type = userLog.getLogType();
        log_date = userLog.getLogDate();
        log_value = userLog.getLogValue();
    }
}
