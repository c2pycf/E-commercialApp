package com.example.fang.walmartproject.login;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.os.Bundle;


import android.support.design.widget.CollapsingToolbarLayout;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.homePage.HomePageActivity;

public class LoginActivity extends Activity implements LoginContract.LoginView{
    AppBarLayout appBarLayout;
    Button loginButton;
    EditText loginEditText, passwordEditText;
    ConstraintLayout constraintLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    LoginPresenter mPresenter;

    static final String TAG = "LoginView";
    static int SIGN_FLAG = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appBarLayout = findViewById(R.id.appBarLayout_login);
        loginEditText = findViewById(R.id.ed_phone_sign);
        passwordEditText = findViewById(R.id.ed_password_sign);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        constraintLayout = findViewById(R.id.appbar_content);
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mPresenter = new LoginPresenter(this);

        setActionBar(mToolbar);

       

        loginEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        loginButton = findViewById(R.id.bt_login);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int scrollRange = appBarLayout.getTotalScrollRange();

                if (scrollRange + i ==0) {
                    loginButton.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void onForgetClicked(View view){

    }

    public void onCreateClicked(View view){
        mPresenter.onCreateHandled();
    }

    public void onLoginClicked(View view){
        if (loginEditText.getText()!=null && passwordEditText.getText()!=null ) {
            String phone = loginEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            mPresenter.onLoginHandled(phone,password);

        }

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startRegistration() {
        Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }

    @Override
    public void finishSignIn() {
        SIGN_FLAG=1;
//        Intent homeIntent = new Intent(LoginActivity.this,HomePageActivity.class);
//        startActivity(homeIntent);
        finish();
    }
}
