package com.example.fang.walmartproject.productCategory.productDetail;

public class ProductDetailPresenter implements ProductDetailContract.DetailPresenter {
    ProductDetailContract.DetailView mView;

    public ProductDetailPresenter(ProductDetailFragment context) {
        this.mView = context;
    }

    @Override
    public void onAddCartHandled() {
        //add to cart

        String message = "Success";
        mView.addCartResult(message);
    }
}
