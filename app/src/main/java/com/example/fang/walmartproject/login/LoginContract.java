package com.example.fang.walmartproject.login;

public interface LoginContract {
    interface LoginView{
        void showToast(String message);

        void startRegistration();

        void finishSignIn();
    }

    interface FogetView{}

    interface  LoginPresenter{
        void onLoginHandled(String phone, String password);

        void onForgetHandled();

        void onCreateHandled();
    }

    interface  FogetPresenter{}
}
