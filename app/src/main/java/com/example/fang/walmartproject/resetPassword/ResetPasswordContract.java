package com.example.fang.walmartproject.resetPassword;

public interface ResetPasswordContract {
    interface ResetView{
        void showToast(String message);
    }

    interface ResetPresenter{
        void onReset(String oldPassword,String newPassword);
    }
}
