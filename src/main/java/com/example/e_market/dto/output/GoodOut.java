package com.example.e_market.dto.output;

import com.example.e_market.entity.Good;
import lombok.Data;

@Data
public class GoodOut {
    private int id;
    private String ComName;
    private String ComDescription;
    private String pic_src;
    private int price;
    private int inventory;

    public GoodOut(Good good) {
        ComDescription = good.getGDes();
        ComName = good.getGName();
        id = good.getGId();
        inventory = good.getAmount();
        pic_src = good.getGPic();
        price = good.getGPrice();
    }
}
