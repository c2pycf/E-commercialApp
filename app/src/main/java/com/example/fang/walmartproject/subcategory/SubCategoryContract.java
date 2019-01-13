package com.example.fang.walmartproject.subcategory;

import com.example.fang.walmartproject.data.SubCategoryItem;

import java.util.List;

public interface SubCategoryContract {
    interface ShopView{
        void showList(List<SubCategoryItem> itemList);

        void showProductList(String cid, String scid);
    }

    interface ShopPresenter{
        void getCategoryList();

        void onSubCategoryItemClicked(String cid,String scid);
    }
}
