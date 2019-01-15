package com.example.fang.walmartproject.data.source;

import android.content.Context;

import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.WishList;
import com.example.fang.walmartproject.data.source.local.WishListDb.WishListLocalDataSource;

public class WishListRepository implements WishListDataSource {
    WishListLocalDataSource localDataSource;

    public WishListRepository(Context context) {
        this.localDataSource = new WishListLocalDataSource(context);
    }

    @Override
    public void saveLater(Product product) {
        localDataSource.saveLater(product);

    }

    @Override
    public WishList getList() {
        return localDataSource.getList();
    }

    @Override
    public void clearList() {

    }

    @Override
    public void deleteProduct(String id) {
        localDataSource.deleteProduct(id);
    }

    @Override
    public void update(Product product) {
        localDataSource.update(product);
    }
}
