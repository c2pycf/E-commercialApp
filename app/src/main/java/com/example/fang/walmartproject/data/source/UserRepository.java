package com.example.fang.walmartproject.data.source;

import android.content.Context;

import com.example.fang.walmartproject.data.UserImformation;
import com.example.fang.walmartproject.data.source.local.UserSharePreference.UserLocalDataSource;

public class UserRepository implements UserDataSource{

    UserLocalDataSource userLocalDataSource;

    public UserRepository(Context context) {
        this.userLocalDataSource = new UserLocalDataSource(context);
    }

    @Override
    public void saveUser(UserImformation userImformation) {
        userLocalDataSource.saveUser(userImformation);
    }

    @Override
    public UserImformation getUser() {
       return userLocalDataSource.getUser();
    }

    @Override
    public void updateUser(UserImformation userImformation) {
        userLocalDataSource.updateUser(userImformation);
    }

    @Override
    public void signOut() {
        userLocalDataSource.signOut();
    }

    @Override
    public void saveFname(String fname) {

    }

    @Override
    public void saveLname(String lname) {

    }

    @Override
    public void saveEmail(String email) {

    }

    @Override
    public void saveAddress(String address) {

    }

    @Override
    public void saveMobile(String mobile) {

    }


}
