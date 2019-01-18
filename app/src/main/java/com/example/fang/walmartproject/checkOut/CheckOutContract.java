package com.example.fang.walmartproject.checkOut;

import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.OrderList;

public interface CheckOutContract {
    interface CheckOutView{
        void showRecycleView(Cart cart);

        void showToast(String msg);

        void showOrderComfirmation(OrderList orderList);

        void showDialog();
    }

    interface CheckOutPresenter{
        void getCart();

        void checkCoupon(String coupon);

        void checkPayment(String address,String bill,String cardNum,String monthInt,String yearInt,String cvc);

        void getUserInfor();
    }



}
