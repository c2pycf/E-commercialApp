package com.example.fang.walmartproject.cart;

import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.source.CartDataSource;
import com.example.fang.walmartproject.data.source.CartRepository;

public class ShoppingCartPresenter implements ShoppingCartContracter.CartPresenter {
    ShoppingCartContracter.CartView mView;
    AppController volley;
    CartDataSource repository;

    public ShoppingCartPresenter(ShoppingCartActivity activity) {
        this.mView = activity;
        this.volley = AppController.getInstance();
        repository = new CartRepository(activity.getBaseContext());
    }

    @Override
    public void getCartData() {
        Cart cart = repository.getCarts();
        int totalPrise = cart.getTotalPrize();
        mView.showRecyvleView(cart, totalPrise);

    }
}
