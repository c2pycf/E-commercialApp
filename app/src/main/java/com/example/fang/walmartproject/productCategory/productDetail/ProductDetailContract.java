package com.example.fang.walmartproject.productCategory.productDetail;

public interface ProductDetailContract {
    interface DetailView{
        void addCartResult(String message);
    }

    interface DetailPresenter{
        void onAddCartHandled();
    }


}
