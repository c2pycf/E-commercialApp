package com.example.fang.walmartproject.wishList;

import com.example.fang.walmartproject.data.WishList;

public interface WishListContract {
    interface WishListView {
        void showWishList(WishList wishList);
    }

    interface WishListPresenter{
        void getWishList();
    }

}
