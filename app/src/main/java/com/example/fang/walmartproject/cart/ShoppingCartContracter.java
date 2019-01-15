package com.example.fang.walmartproject.cart;

import com.example.fang.walmartproject.data.Cart;

public interface ShoppingCartContracter {
    interface CartView{
        void showRecyvleView(Cart cart, int totalPrise);
    }

    interface CartPresenter{
        void getCartData();
    }
}
