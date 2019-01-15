package com.example.fang.walmartproject.profile;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.UserImformation;
import com.example.fang.walmartproject.data.source.UserDataSource;
import com.example.fang.walmartproject.data.source.UserRepository;

public class ProfilePresenter implements ProfileContract.ProfilePresenter {
    ProfileContract.ProfileView mView;
    UserDataSource reprository;
    AppController volley;
    static private final String TAG = ProfilePresenter.class.getSimpleName();

    public ProfilePresenter(ProfileFragment context) {
        this.mView = context;
        volley = AppController.getInstance();
        reprository = new UserRepository(context.getContext());
    }

    @Override
    public void onSignInHandled() {
        mView.showSignInPage();
    }

    @Override
    public void setText() {
        UserImformation user =reprository.getUser();
        mView.showTexts(user);
    }

    @Override
    public void onUpdateHandled(final String fname, final String lname, final String email, final String mobile, final String address) {
        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/edit_profile.php?fname="+fname+"&lname="+lname+"&address="+address+"& email="+email+"&mobile="+mobile;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    Log.d(TAG,response);
                    if(response.equals("successfully updated")){
                        mView.showToast(response);

                        reprository.saveFname(fname);
                        reprository.saveLname(lname);
                        reprository.saveEmail(email);
                        reprository.saveAddress(address);
                        reprository.saveMobile(mobile);
                        //show toast and save to db
                    }
                    else {
                        //show toast
                        mView.showToast(response);
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
            }
        });

        volley.addToRequestQueue(stringRequest,"updateUser");
    }

    @Override
    public void onResetHandled() {
        mView.showResetPasswordPage();
    }
}
