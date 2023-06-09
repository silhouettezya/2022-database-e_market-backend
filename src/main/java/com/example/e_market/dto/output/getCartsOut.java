package com.example.e_market.dto.output;

import com.example.e_market.entity.Good;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class getCartsOut {
    List<CartOut> items;

    public getCartsOut(List<Good>goods) {
        items = new ArrayList<>();
        for (Good good : goods) {
            items.add(new CartOut(good));
        }
    }
}
