package com.example.fang.walmartproject.subcategory;

import com.example.fang.walmartproject.data.SubCategoryItem;
import com.example.fang.walmartproject.data.source.TopSeller;

import java.util.List;

public interface SubCategoryContract {
    interface ShopView{
        void showList(List<SubCategoryItem> itemList);

        void showProductList(String cid, String scid);

        void setTopSells(List<TopSeller> sellerList);
    }

    interface ShopPresenter{
        void getCategoryList();

        void onSubCategoryItemClicked(String cid,String scid);

        void getTopSells();
    }
}
