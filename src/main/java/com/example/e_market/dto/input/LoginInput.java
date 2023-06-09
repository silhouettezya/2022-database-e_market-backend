package com.example.e_market.dto.input;

import lombok.Data;

@Data
public class LoginInput {
    private String username;
    private String password;
    String identity;
}
