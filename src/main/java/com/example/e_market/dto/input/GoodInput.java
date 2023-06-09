package com.example.e_market.dto.input;

import com.example.e_market.entity.Good;
import lombok.Data;

@Data
public class GoodInput {
    private int id;
    private String ComName;
    private String ComDescription;
    private String pic_src;
    private int price;
    private int inventory;

    public Good buildGood() {
        Good good = new Good();
        good.setAmount(this.getInventory());
        good.setGPrice(this.getPrice());
        good.setGDes(this.getComDescription());
        good.setGName(this.getComName());
        good.setGPic(this.getPic_src());
        return good;
    }
}
