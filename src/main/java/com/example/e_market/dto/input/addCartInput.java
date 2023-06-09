package com.example.e_market.dto.input;

import com.example.e_market.entity.Cart;
import lombok.Data;

@Data
public class addCartInput {
    private int u_id;
    private int g_id;
    private int num;

    public Cart buildCart() {
        Cart cart = new Cart();
        cart.setAmount(num);
        cart.setGId(g_id);
        cart.setUId(u_id);
        return cart;
    }
}
