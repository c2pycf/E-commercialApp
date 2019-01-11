package com.example.fang.walmartproject.login;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;


import com.example.fang.walmartproject.R;

public class LoginActivity extends Activity {
    AppBarLayout appBarLayout;
    Button loginButton;
    EditText loginEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appBarLayout = findViewById(R.id.appBarLayout_login);
        loginEditText = findViewById(R.id.ed_phone_sign);
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setActionBar(mToolbar);

        loginEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appBarLayout.setVisibility(View.GONE);

            }
        });
        loginButton = findViewById(R.id.bt_login);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int scrollRange = appBarLayout.getTotalScrollRange();

                if (scrollRange  == i) {
                    loginButton.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void onForgetClicked(View view){

    }

    public void onCreateClicked(View view){

    }

    public void onLoginClicked(View view){

    }
}
