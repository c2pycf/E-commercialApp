package com.example.fang.walmartproject.checkOut;

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
import com.simplify.android.sdk.Card;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckOutPresenter implements CheckOutContract.CheckOutPresenter {
    CheckOutContract.CheckOutView mView;
    CartDataSource cartRepository;
    AppController volley;
    UserDataSource userRepository;
    Simplify simplify;
    float discuontRate = (float) 1.00;

    static final String TAG = CheckOutPresenter.class.getSimpleName();

    public CheckOutPresenter(CheckOutActivity activity) {
        this.mView = activity;
        cartRepository = new CartRepository(activity.getBaseContext());
        volley = AppController.getInstance();
        userRepository =  new UserRepository(activity.getBaseContext());

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
        //simplify = new Simplify();
        //simplify.setApiKey("sbpb_Mjk0NjYwYTEtYmM2YS00YTE0LWFhMDEtZjcyMGQ1YzIzOThj");
//        Card card = new Card()
//                .setNumber(cardNum)
//                .setExpMonth(month)
//                .setExpYear(year)
//                .setCvc(cvc);
//
//        simplify.createCardToken(card, new CardToken.Callback() {
//            @Override
//            public void onSuccess(CardToken cardToken) {
//                mView.showToast("Proceed the order...");
//                placeOrder(address,bill);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Log.e(TAG, throwable.getMessage());
//                mView.showToast(throwable.getMessage());
//            }
//        });
        placeOrder(address,bill);

    }

    private void placeOrder(String address, String bill) {
        UserImformation user = userRepository.getUser();
        String userid = user.getUserId();
        String name = user.getFirstName();
        String mobile = user.getMobile();
        String email = user.getEmail();
        String api_key = user.getUserAppApiKey();

        Cart cart = cartRepository.getCarts();
        int count = cart.getCartSize();
        final OrderList orderList = new OrderList();

        for(int i=0;i<count;i++) {

            Product product = cart.getProduct(i);
            String productId =product.getId();
            String pname = product.getPname();
            int quantity = product.getUserAmount();
            Float prise = Float.parseFloat(product.getPrize())*discuontRate;
            String url = "http://rjtmobile.com/aamir/e-commerce/android-app/orders.php?&item_id="
            +productId+"&item_names="+pname+"&item_quantity="+quantity+"&final_price="+prise
                    +"&&api_key="+api_key+"&user_id="+userid+"&user_name="+name+"&billingadd="
                    +bill+"&deliveryadd="+address+"&mobile="+mobile+"&email="+email;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
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
                            String itemId = orderJSON.getString("itemid");
                            String itemName = orderJSON.getString("itemname");
                            String itemQuantity = orderJSON.getString("itemquantity");
                            String totalPrise = orderJSON.getString("totalprice");
                            String paidPrise = orderJSON.getString("paidprice");
                            String date = orderJSON.getString("placedon");

                            Order newOrder = new Order(orderId,
                                    orderStatus,name,bill,address,mobile,email,itemId,
                                    itemName,itemQuantity,totalPrise,paidPrise,date);
                            orderList.addOrder(newOrder);
                        }

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

            volley.addToRequestQueue(stringRequest,"place_order_"+ i);

        }

        mView.showOrderComfirmation(orderList);
    }
}
