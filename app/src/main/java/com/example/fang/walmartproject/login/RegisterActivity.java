package com.example.fang.walmartproject.login;
import com.example.fang.walmartproject.R;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView{

    EditText fnameEditText,lnameEditText,addressEditText,emailEditText,phoneEditText,passwordEditText;
    RegisterPresenter mPresenter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPresenter = new RegisterPresenter(this);
        toolbar = findViewById(R.id.registration_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign up");
        bindId();

    }

    private void bindId() {
        fnameEditText = findViewById(R.id.et_fname_re);
        lnameEditText = findViewById(R.id.et_lname_re);
        addressEditText = findViewById(R.id.et_address_re);
        emailEditText = findViewById(R.id.et_emil_re);
        phoneEditText = findViewById(R.id.et_mobile_re);
        passwordEditText = findViewById(R.id.et_password_re);
    }

    public void onContinueClicked(View view){
        if(!fnameEditText.getText().toString().isEmpty()&& !lnameEditText.getText().toString().isEmpty()
                &&!addressEditText.getText().toString().isEmpty()&&!emailEditText.getText().toString().isEmpty()
                &&!phoneEditText.getText().toString().isEmpty()&&!passwordEditText.toString().isEmpty()) {
            String fname = fnameEditText.getText().toString();
            String lname = lnameEditText.getText().toString();
            String address = addressEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            Pattern emailPatter = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            if (emailPatter.matcher(email).matches()) {

                mPresenter.onRegisterHandle(fname, lname, address, password, email, phone);
            }
            else showToast("Invalid email format");
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
