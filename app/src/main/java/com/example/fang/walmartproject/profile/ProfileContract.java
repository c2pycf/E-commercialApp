package com.example.fang.walmartproject.profile;

public interface ProfileContract {
    interface ProfileView{
        void showSignInPage();
    }

    interface ProfilePresenter{
        void onSignInHandled();
        void setText();

    }
}
