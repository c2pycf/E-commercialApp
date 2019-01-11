package com.example.fang.walmartproject.login;

public class LoginPresenter implements LoginContract.LoginPresenter {
    LoginContract.LoginView mView;

    public LoginPresenter(LoginContract.LoginView mView) {
        this.mView = mView;
    }

    @Override
    public void onLoginHandled() {
        //Post url
    }

    @Override
    public void onForgetHandled() {
        //findpassowrd page
    }

    @Override
    public void onCreateHandled() {
        //Jump to register
    }
}
