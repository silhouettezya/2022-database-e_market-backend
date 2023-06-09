package com.example.e_market.dto.output;

import com.example.e_market.entity.Good;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GoodListOut {
    List<GoodOut> item;

    public GoodListOut(List<Good>goods) {
        item = new ArrayList<>();
        for (Good good : goods) {
            item.add(new GoodOut(good));
        }
    }
}
