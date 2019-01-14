package com.example.fang.walmartproject.productCategory.productDetail;

import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.CartDataSource;
import com.example.fang.walmartproject.data.source.CartRepository;

public class ProductDetailPresenter implements ProductDetailContract.DetailPresenter {
    ProductDetailContract.DetailView mView;
    CartDataSource repository;

    public ProductDetailPresenter(ProductDetailFragment context) {
        this.mView = context;
        repository = new CartRepository(context.getActivity());
    }

    @Override
    public void onAddCartHandled(Product product) {
        //add to cart
        repository.saveCart(product);
        String message = "Success";
        mView.addCartResult(message);
    }
}
