package com.example.e_market.dto.input;

import lombok.Data;

@Data
public class ReginInput {
    private String username;
    private String password;
    private String email;
    private String phone;
    private int gender;
    private String identity;
}
