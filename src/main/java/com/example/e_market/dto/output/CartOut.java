package com.example.e_market.dto.output;

import com.example.e_market.entity.Cart;
import com.example.e_market.entity.Good;
import lombok.Data;

@Data
public class CartOut {
    private String ComDescription;
    private String ComName;
    private int id;
    private int num;
    private String pic_src;
    private int price;

    public CartOut(Good good) {
        ComDescription = good.getGDes();
        ComName = good.getGName();
        id = good.getGId();
        num = good.getAmount();
        pic_src = good.getGPic();
        price = good.getGPrice();
    }
}
