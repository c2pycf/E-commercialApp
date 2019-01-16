package com.example.fang.walmartproject.wishList;

import com.example.fang.walmartproject.data.WishList;
import com.example.fang.walmartproject.data.source.CartDataSource;
import com.example.fang.walmartproject.data.source.CartRepository;
import com.example.fang.walmartproject.data.source.WishListDataSource;
import com.example.fang.walmartproject.data.source.WishListRepository;

public class WishListPresenter implements WishListContract.WishListPresenter {
    WishListContract.WishListView mView;
    CartDataSource cartDataSource;
    WishListDataSource wishListDataSource;

    public WishListPresenter(WishListFragment fragment) {
        this.mView = fragment;
        cartDataSource = new CartRepository(fragment.getContext());
        wishListDataSource =  new WishListRepository(fragment.getContext());
    }


    @Override
    public void getWishList() {
        WishList wishList =wishListDataSource.getList();
        mView.showWishList(wishList);
    }
}
