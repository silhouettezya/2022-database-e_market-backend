package com.example.e_market.dto.output;

import com.example.e_market.entity.UserLog;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LogListOut {
    private List<LogsOut> log;

    public LogListOut(List<UserLog> logList) {
        log = new ArrayList<>();
        for (UserLog userLog : logList) {
            log.add(new LogsOut(userLog));
        }
    }
}
