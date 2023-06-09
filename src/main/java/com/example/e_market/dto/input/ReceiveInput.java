package com.example.e_market.dto.input;

import com.example.e_market.entity.Receive;
import lombok.Data;

@Data
public class ReceiveInput {
    private int id;
    private String recv_name;
    private String recv_tel;
    private String recv_province;
    private String recv_addr;
    private String note;
}
