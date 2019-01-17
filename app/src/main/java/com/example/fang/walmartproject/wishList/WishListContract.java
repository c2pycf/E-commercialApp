package com.example.fang.walmartproject.wishList;

import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.WishList;

public interface WishListContract {
    interface WishListView {
        void showWishList(WishList wishList);

        void showToast(String removed);
    }

    interface WishListPresenter{
        void getWishList();

        void deleteProduct(String id);

        void updateProduct(Product product);

        void addToCart(Product product);
        
    }

}
