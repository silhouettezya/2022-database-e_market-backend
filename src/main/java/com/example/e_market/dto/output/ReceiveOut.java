package com.example.e_market.dto.output;

import com.example.e_market.entity.Receive;
import lombok.Data;

@Data
public class ReceiveOut {
    private int id;
    private String recv_name;
    private String recv_tel;
    private String recv_province;
    private String recv_addr;
    private String note;

    public ReceiveOut(Receive receive) {
        id = receive.getId();
        recv_name = receive.getRName();
        recv_tel = receive.getRTel();
        recv_province = receive.getRProvince();
        recv_addr = receive.getRAddr();
        note = receive.getNote();
    }
}
