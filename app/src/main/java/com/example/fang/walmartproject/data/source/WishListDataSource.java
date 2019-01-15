package com.example.fang.walmartproject.data.source;

import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.WishList;

public interface WishListDataSource {
    void saveLater(Product product);

    WishList getList();

    void clearList();

    void deleteProduct(String id);

    void update(Product product);
}
