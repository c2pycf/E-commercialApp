package com.example.fang.walmartproject.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.UserImformation;
import com.example.fang.walmartproject.login.LoginActivity;
import com.example.fang.walmartproject.resetPassword.ResetPasswordActivity;


public class ProfileFragment extends Fragment implements ProfileContract.ProfileView {
    AppController volley;
    EditText fnameEditText,lnameEditText,addressEditText,emailEditText,phoneEditText;
    LinearLayout profileContent,progressLayout;
    Button signInButton, updateButton, resetPassword;
    ProfilePresenter mProfilePresenter;
    static String TAG = ProfileFragment.class.getSimpleName();

    public ProfileFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profie,container,false);
        volley = AppController.getInstance();
        Log.d(TAG,"Fragment on creat");
        fnameEditText = view.findViewById(R.id.et_fname_pro);
        lnameEditText = view.findViewById(R.id.et_lname_pro);
        addressEditText = view.findViewById(R.id.et_address_pro);
        emailEditText = view.findViewById(R.id.et_emil_pro);
        phoneEditText = view.findViewById(R.id.et_mobile_pro);
        profileContent = view.findViewById(R.id.profile_content);
        signInButton = view.findViewById(R.id.bt_login_pro);
        updateButton = view.findViewById(R.id.bt_update_pro);
        resetPassword =view.findViewById(R.id.bt_reset_pro);
        mProfilePresenter = new ProfilePresenter(this);
        progressLayout = view.findViewById(R.id.progress_layout_pro);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
        Log.d(TAG,"resume "+volley.getSignFlag());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updating();
            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProfilePresenter.onResetHandled();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProfilePresenter.onSignInHandled();
            }
        });

        if(volley.getSignFlag()==1){
            //getdata
            Log.d(TAG,"showing");
            profileContent.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.GONE);
            mProfilePresenter.setText();
        }
        else {
            profileContent.setVisibility(View.GONE);
            signInButton.setVisibility(View.VISIBLE);

        }

    }

    private void updating() {

        progressLayout.setVisibility(View.VISIBLE);

        if(!fnameEditText.getText().toString().isEmpty()&&
                !lnameEditText.getText().toString().isEmpty()&&
                !emailEditText.getText().toString().isEmpty()&&
                !phoneEditText.getText().toString().isEmpty()&&
                !addressEditText.toString().isEmpty()) {
            String fname = fnameEditText.getText().toString();
            String lname = lnameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String address = addressEditText.getText().toString();
            mProfilePresenter.onUpdateHandled(fname, lname,email,phone,address);
        }
        else {
            showToast("Please fill all up..");
        }
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"resume");
        if(volley.getSignFlag()==1){
            //getdata
            Log.d(TAG,"showing");
            profileContent.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.GONE);
            mProfilePresenter.setText();
        }
        else {
            profileContent.setVisibility(View.GONE);
            signInButton.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showSignInPage() {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        getActivity().startActivity(intent);

    }

    @Override
    public void showTexts(UserImformation user) {
        fnameEditText.setText(user.getFirstName());
        lnameEditText.setText(user.getLastName());
        emailEditText.setText(user.getEmail());
        phoneEditText.setText(user.getMobile());
        addressEditText.setText(user.getAddress());
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResetPasswordPage() {
        Intent intent = new Intent(getActivity(),ResetPasswordActivity.class);
        startActivity(intent);
    }


}
