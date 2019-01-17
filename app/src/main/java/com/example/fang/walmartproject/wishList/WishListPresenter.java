package com.example.fang.walmartproject.wishList;

import com.example.fang.walmartproject.data.Product;
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

    @Override
    public void deleteProduct(String id) {
        wishListDataSource.deleteProduct(id);
        WishList wishList =wishListDataSource.getList();
        mView.showWishList(wishList);
        mView.showToast("Removed");
    }

    @Override
    public void updateProduct(Product product) {
        wishListDataSource.update(product);
        WishList wishList =wishListDataSource.getList();
        mView.showWishList(wishList);
        mView.showToast("Updated");

    }

    @Override
    public void addToCart(Product product) {
        wishListDataSource.deleteProduct(product.getId());
        cartDataSource.saveCart(product);
        WishList wishList =wishListDataSource.getList();
        mView.showWishList(wishList);
        mView.showToast("Added to cart");

    }
}
