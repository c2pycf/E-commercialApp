package com.example.fang.walmartproject.checkOut;

import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Order;
import com.example.fang.walmartproject.data.OrderList;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.UserImformation;
import com.example.fang.walmartproject.data.source.CartDataSource;
import com.example.fang.walmartproject.data.source.CartRepository;
import com.example.fang.walmartproject.data.source.UserDataSource;
import com.example.fang.walmartproject.data.source.UserRepository;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Month;


public class CheckOutPresenter implements CheckOutContract.CheckOutPresenter {
    CheckOutContract.CheckOutView mView;
    CartDataSource cartRepository;
    AppController volley;
    UserDataSource userRepository;
    Stripe stripe;
    float discuontRate = (float) 1.00;
    static final String SIMPLIFY_API = "pk_test_Bb1D97K4vdVgMi3wmDkH3C8h";

    static final String TAG = CheckOutPresenter.class.getSimpleName();

    public CheckOutPresenter(CheckOutActivity activity) {
        this.mView = activity;
        cartRepository = new CartRepository(activity.getBaseContext());
        volley = AppController.getInstance();
        userRepository =  new UserRepository(activity.getBaseContext());
        stripe = new Stripe(activity,SIMPLIFY_API);

    }

    @Override
    public void getCart() {
        Cart cart = cartRepository.getCarts();
        mView.showRecycleView(cart);

    }

    @Override
    public void checkCoupon(final String coupon) {
        UserImformation user = userRepository.getUser();
        String api = user.getUserAppApiKey();
        String id = user.getUserId();
        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/coupon.php?api_key="
                + api+"&user_id="+id+"&couponno="+coupon;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject couponRes = new JSONObject(response);
                    JSONArray couponDiscountArray = couponRes.getJSONArray("Coupon discount");
                    for(int i=0;i<couponDiscountArray.length();i++){
                        boolean findFLG = false;
                        JSONObject counponItem = couponDiscountArray.getJSONObject(i);
                        String couponResult = counponItem.getString("couponno");
                        if(coupon.equals(couponResult)){
                            String discount = counponItem.getString( "discount");
                            float discountInt = Float.parseFloat(discount);
                            float rate = (100-discountInt)/100;
                            Cart cart = cartRepository.getCarts();
                            float disPrise = cart.getTotalPrize()*rate;
                            cart.setTotalPrize(disPrise);
                            Log.d(TAG,"prise "+ cart.getTotalPrize() + ", "+ disPrise);
                            mView.showRecycleView(cart);
                            mView.showToast("Coupon Applied");
                            discuontRate = rate;
                            findFLG = true;
                        }
                        if (!findFLG){
                            mView.showToast("Invalid coupon");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
            }
        });

        volley.addToRequestQueue(stringRequest,"coupon");
    }

    @Override
    public void checkPayment(final String address, final String bill, String cardNum, String month, String year, String cvc) {

        Card card = new Card(
                cardNum,
                Integer.parseInt(month),
                Integer.parseInt(year),
                cvc
        );

        if(!card.validateNumber()){
            mView.showToast("Card Number is not valid");
        }
        else if(!card.validateCVC()){
            mView.showToast("Card CVC is not valid");
        }


        stripe.createToken(card, new TokenCallback() {
            @Override
            public void onError(Exception error) {
                Log.e(TAG,error.getMessage());
                mView.showToast(error.getMessage());
            }

            @Override
            public void onSuccess(Token token) {
                Log.d(TAG,token.getCard().toString());
                placeOrder(address,bill);
            }
        });


    }

    @Override
    public void getUserInfor() {
        ///userRepository.getUser()
    }

    private void placeOrder(String address, String bill) {
        UserImformation user = userRepository.getUser();
        String userid = user.getUserId();
        String name = user.getFirstName();
        String mobile = user.getMobile();
        String email = user.getEmail();
        String api_key = user.getUserAppApiKey();


        final Cart cart = cartRepository.getCarts();
        int count = cart.getCartSize();

        final float paid = cart.getTotalPrize()*discuontRate;

        String url= "http://rjtmobile.com/aamir/e-commerce/android-app/orders.php?";
        String item="";


        for(int i=0;i<count;i++) {
            Product product = cart.getProduct(i);

            item = item.concat("&item_id=");
            item = item.concat(product.getId());
            item = item.concat("&item_names=");
            item = item.concat(product.getPname());
            item = item.concat("&item_quantity=");
            item = item.concat(Integer.toString(product.getUserAmount()));
            item = item.concat("&final_price=");
            float prise = Float.parseFloat(product.getPrize()) * discuontRate;
            item = item.concat(Float.toString(prise));


        }


            url = url + item +"&&api_key="+api_key+"&user_id="+userid+"&user_name="+name+"&billingadd="
                    +bill+"&deliveryadd="+address+"&mobile="+mobile+"&email="+email;

            Log.d(TAG,url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        OrderList orderList = new OrderList();
                        Log.d(TAG,"response" + response);
                        JSONObject orderConfirm = new JSONObject(response);
                        JSONArray orderArray = orderConfirm.getJSONArray("Order confirmed");
                        for (int j=0;j<orderArray.length();j++){
                            JSONObject orderJSON = orderArray.getJSONObject(j);
                            String orderId = orderJSON.getString("orderid");
                            String orderStatus = orderJSON.getString("orderstatus");
                            String name = orderJSON.getString("name");
                            String bill = orderJSON.getString("billingadd");
                            String address = orderJSON.getString("deliveryadd");
                            String mobile = orderJSON.getString("mobile");
                            String email = orderJSON.getString("email");
                            String date = orderJSON.getString("placedon");

                            Order newOrder = new Order(orderId,
                                    orderStatus,name,bill,address,mobile,email,paid,date,cart);
                            orderList.addOrder(newOrder);

                        }
                        mView.showOrderComfirmation(orderList);
                        mView.showToast("Order placed");
                        cartRepository.clearCart();

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

            volley.addToRequestQueue(stringRequest,"place_order");


    }


}
