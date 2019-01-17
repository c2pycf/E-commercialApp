package com.example.fang.walmartproject.trackingOrder;

public interface TrackingOrderContract {

    interface TrackingOrderView{
        void setText(String shipId,String status);
    }

    interface TrackingOrderPresenter{

        void startTrack(String api,String uid,String orderId);
    }
}
