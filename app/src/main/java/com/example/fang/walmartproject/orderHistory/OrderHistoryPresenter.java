package com.example.fang.walmartproject.orderHistory;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Order;
import com.example.fang.walmartproject.data.OrderList;
import com.example.fang.walmartproject.data.UserImformation;
import com.example.fang.walmartproject.data.source.UserDataSource;
import com.example.fang.walmartproject.data.source.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderHistoryPresenter implements OrderHistoryContract.OrderHistoryPresenter {
    AppController volley;
    OrderHistoryContract.OrderHistoryView mView;
    UserDataSource userDataSource;
    static private final String TAG = OrderHistoryPresenter.class.getSimpleName();

    public OrderHistoryPresenter(OrderHistoryFragment fragment) {
        this.volley = AppController.getInstance();
        this.mView = fragment;
        userDataSource = new UserRepository(fragment.getContext());
    }

    @Override
    public void getOrderHistory() {
        UserImformation user = userDataSource.getUser();
        String api = user.getUserAppApiKey();
        String id = user.getUserId();
        String mobile = user.getMobile();

        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/order_history.php?api_key="
        +api+"&user_id="+id+"&mobile="+mobile;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    OrderList orderList = new OrderList();
                    Log.d(TAG,"response" + response);
                    JSONObject orderConfirm = new JSONObject(response);
                    JSONArray orderArray = orderConfirm.getJSONArray("Order history");
                    for (int j=0;j<orderArray.length();j++){
                        JSONObject orderJSON = orderArray.getJSONObject(j);
                        String orderId = orderJSON.getString("orderid");
                        String orderStatus = orderJSON.getString("orderstatus");
                        String name = orderJSON.getString("name");
                        String bill = orderJSON.getString("billingadd");
                        String address = orderJSON.getString("deliveryadd");
                        String mobile = orderJSON.getString("mobile");
                        String email = orderJSON.getString("email");
                        String paidprice = orderJSON.getString("paidprice");
                        float paid =Float.parseFloat(paidprice);
                        String date = orderJSON.getString("placedon");

                        Order newOrder = new Order(orderId,
                                orderStatus,name,bill,address,mobile,email,paid,date,new Cart());
                        orderList.addOrder(newOrder);

                    }
                    mView.showList(orderList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mView.showToast(error.getMessage());
            }
        });

        volley.addToRequestQueue(stringRequest,"order_history");
    }

    @Override
    public void onSignInClicked() {
        mView.showSignInPage();
    }

    @Override
    public void track(Order order) {
        UserImformation user = userDataSource.getUser();
        Bundle b = new Bundle();
        b.putString("api",user.getUserAppApiKey());
        b.putString("uid",user.getUserId());
        b.putString("order_id",order.getOrderId());

        mView.showTrack(b);
    }
}
