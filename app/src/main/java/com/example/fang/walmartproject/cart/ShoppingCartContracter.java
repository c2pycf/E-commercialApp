package com.example.fang.walmartproject.cart;

import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;

public interface ShoppingCartContracter {
    interface CartView{
        void showRecyvleView(Cart cart, float totalPrise);

        void showCheckOut();
    }

    interface CartPresenter{
        void getCartData();

        void deleteProduct(String id);

        void updateProduct(Product product);

        void saveLater(Product product);

        void processToCheckOut();
    }
}
