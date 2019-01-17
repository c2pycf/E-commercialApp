package com.example.fang.walmartproject.orderHistory;

import android.os.Bundle;

import com.example.fang.walmartproject.data.Order;
import com.example.fang.walmartproject.data.OrderList;

public interface OrderHistoryContract {
    interface OrderHistoryView {
        void showList(OrderList orderList);

        void showToast(String msg);

        void showSignInPage();

        void showTrack(Bundle b);
    }

    interface OrderHistoryPresenter{
        void getOrderHistory();

        void onSignInClicked();

        void track(Order order);
    }
}
