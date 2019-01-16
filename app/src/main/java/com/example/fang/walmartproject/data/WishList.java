package com.example.fang.walmartproject.data;

import java.util.ArrayList;
import java.util.List;

public class WishList {
    List<Product> mCart;
    String userId;

    public WishList() {
        this.mCart = new ArrayList<>();
    }

    public List<Product> getmCart() {
        return mCart;
    }

    public void addProduct(Product product){
        mCart.add(product);
    }

    public Product getProduct(int i){
        return mCart.get(i);
    }

    public int getCartSize(){
        return mCart.size();
    }


}
