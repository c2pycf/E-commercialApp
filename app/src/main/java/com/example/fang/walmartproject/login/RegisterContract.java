package com.example.fang.walmartproject.login;

public interface RegisterContract {
    interface RegisterView{
        void showToast(String message);

        void finish();
    }

    interface Register{
        void onRegisterHandle(String fname, String lname, String address, String password, String email, String mobile);

    }
}
