package com.example.fang.walmartproject.resetPassword;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.source.UserDataSource;
import com.example.fang.walmartproject.data.source.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResetPasswordPresenter implements ResetPasswordContract.ResetPresenter {
    private ResetPasswordContract.ResetView mView;
    private UserDataSource repository;
    private AppController volley;
    static private final String TAG = ResetPasswordPresenter.class.getSimpleName();

    public ResetPasswordPresenter(ResetPasswordActivity activity) {
        this.mView = activity;
        volley = AppController.getInstance();
        repository = new UserRepository(activity.getBaseContext());
    }


    @Override
    public void onReset(String oldPassword, final String newPassword) {
        String mobile = repository.getUser().getMobile();
        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reset_pass.php?&mobile="+mobile+"&password="+oldPassword+"&newpassword="+newPassword;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String message = "";
                try {
                    JSONObject resetMessage = new JSONObject(response);
                    JSONArray msgArray = resetMessage.getJSONArray( "msg");
                    message = msgArray.getString(0);
                        mView.showToast(message);

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

        volley.addToRequestQueue(stringRequest,"Reset Password");
    }
}
