package com.example.fang.walmartproject.login;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;

public class ForgetPasswordPresenter implements LoginContract.ForgetPresenter {
   private LoginContract.ForgetView mView;
   private AppController volley;
   static private final String TAG = ForgetPasswordPresenter.class.getSimpleName();

    ForgetPasswordPresenter(ForgetPasswordActivity activity) {
        this.mView = activity;
        volley = AppController.getInstance();
    }

    @Override
    public void onContinue(String email) {
        String url ="http://rjtmobile.com/aamir/e-commerce/android-app/forgot_pass_email.php?email="+email;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mView.showToast(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
            }
        });

        volley.addToRequestQueue(stringRequest,"forget_password");
    }
}
