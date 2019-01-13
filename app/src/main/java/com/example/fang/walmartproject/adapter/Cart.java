package com.example.fang.walmartproject.adapter;

import com.example.fang.walmartproject.data.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<Product> mCart;
    int totalPrize;

    public Cart() {
        this.mCart = new ArrayList<>();
        this.totalPrize = 0;
    }

    public List<Product> getmCart() {
        return mCart;
    }
    
    public void addProduct(Product product){
        mCart.add(product);
        totalPrize = totalPrize + Integer.parseInt(product.getPrize());
    }

    public void setmCart(List<Product> mCart) {
        this.mCart = mCart;
    }

    public int getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(int totalPrize) {
        this.totalPrize = totalPrize;
    }
}
