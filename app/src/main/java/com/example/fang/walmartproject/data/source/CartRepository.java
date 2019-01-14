package com.example.fang.walmartproject.data.source;

import android.content.Context;

import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.local.CartLocalDataSource;

import java.util.List;

public class CartRepository implements CartDataSource {
    CartLocalDataSource localDataSource;


    public CartRepository(Context context) {
        this.localDataSource = new CartLocalDataSource(context);
    }

    @Override
    public void saveCart(Product product) {
        localDataSource.saveCart(product);
    }

    @Override
    public List<Product> getCarts() {
        return null;
    }

    @Override
    public void clearCart() {

    }

    @Override
    public void deleteProduct(String id) {

    }
}
