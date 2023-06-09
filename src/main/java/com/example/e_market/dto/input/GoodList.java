package com.example.e_market.dto.input;

import com.example.e_market.entity.Good;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GoodList {
    private int id;
    private List<GoodListItem> items;

    public List<Good> buildGoods() {
        List<Good> goods = new ArrayList<>();
        for (GoodListItem item : items) {
            goods.add(item.buildGood());
        }
        return goods;
    }
}
