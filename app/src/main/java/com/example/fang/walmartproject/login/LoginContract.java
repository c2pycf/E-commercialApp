package com.example.fang.walmartproject.login;

public interface LoginContract {
    interface LoginView{
        void showToast(String message);

        void startRegistration();

        void finishSignIn();

        void startFindPassword();
    }

    interface ForgetView{
        void showToast(String message);
    }

    interface  LoginPresenter{
        void onLoginHandled(String phone, String password);

        void onForgetHandled();

        void onCreateHandled();
    }

    interface  ForgetPresenter{
        void onContinue(String email);
    }
}
