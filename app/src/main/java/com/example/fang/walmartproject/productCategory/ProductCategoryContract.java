package com.example.fang.walmartproject.productCategory;

import com.example.fang.walmartproject.data.Product;

import java.util.List;

public interface ProductCategoryContract {

    interface ProductListView{
        void showProductList(List<Product> itemList);

        void showProductDetail(Product product);
    }

    interface ProductListPresenter{
        void getProducts(String cid, String scid);

        void onProductClicked(Product product);
    }


}
