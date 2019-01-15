package com.example.fang.walmartproject.profile;

import com.example.fang.walmartproject.data.UserImformation;

public interface ProfileContract {
    interface ProfileView{
        void showSignInPage();

        void showTexts(UserImformation user);

        void showToast(String message);

        void showResetPasswordPage();
    }

    interface ProfilePresenter{
        void onSignInHandled();
        void setText();
        void onUpdateHandled(String fname, String lname, String email,String mobile,String address);
        void onResetHandled();

    }
}
