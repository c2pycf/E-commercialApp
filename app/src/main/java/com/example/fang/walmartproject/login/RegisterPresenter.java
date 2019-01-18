package com.example.fang.walmartproject.login;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.source.remote.network.RetrofitClientInstance;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

public class RegisterPresenter implements RegisterContract.Register {
    Retrofit mRetrofit;
    RegisterContract.RegisterView mView;
    AppController volley;
    String result;

    static final String TAG = "Registration";

    public RegisterPresenter(RegisterActivity activity) {
        this.mView = activity;
        mRetrofit = RetrofitClientInstance.getInstance();

        volley =  AppController.getInstance();
    }

    @Override
    public void onRegisterHandle(final String fname, final String lname, final String address, final String password, final String email, final String mobile) {

        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?fname="+fname+"&lname="+
                lname+"&address="+address+"&email="+email+"&mobile="+mobile+"&password="+password;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,response);
                mView.showToast(response);
                result = response;
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
                mView.showToast(error.getMessage());
            }
        });

       volley.addToRequestQueue(stringRequest,"Registration");
//
//        if(result.equals("successfully registered")){
//            mView.finish();
//        }
//        GetDataService dataService = mRetrofit.create(GetDataService.class);
//        Call<String> call = dataService.getRegistrationResult(fname,lname,address,email,mobile,password);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                String message = response.body();
//                mView.showToast(message);
//                mView.finish();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e(TAG,t.getMessage());
//            }
//        });

    }
}
