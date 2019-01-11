package com.example.fang.walmartproject.login;

public interface LoginContract {
    interface LoginView{}

    interface FogetView{}

    interface  LoginPresenter{
        void onLoginHandled();

        void onForgetHandled();

        void onCreateHandled();
    }

    interface  FogetPresenter{}
}
