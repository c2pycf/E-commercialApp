package com.example.fang.walmartproject.login;
import com.example.fang.walmartproject.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends AppCompatActivity implements LoginContract.ForgetView{

    private LoginContract.ForgetPresenter mPresenter;
    static private final String TAG = ForgetPasswordActivity.class.getSimpleName();
    private EditText emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ForgetPasswordPresenter(this);
        setContentView(R.layout.activity_forget_password);
        emailEditText = findViewById(R.id.ed_reset_email);
    }

    public void onContinueClicked(View view){
        //Not implement yet
        if(emailEditText.getText()!=null) {
            String email = emailEditText.getText().toString();
            mPresenter.onContinue(email);
        }
        else {
            this.showToast("Email can not be empty");
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
