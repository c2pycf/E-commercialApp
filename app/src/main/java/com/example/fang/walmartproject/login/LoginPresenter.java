package com.example.fang.walmartproject.login;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.UserImformation;
import com.example.fang.walmartproject.network.GetDataService;
import com.example.fang.walmartproject.network.RetrofitClientInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter implements LoginContract.LoginPresenter {
    LoginContract.LoginView mView;
    Retrofit mRetrofit;
    AppController volley;

    static final String TAG = "LoginPresenter";

    public LoginPresenter(LoginActivity activity) {
        this.mView = activity;
        mRetrofit = RetrofitClientInstance.getInstance();

        volley = AppController.getInstance();
    }

    @Override
    public void onLoginHandled(final String phone, final String password) {
        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?";
        //Post url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    List<UserImformation> userInformationList = new ArrayList<>();
                    JSONArray userArray = new JSONArray(response);
                    for(int i=0;i<userArray.length();i++){
                        JSONObject user =  userArray.getJSONObject(i);
                        String message = user.getString("msg");
                        String userId = user.getString("id");
                        String fname = user.getString("firstname");
                        String lname = user.getString("lastname");
                        String email = user.getString("email");
                        String mobile = user.getString("mobile");
                        String userApiKey = user.getString("appapikey ");
                        UserImformation userImformation = new UserImformation(message,userId,fname,lname,email,mobile,userApiKey);
                        userInformationList.add(userImformation);
                    }
                    Log.d(TAG,userInformationList.get(0).getLoginMessage());
                    mView.showToast(userInformationList.get(0).getLoginMessage());
                    saveUser(userInformationList.get(0));
                    volley.setSignFlag();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile",phone);
                params.put("password",password);
                return params;
            }
        };

       volley.addToRequestQueue(stringRequest,"SignIn");

       mView.finishSignIn();







        /*GetDataService dataService = mRetrofit.create(GetDataService.class);

        Call<List<UserImformation>> call = dataService.getLoginResult(phone,password);
        call.enqueue(new Callback<List<UserImformation>>() {
            @Override
            public void onResponse(Call<List<UserImformation>> call, Response<List<UserImformation>> response) {
                List<UserImformation> userList = response.body();
                String message = userList.get(0).getLoginMessage();
                Log.d(TAG,message);
                if(message=="success" ){
                    UserImformation newUser = userList.get(0);
                    saveUser(newUser);
                    mView.showToast(message);
                }

            }

            @Override
            public void onFailure(Call<List<UserImformation>> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });*/


    }

    private void saveUser(UserImformation newUser) {

    }

    @Override
    public void onForgetHandled() {
        //findpassowrd page
    }

    @Override
    public void onCreateHandled() {
        //Jump to register
        mView.startRegistration();
    }
}
