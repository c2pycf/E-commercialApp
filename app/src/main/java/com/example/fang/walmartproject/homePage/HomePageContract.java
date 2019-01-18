package com.example.fang.walmartproject.homePage;

import com.example.fang.walmartproject.data.CategoryItem;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.TopSeller;

import java.util.List;

public interface HomePageContract {
    interface HomeView{
        void showSignInPage();

        void showProfile();

        void showShopList();

        void showCart();

        void signOut();

        void showWishList();

        void showOrderHistory();


    }

    interface HomePresenter{
        void onSignInHandled();

        void onProfileHandled();

        void onShopHandled();

        void onOrderHandled();

        void onCartOpen();

        void onSignOutHandled();

        void onWishListOpen();


    }

    interface ShopView{
        void showList(List<CategoryItem> itemList);

        void showSubCategory(String cid);

        void setTopSells(List<TopSeller> sellers);
    }

    interface ShopPresenter{
       void getCategoryList();

       void onItemClickHandled(String cid);

       void getTopSells();

        void setCetagoryList(List<CategoryItem> itemList);
    }

}
