package com.example.fang.walmartproject.productCategory.productDetail;

import com.example.fang.walmartproject.data.Product;

public interface ProductDetailContract {
    interface DetailView{
        void addCartResult(String message);
    }

    interface DetailPresenter{
        void onAddCartHandled(Product product);
    }


}
