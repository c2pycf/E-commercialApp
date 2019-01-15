package com.example.fang.walmartproject.data.source.local.UserSharePreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fang.walmartproject.data.UserImformation;
import com.example.fang.walmartproject.data.source.UserDataSource;

public class UserLocalDataSource implements UserDataSource {
    private SharedPreferences preferences;

    public UserLocalDataSource(Context context) {
        this.preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
    }

    @Override
    public void saveUser(UserImformation userImformation) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("message",userImformation.getLoginMessage());
        editor.putString("userId", userImformation.getUserId());
        editor.putString("fname",userImformation.getFirstName());
        editor.putString("lname",userImformation.getLastName());
        editor.putString("email",userImformation.getEmail());
        editor.putString("mobile",userImformation.getMobile());
        editor.putString("userApiKey",userImformation.getUserAppApiKey());

        editor.apply();

    }

    @Override
    public UserImformation getUser() {
        String message =  preferences.getString("message","");
        String userId = preferences.getString("userId","");
        String fname = preferences.getString("fname","");
        String lname = preferences.getString("lname","");
        String email = preferences.getString("email","");
        String mobile = preferences.getString("mobile","");
        String userApi = preferences.getString("userApiKey","");
        UserImformation user = new UserImformation(message,userId,fname,lname,email,mobile,userApi);
        return user;
    }

    @Override
    public void updateUser(UserImformation userImformation) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("message",userImformation.getLoginMessage());
        editor.putString("userId", userImformation.getUserId());
        editor.putString("fname",userImformation.getFirstName());
        editor.putString("lname",userImformation.getLastName());
        editor.putString("address",userImformation.getAddress());
        editor.putString("email",userImformation.getEmail());
        editor.putString("mobile",userImformation.getMobile());
        editor.putString("userApiKey",userImformation.getUserAppApiKey());

        editor.apply();
    }

    @Override
    public void signOut() {

    }

    @Override
    public void saveFname(String fname) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fname",fname);
        editor.apply();

    }

    @Override
    public void saveLname(String lname) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("lname",lname);
        editor.apply();
    }

    @Override
    public void saveEmail(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email",email);
        editor.apply();

    }

    @Override
    public void saveAddress(String address) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("address",address);
        editor.apply();

    }

    @Override
    public void saveMobile(String mobile) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mobile",mobile);
        editor.apply();

    }


}
