package com.example.fang.walmartproject.resetPassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fang.walmartproject.R;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordContract.ResetView {

    private ResetPasswordContract.ResetPresenter mPresenter;
    private EditText oldPasswordEditText,newPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mPresenter = new ResetPasswordPresenter(this);
        oldPasswordEditText = findViewById(R.id.et_old_password_reset);
        newPasswordEditText = findViewById(R.id.et_new_password_reset);
    }

    public void onResetClicked(View view){
        if(oldPasswordEditText.getText()==null||newPasswordEditText.getText()==null){
            this.showToast("Please fill out all field!");
        }
        else if(oldPasswordEditText.getText().equals(newPasswordEditText.getText())){
            this.showToast("New password can not be the same as Old password");
        }
        else {
            mPresenter.onReset(oldPasswordEditText.getText().toString(), newPasswordEditText.getText().toString());
        }

    }

    @Override
    public void showToast(String message) {

    }
}
