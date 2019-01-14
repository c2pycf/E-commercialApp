package com.example.fang.walmartproject.homePage;

import com.example.fang.walmartproject.data.CategoryItem;

import java.util.List;

public interface HomePageContract {
    interface HomeView{
        void showSignInPage();

        void showProfile();

        void showShopList();

        void showCart();

    }

    interface HomePresenter{
        void onSignInHandled();

        void onProfileHandled();

        void onShopHandled();

        void onOrderHandled();

        void onCartOpen();

    }

    interface ShopView{
        void showList(List<CategoryItem> itemList);

        void showSubCategory(String cid);
    }

    interface ShopPresenter{
       void getCategoryList();

       void onItemClickHandled(String cid);
    }

}
