package com.example.fang.walmartproject.homePage;

import com.android.volley.RequestQueue;
import com.example.fang.walmartproject.AppController;

public class HomePagePresenter implements HomePageContract.HomePresenter {
    HomePageContract.HomeView mView;
    RequestQueue requestQueue;

    public HomePagePresenter(HomePageActivity activity) {
        this.mView = activity;
        requestQueue = AppController.getInstance().getRequestQueue();
    }

    @Override
    public void onSignInHandled() {
         mView.showSignInPage();
    }

    @Override
    public void onProfileHandled() {
        mView.showProfile();

    }

    @Override
    public void onShopHandled() {
        mView.showShopList();
    }

    @Override
    public void onOrderHandled() {

    }

    @Override
    public void onCartOpen() {
        mView.showCart();
    }
}
