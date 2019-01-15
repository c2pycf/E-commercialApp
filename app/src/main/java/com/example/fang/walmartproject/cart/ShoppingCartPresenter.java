package com.example.fang.walmartproject.cart;

import android.util.Log;

import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.CartDataSource;
import com.example.fang.walmartproject.data.source.CartRepository;

public class ShoppingCartPresenter implements ShoppingCartContracter.CartPresenter {
    ShoppingCartContracter.CartView mView;
    AppController volley;
    CartDataSource repository;

    static final private String TAG = ShoppingCartPresenter.class.getSimpleName();

    public ShoppingCartPresenter(ShoppingCartActivity activity) {
        this.mView = activity;
        this.volley = AppController.getInstance();
        repository = new CartRepository(activity.getBaseContext());
    }

    @Override
    public void getCartData() {
        Cart cart = repository.getCarts();
        int totalPrise = cart.getTotalPrize();
        Log.d(TAG,"size "+ cart.getCartSize());

        mView.showRecyvleView(cart, totalPrise);

    }

    @Override
    public void deleteProduct(String id) {
        repository.deleteProduct(id);
        getCartData();
    }

    @Override
    public void updateProduct(Product product) {
        repository.update(product);
        getCartData();
    }

    @Override
    public void saveLater(Product product) {

    }
}
