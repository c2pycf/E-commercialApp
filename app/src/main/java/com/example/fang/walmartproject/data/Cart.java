package com.example.fang.walmartproject.data;

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

    public void addProduct(Product product,int amount){
        if(amount<=Integer.parseInt(product.getQuantity())){
            product.setUserAmount(amount);
            mCart.add(product);
            totalPrize = totalPrize + (Integer.parseInt(product.getPrize()))*amount;
        }
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
