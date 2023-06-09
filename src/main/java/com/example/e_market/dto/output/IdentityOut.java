package com.example.e_market.dto.output;

import com.example.e_market.service.ex.ServiceException;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.ArrayList;
import java.util.List;

@Data
public class IdentityOut {
    private String avatar;
    private String introduction;
    private String name;
    private List<String> roles = new ArrayList<>();

    public IdentityOut(String token) {
        if (token.equals("user-token")) {
            roles.add("normal_user");
            introduction = "I am a normal user";
            avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
            name = "normal_user";
        } else if (token.equals("seller-token")) {
            roles.add("seller");
            introduction = "I am an seller.js";
            avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
            name = "seller";
        } else if (token.equals("admin-token")) {
            roles.add("admin");
            introduction = "I am a super administrator";
            avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
            name = "Super Admin";
        } else if(token.equals("delivery-token")) {
            roles.add("delivery");
            introduction = "I am a delivery man";
            avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
            name = "delivery";
        } else {
            throw new ServiceException("非法角色登录！");
        }
    }
}
