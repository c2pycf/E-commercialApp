package com.example.fang.walmartproject.data.source;

import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;

import java.util.List;

public interface CartDataSource {
    void saveCart(Product product);

    Cart getCarts();

    void clearCart();

    void deleteProduct(String id);

    void update(Product product);
}
